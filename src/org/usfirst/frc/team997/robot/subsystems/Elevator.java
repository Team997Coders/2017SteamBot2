package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ElevatorSpinWhileHeld;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private Talon elevatorMotor;

    public Elevator() {
    	elevatorMotor = new Talon(RobotMap.Ports.elevatorTalon);
    }
    public void spinInward() {
    	elevatorMotor.set(0.8);
    }
    public void spinOutward() {
    	elevatorMotor.set(-0.8);
    }
    public void stop() {
    	elevatorMotor.set(0);
    }

    public void initDefaultCommand() {}
}

