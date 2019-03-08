package de.tuhh.diss.plotbot.shape;

import de.tuhh.diss.plotbot.shape.Plottable;
import de.tuhh.diss.plotbot.PlotbotControl;


public class Ship implements Plottable{
	
	int width;
	int height;	

	
	public Ship(int width) {						
		
		this.width = width;	
		height = width*5/6;		
		
	}
	
	public void plot(PlotbotControl pc){
				
		pc.drawShip(this.width);
		
	}
	
}
