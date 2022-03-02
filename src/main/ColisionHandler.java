package main;

import entity.Player;
import tile.TileManager;

public class ColisionHandler {

	//handlers and managers
	GamePanel gp;
	KeyHandler kh;
	TileManager tm;
	
	//Initialize variables
	public ColisionHandler(GamePanel gp, KeyHandler kh, TileManager tm) {
		this.gp = gp;
		this.kh = kh;
		this.tm = tm;
	}
	
	//Stop if wall hit
	public void CheckCollision(Player en) {
		
		int tile;
		
		if (kh.rightPressed) {
			tile = (en.x + gp.tileSize + en.speed - 1) / gp.tileSize;
			if (tm.mapTiles[en.y / gp.tileSize][tile] < 16 || en.y % gp.tileSize != 0) {
				kh.rightPressed = false;
				kh.downNext = false;
				kh.rightNext = false;
				kh.upNext = false;
				kh.leftNext = false;
			}

		}
		else if (kh.upPressed) {
			tile = (en.y - en.speed + 1) / gp.tileSize;
			if (tm.mapTiles[tile][en.x / gp.tileSize] < 16 || en.x % gp.tileSize != 0) {
				kh.upPressed = false;
				kh.downNext = false;
				kh.rightNext = false;
				kh.upNext = false;
				kh.leftNext = false;
			}
		}
		else if (kh.leftPressed) {
			tile = (en.x - en.speed + 1) / gp.tileSize;
			if (tm.mapTiles[en.y / gp.tileSize][tile] < 16 || en.y % gp.tileSize != 0) {
				kh.leftPressed = false;
				kh.downNext = false;
				kh.rightNext = false;
				kh.upNext = false;
				kh.leftNext = false;
			}
		}
		else if (kh.downPressed) {
			tile = (en.y + gp.tileSize + en.speed - 1) / gp.tileSize;
			if (tm.mapTiles[tile][en.x / gp.tileSize] < 16 || en.x % gp.tileSize != 0) {
				kh.downPressed = false;
				kh.downNext = false;
				kh.rightNext = false;
				kh.upNext = false;
				kh.leftNext = false;
			}
		}
	}
	
	//Check if turn is available
	public void CheckTurn(Player en) {
		
		int tile;
		
		if(kh.upNext) {
			
			tile = (en.y - en.speed + 1) / gp.tileSize;
			if (tm.mapTiles[tile][en.x / gp.tileSize] >= 16 && en.x % gp.tileSize == 0) {
				kh.upNext = false;
				kh.upPressed = true;
				kh.leftPressed = false;
				kh.downPressed = false;
				kh.rightPressed = false;
			}	
		}
		if(kh.leftNext) {
			
			tile = (en.x - en.speed + 1) / gp.tileSize;
			if (tm.mapTiles[en.y / gp.tileSize][tile] >= 16 && en.y % gp.tileSize == 0) {
				kh.leftNext = false;
				kh.upPressed = false;
				kh.leftPressed = true;
				kh.downPressed = false;
				kh.rightPressed = false;
			}	
		}
		if(kh.downNext) {
			
			tile = (en.y + gp.tileSize + en.speed - 1) / gp.tileSize;
			if (tm.mapTiles[tile][en.x / gp.tileSize] >= 16 && en.x % gp.tileSize == 0) {
				kh.downNext = false;
				kh.upPressed = false;
				kh.leftPressed = false;
				kh.downPressed = true;
				kh.rightPressed = false;
			}	
		}
		if(kh.rightNext) {
			
			tile = (en.x + gp.tileSize + en.speed - 1) / gp.tileSize;
			if (tm.mapTiles[en.y / gp.tileSize][tile] >= 16 && en.y % gp.tileSize == 0) {
				kh.rightNext = false;
				kh.upPressed = false;
				kh.leftPressed = false;
				kh.downPressed = false;
				kh.rightPressed = true;
			}	
		}
	}
}
