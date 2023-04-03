package object;

import adventureGame2D.GamePanel;
import enums.ObjectType;


public class BronzeCoin extends GameObject {
	
	GamePanel gp;
	
	public BronzeCoin(GamePanel gp, int worldX, int worldY) {
		super (gp);
		this.gp = gp;
		this.WorldX = worldX * gp.getTileSize();
		this.WorldY = worldY * gp.getTileSize();
		
		setDefaultAttributes();
		setPickupState();
		setInventoryType();
	}
	
	@Override
	protected void setDefaultAttributes() {
		name = "Shiny Bronze Coin";
		collisionOn = false;
		down1 = setupEntity("coin_bronze", "/objects/equip/", gp.getTileSize(), gp.getTileSize());
		objectDescription =  "Looks really like your old mother's bedroom key.\nToo bad she's sleeping in the skies now.\nCan be used to open locked doors.";
		
	}


	@Override
	public void setPickupState() {
		this.pickUpState = true;
		
	}


	@Override
	public void setInventoryType() {
		this.inventoryType = ObjectType.POWERUP;
		
	}
	
	public int getMonetaryValue() {return 1;}
	
	
	
}
