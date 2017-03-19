package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team997.robot.CustomDashboard;

/**
 *
 */
public class ExtendGatherer extends Command {
	private Timer timer;

    public ExtendGatherer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gatherer);
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	CustomDashboard.putBoolean("Extending gatherer", true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gatherer.releaseSolenoid();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gatherer.resetSolenoid();
    	
    	CustomDashboard.putBoolean("Extending gatherer", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
