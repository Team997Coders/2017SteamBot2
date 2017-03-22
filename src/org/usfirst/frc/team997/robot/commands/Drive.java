package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
    public static boolean useAccelerationControl = false;
    

	public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (useAccelerationControl) {
    		Robot.driveTrain.startPID();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left, right;
    	if(Robot.oi.isTank) {
    		left = Robot.deadband(Robot.oi.getLeftY());
    		right = Robot.deadband(Robot.oi.getRightX());
    	} else {
    		double aleft = Robot.deadband(Robot.oi.getLeftY());
    		double aright = Robot.deadband(Robot.oi.getRightX()) * (RobotMap.Values.driveTrainMaxTurn - (RobotMap.Values.driveTrainMaxTurn - RobotMap.Values.driveTrainMinTurn) * Math.abs(aleft));
    		left = aleft + aright;
    		right = aleft - aright;
    	}
    	if (useAccelerationControl) {
    		Robot.driveTrain.drivePID(left * 200, right * 200);
    	} else {
    		if(Robot.oi.useDeccelerationControl && Robot.oi.forward) {
        		Robot.driveTrain.driveDeccel(Robot.clamp(left), Robot.clamp(right));
        		
        	} else if(Robot.oi.useDeccelerationControl && !Robot.oi.forward) {
        		Robot.driveTrain.driveVoltage(-Robot.clamp(left), -Robot.clamp(right));
        		
        	} else if(Robot.oi.forward && !Robot.oi.useDeccelerationControl) {
    			Robot.driveTrain.driveVoltage(Robot.clamp(left), Robot.clamp(right));
    			
    		} else if(!Robot.oi.forward && !Robot.oi.useDeccelerationControl) {
    			Robot.driveTrain.driveVoltage(-Robot.clamp(left), -Robot.clamp(right));
    			
    		}
    		
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (useAccelerationControl) {
    		Robot.driveTrain.stopPID();
    	}
    	Robot.driveTrain.driveVoltage(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
