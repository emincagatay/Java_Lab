package de.tuhh.diss.plotbot;
import de.tuhh.diss.plotbot.shape.*;
import lejos.nxt.Motor;

/**
 * This Class is used for control of the plotting robot. A great amount of time should spend for controlling the robot.
 * Add a suitable constructor and add further methods you need for driving the motors, evaluating the sensors etc.
 */
public class PlotbotControl {	
	
	private static final int Y_GAP = 25;
	private static final int initialSpeed = 360;
	private static final int initialSpeed2 = 200;
	private static final int COMP_ANG = 720;
	
	private static final int R1 = 80;

	private static final int G_RATIO_ARM = 84;

	
	private int MaxArmAngle;
	private int MaxPenUp;
	
	
	
	public PlotbotControl(int MaxArmAngle, int MaxPenUp) {
		this.MaxArmAngle = MaxArmAngle;
		this.MaxPenUp = MaxPenUp;
		
		Motor.C.setSpeed(initialSpeed);
		Motor.B.setSpeed(initialSpeed2);
		Motor.A.setSpeed(initialSpeed);
	}
	
		
	
	public int getMaxArmAngle() {
		return MaxArmAngle;
	}
		

	
	public void drawShip(int width) {
		
		int height = width*5/6;		
		
		//creating coordinates of the points of the ship
		Coord lowerLeftCorner = new Coord (width/6, (230-height));		
		Coord first = new Coord((width-width/6), (230-height));		
		Coord second = new Coord(width, (230-height+height/5));		
		Coord third =  new Coord(0, (230-height+height/5));		
		Coord forth = new Coord((width-4*(width/6)), (230-height+height/5));		
		Coord fifth = new Coord((width-4*(width/6)), (230-height+2*(height/5)));		
		Coord sixth = new Coord((width-4*(width/6)), 230);		
		Coord seventh = new Coord(width, (230-height+2*(height/5)));	
		
		//introducing the lines of the ship. 
		Line line1 = new Line(lowerLeftCorner, first);		
		Line line2 = new Line(first,second);
		
		Line line3 = new Line(second,third);
		line3.getStartAngs()[0] = line3.getStartAngs()[0] + COMP_ANG;
		line3.getEndAngs()[0] = line3.getEndAngs()[0] + COMP_ANG;
		
		Line line4 = new Line(third,lowerLeftCorner);
		Line line5 = new Line(forth,fifth);		
		
		goStart(lowerLeftCorner);
		
		setLine(line1);
		setLine(line2);
		setLine(line3);
		Motor.A.rotate(-COMP_ANG);//takes cares of the clearance in arm motor.
		//Motor.A.resetTachoCount();
		setLine(line4);
		setLine(line5);
		drawTri(fifth,sixth,seventh);		
	
	}
		
	
	public void drawTri(Coord LowLeftCorner, Coord HighLeftCorner, Coord LowRightCorner) {
		
		
		int[] LowLeftAng = LowLeftCorner.xytoAngle();
		int[] HighLeftAng = HighLeftCorner.xytoAngle();
		int[] LowRightAng = LowRightCorner.xytoAngle();		
		int[] move2 = {(LowRightAng[0]-HighLeftAng[0]),(LowRightAng[1]-HighLeftAng[1])};
		int[] move3 = {(LowLeftAng[0]-LowRightAng[0]),(LowLeftAng[1]-LowRightAng[1])};
				
		
		Motor.B.rotateTo(MaxPenUp, false);
		
		
		//checks it the pen is at the stating point. If not motors go to starting coordinates.		
		if(Motor.C.getTachoCount() != LowLeftAng[1]) {
			goStart(LowLeftCorner);	
		}
			
			
		
		Motor.B.rotate(0, false);
		Motor.C.rotateTo(HighLeftAng[1], false);
		
						
		for(int i = 0; i < 10 ; i++) {
			
			Motor.A.rotate(-((move2[0])/10), false);
			Motor.C.rotate((move2[1])/10, false);
		}
				
		/*
		 * Since there is mathematical possibility of that there may be a remainer of the division
		 * operation above. In order to make sure of that robots reach the desired destination
		 * we check the current position of the motor after the movement. Then, robots go to desired
		 * coordinates if they are not already at the desired coordinates. 		 * 
		 */
		
		if( Motor.A.getTachoCount() != LowRightAng[0]) {
			Motor.A.rotateTo(LowRightAng[0], false);			
		}
		
		
		if( Motor.C.getTachoCount() != LowRightAng[1]) {
			Motor.C.rotateTo(LowRightAng[1], false);			
		}
		
		pause(500);			
			
				
		Motor.B.rotateTo(MaxPenUp,false);
		
		
		//since the direction of arm motor will change, first it overcomes the compensate.
		Motor.A.rotate(COMP_ANG,false);
		//drops the pen for drawing process.
		Motor.B.rotate(0, false);
		
		//Motors start to draw.		
		for(int i = 0; i<10;i++) {
			
			Motor.A.rotate((move3[0])/10, false);
			Motor.C.rotate((move3[1])/10, false);
		}
				
		/*
		 * Since there is mathematical possibility of that there may be a remainer of the division
		 * operation above. In order to make sure of that robots reach the desired destination
		 * we check the current position of the motor after the movement. Then, robots go to desired
		 * coordinates if they are not already at the desired coordinates. 		 * 
		 */
		if( Motor.A.getTachoCount() != (LowLeftAng[0]+COMP_ANG)) {
			Motor.A.rotateTo((LowLeftAng[0]+COMP_ANG), false);			
		}						
		if( Motor.C.getTachoCount() != LowLeftAng[1]) {
			Motor.C.rotateTo(LowLeftAng[1], false);			
		}
				
		pause(500);		
							
		//All motors return to their initial positions.
		Motor.B.rotateTo(MaxPenUp, false);
		Motor.A.rotateTo(0, false);
		Motor.C.rotateTo(0, false);			
	}
	/**
	 * Moves the robot to starting point and compensates the clearance in the arm movement
	 * 
	 * @param start Coordinate of the desired starting point
	 */
	public void goStart(Coord start) {
		
		Coord gap = new Coord(0, Y_GAP);			
		int[] gap_angs = gap.xytoAngle();
		int[] start_angles = start.xytoAngle();		
		
		Motor.C.rotateTo(gap_angs[1], false);
		Motor.A.rotate(-COMP_ANG, false);
		Motor.A.resetTachoCount();
		Motor.A.rotate(start_angles[0], false);
		Motor.C.rotateTo(start_angles[1]);		
		
		
	}	
	
