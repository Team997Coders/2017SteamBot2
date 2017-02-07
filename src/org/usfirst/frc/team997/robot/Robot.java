package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.DriveToAngle;
import org.usfirst.frc.team997.robot.subsystems.Climber;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	public static Shooter shooter;
	public static DriveToAngle driveToAngleCommand;
	public static Climber climber;
	
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
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
			driveToAngleCommand = new DriveToAngle();
		/*} catch (Exception e){
			e.printStackTrace();
		}*/

		climber = new Climber();
		
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		//OI INITIALIZATION MUST MUST MUST MUST BE LAST
		
		oi = new OI();
	
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
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {    	
		SmartDashboard.putNumber("DriveTrain Yaw", Robot.driveTrain.ahrs.getYaw());
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	private int tickcount;
	@Override
	public void teleopPeriodic() {
    	SmartDashboard.putNumber("DriveTrain Yaw", Robot.driveTrain.ahrs.getYaw());
    	++tickcount;
    	SmartDashboard.putNumber("tickCount", tickcount);
    	Scheduler.getInstance().run();
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
}

