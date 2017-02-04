package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class StartShoot extends Command {
	protected void initialize() {
		Shoot.shootSpeed = RobotMap.Values.shooterSpeed;
	}

	protected boolean isFinished() {
		return true;
	}
}
