package main;

import entity.Player;
import tile.TileManager;

public class ColisionHandler {

	//handlers and managers
	GamePanel gp;
	KeyHandler kh;
	TileManager tm;
	int tileSize;
	int colNum;
	int rowNum;
	
	//Initialize variables
	public ColisionHandler(GamePanel gp, KeyHandler kh, TileManager tm) {
		this.gp = gp;
		this.kh = kh;
		this.tm = tm;
		
		tileSize = gp.getTileSize();
		colNum = gp.getRowNum();
		rowNum = gp.getRowNum();
	}
	
	//Stop if wall hit
	public void CheckCollision(Player en) {
		
		int tile;
		
		if (kh.isRightPressed()) {
			tile = (en.x + tileSize + en.speed - 1) / tileSize;
			tile %= colNum;
			if (tm.mapTiles[en.y / tileSize][tile] < 16 || en.y % tileSize != 0) {
				kh.setRightPressed(false);
				kh.setDownNext(false);
				kh.setRightNext(false);
				kh.setUpNext(false);
				kh.setLeftNext(false);
			}

		}
		else if (kh.isUpPressed()) {
			tile = (en.y - en.speed + 1) / tileSize;
			tile %= rowNum;
			if (tm.mapTiles[tile][en.x / tileSize] < 16 || en.x % tileSize != 0) {
				kh.setUpPressed(false);
				kh.setDownNext(false);
				kh.setRightNext(false);
				kh.setUpNext(false);
				kh.setLeftNext(false);
			}
		}
		else if (kh.isLeftPressed()) {
			tile = (en.x - en.speed + 1) / tileSize;
			tile %= colNum;
			if (tm.mapTiles[en.y / tileSize][tile] < 16 || en.y % tileSize != 0) {
				kh.setLeftPressed(false);
				kh.setDownNext(false);
				kh.setRightNext(false);
				kh.setUpNext(false);
				kh.setLeftNext(false);
			}
		}
		else if (kh.isDownPressed()) {
			tile = (en.y + tileSize + en.speed - 1) / tileSize;
			tile %= rowNum;
			if (tm.mapTiles[tile][en.x / tileSize] < 16 || en.x % tileSize != 0) {
				kh.setDownPressed(false);
				kh.setDownNext(false);
				kh.setRightNext(false);
				kh.setUpNext(false);
				kh.setLeftNext(false);
			}
		}
	}
	
	//Check if turn is available
	public void CheckTurn(Player en) {
		
		int tile;
		
		if(kh.isUpNext()) {
			
			tile = (en.y - en.speed + 1) / tileSize;
			if(tile < 0)
				tile = rowNum + tile;
			if (tm.mapTiles[tile][en.x / tileSize] >= 16 && en.x % tileSize == 0) {
				kh.setUpNext(false);
				kh.setUpPressed(true);
				kh.setLeftPressed(false);
				kh.setDownPressed(false);
				kh.setRightPressed(false);
			}	
		}
		if(kh.isLeftNext()) {
			
			tile = (en.x - en.speed + 1) / tileSize;
			if(tile < 0)
				tile = colNum + tile;
			if (tm.mapTiles[en.y / tileSize][tile] >= 16 && en.y % tileSize == 0) {
				kh.setLeftNext(false);
				kh.setUpPressed(false);
				kh.setLeftPressed(true);
				kh.setDownPressed(false);
				kh.setRightPressed(false);
			}	
		}
		if(kh.isDownNext()) {
			
			tile = (en.y + tileSize + en.speed - 1) / tileSize;
			tile %= rowNum;
			if (tm.mapTiles[tile][en.x / tileSize] >= 16 && en.x % tileSize == 0) {
				kh.setDownNext(false);
				kh.setUpPressed(false);
				kh.setLeftPressed(false);
				kh.setDownPressed(true);
				kh.setRightPressed(false);
			}	
		}
		if(kh.isRightNext()) {
			
			tile = (en.x + tileSize + en.speed - 1) / tileSize;
			tile %= colNum;
			if (tm.mapTiles[en.y / tileSize][tile] >= 16 && en.y % tileSize == 0) {
				kh.setRightNext(false);
				kh.setUpPressed(false);
				kh.setLeftPressed(false);
				kh.setDownPressed(false);
				kh.setRightPressed(true);
			}	
		}
	}
}
