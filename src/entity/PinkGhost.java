package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import tile.TileManager;

public class PinkGhost extends Ghost {
	
	int toX;
	int toY;
	
	public PinkGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		super(gp, tm, p);
		this.x = gp.tileSize * strtX;
		this.y = gp.tileSize * strtY;
		
		getDestination();
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX / gp.tileSize, toY / gp.tileSize, direction);
		getDirection();
	}
	
	void getDestination() {
		
		if(p.direction == "up") {
			
			toY = p.y - 3 * gp.tileSize;
			toY = Math.max(toY, 0);
			while(tm.mapTiles[toY / gp.tileSize][p.x / gp.tileSize] <= 16)
				toY += gp.tileSize;
			toX = p.x;
		}
		else if(p.direction == "left") {

			toX = p.x - 3 * gp.tileSize;
			toX = Math.max(toX, 0);
			while(tm.mapTiles[p.y / gp.tileSize][toX / gp.tileSize] <= 16) {
				toX += gp.tileSize;
			}
			toY = p.y;
		}
		else if(p.direction == "down") {

			toY = p.y + 3 * gp.tileSize;
			toY = Math.min(toY, (gp.rowNum - 1) * gp.tileSize);
			while(tm.mapTiles[toY / gp.tileSize][p.x / gp.tileSize] <= 16)
				toY -= gp.tileSize;
			toX = p.x;
		}
		else if(p.direction == "right") {
			
			toX = p.x + 3 * gp.tileSize;
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
			if(inChase) {
				getDestination();
				target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX / gp.tileSize, toY / gp.tileSize, direction);
			}
			else {
				target = bfs.find(x / gp.tileSize, y / gp.tileSize, 2, 1, direction);
			}
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
