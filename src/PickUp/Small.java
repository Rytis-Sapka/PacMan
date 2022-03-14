package PickUp;

import java.awt.Color;
import java.awt.Graphics2D;

import algorithms.Array;
import entity.Player;
import main.GamePanel;
import tile.TileManager;

public class Small {
	
	GamePanel gp;
	TileManager tm;
	Player p;
	
	Array dArr = new Array(1);
	int size;
	int off;
	
	int tileSize;
	int colNum;
	int rowNum;
	
	public Small(GamePanel gp, TileManager tm, Player p) {
		
		this.gp = gp;
		this.tm = tm;
		this.p = p;
		
		tileSize = gp.getTileSize();
		colNum = gp.getColNum();
		rowNum = gp.getRowNum();
		
		size = tileSize / 8;
		off = tileSize * 3 / 8;
		
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (tm.mapTiles[i][j] == 17) {
					Item insertion = new Item();
					insertion.x = j * tileSize;
					insertion.y = i * tileSize;
					dArr.insert(insertion);
					if(isValid(j + 1, i)) {
						if(tm.mapTiles[i][j+1] == 17) {
							Item insertion1 = new Item();
							insertion1.x = j * tileSize + tileSize / 2;
							insertion1.y = i * tileSize;
							dArr.insert(insertion1);
						}
					}
					if(isValid(j, i + 1)) {
						if(tm.mapTiles[i+1][j] == 17) {
							Item insertion1 = new Item();
							insertion1.x = j * tileSize;
							insertion1.y = i * tileSize + tileSize / 2;
							dArr.insert(insertion1);
						}
					}
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setColor(Color.yellow);
		for (int i = 0; i < dArr.count; i++) {
			g2.fillOval(dArr.arr[i].x + off, dArr.arr[i].y + off, size, size);
		}
			
	}
	
	private boolean isValid(int x, int y) {
		
		return (x >= 0 && x < colNum && y >= 0 && y < rowNum);
	}
	
	public void update() {
		
		for (int i = 0; i < dArr.count; i++) {
			if (p.x % tileSize == 0 && p.x == dArr.arr[i].x && 
					p.y >= (dArr.arr[i].y) - tileSize / 3 && 
					p.y <= (dArr.arr[i].y) + tileSize / 3) {
				dArr.delete(i);
			}
			if (p.y % tileSize == 0 && p.y == dArr.arr[i].y && 
					p.x >= (dArr.arr[i].x) - tileSize / 3 && 
					p.x <= (dArr.arr[i].x) + tileSize / 3) {
				dArr.delete(i);
			}
		}
	}
}
