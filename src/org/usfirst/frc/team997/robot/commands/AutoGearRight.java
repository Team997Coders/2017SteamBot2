package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearRight extends CommandGroup {

    public AutoGearRight() {
    	
    	// AS OF NOW, THESE NUMBERS ARE ARBITRARY.
    	
    	addSequential(new DriveToDistance(-50)); // drive backwards a bit
    	addSequential(new DriveToAngle(-45)); // turn left towards airship
    	addSequential(new DriveToDistance(-49)); // drive backwards some more
    	// add gear deposit command here
    	
    }
}
