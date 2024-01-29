package api;

import hw4.Combination;

/**
 * Abstract representation of a score box for a dice game
 * such as Maxi Yatzy.  Each score box can determine, given an
 * int array, whether the score box <em>is satisfied by</em> 
 * the dice group, and also what the potential score would be.
 * These two methods do not modify the state of the category.
 * A score box also has a state; that is, it can be 
 * set to a status of <em>filled</em>, at which point it permanently
 * records the combination that was used to satisfy it
 * and can report the score.
 */
public interface ScoreBox
{
  /**
   * Determines whether this score box is filled.
   * @return
   *   true if this score box has a fixed dice group and score, 
   *   false otherwise
   */
  public boolean isFilled();
  
  /**
   * Returns the score for this score box, or zero if the 
   * category is not <em>filled</em>.
   * @return
   *   score for the score box or zero if not filled
   */
  public int getScore();  

  /**
   * Returns the combination used to satisfy this category, or null if
   * not <em>filled</em>.
   * @return
   *   combination satisfying this category, or null if not done
   */
  public Combination getDice(); 
  
  /**
   * Returns the name for this category.
   * @return
   *   name for this category
   */
  public String getDisplayName();
  
  /**
   * Permanently sets the combination being used to satisfy
   * this category.  The score is set to the value of
   * <code>getPotentialScore</code> for this combination's 
   * completed dice.
   * @param dice
   *   combination to be used to satisfy this category
   * @throws IllegalStateException
   *   if the given combination is not complete (according
   *   to the <code>isComplete()</code> method of Combination
   */
  public void fill(Combination dice);
  
  /**
   * Determines whether the given array of die values satisfies
   * this score box.  This method does not modify the state
   * of this score box.
   * @param arr
   *   array to check, in increasing order
   * @return
   *   true if the given values can be used to satisfy the 
   *   score box, false otherwise
   */
  public boolean isSatisfiedBy(int[] arr);
  
  /**
   * Returns the potential score that would result from 
   * using the given array of die values to satisfy this score box.
   * Always returns zero if the <code>isSatisfiedBy()</code> 
   * method returns false for the given array.
   * This method does not modify the state of this score box.
   * @param arr
   *   array to check, in increasing order
   * @return
   *   potential score for the given die values
   */
  public int getPotentialScore(int[] arr);
  
  
}
