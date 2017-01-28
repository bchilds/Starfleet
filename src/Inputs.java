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

    public void CreateFields1(){
        fields1.add("z");
    }

    public void CreateFields2(){
        fields2.add("..c..");
        fields2.add("b....");
        fields2.add(".z...");
    }

    public ArrayList<Mine> GenerateMineList(ArrayList<String> list){
        ArrayList<Mine> minesOut = new ArrayList<>();

        for(String line: list){
            //logic to create a mine from any characters that are not '.'
        }

        return minesOut;
    }

    public ArrayList<String> GenerateCommandList(){
        ArrayList<String> commandList = new ArrayList<>();

        //logic to generate command list from Script file

        return commandList;
    }
}
