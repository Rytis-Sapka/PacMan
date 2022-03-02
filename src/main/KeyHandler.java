package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	//Movement variables
	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;
	public boolean upNext;
	public boolean downNext;
	public boolean leftNext;
	public boolean rightNext;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//movement management
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		// If a button is pressed it queues the movement
		// If nothing is pressed, movement direction is given
		
		if (code == KeyEvent.VK_W) {
			if (downPressed || leftPressed || rightPressed || upPressed) {
				downNext = false;
				leftNext = false;
				rightNext = false;
				upNext = true;
			}
			else {
				downPressed = false;
				leftPressed = false;
				rightPressed = false;
				upPressed = true;
			}
		}
		if (code == KeyEvent.VK_A) {
			if (downPressed || leftPressed || rightPressed || upPressed) {
				downNext = false;
				leftNext = true;
				rightNext = false;
				upNext = false;
			}
			else {
				downPressed = false;
				leftPressed = true;
				rightPressed = false;
				upPressed = false;
			}
		}
		if (code == KeyEvent.VK_S) {
			if (downPressed || leftPressed || rightPressed || upPressed) {
				downNext = true;
				leftNext = false;
				rightNext = false;
				upNext = false;
			}
			else {
				downPressed = true;
				leftPressed = false;
				rightPressed = false;
				upPressed = false;
			}
		}
		if (code == KeyEvent.VK_D) {
			if (downPressed || leftPressed || rightPressed || upPressed) {
				downNext = false;
				leftNext = false;
				rightNext = true;
				upNext = false;
			}
			else {
				downPressed = false;
				leftPressed = false;
				rightPressed = true;
				upPressed = false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
