package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot extends Command {
	public double shootSpeed;
	public boolean done;

	public Shoot() { this(true); }
    public Shoot(boolean require) {
    	if (require) requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.shooterMotorMaster.enable();
    	Robot.shooter.shooterMotorSlave.enable();
    	shootSpeed = 0;
    	done = false;
    	
        Robot.shooter.shooterMotorMaster.setF(RobotMap.PrefVars.Shooter_F);
        Robot.shooter.shooterMotorMaster.setP(RobotMap.PrefVars.Shooter_P);
        Robot.shooter.shooterMotorMaster.setI(RobotMap.PrefVars.Shooter_I);
        Robot.shooter.shooterMotorMaster.setD(RobotMap.PrefVars.Shooter_D);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.shooterMotorMaster.set(RobotMap.PrefVars.Shooter_defSpeed);
    	Robot.shooter.shooterMotorSlave.set(Robot.shooter.shooterMotorMaster.getDeviceID());
    	SmartDashboard.putNumber("Shooter speed", Robot.shooter.shooterMotorMaster.getSpeed());
    	SmartDashboard.putNumber("Shooter error", Robot.shooter.shooterMotorMaster.getClosedLoopError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !done;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.shooterMotorMaster.set(0);
    	Robot.shooter.shooterMotorSlave.set(Robot.shooter.shooterMotorMaster.getDeviceID());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.shooterMotorMaster.set(0);
    	Robot.shooter.shooterMotorSlave.set(Robot.shooter.shooterMotorMaster.getDeviceID());
    }
}
