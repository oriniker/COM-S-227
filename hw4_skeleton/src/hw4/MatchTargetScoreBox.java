package hw4;

/**
 * Score box that is based on counting dice that match
 * a particular target value (as specified in the constructor).
 * This category is satisfied by any Combination.  The score
 * is the sum of the dice that match the target.
 * 
 * @author Olivia Riniker 
 */
//TODO: this class must implement ScoreBox or extend another class that does
public  class MatchTargetScoreBox extends AbstractScoreBox
{
	private int targetValue; 
  /**
   * Constructs a MatchTargetScoreBox with the given display name and 
   * target value.
   * @param displayName
   *   name for this score box
   * @param whichValue
   *   target value that must be matched for the dice to count toward the
   *   score
   */
  public MatchTargetScoreBox(String displayName, int whichValue)
  {
    super(displayName);
    targetValue = whichValue;
  }
  

  @Override
  public boolean isSatisfiedBy(int[] arr) {
      // Return true if at least one die has the target value
      for (int value : arr) {
          if (value == targetValue) {
              return true;
          }
      }
      return false;
  }
  
 @Override
	public  int getPotentialScore(int[] arr) {
     if(isSatisfiedBy(arr) == true) {

	 int potentialScore = 0; 
	 for( int i = 0; i < arr.length; i++) { 
		 if (arr[i] == targetValue) { 
			 potentialScore += targetValue; 
		 }
	 }
	 return potentialScore;
     }
     return 0;
	 
 }
}
