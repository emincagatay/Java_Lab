package de.tuhh.diss.io;
import de.tuhh.diss.io.SimpleIO;


public class NewtonsMethod {
	
	public static double f(double x){
		
		return Math.pow(x, 4)-2*Math.pow(x,3)-Math.pow(x, 2)-2*x+2;
	}
	public static double df(double x){
		
		return 4*Math.pow(x, 3)-6*Math.pow(x,2)-2*x-2;
	}
	
	public static void main(String[] args){
		
		double x0 =10;
		int count = 0;
	
		while (f(x0) > 0.0001){
			x0 = x0 - (f(x0)/df(x0));	
			count++;
						
		}
		
		SimpleIO.println("The number of iteration is "+count);
		SimpleIO.println("The approximated root of the function is "+x0);
		
		
		
	}

}
