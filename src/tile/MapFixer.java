package tile;

import main.GamePanel;

//a solid block of if statements to make the walls nice and rounded

public class MapFixer {

	GamePanel gp;
	
	public MapFixer(GamePanel gp) {
		
		this.gp = gp;
	}
	
	//Makes corners correct
	public void fixMap(int[][] mapTiles) {
		
		//middle cases
		for (int i = 1; i < gp.rowNum - 1; i++) {
			for (int j = 1; j < gp.colNum - 1; j++) {
				if(mapTiles[i][j] < 16) {
					
					if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 0;
					else if(mapTiles[i-1][j] < 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 1;
					else if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] < 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 2;
					else if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] < 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 3;
					else if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] < 16)
						mapTiles[i][j] = 4;
					else if(mapTiles[i-1][j] < 16 && mapTiles[i][j-1] < 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 5;
					else if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] < 16 && mapTiles[i+1][j] < 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 6;
					else if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] < 16 && mapTiles[i][j+1] < 16)
						mapTiles[i][j] = 7;
					else if(mapTiles[i-1][j] < 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] < 16)
						mapTiles[i][j] = 8;
					else if(mapTiles[i-1][j] < 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] < 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 9;
					else if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] < 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] < 16)
						mapTiles[i][j] = 10;
					else if(mapTiles[i-1][j] < 16 && mapTiles[i][j-1] < 16 && mapTiles[i+1][j] < 16 && mapTiles[i][j+1] >= 16)
						mapTiles[i][j] = 11;
					else if(mapTiles[i-1][j] >= 16 && mapTiles[i][j-1] < 16 && mapTiles[i+1][j] < 16 && mapTiles[i][j+1] < 16)
						mapTiles[i][j] = 12;
					else if(mapTiles[i-1][j] < 16 && mapTiles[i][j-1] >= 16 && mapTiles[i+1][j] < 16 && mapTiles[i][j+1] < 16)
						mapTiles[i][j] = 13;
					else if(mapTiles[i-1][j] < 16 && mapTiles[i][j-1] < 16 && mapTiles[i+1][j] >= 16 && mapTiles[i][j+1] < 16)
						mapTiles[i][j] = 14;
					else
						mapTiles[i][j] = 15;
				}	
			}
		}
		
		//left and right columns
		for (int i = 1; i < gp.colNum - 1; i++) {
			if(mapTiles[0][i] < 16) {
				
				if(mapTiles[0][i-1] >= 16 && mapTiles[1][i] >= 16 && mapTiles[0][i+1] >= 16)
					mapTiles[0][i] = 0;
				else if(mapTiles[0][i-1] < 16 && mapTiles[1][i] >= 16 && mapTiles[0][i+1] >= 16)
					mapTiles[0][i] = 2;
				else if(mapTiles[0][i-1] >= 16 && mapTiles[1][i] < 16 && mapTiles[0][i+1] >= 16)
					mapTiles[0][i] = 9;
				else if(mapTiles[0][i-1] >= 16 && mapTiles[1][i] >= 16 && mapTiles[0][i+1] < 16)
					mapTiles[0][i] = 4;
				else if(mapTiles[0][i-1] < 16 && mapTiles[1][i] < 16 && mapTiles[0][i+1] >= 16)
					mapTiles[0][i] = 6;
				else if(mapTiles[0][i-1] >= 16 && mapTiles[1][i] < 16 && mapTiles[0][i+1] < 16)
					mapTiles[0][i] = 7;
				else if(mapTiles[0][i-1] < 16 && mapTiles[1][i] >= 16 && mapTiles[0][i+1] < 16)
					mapTiles[0][i] = 10;
				else
					mapTiles[0][i] = 12; 
			}
			
			if(mapTiles[gp.rowNum-1][i] < 16) {
				
				if(mapTiles[gp.rowNum-1][i-1] >= 16 && mapTiles[gp.rowNum-2][i] >= 16 && mapTiles[gp.rowNum-1][i+1] >= 16)
					mapTiles[gp.rowNum-1][i] = 0;
				else if(mapTiles[gp.rowNum-1][i-1] >= 16 && mapTiles[gp.rowNum-2][i] < 16 && mapTiles[gp.rowNum-1][i+1] >= 16)
					mapTiles[gp.rowNum-1][i] = 9;
				else if(mapTiles[gp.rowNum-1][i-1] < 16 && mapTiles[gp.rowNum-2][i] >= 16 && mapTiles[gp.rowNum-1][i+1] >= 16)
					mapTiles[gp.rowNum-1][i] = 2;
				else if(mapTiles[gp.rowNum-1][i-1] >= 16 && mapTiles[gp.rowNum-2][i] >= 16 && mapTiles[gp.rowNum-1][i+1] < 16)
					mapTiles[gp.rowNum-1][i] = 4;
				else if(mapTiles[gp.rowNum-1][i-1] < 16 && mapTiles[gp.rowNum-2][i] < 16 && mapTiles[gp.rowNum-1][i+1] >= 16)
					mapTiles[gp.rowNum-1][i] = 5;
				else if(mapTiles[gp.rowNum-1][i-1] >= 16 && mapTiles[gp.rowNum-2][i] < 16 && mapTiles[gp.rowNum-1][i+1] < 16)
					mapTiles[gp.rowNum-1][i] = 8;
				else if(mapTiles[gp.rowNum-1][i-1] < 16 && mapTiles[gp.rowNum-2][i] >= 16 && mapTiles[gp.rowNum-1][i+1] < 16)
					mapTiles[gp.rowNum-1][i] = 10;
				else
					mapTiles[gp.rowNum-1][i] = 14; 
			}
		}
		
		//top and bottom rows
		for (int i = 1; i < gp.rowNum - 1; i++) {
			
			if(mapTiles[i][0] < 16) {
				
				if(mapTiles[i-1][0] >= 16 && mapTiles[i][1] >= 16 && mapTiles[i+1][0] >= 16)
					mapTiles[i][0] = 0;
				else if(mapTiles[i-1][0] < 16 && mapTiles[i][1] >= 16 && mapTiles[i+1][0] >= 16)
					mapTiles[i][0] = 1;
				else if(mapTiles[i-1][0] >= 16 && mapTiles[i][1] < 16 && mapTiles[i+1][0] >= 16)
					mapTiles[i][0] = 10;
				else if(mapTiles[i-1][0] >= 16 && mapTiles[i][1] >= 16 && mapTiles[i+1][0] < 16)
					mapTiles[i][0] = 3;
				else if(mapTiles[i-1][0] < 16 && mapTiles[i][1] < 16 && mapTiles[i+1][0] >= 16)
					mapTiles[i][0] = 8;
				else if(mapTiles[i-1][0] >= 16 && mapTiles[i][1] < 16 && mapTiles[i+1][0] < 16)
					mapTiles[i][0] = 7;
				else if(mapTiles[i-1][0] < 16 && mapTiles[i][1] >= 16 && mapTiles[i+1][0] < 16)
					mapTiles[i][0] = 9;
				else
					mapTiles[i][0] = 13;
			}
			
			if(mapTiles[i][gp.colNum-1] < 16) {
				
				if(mapTiles[i-1][gp.colNum-1] >= 16 && mapTiles[i][gp.colNum-2] >= 16 && mapTiles[i+1][gp.colNum-1] >= 16)
					mapTiles[i][gp.colNum-1] = 0;
				else if(mapTiles[i-1][gp.colNum-1] < 16 && mapTiles[i][gp.colNum-2] >= 16 && mapTiles[i+1][gp.colNum-1] >= 16)
					mapTiles[i][gp.colNum-1] = 1;
				else if(mapTiles[i-1][gp.colNum-1] >= 16 && mapTiles[i][gp.colNum-2] < 16 && mapTiles[i+1][gp.colNum-1] >= 16)
					mapTiles[i][gp.colNum-1] = 10;
				else if(mapTiles[i-1][gp.colNum-1] >= 16 && mapTiles[i][gp.colNum-2] >= 16 && mapTiles[i+1][gp.colNum-1] < 16)
					mapTiles[i][gp.colNum-1] = 3;
				else if(mapTiles[i-1][gp.colNum-1] < 16 && mapTiles[i][gp.colNum-2] < 16 && mapTiles[i+1][gp.colNum-1] >= 16)
					mapTiles[i][gp.colNum-1] = 5;
				else if(mapTiles[i-1][gp.colNum-1] >= 16 && mapTiles[i][gp.colNum-2] < 16 && mapTiles[i+1][gp.colNum-1] < 16)
					mapTiles[i][gp.colNum-1] = 6;
				else if(mapTiles[i-1][gp.colNum-1] < 16 && mapTiles[i][gp.colNum-2] >= 16 && mapTiles[i+1][gp.colNum-1] < 16)
					mapTiles[i][gp.colNum-1] = 9;
				else
					mapTiles[i][gp.colNum-1] = 11;
			}
		}
		
		//top left
		if(mapTiles[0][0] < 16) {
			
			if(mapTiles[0][1] >= 16 && mapTiles[1][0] >= 16) 
				mapTiles[0][0] = 0;
			else if(mapTiles[0][1] < 16 && mapTiles[1][0] >= 16) 
				mapTiles[0][0] = 10;
			else if(mapTiles[0][1] >= 16 && mapTiles[1][0] < 16) 
				mapTiles[0][0] = 9;
			else
				mapTiles[0][0] = 7;
		}
		
		//bottom left
		if(mapTiles[gp.rowNum-1][0] < 16) {
			
			if(mapTiles[gp.rowNum-1][1] >= 16 && mapTiles[gp.rowNum-2][0] >= 16) 
				mapTiles[gp.rowNum-1][0] = 0;
			else if(mapTiles[gp.rowNum-1][1] < 16 && mapTiles[gp.rowNum-2][0] >= 16) 
				mapTiles[gp.rowNum-1][0] = 10;
			else if(mapTiles[gp.rowNum-1][1] >= 16 && mapTiles[gp.rowNum-2][0] < 16) 
				mapTiles[gp.rowNum-1][0] = 9;
			else
				mapTiles[gp.rowNum-1][0] = 8;
		}
		
		//top right
		if(mapTiles[0][gp.colNum-1] < 16) {
			
			if(mapTiles[0][gp.colNum-2] >= 16 && mapTiles[1][gp.colNum-1] >= 16) 
				mapTiles[0][gp.colNum-1] = 0;
			else if(mapTiles[0][gp.colNum-2] < 16 && mapTiles[1][gp.colNum-1] >= 16) 
				mapTiles[0][gp.colNum-1] = 10;
			else if(mapTiles[0][gp.colNum-2] >= 16 && mapTiles[1][gp.colNum-1] < 16) 
				mapTiles[0][gp.colNum-1] = 9;
			else
				mapTiles[0][gp.colNum-1] = 6;
		}
		
		//bottom right
		if(mapTiles[gp.rowNum-1][gp.colNum-1] < 16) {
			
			if(mapTiles[gp.rowNum-1][gp.colNum-2] >= 16 && mapTiles[gp.rowNum-2][gp.colNum-1] >= 16) 
				mapTiles[gp.rowNum-1][gp.colNum-1] = 0;
			else if(mapTiles[gp.rowNum-1][gp.colNum-2] < 16 && mapTiles[gp.rowNum-2][gp.colNum-1] >= 16) 
				mapTiles[gp.rowNum-1][gp.colNum-1] = 10;
			else if(mapTiles[gp.rowNum-1][gp.colNum-2] >= 16 && mapTiles[gp.rowNum-2][gp.colNum-1] < 16) 
				mapTiles[gp.rowNum-1][gp.colNum-1] = 9;
			else
				mapTiles[gp.rowNum-1][gp.colNum-1] = 5;
		}
		
	}
}
