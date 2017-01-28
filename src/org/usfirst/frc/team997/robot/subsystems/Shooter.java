package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.Shoot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        shooterMotorMaster.setPosition(0);
        shooterMotorMaster.setVoltageRampRate(24);

        shooterMotorMaster.changeControlMode(TalonControlMode.Speed);
        shooterMotorSlave.changeControlMode(TalonControlMode.Follower);

        shooterMotorMaster.setF(RobotMap.Values.shooterF);
        shooterMotorMaster.setP(RobotMap.Values.shooterP);
        shooterMotorMaster.setI(RobotMap.Values.shooterI);
        shooterMotorMaster.setD(RobotMap.Values.shooterD);

        SmartDashboard.putNumber("Velocity:", shooterMotorMaster.getSpeed());
        SmartDashboard.putNumber("Distance:", shooterMotorMaster.getEncPosition());
        SmartDashboard.putNumber("Sensor Position", shooterMotorMaster.getPosition());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Shoot());
    }
}