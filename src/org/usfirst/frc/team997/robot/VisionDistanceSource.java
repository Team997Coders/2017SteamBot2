package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class VisionDistanceSource implements PIDSource {
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	private VisionStorage previous;

	@Override
	public double pidGet() {
		VisionStorage storage = Robot.udpReceive.work();
		if (storage == null) {
			storage = previous;
		} else {
			previous = storage;
		}
		return Robot.udpReceive.work().distan;
	}
}
