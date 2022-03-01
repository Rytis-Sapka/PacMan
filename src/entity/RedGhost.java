package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import algorithms.BFS;
import algorithms.node;
import main.GamePanel;
import tile.TileManager;

public class RedGhost extends Entity {

	node target;
	GamePanel gp;
	TileManager tm;
	BFS bfs;
	Player p;
	
	public RedGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		this.p = p;
		this.gp = gp;
		this.tm = tm;
		bfs = new BFS(gp, tm, p);
		speed = 2;
		
		this.x = gp.tileSize * strtX;
		this.y = gp.tileSize * strtY;
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize, direction);
		getDirection();
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
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.red);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
		
	}
	
	public void update() {
		
		if(x == target.x * gp.tileSize && y == target.y * gp.tileSize) {
			target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize, direction);
			getDirection();
		}
		
		if(direction == "up") {
			y -= speed;
		}
		if(direction == "down") {
			y += speed;
		}
		if(direction == "left") {
			x -= speed;
		}
		if(direction == "right") {
			x += speed;
		}
	}
}
