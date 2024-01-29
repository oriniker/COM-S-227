import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import api.GameConfiguration;
import api.ScoreBox;
import hw4.AllMatchScoreBox;
import hw4.CastleScoreBox;
import hw4.ChanceScoreBox;
import hw4.Combination;
import hw4.FullHouseScoreBox;
import hw4.LargeStraightScoreBox;
import hw4.MatchTargetScoreBox;
import hw4.MaxiYatzy;
import hw4.NOfAKindScoreBox;
import hw4.ShortStraightScoreBox;
import hw4.TowerScoreBox;

/**
 * Some examples of simple tests for the Maxi Yatzy classes. 
 * Various parts are commented out; you should uncomment them
 * when you get the relevant classes implemented 
 */
public class SimpleTests
{
  public static void main(String[] args)
  {
    // construct a combination with known values
    Combination c = new Combination(new int[] {1, 3, 2, 5, 2});
    System.out.println(Arrays.toString(c.getAvailableDice()));
    System.out.println("Expected [1, 2, 2, 3, 5]");
    System.out.println(Arrays.toString(c.getCompletedDice()));
    System.out.println("Expected []");
    System.out.println();
    
    // move some from available to complete
    c.choose(3);
    c.choose(2);
    System.out.println(Arrays.toString(c.getAvailableDice()));
    System.out.println("Expected [1, 2, 5]");
    System.out.println(Arrays.toString(c.getCompletedDice()));
    System.out.println("Expected [2, 3]");
    System.out.println(Arrays.toString(c.getAll()));
    System.out.println("Expected [1, 2, 2, 3, 5]");
    System.out.println();    
    
    // should not be complete
    System.out.println(c.isComplete());
    System.out.println("Expected false");
    System.out.println();
    
    // imagine we roll three new values for the available dice
    c.updateAvailableDice(new int[] {6, 4, 2});
    System.out.println(Arrays.toString(c.getAvailableDice()));
    System.out.println("Expected [2, 4, 6]");
    System.out.println(Arrays.toString(c.getCompletedDice()));
    System.out.println("Expected [2, 3]");
    System.out.println(Arrays.toString(c.getAll()));
    System.out.println("Expected [2, 2, 3, 4, 6]");
    System.out.println();
    
    // move all to completed
    c.chooseAll();
    System.out.println(Arrays.toString(c.getAvailableDice()));
    System.out.println("Expected []");
    System.out.println(Arrays.toString(c.getCompletedDice()));
    System.out.println("Expected [2, 2, 3, 4, 6]");
    System.out.println(Arrays.toString(c.getAll()));
    System.out.println("Expected [2, 2, 3, 4, 6]");
    System.out.println();
    
    // should be complete now
    System.out.println(c.isComplete());
    System.out.println("Expected true");
    System.out.println();
    
    // UNCOMMENT THESE SECTIONS WHEN YOU IMPLEMENT MatchTargetScoreBox
    // try a ScoreBox
    ScoreBox sb = new MatchTargetScoreBox("Threes", 3);
    int[] test = {1, 3, 3, 3, 3, 5, 6};
    System.out.println(sb.isSatisfiedBy(test));
    System.out.println("Expected true");
    System.out.println(sb.getPotentialScore(test));
    System.out.println("Expected 12");
    
    System.out.println(sb.isFilled());
    System.out.println("Expected false");
    System.out.println(sb.getScore());
    System.out.println("Expected 0");
    
    // try filling the score box
    c = new Combination(test);
    c.chooseAll();
    sb.fill(c);
    System.out.println(sb.isFilled());
    System.out.println("Expected true");
    System.out.println(sb.getScore());
    System.out.println("Expected 12");
    Combination saved = sb.getDice();
    System.out.println(c == saved);
    System.out.println("Expected true");
    
   
    
    
    // try some game operations
    GameConfiguration config = new GameConfiguration(
        4,  // four dice
        5,  // max value 5
        4,  // 4 rolls per turn
        0,  // upper section bonus
        0,   // upper section bonus cutoff
        true);  // extra rolls are saved for future turns

    MaxiYatzy game = new MaxiYatzy(config, new Random(42));
    // UNCOMMENT THESE LINES WHEN YOU IMPLEMENT ChanceScoreBox
    game.addLowerSectionScoreBox(new ChanceScoreBox("Chance"));
    game.addLowerSectionScoreBox(new MatchTargetScoreBox("Threes", 3));
    ArrayList<ScoreBox> lower = game.getLowerSection();
    for (ScoreBox b : lower)
    {
      System.out.println(b.getDisplayName());
    }
    System.out.println("Expected 'Threes', 'Chance' ");
    
    game.startTurn();
    System.out.println(game.getRemainingRolls());
    System.out.println("Expected 4");
    Combination comb = game.getCurrentDice();
    System.out.println(Arrays.toString(comb.getAvailableDice()));
    System.out.println("Expected [0, 0, 0, 0]");
    game.rollAvailableDice();
    System.out.println(Arrays.toString(comb.getAvailableDice()));
    System.out.println("(Expected four random nonzero values in range 1-5)");
    System.out.println(game.getRemainingRolls());
    System.out.println("Expected 3");
    
    // finish this turn and start another
    comb.chooseAll();
    lower.get(0).fill(comb);
    System.out.println(game.getLowerSectionTotal());
    System.out.println(game.getTotalScore());
    System.out.println("(Expected: sum of dice values above used to fill Chance box)");

    // start new turn
    game.startTurn();
    System.out.println(game.getRemainingRolls());
    System.out.println("Expected 7");

//    System.out.println("Full house");
//    sb = new FullHouseScoreBox("Full House");
//    System.out.println(sb.getPotentialScore(new int[] {1, 2, 3, 4, 5, 5}));
//    System.out.println("Expected 0");
//    System.out.println(sb.getPotentialScore(new int[] {2, 2, 3, 4, 5, 5}));
//    System.out.println("Expected 0");
//    System.out.println(sb.getPotentialScore(new int[] {2, 2, 3, 3, 3, 5}));
//    System.out.println("Expected 18");
//    
//    System.out.println("N of a kind");
//    sb = new NOfAKindScoreBox("3 of a kind", 3);
//    System.out.println(sb.getPotentialScore(new int[] {2, 2, 2, 3, 3, 3, 3, 4}));
//    System.out.println("Expected 9");
//    
//    
//System.out.println("All match");
//  sb = new AllMatchScoreBox("All Match", 15);
//  System.out.println(sb.getPotentialScore(new int[] {1, 2, 3, 4, 5, 6}));
//  System.out.println("Expected 0");
//  
//  sb = new AllMatchScoreBox("All Match", 15);
//  System.out.println(sb.getPotentialScore(new int[] {1, 1, 1, 1, 1, 1}));
//  System.out.println("Expected 15");
//System.out.println("castle");  
//  sb = new CastleScoreBox("Castle");
//  System.out.println(sb.getPotentialScore(new int[] {1, 1, 1, 2, 2, 2}));
//  System.out.println("Expected 9");
//  
//  sb = new CastleScoreBox("Castle");
//  System.out.println(sb.getPotentialScore(new int[] {1, 1, 3, 2, 2, 2}));
//	System.out.println("Expected 0");
System.out.println("short straight");
sb = new ShortStraightScoreBox("Short Straight", 10);
System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 4, 3}));
System.out.println("Expected true");

