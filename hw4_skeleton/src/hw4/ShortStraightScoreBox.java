package hw4;

import java.util.Arrays;

/**
 * Score box for a short straight.  A Combination
 * with N dice satisfies this category only if it includes
 * N - 1 distinct consecutive values.  For a dice group that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * @author Olivia Riniker
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class ShortStraightScoreBox extends AbstractScoreBox
{
	private int fullPoints;
  /**
   * Constructs a SmallStraightScoreBox with the given display name
   * and score.
   * @param displayName
   *   name of this score box
   * @param points
   *   points awarded for a dice group that satisfies this score box
   */  
  public ShortStraightScoreBox(String displayName, int points)
  {
	  super(displayName);
	  fullPoints = points; 
  }
  
  @Override
  public boolean isSatisfiedBy(int[] arr) {
      // Check if the array represents a short straight
      Arrays.sort(arr);
      int distinctCount = 1;
      
      for (int i = 0; i < arr.length - 1; i++) {
    	  
          if (arr[i] == (arr[i + 1]-1)) {
              distinctCount++;
              if (distinctCount == arr.length - 1) {
                  return true;
              }
              
              
          }
          
      }
      
      return false;
  }

  
  @Override
  public int getPotentialScore(int[] arr) {
	  return isSatisfiedBy(arr) ? fullPoints : 0;
  }
}
