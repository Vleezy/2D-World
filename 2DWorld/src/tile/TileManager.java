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
	Tile[] tile;
	int mapTileNum[][]; // will store the text file numbers

	public TileManager(GamePanel gp) {

		this.gp = gp;

		// setting the size
		tile = new Tile[10]; // how many tiles we will create (can change)
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

		getTileImage();
		loadMap("/maps/map01.txt"); // loads text file (map)
	}

	public void getTileImage() { // loading images in this method

		try {

			// Instantiation
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

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

			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine(); // read line of text

				while (col < gp.maxScreenCol) {
					String numbers[] = line.split(" "); // splits the string around matches of the given regular
														// expression and splits line at the space
					int num = Integer.parseInt(numbers[col]); // use col as an index for number[] array

					mapTileNum[col][row] = num; // can store the extracted number in the mapTileNum
					col++;
				}

					if (col == gp.maxScreenCol) {
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
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

			int tileNum = mapTileNum[col][row]; // extract a tile number which is stored on maptTileNum!

			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;

			if (col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}

	}

}
