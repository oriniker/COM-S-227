package hw4;

import java.util.ArrayList;
import java.util.Random;

import api.GameConfiguration;
import api.ScoreBox;

/**
 * Game state for dice games such as MaxiYatzy.  The game includes a
 * <code>GameConfiguration</code> object along with two lists of 
 * <code>ScoreBox</code> objects, one for the upper section 
 * and one for the lower section. (Note that the only actual distinction
 * between the two sections is that the upper section scores are 
 * added up and checked to see whether they exceed the upper section 
 * bonus cutoff; if so, the upper section bonus is added to the score.)
 * This class is also responsible for
 * maintaining a current Combination (group of dice) and counting the number of 
 * rolls. 
 * <p>
 * Most of the game state is stored in the associated <code>ScoreBox</code>
 * objects, each of which knows its contribution to the overall
 * score, obtained via its <code>getScore</code> method.
 *
 *- My design Choices - 
 *in my class heirchy I chose for MaxiYatzy to sit at the top of the oyramid because it manages the overall
 *game state and the interactions with the scoreboxes. next below it is the combination class which keeps 
 *track of the idce and their state throughts the round and i wanted maxi yatzy to interact with the combination
 *class to roll dice and make choices. Next I chose for base scorebox interface to be implements and althought 
 *I created an abstract class for it which encapsulates all the actual functionality I think the interface it 
 *implements should go before it in the heirchy. Lastly, is all the different scoreboard classes which i choose to 
 *each represent their distinct way of scoring with its unique conditions. Overall i think the way I set up the heirchy
 *would allow for easy modification of extension in the future.Which I belive is the point of interfaces and inheritance. 
 *
 * @author Olivia Riniker
 */
public class MaxiYatzy
{
	/**
     * The current configuration of the MaxiYatzy game, specifying dice and scoring rules.
     */
    private  GameConfiguration configuration;

    /**
     * The random number generator used for rolling dice.
     */
    private  Random random;

    /**
     * The number of rolls remaining in the current turn.
     */
    private int rollsRemaining;

    /**
     * The total number of extra rolls available for future turns.
     */
    private int extraRolls;

    /**
     * The current set of dice in play, represented by the Combination class.
     */
    private  Combination currentDice;
	
    /**
     * The list of score boxes representing the upper section of the game.
     */
    private ArrayList<ScoreBox> upperSection;
    
    /**
     * The list of score boxes representing the lower section of the game.
     */
    private ArrayList<ScoreBox> lowerSection;
   

  /**
   * Constructs a new MaxiYatzy game based on the given configuration. 
   * Initially the upper section and lower section lists are
   * empty.
   * @param config
   *   configuration to use for this game
   * @param rand
   *   random number generator to use for rolling dice
   */
  public MaxiYatzy(GameConfiguration config, Random rand)
  {
    random = rand; 
    configuration = config;
    upperSection = new ArrayList<>();
    lowerSection = new ArrayList<>();
    currentDice = null;
    rollsRemaining = config.getMaxRolls();
    extraRolls = 0;
  }
  
  /**
   * Adds a score box to the lower section of this game.
   * @param box
   *   score box to add
   */
  public void addLowerSectionScoreBox(ScoreBox box)
  {
    lowerSection.add(box);
  }
  
  /**
   * Adds a score box to the upper section of this game.
   * @param box
   *   score box to add
   */  
  public void addUpperSectionScoreBox(ScoreBox box)
  {
    upperSection.add(box);
  }
  
  /**
   * Returns the list of score boxes in the upper section 
   * for this game.
   * @return
   *   list of score boxes in the upper section
   */
  public ArrayList<ScoreBox> getUpperSection()
  {
    return upperSection;
  }
  
  /**
   * Returns the list of score boxes in the lower section
   * for this game.
   * @return
   *   list of score boxes for the lower section
   */
  public ArrayList<ScoreBox> getLowerSection()
  {
    return lowerSection;
  }
  
  /**
   * Starts a new turn by creating a new Combination
   * and updating the available rolls according to this
   * game's configuration.  If there is already a
   * current Combination that is not complete, this
   * method does nothing.
   */
  public void startTurn() {
      if (currentDice == null || currentDice.isComplete()) {
          currentDice = new Combination(configuration.getNumDice());
          rollsRemaining = configuration.getMaxRolls();
          extraRolls += configuration.getMaxRolls();
      }
  }
  
  /**
   * Returns the remaining number of rolls for this turn.
   * The value returned is always zero if the current Combination
   * is complete, even if the configuration allows unused
   * rolls to be saved for future turns.
   * @return
   *   number of rolls
   */
  public int getRemainingRolls()
  {    
	  if(configuration.allowSavedRolls() == true) { 
		  return extraRolls;
	  }
    return rollsRemaining;
  }
  
  /**
   * Rolls the available dice in the current Combination.
   * That is, the available die values are replaced by randomly
   * generated values in the range 1 through the given maximum (according
   * to this game's configuration). If there are no more remaining
   * rolls, all available dice in the current Combination are
   * moved to the completed state.
   */
  public void rollAvailableDice() {
      if (rollsRemaining > 0) {
	            // Get the available dice values from the Combination
	            int[] availableDiceValues = currentDice.getAvailableDice();

	            for (int i = 0; i < availableDiceValues.length; i++) {
	                availableDiceValues[i] = random.nextInt(configuration.getMaxValue()) + 1;
	            }

	            currentDice.updateAvailableDice(availableDiceValues);
	            rollsRemaining--;
	            extraRolls--;

	            if (rollsRemaining == 0) {
	                currentDice.chooseAll();
	            }
	        }
	    }

  
  /**
   * Returns the current Combination (group of dice).  
   * @return
   *   current group of dice
   */
  public Combination getCurrentDice()
  {
    return currentDice;
  }
  
  /**
   * Returns true if the game is over.  The game is considered over
   * when all score boxes are filled.
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver() {
	    for (ScoreBox box : upperSection) {
	        if (!box.isFilled()) {
	            return false;
	        }
	    }

	    for (ScoreBox box : lowerSection) {
	        if (!box.isFilled()) {
	            return false;
	        }
	    }

	    return true;
	}
  /**
   * Returns the total score for the filled upper section score boxes
   * (not including the upper section bonus, if any).
   * @return
   *   upper section total score
   */
  public int getLowerSectionTotal() {
	    int total = 0;
	    for (ScoreBox box : lowerSection) {
	        if (box.isFilled()) {
	            total += box.getScore();
	        }
	    }

	    return total;
	}
  
  /**
   * Returns the total score for the filled lower section score boxes.
   * @return
   *   lower section total score
   */
  public int getUpperSectionTotal() {
	    int total = 0;
	    for (ScoreBox box : upperSection) {
	        if (box.isFilled()) {
	            total += box.getScore();
	        }
	    }

	    return total;
	}
  
  /**
   * Returns the total score for all categories including the upper section
   * bonus, if any.
   * @return
   *   total score for all categories
   */
  public int getTotalScore()
  {
    return getUpperSectionTotal() + getLowerSectionTotal() + getUpperSectionBonus();
  }
  
  /**
   * Returns the upper section bonus if one is currently being applied,
   * otherwise returns zero. 
   * @return
   *    upper section bonus if applicable, otherwise zero
   */
  public int getUpperSectionBonus() {
	    if (getUpperSectionTotal() >= configuration.getUpperSectionBonusCutoff()) {
	        return configuration.getUpperSectionBonus();
	    } else {
	        return 0;
	    }
	}

}
