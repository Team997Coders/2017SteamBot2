package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.DriveToAngle;
import org.usfirst.frc.team997.robot.commands.SetDriveToAngle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	public Joystick joy;
	private JoystickButton driveToAngleButton, 
		driveTo0Button, driveTo90Button, 
		driveTo180Button, driveToN90Button;
	
	public OI() {
		joy = new Joystick(0);
		driveToAngleButton = new JoystickButton(joy, 1);
		driveToAngleButton.whenPressed(Robot.driveToAngleCommand);
		driveTo0Button = new JoystickButton(joy, 2);
		driveTo0Button.whenPressed(new SetDriveToAngle(0));
		driveTo90Button = new JoystickButton(joy, 3);
		driveTo90Button.whenPressed(new SetDriveToAngle(90));
		driveTo180Button = new JoystickButton(joy, 4);
		driveTo180Button.whenPressed(new SetDriveToAngle(179.9));
		driveToN90Button = new JoystickButton(joy, 5);
		driveToN90Button.whenPressed(new SetDriveToAngle(-90));
	}
}
