package de.tuhh.diss.io;



public class Tank {
	
	private double totalVolume;
	private double fillLevel;
	
	public Tank(double totalVolume){
		this.totalVolume = totalVolume;		
		fillLevel = 0;
	}
	
	/**
	 * Consumes the amount of fuel in terms of entered amount of fuel and f.The removed fuel is returned.
	 * @param removedFuel  Quantity of the fuel which is consumed.
	 * @param fillLevel    Indicates the amount of fuel remaining inside of the tank
	 * @param amount       Amount of fuel requested to be consumed from the tank.
	 * */
	
	public double consume(double amount){
		double removedFuel;
		
		if(amount>fillLevel){
			removedFuel = fillLevel;
			fillLevel = 0;
		}
		
		else{
			removedFuel = amount;
			fillLevel -= amount;
		}
		
		return removedFuel;
	}
	/** 
	 * Method fill() fills the tank if possible(if it is not full already.)
	 * The method gets a double type variable which implicates the amount requested to fill the tank with.
	 * The method include a nested if block in order to execute the appropriate code 
	 * with respect the sorting between given amount,fillLevel and totalVolume which is done 
	 * by nested an if block.E.g. if entered amount is smaller than both fillLevel and 
	 * the difference between totalVolume and fillLevel,then it fills tank with entered amount
	 * since empty volume of tank is greater than entered amount.
	 * */
	public double fill(double amount){
		double filledAmount;
		if(amount <= (totalVolume-fillLevel)){
			if(amount <= fillLevel){
				filledAmount = amount ;				
			}			
			else{
				filledAmount = amount - fillLevel;				
			}
		}
		filledAmount = totalVolume - fillLevel;
		fillLevel = totalVolume;
		return filledAmount;
		
	}

	public double getFillLevel(){
		
		return fillLevel;
		
	}
	


}
