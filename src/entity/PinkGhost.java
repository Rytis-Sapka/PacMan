package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import tile.TileManager;

public class PinkGhost extends Ghost {
	
	int toX;
	int toY;
	
	public PinkGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		super(gp, tm, p);
		
		this.x = tileSize * strtX;
		this.y = tileSize * strtY;
		
		
		try {
			
			up2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkUpLe.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkLeftHi.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkDownLe.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkRightHi.png"));
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkUpRi.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkLeftLo.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkDownRi.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/PinkRightLo.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		getDestination();
		target = bfs.find(x / tileSize, y / tileSize, toX / tileSize, toY / tileSize, direction);
		getDirection();
	}
	
	void getDestination() {
		
		if(p.direction == "up") {
			
			toY = p.y - 3 * tileSize;
			toY = Math.max(toY, 0);
			while(tm.mapTiles[toY / tileSize][p.x / tileSize] <= 16)
				toY += tileSize;
			toX = p.x;
		}
		else if(p.direction == "left") {

			toX = p.x - 3 * tileSize;
			toX = Math.max(toX, 0);
			while(tm.mapTiles[p.y / tileSize][toX / tileSize] <= 16) {
				toX += tileSize;
			}
			toY = p.y;
		}
		else if(p.direction == "down") {

			toY = p.y + 3 * tileSize;
			toY = Math.min(toY, (rowNum - 1) * tileSize);
			while(tm.mapTiles[toY / tileSize][p.x / tileSize] <= 16)
				toY -= tileSize;
			toX = p.x;
		}
		else if(p.direction == "right") {
			
			toX = p.x + 3 * tileSize;
			toX = Math.min(toX, (colNum - 1) * tileSize);
			while(tm.mapTiles[p.y / tileSize][toX / tileSize] <= 16)
				toX -= tileSize;
			toY = p.y;
		}
		else {
			toY = p.y;
			toX = p.x;
		}
	}
	
	public void update() {
		
		if(x == target.x * tileSize && y == target.y * tileSize) {
			if(inChase) {
				getDestination();
				target = bfs.find(x / tileSize, y / tileSize, toX / tileSize, toY / tileSize, direction);
			}
			else {
				target = bfs.find(x / tileSize, y / tileSize, 2, 1, direction);
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
		
		loop();
		
		spriteCounter++;
		
		if(spriteCounter > 20) {
			spriteNum = (spriteNum == 1) ? 2 : 1;
			spriteCounter = 0;
		}
	}
}
