package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedLeftGear extends CommandGroup {
	
	Timer timer;

    public AutoRedLeftGear() {
    	
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalForward)); // drive backwards a bit
    	addSequential(new DriveToAngle(60)); // turn right towards airship
    	while(timer.get() <= 2){}
    	timer.stop();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalCross)); // drive backwards some more
    	// add gear deposit command here
    }
}
