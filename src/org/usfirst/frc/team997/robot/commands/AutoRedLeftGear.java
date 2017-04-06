package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedLeftGear extends CommandGroup {
    public AutoRedLeftGear() {
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalForward - RobotMap.Values.redOffset)); // drive backwards a bit
    	addSequential(new DriveToAngle(60)); // turn right towards airship
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalCross)); // drive backwards some more
//    	addSequential(new AutoDepositGear());
    }
}
