package de.tuhh.diss.ship;

import de.tuhh.diss.io.SimpleIO;

/**
 * A test class for all classes of the ship example.
 * 
 * @author Sven Koehler / Marcus Venzke / Lars Hanschke
 * 
 */
public class ShipTest {
	private static final double SIZE_OF_TANK = 300;         // Liters
	private static final double PETROL_CONSUMPTION = 10;    // Liters per hour
	private static final double SPEED = 21.6;					 // in knots (nautic miles per hour)	
	
	private static final int TEST_TOLERANCE_DIGITS_FUEL = 3; // Liters, 3 digits behind comma
	private static final String UNIT_FUEL = "l";             // Liters
	private static final int TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER = 3; // nautic miles, 3 digits behind comma
	private static final String UNIT_DISTANCE_COUNTERS = "M";           // nautic miles
	private static final int TEST_TOLERANCE_DIGITS_DISTANCE_DRIVEN = 3; // nautic miles, 3 digits behind comma
	private static final String UNIT_DISTANCE_DRIVEN = "M";           // nautic miles
	
	/**
	 * The main test method, tests many methods (but not all!) of the classes
	 */
	public static void main(String[] args) {
		// Create a new ship
		Ship myShip = new Ship(PETROL_CONSUMPTION, SIZE_OF_TANK, SPEED);

		// Test if all attributes of the new ship are cleared
		if (!test(1, myShip.getFillLevel(), 0, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;
		if (!test(2, myShip.getDistanceTotal(), 0, TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER, UNIT_DISTANCE_COUNTERS))
			return;
		if (!test(3, myShip.getDistanceSinceFilled(), 0, TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER, UNIT_DISTANCE_COUNTERS))
			return;
		
		// Try to fill the tank with a lot of patrol. Test if tank was filled with amount of its size
		// and is then full, but not more than full. 
		if (!test(4, myShip.fill(2 * SIZE_OF_TANK), SIZE_OF_TANK, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;
		if (!test(5, myShip.getFillLevel(), SIZE_OF_TANK, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;

		// Fill the maximum amount of patrol into the full tank (that is already full) 
		// Test that nothing is added to the and and that the tank is still full (not more that full)
		if (!test(6, myShip.fill(SIZE_OF_TANK), 0, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;
		if (!test(7, myShip.getFillLevel(), SIZE_OF_TANK, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;
		
		// Drive the ship forward until out of patrol. Test that it moved the correct distance.  
		double movedDistance = (SIZE_OF_TANK*SPEED) / PETROL_CONSUMPTION ;
		if (!test(8, myShip.sailForward(4000000), movedDistance, TEST_TOLERANCE_DIGITS_DISTANCE_DRIVEN, UNIT_DISTANCE_DRIVEN))
			return;

		// Check the fill level and distance, if they are calculated correctly
		if (!test(9, myShip.getFillLevel(), 0, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;
		if (!test(10, myShip.getDistanceTotal(), movedDistance, TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER, UNIT_DISTANCE_COUNTERS))
			return;
		if (!test(11, myShip.getDistanceSinceFilled(), movedDistance, TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER, UNIT_DISTANCE_COUNTERS))
			return;

		// Refill Tank. Check that it can be fill with its complete volume 
		// and afterwards it cannot be filled any more. 
		if (!test(12, myShip.fill(SIZE_OF_TANK), SIZE_OF_TANK, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;
		if (!test(13, myShip.fill(SIZE_OF_TANK / 2), 0, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;

		// Check that the tank is now full, but not more than full. 
		if (!test(14, myShip.getFillLevel(), SIZE_OF_TANK, TEST_TOLERANCE_DIGITS_FUEL, UNIT_FUEL))
			return;

		// Drive the ship only a short distance 
		final double SHORTDISTANCETODRIVE = 20; // nautic miles 
		if (!test(15, myShip.sailForward(SHORTDISTANCETODRIVE), SHORTDISTANCETODRIVE, TEST_TOLERANCE_DIGITS_DISTANCE_DRIVEN, UNIT_DISTANCE_DRIVEN))
			return;

		// Drive the ship forward until out of patrol. Check distance. 
		movedDistance = (SIZE_OF_TANK*SPEED) / PETROL_CONSUMPTION - SHORTDISTANCETODRIVE;
		if (!test(16, myShip.sailForward(4000000), movedDistance, TEST_TOLERANCE_DIGITS_DISTANCE_DRIVEN, UNIT_DISTANCE_DRIVEN))
			return;

		// Check the fill level and distance, if they are calculated correctly
		if (!test(17, myShip.getFillLevel(), 0, TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER, UNIT_DISTANCE_COUNTERS))
			return;
		movedDistance = (SIZE_OF_TANK*SPEED) * 2.0 / PETROL_CONSUMPTION;
		if (!test(18, myShip.getDistanceTotal(), movedDistance, TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER, UNIT_DISTANCE_COUNTERS))
			return;
		movedDistance = (SIZE_OF_TANK*SPEED) / PETROL_CONSUMPTION;
		if (!test(19, myShip.getDistanceSinceFilled(), movedDistance, TEST_TOLERANCE_DIGITS_DISTANCE_COUNTER, UNIT_DISTANCE_COUNTERS))
			return;

		System.out.println("All tests passed successfully");
	}

	/**
	 * Checks if the result of test is what is expected. The result is returned. It is also displayed to the user.
	 * 
	 * @param testNumber      Number of the performed test. The number is displayed to the user.
	 * @param value           Value returned by the method under test
	 * @param expected        Value expected to be returned by the method under test
	 * @param toleranceDigits Number of digits behind comma considered for comparing value and expected value
	 * @param unit            Physical unit of the value (for displaying it to the user)
	 * @return                Returns true if the value equals the expected value with the tolerance defined in toleranceDigits. Otherwise it returns false.  
	 */
	private static boolean test(int testNumber, double value, double expected, int toleranceDigits, String unit) {
		double tolerance = Math.pow(10, -toleranceDigits);
		return testResult(testNumber, Math.abs(value - expected) < tolerance, value, expected, toleranceDigits, unit);
	}

	/**
	 * Display the result of the test to the user.
	 * 
	 * @param testNumber      Number of the performed test. The number is displayed to the user.
	 * @param testResult      Result of the test. It is true if the test was successful and false otherwise. 
	 * @param value           Value returned by the method under test
	 * @param expected        Value expected to be returned by the method under test
	 * @param toleranceDigits Number of digits behind comma considered for comparing value and expected value
	 * @param unit            Physical unit of the value (for displaying it to the user)
	 * @return                Returns the value of parameter testResult  
	 */
	private static boolean testResult(int testNumber, boolean testResult, double value, double expected, int toleranceDigits, String unit) {
		SimpleIO.print("Test " + testNumber + ": ");
				
		if (testResult)
			SimpleIO.print("PASSED");
		else
			SimpleIO.print("FAILED");
		
		SimpleIO.println("");
		
		return testResult;
	}
}