sb = new ShortStraightScoreBox("Short Straight", 10);
System.out.println(sb.isSatisfiedBy(new int[] {1, 1, 2, 3, 4, 5}));
System.out.println("Expected true");

sb = new ShortStraightScoreBox("Short Straight", 10);
System.out.println(sb.getPotentialScore(new int[] {1, 1, 2, 3, 4, 5}));
System.out.println("Expected 10");

sb = new ShortStraightScoreBox("Short Straight", 10);
System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 4, 6, 6}));//wrong
System.out.println("Expected false");

sb = new ShortStraightScoreBox("Short Straight", 10);
System.out.println(sb.getPotentialScore(new int[] {1, 6, 2, 3, 4, 5})); // not correct yet 
System.out.println("Expected 10");
System.out.println("tower");
 sb = new TowerScoreBox("Tower");
System.out.println(sb.getPotentialScore(new int[] {1, 1, 1, 1, 3, 3, 4, 5, 6})); 
System.out.println("Expected 25");
 
//sb = new TowerScoreBox("Tower");
//System.out.println(sb.isSatisfiedBy(new int[] {4, 2, 5, 7, 12}));
//System.out.println("Expected false");
//System.out.println(sb.getPotentialScore(new int[] {4, 2, 5, 7, 12}));
//System.out.println("Expected 0"); 
    
    sb = new LargeStraightScoreBox("Large Straight", 10);
    System.out.println();
    System.out.println(" Large Straight");
     System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 4, 5, 5}));
    System.out.println("Expected false");
     System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 4, 5, 6}));
    System.out.println("Expected true");
    System.out.println(sb.isSatisfiedBy(new int[] {5, 6, 7, 8, 9, 10, 11, 12}));
    System.out.println("Expected true");
    
    sb = new FullHouseScoreBox("Full House");
    System.out.println();
    System.out.println(" Full House");
     System.out.println(sb.isSatisfiedBy(new int[] {1, 2, 3, 4, 5, 5}));
    System.out.println("Expected false");
    System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 3, 4, 5, 5}));
    System.out.println("Expected false");
    System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 3, 4, 5, 5, 5}));
    System.out.println("Expected true");
    System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 3, 3, 5, 5}));
    System.out.println("Expected false");
    System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 3, 3, 5}));
    System.out.println("Expected true");
    System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 3, 3, 3, 5}));
    System.out.println("Expected true");
    System.out.println(sb.isSatisfiedBy(new int[] {2, 3, 3, 3, 5, 5, 5}));//wrong 
    System.out.println("Expected true");
    System.out.println(sb.isSatisfiedBy(new int[] {3, 3, 3, 4, 6}));
    System.out.println("Expected false");
    System.out.println(sb.isSatisfiedBy(new int[] {2, 2, 2, 2, 3}));
    System.out.println("Expected false");
    System.out.println(sb.isSatisfiedBy(new int[] {3, 4, 4, 4, 6, 6}));
    System.out.println("Expected true");
  
  }
}
