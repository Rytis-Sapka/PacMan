package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	//Movement variables
	private boolean upPressed;
	private boolean downPressed;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upNext;
	private boolean downNext;
	private boolean leftNext;
	private boolean rightNext;
	
	
	
	
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

	//getters
	public boolean isUpPressed() {
		return upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isUpNext() {
		return upNext;
	}

	public boolean isDownNext() {
		return downNext;
	}

	public boolean isLeftNext() {
		return leftNext;
	}

	public boolean isRightNext() {
		return rightNext;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public void setUpNext(boolean upNext) {
		this.upNext = upNext;
	}

	public void setDownNext(boolean downNext) {
		this.downNext = downNext;
	}

	public void setLeftNext(boolean leftNext) {
		this.leftNext = leftNext;
	}

	public void setRightNext(boolean rightNext) {
		this.rightNext = rightNext;
	}
	
}
