package de.tuhh.diss.io;
import de.tuhh.diss.io.SimpleIO;


public class MaxOxygen {
	
	public static double obtainVO2Max(double a,double b){
		
		double VO2Max = 15 * (a/b);;
		return VO2Max;
		
	}
	
	public static double obtainHRMax(int a){
		
		double HRMax = 206.9-(0.67*a);
		return HRMax;
	}
	
	public static int getHRReest(int a){
		int HRRest = 71;
		if(a>=36){
			if(a>=56){
				HRRest = 76;				
			}
			else{
				HRRest = 73;
				
			}			
		}
		return HRRest;		
	}
	
	public static void main(String[] args){
		
		SimpleIO.println("Enter your age:");
		int age = SimpleIO.readInteger();
		while(age<18){
			SimpleIO.println("This is an adult business.\nPlease enter an age that is equal to bigger than 18:");
			age = SimpleIO.readInteger();
		}
			
		double HRRest = getHRReest(age);
		double HRMax = obtainHRMax(age);
		double VO2Max = obtainVO2Max(HRMax,HRRest);
		SimpleIO.println("The maximal oxygen consumption of someone at your age is "+ VO2Max+" mL/(kg.min).");
			
		}

}
