package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import algorithms.BFS;
import algorithms.node;
import main.GamePanel;
import tile.TileManager;

public class PinkGhost extends Entity {

	node target;
	GamePanel gp;
	TileManager tm;
	BFS bfs;
	Player p;
	
	int toX;
	int toY;
	
	public PinkGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		this.p = p;
		this.gp = gp;
		this.tm = tm;
		bfs = new BFS(gp, tm, p);
		speed = 2;
		
		this.x = gp.tileSize * strtX;
		this.y = gp.tileSize * strtY;
		
		getDestination();
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX / gp.tileSize, toY / gp.tileSize, direction);
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
	
	void getDestination() {
		
		if(p.direction == "up") {
			
			toY = p.y - 2 * gp.tileSize;
			toY = Math.max(toY, 0);
			while(tm.mapTiles[toY / gp.tileSize][p.x / gp.tileSize] <= 16)
				toY += gp.tileSize;
			toX = p.x;
		}
		else if(p.direction == "left") {

			toX = p.x - 2 * gp.tileSize;
			toX = Math.max(toX, 0);
			while(tm.mapTiles[p.y / gp.tileSize][toX / gp.tileSize] <= 16) {
				toX += gp.tileSize;
			}
			toY = p.y;
		}
		else if(p.direction == "down") {

			toY = p.y + 2 * gp.tileSize;
			toY = Math.min(toY, (gp.rowNum - 1) * gp.tileSize);
			while(tm.mapTiles[toY / gp.tileSize][p.x / gp.tileSize] <= 16)
				toY -= gp.tileSize;
			toX = p.x;
		}
		else if(p.direction == "right") {
			
			toX = p.x + 2 * gp.tileSize;
			toX = Math.min(toX, (gp.colNum - 1) * gp.tileSize);
			while(tm.mapTiles[p.y / gp.tileSize][toX / gp.tileSize] <= 16)
				toX -= gp.tileSize;
			toY = p.y;
		}
		else {
			toY = p.y;
			toX = p.x;
		}
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.pink);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
		
	}
	
	public void update() {
		
		if(x == target.x * gp.tileSize && y == target.y * gp.tileSize) {
			getDestination();
			target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX / gp.tileSize, toY / gp.tileSize, direction);
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
