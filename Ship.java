public class Ship {

    boolean[] hull; //true = hit
    boolean orient;
    int goodHull; //the number of tiles not broken
    int x,y;
    int len;
    
    public Ship(int len, boolean orient) {
	this.orient = orient;
	this.len = len;

	hull = new boolean[len];
	goodHull = len;
    }
}