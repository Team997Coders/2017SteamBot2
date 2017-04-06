package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedStraightGEAR extends CommandGroup{

    public AutoRedStraightGEAR() {
    	addSequential(new DriveToDistance((-114 + RobotMap.Values.robotLength)-RobotMap.Values.redOffset)); // drive backwards into gear deposit
    	addSequential(new AutoDepositGear());
    }
    
}

   