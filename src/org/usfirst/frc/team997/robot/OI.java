package org.usfirst.frc.team997.robot;


import org.usfirst.frc.team997.robot.commands.Climb;
import org.usfirst.frc.team997.robot.commands.DriveDirectionToggle;
import org.usfirst.frc.team997.robot.commands.DriveToAngle;
import org.usfirst.frc.team997.robot.commands.DriveToDistance;
import org.usfirst.frc.team997.robot.commands.DriveToggle;
import org.usfirst.frc.team997.robot.commands.ElevatorSpinWhileHeld;
import org.usfirst.frc.team997.robot.commands.ExtendGatherer;
import org.usfirst.frc.team997.robot.commands.ReverseGatherHold;
import org.usfirst.frc.team997.robot.commands.ShakeGatherer;
import org.usfirst.frc.team997.robot.commands.SpitoutGatherer;
import org.usfirst.frc.team997.robot.commands.StopClimb;
import org.usfirst.frc.team997.robot.commands.ToggleAccelerationControl;
import org.usfirst.frc.team997.robot.commands.ToggleDeccelerationControl;
import org.usfirst.frc.team997.robot.commands.UnClimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team997.robot.CustomDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	public boolean isTank = false;
	public boolean forward = true; //true when gatherer is front
	public boolean useDeccelerationControl = true;
	public Joystick joy;

	private JoystickButton extendGatherButton;
	private JoystickButton driveSetDistanceButton;
	public JoystickButton climbButton;
	public JoystickButton elevatorButton;
	public JoystickButton reverseGatherButton;
	public JoystickButton driveDirectionToggle;
	public JoystickButton deccelControlToggle;
	
	public OI() {
		CustomDashboard.putData("Drive type", new DriveToggle());
		
		joy = new Joystick(0);

		//JoystickButton drive60In = new JoystickButton(joy, 4);
		//drive60In.whenPressed(new DriveToDistance(-60));

//		JoystickButton drive90Clockwise = new JoystickButton(joy, 4);
//		drive90Clockwise.whenPressed(new DriveToAngle(-90));
		//JoystickButton drive90CounterClockwise = new JoystickButton(joy, 3);
		//drive90CounterClockwise.whenPressed(new DriveToAngle(90));
		
//		reverseGatherButton = new JoystickButton(joy, 3);
//		reverseGatherButton.whenPressed(new SpitoutGatherer());
		
		climbButton = new JoystickButton(joy, 1);
		climbButton.whenPressed(new Climb());

		elevatorButton = new JoystickButton(joy, 5);
		elevatorButton.whenPressed(new ElevatorSpinWhileHeld());

//		extendGatherButton = new JoystickButton(joy, 2);
//		extendGatherButton.whenPressed(new ExtendGatherer());
		
		reverseGatherButton = new JoystickButton(joy, 2);
		reverseGatherButton.whenPressed(new ReverseGatherHold());
		
		driveDirectionToggle = new JoystickButton(joy, 3);
		driveDirectionToggle.whenPressed(new DriveDirectionToggle());
		
		deccelControlToggle = new JoystickButton(joy, 4);
		deccelControlToggle.whenPressed(new ToggleDeccelerationControl());

    	CustomDashboard.putNumber("Shooter Setpoint", RobotMap.Values.shooterSpeed);
    	CustomDashboard.putData("Toggle Acceleration Control", new ToggleAccelerationControl());
    	CustomDashboard.putData("Climb", new Climb());
    	CustomDashboard.putData("UnClimb", new UnClimb());
    	CustomDashboard.putData("Stop Climb", new StopClimb());
    	CustomDashboard.putData("ShakeGatherer", new ShakeGatherer());
    	CustomDashboard.putBoolean("Drivetrain Forward", forward);
    	CustomDashboard.putData("Toggle Decceleration Control", new ToggleDeccelerationControl());
	}
	
	public double getLeftTrigger() {
		return joy.getRawAxis(2);
	}
	
	public double getRightTrigger() {
		return joy.getRawAxis(3);
	}
	
	public double getLeftY() {
		return -joy.getRawAxis(1);
	}
	
	public double getRightY() {
		return joy.getRawAxis(5);
	}
	
	public double getRightX() {
		return joy.getRawAxis(4);
	}
}
