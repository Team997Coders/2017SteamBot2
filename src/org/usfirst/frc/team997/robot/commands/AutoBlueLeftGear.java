package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeftGear extends CommandGroup {

	Timer timer;
	
    public AutoBlueLeftGear() {
        
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyForward)); //drive backward towards airship
    	addSequential(new DriveToAngle(60)); //turn at angle
    	while(timer.get() <= 2){}
    	timer.stop();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyCross)); //drive backward to place gear
    	
    }
}
