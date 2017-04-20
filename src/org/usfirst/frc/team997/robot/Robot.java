package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.AutoRedLeftGear;
import org.usfirst.frc.team997.robot.commands.AutoRedRightGear;
import org.usfirst.frc.team997.robot.commands.AutoRedStraightGEAR;
import org.usfirst.frc.team997.robot.commands.AutoBlueLeftGear;
import org.usfirst.frc.team997.robot.commands.AutoBlueRightGear;
import org.usfirst.frc.team997.robot.commands.AutoBlueStraightGEAR;
import org.usfirst.frc.team997.robot.commands.AutoMeasuredTurnBlueLeft;
import org.usfirst.frc.team997.robot.commands.AutoMeasuredTurnBlueRight;
import org.usfirst.frc.team997.robot.commands.AutoMeasuredTurnRedLeft;
import org.usfirst.frc.team997.robot.commands.AutoMeasuredTurnRedRight;
import org.usfirst.frc.team997.robot.commands.AutoStraightNOGEAR;
import org.usfirst.frc.team997.robot.commands.AutoNullCommand;
import org.usfirst.frc.team997.robot.commands.DriveToAngle;
import org.usfirst.frc.team997.robot.subsystems.Arduino;
import org.usfirst.frc.team997.robot.subsystems.Climber;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.Elevator;
import org.usfirst.frc.team997.robot.subsystems.Gatherer;
import org.usfirst.frc.team997.robot.subsystems.UltraSonic;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import java.util.ArrayList;

import org.usfirst.frc.team997.robot.CustomDashboard;

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
	public static DriveToAngle driveToAngleCommand;
	public static Climber climber;
	public static PowerDistributionPanel pdp;
	public static Elevator elevator;
	public static UltraSonic ultraSonic;
	public static Arduino arduino;
	
	public static ArrayList<Loggable> logList;
	
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
		logList = new ArrayList<Loggable>();
		
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
		final String autoChoicesNumber = "Fri Apr 21 08:14:42 2017 7faf79307f3b32cd85ed687619c6e62dc4a0e0e1";
		chooser.addDefault("Null " + autoChoicesNumber, new AutoNullCommand());
		chooser.addObject("Straight (No Gear)", new AutoStraightNOGEAR());
		chooser.addObject("Red Straight", new AutoRedStraightGEAR());
		chooser.addObject("Blue Straight", new AutoBlueStraightGEAR());
		chooser.addObject("Red Left", new AutoRedLeftGear());
		chooser.addObject("Red Right", new AutoRedRightGear());
		chooser.addObject("Blue Left", new AutoBlueLeftGear());
		chooser.addObject("Blue Right", new AutoBlueRightGear());
		chooser.addObject("Red Left Measured", new AutoMeasuredTurnRedLeft());
		chooser.addObject("Red Right Measured", new AutoMeasuredTurnRedRight());
		chooser.addObject("Blue Left Measured", new AutoMeasuredTurnBlueLeft());
		chooser.addObject("Blue Right Measured", new AutoMeasuredTurnBlueRight());
	
		
		CustomDashboard.putData("Auto Choices " + autoChoicesNumber, chooser);

		udpReceive = new UDPReceive();

		prefs = Preferences.getInstance();

		pollPreferences();
		
		ultraSonic = new UltraSonic();
		
		try {
			arduino = new Arduino();
			if(DriverStation.getInstance().getAlliance() == Alliance.Red) {
				arduino.sendRed();
			} else {
				arduino.sendBlue();
			}
		} catch (Exception e) {
			System.err.println("Arduino Failed.  This shouldn't happen on Comp Bot");
			e.printStackTrace();
		}
		
		//OI INITIALIZATION MUST MUST MUST MUST BE LAST
		oi = new OI();
	}
	
	private void pollPreferences() {
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java CustomDashboard. If you prefer the
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
		if(autoSelected instanceof Initializer) {
			((Initializer)autoSelected).init();
		}
		autoSelected.start();
		// autoSelected = CustomDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		pollPreferences();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		/*switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}*/
		log();
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		chooser.getSelected().cancel(); //STOPS AUTO ROUTINE
		driveTrain.resetEncoders();
		driveTrain.resetGyro();
		pollPreferences();

	}
	
	@Override
	public void teleopPeriodic() {
    	log();
    	Scheduler.getInstance().run();
    	
    	/*
    	 * CustomDashboard.putNumber("DriveTrain Left Current 1", pdp.getCurrent(RobotMap.PDP.leftDriveMotor[0]));
    	 * CustomDashboard.putNumber("DriveTrain Left Current 2", pdp.getCurrent(RobotMap.PDP.leftDriveMotor[1]));
    	 * CustomDashboard.putNumber("DriveTrain Left Current 3", pdp.getCurrent(RobotMap.PDP.leftDriveMotor[2]));
         *    	
    	 * CustomDashboard.putNumber("DriveTrain Right Voltage 1", pdp.getCurrent(RobotMap.PDP.rightDriveMotor[0]));
    	 * CustomDashboard.putNumber("DriveTrain Right Voltage 2", pdp.getCurrent(RobotMap.PDP.rightDriveMotor[1]));
    	 * CustomDashboard.putNumber("DriveTrain Right Voltage 3", pdp.getCurrent(RobotMap.PDP.rightDriveMotor[2]));
    	 */
	}
	
	public void disabledPeriodic() {
    	log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
    	CustomDashboard.putNumber("DriveTrain Yaw", Robot.driveTrain.ahrs.getYaw());
    	log();
	}
	
	public static double deadband(double x) {
		if(Math.abs(x) < 0.05) {
			return 0;
		} 
		return x;
	}
	
	private void log() {
		for(Loggable l : logList) {
			l.log();
		}
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

