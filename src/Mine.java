package starfleet;

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
    public boolean destroyed;

    public Mine(int x, int y, char c){
        setCoords(x, y, c);
        destroyed = false;
    }

    @Override
    public String toString(){
        return "" + c;
    }

    public char getChar(){
        return c;
    }

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

    public void decrementZ(){
        z--;
        if (z > 26){
            c = (char) (z + 38);
        } else {
            c = (char) (z + 96);
        }
    }

    public void incrementX(){
        x++;
    }

    public void decrementX(){
        x--;
    }

    public void incrementY(){
        y++;
    }

    public void decrementY(){
        y--;
    }

    public boolean checkPassed(){
        //false means that the mine has been passed, true means the mine is not yet passed
        if(this.z < 1){
            return false;
        } else {
            return true;
        }
    }

    public int[] getCoords(){
        int[] tempCoords = {x,y};
        return tempCoords;
    }

    public void destroyMine(){
        destroyed = true;
    }
}
