package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// **SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 Tile (Default size)
	final int scale = 3; // will scale due to monitor resolutions varying.

	public final int tileSize = originalTileSize * scale; // 48x48 Tile

	public final int maxScreenCol = 16; // horizontal
	public final int maxScreenRow = 12; // vertical
	public final int screenWidth = tileSize * maxScreenCol; // 758 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// **WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	// NOT USING - can delete later******
//	public final int worldWidth = tileSize * maxWorldCol;
//	public final int worldRow = tileSize * maxWorldRow;

	// **FPS
	int FPS = 60;

	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread; // Keeps programming running

	// ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10]; // number of objects

	// CONSTRUCTOR
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // If set to true, all drawings from this component will be done in an
										// off-screen painting buffer
										// Enabling improves game's rendering performance
		this.addKeyListener(keyH); // recognize key input
		this.setFocusable(true);
	}

	public void setupGame() {
		
		aSetter.setObject();
	
		playMusic(0);
	}

	public void startGamethread() {
		gameThread = new Thread(this); // instantiating
		gameThread.start(); // will automatically call the run() method
	}

	// currently commented out - not in use IGNORE
	public void SleepGameLoop() {
		// SLEEP METHOD GAME LOOP (could use this instead of delta game loop)
//	@Override
//	public void run() {
//
//		// GAME LOOP (Core of game)
//		while (gameThread != null) { // as long as game thread exists it repeats the process
//
////			System.out.println("The game loop is running.."); // TESTING LOOP
////			long currentTime = System.nanoTime();	
////			System.out.println("Current Time:" + currentTime); // When we need to check for current time
//
//			double drawInterval = 1000000000 / FPS; // 0.01666 seconds
//			double nextDrawTime = System.nanoTime() + drawInterval;
//
//			// 1 UPDATE: update information such as characters
//			update();
//
//			// 2 DRAW: draw the screen with the updated information
//			repaint();
//
//			try {
//
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime / 1000000; // converting to seconds because of the sleep method
//
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	}

	// DELTA/ACCUMULATOR METHOD GAME LOOP
	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime = 0;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) { // as long as game thread exists it repeats the process

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				// 1 UPDATE: update information such as characters
				update();
				// 2 DRAW: draw the screen with the updated information
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
//				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}

		}
	}

	public void update() {

		player.update();

	}

	public void paintComponent(Graphics g) { // Graphics a class that has many functions to draw objects on the screen

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g; // extends Graphics class to provide more sophisticated control

		// TILE
		tileM.draw(g2); // draw tiles before player (like a layer)

		// OBJECT
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

		// PLAYER
		player.draw(g2);
		
		//UI
		
		ui.draw(g2);

		g2.dispose(); // dispose of this graphics context and release any system resources that it is
						// using

	}

	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {

		music.stop();
	}

	public void playSE(int i) {

		se.setFile(i);
		se.play();
	}
}
