package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climb extends Command {
	private boolean released = false;
    public Climb() {
    	requires(Robot.climber);
    }

    protected void initialize() {released = false;}

    protected void execute() {
    	double current = Robot.averageCurrent(RobotMap.PDP.climberTalon);
    	SmartDashboard.putNumber("Climber current", current);
    	Robot.climber.climb(RobotMap.Values.climbSpeed);
    }

    protected boolean isFinished() {
    	if (!released) {
    		released = !Robot.oi.climbButton.get();
    		return false;
    	} else {
    		return Robot.oi.climbButton.get();
    	}
    }

    protected void end() {
    	Robot.climber.climb(0);
    }

    protected void interrupted() {
    	end();
    }
}
