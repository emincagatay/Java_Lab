package de.tuhh.diss.io;
import de.tuhh.diss.io.SimpleIO;



public class EigenvalueUpperBound {
	public static final int MAX_MATRIXSIZE=3;
	
	public static double obtainFrobeniusNorm(double[][] matrix_a){
		double frobeniusNorm = 0;
		double pow_sum = 0;
		
		for(int i=0;i<MAX_MATRIXSIZE;i++){
			for(int j=0;j<MAX_MATRIXSIZE;j++){
				pow_sum += Math.pow(matrix_a[i][j],2);				
			}					
		}				
		frobeniusNorm = Math.sqrt(pow_sum);
		return frobeniusNorm;		
		
	}


	
	
	public static void main(String[] args){
		double[][] MatrixA = new double[MAX_MATRIXSIZE][MAX_MATRIXSIZE];
		
		for(int i=0;i<MAX_MATRIXSIZE;i++){
			for(int j=0;j < MAX_MATRIXSIZE;j++){
				SimpleIO.println("Enter the number for "+(i+1)+". row, "+(j+1)+". column");
				MatrixA[i][j]= SimpleIO.readDouble();
			}
		}
		/*for (int i = 0; i < MatrixA.length; i++) {
		    for (int j = 0; j < MatrixA[i].length; j++) {
		        System.out.print(MatrixA[i][j] + " ");
		    }
		    System.out.println();
		}*/
		double frobeniusNorm = obtainFrobeniusNorm(MatrixA);
		
		SimpleIO.println("Frobenius norm of the entered matrix is "+ frobeniusNorm);
		
		

		
	}

}
