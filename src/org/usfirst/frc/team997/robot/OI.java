package org.usfirst.frc.team997.robot;

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
	public Joystick joy;
	public Button shoot;
	
	public OI() {
		joy = new Joystick(1);
		shoot = new JoystickButton(joy, 2);
		shoot.whenPressed(new Shoot());
    	SmartDashboard.putNumber("Shooter Speed", RobotMap.Values.shooterSpeed);
	}
}
