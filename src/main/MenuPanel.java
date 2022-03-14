package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int colNum = 21;
	int rowNum = 21;
	int tileSize = 36;
	
	JButton button;
	
	public MenuPanel() {
		
		this.setPreferredSize(new Dimension(colNum * tileSize, rowNum * tileSize));
		this.setDoubleBuffered(true);
		this.setBackground(new Color(0, 0, 0));
		
		button = new JButton();
		button.addActionListener(this);
		button.setText("Start game");
		this.add(button);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			
			GamePanel gamePanel = new GamePanel();
			Main.window.add(gamePanel);
			Main.window.pack();
		}
	}
	
}
