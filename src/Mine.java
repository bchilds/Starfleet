/**
 * Created by BChilds on 1/27/17.
 */
public class Mine {
    /*
    This class is a mine. Contains X, Y and Character (Z) integers, as well as a boolean for Destroyed.
    If Z becomes 0 or -1 or whatever is the value before 'a' and destroyed is false, becomes a miss and script fails
    Should contain a method to check given coordinates with the mine's own coordinate
     */
    private int x;
    private int y;
    private int z;
    private char c;

    public void setCoords(int x, int y, char c){
        this.x = x;
        this.y = y;
        this.c = c;

        //calculate z from the given C
        if((int) c < 97){
            z = ((int) c) - 38; //sets A to be = 27
        } else {
            z = ((int) c) - 96; //sets a to be = 1
        }
    }

    public void getCoords(){
        //not actually void, gotta figure out the return type
    }
}
