package hw2;

/** 
 * This class represents a basic model for 
 * a simplified American football game. 
 * 
 * @author Olivia Riniker
 */
public class Handegg
{
	/**
	 * current location of the ball 
	 */
	private int location; 
	/**
	 * Scores for each team (team 0, team 1)
	 */
	private int[] score; 
	/**
	 * team currently on offense (0 or 1)
	 */
	private int offenseTeam; 
	/**
	 * the amount of downs the team on offense currently has (e.g. 1st, 2nd)
	 */
	private int currentDown; 
	/**
	 * yards needed by team on offesne to achieve a first down 
	 */
	private int yardsToFirstDown;
	
  /**
   * Number of points awarded for a touchdown.
   */ 
  public static final int TOUCHDOWN_POINTS = 6;
  
  /**
   * Number of points awarded for a successful extra point attempt
   * after a touchdown.
   */
  public static final int EXTRA_POINTS = 1;
  
  /**
   * Number of points awarded for a field goal.
   */
  public static final int FIELD_GOAL_POINTS = 3;
  
  /**
   * Total length of the field from goal line to goal line, in yards.
   */
  public static final int FIELD_LENGTH = 100;
  
  /**
   * Initial position of the offensive team after a touchdown and extra point attempt.
   */
  public static final int STARTING_POSITION = 30;
  
  /**
   * Yards required to get a first down.
   */
  public static final int YARDS_FOR_FIRST_DOWN = 10;
  
  // Constructor
  public Handegg() {
	  location = STARTING_POSITION;
	  score = new int[2]; 
	  offenseTeam = 0;     
	  currentDown = 1;     
	  yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
  }
  
  /** 
   * returns the current location of the ball
   * @return
   */
  public int getLocation() { 
	  return location;
  }
  /**
   * returns the score of the specified team (0 or 1)
   * @param team
   * @return
   */
  public int getScore(int team) { 
	  return score[team];
  }
  /**
   * returns the team (0 or 1) that is currently on offense 
   * @return
   */
  public int whoIsOffense() { 
	  return offenseTeam;
  }
  /** 
   * returns the current downs 
   * @return
   */
  public int whichDown() { 
	  return currentDown; 
  }
  /** 
   * returns the number of yards needed to achieve a first down 
   * @return
   */
  public int getYardsToFirstDown() { 
	  return yardsToFirstDown; 
  }
  /**
   * moves the ball a specified number of yards, changes the offense, and updates the downs 
   * @param yards
   */
  public void punt(int yards) { 
	  changeOffense();
	  moveBall(yards);
	  location = FIELD_LENGTH - location;
	  nonNegLocation();
  }
  /**
   * attempts a feild goal and updates the score and ball location accordingly 
   * @param success
   */
  public void fieldGoal(boolean success) { 
	  if(success) { 
		  score[offenseTeam] += FIELD_GOAL_POINTS;
	  	  yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
	  	  location = 30;
	  }
	  else { 
		  location = FIELD_LENGTH - location;
	  }
	  changeOffense();
	  currentDown = 1;
	  ;
  }
  /** 
   * handles an extra point attempt and updates the score, offense team, and ball location 
   * @param success
   */
  public void extraPoint(boolean success) { 
	  if (success) { 
		  score[offenseTeam]+= EXTRA_POINTS;
		  currentDown = 1;
		  yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
		  
	  }
	  changeOffense();
	  location = 30;
  }
  /** 
   * runs the ball a specified number of yards updates the ball location and checks touchdowns. 
   * Also keeps track of downs and yards needed for a first down
   * @param yards
   */
  public void run(int yards) { 
	  moveBall(yards);
	  
	  if (yards >= yardsToFirstDown) {
		  yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
	      currentDown = 1;
	  } 
	  else {
	      // Decrease the yards needed for a first down
	      yardsToFirstDown -= yards;
	      // Increment the current down
	      currentDown += 1;
	  }
	 
	  if (currentDown > 4) {
		location = FIELD_LENGTH - location;
		changeOffense(); 
		currentDown = 1;
		yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
	  }
}

	  
 
  /** 
   *  Passes the ball a specified number of yards, updates the ball location, and handles interceptions.
   * @param yards
   * @param interceptions
   */
  public void pass(int yards, boolean interceptions) { 
	  
	  if(interceptions) { 
		  location += yards;
		  changeOffense();
		  location = FIELD_LENGTH - location;
		  nonNegLocation();
	  }
	  else{
		  moveBall(yards);

		  if (yards >= yardsToFirstDown) {
			yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
	      	currentDown = 1;
		  } 
		  else {
	      // Decrease the yards needed for a first down
			  yardsToFirstDown -= yards;
	      // Increment the current downs
	      	currentDown += 1;
		  }
	 
		  if (currentDown > 4) {
			  location = FIELD_LENGTH - location;
			  changeOffense(); 
			  currentDown = 1;
			  yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
		  }
	  }
  }
  /**
   * Moves the ball by the specified number of yards and handles downs and touchdowns
   * @param yards
   */
  private void moveBall(int yards) { 
	  location += yards;
	  nonNegLocation();
	  if (location > FIELD_LENGTH) {
		  handleTouchdown();
	  }
  }
  /** 
   * Updates the score if a touchdown occurs
   */
  private void handleTouchdown() { 
	  score[offenseTeam] += TOUCHDOWN_POINTS;
  }
  /**
   * Switches the offense from one team to the other
   */
  private void changeOffense() { 
	  offenseTeam = 1 - offenseTeam;
  }
  /**
   * sets the location to 0 if it a negative number
   */
  private void nonNegLocation() { 
	  if (location < 0) {
		  location = 0;
	  }
  }
}
