package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearShootLeft extends CommandGroup {

    public AutoGearShootLeft() {
        
    	addSequential(new AutoGearLeft()); // deposit gear
    	addSequential(new AutoShoot()); // use vision tracking to align and shoot
    	
    }
}
