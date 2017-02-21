package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.VisionStorage;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionDriveToAngle extends Command {
    public VisionDriveToAngle() {}

    // Called just before this Command runs the first time
    protected void initialize() {
//    	VisionStorage vs = Robot.udpReceive.work();
//    	Scheduler.getInstance().add(new DriveToAngle(
//    			Math.atan(vs.offset / vs.distan)));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
}
