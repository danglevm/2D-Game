package tile;

import adventureGame2D.GamePanel;
import enums.Direction;
import enums.ToolType;
import object.interfaces.ToolObjectInterface;

public class DryTree extends InteractiveTile {

	public DryTree(GamePanel gp, int WorldX, int WorldY) {
		super(gp, WorldX, WorldY);
		down1 = setupEntity("drytree", "/tiles/interactive/", gp.getTileSize(), gp.getTileSize());
		destructible = true;
		collisionOn = true;
		life = 3;
		this.WorldX = WorldX * gp.getTileSize();
		this.WorldY = WorldY * gp.getTileSize();
		direction = Direction.DOWN;
	}

	@Override
	public void updateInteractiveTile() {
		// TODO Auto-generated method stub
		if (invincibility) {
			++invincibilityCounter;
		if (invincibilityCounter > 20) {
			invincibility = false;
			invincibilityCounter = 0;
			
			}
		}
	}

	@Override
	public void interactTile(int index, boolean useTool) {
		if (index != 9999 && this.destructible && useTool && !this.invincibility) {
			if (((ToolObjectInterface)gp.getPlayer().getEquippedTool()).getToolType() == ToolType.AXE) {
				gp.playSE(17);
				--this.life;
				this.invincibility = true;
				
				if (this.life < 1) {
					gp.getInteractiveTiles().set(index, new Trunk(gp, this.WorldX/gp.getTileSize(), this.WorldY/gp.getTileSize()));
				}
			}
		}
	}


}
