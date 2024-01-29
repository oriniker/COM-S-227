package hw4;

import java.util.Arrays;

/**
 * Score box for all dice the same.  A Combination
 * with N dice satisfies this category only if all N
 * values are the same.  For a Combination that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * 
 * @author Olivia Riniker
 */
// TODO: this class must implement ScoreBox or extend another class that does
public class AllMatchScoreBox extends AbstractScoreBox {

	private int fullPoints; 
    /**
     * Constructs an AllMatchScoreBox with the given display name.
     * @param displayName
     *   name for this score box
     */
    public AllMatchScoreBox(String displayName, int points ) {
        super(displayName);
        fullPoints = points;
    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        // Check if all dice have the same value
        return Arrays.stream(arr).distinct().count() == 1;
    }

    @Override
    public int getPotentialScore(int[] arr) {
        // Check if the combination satisfies the score box
        if (isSatisfiedBy(arr)) {
            // Sum of all die values
            return fullPoints;
        } else {
            return 0;  // Return 0 if the combination does not satisfy the score box
        }
    }
}