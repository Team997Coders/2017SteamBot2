package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearStraight extends CommandGroup {

    public AutoGearStraight() {    	
    
    	//112=field length, 30=robot length
    	addSequential(new DriveToDistance(-112 + 30)); // drive backwards into gear deposit
    	
    	
    	// add gear deposit command here
    	
    	//For testing purposes
    	//addSequential(new DriveToDistance(60));
    	//addSequential(new DriveToAngle(90));
    	
    }
}
