package org.usfirst.frc.team997.robot;


import org.usfirst.frc.team997.robot.commands.Climb;
import org.usfirst.frc.team997.robot.commands.DriveToAngle;
import org.usfirst.frc.team997.robot.commands.DriveToDistance;
import org.usfirst.frc.team997.robot.commands.DriveToggle;
import org.usfirst.frc.team997.robot.commands.ElevatorSpinWhileHeld;
import org.usfirst.frc.team997.robot.commands.ExtendGatherer;
import org.usfirst.frc.team997.robot.commands.GatherTrigger;
import org.usfirst.frc.team997.robot.commands.SpitoutGatherer;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
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
		extendGatherButton;
	public JoystickButton climbButton;
	public JoystickButton elevatorButton;
	public JoystickButton reverseGatherButton;
	
	public OI() {
		SmartDashboard.putData("Drive type", new DriveToggle());
		
		joy = new Joystick(0);
		
//		driveTo90Button = new JoystickButton(joy, 3);
//		driveTo90Button.whenPressed(new DriveToDistance(60));
		reverseGatherButton = new JoystickButton(joy, 3);
		reverseGatherButton.whenPressed(new SpitoutGatherer());
		
		climbButton = new JoystickButton(joy, 1);
		climbButton.whenPressed(new Climb());

		elevatorButton = new JoystickButton(joy, 5);
		elevatorButton.whenPressed(new ElevatorSpinWhileHeld());

		extendGatherButton = new JoystickButton(joy, 2);
		extendGatherButton.whenPressed(new ExtendGatherer());
		
		

    	SmartDashboard.putNumber("Shooter Setpoint", RobotMap.Values.shooterSpeed);
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
