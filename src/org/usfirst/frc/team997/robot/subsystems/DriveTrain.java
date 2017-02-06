package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public AHRS ahrs;
	private SpeedController left, right;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveTrain() {
		ahrs = new AHRS(RobotMap.AHRSPort);
		left = new VictorSP(RobotMap.leftPort);
		right = new VictorSP(RobotMap.rightPort);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
    
    public void driveVoltage(double leftV, double rightV) {
    	left.set(-leftV);
    	right.set(rightV);
    }
    
    
}

