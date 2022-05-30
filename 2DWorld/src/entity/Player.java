package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	// Where we draw player on the screen
	public final int screenX;
	public final int screenY;

	// constructor
	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {

		worldX = gp.tileSize * 23; //default player initial position (position on map tile)
		worldY = gp.tileSize * 21; //default player initial position (position on map tile)
		speed = 4;
		direction = "down"; // any direction is fine

	}

	public void getPlayerImage() {

		try { // will load player images

			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) { // Statement to stop player sprite from switching automatically only
												// switches when a key is pressed

			if (keyH.upPressed == true) {
				direction = "up";
				worldY -= speed; // short method
//				y = y - speed
			} else if (keyH.downPressed == true) {
				direction = "down";
				worldY += speed;
//				y = y + speed
			} else if (keyH.leftPressed == true) {
				direction = "left";
				worldX -= speed;
//				x = x - speed;
			} else if (keyH.rightPressed == true) {
				direction = "right";
				worldX += speed;
//				x = x + speed
			}

			spriteCounter++;
			if (spriteCounter > 12) { // every 12 frames player image changes
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	public void draw(Graphics2D g2) {

//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); // draws a rectangle and fills it with the specific color

		BufferedImage image = null;

		switch (direction) { // based on direction we grab the image
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // size of image

	}
}
