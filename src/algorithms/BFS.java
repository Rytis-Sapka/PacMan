package algorithms;

import java.util.LinkedList;
import java.util.Queue;

import main.GamePanel;
import tile.TileManager;

public class BFS {
	
	GamePanel gp;
	TileManager tm;
	int map[][];
	int visitedMap[][];
	
	public BFS(GamePanel gp, TileManager tm) {
		this.gp = gp;
		this.tm = tm;
		
		map = new int[gp.rowNum][gp.rowNum];
		initializeMap();
	}
	
	void initializeMap() {
		
		for (int i = 0; i < gp.rowNum; i++) {
			for (int j = 0; j < gp.colNum; j++) {
				if (tm.mapTiles[i][j] < 16) map[i][j] = 1;
				else map[i][j] = 0;
			}
		}
	}
	
	public node find(int x, int y, int finx, int finy) {
		
		node start = new node(x, y);
		node end = new node(finx, finy);
		
		visitedMap = new int[gp.rowNum][gp.colNum];
		for (int i = 0; i < gp.rowNum; i++) {
			for (int j = 0; j < gp.colNum; j++) {
				visitedMap[i][j] = map[i][j];
			}
		}
				
		Queue<node> q = new LinkedList<node>();
		q.add(start);
		node curr;
		visitedMap[y][x] = 5;
		
		while(!q.isEmpty()) {
			
			curr = q.remove();
			if(curr.x == end.x && curr.y == end.y)
				break;
			x = curr.x;
			y = curr.y;
			
			if(isValid(x-1, y)) {
				if(!isUsed(x-1, y)) {
					visitedMap[y][x-1] = 6;
					q.add(new node(x-1, y));
				}
			}
			if(isValid(x, y+1)) {
				if(!isUsed(x, y+1)) {
					visitedMap[y+1][x] = 8;
					q.add(new node(x, y+1));
				}
			}
			if(isValid(x+1, y)) {
				if(!isUsed(x+1, y)) {
					visitedMap[y][x+1] = 4;
					q.add(new node(x+1, y));
				}
			}
			if(isValid(x, y-1)) {
				if(!isUsed(x, y-1)) {
					visitedMap[y-1][x] = 2;
					q.add(new node(x, y-1));
				}
			}
		}
		
		curr = end;
		node ret = new node(end.x, end.y);
		int last = -1;
		while(!(curr.x == start.x && curr.y == start.y)) {
			
			if(visitedMap[curr.y][curr.x] == 8)
			{
				if (last != 8) {
					last = 8;
					ret.x = curr.x;
					ret.y = curr.y;
				}
				curr.y--;
			}
			else if(visitedMap[curr.y][curr.x] == 4)
			{
				if (last != 4) {
					last = 4;
					ret.x = curr.x;
					ret.y = curr.y;
				}
				curr.x--;
			}
			else if(visitedMap[curr.y][curr.x] == 2)
			{
				if (last != 2) {
					last = 2;
					ret.x = curr.x;
					ret.y = curr.y;
				}
				curr.y++;
			}
			else if(visitedMap[curr.y][curr.x] == 6)
			{
				if (last != 6) {
					last = 6;
					ret.x = curr.x;
					ret.y = curr.y;
				}
				curr.x++;
			}
			else if(visitedMap[curr.y][curr.x] == 0)
			{
				System.out.println("Something's wrong");
			}
		}
		return ret;
		
	}
	
	boolean isValid(int x, int y) {
		
		if(x < 0 || x >= gp.colNum)
			return false;
		if(y < 0 || y >= gp.rowNum)
			return false;
		return true;
	}
	
	boolean isUsed(int x, int y) {
		
		if(visitedMap[y][x] > 0)
			return true;
		return false;
	}
	
}
