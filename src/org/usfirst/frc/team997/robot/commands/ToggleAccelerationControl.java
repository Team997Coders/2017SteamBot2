package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleAccelerationControl extends Command implements Sendable {
	protected void initialize() {
		Drive.useAccelerationControl = !Drive.useAccelerationControl;
		if (Drive.useAccelerationControl) {
			Robot.driveTrain.startPID();
		} else {
			Robot.driveTrain.stopPID();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
