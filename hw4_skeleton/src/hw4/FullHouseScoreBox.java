package hw4;

import java.util.Arrays;

/**
 * Score box that is satisfied by a Combination including
 * at least three dice of one value and two of a different value.
 * The score is the sum of all die values.
 * 
 * @author Olivia Riniker
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class FullHouseScoreBox extends AbstractScoreBox {

    public FullHouseScoreBox(String displayName) {
        super(displayName);
    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        Arrays.sort(arr);
        boolean threeOfAKind = false;
        int threeMatch = 0 ; 
        boolean twoOfAKind = false;
        
        for (int i = 0; i < arr.length - 2; i++) {
           
        	if (arr[i] == arr[i + 1] && arr[i + 1] == arr[i + 2] && threeOfAKind == false) {
                threeOfAKind = true;
                threeMatch = arr[i];  
            } 
        }
        for(int j = 0; j < arr.length -1; j++) {
            if (arr[j] == arr[j + 1] && (arr[j]!= threeMatch)) {
                twoOfAKind = true;
            }
        }
        return (threeOfAKind && twoOfAKind);
    }

    @Override
    public int getPotentialScore(int[] arr) {
        if (isSatisfiedBy(arr)) {
            return Arrays.stream(arr).sum();
        } else {
            return 0;  
        }
    }
}