package org.usfirst.frc.team997.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.I2C;

/**
 *
 */
public class Arduino extends Subsystem {
	I2C i2c;
	public Arduino(){
		i2c = module.getI2C(168);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        
    }
    public void sendRed(){
		byte[] byteArray = new byte[1];
		byteArray[0] = 'R'; 
		i2c.transaction(byteArray, 1, null, 0);
	}
    public void sendWhite(){
		byte[] byteArray = new byte[1];
		byteArray[0] = 'W'; 
		i2c.transaction(byteArray, 1, null, 0);
	}
    public void sendBlue(){
		byte[] byteArray = new byte[1];
		byteArray[0] = 'B'; 
		i2c.transaction(byteArray, 1, null, 0);
	}
    public void sendBlackout(){
		byte[] byteArray = new byte[1];
		byteArray[0] = 'K'; 
		i2c.transaction(byteArray, 1, null, 0);
	}
    public void sendGreen(){
		byte[] byteArray = new byte[1];
		byteArray[0] = 'G'; 
		i2c.transaction(byteArray, 1, null, 0);
	}
    public void sendYellow(){
		byte[] byteArray = new byte[1];
		byteArray[0] = 'Y'; 
		i2c.transaction(byteArray, 1, null, 0);
	}
    
    public void chaseSend(byte x){
    	byte[] chase = new byte[2];
    	chase[0] = 'C';
    	chase[1] = x;
    	i2c.transaction(chase, 2, null, 0);
    }
    public void fadeSend(byte x){
    	byte[] fade = new byte[2];
    	fade[0] = 'A';
    	fade[1] = x;
    	i2c.transaction(fade, 2, null, 0);
    }
    public void flashSend(byte x){
    	byte[] flash = new byte[2];
    	flash[0] = 'F';
    	flash[1] = x;
    	i2c.transaction(flash, 2, null, 0);
    }
    public void sparkleSend(byte x, byte y){
    	byte[] sparkle = new byte[3];
    	sparkle[0] = 'Z';
    	sparkle[1] = x;
    	sparkle[2] = y;
    	i2c.transaction(sparkle, 3, null, 0);
    }
    
}

