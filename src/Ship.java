package starfleet;

/**
 * Created by BChilds on 1/27/17.
 */
public class Ship {
    //static? Contains an X and a Y coordinate. Z unnecessary, since mines contain only relevant Z coordinates
    private static int[] coords;

    public static void setCoords(int[] xy){
        coords = xy;
    }

    public static int[] centerShip(int[] dims){

        //center ship. coords always odd
        //if the difference between max/mins is even, add one
        if(dims[0]%2 == 0){
            dims[0]+=1;
        }
        if(dims[1]%2 == 0){
            dims[1]+=1;
        }

        Ship.setCoords(new int[] {(int) Math.ceil(dims[0]/2), (int) Math.ceil(dims[1]/2)});
        return dims;
    }

    public static int[] getCoords(){
        return coords;
    }
}
