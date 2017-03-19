package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team997.robot.CustomDashboard;

public class SetDriveToAngle extends Command {
	private double setpoint;
	public SetDriveToAngle(double setpoint) {
		this.setpoint = setpoint;
	}

	protected void initialize() {
		CustomDashboard.putNumber("SetDriveToAngle setSetpoint", setpoint);
		Robot.driveToAngleCommand.controller.setSetpoint(setpoint);
	}

	protected boolean isFinished() {
		return true;
	}
}
