package main;

/**
 * 	File containing information about the panel
 * 	This is where most of the logic is executed
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import PickUp.Small;
import entity.BlueGhost;
import entity.OrangeGhost;
import entity.PinkGhost;
import entity.Player;
import entity.RedGhost;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	// Window dimensions
	private static final long serialVersionUID = 1L;
	public final int tileSize = 36;
	public final int colNum = 21;
	public final int rowNum = 21;
	public final int fps = 60;
	
	//Handlers and managers
	KeyHandler kh = new KeyHandler();
	TileManager tm = new TileManager(this);
	ColisionHandler ch = new ColisionHandler(this, kh, tm);
	Thread gameThread;

	//entities
	Player player = new Player(this, kh, ch);
	RedGhost redGhost = new RedGhost(this, tm, player, 10, 9);
	PinkGhost pinkGhost = new PinkGhost(this, tm, player, 11, 9);
	OrangeGhost orangeGhost = new OrangeGhost(this, tm, player, 9, 9);
	BlueGhost blueGhost = new BlueGhost(this, tm, player, 10, 8, redGhost);
	
	//Items
	Small sm = new Small(this, tm, player);
	
	//Constructor
	public GamePanel() {
		
		//panel settings
		this.setPreferredSize(new Dimension(colNum * tileSize, rowNum * tileSize));
		this.setDoubleBuffered(true);
		this.setBackground(new Color(0, 0, 0));
		this.addKeyListener(kh);
		this.setFocusable(true);
		
		//initialize game loop
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		double timeInterval = 1000000000 / fps;
		double updateTime = System.nanoTime() + timeInterval;
		
		//game loop
		while (gameThread != null) {
			
			//update all game contents
			update();
			repaint();
			
			//wait for new frame
			try {
				double remainingTime  = updateTime - System.nanoTime();
				remainingTime /= 1000000;
				if (remainingTime < 0)
					remainingTime = 0;
				
				Thread.sleep((long)remainingTime);
				updateTime = System.nanoTime() + timeInterval;
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//update entities
	public void update() {
		
		redGhost.update();
		pinkGhost.update();
		orangeGhost.update();
		blueGhost.update();
		player.update();
		sm.update();
		
		if(checkDeath()) {
			gameThread = null;
		}
	}
	
	//paint all
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
        
		tm.draw(g2);
		sm.draw(g2);
		player.draw(g2);
		redGhost.draw(g2);
		pinkGhost.draw(g2);
		orangeGhost.draw(g2);
		blueGhost.draw(g2);
		
		g2.dispose();
	}
	
	boolean checkDeath() {
		
		if(Math.abs(player.x - redGhost.x) < tileSize / 3 && Math.abs(player.y - redGhost.y) < tileSize / 3)
			return true;
		if(Math.abs(player.x - pinkGhost.x) < tileSize / 3 && Math.abs(player.y - pinkGhost.y) < tileSize / 3)
			return true;
		if(Math.abs(player.x - orangeGhost.x) < tileSize / 3 && Math.abs(player.y - orangeGhost.y) < tileSize / 3)
			return true;
		if(Math.abs(player.x - blueGhost.x) < tileSize / 3 && Math.abs(player.y - blueGhost.y) < tileSize / 3)
			return true;
		return false;
	}
}



