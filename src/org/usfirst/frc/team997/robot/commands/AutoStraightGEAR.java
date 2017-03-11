package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightGEAR extends CommandGroup{

    public AutoStraightGEAR() {
    	addSequential(new DriveToDistance(-110 + RobotMap.Values.robotLength)); // drive backwards into gear deposit
    	addSequential(new AutoDepositGear());
    }
    
}

   