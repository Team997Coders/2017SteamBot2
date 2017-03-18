package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	public void safeClimb(double climbSpeed){
		if(Robot.pdp.getCurrent(RobotMap.PDP.climberTalon[0]) < 40 | Robot.pdp.getCurrent(RobotMap.PDP.climberTalon[1]) < 40) // 40 is a magic number atm
			climberMotor.set(climbSpeed);
		else {
			System.out.println("Climber Overcurrent");
			climberMotor.set(climbSpeed * .9); // change if necessary
		}
		
	}

    public void initDefaultCommand() {}
}