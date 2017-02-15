package starfleet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BChilds on 1/27/17.
 */
public class Inputs {
    /*
    starfleet.Inputs will take in two files: a "Field" file which defines the starting field using text characters, and
    a "Script" file which provides a series of text strings equivalent to Enum commands.
    starfleet.Inputs should have a method to convert both of these into ArrayLists of Strings
     */

    //For testing, define two simple cases for Fields

    public List<String> fields1 = new ArrayList<>();
    public List<String> fields2 = new ArrayList<>();
    int[] mapDims = new int[2];

    //input - Command file
    public String inputFile;
    //input - Field file
    public String fieldFile;

    public Inputs(){
        //this constructor is only used for unit testing
        createFields2();
    }

    public Inputs(Boolean b){
        //boolean value irrelevant, only used for loading files
        loadFiles();
    }

    //method for unit testing, creating a map
    public void createFields2(){
        fields1.add("..c..");
        fields1.add("b....");
        fields1.add(".z...");
    }

    //this method loads files entered by the user and converts them to arraylists of strings
    public void loadFiles(){
        List<String> commandsIn = new ArrayList<String>();
        List<String> mapIn = new ArrayList<String>();
        String mapFile;
        String commandFile;
        Charset charset = Charset.forName("ISO-8859-1");

        System.out.println("Enter Map file path: ");
        Scanner scan = new Scanner(System.in);
        mapFile = scan.next();


        System.out.println("Enter Commands file path: ");
        commandFile = scan.next();

        try{
            mapIn = Files.readAllLines(Paths.get(mapFile), charset);
            commandsIn = Files.readAllLines(Paths.get(commandFile), charset);
        } catch (IOException e) {
            System.out.println(e);
        }

        fields1 = mapIn;
        fields2 = commandsIn;

    }

    public ArrayList<Mine> generateMineList(List<String> list){
        //takes in an arraylist of Strings, generates mines
        //the strings are individual lines from the file
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
        if(fields2.size() == 0) {
            ArrayList<String> commandList = new ArrayList<>();
            //logic to generate command list from Script file
            commandList.add("Beta");
            commandList.add("North");
            //commandList.add("Beta");
            commandList.add("Increment");
            commandList.add("Beta");
            //commandList.add("North");
            commandList.add("Increment");
            return commandList;
        } else {
            Boolean match;
            ArrayList<String> commandList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\s"); //looks for the whitespace character in the string
            for (String line : fields2) {
                Matcher matcher = pattern.matcher(line);
                match = matcher.find();
                if (match) { //if there is a whitespace character, find its location and do a substring of before and after it
                    for (int i = 0; i < line.length(); i++) {
                        if (Character.isWhitespace(line.charAt(i))) {
                            commandList.add(line.substring(0, i));
                            commandList.add(line.substring(i + 1, line.length()));
                        }
                    }
                } else if (!match) { //if there is no whitespace, just add the line.
                    commandList.add(line);
                }

                //now add the Increment command after each line
                commandList.add("Increment");
            }

            //clean weird text
            for (int i = 0; i<commandList.size(); i++){
                String line = commandList.get(i);

                if (commandList.get(i).charAt(0) == 'ï') {
                    commandList.set(i, line.substring(3, line.length() ) );
                }
            }
                //ï»¿

            return commandList;
        }
    }

}
