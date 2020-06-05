package JungleExplorer;

import java.awt.Rectangle;
import java.util.ArrayList;

public class ObjectManager {

	Player player;
	Box b = new Box();
	public ArrayList<ArrayList<ArrayList<Rectangle>>> lvls = new ArrayList<ArrayList<ArrayList<Rectangle>>>();
	
public ObjectManager(Player player) {
		this.player = player;
		lvls.add(createLevelI());
	}
	void update() {
	
		player.update();
		if (!checkHittingHead()) {
			if (!checkStanding()){
				player.ifAir = false;
				checkWall();
			}
			else {
				player.ifAir = true;
				
			}
		}
	
		

	

	
}
 boolean standing = false;
	boolean checkStanding() {
		standing = false;
		for(int i = 0; i<1;i++) {
		if(player.playerHitBox.intersects(lvls.get(0).get(i).get(2))) {
			player.yvelocity= 0;
	standing = true;
	break;
			
		}

		}
		return standing;
		}
		

		boolean hittingHead = false;
	
	boolean checkHittingHead() {
		hittingHead = false;
		for (int i = 0; i<1; i++) {
		if(player.playerHitBox.intersects(lvls.get(0).get(i).get(0))) {
		
			player.yvelocity = 0;
			
			hittingHead = true;
			break;
		}
	
		}
		return hittingHead;

		
	}
	boolean hittingWall = false;
	boolean checkWall() {
		hittingWall = false;
		for (int i = 0; i<1; i++) {
		if(player.playerHitBox.intersects(lvls.get(0).get(i).get(1))) {
			
			System.out.println("WALL");
		hittingWall = true;
				player.xspeed=0;
				break;	
		}
			
		}
		return hittingWall;
		}

		
	
	
	ArrayList<ArrayList<Rectangle>> createLevelI() {
		ArrayList<ArrayList<Rectangle>> levelI = new ArrayList<ArrayList<Rectangle>>();
		
		levelI.add(b.createBox(300, 600, 100, 100));
		levelI.add(b.createBox(800, 600, 300, 150));
		
		return levelI;
	}
	
}
