package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climb extends Command {
    public Climb() {
    	requires(Robot.climber);
    }

    protected void initialize() {}

    protected void execute() {
     	//Robot.climber.climb(RobotMap.Values.climbSpeed);
    	Robot.climber.safeClimb(RobotMap.Values.climbSpeed);
    }

    protected boolean isFinished() {
    	return !Robot.oi.climbButton.get();
    }

    protected void end() {
    	Robot.climber.climb(0);
    }

    protected void interrupted() {
    	end();
    }
}
