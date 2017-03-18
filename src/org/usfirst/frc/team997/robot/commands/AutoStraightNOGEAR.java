package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightNOGEAR extends CommandGroup {
    public AutoStraightNOGEAR() {
    	addSequential(new DriveToDistance(-110)); // drive backwards over base line
    }
}
