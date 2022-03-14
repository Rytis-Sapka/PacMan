package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.ColisionHandler;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler kh;
	ColisionHandler ch;
	
	int tileSize;
	int colNum;
	int rowNum;
	
	//initialize variables and set values
	public Player(GamePanel gp, KeyHandler kh, ColisionHandler ch) {
		
		this.gp = gp;
		this.kh = kh;
		this.ch = ch;
		
		tileSize = gp.getTileSize();
		colNum = gp.getColNum();
		rowNum = gp.getRowNum();
		
		x = tileSize * 10;
		y = tileSize * 11;
		speed = 2;
		
		getPlayerImage();
	}
	
	//load images
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/pacClosed.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/pacClosed.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/pacClosed.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/pacClosed.png"));
			
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/pacOpenUp.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/pacOpenLeft.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/pacOpenDown.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/pacOpenRight.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//update player sprite and position
	public void update() {
		
		ch.CheckTurn(this);
		ch.CheckCollision(this);
		
		if (kh.isUpPressed()) {
			direction = "up";
			y -= speed;
			if(y < 0)
				y = rowNum * tileSize + y;
		}
		if (kh.isDownPressed()) {
			direction = "down";
			y += speed;
			y %= colNum * tileSize;
		}
		if (kh.isLeftPressed()) {
			direction = "left";
			x -= speed;
			if(x < 0)
				x = colNum * tileSize + x;
		}
		if (kh.isRightPressed()) {
			direction = "right";
			x += speed;
			x %= colNum * tileSize;
		}
		
		spriteCounter++;
		
		if(spriteCounter > 10) {
			spriteNum = (spriteNum == 1) ? 2 : 1;
			spriteCounter = 0;
		}
	}
	
	//draw player
	public void draw(Graphics2D g2) {
		
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
		
		g2.drawImage(image, x, y, tileSize, tileSize, null);
		if (x > (colNum - 1) * tileSize)
			g2.drawImage(image, x - colNum * tileSize, y, tileSize, tileSize, null);
		if (y > (rowNum - 1) * tileSize)
			g2.drawImage(image, x, y - rowNum * tileSize, tileSize, tileSize, null);
	}
}
