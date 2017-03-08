package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class UltraSonic extends Subsystem {
	public AnalogInput ultraSonic;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public UltraSonic() {
		ultraSonic = new AnalogInput(RobotMap.Ports.ultraSonic);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

