package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueRightGear extends CommandGroup {

	Timer timer;
	
    public AutoBlueRightGear() {
       
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalForward)); // drive backward towards airship
    	addSequential(new DriveToAngle(-60)); // turn to position
    	while(timer.get() <= 2){}
    	timer.stop();
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalCross)); // drive backwards to place gear
    	
    }
}
