package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.DistanceEncoder;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToDistance extends Command implements PIDOutput{
	private double pidRate;
	public PIDController controller;
	private double setPoint;

	public DriveToDistance(double distance) { this(distance, new DistanceEncoder(Robot.driveTrain.rightEncoder)); }
    public DriveToDistance(double distance, PIDSource source) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setPoint = distance;

    	controller = new PIDController(0.5, 0, 0.5, source, this);
    	controller.setOutputRange(-.5, .5);
    	controller.setAbsoluteTolerance(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	controller.setSetpoint(setPoint + Robot.driveTrain.rightEncoder.getDistance());
    	controller.enable();
    	SmartDashboard.putBoolean("DriveToDistance On", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.driveTrain.driveVoltage(-pidRate, 0.06*Robot.driveTrain.ahrs.getAngle());
    	Robot.driveTrain.driveVoltage(pidRate, pidRate);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	controller.disable();
    	SmartDashboard.putBoolean("DriveToDistance On", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void pidWrite(double output) {
    	pidRate = output;
    }
}
