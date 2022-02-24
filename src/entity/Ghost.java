package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import algorithms.BFS;
import algorithms.node;
import main.GamePanel;
import tile.TileManager;

public class Ghost extends Entity {

	node target;
	GamePanel gp;
	TileManager tm;
	BFS bfs;
	Player p;
	
	public Ghost(GamePanel gp, TileManager tm, Player p) {
		
		this.p = p;
		this.gp = gp;
		this.tm = tm;
		bfs = new BFS(gp, tm);
		speed = 2;
		
		this.x = gp.tileSize * 2;
		this.y = gp.tileSize * 1;
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize);
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(Color.red);
		g.fillRect(x, y, gp.tileSize, gp.tileSize);
		
	}
	
	public void update() {
		
		if(x == target.x * gp.tileSize && y == target.y * gp.tileSize) {
			target = bfs.find(x / gp.tileSize, y / gp.tileSize, p.x / gp.tileSize, p.y / gp.tileSize);
		}
		
		if(target.x * gp.tileSize == x) {
			if(target.y * gp.tileSize > y)
				y += speed;
			else
				y -= speed;
		}
		else {
			if(target.x * gp.tileSize > x)
				x += speed;
			else
				x -= speed;
		}
	}
}
