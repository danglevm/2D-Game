package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import adventureGame2D.GamePanel;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	
	//Rectangle values
	//x,y,width,length
	//Rectangle value can be changed for different objects to reflect size
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX-gp.player.WorldX + gp.player.screenX;
		int screenY = worldY - gp.player.WorldY + gp.player.screenY;
		if (worldX+gp.tileSize>gp.player.WorldX-gp.player.screenX && 
			worldX-gp.tileSize<gp.player.WorldX+gp.player.screenX &&
			worldY+gp.tileSize>gp.player.WorldY-gp.player.screenY&&
			worldY-gp.tileSize <gp.player.WorldY+gp.player.screenY) {
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
	}
}