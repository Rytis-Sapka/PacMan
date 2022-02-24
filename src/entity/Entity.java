package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	//position
	public int x, y;
	public int speed;
	
	//sprite
	public BufferedImage up1, up2;
	public BufferedImage left1, left2;
	public BufferedImage down1, down2;
	public BufferedImage right1, right2;
	public String direction;
	
	//animation counter
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
