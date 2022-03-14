package algorithms;

//	The BFS algorithm to get ghost destination
//	It is mainly the function find, which returns the target tile

//TODO: might get an error where a ghost has nowhere to go.
//in this case it should turn around.
//also, is a ghost can't reach it's location or is already there,
//it should still move to an intersection.

import java.util.LinkedList;
import java.util.Queue;

import entity.Player;
import main.GamePanel;
import tile.TileManager;

public class BFS {
	
	//Managers and handlers
	GamePanel gp;
	TileManager tm;
	Player p;
	
	//dimensions
	int tileSize;
	int colNum;
	int rowNum;
	
	//2d array used for backtracking
	int visitedMap[][];
	
	//check if player crossed border
	public boolean jump = false;
	
	public BFS(GamePanel gp, TileManager tm, Player p) {
		this.gp = gp;
		this.tm = tm;
		this.p = p;
		
		tileSize = gp.getTileSize();
		colNum = gp.getColNum();
		rowNum = gp.getRowNum();
		
		visitedMap = new int[rowNum][rowNum];
		initializeMap();
	}
	
	//creates a clear map for backtracking
	void initializeMap() {
		
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (tm.mapTiles[i][j] < 16) visitedMap[i][j] = 1;
				else visitedMap[i][j] = 0;
			}
		}
	}
	
	//Main function
	public node find(int x, int y, int finx, int finy, String direction) {
		
		node start = new node(x, y);
		node end = new node(finx, finy);
		
		//create empty map
		initializeMap();
		disallowBacktracking(x, y, finx, finy, direction);
		
		//error handling
		if(!isIntersectionOf(x, y, 0))
			allowBacktracking(x, y, direction);
		if(isUsed(finx, finy))
			return panicMode(x, y);
		
		//fill map with direction values
		Queue<node> q = new LinkedList<node>();
		q.add(start);
		node curr = null;
		visitedMap[y][x] = 5;
		fillMap(q, curr, end, x, y);
		
		//get the next position of ghost
		node ret = new node(end.x, end.y);
		curr = end;
		backtrack(curr, start, ret);
		
		//error handling
		notMoving(ret, start);

		return ret;
		
	}
	
	//make the tile behind ghost a wall
	void disallowBacktracking(int x, int y, int finx, int finy, String direction) {
		
		if(direction == "left")
			if(isValid(x+1, y))
				visitedMap[y][x+1] = 1;
		if(direction == "right")
			if(isValid(x-1, y))
				visitedMap[y][x-1] = 1;
		if(direction == "up")
			if(isValid(x, y+1))
				visitedMap[y+1][x] = 1;
		if(direction == "down")
			if(isValid(x, y-1))
				visitedMap[y-1][x] = 1;
	}
	
	//in case a ghost is stuck in a corner
	void allowBacktracking(int x, int y, String direction) {
		if(direction == "left")
			if(isValid(x+1, y))
				visitedMap[y][x+1] = 0;
		if(direction == "right")
			if(isValid(x-1, y))
				visitedMap[y][x-1] = 0;
		if(direction == "up")
			if(isValid(x, y+1))
				visitedMap[y+1][x] = 0;
		if(direction == "down")
			if(isValid(x, y-1))
				visitedMap[y-1][x] = 0;
	}
	
	//fills map with movement directions
	void fillMap(Queue<node> q, node curr, node end, int x, int y) {
		
		//core BFS algorithm
		//8 is up, 6 is right, 4 is left, 2 is down
		boolean setBack = false;
		while(!q.isEmpty()) {
			
			curr = q.remove();
			if(curr.x == end.x && curr.y == end.y)
				break;
			x = curr.x;
			y = curr.y;
			
			if(x == 0) {
				setBack = true;
				x = colNum;
			}
			if(isValid(x-1, y)) {
				if(!isUsed(x-1, y)) {
					visitedMap[y][x-1] = 6;
					q.add(new node(x-1, y));
				}
			}
			if(setBack == true) {
				setBack = false;
				x = 0;
			}
			
			if(isValid(x, (y+1)%rowNum)) {
				if(!isUsed(x,(y+1)%rowNum)) {
					visitedMap[(y+1)%rowNum][x] = 8;
					q.add(new node(x, (y+1)%rowNum));
				}
			}
			if(isValid((x+1)%colNum, y)) {
				if(!isUsed((x+1)%colNum, y)) {
					visitedMap[y][(x+1)%colNum] = 4;
					q.add(new node((x+1)%colNum, y));
				}
			}
			
			if(y == 0) {
				setBack = true;
				y = rowNum;
			}
			if(isValid(x, y-1)) {
				if(!isUsed(x, y-1)) {
					visitedMap[y-1][x] = 2;
					q.add(new node(x, y-1));
				}
			}
			if(setBack == true) {
				setBack = false;
				y = 0;
			}
		}
		
	}
	
	//follows directions back to start
	void backtrack(node curr, node start, node ret) {
		
		//only update position if there is a turn or an intersection
		//while doing this, gradually return to the starting position
		int last = -1;
		while(!(curr.x == start.x && curr.y == start.y)) {
			
			if(visitedMap[curr.y][curr.x] == 8)
			{
				if (last != 8 || isIntersectionOf(curr.x, curr.y, 2)) {
					last = 8;
					ret.x = curr.x;
					ret.y = curr.y;
					jump = false;
				}
				curr.y--;
				if(curr.y < 0) {
					jump = true;
					curr.y = rowNum + curr.y;
				}
			}
			else if(visitedMap[curr.y][curr.x] == 4)
			{
				if (last != 4 || isIntersectionOf(curr.x, curr.y, 2)) {
					last = 4;
					ret.x = curr.x;
					ret.y = curr.y;
					jump = false;
				}
				curr.x--;
				if(curr.x < 0) {
					jump = true;
					curr.x = colNum + curr.x;
				}
			}
			else if(visitedMap[curr.y][curr.x] == 2)
			{
				if (last != 2 || isIntersectionOf(curr.x, curr.y, 2)) {
					last = 2;
					ret.x = curr.x;
					ret.y = curr.y;
					jump = false;
				}
				curr.y++;
				if(curr.y >= rowNum) {
					jump = true;
					curr.y %= rowNum;
				}
			}
			else if(visitedMap[curr.y][curr.x] == 6)
			{
				if (last != 6 || isIntersectionOf(curr.x, curr.y, 2)) {
					last = 6;
					ret.x = curr.x;
					ret.y = curr.y;
					jump = false;
				}
				curr.x++;
				if(curr.x >= colNum) {
					jump = true;
					curr.x %= colNum;
				}
			}
			else if(visitedMap[curr.y][curr.x] == 0)
			{
				printMap();
				System.out.printf("%d %d coordinates are not accessible", curr.x, curr.y);
				System.exit(0);
			}
		}
	}
	
	//in case pacman is already in destination
	void notMoving(node ret, node start) {
		
		if(ret.x == start.x && ret.y == start.y) {
	
			//first check if pacMan and ghost are on the same tile
			if(p.direction == "right") {
				if (isValid(start.x+1, start.y) && visitedMap[start.y][start.x+1] != 1) {
					ret.x++;
					return;
				}
			}
			if(isValid(start.x, start.y+1) && visitedMap[start.y+1][start.x] != 1) {
				ret.y++;
				return;
			}
			//otherwise choose new point
			if(isValid(start.x+1, start.y) && visitedMap[start.y][start.x+1] != 1) {
				ret.x++;
				return;
			}
			if(isValid(start.x-1, start.y) && visitedMap[start.y][start.x-1] != 1) {
				ret.x--;
				return;
			}
			if(isValid(start.x, start.y-1) && visitedMap[start.y-1][start.x] != 1) {
				ret.y--;
				return;
			}
			else {
				System.out.printf("%d %d my man is stuck", ret.x, ret.y);
				System.exit(0);
			}
		}
	}
	
	//for debugging
	void printMap() {
		
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				System.out.printf("%d ", visitedMap[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//check how many path options in tile
	boolean isIntersectionOf(int x, int y, int n) {
		
		int count = 0;
		if(isValid(x-1, y) && visitedMap[y][x-1] != 1)
			count++;
		if(isValid(x+1, y) && visitedMap[y][x+1] != 1)
			count++;
		if(isValid(x, y-1) && visitedMap[y-1][x] != 1)
			count++;
		if(isValid(x, y+1) && visitedMap[y+1][x] != 1)
			count++;
		return (count > n) ? true : false;
	}
	
	//if the target is blocked off
	node panicMode(int x, int y) {
		
		if(isValid(x-1, y) && visitedMap[y][x-1] != 1)
			return new node(x-1, y);
		if(isValid(x+1, y) && visitedMap[y][x+1] != 1)
			return new node(x+1, y);
		if(isValid(x, y-1) && visitedMap[y-1][x] != 1)
			return new node(x, y-1);
		if(isValid(x, y+1) && visitedMap[y+1][x] != 1)
			return new node(x, y+1);
		System.out.println("This is technically impossible");
		return new node(0, 0);
	}
	
	//utility
	boolean isValid(int x, int y) {
		
		if(x < 0 || x >= colNum)
			return false;
		if(y < 0 || y >= rowNum)
			return false;
		return true;
	}
	
	//utility
	boolean isUsed(int x, int y) {
		
		if(visitedMap[y][x] > 0)
			return true;
		return false;
	}
	
}
