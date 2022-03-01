package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import algorithms.BFS;
import algorithms.node;
import main.GamePanel;
import tile.TileManager;

public class BlueGhost extends Entity {

	node target;
	GamePanel gp;
	TileManager tm;
	BFS bfs;
	Player p;
	RedGhost rg;
	
	int toX;
	int toY;
	
	public BlueGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY, RedGhost rg) {
		
		this.p = p;
		this.gp = gp;
		this.tm = tm;
		this.rg = rg;
		bfs = new BFS(gp, tm, p);
		speed = 2;
		
		this.x = gp.tileSize * strtX;
		this.y = gp.tileSize * strtY;
		
		getDestination();
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX, toY, direction);
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
		
		toX = p.x / gp.tileSize - (rg.x / gp.tileSize - p.x / gp.tileSize);
		toY = p.y / gp.tileSize - (rg.y / gp.tileSize - p.y / gp.tileSize);
		
		if(toX < 0)
			toX = 0;
		if(toX >= gp.colNum)
			toX = gp.colNum - 1;
		if(toY < 0)
			toY = 0;
		if(toY >= gp.rowNum)
			toY = gp.rowNum - 1;
		
		while(tm.mapTiles[toY][toX] <= 16) {
			
			if(Math.abs(toY - p.y / gp.tileSize) > Math.abs(toX - p.x / gp.tileSize)) {
				if(toY - p.y / gp.tileSize > 0)
					toY--;
				else
					toY++;
			}
			else {
				if(toX - p.x / gp.tileSize > 0)
					toX--;
				else
					toX++;
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.blue);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
		
	}
	
	public void update() {
		
		if(x == target.x * gp.tileSize && y == target.y * gp.tileSize) {
			getDestination();
			target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX, toY, direction);
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
