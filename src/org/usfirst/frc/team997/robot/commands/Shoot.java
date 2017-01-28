package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team997.robot.OI;
import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;


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
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.shooterMotorMaster.set(shootSpeed);
    	Robot.shooter.shooterMotorSlave.set(Robot.shooter.shooterMotorMaster.getDeviceID());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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