package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorSpinWhileHeld extends Command {
    public ElevatorSpinWhileHeld() {
    	requires(Robot.elevator);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.elevator.spinInward();
    }

    protected boolean isFinished() {
        return !Robot.oi.elevatorButton.get();
    }

    protected void end() {
    	Robot.elevator.stop(); }

    protected void interrupted() {
    	end(); }
}
