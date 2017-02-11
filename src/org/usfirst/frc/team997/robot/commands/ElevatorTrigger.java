package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorTrigger extends Command {

    public ElevatorTrigger() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.deadband(Robot.oi.getLeftTrigger())==0){
    		Robot.elevator.stop();
    	}else{
    		Robot.elevator.spinInward();	
    	}
    	/*
    	 * in here, u need to make an if statement that gets whether the left trigger is
    	 * active (WITH deadband applied). if so, it will run the elevator in command;
    	 * if not, it will run the elevator stop commmand. this toggle runs forever.
    	 *  
    	 */
    	 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
