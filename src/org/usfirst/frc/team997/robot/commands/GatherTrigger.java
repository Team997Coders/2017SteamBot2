package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GatherTrigger extends Command {
    public GatherTrigger() {
    	requires(Robot.gatherer);
    }

    protected void initialize() {
    }
 
    protected void execute() {
    	if (0 == Robot.deadband(Robot.oi.getLeftTrigger())) {
    		Robot.gatherer.stop();
    	} else {
    		Robot.gatherer.spinInward();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.gatherer.stop();
    }

    protected void interrupted() {
    	end();
    }
}
