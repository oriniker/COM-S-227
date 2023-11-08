

import hw2.Handegg;

/**
 * A few simple test cases for the Handegg class.
 */
public class SimpleTests
{
  public static void main(String[] args)
  {
    // initial state
    Handegg game = new Handegg();
    System.out.println(game.getLocation());
    System.out.println("Expected 30");
    
    System.out.println(game.whoIsOffense());  
    System.out.println("Expected 0");
    System.out.println(game.whichDown());  
    System.out.println("Expected 1");
    System.out.println(game.getScore(0));  
    System.out.println("Expected 0");
    // after a punt
    game.punt(50);
    System.out.println(game.whoIsOffense());  
    System.out.println("Expected 1");
    System.out.println(game.getLocation());
    System.out.println("Expected 20");
    
   

    // successful attempt at a field goal
    game = new Handegg();
    game.fieldGoal(true);
    System.out.println(game.getScore(0));
    System.out.println("Expected 3");
    System.out.println(game.whoIsOffense());      
    System.out.println("Expected 1");
    System.out.println(game.getLocation());
    System.out.println("Expected 30");

    // expected behavior of extraPoint    
    game = new Handegg();
    game.extraPoint(true);
    System.out.println(game.getScore(0));
    System.out.println("Expected 1");
    System.out.println(game.whoIsOffense());      
    System.out.println("Expected 1");
    System.out.println(game.getLocation());
    System.out.println("Expected 30");

    // ball location should change after call to run()   
    game = new Handegg();
    game.run(5); // advance the ball 5 yards
    System.out.println(game.getLocation());
    System.out.println("Expected 35");

    // unsuccessful attempt at a field goal
    game = new Handegg();
    game.run(25); // advance the ball 55 yards
    System.out.println(game.getLocation());
    System.out.println("Expected 55");
    game.fieldGoal(false);
    System.out.println(game.getScore(0));
    System.out.println("Expected 0");
    System.out.println(game.whoIsOffense());      
    System.out.println("Expected 1");
    System.out.println(game.getLocation());
    System.out.println("Expected 45");
    
    // detect a touchdown after run
    game = new Handegg();
    game.run(100); // advance the ball 100 yards
    System.out.println(game.getLocation());
    System.out.println("Expected 130");
    System.out.println(game.getScore(0));
    System.out.println("Expected 6");

    // counting downs after run
    game = new Handegg();
    System.out.println(game.whichDown());     
    System.out.println("Expected 1");
    System.out.println(game.getYardsToFirstDown());
    System.out.println("Expected 10");
    game.run(-4);
    System.out.println(game.whichDown());     
    System.out.println("Expected 2");
    System.out.println(game.getYardsToFirstDown());
    System.out.println("Expected 14");
    game.run(9);
    System.out.println(game.whichDown());
    System.out.println("Expected 3");
    System.out.println(game.getYardsToFirstDown());
    System.out.println("Expected 5");
    game.run(20);
    System.out.println(game.whichDown());
    System.out.println("Expected 1");
    System.out.println(game.getYardsToFirstDown());
    System.out.println("Expected 10");

    // counting downs with turnover
    game = new Handegg();
    System.out.println(game.whichDown());     
    System.out.println("Expected 1");
    System.out.println(game.getYardsToFirstDown());
    System.out.println("Expected 10");
    game.run(1);
    game.run(1);
    game.run(1);
    game.run(1);
    System.out.println(game.whoIsOffense());      
    System.out.println("Expected 1");
    System.out.println(game.whichDown());     
    System.out.println("Expected 1");
    System.out.println(game.getLocation());
    System.out.println("Expected 66");

    // pass with and without interception
    game = new Handegg();
    game.pass(4, false);
    System.out.println(game.whichDown());     
    System.out.println("Expected 2");
    System.out.println(game.getYardsToFirstDown());
    System.out.println("Expected 6");  
    game.pass(21, true);
    System.out.println(game.whoIsOffense());      
    System.out.println("Expected 1");
    System.out.println(game.getLocation());
    System.out.println("Expected 45");
    
    game = new Handegg(); 
    game.pass(20, false); 
    game.pass(20, false); 
    game.pass(20, false);
    game.pass(20, false);
    game.extraPoint(true);
    System.out.println( " Team zero score: " + game.getScore(0) + " ,expected score = 7");
    System.out.println( " Team one score: " + game.getScore(1) + " ,expected score = 0");
    game.run(4);
    game.pass(25, true);
    game.fieldGoal(true);
    System.out.println( " Team zero score: " + game.getScore(0) + " ,expected score = 10");
    System.out.println( " Team one score: " + game.getScore(1) + " ,expected score = 0");
    game.pass(20, false);
    game.pass(20, false); 
    game.pass(20, false);
    game.pass(20, false);
    game.extraPoint(false);
    System.out.println( " Team zero score: " + game.getScore(0) + " ,expected score = 10");
    System.out.println( " Team one score: " + game.getScore(1) + " ,expected score = 6");
    game.pass(8, false);
    game.pass(7, true);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.run(4);
    game.extraPoint(true);
    System.out.println( " Team zero score: " + game.getScore(0) + " ,expected score = 10");
    System.out.println( " Team one score: " + game.getScore(1) + " ,expected score = 13");
    game.pass(75, true);
    System.out.println( " Team zero score: " + game.getScore(0) + " ,expected score = 10");
    System.out.println( " Team one score: " + game.getScore(1) + " ,expected score = 13");
    

  }
}
