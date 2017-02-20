package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {

    public AutoShoot() {
    	// put vision tracking alignment command here
    	

    	addSequential(new Shoot()); // shoot *woosh*
    	addParallel(new ElevatorDelayedIn());
    }
}
