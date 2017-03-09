package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearStraight extends CommandGroup {
    public AutoGearStraight() {
    	addSequential(new DriveToDistance(-112 + RobotMap.Values.robotLength)); // drive backwards into gear deposit
    	addSequential(new AutoDepositGear());
    }
}
