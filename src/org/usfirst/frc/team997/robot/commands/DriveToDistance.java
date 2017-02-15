package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToDistance extends Command implements PIDOutput{
	private double pidRate;
	public PIDController controller;
	private double setPoint;

    public DriveToDistance(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setPoint = distance;
    	
    	Robot.driveTrain.ahrs.reset();
    	
    	controller = new PIDController(0.5/setPoint, 0, (setPoint-Robot.driveTrain.leftEncoder.getDistance())-0.01, Robot.driveTrain.leftEncoder, this);
    	controller.setInputRange(-180, 180);
    	controller.setOutputRange(-1, 1);
    	controller.setAbsoluteTolerance(0.01);
    	controller.setContinuous(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	controller.setSetpoint(setPoint);
    	controller.enable();
    	SmartDashboard.putBoolean("DriveToDistance On", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveVoltage(-pidRate, 0.06*Robot.driveTrain.ahrs.getAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	controller.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void pidWrite(double output) {
    	pidRate = output;
    }
}
