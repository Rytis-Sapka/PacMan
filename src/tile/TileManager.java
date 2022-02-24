package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	//object variables
	GamePanel gp;
	Scanner fin;
	
	//sprite
	Tile[] tile;

	//map
	public int mapTiles[][];
	
	//initialize values, load map
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tile = new Tile[20];
		mapTiles = new int[gp.rowNum][gp.colNum];
		
		getTileImage();
		loadMap("/maps/map1.txt");
		MapFixer mp = new MapFixer(gp);
		mp.fixMap(mapTiles);
		
	}
	
	//load sprite, set collision info
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall0.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1W.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1A.png"));
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1S.png"));
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1D.png"));
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2WA.png"));
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2AS.png"));
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2SD.png"));
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2WD.png"));
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2WS.png"));
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall2AD.png"));
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall3WAS.png"));
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall3ASD.png"));
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall3WSD.png"));
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall3WAD.png"));
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall4.png"));
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/testH.png"));
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/testV.png"));
			
			for (int i = 0; i < 16; i++)
				tile[i].collision = true;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//read map info from text file
	public void loadMap(String path) {
		
		try {
			InputStream is = getClass().getResourceAsStream(path);			
			fin = new Scanner(new BufferedReader(new InputStreamReader(is)));
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < gp.rowNum; i++) {
			for (int j = 0; j < gp.colNum; j++) {
				mapTiles[i][j] = fin.nextInt();
			}
		}
	}
	
	//draw tile grid
	public void draw(Graphics2D g2) {
		
		for (int i = 0; i < gp.rowNum; i++) {
			for (int j = 0; j < gp.colNum; j++) {
				if (mapTiles[i][j] < 16) {
					BufferedImage image = tile[mapTiles[i][j]].image;
					g2.drawImage(image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}
	
}
