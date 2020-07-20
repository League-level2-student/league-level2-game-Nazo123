package JungleExplorer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectManager {
	boolean stand = true;
	Player player;
	Box b = new Box();
	public boolean noRight;
	int runner = 0;
	public boolean noLeft;
	ArrayList<Projectile> projectile = new ArrayList<Projectile>();

	public ArrayList<ArrayList<ArrayList<Rectangle>>> lvls = new ArrayList<ArrayList<ArrayList<Rectangle>>>();

	public ObjectManager(Player player) {
		this.player = player;
		lvls.add(createLevelI());

	}

	void update() {
	
		checkHittingHead();

			if (!checkStanding()) {
				if (stand == true)
					System.out.println("Not standing");
				stand = false;
				player.ifAir = true;
			} else {
				if (stand == false)
					System.out.println("standing");
				stand = true;
				player.ifAir = false;

			}

		
		
		checkStanding();
		checkRightWall();
		checkLeftWall();

		for (int i = 0; i < projectile.size(); i++) {
			projectile.get(i).update();
		}
		
		removeObjects();
		player.update();
		runner++;
	}

	void addProjectile(Projectile a) {
		projectile.add(a);

	}

	boolean standing = false;

	boolean checkStanding() {
		standing = false;
		
		for (int i = 0; i < lvls.get(0).size(); i++) {
			// Rectangle r = lvls.get(0).get(i).get(2);
			// Rectangle p = player.playerHitBox;
			// System.out.println("P" + i + " " + p.x + " " + p.y + " " + p.width + " " +
			// p.height);
			// System.out.println("R" + i + " " + r.x + " " + r.y + " " + r.width + " " +
			// r.height);
			// try {
			// System.in.read();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

	
			if (player.playerHitBox.intersects(lvls.get(0).get(i).get(2))) {
			
				player.ifAir=false;
				
					while (player.y + 125 > lvls.get(0).get(i).get(2).y + 1) {
						player.y--;
					
				}
		
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
				hittingHead = true;
				player.ifAir=true;
				player.yvelocity = 0;

				
				if (player.playerHitBox.intersects(lvls.get(0).get(i).get(0)) && noLeft == false && noRight == false) {
				while (player.y < lvls.get(0).get(i).get(0).y - 1) {
					player.y++;
				}

				}
				
				break;
			}

		}
		return hittingHead;

	}

	boolean hittingWall = false;

	void draw(Graphics g) {
		for (int i = 0; i < projectile.size(); i++) {

			projectile.get(i).draw(g);

		}
		for (int i = 0; i < lvls.get(0).size(); i++) {
			for (int k = 0; k < lvls.get(0).get(k).size(); k++) {
				Rectangle r = lvls.get(0).get(i).get(k);
				g.drawRect(r.x, r.y, r.width, r.height);
			}
		}
	}

	boolean checkLeftWall() {
		hittingWall = false;
		noRight = false;
		for (int i = 0; i < lvls.get(0).size(); i++) {
			if (player.playerHitBox.intersects(lvls.get(0).get(i).get(1))) {
				if (player.facingRight) {
					player.rightSpeed = 0;
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
				if (!player.facingRight) {
					noLeft = true;
					player.leftSpeed = 0;
				}

				break;
			}

		}
		return hittingWall;
	}

	// void debug_rectangle(String s, Rectangle r) {
	// System.out.println(s + " " + r.x + " " + r.y + " " + r.width + " " +
	// r.height);
//	}

	ArrayList<ArrayList<Rectangle>> createLevelI() {
		ArrayList<ArrayList<Rectangle>> levelI = new ArrayList<ArrayList<Rectangle>>();

		levelI.add(b.createBox(300, 600, 150, 100));
		levelI.add(b.createBox(0, 700, 450, 100));
		levelI.add(b.createBox(-20, -500, 50, 1900));
		levelI.add(b.createBox(1370, -1000, 30, 2700));
		levelI.add(b.createBox(650, 600, 525, 30));
		levelI.add(b.createBox(1250, 475, 120, 70));
		levelI.add(b.createBox(0, -100, 1400, 130));
		levelI.add(b.createBox(725, 350, 350, 50));
		levelI.add(b.createBox(0, 300, 525, 50));
		levelI.add(b.createBox(0, 250, 425, 50));
		levelI.add(b.createBox(0, 200, 325, 50));
		System.out.println(levelI.size());

		// debug_rectangle("lI0", levelI.get(0).get(2));
		// debug_rectangle("lI1", levelI.get(1).get(2));

		return levelI;
	}

	ArrayList<ArrayList<Rectangle>> createLevelII() {
		ArrayList<ArrayList<Rectangle>> levelII = new ArrayList<ArrayList<Rectangle>>();

		levelII.add(b.createBox(0, 700, 400, 50));
		levelII.add(b.createBox(400, 600, 100, 200));
		levelII.add(b.createBox(400, 700, 500, 50));
		levelII.add(b.createBox(900, 620, 130, 200));
		levelII.add(b.createBox(1000, 700, 400, 50));
		levelII.add(b.createBox(0, -500, 1400, 530));
		levelII.add(b.createBox(-20, -500, 50, 1900));
		levelII.add(b.createBox(1370, -1000, 30, 2700));
		levelII.add(b.createBox(1300, 575, 100, 125));
		levelII.add(b.createBox(1100, 425, 50, 50));
		levelII.add(b.createBox(1320, 320, 50, 50));
		levelII.add(b.createBox(1000, 180, 150, 50));
		levelII.add(b.createBox(0, 220, 1150, 60));

		return levelII;
	}
	ArrayList<ArrayList<Rectangle>> createLevelIII() {
		ArrayList<ArrayList<Rectangle>> levelIII = new ArrayList<ArrayList<Rectangle>>();
	
	
		levelIII.add(b.createBox(0, -500, 1400, 530));
		levelIII.add(b.createBox(-20, -500, 50, 1900));
		levelIII.add(b.createBox(1370, -1000, 30, 2700));
		levelIII.add(b.createBox(0, 700, 400, 50));
		levelIII.add(b.createBox(500, 650, 270, 150));
		levelIII.add(b.createBox(1000, 675, 50, 50));
		levelIII.add(b.createBox(1220, 615, 50, 50));
		levelIII.add(b.createBox(1330, 475, 70, 50));
		levelIII.add(b.createBox(1130, 330, 50, 50));
		levelIII.add(b.createBox(150, 200, 900, 50));
	
		return levelIII;
	}
	ArrayList<ArrayList<Rectangle>> createLevelIV() {
		ArrayList<ArrayList<Rectangle>> levelIV = new ArrayList<ArrayList<Rectangle>>();

	

		return levelIV;
	}
	ArrayList<ArrayList<Rectangle>> createLevelV() {
		ArrayList<ArrayList<Rectangle>> levelV = new ArrayList<ArrayList<Rectangle>>();

		levelV.add(b.createBox(0, 700, 400, 50));
		levelV.add(b.createBox(400, 600, 100, 200));
		levelV.add(b.createBox(400, 700, 500, 50));
		levelV.add(b.createBox(900, 620, 130, 200));
		levelV.add(b.createBox(1000, 700, 400, 50));
		levelV.add(b.createBox(0, -500, 1400, 530));
		levelV.add(b.createBox(-20, -500, 50, 1900));
		levelV.add(b.createBox(1370, -1000, 30, 2700));
		levelV.add(b.createBox(1300, 575, 100, 125));
		levelV.add(b.createBox(1100, 425, 50, 50));
		levelV.add(b.createBox(1320, 320, 50, 50));
		levelV.add(b.createBox(1000, 180, 150, 50));
		levelV.add(b.createBox(0, 220, 1150, 60));

		return levelV;
	}
	void removeObjects() {
		
		for(int i = 0; i<GamePanel.coins.size(); i++) {
			if(GamePanel.coins.get(i).hitbox.intersects(player.playerHitBox)&&GamePanel.coins.get(i).active) {
GamePanel.coin++;				
GamePanel.coins.get(i).collected = true;
			}
		}
		for (int i = 0; i<GamePanel.pads.size(); i++) {
			if (GamePanel.pads.get(i).hitbox.intersects(player.playerHitBox)&&GamePanel.pads.get(i).active) {
				projectile.clear();
				player.health--;
				player.x = 100;
				player.y = 400;
			}
		}
		for (int i = 0; i < projectile.size(); i++) {
			if (projectile.get(i).hitBox.intersects(player.playerHitBox)) {
				projectile.clear();
				player.health--;
				player.x = 100;
				player.y = 400;

			}
		}
		int holder = 0;
		for (int a = 0; a < projectile.size(); a++) {
			for (int s = 0; s < lvls.get(0).size(); s++) {

				if (projectile.get(a).hitBox.intersects(lvls.get(0).get(s).get(3))) {
					projectile.remove(a);

					break;
				}

			}
			if(projectile.size()==0) {
				break;
			}
			for (int s = 0; s < lvls.get(0).size(); s++) {
				

				if (projectile.get(a).hitBox.intersects(lvls.get(0).get(s).get(2))) {
					projectile.remove(a);
					break;
				}

			}
			for (int s = 0; s < lvls.get(0).size(); s++) {

				if (projectile.get(a).hitBox.intersects(lvls.get(0).get(s).get(1))) {
					projectile.remove(a);
					break;
				}

			}
			for (int s = 0; s < lvls.get(0).size(); s++) {

				if (projectile.get(a).hitBox.intersects(lvls.get(0).get(s).get(0))) {
					projectile.remove(a);
					break;
				}

			}
		}

	}

}
