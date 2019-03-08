package de.tuhh.diss.plotbot;


import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class MainMenu {

	private static final String[] ITEMS = {"Calibration","Triangle", "Ship"};	//add new text menu entries here
	private	static final String TITLE = "Choose Shape to draw:";
	private	static final String TITLE_NUMS = "Choose Dimension:";
	private static final String[] NUMBERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private static final int MAX_HEIGHT = 230;

	private TextMenu menu;
	private TextMenu numbers;
	private PlotbotControl mypc; 
	private int maxWidth;

	/**
	 * Creates a new MainMenu object.
	 */
	public MainMenu(PlotbotControl pc) {
		menu = new TextMenu(ITEMS, 1, TITLE);
		mypc = pc;
		maxWidth = mypc.getMaxWidth();
		
		
	}
	/**
	 * Displays possible options on the LCD screen. 
	 * Asks for input values (height and/or width) depending on the selected choice.
	 * 
	 * @return		int[] which consists of the user selection, entered height and width
	 */
	public int[] start(){
		int selection = -1;
		int height = 0;
		int width = 0;
		do {
			selection = menu.select();
		} while(selection < 0);
		LCD.clear();
	
		if (selection == 0) {
			
			
		}	else if (selection == 1){
			
				do {
					askHeight();
					height = sizeSelection();
					LCD.drawString(height + " mm is", 0, 0);
					LCD.drawString("the height", 0, 1);
					Button.ENTER.waitForPressAndRelease();
					checkHeight(height);
					} while (height > MAX_HEIGHT);
			
			
				do {
					askWidth();
					width = sizeSelection();
					LCD.drawString(width + " mm is", 0, 0);
					LCD.drawString("the width", 0, 1);
					Button.ENTER.waitForPressAndRelease();
					checkWidth(width);
					
				} while(width > maxWidth);
			
			
			
			
			
		}	else if (selection == 2){
				do{
					askWidth();
					width = sizeSelection(); 
					LCD.drawString(width + " mm is", 0, 0);
					LCD.drawString("the width", 0, 1);
					Button.ENTER.waitForPressAndRelease();
					checkWidth(width);
				} while (width > maxWidth);
			
		}
		int[] selAndDim = new int[3];
		selAndDim[0] = selection;
		selAndDim[1] = height;
		selAndDim[2] = width;
		return selAndDim;
	}
	/**
	 * Asks the user for a dimension. Each digit has to be selected separately.
	 * 
	 * @return   dimension in mm of the desired measure (height or width)
	 */
	public int sizeSelection(){
		int[] size= {0, 0, 0};
		int finalSize;
		numbers = new TextMenu(NUMBERS, 1, TITLE_NUMS);
				
		LCD.clear();
		//draw side by side
		for(int i = 0; i < 3; i++){
			LCD.clear();
			for(int j = 0; j<3; j++){
				LCD.drawInt(size[j], j, 0); //on the same row, draw one after another the 3 numbers
				
				
			}
		LCD.drawString("mm", 5, 0);
		Button.ENTER.waitForPressAndRelease();
		//wait until someone presses enter and then give options 0-9 for 1s digit
		
		LCD.clear();
		//display options for digit and return the chosen one
		size[i] = numbers.select();
		}
		
		//putting together the size in mm
		finalSize = size[0]*100 + size[1]*10 + size[2];
		
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
		
		return finalSize;
	}
	
	private void askHeight(){
		LCD.clear();
		LCD.drawString("Plz, introduce", 0, 0);
		LCD.drawString("the height", 0, 1);
		Button.ENTER.waitForPressAndRelease();
		
	}
	private void askWidth(){
		LCD.clear();
		LCD.drawString("Plz, introduce", 0, 0);
		LCD.drawString("the width", 0, 1);
		Button.ENTER.waitForPressAndRelease();
	}
	private void checkHeight(int height){
		
		boolean parameter = height > MAX_HEIGHT;
		if (parameter) {
			LCD.clear();
			LCD.drawString("The max. allowed ", 0, 0);
			LCD.drawString(" height is " + MAX_HEIGHT +" mm", 1, 0);
		}
	
	}

	private void checkWidth(int width){
		
		boolean parameter = width > maxWidth;
		if (parameter) {
			LCD.clear();
			LCD.drawString("The max. allowed ", 0, 0);
			LCD.drawString(" width is " + maxWidth +" mm", 1, 0);
		}
	
	}
	
	
}
