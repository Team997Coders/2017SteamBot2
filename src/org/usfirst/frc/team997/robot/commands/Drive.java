package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.isTank) {
    		Robot.driveTrain.driveVoltage(Robot.deadband(Robot.oi.getLeftY()), 
    										Robot.deadband(Robot.oi.getRightY()));
    	} else {
    		double aleft = Robot.oi.getLeftY();
    		double aright = Robot.oi.getRightX();
    		Robot.driveTrain.driveVoltage(Robot.deadband(aleft + aright),
    										Robot.deadband(aleft - aright));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveVoltage(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}