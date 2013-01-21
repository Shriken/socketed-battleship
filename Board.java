public class Board {

    static final int BLANK = -1;
    static final int MISS = -2;
    static final int HIT = -3;

    int[][] tiles;
    int width;

    int shipsAlive;
    Ship[] ships;

    public Board() {
	width = 10;

	tiles = new int[width][width];
	for (int i=0; i<width; i++)
	    for (int j=0; j<width; j++)
		tiles[j][i] = BLANK;

	shipsAlive = 0;
	ships = new Ship[5];
    }

    public boolean addShip(int x, int y, Ship ship) {
	x--;
	y--;

	//if any part of the ship is off the board, fail
	if (x < 0 || width <= x || y < 0 || width <= y)
	    return false;
	if ((ship.orient && width <= x+ship.len-1) || (!ship.orient && width <= y+ship.len-1))
	    return false;
	//if there is intersection with another ship, fail
	for (int i=0; i<ship.len; i++)
	    if (ship.orient) {
		if (tiles[y][x+i] != BLANK) {
		    return false;
		}
	    } else
		if (tiles[y+i][x] != BLANK)
		    return false;
	
	//everything is good, place the ship
	//the number placed corresponds to the ship's index in ships

	for (int i=0; i<ship.len; i++)
	    if (ship.orient)
		tiles[y][x+i] = shipsAlive;
	    else
		tiles[y+i][x] = shipsAlive;
	ships[shipsAlive] = ship;
	shipsAlive++;
	ship.x = x;
	ship.y = y;
	return true;
    }

    public int fire(int x, int y) {
	//fire receives coordinates directly from the user, and must adjust
	//them for use in the array
	x--;
	y--;

	if (x < 0 || width <= x || y < 0 || width <= y)
	    return -2; //
	if (tiles[y][x] == MISS || tiles[y][x] == HIT)
	    return -1; //already shot here
	else if (tiles[y][x] == BLANK) {
	    tiles[y][x] = MISS;
	    return 0; //miss
	} else { //hit
	    Ship ship = ships[tiles[y][x]];
	    tiles[y][x] = HIT;
	    ship.hull[ship.orient ? x-ship.x : y-ship.y] = true;
	    ship.goodHull--;
	    if (ship.goodHull == 0) {
		shipsAlive--;
		if (shipsAlive == 0)
		    return 3; //win
		else
		    return 2; //sunk
	    } else {
		return 1; //just a hit
	    }
	}
    }

    public int get(int x, int y) {
	return tiles[y][x];
    }

    public void set(int x, int y, int val) {
	tiles[y][x] = val;
    }
}