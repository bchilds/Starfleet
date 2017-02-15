package test.starfleet;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import starfleet.Inputs;
import starfleet.Map;
import starfleet.Mine;
import starfleet.Ship;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class MapTest {
    Inputs newInput;
    Map newMap;
@Before
public void before() throws Exception {
    newInput = new Inputs(); //by default has a 3x3 grid
    newMap = new Map(newInput);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getMines(starfleet.Inputs input)
* 
*/ 
@Test
public void testGetMines() throws Exception {
    ArrayList<Mine> testList = newInput.generateMineList(newInput.fields1);

    //check that the mine list is 3 mines long
    assertEquals(3,testList.size());
} 

/** 
* 
* Method: getCommands(starfleet.Inputs input)
* 
*/ 
@Test
public void testGetCommands() throws Exception {
    newMap.getCommands(newInput);
    //testCommands currently should contain four commands: North, Increment, North, Increment
    assertEquals(5,newMap.commandArrayList.size() );
} 

/** 
* 
* Method: printMap() 
* 
*/ 
@Test
public void testPrintMap() throws Exception {
    Map testCommandsMap = new Map(new Inputs() );
    testCommandsMap.printMap(); //expect a 3,3 grid with default mines
    testCommandsMap.mineArrayList.get(0).setCoords(-5,1,'d'); //sets new distances from the ship
    //ship coords at 1,1 from original grid, new mine coords should make grid an 11x3 with center at 5,1
    testCommandsMap.printMap();
    assertEquals(true,Arrays.equals(Ship.getCoords(),new int[] {5,1}));
} 

/** 
* 
* Method: doAllCommands() 
* 
*/ 
@Test
public void testDoAllCommands() throws Exception {  //relies on a bunch of other methods, such as decrement mines and destroy mines
    //create a new map for this test
    Map testCommandsMap = new Map(new Inputs() );

    //validate that there are three mines
    assertEquals(3, testCommandsMap.mineArrayList.size() );

    //validate ship coordinates
    assertEquals(true,Arrays.equals(new int[] {1,1}, Ship.getCoords() ) ); //should be 1,1
    assertEquals(true,Arrays.equals(new int[] {3,3}, testCommandsMap.mapDimension) ); //should be 3,3

    //create a commands list to test
    testCommandsMap.commandArrayList = new ArrayList<>();
    //make the only command be "North".
    testCommandsMap.commandArrayList.add("North");
    testCommandsMap.commandArrayList.add("Increment");

    //This will go through all commands (North) and do them, printing the map each time.
    testCommandsMap.doAllCommands();
    System.out.println("---  North completed");
    System.out.println("");
    assertEquals(true,Arrays.equals(new int[] {1,2}, Ship.getCoords() ) ); //should be 1,2
    assertEquals(true,Arrays.equals(new int[] {3,5}, testCommandsMap.mapDimension) ); //should be 3,5 after north

    //now test Increment
    testCommandsMap = new Map(new Inputs() );
    testCommandsMap.commandArrayList = new ArrayList<>();
    testCommandsMap.commandArrayList.add("Increment");
    assertEquals('c',testCommandsMap.mineArrayList.get(0).getChar()); //char should be c
    testCommandsMap.doAllCommands();
    assertEquals('b',testCommandsMap.mineArrayList.get(0).getChar()); //char should be b
    System.out.println(" ---  Increment Completed");
    System.out.println("");

    //movement tested, now test firing missiles and mine destruction (same thing)
    testCommandsMap = new Map(new Inputs() );  //reset map and mines
    testCommandsMap.commandArrayList = new ArrayList<>();
    testCommandsMap.commandArrayList.add("Alpha");
    testCommandsMap.commandArrayList.add("Increment");
    testCommandsMap.doAllCommands();
    assertEquals(true,testCommandsMap.mineArrayList.get(0).destroyed);

    System.out.println(" ---  Destroyed completed");

    //now test with mines passed
    testCommandsMap = new Map(new Inputs() );  //reset map and mines
    testCommandsMap.commandArrayList = new ArrayList<>();
    testCommandsMap.commandArrayList.add("Increment");
    testCommandsMap.commandArrayList.add("Increment");
    testCommandsMap.doAllCommands();
    assertEquals(false, testCommandsMap.mineArrayList.get(1).checkPassed() );

    System.out.println(" ---  Mine Passed completed");
    System.out.println("");

    //test multiple actions in one step
    testCommandsMap = new Map(new Inputs() );
    testCommandsMap.commandArrayList = new ArrayList<>();
    testCommandsMap.commandArrayList.add("Beta");
    testCommandsMap.commandArrayList.add("North");
    testCommandsMap.commandArrayList.add("Increment");
    testCommandsMap.commandArrayList.add("Beta");
    testCommandsMap.commandArrayList.add("Increment");
    testCommandsMap.doAllCommands();
    assertEquals(2,testCommandsMap.shots);
    assertEquals(1,testCommandsMap.moves);
    assertEquals(true, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(2).destroyed);

} 

/** 
* 
* Method: commandDetails(String commIn) 
* 
*/ 
@Test
//This test needs to test that ALL possible commands input return expected values
public void testCommandDetails() throws Exception {
    Map testCommandsMap = new Map(new Inputs()); //create new map to test on. Default mines are (1,-1), (-1,0) and (0,-1)
    testCommandsMap.commandDetails("North");
    assertEquals(true, Arrays.equals(new int[]{1, 0}, testCommandsMap.mineArrayList.get(0).getCoords()));

    testCommandsMap.commandDetails("South");
    assertEquals(true, Arrays.equals(new int[]{1, -1}, testCommandsMap.mineArrayList.get(0).getCoords()));

    testCommandsMap.commandDetails("East");
    assertEquals(true, Arrays.equals(new int[]{0, -1}, testCommandsMap.mineArrayList.get(0).getCoords()));

    testCommandsMap.commandDetails("West");
    assertEquals(true, Arrays.equals(new int[]{1, -1}, testCommandsMap.mineArrayList.get(0).getCoords()));

    testCommandsMap.commandDetails("Alpha");
    assertEquals(true, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(false, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(false, testCommandsMap.mineArrayList.get(2).destroyed);

    testCommandsMap = new Map(new Inputs()); //create new map to test on. Default mines are (1,-1), (-1,0) and (0,-1)
    testCommandsMap.commandDetails("Beta");
    assertEquals(false, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(2).destroyed);

    testCommandsMap = new Map(new Inputs());
    testCommandsMap.commandDetails("Gamma");
    assertEquals(false, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(false, testCommandsMap.mineArrayList.get(2).destroyed);

    testCommandsMap = new Map(new Inputs());
    testCommandsMap.commandDetails("Delta");
    assertEquals(false, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(false, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(2).destroyed);

    testCommandsMap = new Map(new Inputs());
    assertEquals('c',testCommandsMap.mineArrayList.get(0).getChar());
    testCommandsMap.commandDetails("Increment");
    assertEquals('b',testCommandsMap.mineArrayList.get(0).getChar());
    testCommandsMap.commandDetails("Alpha");
    testCommandsMap.commandDetails("Increment");
    //test to make sure increment does not affect destroyed mines
    assertEquals('b',testCommandsMap.mineArrayList.get(0).getChar());

}
/** 
* 
* Method: destroyMines(int[] missileCoords) 
* 
*/ 
@Test
public void testDestroyMines() throws Exception {
    Map testCommandsMap = new Map(new Inputs());
    //Default mines are (1,-1), (-1,0) and (0,1)
    testCommandsMap.destroyMines(new int[] {1,-1});
    assertEquals(true, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(false, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(false, testCommandsMap.mineArrayList.get(2).destroyed);

    testCommandsMap.destroyMines(new int[] {-1,0});
    assertEquals(true, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(false, testCommandsMap.mineArrayList.get(2).destroyed);

    testCommandsMap.destroyMines(new int[] {0,1});
    assertEquals(true, testCommandsMap.mineArrayList.get(0).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(1).destroyed);
    assertEquals(true, testCommandsMap.mineArrayList.get(2).destroyed);
}
/** 
* 
* Method: checkMines() 
* 
*/ 
@Test
public void testCheckMines() throws Exception {
    Map testCommandsMap = new Map(new Inputs() );
    assertEquals(true,testCommandsMap.checkMines());

    //decrement mine 1 twice, from a z value of 2 to 0
    testCommandsMap.mineArrayList.get(1).decrementZ();
    testCommandsMap.mineArrayList.get(1).decrementZ();

    assertEquals(false,testCommandsMap.checkMines());
}

/** 
* 
* Method: decrementMines() 
* 
*/ 
@Test
public void testDecrementMines() throws Exception {
    //tests decrement of all mines, including transition from A to z
    Map testCommandsMap = new Map(new Inputs() );
    testCommandsMap.mineArrayList.get(2).setCoords(0,1,'A');
    assertEquals('c',testCommandsMap.mineArrayList.get(0).getChar());
    assertEquals('b',testCommandsMap.mineArrayList.get(1).getChar());
    assertEquals('A',testCommandsMap.mineArrayList.get(2).getChar());
    testCommandsMap.decrementMines();
    assertEquals('b',testCommandsMap.mineArrayList.get(0).getChar());
    assertEquals('a',testCommandsMap.mineArrayList.get(1).getChar());
    assertEquals('z',testCommandsMap.mineArrayList.get(2).getChar());
}


/** 
* 
* Method: newMapDimensions() 
* 
*/ 
@Test
public void testNewMapDimensions() throws Exception {
    Inputs testNewMapDimsInput = new Inputs();
    Map testDimsMap = new Map( testNewMapDimsInput );
    //default is a 3,3 grid with ship at 1,1
    assertEquals(true, Arrays.equals(new int[] {3,3}, testDimsMap.newMapDimensions() ) );

    for(Mine mine: testDimsMap.mineArrayList) {
        mine.incrementY();
    }

    //after incrementing mine Y's (moving North), there is a mine which is 2 away from ship, so grid should become a 3,5
    assertEquals(true, Arrays.equals(new int[] {3,5}, testDimsMap.newMapDimensions() ) );
}

/**
 *
 * * Method: scoreFile()
 *
 **/
@Test
public void testScoreFile() throws Exception {
    Inputs testNewMapScore = new Inputs();
    Map testScoreMap = new Map( testNewMapScore );

    //test that initial mines fail, return a score of 0, as some mines are not destroyed
    assertEquals(0,testScoreMap.scoreFile(false ));

    //test that if a mine is passed, it fails
    testScoreMap.mineArrayList.get(1).decrementZ(); //z = 1
    testScoreMap.mineArrayList.get(0).decrementZ(); //z = 0 = fail
    assertEquals(0,testScoreMap.scoreFile( false ));

    testScoreMap = new Map( testNewMapScore ); //reset mines to initial values
    //initial score = 30, test that mines destroyed with no moves = 30
    for (Mine mine:testScoreMap.mineArrayList){
        mine.destroyMine();
    }
    assertEquals(30,testScoreMap.scoreFile(false));

    //now test that 70 shots only reduces score by 5*15=75 for a value of -45, instead of 5*70
    testScoreMap.shots = 70;
    assertEquals(1,testScoreMap.scoreFile(false )); //minimum score of 1

    //now test that 70 moves only reduces score by 2*9 instead of 2*70
    testScoreMap.shots = 0;
    testScoreMap.moves = 70;
    assertEquals(12,testScoreMap.scoreFile(false ));

    //now test normal process - shoot, move, shoot
    testScoreMap.shots = 2;
    testScoreMap.moves = 1;
    assertEquals(18,testScoreMap.scoreFile(false ));
}

} 
