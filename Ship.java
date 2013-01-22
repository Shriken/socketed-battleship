public class Ship {

    String name;
    boolean[] hull; //true = hit
    boolean orient;
    int goodHull; //the number of tiles not broken
    int x,y;
    int len;
    
    public Ship(String name, int len, boolean orient) {
	this.name = name;
	this.len = len;
	this.orient = orient;

	hull = new boolean[len];
	goodHull = len;
    }
}