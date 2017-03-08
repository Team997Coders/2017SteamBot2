package org.usfirst.frc.team997.robot;

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
		climberTalon = {12, 13},
		ballLiftTalon = {11},
		gatherTalon = {10};
	}
	
	public static class Ports {
		public static final int
		//PWM
		leftDriveMotor = 0,
		rightDriveMotor = 1,
		climberTalon = 2,
		elevatorTalon = 3,
		gatherTalon = 4,
		
		//Digital IO
		leftEncoderOne = 2,
		leftEncoderTwo = 3,
		rightEncoderOne = 0,
		rightEncoderTwo = 1,
		gatherSensor = 4,
		elevatorSensor = 5,
		
		//Analog
		ultraSonic = 0,
		
		//Pneumatic???
		gatherSolenoidPort = 3;
	}
	
	
	public static class Values {
		public static final double
		robotWidth = 30, // FIXME
		robotLength = 30, // FIXME

		autoKeyForward = 129.125 - (162 - 70.5/Math.sqrt(3) - robotWidth/2 - 73*Math.sqrt(2))/2 - robotLength/2,
		autoKeyCross = (162 - 70.5/Math.sqrt(3) - robotWidth/2 - 73*Math.sqrt(2))*Math.sqrt(3)/2 - robotLength/2,

		autoRetrievalForward = 129.125 - (162 - 70.5/Math.sqrt(3) - robotWidth/2 - 53.75)/2 - robotLength/2,
		autoRetrievalCross = (162 - 70.5/Math.sqrt(3) - robotWidth/2 - 53.75)*Math.sqrt(3)/2 - robotLength/2,

		shooterF = 10,
		shooterP = 0,//30,
		shooterI = 0.0,
		shooterD = 0,//500,
		
		driveTrainP = 0.005,
		driveTrainI = 0,
		driveTrainD = 0,
		driveTrainF = 0,
		
		shooterSpeed = 1555,

		climbSpeed = .8,
		gatherSpeed = -1;
	}
	
	public static class PrefVars {
		public static double 
		
		Shooter_P,
		Shooter_I,
		Shooter_D,
		Shooter_F,
		Shooter_defSpeed;
	}
}
