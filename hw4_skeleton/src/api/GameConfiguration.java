package api;

/**
 * A GameConfiguration stores some relevant numerical parameters for configuring
 * a dice game such as Maxi Yatzy,  including 
 * the number of dice, the maximum value of the dice, and the 
 * number of rolls allowed in each turn.
 * @author smkautz
 */
public class GameConfiguration
{
  /**
   * Number of dice used in this game.
   */
  private final int numDice;
  
  /**
   * Largest value for die faces.  Die value range from 
   * 1 through <code>maxValue</code>.
   */
  private final int maxValue;
  
  /**
   * Number of rolls allowed in each turn of the game.
   */
  private final int maxRolls;
  
  /**
   * Minimum number of points required to get the upper section
   * bonus.
   */
  private final int upperSectionBonusCutoff;
  
  /**
   * Value of the upper section bonus.
   */
  private final int upperSectionBonus;
  
  /**
   * Indicates whether unused rolls from a turn can be saved for use in future turns.
   */
  private boolean allowSavedRolls;
  
  /**
   * Constructs a game configuration with the given parameters and an empty
   * list of <code>CategoryDescriptor</code> objects.
   * 
   * @param numDice
   *   number of dice to use in this game
   * @param maxValue
   *   maximum value of dice, where values range from 1 through <code>maxValue</code>
   * @param maxRolls
   *   maximum number of rolls allowed per turn
   * @param upperSectionBonus
   *   points added for the upper section bonus
   * @param upperSectionBonusCutoff
   *   minimum points needed to obtain the upper section bonus
   * @param allowSavedRolls
   *   if true, unused rolls can be saved for use in future turns
   */
  public GameConfiguration(int numDice, int maxValue, int maxRolls, int upperSectionBonus, int upperSectionBonusCutoff, boolean allowSavedRolls)
  {
    this.numDice = numDice;
    this.maxValue = maxValue;
    this.maxRolls = maxRolls;
    this.upperSectionBonusCutoff = upperSectionBonusCutoff;
    this.upperSectionBonus = upperSectionBonus;
    this.allowSavedRolls = allowSavedRolls;
  }
    
  /**
   * Returns the number of dice for this configuration.
   * @return
   *   number of dice
   */
  public int getNumDice()
  {
    return numDice;
  }

  /**
   * Returns the maximum value of each die for this configuration.
   * @return
   *   maximum value on the dice
   */
  public int getMaxValue()
  {
    return maxValue;
  }
  
  /**
   * Returns the number of rolls allowed per turn for this configuration.
   * @return
   *   maximum number of rolls per turn
   */
  public int getMaxRolls()
  {
    return maxRolls;
  }
  
  /**
   * Returns the minumum number of upper section points required
   * to get the upper section bonus.
   * @return
   *   minimum points for upper section bonus
   */
  public int getUpperSectionBonusCutoff()
  {
    return upperSectionBonusCutoff;
  }
  
  /**
   * Returns the number of points given for the upper section
   * bonus.
   * @return
   *   number of points for the upper section bonus
   */
  public int getUpperSectionBonus()
  {
    return upperSectionBonus;
  }
  
  /**
   * Determines whether unused rolls can be saved for use in future turns.
   * @return
   *   if true, unused rolls can be saved for future turns
   */
  public boolean allowSavedRolls()
  {
    return allowSavedRolls;
  }
}
