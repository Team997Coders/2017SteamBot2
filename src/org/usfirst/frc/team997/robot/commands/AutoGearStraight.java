package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearStraight extends CommandGroup {

    public AutoGearStraight() {    	
    	
    	// AS OF NOW, THESE NUMBERS ARE ARBITRARY.
    	
    	//addSequential(new DriveToDistance(-99)); // drive backwards into gear deposit
    	
    	// add gear deposit command here
    	
    	addSequential(new DriveToDistance(60));
    	addSequential(new DriveToAngle(90));
    	
    }
}
