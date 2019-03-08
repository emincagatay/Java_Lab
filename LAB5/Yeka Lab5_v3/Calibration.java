package de.tuhh.diss.plotbot;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Calibration {
	/*
	 * This class performs the calibration for one specific Plotbot robot,
	 * returning that specific robot's maximum angles
	 * 
	 * */
	private static final int HIGH_LIGHT = 890;
	private static final int LOW_LIGHT = 450;
	private static final int COLOR_GRAY = 20;

	/**
	 * calibrates the all three motors and obtain physical limitations.
	 * Then robot moves to the starting point of the plane.
	 * 
	 * 
	 * @return   an integer array consisting of max angle the swivel arm can rotate to and maximum height the pen can go
	 */
	public int[] calibration(){
		
		int[] parameters = new int[2];
		// PARAMETERS = MAX_ANGLE, MAX_UP
		LCD.clear();
		LCD.drawString("Calibrating", 0, 0);
		parameters[1] = penCalib(); 
		parameters[0] = armCalib();
		wheelCalib();	
		LCD.clear();
		return parameters;
	}
	private void wheelCalib(){
		LightSensor light = new LightSensor(SensorPort.S3, false);
		light.setFloodlight(true);
		light.setLow(LOW_LIGHT);
		light.setHigh(HIGH_LIGHT);	
		
		while(light.getLightValue() > COLOR_GRAY){
			Motor.C.backward();
			LCD.clear(0, 4, 6);
			LCD.drawInt(light.getNormalizedLightValue(), 4, 4);
		}
		
		Motor.C.stop(); 
		Motor.C.resetTachoCount();	
		light.setFloodlight(false);
	}
	private int armCalib(){
		int maxAngle = 0;
		TouchSensor touch = new TouchSensor(SensorPort.S1);
		Motor.A.resetTachoCount();
		while(!touch.isPressed()){
			Motor.A.backward();
		 }
		Motor.A.stop();
		maxAngle = Motor.A.getTachoCount();
		
		Motor.A.rotateTo(0);		
		return maxAngle;
	}
	private int penCalib(){
		int maxUp = 0;
		TouchSensor pensensor = new TouchSensor(SensorPort.S2);
		
		Motor.B.resetTachoCount();
		while(!pensensor.isPressed()){
			Motor.B.forward();
		}
		
		maxUp = Motor.B.getTachoCount();
		Motor.B.stop();	
		
		return maxUp;
	}
	
}
