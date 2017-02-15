package test.starfleet;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import starfleet.Ship;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class ShipTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/**
*
* Method: setCoords(int[] xy)
*
*/
@Test
public void testSetCoords() throws Exception {
    int[] testIntArray = new int[] {1,1};
    Ship.setCoords(testIntArray);
    assertEquals(true, Arrays.equals(testIntArray,Ship.getCoords() ) );
} 

/** 
* 
* Method: centerShip(int[] dims) 
* 
*/ 
@Test
public void testCenterShip() throws Exception { 
    int[] newDims = new int[] {5,6};

    //centerShip should adjust this to be 5,7 and then center at 2,3
    assertEquals(true,Arrays.equals(new int[]{5,7},Ship.centerShip(newDims) ) );
    assertEquals(true,Arrays.equals(new int[] {2,3}, Ship.getCoords() ) );
} 

/** 
* 
* Method: getCoords() 
* 
*/ 
@Test
public void testGetCoords() throws Exception { 
    Ship.setCoords(new int[] {2,7});
    assertEquals(2,Ship.getCoords()[0]);
    assertEquals(7,Ship.getCoords()[1]);
} 


} 
