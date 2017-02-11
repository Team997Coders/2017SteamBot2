package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final SerialPort.Port AHRSPort = SerialPort.Port.kUSB;
	
	public static class PDP {
		public static final int[]
		leftDriveMotor = {0, 1, 2},
		rightDriveMotor = {3, 14, 15}, 
		climberTalon = {12, 13};
		
		public static final int 
		ballLiftTalon = 11,
		gatherTalon = 10;
		//shooterMotorMaster = 1,
		//shooterMotorSlave = 2;
		
	}
	
	public static class Ports {
		public static final int
		//PWM
		leftDriveMotor = 0,
		rightDriveMotor = 1,
		climberTalon = 2,
		ballLiftTalon = 3,
		gatherTalon = 4,
		
		//Digital IO
		leftEncoderOne = 0,
		leftEncoderTwo = 1,
		rightEncoderOne = 2,
		rightEncoderTwo = 3,
		gatherSensor = 4,
		ballLiftSensor = 5,
		
		//Analog
		ultraSonic = 0;
	}
	
	
	public static class Values {
		public static final double

		shooterF = 3.965,
		shooterP = 30,
		shooterI = 0.0,
		shooterD = 500,
		
		shooterSpeed = 3500; 
		
	}
	
}
