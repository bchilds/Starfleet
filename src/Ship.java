/**
 * Created by BChilds on 1/27/17.
 */
public class Ship {
    //static? Contains an X and a Y coordinate. Z unnecessary, since mines contain only relevant Z coordinates
    private static int[] coords;

    static void setCoords(int[] xy){
        coords = xy;
    }

    static int[] getCoords(){
        return coords;
    }
}
