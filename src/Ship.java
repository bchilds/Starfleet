/**
 * Created by BChilds on 1/27/17.
 */
public class Ship {
    //static? Contains an X and a Y coordinate. Z unnecessary, since mines contain only relevant Z coordinates
    private static int[] coords;

    static void setCoords(int[] xy){
        coords = xy;
    }

    //TODO: ship doesn't really exist. Need to use coords differently
    static void moveCoords(int[] move){
        for(int i = 0; i<move.length; i++){
            coords[i] += move[i];
        }
    }

    static void centerShip(int[] dims){

        //center ship. coords always odd
        //if the difference between max/mins is even, add one
        if((dims[1]-dims[0])%2 == 0){
            dims[1]+=1;
        }
        if((dims[3]-dims[2])%2 == 0){
            dims[3]+=1;
        }

        //out[0] = dims[1]-dims[0];
        //out[1] = dims[3]-dims[2];

        Ship.setCoords(new int[] {(int) Math.ceil(dims[0]/2), (int) Math.ceil(dims[1]/2)});
    }

    static int[] getCoords(){
        return coords;
    }
}
