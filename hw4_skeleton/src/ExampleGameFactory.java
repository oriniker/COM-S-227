

import java.util.Random;

import api.GameConfiguration;
import api.ScoreBox;
import hw4.AllMatchScoreBox;
import hw4.CastleScoreBox;
import hw4.ChanceScoreBox;
import hw4.FullHouseScoreBox;
import hw4.LargeStraightScoreBox;
import hw4.MatchTargetScoreBox;
import hw4.MaxiYatzy;
import hw4.NOfAKindScoreBox;
import hw4.ShortStraightScoreBox;
import hw4.TowerScoreBox;
import hw4.TwoPairsScoreBox;

/**
 * Utility methods for creating various examples of Maxi Yatzy
 * games.
 * @author smkautz
 */
public class ExampleGameFactory
{
  /**
   * Creates and returns a very small version of Yahtzee.
   * There is just one score box, which is to roll as many
   * sixes as possible
   * @return
   *   tiny game
   */
  public static MaxiYatzy createReallyTinyGame(Random rand)
  {
    GameConfiguration config = new GameConfiguration(
        5,  // five dice
        6,  // max value 6
        3,  // 3 rolls per turn
        0,  // upper section bonus
        0,   // upper section bonus cutoff
        false  // can't save rolls after turn
        );

    MaxiYatzy game = new MaxiYatzy(config, rand);
    MatchTargetScoreBox box = new MatchTargetScoreBox("Sixes", 6);
    // UNCOMMENT WHEN YOU HAVE IMPLEMENTED MatchTargetScoreBox
    game.addLowerSectionScoreBox(box);
    return game;
  }  
  
  
  /**
   * Creates and returns a short version of Maxi Yatzy. There are 
   * four dice with values 1 through 4.  There are no 
   * upper section categories.
   * @return
   *   small game 
   */
  public static MaxiYatzy createMiniMaxiGame(Random rand)
  {
    ScoreBox[] categories = {
// UNCOMMENT AS YOU GET THESE IMPLEMENTED
       new LargeStraightScoreBox("Large Straight", 30),
        new AllMatchScoreBox("Mini Yatzy", 40),
        new NOfAKindScoreBox("One Pair", 2)
      };
    
    GameConfiguration config = new GameConfiguration(
        4,  // four dice
        4,  // max value 4
        3,  // 3 rolls per turn
        0,  // upper section bonus
        0,   // upper section bonus cutoff
        true // allow unused rolls be saved for future turns
        );

    MaxiYatzy game = new MaxiYatzy(config, rand);
    for (int i = 0; i < categories.length; ++i)
    {
      game.addLowerSectionScoreBox(categories[i]);
    }
    
    return game;
  }  
  
  /**
   * Creates and returns a default based based on more or less standard
   * score boxes for Yahtzee.
   * @return
   *   game based on standard rules
   */
  public static MaxiYatzy createDefault(Random rand)
  {
    String[] upperSectionNames = {
      "Aces",
      "Twos",
      "Threes",
      "Fours",
      "Fives",
      "Sixes",
    };

    ScoreBox[] lowerSectionCategories = {
// UNCOMMENT AS YOU GET THESE IMPLEMENTED 
        new NOfAKindScoreBox("3 of a kind", 3),
        new NOfAKindScoreBox("4 of a kind", 4),
        new FullHouseScoreBox("Full House"),
        new ShortStraightScoreBox("Small Straight", 30),
        new LargeStraightScoreBox("Large Straight", 40),
        new AllMatchScoreBox("Yahtzee", 50),
        new ChanceScoreBox("Chance")
      };

    
    GameConfiguration config = new GameConfiguration(
        5,  // five dice
        6,  // max value 6
        3,  // 3 rolls per turn
        35, // upper section bonus
        63,  // upper section bonus cutoff
        false
        );
    
    MaxiYatzy game = new MaxiYatzy(config, rand);
    
    // Create upper section 
    for (int i = 0; i < 6; ++i)
    {
// UNCOMMENT WHEN YOU HAVE IMPLEMENTED MatchTargetScoreBox
    game.addUpperSectionScoreBox(new MatchTargetScoreBox(upperSectionNames[i], i + 1));
    }
    
    // lower section
    for (int i = 0; i < lowerSectionCategories.length; ++i)
    {
      game.addLowerSectionScoreBox(lowerSectionCategories[i]);
    }
    
    return game;
  }

  /**
   * Creates and returns a default based based on more or less standard
   * score boxes for Maxi Yatzy
   * @return
   *   game based on standard rules
   */
  public static MaxiYatzy createMaxi(Random rand)
  {
    String[] upperSectionNames = {
      "Ones",
      "Twos",
      "Threes",
      "Fours",
      "Fives",
      "Sixes",
    };

    ScoreBox[] lowerSectionCategories = {
// UNCOMMENT AS YOU GET THESE IMPLEMENTED 
        new NOfAKindScoreBox("One Pair", 2),
        new TwoPairsScoreBox("Two Pairs"),
        new NOfAKindScoreBox("Three of a kind", 3),
        new NOfAKindScoreBox("Four of a kind", 4),
        new NOfAKindScoreBox("Five of a kind", 5),
        new FullHouseScoreBox("Full House"),
        new CastleScoreBox("Castle"),
        new TowerScoreBox("Tower"),       
        new ShortStraightScoreBox("Short Straight", 30),
        new LargeStraightScoreBox("Full Straight", 40),
        new AllMatchScoreBox("Maxi Yatzy", 100),
        new ChanceScoreBox("Chance")
      };

    
    GameConfiguration config = new GameConfiguration(
        6,  // six dice
        6,  // max value 6
        3,  // 3 rolls per turn
        35, // upper section bonus
        63,  // upper section bonus cutoff
        true // can save extra rolls to use in later turns
        );
    
    MaxiYatzy game = new MaxiYatzy(config, rand);
    
    // Create upper section 
    for (int i = 0; i < 6; ++i)
    {
// UNCOMMENT WHEN YOU HAVE IMPLEMENTED MatchTargetScoreBox
   game.addUpperSectionScoreBox(new MatchTargetScoreBox(upperSectionNames[i], i + 1));
    }
    
    // lower section
    for (int i = 0; i < lowerSectionCategories.length; ++i)
    {
      game.addLowerSectionScoreBox(lowerSectionCategories[i]);
    }
    
    return game;
  }


}
