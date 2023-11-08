package hw1;

public class AirportVan {
	/**
	 * @author Olivia Riniker
	 */
	
	/**
	 * is initialized with the hourly rate the driver recieves 
	 */
	private double hourlyRate;
	
	/** 
	 * is initialized with the mileage bonus for that constructed van 
	 */
    private int mileageBonus;
    
    /**
     * is initialized with the time bonus for that constructed van
     */
    private int timeBonus;
    
    /** 
     * is initialized woth the maximum number of riders for that constructed van 
     */
    private int maxRiders;
    
    /** 
     * gives the number fo riders in that van 
     */
    private int numRiders;
    
    /** 
     * gives the totals miles that van has driven since it was constructed 
     */
    private int totalMiles;
    
    /** 
     * gives the total time the van has driven or waited around since constructed 
     */
    private int totalTime;
    
    /** 
     * gives the total tips the driver has recieved since the van was constructed 
     */
    private double totalTips;
    
    /** 
     * gives the total points the driver has accumulated since the van was constructed 
     */
    private int totalPoints;
	
    
    /**
     * @param givenHourlyRate  The hourly wage rate for the driver.
     * @param givenMileageBonus The mileage bonus in points per mile.
     * @param givenTimeBonus    The time bonus in points per minute.
     * @param givenMaxRiders    The maximum number of riders allowed in the van.
     */
	public AirportVan(double givenHourlyRate, int givenMileageBonus, int givenTimeBonus, int givenMaxRiders) {
		hourlyRate = givenHourlyRate;
        mileageBonus = givenMileageBonus;
        timeBonus = givenTimeBonus;
        maxRiders = givenMaxRiders;
        numRiders = 0;
	}
	/**
	 * Simulates driving the van for the given number of miles 
	 * over the given number of minutes. 
	 * @param miles
	 * @param minutes
	 */
	public void drive(int miles, int minutes) {
		totalMiles += Math.max(0,  miles);
		totalTime += Math.max(0,  minutes);
		totalPoints += (minutes * timeBonus)*Math.min(1, numRiders);
		totalPoints += miles * mileageBonus * Math.max(1, numRiders);
	}
	/** 
	 * Reduces the number of riders by 1(not going below 0)
	 * and collects the given tip. 
	 * this method does nothing if the number of rider is already zero.
	 * The tip amount is given in dollars.
	 * @param tip
	 */
	public void dropOff(double tip) {
		totalTips += tip * Math.min(Math.max(numRiders, 0), 1);
		numRiders = Math.max(Math.min(numRiders-1,maxRiders ), 0);
	}
	/**
	 * reutrns the average hourly pay of the driver (total pay / time) 
	 * since this van was constructed. "total pay" includes 
	 * wages, tips, and bonus points. 
	 * @param dollarsPerPoint
	 * @return
	 */
	public double getAverageHourlyPay(double dollarsPerPoint) {
	    double totalPay = getTotalPay(dollarsPerPoint);
	    double hourlyRate = totalPay / (totalTime/60.0);// Update totalPay instance variable
	    return hourlyRate;
	}
	/**
	 * returns the number of riders currently in the van
	 * @return
	 */
	public int getRiderCount() {
		return numRiders; 
	}
	/**
	 * Returns the total miles driven since the van was constructed 
	 * @return
	 */
	public int getTotalMiles() {
		return totalMiles; 
	}
	/** 
	 * Returns the total pay earned by the driver since this van was constructed,
	 * including wages, tips, and bonus points, using the given multiplier 
	 * to convert bonus points to dollars.
	 * @param dollarsPerPoint
	 * @return
	 */
	public double getTotalPay(double dollarsPerPoint) {
		double totalBonusPoints = (double) totalPoints * dollarsPerPoint;
		double totalHours = (double) totalTime / 60;
		double totalWages = totalHours * hourlyRate;
		double totalPay = totalWages + totalTips + totalBonusPoints;
		return totalPay; 
	}
	/** 
	 * Returns the total bonus points earned by the driver since this van was constructed.
	 * @return
	 */
	public int getTotalPoints() {
		return totalPoints; 
	}
	/** 
	 * Returns the total time spent by the driver since this van was constructed, in
	 * minutes.
	 * @return
	 */
	public int getTotalTime() { 
		return totalTime; 
	}
	/**
	 * Returns the total tips earned by the driver since this van was constructed, in
	 * dollars.
	 * @return
	 */
	public double getTotalTips() { 
		return totalTips; 
	}
	/** 
	 * Increases the number of riders by 1; however, this method does nothing if the
	 * number of riders is already at the maximum for this van.
	 */
	public void pickUp() { 
		numRiders = Math.min(numRiders + 1, maxRiders);
		
	}
	/**
	 * Simulates the passage of time without the van moving. 
	 * equivelent to calling drive(0,minutes). 
	 * @param minutes
	 */
	public void waitAround(int minutes) {
		totalTime += Math.max(0,  minutes);
		totalPoints += (minutes * timeBonus)*Math.min(1, numRiders);
		
	}
}
