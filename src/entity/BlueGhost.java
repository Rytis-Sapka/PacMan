package entity;

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
		
		this.x = tileSize * strtX;
		this.y = tileSize * strtY;
		
		try {
			
			up2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueUpLe.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueLeftHi.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueDownLe.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueRightHi.png"));
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueUpRi.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueLeftLo.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueDownRi.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/BlueRightLo.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		getDestination();
		target = bfs.find(x / tileSize, y / tileSize, toX, toY, direction);
		getDirection();
	}
	
	void getDestination() {
		
		toX = p.x / tileSize - (rg.x / tileSize - p.x / tileSize);
		toY = p.y / tileSize - (rg.y / tileSize - p.y / tileSize);
		
		if(toX < 0)
			toX = 0;
		if(toX >= colNum)
			toX = colNum - 1;
		if(toY < 0)
			toY = 0;
		if(toY >= rowNum)
			toY = rowNum - 1;
		
		while(tm.mapTiles[toY][toX] <= 16) {
			
			if(Math.abs(toY - p.y / tileSize) > Math.abs(toX - p.x / tileSize)) {
				if(toY - p.y / tileSize > 0)
					toY--;
				else
					toY++;
			}
			else {
				if(toX - p.x / tileSize > 0)
					toX--;
				else
					toX++;
			}
		}
		
	}
	
	public void update() {
		
		if(x == target.x * tileSize && y == target.y * tileSize) {
			if(inChase) {
				getDestination();
				target = bfs.find(x / tileSize, y / tileSize, toX, toY, direction);
			}
			else
				target = bfs.find(x / tileSize, y / tileSize, 18, 19, direction);
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
		
		loop();
		
		spriteCounter++;
		
		if(spriteCounter > 20) {
			spriteNum = (spriteNum == 1) ? 2 : 1;
			spriteCounter = 0;
		}
	}
}
