package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// ** STORES VARIABLES THAT WILL BE USED IN PLAYER, MONSTER AND NPC CLASSES
public class Entity {

	public int worldX, worldY; // screen coordinates
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // It describes an image with an
																				// accessible buffer of image data(used
																				// to store image files)
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;  //invisible abstract rectangle
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}
