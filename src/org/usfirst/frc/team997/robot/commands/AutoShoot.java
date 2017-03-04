package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.VisionAngleSource;
import org.usfirst.frc.team997.robot.VisionDistanceSource;
import org.usfirst.frc.team997.robot.VisionStorage;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {

    public AutoShoot() {
    	
    	
    	// put vision tracking alignment command here
    	addSequential(new DriveToAngle(0, new VisionAngleSource()));
    	addSequential(new DriveToDistance(10, new VisionDistanceSource()));

    	addSequential(new Shoot()); // shoot *woosh*
    	addParallel(new ElevatorDelayedIn(2));
    }
}
