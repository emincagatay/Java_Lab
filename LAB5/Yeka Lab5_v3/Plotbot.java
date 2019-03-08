package de.tuhh.diss.plotbot;

import de.tuhh.diss.plotbot.shape.*;
import lejos.nxt.Button;
import lejos.nxt.LCD;


public class Plotbot {
	

	
	public static void main(String[] args)
	{
		
		Calibration myCalib = new Calibration();
		MainMenu myMainMenu;
		int[] maxParameters = new int[2];
		int[] selectionFromMenu = new int[3];
		PlotbotControl mypc;
		
		// Some example code to check if the build process works
		LCD.drawString("Compiled successfully", 0, 0);
		LCD.drawString("Good Luck!", 0, 1);
		Button.ESCAPE.waitForPressAndRelease();
		
		//calibrates the maximum angle and height that the motors can go to
		maxParameters = myCalib.calibration();
		// Initialize PlotbotController with said parameters
		mypc = new PlotbotControl(maxParameters[0], maxParameters[1]);
		myMainMenu = new MainMenu(mypc);
		do{
			/* Show Menu 
			 * This method returns the selected option
			 * as well as the measures that were input for height and width*/
			selectionFromMenu = myMainMenu.start();
			
			if(selectionFromMenu[0] == 0){				
				maxParameters = myCalib.calibration();
				mypc = new PlotbotControl(maxParameters[0], maxParameters[1]); // And adjust the PC to the parameters
				myMainMenu = new MainMenu(mypc);
			}
			else if(selectionFromMenu[0] == 1){
				Triangle myTriangle = new Triangle(selectionFromMenu[1], selectionFromMenu[2]);
				
				try{
					myTriangle.plot(mypc);
				}
				catch(Exception e){
					LCD.clear();
					LCD.drawString(e.getMessage(), 0, 0);
				}
				
			}
			else if(selectionFromMenu[0] == 2){
				Ship myShip = new Ship(selectionFromMenu[2]);
				
				try{
					myShip.plot(mypc);
				  }
				catch(Exception e){
					LCD.clear();
					LCD.drawString(e.getMessage(), 0, 0);
				}
				
			}
		
		
		} while (! Button.ESCAPE.isDown());
		
			
	}
	
	
	
	
	
	
	
}
