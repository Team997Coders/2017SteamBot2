package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RateEncoder;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	public AHRS ahrs;
	private SpeedController left, right;
	public Encoder leftEncoder;
	public Encoder rightEncoder;
	private PIDController leftPID, rightPID;

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
		right.setInverted(true);
		
		final double gearRatio = 4/3;
		final double ticksPerRev = 2048;
		final double radius = 1.5;
		final double magic = 1/.737;
		final double calculated = (radius * 2 * Math.PI) * gearRatio * magic / ticksPerRev;
		
		ahrs.reset();
		
		leftEncoder = new Encoder(RobotMap.Ports.leftEncoderOne, RobotMap.Ports.leftEncoderTwo, true, EncodingType.k4X);
		leftEncoder.setDistancePerPulse(calculated);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		rightEncoder = new Encoder(RobotMap.Ports.rightEncoderOne, RobotMap.Ports.rightEncoderTwo, false, EncodingType.k4X);
		rightEncoder.setDistancePerPulse(calculated);
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);

		leftPID = new PIDController(
				RobotMap.Values.driveTrainP, RobotMap.Values.driveTrainI,
				RobotMap.Values.driveTrainD, RobotMap.Values.driveTrainF, 
				new RateEncoder(leftEncoder), left);
		leftPID.setInputRange(-300, 300);
		leftPID.setOutputRange(-1, 1);
		rightPID = new PIDController(
				RobotMap.Values.driveTrainP, RobotMap.Values.driveTrainI,
				RobotMap.Values.driveTrainD, RobotMap.Values.driveTrainF, 
				new RateEncoder(rightEncoder), right);
		rightPID.setInputRange(-300, 300);
		rightPID.setOutputRange(-1, 1);
		
		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Left Motor", (VictorSP) left);
		LiveWindow.addActuator("Drive Train", "Right Motor", (VictorSP) right);
		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
		LiveWindow.addSensor("Drive Train", "Gyro", ahrs);
		LiveWindow.addActuator("Drive Train", "PID", leftPID);
		
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
    
    public void driveVoltage(double leftV, double rightV) {
    	updateSD();
    	left.set(leftV*driveSpeed);
    	right.set(rightV*driveSpeed*driveDrift);
    }
    
    private void updateSD() {
    	SmartDashboard.putNumber("DriveTrain Encoder Left", leftEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain Encoder Right", rightEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain Encoder Left Ticks", leftEncoder.get());
    	SmartDashboard.putNumber("DriveTrain Encoder Right Ticks", rightEncoder.get());
    	SmartDashboard.putNumber("DriveTrain Encoder L Rate", leftEncoder.getRate());
    	SmartDashboard.putNumber("DriveTrain Encoder R Rate", rightEncoder.getRate());
    	
    	SmartDashboard.putBoolean("PID Status", leftPID.isEnabled());
    	SmartDashboard.putNumber("PID Setpoint", leftPID.getSetpoint());
    	SmartDashboard.putNumber("PID Error", leftPID.getError());
    	SmartDashboard.putNumber("PID Output", leftPID.get());
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

	public void drivePID(double leftV, double rightV) {
		updateSD();
		leftPID.setSetpoint(leftV);
		rightPID.setSetpoint(rightV);
	}
	public void startPID() {
		leftPID.enable();
		rightPID.enable();
	}
	public void stopPID() {
		leftPID.disable();
		rightPID.disable();
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

