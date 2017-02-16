package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToAngle extends Command implements PIDOutput {
	public PIDController controller;
	private double pidRate;
	private double setPoint;

    public DriveToAngle(double angle) {
    	requires(Robot.driveTrain);
    	setPoint = angle;

    	Robot.driveTrain.ahrs.reset();

    	controller = new PIDController(0.015, 0, 0, Robot.driveTrain.ahrs, this);
    	controller.setInputRange(-180, 180);
    	controller.setOutputRange(-.3, .3);
    	controller.setAbsoluteTolerance(2.0);
    	controller.setContinuous(true);

    	LiveWindow.addActuator("DriveToAngle", "RotationController", controller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	controller.setSetpoint(setPoint);
    	controller.enable();
    	SmartDashboard.putBoolean("DriveToAngleOn", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("DriveToAngle currentRotationRate", pidRate);

    	Robot.driveTrain.driveVoltage(-pidRate, pidRate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       
    	if(controller.get() == setPoint) {
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	controller.disable();
    	SmartDashboard.putBoolean("DriveToAngleOn", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	public void pidWrite(double output) {
		pidRate = output;
	}
}
