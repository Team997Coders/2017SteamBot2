package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Gather extends Command {
	public static double gatherSpeed;
	public static boolean isGather;

    public Gather() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gatherer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.oi.gatherButton.get()) {
    		gatherSpeed = RobotMap.Values.gatherSpeed;
    		isGather = false;
    	} else {
    		gatherSpeed = 0;
    		isGather = true;
    	}
    	Robot.gatherer.gather(gatherSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isGather;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gatherer.gatherMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
