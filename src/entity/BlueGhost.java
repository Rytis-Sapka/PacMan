package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import tile.TileManager;

public class BlueGhost extends Ghost {

	RedGhost rg;
	
	int toX;
	int toY;
	
	public BlueGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY, RedGhost rg) {
		
		super(gp, tm, p);
		this.rg = rg;
		
		this.x = gp.tileSize * strtX;
		this.y = gp.tileSize * strtY;
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueUpLe.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueLeftHi.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueDownLe.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueRightHi.png"));
			
			up2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueUpRi.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueLeftLo.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueDownRi.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueRightLo.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		getDestination();
		target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX, toY, direction);
		getDirection();
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
		
		g.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
	}
	
	public void update() {
		
		if(x == target.x * gp.tileSize && y == target.y * gp.tileSize) {
			if(inChase) {
				getDestination();
				target = bfs.find(x / gp.tileSize, y / gp.tileSize, toX, toY, direction);
			}
			else
				target = bfs.find(x / gp.tileSize, y / gp.tileSize, 18, 19, direction);
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
		
		spriteCounter++;
		
		if(spriteCounter > 20) {
			spriteNum = (spriteNum == 1) ? 2 : 1;
			spriteCounter = 0;
		}
	}
}
