package entity;

import algorithms.BFS;
import algorithms.node;
import main.GamePanel;
import tile.TileManager;

public class Ghost extends Entity {
	
	node target;
	static GamePanel gp;
	static TileManager tm;
	static BFS bfs;
	static Player p;
	static int frameCount = 0;
	static int chaseLen = 20;
	static int scatterLen = 5;
	static boolean inChase = false;
	
	public Ghost(GamePanel gp, TileManager tm, Player p) {
		
		Ghost.p = p;
		Ghost.gp = gp;
		Ghost.tm = tm;
		bfs = new BFS(gp, tm, p);
		speed = 2;
	}
	
	void getDirection() {
		
		if(target.x * gp.tileSize == x) {
			if(target.y * gp.tileSize > y)
				direction = "down";
			else
				direction = "up";
		}
		else {
			if(target.x * gp.tileSize > x)
				direction = "right";
			else
				direction = "left";
		}
	}
	
	//only called by red ghost
	void update() {
		
		frameCount++;
		if(inChase) {
			if(frameCount == gp.fps * chaseLen) {
				inChase = false;
				frameCount = 0;
			}
		}
		else {
			if(frameCount == gp.fps * scatterLen) {
				inChase = true;
				frameCount = 0;
			}
		}
	}
}
