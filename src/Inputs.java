package starfleet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by BChilds on 1/27/17.
 */
public class Inputs {
    /*
    starfleet.Inputs will take in two files: a "Field" file which defines the starting field using text characters, and
    a "Script" file which provides a series of text strings equivalent to Enum commands.
    starfleet.Inputs should have a method to convert both of these into ArrayLists of Strings
     */

    //For early testing, define two simple cases for Fields

    public ArrayList<String> fields1 = new ArrayList<>();
    public ArrayList<String> fields2 = new ArrayList<>();
    int[] mapDims = new int[2];

    public Inputs(){
        createFields1();
        createFields2();
    }

    public void createFields1(){
        fields2.add("z");
    }

    public void createFields2(){
        fields1.add("..c..");
        fields1.add("b....");
        fields1.add(".z...");
    }

    public void loadFiles(){

    }

    public ArrayList<Mine> generateMineList(ArrayList<String> list){
        ArrayList<Mine> minesOut = new ArrayList<>();
        ArrayList<Integer> xes = new ArrayList<>();
        ArrayList<Integer> yes = new ArrayList<>();

        //Iterate through all the list of strings handed to us from Init. i = Y coord
        for(int i = 0; i<list.size(); i++){
            //logic to create a mine from any characters that are not '.'
            String curString = list.get(i);

            //Iterate through each character in this line. j = X coord
            for(int j = 0; j<curString.length(); j++){
                if(curString.charAt(j) != '.'){
                    //create a new starfleet.Mine with j as X-coord, i as Y-coord
                    minesOut.add(new Mine(j,i,curString.charAt(j)));
                    xes.add(j);
                    yes.add(i);

                    //debug to see mine coords as created
                    //System.out.println(i + " , "  + j + " , " + curString.charAt(j));
                }
            }
        } //end checking the initial list for mines to create


        int[] dims = new int[2];
        dims[0] = Collections.max(xes) - Collections.min(xes); //gets the X dimension width
        dims[1] = Collections.max(yes) - Collections.min(yes); //gets the Y dimension width

        mapDims = Ship.centerShip(dims);
        //these are the final dimensions of the initial map

        //Now that the ship is at the center coords, we set all mine coords to be relative to ship
        for(int i = 0; i < minesOut.size(); i++){

            Mine thisMine = minesOut.get(i); //now subtract center from mine
            //ex: mine of (1,4) center of (2,2) will be a mine at (-1,2)
            thisMine.setCoords(thisMine.getCoords()[0]-Ship.getCoords()[0],
                    thisMine.getCoords()[1]-Ship.getCoords()[1],thisMine.getChar());
        }

        return minesOut;
    }

    public ArrayList<String> generateCommandList(){
        ArrayList<String> commandList = new ArrayList<>();

        //logic to generate command list from Script file
        commandList.add("North");
        commandList.add("Increment");
        commandList.add("North");



        return commandList;
    }

    //TODO method to interpret an input text file into a command list with appropriate case/increment

}
