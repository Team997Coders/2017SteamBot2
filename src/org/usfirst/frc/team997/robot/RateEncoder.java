package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class RateEncoder implements PIDSource{
	public Encoder encoderStore;
	public RateEncoder(Encoder encoder){
		encoderStore = encoder;
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kRate;
	}

	@Override
	public double pidGet() {
		return encoderStore.getRate();
	}

}
