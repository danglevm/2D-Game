package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import adventureGame2D.GamePanel;
import adventureGame2D.KeyHandler;
import adventureGame2D.UI;
import adventureGame2D.UtilityTool;

public class Player extends Entity {
	
	//Variables
	KeyHandler keyH;
	BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	

	//Where the player is drawn on the screen - camera 
	public final int screenX;
	public final int screenY;
	
	private boolean switchOpacity = false;
	private int switchOpacityCounter = 0;
	
	
	
	//-------------------------------CONSTRUCTORS------------------
	public Player (GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		//Places the character at the center of the screen
		//Subtract half of the tile length to be at the center of the player character
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//x, y, width, length
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 8;
		//Default values so x and y values of the rectangle can be changed later
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 24;
		solidArea.height = 36;
		
		this.setDefaultPlayerValues();
		this.getPlayerImage();
	}
	
	//-------------------------------CLASS METHODS------------------
	public void getPlayerImage() {
		
		up1 = setupCharacter("boy_up_1", "/player/");
		up2 = setupCharacter("boy_up_2", "/player/");
		down1 = setupCharacter("boy_down_1", "/player/");
		down2 = setupCharacter("boy_down_2", "/player/");
		left1 = setupCharacter("boy_left_1", "/player/");
		left2 = setupCharacter("boy_left_2", "/player/");
		right1 = setupCharacter("boy_right_1", "/player/");
		right2 = setupCharacter ("boy_right_2", "/player/");
		
	}
	
	public void setDefaultPlayerValues() {
		//Default player values
		WorldX = gp.tileSize * 122;
		WorldY= gp.tileSize * 132;
		speed = 3;
		entityType = 0;
		direction = "down";
		maxLife = 8;
		life = maxLife;
		
	}
	
	
	
	public void update() {
		if (keyH.upPressed||keyH.downPressed||
				keyH.leftPressed||keyH.rightPressed)
		{//X and Y values increase as the player moves right and down
			
			//Check tile collision
			collisionOn = false;
			
			//monster collision
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
			//this might return 9999
			if (monsterIndex != 9999) {
				gp.monsters.get(monsterIndex).damageContact(this);
			}
			
			//Collision checker receive subclass
			gp.cChecker.CheckTile(this);
			
			//Check event collision
			gp.eHandler.checkEvent();
			
			//Check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			ObjectPickUp(objIndex);
			
			
			//check NPC collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.NPCs);
			collisionNPC(npcIndex);
			
	
			if (keyH.upPressed) {
				this.direction = "up";
				if (!collisionOn && !keyH.dialoguePressed) {this.WorldY -= speed;} 
			} 
			if (keyH.downPressed) {
				this.direction = "down";
				if (!collisionOn && !keyH.dialoguePressed) {this.WorldY += speed;}
			} 
			if (keyH.leftPressed) {
				this.direction = "left";
				if (!collisionOn && !keyH.dialoguePressed) {this.WorldX -= speed;}
			} 
			if (keyH.rightPressed) {
				this.direction = "right";
				if (!collisionOn && !keyH.dialoguePressed) {this.WorldX += speed;}
			}
		
			
			
			
			
			
			spriteCounter++;
			//Player image changes every 12 frames
			if (spriteCounter > 12) {
				if (spriteNum) {
					spriteNum = false;
				}
				
				else if (!spriteNum) {
					spriteNum = true;
				}
				spriteCounter = 0;
			}
			
		}//keypress loop
		
		//Call superclass invincibility time
		this.checkInvincibilityTime();
		
	}//update
	
	public void ObjectPickUp(int index) {
		
		if (index != 9999) {
			

	}
	}
	
	private final void collisionNPC (int i) {
		if (i != 9999) {
			//player touching npc
			if (keyH.dialoguePressed) {
				gp.gameState = gp.dialogueState;
				gp.NPCs.get(i).speak();
				keyH.dialoguePressed = false; 
			}
		}
	
	}
	
	public void draw(Graphics2D g2) {
		/*
		g2.setColor(Color.yellow);
		g2.fillRect(x, y, gp.tileSize,gp.tileSize );
		*/
		
		BufferedImage image = null;
		
		
		switch (direction) {
		case "up":
			if (spriteNum) {
				image = up1;
			}
			if (!spriteNum) {
				image = up2;
			}
			
			break;
		case "down":
			if (spriteNum) {
				image = down1;
			}
			if (!spriteNum) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum) {
				image = left1;
			}
			if (!spriteNum) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum) {
				image = right1;
			}
			if (!spriteNum) {
				image = right2;
			}
			break;
		}
		
		//Draw effect when player gets damaged
		if (this.invincibility) {
			++this.switchOpacityCounter;
			if (!switchOpacity && switchOpacityCounter > 3) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));	
				switchOpacity = true;
				switchOpacityCounter = 0;
			} else {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));	
				switchOpacity = false;
			}
		}
		//16 pixels
		g2.drawImage(image, screenX, screenY, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		
			
	}
	
	@Override
	protected final void checkInvincibilityTime() {

		if (this.invincibility) {
		++this.invincibilityCounter;
		if (this.invincibilityCounter > 90) {
			this.invincibility = false;
			this.invincibilityCounter = 0;
			
			}
		}
	}
	
}
