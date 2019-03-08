package de.tuhh.diss.io;
//import de.tuhh.diss.io.SimpleIO;

public class Test {
	
	private static final double SIZE_OF_TANK = 300;         // Liters
	private static final double PETROL_CONSUMPTION = 10;    // Liters per hour
	private static final double SPEED = 21.6;					 // in knots (nautic miles per hour)	

	
	public static void main(String[] args){
		
		Ship myShip = new Ship(PETROL_CONSUMPTION, SIZE_OF_TANK, SPEED);
		
		
		final double SHORTDISTANCETODRIVE = 20;
		
		System.out.println("fillLevel is " + myShip.getFillLevel());
		
		

		myShip.fill(SIZE_OF_TANK);
		System.out.println("fillLevel is " + myShip.getFillLevel());
		
		double a = myShip.sailForward(SHORTDISTANCETODRIVE);
		
		
		System.out.println("sonuç " + a + "ama gereken sonuç "+ 20);
		
	}
	

}
