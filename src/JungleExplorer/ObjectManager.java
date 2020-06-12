package JungleExplorer;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectManager {
    boolean stand = true;
	Player player;
	Box b = new Box();
	public boolean noRight;
	public boolean noLeft;
	public ArrayList<ArrayList<ArrayList<Rectangle>>> lvls = new ArrayList<ArrayList<ArrayList<Rectangle>>>();

	public ObjectManager(Player player) {
		this.player = player;
		lvls.add(createLevelI());
        System.out.println("Hit Ceiling:" + checkHittingHead());		
        System.out.println("Hit Roof:" + checkStanding());		
        System.out.println("Hit Left Wall:" + checkLeftWall());		
        System.out.println("Hit Right Wall:" + checkRightWall());	
	}

	void update() {
	    
		if (!checkHittingHead()) {
			if (!checkStanding()) {
				if(stand == true) System.out.println("Not standing");
                stand = false;
                player.ifAir=true;
							} else {
				if(stand == false) System.out.println("standing");
				stand = true;
				player.ifAir = false;

			}
			
		}
	checkStanding();
	checkLeftWall();
		checkRightWall();
	

		player.update();

	}

	boolean standing = false;

	boolean checkStanding() {
		standing = false;
		for (int i = 0; i < lvls.get(0).size(); i++) {
		//	Rectangle r = lvls.get(0).get(i).get(2);
		//	Rectangle p = player.playerHitBox;
		//	System.out.println("P" + i + " " + p.x + " " + p.y + " " + p.width + " " + p.height);
		//	System.out.println("R" + i + " " + r.x + " " + r.y + " " + r.width + " " + r.height);
        //    try {
		//		System.in.read();
		//	} catch (IOException e) {
		//		// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
			if (player.playerHitBox.intersects(lvls.get(0).get(i).get(2))) {
			

				standing = true;
				break;

			}

		}
	

		return standing;

	}

	boolean hittingHead = false;

	boolean checkHittingHead() {
		hittingHead = false;
		for (int i = 0; i < lvls.get(0).size(); i++) {
			if (player.playerHitBox.intersects(lvls.get(0).get(i).get(0))) {

				player.yvelocity = 0;

				hittingHead = true;
				break;
			}

		}
		return hittingHead;

	}

	boolean hittingWall = false;
	

	boolean checkLeftWall() {
		hittingWall = false;
		noRight = false;
		for (int i = 0; i < lvls.get(0).size(); i++) {
			if (player.playerHitBox.intersects(lvls.get(0).get(i).get(1))) {
				if(player.facingRight) {
				player.rightSpeed =0;
				noRight = true;
				
				}
				hittingWall = true;
				
				
				break;
			}

		}
		return hittingWall;
	}

	boolean checkRightWall() {
		hittingWall = false;
		noLeft = false;
		
		for (int i = 0; i < lvls.get(0).size(); i++) {
			if (player.playerHitBox.intersects(lvls.get(0).get(i).get(3))) {
			
				hittingWall = true;
				if(!player.facingRight) {
					noLeft = true;
				player.leftSpeed = 0;
				}
				
				break;
			}

		}
		return hittingWall;
	}
	
	//void debug_rectangle(String s, Rectangle r)  {
	//  System.out.println(s + " " + r.x + " " + r.y + " " + r.width + " " + r.height);
//	}

	ArrayList<ArrayList<Rectangle>> createLevelI() {
		ArrayList<ArrayList<Rectangle>> levelI = new ArrayList<ArrayList<Rectangle>>();

		levelI.add(b.createBox(300, 600, 150, 100));
		levelI.add(b.createBox(0, 700, 450, 200));
		levelI.add(b.createBox(-20, 0, 50, 900));
		levelI.add(b.createBox(1370, 0, 50, 900));
		levelI.add(b.createBox(650, 600, 525, 200));
		levelI.add(b.createBox(1250,475,120,70));
		levelI.add(b.createBox(0, 0, 1400, 30));
		levelI.add(b.createBox(725, 350, 350, 50));
		levelI.add(b.createBox(0, 300, 525, 50));
		levelI.add(b.createBox(0, 250, 425, 50));
		levelI.add(b.createBox(0, 200, 325, 50));
		
		System.out.println(levelI.size());
		
	 //  debug_rectangle("lI0", levelI.get(0).get(2));
	 //   debug_rectangle("lI1", levelI.get(1).get(2));


		return levelI;
	}

}
