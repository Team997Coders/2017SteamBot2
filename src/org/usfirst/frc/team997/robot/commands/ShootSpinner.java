package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShootSpinner extends Command {
	
	public ShootSpinner() {
		requires(Robot.shooter);
	}

	protected void excecute() {
		if(Robot.oi.getLeftTrigger() > 0) {
			Robot.driveTrain.driveSpeed = 0.4;
		} else {
			Robot.driveTrain.driveSpeed = 1.0;
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
}
