package org.usfirst.frc.team997.robot;


import org.usfirst.frc.team997.robot.commands.Climb;
import org.usfirst.frc.team997.robot.commands.DriveToggle;
import org.usfirst.frc.team997.robot.commands.ExtendGatherer;
import org.usfirst.frc.team997.robot.commands.Gather;
import org.usfirst.frc.team997.robot.commands.SetDriveToAngle;
import org.usfirst.frc.team997.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	public boolean isTank = false;
	public Joystick joy;

	private JoystickButton driveToAngleButton, 
		driveTo0Button, driveTo90Button, 
		driveTo180Button, driveToN90Button, 
		climbButton, extendGatherButton;
	public JoystickButton gatherButton;
	
	public OI() {
		SmartDashboard.putData("Drive type", new DriveToggle());
		
		joy = new Joystick(0);
		//driveToAngleButton = new JoystickButton(joy1, 1);
		//driveToAngleButton.whenPressed(Robot.driveToAngleCommand);
		//driveTo0Button = new JoystickButton(joy1, 2);
		//driveTo0Button.whenPressed(new SetDriveToAngle(0));
		//driveTo90Button = new JoystickButton(joy1, 3);
		//driveTo90Button.whenPressed(new SetDriveToAngle(90));
		//driveTo180Button = new JoystickButton(joy1, 4);
		//driveTo180Button.whenPressed(new SetDriveToAngle(179.9));
		//driveToN90Button = new JoystickButton(joy1, 5);
		//driveToN90Button.whenPressed(new SetDriveToAngle(-90));
		
		climbButton = new JoystickButton(joy, 1);
		climbButton.whenPressed(new Climb());

		gatherButton = new JoystickButton(joy, 6);
		gatherButton.whenPressed(new Gather());
		
		extendGatherButton = new JoystickButton(joy, 7);
		extendGatherButton.whenPressed(new ExtendGatherer());
		
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
