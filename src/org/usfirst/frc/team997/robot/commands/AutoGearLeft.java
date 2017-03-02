package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearLeft extends CommandGroup {

    public AutoGearLeft() {
    	
    	addSequential(new DriveToDistance(-(129.125 - (162 - (70.5/Math.sqrt(3)) -68.75) / 2))); // drive backwards a bit
    	addSequential(new DriveToAngle(60)); // turn right towards airship
    	addSequential(new DriveToDistance(-(162 - (20.5/Math.sqrt(3)) -68.75) *Math.sqrt(3) /2)); // drive backwards some more
    	// add gear deposit command here
    	
    }
}
