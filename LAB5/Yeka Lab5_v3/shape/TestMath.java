package de.tuhh.diss.plotbot.shape;

import java.util.Arrays;


public class TestMath extends Coord {
	
	
	private static final int R1 = 80;
	private static final int R2 = 56;		
	
	
	
	
	
	public static void main(String arg[]) {

		
		/*
		int width = 23;
		
		int height = width*5/6;
		System.out.println("height is "+ height);
		*/
		
		/*
		Coord lowerLeftCorner = new Coord (width/6,230-height);
		System.out.println("lowerLeftCorner is "+"["+(width/6)+", "+ (230-height)+"]" );
		
		int[] a = lowerLeftCorner.xytoAngle();
		int[] b = lowerLeftCorner.angleToXY(a[0], a[1]);
		
		//System.out.println("a is "+Arrays.toString(a));
		System.out.println("b is "+Arrays.toString(b));

		int[] first = {(width-width/6),(230-height)};
		
		int[] second = {width,(230-height+height/5)};
		
		int[] third =  {0,(230-height+height/5)};
		
		int[] forth = {(width-4*(width/6)),(230-height+height/5)};
		
		int[] fifth = {(width-4*(width/6)),(230-height+2*(height/5))};
		
		int[] sixth = {(width-4*(width/6)),230};
		
		int[] seventh = {width,(230-height+2*(height/5))};
		
		int[] Tri = {(width-(width-4*(width/6))),(230-2*(height/5))};
		
		System.out.println("First is "+Arrays.toString(first));
		System.out.println("Second is "+Arrays.toString(second));
		System.out.println("Third is "+Arrays.toString(third));
		System.out.println("Forth"+Arrays.toString(forth));
		System.out.println("Fifth is "+Arrays.toString(fifth));
		System.out.println("Sixth is "+Arrays.toString(sixth));
		System.out.println("Seventh is "+Arrays.toString(seventh));
		System.out.println("Tri is "+Arrays.toString(Tri));
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		int height = 20;
		int width = 30;
		testtriangle(width,height);
		
	
			
		
		
		/*
		double x = 30;
		double y = 10;
		
		
		
		int arm_ang = (int) (Math.toDegrees(Math.asin(x/R1)*84));	
		
		int wheel_ang = (int) ((y+(R1*(1-Math.cos(Math.asin(x/R1)))))*(360/(Math.PI*R2))*5);		
	
		int[] angles = {arm_ang,wheel_ang};
		
		System.out.println(Arrays.toString(angles));
		
		int x1 = (int) Math.ceil(R1*Math.sin(Math.toRadians(arm_ang/84)));
		
		double y1_double = (Math.PI*R2*angles[1]/(360*5))-(R1*(1-Math.cos(Math.toRadians(arm_ang/84))));
				
		int y1 = (int) ((Math.PI*R2*wheel_ang/(360*5))-(R1*(1-Math.cos(Math.toRadians(arm_ang/84)))));
		
		int[] dist = {x1,y1};	
		
		//System.out.println("y1 double is "+y1_double);
		//System.out.println("y1 int is "+y1);
		System.out.println("["+-angles[0]+", "+angles[1]+"]");
		System.out.println(Arrays.toString(dist));
		
				
		if(y1_double - y1< 0.5) {
			
			y1 = (int) Math.floor((Math.PI*R2*angles[1]/(360*5))-(R1*(1-Math.cos(Math.toRadians(arm_ang/84)))));
		}
		else {
			y1 = (int) Math.ceil((Math.PI*R2*angles[1]/(360*5))-(R1*(1-Math.cos(Math.toRadians(arm_ang/84)))));
		}
		
		//System.out.println("After if, y1 int is "+y1);
		
				
		int a_sum = 0;
		int	A_Comp =0;
		if(Math.abs(angles[0]) != angles[0]){
		A_Comp = 720;
		 a_sum = A_Comp;
		}
		else{
		A_Comp = -720;
		a_sum = A_Comp;
		}
		
				
		int c_sum = 0;
		System.out.println("A_sum before for = "+a_sum);
		for(int i = 0; i<10;i++) {
			
			a_sum = a_sum+(-(angles[0])/10);
			c_sum = c_sum + ((angles[1])/10);
			
			//System.out.println("A_sum"+ i +" = "+a_sum);
			//System.out.println("C_sum"+ i +" = "+c_sum);
			
		}
		
		System.out.println("A_sum after for= "+a_sum);
		
		if( a_sum != (-angles[0]+A_Comp)) {
			System.out.println("Missing angle for arm.");
			a_sum = a_sum+(-angles[0]-a_sum);
			System.out.println("New A_sum = "+a_sum);					
		}
		
		
						
		if(a_sum == (-angles[0]+A_Comp)) {
			System.out.println("No missing angle for arm.");
		}
		
		
		if(c_sum != angles[1]) {
			
			System.out.println("Missing angle for wheel.\n");
			c_sum = c_sum+(angles[1]-c_sum);
			System.out.println("New C_sum = "+c_sum);
			
		}
		else if(c_sum == angles[1]) {
			System.out.println("No missing angle for wheel.");
		}
		
		*/
	}
	
	
	public static void testtriangle(int width,int height) {
		
		Coord lowleft = new Coord(0, 230-height);
		
		Triangle tri = new Triangle(width, height);
		tri.testTri();
	}
	

}
