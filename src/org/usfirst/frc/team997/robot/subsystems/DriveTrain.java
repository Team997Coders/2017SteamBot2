package org.usfirst.frc.team997.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.DriveToAngle;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public AHRS ahrs;
	public SpeedController left;
	public SpeedController right;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveTrain() {
		ahrs = new AHRS(RobotMap.AHRSPort);
		left = new VictorSP(RobotMap.leftPort);
		right = new VictorSP(RobotMap.rightPort);
	}

    public void initDefaultCommand() {}
    
    public void driveVoltage(double leftV, double rightV) {
    	left.set(-leftV);
    	right.set(rightV);
    }
}

