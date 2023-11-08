package sectionC;

import hw1.AirportVan;
/**
* Examples of some possible test cases for help in developing
* the AirportVan code.
* @author smkautz
*/
public class SimpleTest {
	
	public static void main(String[] args) {
	// accumulatin miles
	// here all constructor parameters can be ignored
	AirportVan v = new AirportVan(0, 0, 0, 0);
	v.drive(20, 0);
	System.out.println(v.getTotalMiles());
	System.out.println("Expected 20");
	
	
	// accumulating miles and time
	v = new AirportVan(0, 0, 0, 0);
	v.drive(20, 60);
	v.drive(10, 15);
	System.out.println(v.getTotalMiles());
	System.out.println("Expected 30");
	System.out.println(v.getTotalTime());
	System.out.println("Expected 75");
	
	
	// keeping track of rider count
	// constructs a van with max of 3 riders
	v = new AirportVan(0, 0, 0, 3);
	v.pickUp();
	v.pickUp();
	System.out.println(v.getRiderCount());
	System.out.println("Expected 2");
	v.pickUp();
	System.out.println(v.getRiderCount());
	System.out.println("Expected 3");
	
	
	v.pickUp(); // does nothing, can't go over max riders
	System.out.println(v.getRiderCount());
	System.out.println("Expected 3");
	v.dropOff(0);
	System.out.println(v.getRiderCount());
	System.out.println("Expected 2");
	v.dropOff(0);
	System.out.println(v.getRiderCount());
	System.out.println("Expected 1");
	v.dropOff(0);
	System.out.println(v.getRiderCount());
	System.out.println("Expected 0");
	v.dropOff(0); // does nothing, can't go below 0
	System.out.println(v.getRiderCount());
	System.out.println("Expected 0");
	// bonus points for waiting when there is 1 rider
	// try with 5 points per minute
	v = new AirportVan(0, 0, 5, 3);
	v.pickUp();
	v.drive(0, 100);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 500");
	v.drive(0, 50);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 750");
	// bonus points for waiting when there are three riders
	v = new AirportVan(0, 0, 5, 3);
	v.pickUp();
	v.pickUp();
	v.pickUp();
	v.drive(0, 100);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 500");
	v.drive(0, 50);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 750");
	System.out.println("bonus points for waiting when there are no riders"); 
	v = new AirportVan(0, 0, 5, 3);
	v.drive(0, 100);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 0");
	v.drive(0, 50);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 0");
	v.dropOff(10);
	System.out.println(v.getTotalTips());
	System.out.println("Expected 7");
	// bonus points for miles with one rider,
	System.out.println("using 10 bonus points per mile"); 
	v = new AirportVan(0, 10, 5, 3);
	v.pickUp();
	v.drive(2, 0);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 20");
	v.drive(5, 0);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 70");
	System.out.println("bonus points for miles with three riders");
	
	
	v = new AirportVan(0, 10, 5, 3);
	v.pickUp();
	v.pickUp();
	v.pickUp();
	v.drive(2, 0);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 60");
	v.drive(5, 0);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 210");
	// bonus points for miles with no riders => done 
	v = new AirportVan(0, 10, 5, 3);
	v.drive(2, 0);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 20");
	v.drive(5, 0);
	System.out.println(v.getTotalPoints());
	System.out.println("Expected 70");
	// tips
	v = new AirportVan(0, 10, 5, 3);
	v.pickUp();
	v.pickUp();
	v.dropOff(5);
	System.out.println(v.getTotalTips());
	System.out.println("Expected 5");
	v.dropOff(2);
	System.out.println(v.getTotalTips());
	System.out.println("Expected 7");
	// tricky bit - there isn't actually a rider so dropOff should do nothing
	v.dropOff(10);
	System.out.println(v.getTotalTips());
	System.out.println("Expected 7");
	
	//test case for the prposed example on page 3
	v = new AirportVan(12.0, 10, 5, 3);
		v.drive(8, 10);
	    v.waitAround(5);// Simulate driving and picking up passengers
	    v.pickUp();
	    v.drive(25, 30); // Drive 20 miles in 30 minutes
	    v.pickUp();
	    v.pickUp();
	    v.waitAround(15);
	    v.drive(20, 60); // Drive 15 miles in 25 minutes

	    // Drop off passengers with tips
	    v.dropOff(2.0);

	    // Define the multiplier for converting bonus points to dollars
	    double dollarsPerPoint = 0.02;
	    double totalTips = v.getTotalTips();
	    int totalPoints = v.getTotalPoints();// Calculate and print the total pay and average hourly pay
	    double totalPay = v.getTotalPay(dollarsPerPoint);
	    double averageHourlyPay = v.getAverageHourlyPay(dollarsPerPoint);



	    // Compare the calculated values with the expected values
	    System.out.println("Total Pay: $" + totalPay);
	   System.out.println("Average Hourly Pay: $" + averageHourlyPay);
	   System.out.println("Total bonus points is: " + totalPoints);
	   System.out.println("Total tips collect is: " + totalTips);
	}


	}
