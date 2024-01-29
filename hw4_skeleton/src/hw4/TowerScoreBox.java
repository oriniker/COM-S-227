package hw4;

import java.util.Arrays;

/**
 * Score box that is satisfied by a Combination including at least
 * four dice of one value and two of a different value
 * The score is the sum of all die values.
 * @author Olivia Riniker
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class TowerScoreBox extends AbstractScoreBox {

    /**
     * Constructs a TowerScoreBox with the given display name.
     * @param displayName
     *   name for this score box
     */
    public TowerScoreBox(String displayName) {
        super(displayName);
    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        // Check if the array contains at least four dice of one value and two of a different value
        Arrays.sort(arr);
        if ((arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3] && arr[4] == arr[5]) ||
                (arr[0] == arr[1] && arr[2] == arr[3] && arr[3] == arr[4] && arr[4] == arr[5])) {
        	return true;
        }
        return false;
    }

    @Override
    public int getPotentialScore(int[] arr) {
        if(isSatisfiedBy(arr) == true) {
        	 return Arrays.stream(arr).sum();
        }
        return 0;
    }
}
