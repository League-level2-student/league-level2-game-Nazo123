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
	public boolean noLeft;
	ArrayList<Projectile> projectile = new ArrayList<Projectile>();

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
		checkRightWall();
		checkLeftWall();
		if (!checkHittingHead()) {
		
		
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

		}
		checkStanding();

		player.update();
		for (int i = 0; i<projectile.size();i++) {
		projectile.get(i).update();
		}
		removeObjects();

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

				standing = true;
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

				player.yvelocity = 0;
				while (player.y < lvls.get(0).get(i).get(0).y - 1) {
					player.y++;
				}
				hittingHead = true;
				break;
			}

		}
		return hittingHead;

	}

	boolean hittingWall = false;

	void draw(Graphics g) {
		for(int i = 0; i<projectile.size();i++) {
			projectile.get(i).draw(g);
			
		}
		for(int i = 0; i<lvls.get(0).size();i++) {
			for(int k = 0; k<lvls.get(0).get(k).size();k++) {
				Rectangle r = lvls.get(0).get(i).get(k);
				g.drawRect(r.x,r.y,r.width,r.height);
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
		levelI.add(b.createBox(0, 700, 450, 200));
		levelI.add(b.createBox(-20, 0, 50, 900));
		levelI.add(b.createBox(1370, 0, 50, 900));
		levelI.add(b.createBox(650, 600, 525, 200));
		levelI.add(b.createBox(1250, 475, 120, 70));
		levelI.add(b.createBox(0, 0, 1400, 30));
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
		levelII.add(b.createBox(1000,700,400,50));
		levelII.add(b.createBox(0, 0, 1400, 30));
		levelII.add(b.createBox(0, 0, 30, 700));
		levelII.add(b.createBox(1370, 0, 30, 700));
levelII.add(b.createBox(1300, 575, 100, 125));
levelII.add(b.createBox(1100, 425, 50, 50));
levelII.add(b.createBox(1330, 300, 50, 50));

		return levelII;
	}

	void removeObjects() {
		for (int q = 0; q<projectile.size(); q++) {
			if (projectile.get(q).x<0) {
				projectile.remove(q);
			}
		}
		for (int w = 0; w<projectile.size(); w++) {
			if (projectile.get(w).y<-80) {
				projectile.remove(w);
			}
		}
		for (int i = 0; i < projectile.size(); i++) {
			if (projectile.get(i).hitBox.intersects(player.playerHitBox)) {
				projectile.remove(i);
				player.health--;
				player.x = 100;
				player.y = 400;
				projectile.clear();
		}
		}
			
			for (int a = 0; a < projectile.size(); a++) {
				System.out.println(projectile.get(a).x+", "+projectile.get(a).y+"    "+lvls.get(0).get(a).get(3).x+", "+lvls.get(0).get(a).get(3).y);
				if (projectile.get(a).hitBox.intersects(lvls.get(0).get(a).get(3))) {
					System.out.println("test");
					
					projectile.remove(a);

				}
			}
			for (int b = 0; b < projectile.size(); b++) {
				if (projectile.get(b).hitBox.intersects(lvls.get(0).get(b).get(2))) {
					projectile.remove(b);

				}
			}
			for (int c = 0; c < projectile.size(); c++) {
				if (projectile.get(c).hitBox.intersects(lvls.get(0).get(c).get(1))) {
					projectile.remove(c);

				}
			}
			for (int d = 0; d < projectile.size(); d++) {
				if (projectile.get(d).hitBox.intersects(lvls.get(0).get(d).get(0))) {
					projectile.remove(d);

				}
			}

		}

	}


