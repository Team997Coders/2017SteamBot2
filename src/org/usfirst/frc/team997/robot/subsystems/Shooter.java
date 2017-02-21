package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ShootTrigger;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Shooter extends Subsystem {

	
	public CANTalon shooterMotorMaster;
	public CANTalon shooterMotorSlave;
	
    // Initialize your subsystem here
    public Shooter(int shootMotorMaster, int shootMotorSlave) {
        shooterMotorMaster = new CANTalon(shootMotorMaster);
        shooterMotorSlave = new CANTalon(shootMotorSlave);

        shooterMotorMaster.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        shooterMotorMaster.reverseSensor(false);
        shooterMotorMaster.configEncoderCodesPerRev(3);

        shooterMotorMaster.configNominalOutputVoltage(+0.0f, -0.0f);
        shooterMotorMaster.configPeakOutputVoltage(+12.0f, -12.0f);
        shooterMotorMaster.setProfile(0);

        shooterMotorMaster.enableBrakeMode(false);
        shooterMotorSlave.enableBrakeMode(false);
        shooterMotorSlave.reverseOutput(true);
        shooterMotorMaster.setPosition(0);
        shooterMotorMaster.setVoltageRampRate(24);

        shooterMotorMaster.changeControlMode(TalonControlMode.Speed);
        shooterMotorSlave.changeControlMode(TalonControlMode.Follower);

        shooterMotorMaster.setF(RobotMap.Values.shooterF);
        shooterMotorMaster.setP(RobotMap.Values.shooterP);
        shooterMotorMaster.setI(RobotMap.Values.shooterI);
        shooterMotorMaster.setD(RobotMap.Values.shooterD);
        
        LiveWindow.addActuator("Shooter", "Shooter Motor", (CANTalon) shooterMotorMaster);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShootTrigger());
    }
}