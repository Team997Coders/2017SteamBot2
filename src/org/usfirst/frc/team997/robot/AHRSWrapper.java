package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class AHRSWrapper implements PIDSource{
	public double offset;
	@Override
	public void setPIDSourceType(PIDSourceType type) {
		// TODO Auto-generated method stub
		Robot.driveTrain.ahrs.setPIDSourceType(type);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return Robot.driveTrain.ahrs.getPIDSourceType();
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return Robot.driveTrain.ahrs.pidGet() - offset;
	}

}
