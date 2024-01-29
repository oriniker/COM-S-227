

import java.util.Random;
import java.util.Scanner;

import api.ScoreBox;
import hw4.MaxiYatzy;

/**
 * Text-based user interface for a dice game such as Yahtzee.
 * @author smkautz
 */
public class TextUIMain
{
  /**
   * Game instance for this UI.
   */
  private MaxiYatzy game;
    
  /**
   * Scanner to read from standard input.
   */
  private Scanner in;
  
  /**
   * Entry point.
   */
  public static void main(String[] args)
  {

      // Add a seed to make your dice throws reproducible for development
      Random rand = new Random();
      
      // Choose a predefined game, or make up your own
      
      //MaxiYatzy g = ExampleGameFactory.createReallyTinyGame(rand);
      //MaxiYatzy g = ExampleGameFactory.createMiniMaxiGame(rand);
      MaxiYatzy g = ExampleGameFactory.createDefault(rand);
      //MaxiYatzy g = ExampleGameFactory.createMaxi(rand);
    
      TextUIMain ui = new TextUIMain(g);
      ui.runGame();

  }

 
  /**
   * Constructs a UI instance for the given game.
   * @param g
   *   game to use in this UI
   */
  public TextUIMain(MaxiYatzy g)
  {
    game = g;
    in = new Scanner(System.in);
  }
  
  /**
   * Run the main loop for an instance of the game.
   */
  public void runGame()
  {
    // welcome
    System.out.println("Welcome to MaxiYatzy");
    System.out.println("------------------------");
    System.out.println();
    
    // main loop
    while (!isGameOver())
    {
      doOneTurn();
    }
    
    // display final results
    System.out.println();
    System.out.println("Final results");
    System.out.println("-------------");
    printCategories(false);
  }
  
  /**
   * Execute the logic for one turn of the game.
   */
  private void doOneTurn()
  {
    game.startTurn();
    printCategories(false);
    
    // Continue rolling dice until all dice are completed
    do
    {
      System.out.println("You have " + game.getRemainingRolls() + " rolls left.");
      doRollDice(game);
      System.out.println();
      printCategories(true);
      System.out.println();
      System.out.println("You rolled   " + game.getCurrentDice().toString());
      System.out.println();
      
      // if there are still available dice, let the player choose
      // which ones to keep
      if (game.getCurrentDice().getAvailableDice().length > 0)
      {
        chooseDice(game);
        System.out.println("You now have " + game.getCurrentDice().toString());
      }
    } while (game.getCurrentDice().getAvailableDice().length > 0);
    
    System.out.println();
    System.out.println("Completed roll: " + game.getCurrentDice().toString());
    
    // finally, player selects which category
    chooseCategory();
  }
  
