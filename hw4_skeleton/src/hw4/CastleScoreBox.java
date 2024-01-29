package hw4;

import java.util.Arrays;

/**
 * Score box that is satisfied by a Combination including
 * at least three dice of one value and three of a different value.
 * The score is the sum of all die values.
 * 
 * @author Olivia Riniker 
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class CastleScoreBox extends AbstractScoreBox
{
  /**
   * Constructs a CastleScoreBox with the given display name.
   * @param displayName
   *   name for this score box
   */
  public CastleScoreBox(String displayName)
  {
	  super(displayName);
  }
  
  
  @Override 
	public boolean isSatisfiedBy(int[] arr) { 
  if ( (arr[0] == arr[1])&&(arr[1] == arr[2])&&(arr[3]==arr[4])&&(arr[4] == arr[5])) {
  	return true; 
  }
  return false; 
}

  @Override
	public  int getPotentialScore(int[] arr){
   if(isSatisfiedBy(arr) == true) {

	 return Arrays.stream(arr).sum();
   }
   return 0;
		}}