	/**
	 * Decides whether a Line is vertical (and will only require motion of 1 motor) or not
	 * @param a is said Line
	 *
	 */
	public void setLine(Line a) {
		
		int[] end_ang = a.getEndAngs();
		int[] start_ang = a.getStartAngs();
		
		if(end_ang[0]-start_ang[0] == 0) {
			
			drawLine1(start_ang,end_ang);			
		}
		
		else {			
			drawLine2(start_ang,end_ang);		
		}
		
	}		
	
	public void drawLine1(int[] start_ang, int[]end_ang) {
		
		Motor.B.rotateTo(MaxPenUp, false);
		
		if(Motor.A.getTachoCount() != start_ang[0]) {
			Motor.A.rotateTo(start_ang[0], false);
		}
		
		if(Motor.C.getTachoCount() != start_ang[1]) {
			Motor.C.rotateTo(start_ang[1], false);
		}
		
		Motor.B.rotate(0, false);
		Motor.C.rotateTo(end_ang[1], false);
		Motor.B.rotateTo(MaxPenUp, false);	
		
	}
	
	public void drawLine2(int[] start_ang, int[]end_ang) {
		
		Motor.B.rotateTo(MaxPenUp, false);		
		
		
		if(Motor.A.getTachoCount() != start_ang[0]) {
			Motor.A.rotateTo(start_ang[0],false);
		}
		
		if(Motor.C.getTachoCount() != start_ang[1]) {
			Motor.C.rotateTo(start_ang[1], false);
		}
		
		
		Motor.B.rotate(0, false);
		
		// Draws the line in 10 steps (discrete)
		for(int i = 0; i < 10 ;i++) {
			
			Motor.A.rotate(((end_ang[0]-start_ang[0])/10), false);
			Motor.C.rotate((end_ang[1]-start_ang[1])/10, false);			
		}
		
		
		// Makes sure that the motors reached the correct ending point
		if( Motor.A.getTachoCount() != end_ang[0]) {
			Motor.A.rotateTo(end_ang[0], false);			
		}
		
		if( Motor.C.getTachoCount() != end_ang[1]) {
			Motor.C.rotateTo(end_ang[1], false);			
		}
		
		pause(500);		
	
		Motor.B.rotateTo(MaxPenUp, false);	
		
		pause(500);	
	}	
	
		
	public void pause(int milisec) {
		
		try {
			Thread.sleep(milisec);
		}
		catch (InterruptedException e) {
			
		}		
	}
		
	
	
	/**
	 * Converts the arm motor's tacho count to distance in mm.
	 * 
	 * @return   maximum allowed width 
	 */
	public int getMaxWidth (){
		int x1 = (int) (R1*Math.sin(Math.toRadians(-MaxArmAngle/G_RATIO_ARM)));
		return x1;
	}
	
}
