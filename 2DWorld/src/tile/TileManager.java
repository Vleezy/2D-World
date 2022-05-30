package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][]; // will store the text file numbers

	public TileManager(GamePanel gp) {

		this.gp = gp;

		// setting the size
		tile = new Tile[10]; // how many tiles we will create (can change)
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/world01.txt"); // loads text file (map)
	}

	public void getTileImage() { // loading images in this method

		try {

			// Instantiation
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {

		try {

			InputStream is = getClass().getResourceAsStream(filePath); // *map text file rendering
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // reads content of text file

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine(); // read line of text

				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" "); // splits the string around matches of the given regular
														// expression and splits line at the space
					int num = Integer.parseInt(numbers[col]); // use col as an index for number[] array

					mapTileNum[col][row] = num; // can store the extracted number in the mapTileNum
					col++;
				}

				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}

			br.close(); // buffer reader closes

		} catch (Exception e) {

		}

	}

	// promise
	public void draw(Graphics2D g2) {

		// change the x, y for tile location
//		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);

		// Tile Loop automating the tile drawing process
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow]; // extract a tile number which is stored on maptTileNum!

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			// tile drawing system: as long as tiles are in range they will render (helps
			// with performance)
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}

	}

}
