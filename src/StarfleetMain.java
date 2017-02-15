package starfleet;

import java.util.ArrayList;

/**
 * Created by BChilds on 1/27/17.
 */

/*
/Users/BChilds/Documents/workspace/Starfleet/src/starfleet/mapTest.txt
/Users/BChilds/Documents/workspace/Starfleet/src/starfleet/commandTest.txt
*/
public class StarfleetMain {
    public static void main(String[] args){
        Inputs input = new Inputs(true); //using boolean input to get non-test versions of method
        Map testMap = new Map(input);
        testMap.doAllCommands();
    }
}
