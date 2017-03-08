package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueRightGear extends CommandGroup {
    public AutoBlueRightGear() {
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalForward)); // drive backward towards airship
    	addSequential(new DriveToAngle(-60)); // turn to position
    	addSequential(new DriveToDistance(-RobotMap.Values.autoRetrievalCross)); // drive backwards to place gear
    	addSequential(new AutoDepositGear());
    }
}
