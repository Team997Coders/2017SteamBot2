package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ShootTrigger extends Command {
	private Shoot shoot;

    public ShootTrigger() {
    	requires(Robot.shooter);
    	shoot = new Shoot(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shoot.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getRightTrigger() > 0) {
    		shoot.shootSpeed = SmartDashboard.getNumber("Shooter Setpoint", 0);
    	} else {
    		shoot.shootSpeed = 0;
    	}
    	shoot.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shoot.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shoot.interrupted();
    }
}