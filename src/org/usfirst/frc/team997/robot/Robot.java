package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.AutoGearLeft;
import org.usfirst.frc.team997.robot.commands.AutoGearRight;
import org.usfirst.frc.team997.robot.commands.AutoGearShootLeft;
import org.usfirst.frc.team997.robot.commands.AutoGearShootRight;
import org.usfirst.frc.team997.robot.commands.AutoGearStraight;
import org.usfirst.frc.team997.robot.commands.AutoNullCommand;
import org.usfirst.frc.team997.robot.commands.DriveToAngle;
import org.usfirst.frc.team997.robot.subsystems.Climber;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.Elevator;
import org.usfirst.frc.team997.robot.subsystems.Gatherer;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain driveTrain;
	public static Gatherer gatherer;
	public static Shooter shooter;
	public static DriveToAngle driveToAngleCommand;
	public static Climber climber;
	public static PowerDistributionPanel pdp;
	public static Elevator elevator;

	public static Preferences prefs;
	
	public static UDPReceive udpReceive;
	
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	Command autoSelected;
	SendableChooser<Command> chooser = new SendableChooser<>();
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		try {
			shooter = new Shooter(1, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//try {
			driveTrain = new DriveTrain();
			//driveToAngleCommand = new DriveToAngle();
		/*} catch (Exception e){
			e.printStackTrace();
		}*/
			
		try {
			gatherer = new Gatherer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		elevator = new Elevator();
		
		pdp = new PowerDistributionPanel();

		climber = new Climber();
		
		// Set up the Autonomous Chooser to select auto mode
		chooser.addDefault("AutoGearStraight", new AutoGearStraight());
		chooser.addObject("Null", new AutoNullCommand());
		chooser.addObject("AutoGearLeft", new AutoGearLeft());
		chooser.addObject("AutoGearRight", new AutoGearRight());
		chooser.addObject("AutoGearShootLeft", new AutoGearShootLeft());
		chooser.addObject("AutoGearShootRight", new AutoGearShootRight());
		
		SmartDashboard.putData("Auto choices", chooser);

		prefs = Preferences.getInstance();
		
		udpReceive = new UDPReceive();
		
		//OI INITIALIZATION MUST MUST MUST MUST BE LAST
		oi = new OI();
	}
	
	private void pollPreferences() {
		// Set up preference variables for the shooter PID to allow easier tuning
		// and storage of the variables
		RobotMap.PrefVars.Shooter_P = prefs.getDouble("Shooter PID P", 0.0);
		RobotMap.PrefVars.Shooter_I = prefs.getDouble("Shooter PID I", 0.0);
		RobotMap.PrefVars.Shooter_D = prefs.getDouble("Shooter PID D", 0.0);
		RobotMap.PrefVars.Shooter_F = prefs.getDouble("Shooter PID F", 0.0);
		RobotMap.PrefVars.Shooter_defSpeed = prefs.getDouble("Shooter Default Speed", 3000.0);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		driveTrain.resetEncoders();
		driveTrain.resetGyro();
		autoSelected = (Command) chooser.getSelected();
		autoSelected.start();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		pollPreferences();
		SmartDashboard.putNumber("DriveTrain Yaw", Robot.driveTrain.ahrs.getYaw());
		/*switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}*/
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		driveTrain.resetEncoders();
		driveTrain.resetGyro();
	}

	private int tickcount;
	@Override
	public void teleopPeriodic() {
		pollPreferences();
    	SmartDashboard.putNumber("DriveTrain Yaw", Robot.driveTrain.ahrs.getYaw());
    	++tickcount;
    	SmartDashboard.putNumber("tickCount", tickcount);
    	Scheduler.getInstance().run();
    	
    	/*
    	 * SmartDashboard.putNumber("DriveTrain Left Current 1", pdp.getCurrent(RobotMap.PDP.leftDriveMotor[0]));
    	 * SmartDashboard.putNumber("DriveTrain Left Current 2", pdp.getCurrent(RobotMap.PDP.leftDriveMotor[1]));
    	 * SmartDashboard.putNumber("DriveTrain Left Current 3", pdp.getCurrent(RobotMap.PDP.leftDriveMotor[2]));
         *    	
    	 * SmartDashboard.putNumber("DriveTrain Right Voltage 1", pdp.getCurrent(RobotMap.PDP.rightDriveMotor[0]));
    	 * SmartDashboard.putNumber("DriveTrain Right Voltage 2", pdp.getCurrent(RobotMap.PDP.rightDriveMotor[1]));
    	 * SmartDashboard.putNumber("DriveTrain Right Voltage 3", pdp.getCurrent(RobotMap.PDP.rightDriveMotor[2]));
    	 */
	}
	
	public void disabledPeriodic() {
    	SmartDashboard.putNumber("DriveTrain Yaw", Robot.driveTrain.ahrs.getYaw());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
    	SmartDashboard.putNumber("DriveTrain Yaw", Robot.driveTrain.ahrs.getYaw());
	}
	
	public static double deadband(double x) {
		if(Math.abs(x) < 0.05) {
			return 0;
		} 
		return x;
	}
	
	public static double clamp(double x) {
		if(x > 1) {
			return 1;
		} else if(x < -1) {
			return -1;
		}
		return x;
	}
	
	public static double negSqRt(double x) {
		if(x > 0) {
			return Math.sqrt(x);
		} else {
			return -Math.sqrt(-x);
		}
	}
	
	public static double negSq(double x) {
		if(x > 0) {
			return (x*x);
		} else {
			return -(x*x);
		}
	}
	
	public static double cube(double x) {
		if(x > 0) {
			return (4*((x-0.5)*(x-0.5)*(x-0.5))) + 0.5;
		} else {
			return (4*((-x+0.5)*(-x+0.5)*(-x+0.5))) + 0.5;
		}
	}

	public static double averageCurrent(int[] ports) {
		double result = 0;
		if (ports.length != 0) {
			for (int i = 0; i != ports.length; ++i) {
				result += pdp.getCurrent(ports[i]);
			}
			result /= ports.length;
		}
		return result;
	}
}

