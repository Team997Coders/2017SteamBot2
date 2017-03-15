package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team997.robot.CustomDashboard;

/**
 *
 */
public class Climb extends Command {
    public Climb() {
    	requires(Robot.climber);
    }

    protected void initialize() {}

    protected void execute() {
    	double current = Robot.averageCurrent(RobotMap.PDP.climberTalon);
    	CustomDashboard.putNumber("Climber current", current);
    	Robot.climber.climb();
    }

    protected boolean isFinished() {
    	return !Robot.oi.climbButton.get();
    }

    protected void end() {
    	Robot.climber.stopClimb();
    }

    protected void interrupted() {
    	end();
    }
}
