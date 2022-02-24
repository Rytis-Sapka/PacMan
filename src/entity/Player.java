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
	
	//initialize variables and set values
	public Player(GamePanel gp, KeyHandler kh, ColisionHandler ch) {
		
		this.gp = gp;
		this.kh = kh;
		this.ch = ch;
		
		x = gp.tileSize * 10;
		y = gp.tileSize * 11;
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
		//System.out.println(kh.upNext);
		
		if (kh.upPressed) {
			direction = "up";
			y -= speed;
		}
		if (kh.downPressed) {
			direction = "down";
			y += speed;
		}
		if (kh.leftPressed) {
			direction = "left";
			x -= speed;
		}
		if (kh.rightPressed) {
			direction = "right";
			x += speed;
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
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
