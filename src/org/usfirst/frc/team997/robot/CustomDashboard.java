package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CustomDashboard {
	public static void putData(NamedSendable value) {
		SmartDashboard.putData(value);
	}
	
	public static void putData(String name, Sendable value) {
		SmartDashboard.putData(name, value);
	}
	
	public static Sendable getData(String name) {
		return SmartDashboard.getData(name);
	}

	public static void putString(String name, String value) {
		SmartDashboard.putString(name, value);
	}

	public static String getString(String name, String defaultValue) {
		return SmartDashboard.getString(name, defaultValue);
	}

	public static void putNumber(String name, double value) {
		SmartDashboard.putNumber(name, value);
	}

	public static double getNumber(String name, double defaultValue) {
		return SmartDashboard.getNumber(name, defaultValue);
	}

	public static void putBoolean(String name, boolean value) {
		SmartDashboard.putBoolean(name, value);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return SmartDashboard.getBoolean(key, defaultValue);
	}
}
