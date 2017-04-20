package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMeasured extends DriveToDistance {

    public AutoMeasured() {
    	super(0);
    }
    
    public void initialized() {
    	super.setPoint = RobotMap.Values.measured;
    	super.initialize();
    }
}
