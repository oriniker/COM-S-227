package hw4;

import java.util.Arrays;

/**
 * Score box for a given number N of matching dice, where N is specified
 * in the constructor.  A Combination satisfies this category only if it has 
 * (at least) N dice that are the same.  For a Combination that satisfies 
 * this category, the score is the sum of the N dice that have the same value.
 * If there are multiple groups of N with the same value, the group with highest value 
 * is used for the score.  For example, if N is 3 and the combination
 * is (2, 2, 2, 5, 5, 5, 5, 6), the score is 15.
 * @author Olivia Riniker
 */
//TODO: this class must implement ScoreBox or extend another class that does
public class NOfAKindScoreBox extends AbstractScoreBox
{
	private int target; 
 
	 public NOfAKindScoreBox(String displayName, int howMany) {
	        super(displayName);
	        target = howMany;
	    }

	    @Override
	    public boolean isSatisfiedBy(int[] arr) {
	        Arrays.sort(arr);
	        int count = 1;
	        for (int i = 0; i < arr.length - 1; i++) {
	            if (arr[i] == arr[i + 1]) {
	                count++;
	                if (count == target) {
	                    return true;
	                }
	            } else {
	                count = 1;
	            }
	        }
	        return false;
	    }

	    @Override
	    public int getPotentialScore(int[] arr) {
	        if (isSatisfiedBy(arr)) {
	            int sum = 0;
	            int count = 0;

	            for (int i = arr.length - 1; i >= 0; i--) {
	                if (i > 0 && arr[i] == arr[i - 1]) {
	                    sum += arr[i];
	                    count++;
	                    if (count == target) {
	                        break;
	                    }
	                }
	            }
	            return sum;
	        } else {
	        	return 0;
	        }
	    }
	}