  /**
   * Prints the score categories for the game; if argument is true,
   * includes potential scores for the current roll of the dice.
   * @param dice
   *   dice group representing current state of the dice, possibly null
   */
  private void printCategories(boolean printPotentialScores)
  {
    
    // predefine a bunch of format strings to line things up in columns
    String format1 = "%2d) %5d %-15s";        // shows possible score
    String format2 = "%2d)   --- %-15s%5d ";  // shows actual score after category name
    String totalFormat1 = "%25s-----\n";      // 25 spaces and then a dashed line for total
    String totalFormat2 = "%25s%5d\n";        // print a total
    String totalFormat3 = "%25s%5d (%1d plus bonus %1d)\n";  // print upper section total

    System.out.println();
    if (!printPotentialScores)
    {
      System.out.println("Current scores:");
    }
    else
    {
      System.out.println("Potential scores for this roll:");
    }
    ScoreBox[] cats = getAllBoxes();
    for (int i = 0; i < cats.length; ++i)
    {
      String name = cats[i].getDisplayName();
      if (cats[i].isFilled())
      {
        int actualScore = cats[i].getScore();
        System.out.printf(format2, i, name, actualScore);
        System.out.println(cats[i].getDice());
      }
      else
      {
        int potentialScore = 0;
        if (printPotentialScores)
        {
          potentialScore = cats[i].getPotentialScore(game.getCurrentDice().getAll());
        }
        System.out.printf(format1, i, potentialScore, name);
        System.out.println();
      }
    }
    System.out.printf(totalFormat1, "");
    if (game.getUpperSection().size() > 0)
    {
      // if applicable, print upper section total
      System.out.printf(totalFormat3, "Upper section total:", 
          game.getUpperSectionTotal() + game.getUpperSectionBonus(),
          game.getUpperSectionTotal(),
          game.getUpperSectionBonus());
    }
    if (game.getLowerSection().size() > 0)
    {
      System.out.printf(totalFormat2, "Lower section total:", game.getLowerSectionTotal());     
    }
    System.out.printf(totalFormat2, "SCORE:", game.getTotalScore());
  }
  
  
  /**
   * Allows a user to select a category in which to score the current
   * roll of the dice.  (Categories are represented in the console
   * window as numbers.)
   * @param dice
   *   current roll of the dice
   */
  private void chooseCategory()
  {
    System.out.print("Select category: ");
    boolean valid = false;
    ScoreBox[] cats = getAllBoxes();
    while (!valid)
    {
      try
      {
        int response = Integer.parseInt(in.nextLine());
        if (response >= 0 && response < cats.length && !cats[response].isFilled())
        {
          cats[response].fill(game.getCurrentDice());
          valid = true;
        }
      }
      catch (NumberFormatException e)
      {
        // fall through
      }
      if (!valid)
      {
        System.out.print("Please enter a valid category number: ");
      }
    }   
  }
  
  /**
   * Allows a user to select which dice should be added to those
   * completed and which should be rolled again.
   */
  private void chooseDice(MaxiYatzy game)
  {
    boolean valid = false;
    while (!valid)
    {
      System.out.println("a) keep all remaining");
      System.out.println("b) reroll all remaining");
      System.out.println("c) select dice to keep");
      System.out.print("Your choice: ");
      String response = in.nextLine().trim().toLowerCase();
      if (response.startsWith("a"))
      {
        game.getCurrentDice().chooseAll();
        valid = true;
      }
      else if (response.startsWith("b"))
      {
         valid = true;
      }
      else if (response.startsWith("c"))
      {
        System.out.print("Enter dice to keep (separated by spaces): ");
        String line = in.nextLine();
        Scanner temp = new Scanner(line);
        while(temp.hasNextInt())
        {
          game.getCurrentDice().choose(temp.nextInt());
        }
        valid = true;
      }
      else
      {
        System.out.println("Please enter a, b, or c");
      }
    }
  }
  
  /**
   * Generates some interactive output and simulates a dice roll.
   * @param game
   */
  private void doRollDice(MaxiYatzy game)
  {
    System.out.print("Press ENTER to roll the dice...");
    in.nextLine();
    
    // generate an unpredictable number of dots to "roll"...
    int[] temp = game.getCurrentDice().getAll();
    int iters = 0;
    for (int value : temp) iters += value;
    iters = Math.max(iters, 20);
    for (int i = 0; i < iters; ++i)
    {
      System.out.print(".");
      try
      {
        Thread.sleep(10);
      }
      catch (InterruptedException ignore)
      {
        
      }
    }
    System.out.println();
    game.rollAvailableDice();
  }
  
  /**
   * Determines whether the current game is over.
   * @return
   *   true if the game is over, false otherwise
   */
  private boolean isGameOver()
  {
    ScoreBox[] cats = getAllBoxes();
    for (ScoreBox cat : cats)
    {
      if (!cat.isFilled())
      {
        return false;
      }
    }
    return true;     
  }
  
  /**
   * Helper method returns an array of all categories in the game.
   * @return
   *   array of scoring categories
   */
  private ScoreBox[] getAllBoxes()
  {
    ScoreBox[] cats = new ScoreBox[game.getUpperSection().size() + game.getLowerSection().size()];
    int index = 0;
    for (ScoreBox cat : game.getUpperSection())
    {
      cats[index++] = cat;
    }
    for (ScoreBox cat : game.getLowerSection())
    {
      cats[index++] = cat;
    }
    return cats;
  }
  

}
