package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShakeGatherer extends Command {
    private Timer timer;

    public ShakeGatherer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gatherer);
    	timer = new Timer();
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (timer.get() >= .2) {
    		Robot.gatherer.spitOut();
    	} else {
    		Robot.gatherer.spinInward();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() >= .4;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gatherer.stop();
    }
}
