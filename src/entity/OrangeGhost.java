package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import tile.TileManager;

public class OrangeGhost extends Ghost {
	
	public OrangeGhost(GamePanel gp, TileManager tm, Player p, int strtX, int strtY) {
		
		super(gp, tm, p);
		
		this.x = tileSize * strtX;
		this.y = tileSize * strtY;
		
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeUpLe.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeLeftHi.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeDownLe.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeRightHi.png"));
			
			up2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeUpRi.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeLeftLo.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeDownRi.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/ghosts/OrangeRightLo.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		target = bfs.find(x / tileSize, y / tileSize, p.x / tileSize, p.y / tileSize, direction);
		getDirection();
	}
	
	public void update() {
		
		if(x == target.x * tileSize && y == target.y * tileSize) {
			if(inChase) {
				if(Math.abs((x-p.x)) + Math.abs(y-p.y) < 8 * tileSize) {
					target = bfs.find(x / tileSize, y / tileSize, 2, 19, direction);
				}
				else {
					target = bfs.find(x / tileSize, y / tileSize, p.x / tileSize, p.y / tileSize, direction);
				}
			}
			else
				target = bfs.find(x / tileSize, y / tileSize, 2, 19, direction);
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
