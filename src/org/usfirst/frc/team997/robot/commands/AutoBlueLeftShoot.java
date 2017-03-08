package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeftShoot extends CommandGroup {
    public AutoBlueLeftShoot() {
        addSequential(new AutoBlueLeftGear()); // do the gear thing
        addSequential(new AutoShoot()); // Chris says this will work
    }
}
