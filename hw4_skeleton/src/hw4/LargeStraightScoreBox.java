package hw4;

import java.util.Arrays;

/**
 * Score box for a large straight.  A Combination
 * with N dice satisfies this category only if it consists of
 * N distinct consecutive values.  For a dice group that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * 
 * @author Olivia Riniker
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class LargeStraightScoreBox extends AbstractScoreBox {
    private int totalPoints; 
	
	/**
     * Constructs a LargeStraightScoreBox with the given display name
     * and score.
     * 
     * @param displayName name of this score box
     * @param points      points awarded for a dice group that satisfies this score box
     */
    public LargeStraightScoreBox(String displayName, int points) {
        super(displayName);
        totalPoints = points;
    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        // Check for a large straight: N distinct consecutive values
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + 1 != arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getPotentialScore(int[] arr) {
        return isSatisfiedBy(arr) ? totalPoints : 0;
    }
}
