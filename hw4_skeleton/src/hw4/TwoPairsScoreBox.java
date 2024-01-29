package hw4;

import java.util.Arrays;

/**
 * Score box that is satisfied by a Combination including
 * at least two dice of one value and two of a different value.
 * The score is the sum of all die values.
 * @author Olivia Riniker
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class TwoPairsScoreBox extends AbstractScoreBox
{
  /**
   * Constructs a TwoPairsScoreBox with the given display name.
   * @param displayName
   *   name for this score box
   */
  public TwoPairsScoreBox(String displayName)
  {
     super(displayName);
  }
  
 @Override
 public boolean isSatisfiedBy(int[] arr) {
	 Arrays.sort(arr);
	 return (arr[0] == arr[1] && arr[2] == arr[3]) || (arr[1] == arr[2] && arr[3] == arr[4]);
  }
 
 @Override
	public  int getPotentialScore(int[] arr){
     if(isSatisfiedBy(arr) == true) {

	 return Arrays.stream(arr).sum();
     }
     return 0;
		}
		
}
