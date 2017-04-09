package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.CustomDashboard;
import org.usfirst.frc.team997.robot.Loggable;
import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class UltraSonic extends Subsystem implements Loggable {
	public AnalogInput ultraSonic;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public UltraSonic() {
		Robot.logList.add(this);
		ultraSonic = new AnalogInput(RobotMap.Ports.ultraSonic);
	}
	
	public void log() {
		CustomDashboard.putNumber("UltraSonic Voltage", ultraSonic.getVoltage());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

