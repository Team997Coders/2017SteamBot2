package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReverseGatherHold extends Command {
	public ReverseGatherHold() {
		requires(Robot.gatherer);
	}

	public void execute() {
		Robot.gatherer.spitOut();
	}

	@Override
	protected boolean isFinished() {
		return !Robot.oi.reverseGatherButton.get();
	}

	public void end() {
		Robot.gatherer.stop();
	}
}
