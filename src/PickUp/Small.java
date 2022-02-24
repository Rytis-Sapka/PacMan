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
	
	public Small(GamePanel gp, TileManager tm, Player p) {
		
		this.gp = gp;
		this.tm = tm;
		this.p = p;
		size = gp.tileSize / 4;
		off = gp.tileSize * 3 / 8;
		
		for (int i = 0; i < gp.rowNum; i++) {
			for (int j = 0; j < gp.colNum; j++) {
				if (tm.mapTiles[i][j] == 17) {
					Item insertion = new Item();
					insertion.x = j;
					insertion.y = i;
					dArr.insert(insertion);
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setColor(Color.yellow);
		for (int i = 0; i < dArr.count; i++) {
			g2.fillOval(dArr.arr[i].x * gp.tileSize + off, dArr.arr[i].y * gp.tileSize + off, size, size);
		}
			
	}
	
	public void update() {
		
		for (int i = 0; i < dArr.count; i++) {
			if (p.x % gp.tileSize == 0 && p.x / gp.tileSize == dArr.arr[i].x && 
					p.y >= (dArr.arr[i].y) * gp.tileSize - gp.tileSize / 2 && 
					p.y <= (dArr.arr[i].y) * gp.tileSize + gp.tileSize / 2) {
				dArr.delete(i);
			}
			if (p.y % gp.tileSize == 0 && p.y / gp.tileSize == dArr.arr[i].y && 
					p.x >= (dArr.arr[i].x) * gp.tileSize - gp.tileSize / 2 && 
					p.x <= (dArr.arr[i].x) * gp.tileSize + gp.tileSize / 2) {
				dArr.delete(i);
			}
		}
	}
}
