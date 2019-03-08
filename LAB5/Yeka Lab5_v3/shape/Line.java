package de.tuhh.diss.plotbot.shape;
import de.tuhh.diss.plotbot.shape.Plottable;
import de.tuhh.diss.plotbot.PlotbotControl;


public class Line   {	
	
	private Coord start;
	private Coord end;
	private int[] start_ang;
	private int[] end_ang;
	
	
	public Line() {
		
		start = new Coord();
		end = new Coord();	
		start_ang = new int[2];
		end_ang = new int[2];
		
	}
	
	public Line(Coord start, Coord end){
		
		this.start = start;
		this.end = end;		
		start_ang = this.start.xytoAngle();
		end_ang = this.end.xytoAngle();		
		
		
	}			

		
	
	
	
		
	public int[] getStartAngs(){
		
		return this.start_ang;
		
	}
	
	public int[] getEndAngs(){
		
		return this.end_ang;
		
	}	

}
