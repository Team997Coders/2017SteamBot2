package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.VisionAngleSource;
import org.usfirst.frc.team997.robot.VisionDistanceSource;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {
    public AutoShoot() {
    	// Align angle then distance
    	addSequential(new DriveToAngle(0, new VisionAngleSource()));
    	addSequential(new DriveToDistance(10, new VisionDistanceSource()));

    	// Then shoot
    	addParallel(new ElevatorDelayedIn(2));
    	addSequential(new Shoot()); // shoot *woosh*
    }
}
