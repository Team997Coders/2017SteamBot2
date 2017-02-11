package org.usfirst.frc.team997.robot;


import org.usfirst.frc.team997.robot.commands.DriveToggle;
import org.usfirst.frc.team997.robot.commands.SetDriveToAngle;
import org.usfirst.frc.team997.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	public boolean isTank = false;
	public Joystick joy;

	public Button shoot;
	private JoystickButton driveToAngleButton, 
		driveTo0Button, driveTo90Button, 
		driveTo180Button, driveToN90Button;
	
	public OI() {
		SmartDashboard.putData("Drive type", new DriveToggle());
		
		joy = new Joystick(0);
		driveToAngleButton = new JoystickButton(joy, 1);
		driveToAngleButton.whenPressed(Robot.driveToAngleCommand);
		driveTo0Button = new JoystickButton(joy, 2);
		driveTo0Button.whenPressed(new SetDriveToAngle(0));
		//driveTo90Button = new JoystickButton(joy1, 3);
		//driveTo90Button.whenPressed(new SetDriveToAngle(90));
		driveTo180Button = new JoystickButton(joy, 4);
		driveTo180Button.whenPressed(new SetDriveToAngle(179.9));
		driveToN90Button = new JoystickButton(joy, 5);
		driveToN90Button.whenPressed(new SetDriveToAngle(-90));		
		
		
    	SmartDashboard.putNumber("Shooter Speed", RobotMap.Values.shooterSpeed);
	}
	
	public double getLeftTrigger() {
		return joy.getRawAxis(2);
	}
	
	public double getRightTrigger() {
		return joy.getRawAxis(3);
	}
	
	public double getLeftY() {
		return joy.getRawAxis(1);
	}
	
	public double getRightY() {
		return joy.getRawAxis(5);
	}
	
	public double getRightX() {
		return -joy.getRawAxis(4);
	}
}
