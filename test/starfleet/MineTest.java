package test.starfleet;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import starfleet.Mine;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class MineTest {

    Mine testMine;
    @Before
    public void before() throws Exception {
        testMine = new Mine(1,1,'c');
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: toString()
    *
    */
    @Test
    public void testToString() throws Exception {
    assertEquals("c",testMine.toString());
    }

    /**
    *
    * Method: getChar()
    *
    */
    @Test
    public void testGetChar() throws Exception {
        testMine.setCoords(2,2,'c');
        assertEquals('c',testMine.getChar());
    }

    /**
    *
    * Method: setCoords(int x, int y, char c)
    *
    */
    @Test
    public void testSetCoords() throws Exception {
        testMine.setCoords(2,2,'f');
        assertEquals('f',testMine.getChar());
        assertEquals(true, Arrays.equals(new int[] {2,2}, testMine.getCoords() ) );
    }

    /**
    *
    * Method: decrementZ()
    *
    */
    @Test
    public void testDecrementZ() throws Exception {
        testMine.setCoords(2,2,'B');
        testMine.decrementZ();
        assertEquals('A',testMine.getChar() );
        testMine.decrementZ();
        assertEquals('z', testMine.getChar() );
    }

    /**
    *
    * Method: incrementX()
    *
    */
    @Test
    public void testIncrementX() throws Exception {
        testMine.setCoords(2,2,'B');
        testMine.incrementX();
        assertEquals(3, testMine.getCoords()[0]);
    }

    /**
    *
    * Method: decrementX()
    *
    */
    @Test
    public void testDecrementX() throws Exception {
        testMine.setCoords(2,2,'B');
        testMine.decrementX();
        assertEquals(1, testMine.getCoords()[0]);
    }

    /**
    *
    * Method: incrementY()
    *
    */
    @Test
    public void testIncrementY() throws Exception {
        testMine.setCoords(2,2,'B');
        testMine.incrementY();
        assertEquals(3, testMine.getCoords()[1]);
    }

    /**
    *
    * Method: decrementY()
    *
    */
    @Test
    public void testDecrementY() throws Exception {
        testMine.setCoords(2,2,'B');
        testMine.decrementY();
        assertEquals(1, testMine.getCoords()[1]);
    }

    /**
    *
    * Method: checkPassed()
    *
    */
    @Test
    public void testCheckPassed() throws Exception {
        //false means that the mine is passed, true means not yet passed
        testMine.setCoords(2,2,'a');
        assertEquals(true, testMine.checkPassed() );
        testMine.decrementZ();
        assertEquals(false, testMine.checkPassed() );
    }

    /**
    *
    * Method: getCoords()
    *
    */
    @Test
    public void testGetCoords() throws Exception {
        //tested as part of testSetCoords()

    }

    /**
    *
    * Method: destroyMine()
    *
    */
    @Test
    public void testDestroyMine() throws Exception {
        assertEquals(false, testMine.destroyed);
        testMine.destroyMine();
        assertEquals(true, testMine.destroyed);
    }


} 
