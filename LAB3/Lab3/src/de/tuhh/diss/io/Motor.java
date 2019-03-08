package de.tuhh.diss.io;



public class Motor {
	private double speed;
	private double fuelConsumption;
	private double distanceTotal;
	private double distanceSinceFilled;
	private Tank Tank;
	
	
	public Motor(double fuelConsumption,double speed,Tank Tank){
		this.speed = speed;
		this.fuelConsumption= fuelConsumption;	
		distanceTotal = 0;
		distanceSinceFilled = 0;
		this.Tank = Tank;
		
	}
	
	public Motor(double totalVolume){
		Tank = new Tank(totalVolume);		
	}
	
	
	
	public void resetMeter(){
		
		distanceSinceFilled = 0;
	}
	
	public double getDistanceTotal(){
		
		return distanceTotal;
	}
	
	public double getDistanceSinceFilled(){
		
		return distanceSinceFilled;
	}
	
	public double sailForward(double distance){
		
		double consumedFuel;
		double movedDistance;
		System.out.println("requiredDistance is " + distance );
		double neededFuel = (distance/speed)*fuelConsumption;
		System.out.println("neededFuel is " + neededFuel );
		
		System.out.println("fillLevel is " + Tank.getFillLevel());
		consumedFuel = Tank.consume(neededFuel);
		System.out.println("consumedFuel is " + consumedFuel );
		System.out.println("fillLevel is " + Tank.getFillLevel());
		movedDistance = (consumedFuel*speed)/fuelConsumption;
		distanceTotal += movedDistance;
		distanceSinceFilled += movedDistance;
		

		return movedDistance;
		
	}
	
	
	

}
