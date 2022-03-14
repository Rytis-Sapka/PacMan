package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import tile.TileManager;

public class RedGhost extends Ghost {

	public RedGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		super(gp, tm, p);
		
		this.x = tileSize * strtX;
		this.y = tileSize * strtY;
		target = bfs.find(x / tileSize, y / tileSize, p.x / tileSize, p.y / tileSize, direction);
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedUpLe.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedLeftHi.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedDownLe.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedRightHi.png"));
			
			up2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedUpRi.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedLeftLo.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedDownRi.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/RedRightLo.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		getDirection();
	}
	
	public void update() {
		
		super.update();
		
		if(x == target.x * tileSize && y == target.y * tileSize) {
			if(inChase)
				target = bfs.find(x / tileSize, y / tileSize, p.x / tileSize, p.y / tileSize, direction);
			else
				target = bfs.find(x / tileSize, y / tileSize, 18, 1, direction);
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
