package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueStraightGEAR extends CommandGroup{

    public AutoBlueStraightGEAR() {
    	addSequential(new DriveToDistance((-114 + 28)-RobotMap.Values.blueOffset)); // drive backwards into gear deposit
    }
    
}

   