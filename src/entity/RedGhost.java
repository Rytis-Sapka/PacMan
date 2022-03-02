package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import tile.TileManager;

public class RedGhost extends Ghost {

	public RedGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		super(gp, tm, p);
		
		this.x = gp.tileSize * strtX;
		this.y = gp.tileSize * strtY;
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize, direction);
		getDirection();
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.red);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
		
	}
	
	public void update() {
		
		super.update();
		
		if(x == target.x * gp.tileSize && y == target.y * gp.tileSize) {
			if(inChase)
				target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize, direction);
			else
				target = bfs.find(x / gp.tileSize, y / gp.tileSize, 18, 1, direction);
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
