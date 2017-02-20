package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;


/**
 *
 */
public class DriveTrain extends Subsystem {
	public AHRS ahrs;
	private SpeedController left, right;
	public Encoder leftEncoder;
	public Encoder rightEncoder;
	//public Gyro gyro;

	public double driveSpeed = 1;
	public double driveDrift = 1;
	double pasterr = 0.01D;
	double integral = 0.0D;
	double speed = 0.0D;
	
	double rotpasterr = 0;
	double rotintegral = 0;


    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveTrain() {
	
		ahrs = new AHRS(RobotMap.AHRSPort);
		left = new VictorSP(RobotMap.Ports.leftDriveMotor);
		right = new VictorSP(RobotMap.Ports.rightDriveMotor);
		
		final double gearRatio = 0.225;
		final double ticksPerRev = 2048;
		final double pi = 3.14;
		final double radius = 2;
		final double calculated = ((gearRatio*2*pi)/ticksPerRev)*radius;
		
		//gyro = new ADXRS450_Gyro(SPI.Port.valueOf("kOnboardCS0"));
		ahrs.reset();
		
		//leftEncoder = new Encoder(RobotMap.Ports.leftEncoderOne, RobotMap.Ports.leftEncoderTwo, false, EncodingType.k4X);
		leftEncoder = new Encoder(RobotMap.Ports.leftEncoderOne, RobotMap.Ports.leftEncoderTwo);
		leftEncoder.setDistancePerPulse(calculated);
		leftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
		leftEncoder.setReverseDirection(true);
		rightEncoder = new Encoder(RobotMap.Ports.rightEncoderOne, RobotMap.Ports.rightEncoderTwo, false, EncodingType.k4X);
		rightEncoder.setDistancePerPulse(calculated);
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
    
    public void driveVoltage(double leftV, double rightV) {
    	SmartDashboard.putNumber("DriveTrain Encoder Left", leftEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain Encoder Right", leftEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain Encoder Left Ticks", leftEncoder.get());
    	SmartDashboard.putNumber("DriveTrain Encoder Right Ticks", rightEncoder.get());
    	left.set(-leftV*driveSpeed);
    	right.set(rightV*driveSpeed*driveDrift);
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void resetGyro() {
    	ahrs.reset();
    }
    
    public double getDistance() {
    	return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }
    
    /*public void driveToDistance(double setPoint) {
    	while(leftEncoder.getDistance() <= setPoint) {
    		double kpe = 0.5/setPoint;
    		double error = setPoint-leftEncoder.getDistance();
    		double derivative = error - pasterr;
    		pasterr = error;
    		speed = (kpe*error) + (0*integral) + (0*derivative);
    		
    		if(error > 0.01) {
    			integral = 0.0D;
    		} else {integral += error;}
    		
    		double kpg = 0.06;
    		double angle = gyro.getAngle();
    		
    		speed = Math.min(Math.max(speed, 0.5D), -0.5D);
    		driveVoltage(speed, -kpg*angle);
    		
    		Timer.delay(0.004);
    	}
    	
    }
    
    public void rotateToAngle(double angle) {
    	while(Math.abs(gyro.getAngle()) <= Math.abs(angle)) {
    		double maxSpeed = 0.25;
    		double currentAngle = gyro.getAngle();
    		double kpr = maxSpeed/angle;
    		double roterr = angle-currentAngle;
    		double kdr = kpr-rotpasterr;
    		rotpasterr = roterr;
    		
    		double curve = (kpr*roterr) + (0*rotintegral) + (5*kdr);
    		
    		if(roterr > 0.01) {
    			rotintegral = 0.0D;
    		} else {rotintegral += roterr;}
    		
    		curve = Math.min(Math.max(curve,  maxSpeed), -maxSpeed);
    		
    		if(angle > 0) {
    			driveVoltage(-curve, -curve);
    		} else {
    			driveVoltage(curve, curve);
    		}
    	}
    }*/


}

