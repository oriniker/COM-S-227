package hw4;

import api.ScoreBox;

/**
 * Score box that is satisfied by any Combination.
 * The score is the sum of all die values.
 * 
 * @author Olivia Riniker
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class ChanceScoreBox extends AbstractScoreBox
{
  /**
   * Constructs a ChanceScoreBox with the given display name.
   * @param displayName
   *   name for this score box
   */
  public ChanceScoreBox(String displayName)
  {
    super(displayName);// TODO
  }

  @Override
	public  int getPotentialScore(int[] arr) {
	  int potentialScore = 0; 
	 for( int i = 0; i < arr.length; i++) { 
			 potentialScore += arr[i]; 	 
	 }
	 return potentialScore;
      
  }

}
