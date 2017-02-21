package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.startPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left, right;
    	if(Robot.oi.isTank) {
    		left = Robot.deadband(Robot.oi.getLeftY());
    		right = Robot.deadband(Robot.oi.getRightX());
    	} else {
    		double aleft = Robot.deadband(Robot.oi.getLeftY());
    		double aright = Robot.deadband(Robot.oi.getRightX() * .8);
    		left = aleft + aright;
    		right = aleft - aright;
    	}
    	Robot.driveTrain.drivePID(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopPID();
    	Robot.driveTrain.driveVoltage(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
