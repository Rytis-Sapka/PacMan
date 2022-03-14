package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
	static int frameCount;
	static int chaseLen = 20;
	static int scatterLen = 5;
	static boolean inChase = false;
	int tileSize;
	int colNum;
	int rowNum;
	
	public Ghost(GamePanel gp, TileManager tm, Player p) {
		
		Ghost.p = p;
		Ghost.gp = gp;
		Ghost.tm = tm;
		
		tileSize = gp.getTileSize();
		colNum = gp.getColNum();
		rowNum = gp.getRowNum();
		
		bfs = new BFS(gp, tm, p);
		speed = 2;
		frameCount = (scatterLen - 2) * gp.fps;
	}
	
	void getDirection() {
		
		if(target.x * tileSize == x) {
			if(target.y * tileSize > y)
				direction = "down";
			else
				direction = "up";
		}
		else {
			if(target.x * tileSize > x)
				direction = "right";
			else
				direction = "left";
		}
		if(bfs.jump == true) {
			bfs.jump = false;
			if(direction == "left") direction = "right";
			else if(direction == "right") direction = "left";
			else if(direction == "up") direction = "down";
			else if(direction == "down") direction = "up";
		}
	}
	
	public void draw(Graphics2D g) {
		
		BufferedImage image = down1;
		
		if (direction == "up") {
			image = (spriteNum == 1) ? up1 : up2;
		}
		if (direction == "left") {
			image = (spriteNum == 1) ? left1 : left2;
		}
		if (direction == "down") {
			image = (spriteNum == 1) ? down1 : down2;
		}
		if (direction == "right") {
			image = (spriteNum == 1) ? right1 : right2;
		}
		
		g.drawImage(image, x, y, tileSize, tileSize, null);
		if (x > (colNum - 1) * tileSize)
			g.drawImage(image, x - colNum * tileSize, y, tileSize, tileSize, null);
		if (y > (rowNum - 1) * tileSize)
			g.drawImage(image, x, y - rowNum * tileSize, tileSize, tileSize, null);
		
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
	
	void loop() {
		
		x %= colNum * tileSize;
		y %= rowNum * tileSize;
		if(x < 0)
			x = colNum * tileSize + x;
		if(y < 0)
			y = rowNum * tileSize + y;
	}
}
