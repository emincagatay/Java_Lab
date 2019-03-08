package de.tuhh.diss.plotbot.shape;
import de.tuhh.diss.plotbot.shape.Plottable;
import de.tuhh.diss.plotbot.PlotbotControl;
import java.util.Arrays;

public class Triangle implements Plottable{
	
	
	private Coord LowLeftCorner;
	private Coord HighLeftCorner;
	private Coord LowRightCorner;	
	private int height;
	private int width;
	
	
	
	/*
	public Triangle(int width,int height) {
		
		this.height = height;
		this.width = width;
		LowLeftCorner = new Coord(0,230-this.height);
		int[] angLowLeft = LowLeftCorner.xytoAngle();
		int[] xyLowLeft = LowLeftCorner.angleToXY(angLowLeft[0], angLowLeft[1]);
		HighLeftCorner = new Coord(xyLowLeft[0],xyLowLeft[1]+this.height);
		LowRightCorner = new Coord(xyLowLeft[0]+this.width,xyLowLeft[1]);
	}
	*/
	
	
	
	public Triangle(int width, int height) {
		
		this.height = height;
		this.width = width;	
		LowLeftCorner = new Coord(0, 230-this.height);
		
		int[] angLowLeft = LowLeftCorner.xytoAngle();
		int[] xyLowLeft = LowLeftCorner.angleToXY(angLowLeft[0], angLowLeft[1]);
		// LOOK AT POSSIBILITY OF USING A GET FUNCOITN FOR XY INSTEAD OF HAVING TO CONVERT
		HighLeftCorner = new Coord(xyLowLeft[0], xyLowLeft[1]+this.height);
		LowRightCorner = new Coord(xyLowLeft[0]+this.width, xyLowLeft[1]);		
	}
	
	
	public Triangle(Coord LowLeft, Coord HighLeft, Coord LowRight) {
		LowLeftCorner = LowLeft;
		HighLeftCorner = HighLeft;
		LowRightCorner = LowRight;		
	}
		
			
	
	public void plot(PlotbotControl pc){
				
		pc.drawTri(LowLeftCorner, HighLeftCorner, LowRightCorner);	
		
	}
	
	
	

	
	public void testTri() {
		//THIS IS JUST FOR TESTING, WONT HAND IN
		
		int[] angLowLeft = LowLeftCorner.xytoAngle();
		int[] xyLowLeft = LowLeftCorner.angleToXY(angLowLeft[0], angLowLeft[1]);
		System.out.println("LowLeftCorner is "+Arrays.toString(xyLowLeft));
		
		System.out.println("HighLeftCorner are "+(xyLowLeft[0])+" and "+ (xyLowLeft[1]+this.height));
		System.out.println("LowRightCorner are "+(xyLowLeft[0]+this.width)+" and "+ xyLowLeft[1]);
		
		int[] LowLeftAng = LowLeftCorner.xytoAngle();		
		int[] HighLeftAng = HighLeftCorner.xytoAngle();
		int[] LowRightAng = LowRightCorner.xytoAngle();
		int[] move2 = {(LowRightAng[0]-HighLeftAng[0]),(LowRightAng[1]-HighLeftAng[1])};
		int[] move3 = {(LowLeftAng[0]-LowRightAng[0]),(LowLeftAng[1]-LowRightAng[1])};
		
		
		System.out.println("Lowleftangles are "+Arrays.toString(LowLeftAng));
		System.out.println("highleftangles are "+Arrays.toString(HighLeftAng));
		System.out.println("lowrightangles are"+Arrays.toString(LowRightAng));
		System.out.println("move2 angles are"+Arrays.toString(move2));
		System.out.println("move3 angles are"+Arrays.toString(move3));
		
		
		System.out.println("executing move1\n");
		System.out.println("C rotates to "+LowLeftAng[1]+" then c rotates to "+HighLeftAng[1]+"\n");
		
		System.out.println("executing move2\n");
		int a_sum = 0;
		int	A_Comp =0;
		if(move2[0] > 0){
		A_Comp = 720;
		 a_sum = A_Comp;
		}
		else{
		A_Comp = -720;
		System.out.println("A rotates "+A_Comp);
		a_sum = A_Comp;
		}
		int c_sum = HighLeftAng[1];
		
		
		System.out.println("A_sum before for = "+a_sum);
		System.out.println("C_sum before for = "+c_sum);
		for(int i = 0; i<10;i++) {
			
			a_sum = a_sum+((move2[0])/10);
			c_sum = c_sum + ((move2[1])/10);
			
			System.out.println("A"+i+" rotates "+(((move2[0])/10))+" while C"+i+" rotates "+(move2[1])/10);
			System.out.println("A_sum"+ i +" = "+a_sum);
			System.out.println("C_sum"+ i +" = "+c_sum);
			
		}
		

		System.out.println("A_sum after for= "+a_sum);
		
		if( a_sum != (LowRightAng[0]+A_Comp)) {
			System.out.println("Missing angle for arm.");
			System.out.println("A rotates to "+(-LowRightAng[0]+A_Comp));
			
			a_sum = a_sum+(LowRightAng[0]-a_sum);
			System.out.println("New A_sum = "+a_sum);					
		}
		
		
						
		else if(a_sum == (LowRightAng[0]+A_Comp)) {
			System.out.println("No missing angle for arm.");
		}
		
		
		if(c_sum != LowRightAng[1]) {
			
			System.out.println("Missing angle for wheel.\n");
			System.out.println("C rotates to "+(LowRightAng[1]));
			
			c_sum = LowRightAng[1];
			System.out.println("New C_sum = "+c_sum);
			
		}
		else if(c_sum == LowRightAng[1]) {
			System.out.println("No missing angle for wheel.");
		}
		
		System.out.println("executing move3\n");
		
		
		
		if(move3[0] > 0){
		A_Comp = 720;
		 a_sum = a_sum+A_Comp;
		 System.out.println("A rotates "+A_Comp);
		}
		else{
		A_Comp = -720;
		System.out.println("A rotates "+A_Comp);
		a_sum = a_sum+A_Comp;
		}
		
		
		
		System.out.println("A_sum before for = "+a_sum);
		System.out.println("C_sum before for = "+c_sum);
		for(int i = 0; i<10;i++) {
			
			a_sum = a_sum+((move3[0])/10);
			c_sum = c_sum + ((move3[1])/10);
			
			System.out.println("A"+i+" rotates "+(((move3[0])/10))+" while C"+i+" rotates "+(move3[1])/10);
			System.out.println("A_sum"+ i +" = "+a_sum);
			System.out.println("C_sum"+ i +" = "+c_sum);
			
		}
		

		System.out.println("A_sum after for= "+a_sum);
		
		if( a_sum != (LowLeftAng[0])) {
			System.out.println("Missing angle for arm.");
			System.out.println("A rotates to "+(LowRightAng[0]));
			
			a_sum = LowLeftAng[0];
			System.out.println("New A_sum = "+a_sum);					
		}
		
		
						
		else if(a_sum == (LowLeftAng[0])) {
			System.out.println("No missing angle for arm.");
		}
		
		
		if(c_sum != LowLeftAng[1]) {
			
			System.out.println("Missing angle for wheel.\n");
			System.out.println("C rotates to "+(LowRightAng[1]));
			
			c_sum = LowLeftAng[1];
			System.out.println("New C_sum = "+c_sum);
			
		}
		else if(c_sum == LowLeftAng[1]) {
			System.out.println("No missing angle for wheel.");
		}		
	}

	

}
