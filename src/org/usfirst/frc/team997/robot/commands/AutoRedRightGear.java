package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedRightGear extends CommandGroup {
    public AutoRedRightGear() {
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyForward - RobotMap.Values.redOffset)); // drive backwards a bit
    	addSequential(new DriveToAngle(-60)); // turn left towards airship
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyCross)); // drive backwards some more
    	//addSequential(new AutoDepositGear());
    }
}
