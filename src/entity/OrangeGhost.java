package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import tile.TileManager;

public class OrangeGhost extends Ghost {
	
	public OrangeGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		super(gp, tm, p);
		
		this.x = gp.tileSize * strtX;
		this.y = gp.tileSize * strtY;
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize, direction);
		getDirection();
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.orange);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
		
	}
	
	public void update() {
		
		if(x == target.x * gp.tileSize && y == target.y * gp.tileSize) {
			if(inChase) {
				if(Math.abs((x-p.x)) + Math.abs(y-p.y) < 8 * gp.tileSize) {
					target = bfs.find(x / gp.tileSize, y / gp.tileSize, 2, 19, direction);
				}
				else {
					target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize, direction);
				}
			}
			else
				target = bfs.find(x / gp.tileSize, y / gp.tileSize, 2, 19, direction);
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
