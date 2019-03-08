package de.tuhh.diss.io;

public class Ship {
    
	private Motor Motor;
	private Tank Tank;
	
	public Ship(double fuelConsumption, double tankVolume, double speed){
		
		Tank = new Tank(tankVolume);
		Motor = new Motor(fuelConsumption, speed, Tank);
		
	}
	
	public double sailForward(double distance){
		
		return Motor.sailForward(distance);
	}
	
	public double fill(double amount){
		
		Motor.resetMeter();
		return Tank.fill(amount);
		
	}
	
	public double getFillLevel(){
		
		return Tank.getFillLevel();
		
	}
	
	public double getDistanceTotal(){
		
		return Motor.getDistanceTotal();
		
	}
	
	public double getDistanceSinceFilled(){
		
		return Motor.getDistanceSinceFilled();
	}
	
	

}
