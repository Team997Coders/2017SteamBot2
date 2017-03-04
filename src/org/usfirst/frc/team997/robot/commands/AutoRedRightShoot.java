package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRightShoot extends CommandGroup {

    public AutoRedRightShoot() {
        addSequential(new AutoRedRightGear()); // do the gear thing
        addSequential(new AutoShoot()); //Chris says this will work
    }

    
}
