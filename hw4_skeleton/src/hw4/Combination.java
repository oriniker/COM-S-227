  package hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Group of dice for a dice game such as MaxiYatzy in which 
 * multiple rolls per turn are allowed.  The dice are partitioned
 * into "available" dice and "complete" dice.  The available
 * dice can be re-rolled (that is, they can get new values via the
 * <code>update</code> method). The client can select which dice 
 * to move from available to complete.
 * Once all dice are complete, as determined
 * by the <code>isComplete()</code> method, the <code>getAvailable()</code>
 * method always returns an empty array, and the state of the
 * combination can no longer be modified.
 * 
 * @author Olivia Riniker
 */
public class Combination
{  
	/**
	 * Number of dice in this combination
	 */
    private int numDice;

    /**
     * Array to store the values of available dice
     */
    private int[] availableDice;

    /**
     *  Array to store the values of completed dice
     */
    private int[] completedDice;

    /**
     * Flag to indicate whether the combination is complete
     */
    private boolean isComplete;
	
	
	
	
	
	/**
   * Constructs a new Combination in which each die initially has 
   * the (invalid) value zero and all dice are available. In normal usage, 
   * a client would replace the available die values with randomly generated
   * numbers in an appropriate range, to simulate rolling the dice.
   * @param numDice
   *   number of dice in this group
   */
  public Combination(int givenNumDice){
    numDice = givenNumDice;
    availableDice = new int[numDice];
    completedDice = new int[0];
    isComplete = false; 
  }   
  
  /**
   * Constructs a new Combination in which each die initially has 
   * the value given by the <code>initialValues</code> array.
   * All dice are initially available. The number of dice is determined
   * by the size of the given array.
   * <p>
   * This version of the constructor is primarily intended for testing.
   * @param initialValues
   *   initial values for the dice
   */
  public Combination(int[] givenInitialValues){
	  numDice = givenInitialValues.length;
	  availableDice = Arrays.copyOf(givenInitialValues, numDice);
	  completedDice = new int[0];
	  isComplete = false; 
  }  
  
  /**
   * Returns the number of dice in this group.
   * @return
   *   number of dice in this group
   */
  public int getNumDice()
  {
    return numDice;
  }

  /**
   * Selects a die value to be moved from the available dice to the
   * completed dice. Has no effect if the given value is 
   * not among the values in the available dice. 
   * @param value
   *   die value to be moved
   */
  public void choose(int value)
  {
    if(!isComplete) { 
    	for(int i = 0; i < availableDice.length ; i++) { 
    		if(availableDice[i] == value) { 
    			int chosenVal = availableDice[i]; 
    			int[] newAvailableDice = new int[availableDice.length-1];
    			int index = 0; 
    			for(int j = 0; j < availableDice.length; j++) { 
    				if(j != i) { 
    					newAvailableDice[index++] = availableDice[j];
    				}
    			}
    			availableDice = newAvailableDice; 
    			
    			index = Arrays.binarySearch(completedDice, chosenVal);
    			index = index < 0 ? -index - 1: index;
    			int[] newCompleteDice = new int[completedDice.length + 1]; 
    			System.arraycopy(completedDice, 0, newCompleteDice, 0, index);
    			newCompleteDice[index] = value;
    			System.arraycopy(completedDice, index, newCompleteDice, index+1, completedDice.length - index);
    			completedDice = newCompleteDice; 
    			
    			break;
    		}
    	}
    }
	  
	    
  }

  /**
   * Causes all die values be moved from the available dice to the
   * completed dice. After this method is called, <code>getAvailable</code>
   * always returns an empty array, and <code>isComplete</code> returns true.
   */
  public void chooseAll()
  {
	    if(!isComplete) { 
	    	for(int value : availableDice) { 
	    		int index = Arrays.binarySearch(completedDice, value); 
	    		index = index < 0 ? -index-1 : index; 
	    		int[] newCompletedDice = new int[completedDice.length + 1]; 
	    		System.arraycopy(completedDice, 0, newCompletedDice, 0, index);
	    		newCompletedDice[index] = value; 
	    		System.arraycopy(completedDice, index, newCompletedDice, index+1, completedDice.length - index);
	    		completedDice = newCompletedDice;
	    	}
	    	
	    	availableDice = new int[0];
	    	isComplete = true;
	    }
  }
  
  /**
   * Determines whether there are any dice available to be 
   * rolled in this combination.
   * @return
   *   true if there are no available dice, false otherwise
   */
  public boolean isComplete()
  {
    return isComplete;
  }

  /**
   * Returns the values of the dice that are not available
   * to be re-rolled, in ascending order.
   * @return
   *   values of the dice that are not available to be re-rolled
   */
  public int[] getCompletedDice()
  {
    return Arrays.copyOf(completedDice, completedDice.length);
  }
  
  /**
   * Returns the values of the dice that are available to
   * be re-rolled, in ascending order.
   * @return
   *   dice that are available to be re-rolled
   */
  public int[] getAvailableDice()
  {
	  Arrays.sort(availableDice);
	return Arrays.copyOf(availableDice, availableDice.length);
  }
 
  /**
   * Returns all die values in this combination, in ascending order.
   * @return
   *   all die values in this group
   */
  public int[] getAll()
  {
	  ArrayList<Integer> allDice = new ArrayList<>();
	  for( int value : availableDice) { 
		  allDice.add(value);
	  }
	  for( int value : completedDice) { 
		  allDice.add(value);
	  }      
	  allDice.sort(null);

      // Convert ArrayList to array
      int[] result = new int[allDice.size()];
      for (int i = 0; i < result.length; i++) {
          result[i] = allDice.get(i);
      }

      return result;
  }
  
  /**
   * Replaces the available dice with the given values.
   * Throws an IllegalStateException if the length of
   * the given array does not match the number of available
   * dice in this Combination.
   * @param newValues 
   *   array of new die values for available dice
   * @throws IllegalStateException
   *   if the given array has the wrong length
   */
  public void updateAvailableDice(int[] newValues)
  {
	  System.out.println("new values check: " + newValues.length);
	  System.out.println("availble dice check: " + availableDice.length);
	  if(!isComplete) { 
		  if(newValues.length == availableDice.length) { 
			  Arrays.sort(newValues);
			  availableDice = newValues; 
		  }
		  else { 
			  throw new IllegalStateException("Length of the given array does not match the number of available dice.");
		  }
	  }
	  
	}
  
  
  /**
   * Returns a string representation of the die values in
   * this combination, in the form <em>available</em>(<em>complete</em>).
   * (For example, if there are two completed dice with values 2
   * and 4, and there are 3 available dice with values 1, 1, and 6,
   * then the method returns the string
   * <pre>
   * 1 1 6 (2 4)
   * </pre>
   * @return 
   *   string representation of the available and completed dice,
   *   with the completed values in parentheses
   */
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    int[] avail = getAvailableDice();
    int[] completed = getCompletedDice();
    if (avail.length > 0)
    {
      for (int value : avail)
      {
        sb.append(value + " ");
      }
    }
    sb.append("(");
    if (completed.length > 0)
    {
      // use an index so we can add the first one without a leading space
      sb.append(completed[0]);
      for (int i = 1; i < completed.length; ++i)
      {
        sb.append(" " + completed[i]);
      }
    }
    sb.append(")");
    return sb.toString();
  }
  
}
