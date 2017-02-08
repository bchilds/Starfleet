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
    //testCommands currently should contain three commands: North, Increment, North
    assertEquals(3,newMap.commandArrayList.size() );
} 

/** 
* 
* Method: printMap() 
* 
*/ 
@Test
public void testPrintMap() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: doAllCommands() 
* 
*/ 
@Test
public void testDoAllCommands() throws Exception { 
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
    testCommandsMap.commandArrayList.add("North");
    testCommandsMap.commandArrayList.add("South");
    //This will go through all commands (North) and do them, printing the map each time.
    testCommandsMap.doAllCommands();
    assertEquals(true,Arrays.equals(new int[] {1,2}, Ship.getCoords() ) ); //should be 1,2
    assertEquals(true,Arrays.equals(new int[] {3,5}, testCommandsMap.mapDimension) ); //should be 3,5 after north

    //now test Increment
    testCommandsMap.commandArrayList = new ArrayList<>();
    testCommandsMap.commandArrayList.add("Increment");
    assertEquals('c',testCommandsMap.mineArrayList.get(0).getChar()); //char should be c
    testCommandsMap.doAllCommands();
    assertEquals('b',testCommandsMap.mineArrayList.get(0).getChar()); //char should be b

    //movement tested, now test firing missiles and mine destruction (same thing)
    testCommandsMap.commandArrayList.add("Alpha");
    testCommandsMap.doAllCommands();
    assertEquals(true,testCommandsMap.mineArrayList.get(1).destroyed);



} 

/** 
* 
* Method: commandDetails(String commIn) 
* 
*/ 
@Test
public void testCommandDetails() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: destroyMines(int[] missileCoords) 
* 
*/ 
@Test
public void testDestroyMines() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: checkMines() 
* 
*/ 
@Test
public void testCheckMines() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: decrementMines() 
* 
*/ 
@Test
public void testDecrementMines() throws Exception { 
//TODO: Test goes here... 
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

} 
