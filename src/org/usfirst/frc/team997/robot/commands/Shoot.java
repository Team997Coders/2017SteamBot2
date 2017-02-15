package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Shoot extends Command {

	public static double shootSpeed;
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.shooterMotorMaster.enable();
    	Robot.shooter.shooterMotorSlave.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(Robot.oi.getRightTrigger() > 0) {
    		shootSpeed = SmartDashboard.getNumber("Shooter Speed", 0);
    	} else {
    		shootSpeed = 0;
    	}
    	Robot.shooter.shooterMotorMaster.setSetpoint(shootSpeed);
    	Robot.shooter.shooterMotorSlave.set(Robot.shooter.shooterMotorMaster.getDeviceID());
    	SmartDashboard.putNumber("Shooter output", Robot.shooter.shooterMotorMaster.getSpeed());
    	SmartDashboard.putNumber("Shooter error", Robot.shooter.shooterMotorMaster.getClosedLoopError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.shooterMotorMaster.set(0);
    	Robot.shooter.shooterMotorSlave.set(Robot.shooter.shooterMotorMaster.getDeviceID());
    	Robot.shooter.shooterMotorMaster.disable();
    	Robot.shooter.shooterMotorSlave.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.shooterMotorMaster.set(0);
    	Robot.shooter.shooterMotorSlave.set(Robot.shooter.shooterMotorMaster.getDeviceID());
    }
}