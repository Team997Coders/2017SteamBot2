package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTurnToPeg extends Command {
	private double error;
	private double speed = 0.5;
	private boolean onTarget = false;

    public VisionTurnToPeg() {
         requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = RobotMap.centerX - 238;	//238 = arbitrary center value
    	
    	if (error > 0) {
    		//more voltage to right side of robot, less to right
    		Robot.driveTrain.driveAutoVoltage(speed*error, -speed);
    	} else if (error < 0) {
    		//less voltage to right side of robot, more to left
    		Robot.driveTrain.driveAutoVoltage(speed, -speed*error);
    	} else if (error == 0) {
    		Robot.driveTrain.driveAutoVoltage(speed, speed);
    		onTarget = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveAutoVoltage(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
