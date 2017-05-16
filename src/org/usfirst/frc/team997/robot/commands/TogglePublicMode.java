package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TogglePublicMode extends Command {

    public TogglePublicMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    protected void initialize() {
    	Robot.publicMode = !Robot.publicMode;
    }
    	
    @Override
    protected boolean isFinished() {
    	return true;
    }

}
