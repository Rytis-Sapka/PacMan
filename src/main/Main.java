package main;

/**
 * 	Main file for the PacMan game
 * 	Author - Rytis Šapka
 */

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		//creating new frame
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Game");
		
		//creating new panel
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		//display contents
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
