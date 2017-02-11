package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gatherer extends Subsystem {
	public Solenoid gatherSolenoid;
	public Talon gatherMotor;
	
	public Gatherer() {
		gatherSolenoid = new Solenoid(RobotMap.Ports.gatherSolenoidPort);
		gatherMotor = new Talon(RobotMap.Ports.gatherTalon);
	}
	
	public void gather(double speed) {
		gatherMotor.set(speed);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

