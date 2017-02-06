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
	public boolean isTank = true;
	public Joystick joy1;
	public Joystick joy2;

	public Button shoot;
	private JoystickButton driveToAngleButton, 
		driveTo0Button, driveTo90Button, 
		driveTo180Button, driveToN90Button;
	
	public OI() {
		SmartDashboard.putData("Drive type", new DriveToggle());
		
		joy1 = new Joystick(0);
		driveToAngleButton = new JoystickButton(joy1, 1);
		driveToAngleButton.whenPressed(Robot.driveToAngleCommand);
		driveTo0Button = new JoystickButton(joy1, 2);
		driveTo0Button.whenPressed(new SetDriveToAngle(0));
		driveTo90Button = new JoystickButton(joy1, 3);
		driveTo90Button.whenPressed(new SetDriveToAngle(90));
		driveTo180Button = new JoystickButton(joy1, 4);
		driveTo180Button.whenPressed(new SetDriveToAngle(179.9));
		driveToN90Button = new JoystickButton(joy1, 5);
		driveToN90Button.whenPressed(new SetDriveToAngle(-90));
		
		joy2 = new Joystick(1);
		shoot = new JoystickButton(joy2, 2);
		shoot.whenPressed(new Shoot());
    	SmartDashboard.putNumber("Shooter Speed", RobotMap.Values.shooterSpeed);
	}
	
	public double getLeftY() {
		return joy1.getRawAxis(1);
	}
	
	public double getRightY() {
		return joy2.getRawAxis(1);
	}
	
	public double getRightX() {
		return joy2.getRawAxis(0);
	}
}
