package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class StopShoot extends Command {
	protected void initialize() {
		Shoot.shootSpeed = 0;
	}
	
	protected boolean isFinished() {
		return true;
	}
}