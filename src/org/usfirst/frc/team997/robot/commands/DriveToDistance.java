package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.DistanceEncoder;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team997.robot.CustomDashboard;

/**
 *
 */
public class DriveToDistance extends Command implements PIDOutput{
	private double pidRate;
	public PIDController controller;
	private double setPoint;
	private double initAngle;

	public DriveToDistance(double distance) { this(distance, new DistanceEncoder(Robot.driveTrain.rightEncoder)); }
    public DriveToDistance(double distance, PIDSource source) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setPoint = distance;

    	controller = new PIDController(0.3, 0, 1, source, this);
    	controller.setOutputRange(-.35, .35);
    	controller.setAbsoluteTolerance(.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	controller.setSetpoint(setPoint + Robot.driveTrain.rightEncoder.getDistance());
    	controller.enable();
    	CustomDashboard.putBoolean("DriveToDistance On", true);
    	initAngle = Robot.driveTrain.ahrs.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	Robot.driveTrain.driveVoltage(-pidRate, 0.06*Robot.driveTrain.ahrs.getAngle());   	
    	double angleOffset = Robot.driveTrain.ahrs.getAngle() - initAngle;
    	
    	//checks if angles are within range, if not puts them in range
    	if(angleOffset < -180) {  
    		angleOffset += 360;
    	} else if(angleOffset > 180) {
    		angleOffset -= 360;
    	} 
    	
    	double mult = -.03;
    	CustomDashboard.putNumber("DriveToDistance Arcade boost", angleOffset * mult);
    	Robot.driveTrain.driveVoltage(Robot.clamp(pidRate + angleOffset * mult), Robot.clamp(pidRate - angleOffset * mult));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return controller.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	controller.disable();
    	Robot.driveTrain.driveVoltage(0, 0);
    	CustomDashboard.putBoolean("DriveToDistance On", false);
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
