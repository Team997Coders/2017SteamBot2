package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.GatherTrigger;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gatherer extends Subsystem {
	private Solenoid gatherSolenoid;
	private Talon gatherMotor;
	
	public Gatherer() {
		gatherSolenoid = new Solenoid(RobotMap.Ports.gatherSolenoidPort);
		gatherMotor = new Talon(RobotMap.Ports.gatherTalon);
	}
	
	public void spinInward() {
		gatherMotor.set(RobotMap.Values.gatherSpeed);
	}
	
	public void stop() {
		gatherMotor.set(0);
	}
	
	public void releaseSolenoid() {
		gatherSolenoid.set(true);
	}
	public void resetSolenoid() {
		gatherSolenoid.set(false);
	}

    public void initDefaultCommand() { setDefaultCommand(new GatherTrigger()); }
}

