package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearLeft extends CommandGroup {

    public AutoGearLeft() {
    	
    	// AS OF NOW, THESE NUMBERS ARE ARBITRARY.
    	
    	addSequential(new DriveToDistance(-50)); // drive backwards a bit
    	addSequential(new DriveToAngle(45)); // turn right towards airship
    	addSequential(new DriveToDistance(-49)); // drive backwards some more
    	// add gear deposit command here
    	
    }
}
