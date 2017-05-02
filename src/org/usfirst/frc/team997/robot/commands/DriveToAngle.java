package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.AHRSWrapper;
import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team997.robot.CustomDashboard;

/**
 *
 */
public class DriveToAngle extends Command implements PIDOutput {
	public PIDController controller;
	private double pidRate;
	private double setPoint;

	public DriveToAngle(double angle) { this(angle, new AHRSWrapper()); }
    public DriveToAngle(double angle, PIDSource source) {
    	requires(Robot.driveTrain);
    	setPoint = angle;

    	Robot.driveTrain.ahrs.reset();

    	controller = new PIDController(1.25, 0, 0.9, 0, source, this); //p originally 0.75
    	controller.setInputRange(-180, 180);
    	controller.setOutputRange(-.25, .25);
    	controller.setAbsoluteTolerance(1.0);
    	controller.setContinuous(true);

    	LiveWindow.addActuator("DriveToAngle", "RotationController", controller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double setPoint = Robot.driveTrain.ahrs.getYaw() + this.setPoint;
    	if (setPoint > 180) setPoint -= 360;
    	if (setPoint < -180) setPoint += 360;
    	controller.setSetpoint(setPoint);
    	CustomDashboard.putNumber("DriveToAngle Setpoint", controller.getSetpoint());
    	controller.enable();
    	CustomDashboard.putBoolean("DriveToAngleOn", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CustomDashboard.putNumber("DriveToAngle currentRotationRate", pidRate);
    	CustomDashboard.putNumber("DriveToAngle Error", controller.getError());
    	//if (pidRate < .17 && pidRate > 0) { pidRate = .17; }
    	//if (pidRate > -.17 && pidRate <= 0) { pidRate = -.17; }

    	Robot.driveTrain.driveAutoVoltage(pidRate, -pidRate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return controller.onTarget(); //&& !Robot.driveTrain.ahrs.isMoving();
    }

    // Called once after isFinished returns true
    protected void end() {
    	controller.disable();
    	Robot.driveTrain.driveVoltage(0, 0);
    	CustomDashboard.putBoolean("DriveToAngleOn", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	public void pidWrite(double output) {
		final double min = .2;
		if (output < min && output > 0) {
			pidRate = min;
		} else if (output > -min && output < 0) {
			pidRate = -min;
		} else {
			pidRate = output;
		}
	}
}
