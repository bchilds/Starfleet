import java.util.ArrayList;

/**
 * Created by BChilds on 1/27/17.
 */
public class Inputs {
    /*
    Inputs will take in two files: a "Field" file which defines the starting field using text characters, and
    a "Script" file which provides a series of text strings equivalent to Enum commands.
    Inputs should have a method to convert both of these into ArrayLists of Strings
     */

    //For early testing, define two simple cases for Fields

    ArrayList<String> fields1 = new ArrayList<>();
    ArrayList<String> fields2 = new ArrayList<>();

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
                    //create a new Mine with j as X-coord, i as Y-coord
                    minesOut.add(new Mine(j,i,curString.charAt(j)));
                    xes.add(j);
                    yes.add(i);

                    //debug to see mine coords as created
                    //System.out.println(i + " , "  + j + " , " + curString.charAt(j));
                }
            }
        } //end checking the initial list for mines to create

        //TODO: Now, generate the ship center based on the initial mine list, then modify the mine list to be relative to ship
        int[] dims = new int[4];
        for(int i = 0; i < minesOut.size(); i++){

        }
        //now we have the dims of the minelist



        return minesOut;
    }

    public ArrayList<String> generateCommandList(){
        ArrayList<String> commandList = new ArrayList<>();

        //logic to generate command list from Script file
        commandList.add("BETA");
        commandList.add("INCREMENT");
        commandList.add("ALPHA");



        return commandList;
    }

    //TODO method to interpret an input text file into a command list with appropriate case/increment

}
