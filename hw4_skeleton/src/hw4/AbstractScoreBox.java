package hw4;

import api.ScoreBox;


/**
 * This is the abtract class that will house the majority of the code to run the scoreboard for each seperate combination. 
 * It implemenets the scorebox interface. 
 * 
 * @author Olivia Riniker
 */
public abstract class AbstractScoreBox implements ScoreBox {

	/**
	 * The total score accumulated in this score box. It represents the points earned for fulfilling the associated criteria.
	 */
	private int score;

	/**
	 * The combination of dice associated with this score box. It holds the values of dice relevant to the scoring category.
	 */
	private Combination dice;

	/**
	 * A boolean indicating whether this score box has been filled or not. When filled, the score is considered final for this category.
	 */
	private boolean filled;

	/**
	 * The display name of the score box. It provides a human-readable identifier for the scoring category.
	 */
	private String displayName;
	// this will be where common instance variable can be placed
	// not all of these methods need to necisarrily be here but theay are all common methods for each combination 
	protected AbstractScoreBox (String givenDisplayName) { 
		score = 0; // this is the constructor instanciate the instance variables here 
		dice = null; // for each of the methods they need to give a combo that allows for it to be valid
		displayName = givenDisplayName;
		filled = false; // using 0 to indicate that it is empty 
	}
	
	@Override
	public boolean isFilled() { 
		return filled;
	}
	
	@Override
	public boolean isSatisfiedBy(int[] arr) { 
		return true; 
	}
	
	@Override
	public Combination getDice() { 
		 return isFilled() ? dice : null;
	}
	
	@Override
	public void fill(Combination dice) { 
		if(dice.isComplete()) { 
			 this.dice = dice; 
			 this.score = getPotentialScore(dice.getAll()); 
			 this.filled = true; 
		} 
		else { 
			throw new IllegalStateException("The given combination is not complete.");
		}
			
	}
	
	
	@Override
	public String getDisplayName() { 
		return displayName;
	}
	
	@Override
	public  int getPotentialScore(int[] arr){
		return 0;
	}
		
	
	@Override
	public int getScore() { 
		return isFilled() ? score : 0;
		}
	}
	

