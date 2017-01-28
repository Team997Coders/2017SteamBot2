package org.usfirst.frc.team997.robot;

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
	
	public static class PDP {
		public static final int
		shooterMotorMaster = 1,
		shooterMotorSlave = 2;
	}
	
	public static class Values {
		public static final double

		shooterF = 3.965,
		shooterP = 25,
		shooterI = 0.0,
		shooterD = 500,
		
		shooterSpeed = 3500; 
	}
	
}
