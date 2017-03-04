package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRightGear extends CommandGroup {
	
	Timer timer;

    public AutoRedRightGear() {
    	
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyForward)); // drive backwards a bit
    	addSequential(new DriveToAngle(-60)); // turn left towards airship
    	while(timer.get() <=2){}
    	timer.stop();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyCross)); // drive backwards some more
    	// add gear deposit command here
    	
    }
}
