package adventureGame2D;

import monster.MonsterGreenSlime;
import npc.OldDude;
import object.Axe;
import object.Boots;
import object.Chest;
import object.Door;
import object.Key;

public class AssetPlacement {
	//Class managed object placement
	GamePanel gp;
	
	public AssetPlacement (GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.getObjects().add(new Chest(gp, 121, 140));
		gp.getObjects().add(new Door(gp, 121, 123));
		gp.getObjects().add(new Boots(gp, 121, 124));
		gp.getObjects().add(new Axe(gp, 121, 125));

	}
	
	public void setNPCs() {
		gp.getNPCS().add(new OldDude(gp, 121, 139));
	}
	
	public void setMonsters() {
		gp.getMonsters().add(new MonsterGreenSlime (gp, 126, 136));
		gp.getMonsters().add(new MonsterGreenSlime (gp, 125, 120));
		gp.getMonsters().add(new MonsterGreenSlime (gp, 124, 121));
		gp.getMonsters().add(new MonsterGreenSlime (gp, 127, 122));
		gp.getMonsters().add(new MonsterGreenSlime (gp, 127, 123));
		gp.getMonsters().add(new MonsterGreenSlime (gp, 127, 124));
	}
	
}
