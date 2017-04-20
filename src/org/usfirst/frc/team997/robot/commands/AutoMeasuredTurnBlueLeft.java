package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.CustomDashboard;
import org.usfirst.frc.team997.robot.Initializer;
import org.usfirst.frc.team997.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMeasuredTurnBlueLeft extends CommandGroup implements Initializer{
	public double formula = (140 - (162 - 70.5/Math.sqrt(3) + RobotMap.Values.robotWidth/2 - 73*Math.sqrt(2))/2 - RobotMap.Values.robotLength/2);
	private DriveToDistance firstDrive;
	
	public void init() {
		firstDrive.setPoint = CustomDashboard.getNumber("AutoStraight Distance Value", RobotMap.Values.measured);
	}

	public AutoMeasuredTurnBlueLeft() {
		firstDrive = new DriveToDistance(formula + (RobotMap.Values.measured -114));	
		addSequential(firstDrive);
		addSequential(new DriveToAngle(60)); // turn left towards airship
    	addSequential(new DriveToDistance(-RobotMap.Values.autoKeyCross)); // drive backwards some more
	}
	
}