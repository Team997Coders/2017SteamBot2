package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueLeftGear extends CommandGroup {
    public AutoBlueLeftGear() {
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyForward - RobotMap.Values.blueOffset)); //drive backward towards airship
    	addSequential(new DriveToAngle(60)); //turn at angle
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyCross)); //drive backward to place gear
    	addSequential(new AutoDepositGear());
    }
}
