package starfleet;

/**
 * Created by BChilds on 1/27/17.
 */
public class StarfleetMain {
    public static void main(String[] args){
        Inputs input = new Inputs();
        Map testMap = new Map(input);
        testMap.printMap();
        testMap.checkMines();
        testMap.doAllCommands();
    }
}
