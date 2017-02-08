package test.starfleet;

import org.junit.*;
import static org.junit.Assert.*;
import starfleet.*;

import java.util.ArrayList;
import java.util.Arrays;



public class InputsTest {
    ArrayList<String> fields1 = new ArrayList<>();
    ArrayList<String> fields2 = new ArrayList<>();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: createFields1() 
* 
*/ 
@Test
public void testCreateFields1() throws Exception {
    fields1.add("z");
    assertEquals(1,fields1.size());
} 

/** 
* 
* Method: createFields2() 
* 
*/ 
@Test
public void testCreateFields2() throws Exception {
    //contains three mines
    fields2.add("..c..");
    fields2.add("b....");
    fields2.add(".z...");
    assertEquals(3,fields2.size());
} 

/** 
* 
* Method: loadFiles() 
* 
*/ 
@Test
public void testLoadFiles() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: generateMineList(ArrayList<String> list) 
* 
*/ 
@Test
public void testGenerateMineList() throws Exception {
    testCreateFields2();
    /*
    ..c..
    b....
    .z...

    should reduce to

    ..c
    b..
    .z.

    validate by checking coordinates of mines generated, as Ship should be centered on 1,1

    */
    ArrayList<Mine> testList = new Inputs().generateMineList(fields2);

    //check ship coords at 1,1
    assertEquals(true,Arrays.equals(Ship.getCoords(),new int[] {1,1} ) );

    //check mine coords
    assertEquals(true,Arrays.equals(testList.get(0).getCoords(),new int[] {1,-1} ) );
    assertEquals(true,Arrays.equals(testList.get(1).getCoords(),new int[] {-1,0} ) );
    assertEquals(true,Arrays.equals(testList.get(2).getCoords(),new int[] {0,1} ) );
} 

/** 
* 
* Method: generateCommandList() 
* 
*/ 
@Test
public void testGenerateCommandList() throws Exception { 
    ArrayList<String> testCommands = new Inputs().generateCommandList();
    //testCommands currently should contain three commands: North, Increment, North
    assertEquals(3,testCommands.size() );
} 


} 
