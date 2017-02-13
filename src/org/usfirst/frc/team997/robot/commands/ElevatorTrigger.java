package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorTrigger extends Command {
    public ElevatorTrigger() {
    	requires(Robot.elevator);
    }

    protected void initialize() {}

    protected void execute() {
    	if (0 == Robot.deadband(Robot.oi.getLeftTrigger())) {
    		Robot.elevator.stop();
    	} else {
    		Robot.elevator.spinInward();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {}
}
