package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearShootRight extends CommandGroup {

    public AutoGearShootRight() {
       
    	addSequential(new AutoGearRight()); // deposit gear
    	addSequential(new AutoShoot()); // use vision tracking to align and shoot
    	
    }
}
