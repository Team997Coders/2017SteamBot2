package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Climber extends Subsystem {
	private SpeedController climberMotor; // unknown if using victor for climber

	public Climber() {
		climberMotor = new Talon(RobotMap.Ports.climberTalon);	
		
		LiveWindow.addActuator("Climber", "Motor", (Talon) climberMotor);
	}

	public void climb(double climbSpeed){
		climberMotor.set(climbSpeed);
	}

    public void initDefaultCommand() {}
}