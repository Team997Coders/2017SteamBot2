package org.usfirst.frc.team997.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	
	Talon elevatorMotor;

    public Elevator(int elevatorPort){
    	elevatorMotor = new Talon (elevatorPort);
    }
    public void spinInward(){
    	elevatorMotor.set(-1);
    }
    public void spinOutward(){
    	elevatorMotor.set(1);
    }
    public void stop(){
    	elevatorMotor.set(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

