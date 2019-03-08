package de.tuhh.diss.io;
import de.tuhh.diss.io.SimpleIO;


public class BmiCalculation {
	public static final double MAX_HEIGHT = 2.72;
	
	public static double BMI_average(double[][] matrix_a){
		double sum=0;
		double[] bmi = new double[matrix_a[0].length];
		for(int j =0;j<matrix_a[0].length;j++){
			bmi[j] = matrix_a[1][j]/Math.pow(matrix_a[0][j], 2);
			sum+=bmi[j];			
		}
		for(int k=0;k<bmi.length;k++){
			SimpleIO.println((k+1)+". Person's bmi is "+bmi[k]);
		}
	
		SimpleIO.println("Sum of the values is "+sum);	
		return sum/(matrix_a[0].length);
	}

	
	public static void main(String[] args){

		SimpleIO.println("How many people would you like to consider for the calculation: ");
		int NUM_PERSONS = SimpleIO.readInteger();
		while(NUM_PERSONS<=0){
			SimpleIO.println("Entered value is invalid. Please enter a valid(positive) number : ");
			NUM_PERSONS = SimpleIO.readInteger();
		}
			
	
			
		
		

		double[][] arr_bmi = new double[2][NUM_PERSONS];;
		
		for(int j=0;j<NUM_PERSONS;j++){
			for(int i=0;i<2;i++){				
				if(i == 0){
					SimpleIO.println("Enter your "+ (j+1) + ". height in meter: ");
					arr_bmi[i][j] = SimpleIO.readDouble();
					while(arr_bmi[i][j]<=0 || arr_bmi[i][j]>MAX_HEIGHT){
						SimpleIO.println("Entered value is invalid(physically impossible). Please enter a reasonable value for height: ");
						arr_bmi[i][j] = SimpleIO.readDouble();
						
					}continue;					
				}					
				if(i == 1){
					SimpleIO.println("Enter your "+ (j+1) + ". weight in kg: ");
					arr_bmi[i][j] = SimpleIO.readDouble();
					while(arr_bmi[i][j]<=0){
						SimpleIO.println("Entered value is invalid. Please enter the weight again: ");
						arr_bmi[i][j] = SimpleIO.readDouble();			
					}							
					
				}								
			}					
		}
		

		double avg= BMI_average(arr_bmi);
		SimpleIO.println("The average of every bmi is "+avg);
		
		
	}
}



