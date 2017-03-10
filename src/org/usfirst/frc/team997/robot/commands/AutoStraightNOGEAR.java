package org.usfirst.frc.team997.robot.commands;

/**
 *
 */
public class AutoStraightNOGEAR extends CancelableCommandGroup {
    public AutoStraightNOGEAR() {
    	addSequential(new DriveToDistance(-110)); // drive backwards over base line
    	addSequential(new AutoDepositGear());
    }
}
