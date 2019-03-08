package de.tuhh.diss.plotbot.shape;
import java.lang.Math;

public class Coord {
	
	private static final int R1 = 80;
	private static final int R2 = 56;	
	private static final int G_RATIO_ARM = 84;
	private static final int G_RATIO_WHEEL = 5;
	private double x;
	private double y;	
	
	
	public Coord() {
		
		this(0, 0);
	}
	
	public Coord(int width, int height) {
		
		x = width;
		y = height;
	}
	
	/*
	 * turns given x and y distances to necessary arm and wheel angles,respectively.
	 * 
	 */
	public int[] xytoAngle() {
		
		
		
		int arm_ang = (int) (Math.toDegrees(Math.asin(x/R1)*G_RATIO_ARM));	
		
		
		int wheel_ang = (int) ((y+(R1*(1-Math.cos(Math.asin(x/R1)))))*(360/(Math.PI*R2))*G_RATIO_WHEEL);		
	
		int[] angles = {-arm_ang, wheel_ang};
		
		return angles;
		
	}
	
	/*
	 * turns given arm and wheel angles to corresponding distances in x and y directions,respectively.
	 * 
	 */
	
	public int[] angleToXY(int angArm, int angWheel) {		
		
		int x1 = (int) (R1*Math.sin(Math.toRadians(-angArm/G_RATIO_ARM)));
		
		double y1_double = (Math.PI*R2*angWheel/(360*G_RATIO_WHEEL))-(R1*(1-Math.cos(Math.toRadians(-angArm/G_RATIO_ARM))));
		
		int y1 = (int) ((Math.PI*R2*angWheel/(360*G_RATIO_WHEEL))-(R1*(1-Math.cos(Math.toRadians(-angArm/G_RATIO_ARM)))));
		
		if(y1_double - y1< 0.5) {
			
			y1 = (int) Math.floor((Math.PI*R2*angWheel/(360*G_RATIO_WHEEL))-(R1*(1-Math.cos(Math.toRadians(-angArm/G_RATIO_ARM)))));
		}
		else {
			y1 = (int) Math.ceil((Math.PI*R2*angWheel/(360*G_RATIO_WHEEL))-(R1*(1-Math.cos(Math.toRadians(-angArm/G_RATIO_ARM)))));
		}
		
		int[] dist = {x1, y1};
		
		return dist;
	}
	
}
