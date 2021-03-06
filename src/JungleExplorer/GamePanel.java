package JungleExplorer;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	ArrayList<projectileLauncher> launchers = new ArrayList<projectileLauncher>();
	public static ArrayList<hotPad> pads = new ArrayList<hotPad>();
	public static ArrayList<Coin> coins = new ArrayList<Coin>();
	public static ArrayList<Boss> boss = new ArrayList<Boss>();
	public static BufferedImage aimage;
	public static boolean aneedImage = true;
	public static int highScore = 0;
	public static boolean agotImage = false;
	int firstRun = 0;
	private AudioClip sound;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static BufferedImage himage;
	public static boolean hneedImage = true;
	public static boolean hgotImage = false;
	public static int counter = 0;
	public static Rectangle key = new Rectangle(50, 75, 25, 50);
	public static boolean gotKey = false;
	int doorX = 30;
	boolean win = false;
	int doorY = 60;
	Timer bulletTimer;
	final static int MENU = 0;
	final static int INT = 1;
	final static int LEVELI = 2;
	final static int LEVELII = 3;
	final static int LEVELIII = 4;
	final static int LEVELIV = 5;
	final static int LEVELV = 6;
	final static int END = 7;
	int score = 0;
	static int currentState = MENU;
	Timer frameRate;
	Font intTitleFont;
	Font intTextFont;
	Font intScoreFont;
	Font deathFont;
	Player player;
	ObjectManager manager;
	Rectangle door = new Rectangle(doorX, doorY, 100, 150);

	GamePanel() {
		coins.add(new Coin(1240, 110, 50, 50, true, false));
		coins.add(new Coin(60, 250, 50, 50, false, false));
		player = new Player(50, 525, 125, 75, 10, true, true);
		intTitleFont = new Font("Times New Roman", Font.BOLD, 100);
		intTextFont = new Font("Times New Roman", Font.PLAIN, 30);
		intScoreFont = new Font("Times New Roman", Font.BOLD, 45);
		deathFont = new Font("Courier", Font.BOLD, 70);
		frameRate = new Timer(1000 / 60, this);
		bulletTimer = new Timer(2000, this);
		frameRate.start();
		manager = new ObjectManager(player);
		if (needImage) {
			loadImage("Test2.png");
		}
		if (hneedImage) {
			hloadImage("Health.png");
		}

	}

	void drawMenuState(Graphics g) {

		if (gotImage) {
			g.drawImage(image, 0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT, null);
		} else {

			g.setColor(Color.GREEN);
			g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
		}

	}

	void drawIntState(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
		g.setFont(intTitleFont);
		g.setColor(Color.WHITE);
		g.drawString("How To Play", 400, 225);
		g.setFont(intTextFont);
		g.drawString("       To move use the or the left and right arrow keys. To jump simply press space.", 50, 325);

		g.drawString("Your goal is to reach the door at the end of the level and presss the up arrow. There are obstacles", 50, 400);
		g.drawString("such as projectiles, gaps in the floor and fire hot rocks that kill you while red. One level includes a", 50, 475);
		g.drawString("black switch. Pushing it will cause new blocks to apear, and old ones to be removed. At the end there is a boss. ", 50,550);
		g.drawString("To kill it you must jump on his head repededly. You have limmited lives to see how far you can go.", 50,625);
		g.drawString("There are a total of 5 levels! Throught the game there are secret coins to help boost your score.", 50,700);
	}
	
		

	void drawLevelIState(Graphics g) {
		
		g.setColor(Color.lightGray);
		g.drawImage(aimage, 0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT, null);
		g.setColor(Color.yellow);
		g.fillRect(doorX, doorY, 100, 150);
		g.setColor(Color.GREEN);

		g.fillRect(300, 600, 150, 100);
		g.fillRect(0, 700, 450, 100);
		g.fillRect(-20, 0, 50, 900);
		g.fillRect(1370, 0, 50, 900);
		g.fillRect(650, 650, 525, 200);
		g.fillRect(1250, 500, 120, 75);
		g.fillRect(0, 0, 1400, 30);
		g.fillRect(725, 350, 350, 50);
		g.fillRect(0, 300, 525, 50);
		g.fillRect(0, 250, 425, 50);
		g.fillRect(0, 200, 325, 50);
		coins.get(0).draw(g);
		for (int i = 0; i < player.health; i++) {
			g.drawImage(himage, 1275 - 100 * i, 60, 75, 75, null);
		}

		player.draw(g);
g.setFont(intScoreFont);
g.setColor(Color.blue);
g.drawString("Score: "+score, 10, 40);
	}

	void drawLevelIIState(Graphics g) {

		g.setColor(Color.lightGray);
		g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
		g.setColor(Color.yellow);
		g.fillRect(doorX, doorY, 100, 150);
		g.setColor(Color.GREEN);

		g.fillRect(0, 700, 400, 50);
		g.fillRect(400, 600, 100, 200);
		g.fillRect(900, 620, 130, 200);
		g.fillRect(400, 700, 500, 50);
		g.fillRect(1000, 700, 400, 50);
		g.fillRect(0, 0, 1400, 30);
		g.fillRect(0, 0, 30, 700);
		g.fillRect(1370, 0, 30, 700);
		g.fillRect(1300, 575, 100, 125);
		g.fillRect(1100, 425, 50, 50);
		g.fillRect(1320, 320, 50, 50);
		g.fillRect(1000, 180, 150, 50);
		g.fillRect(0, 220, 1150, 60);

		g.setColor(Color.gray);
		g.fillRect(900, 650, 20, 30);
		g.fillRect(1175, 700, 30, 20);
		g.fillRect(225, 220, 30, 20);
		g.fillRect(491, 220, 30, 20);
		g.fillRect(758, 220, 30, 20);

		for (int i = 0; i < player.health; i++) {
			g.drawImage(himage, 1275 - 100 * i, 60, 75, 75, null);
		}

		player.draw(g);
		g.setFont(intScoreFont);
		g.setColor(Color.blue);
		g.drawString("Score: "+score, 10, 40);
		for (int i = 0; i < manager.projectile.size(); i++) {
			manager.projectile.get(i).draw(g);

		}
	}

	void drawLevelIIIState(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
		g.setColor(Color.yellow);
		g.fillRect(doorX, doorY, 100, 150);
		g.setColor(Color.GREEN);
		g.fillRect(0, 700, 400, 50);
		g.fillRect(0, -500, 1400, 530);
		g.fillRect(-20, -500, 50, 1900);
		g.fillRect(1370, -1000, 30, 2700);
		g.fillRect(500, 650, 270, 150);
		g.fillRect(1220, 615, 50, 50);
		g.fillRect(1130, 330, 50, 50);
		g.fillRect(150, 200, 900, 50);

		g.setColor(Color.gray);

		for (int i = 0; i < pads.size(); i++) {
			if (pads.get(i).active) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.ORANGE);
			}
			g.fillRect(pads.get(i).x, pads.get(i).y, pads.get(i).width, pads.get(i).height);
		}
		coins.get(1).draw(g);
		for (int i = 0; i < player.health; i++) {
			g.drawImage(himage, 1275 - 100 * i, 60, 75, 75, null);
		}

		player.draw(g);
		g.setFont(intScoreFont);
		g.setColor(Color.blue);
		g.drawString("Score: "+score, 10, 40);
		for (int i = 0; i < manager.projectile.size(); i++) {
			manager.projectile.get(i).draw(g);

		}
	}

	void drawLevelIVState(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
		g.setColor(Color.yellow);
		g.fillRect(doorX, doorY, 100, 150);
		g.setColor(Color.GREEN);
		if (gotKey) {

			g.fillRect(50, 650, 350, 50);

		} else {
			g.setColor(Color.black);
			g.fillRect(50, 75, 25, 50);
			g.setColor(Color.GREEN);
			g.fillRect(1250 - 10, 0, 50, 200);
			g.fillRect(0, 120, 300, 25);
		}
		g.fillRect(750, 450, 110, 80);
		g.fillRect(650, 700, 750, 100);
		g.fillRect(350, 650, 50, 50);
		g.fillRect(50, 600, 50, 50);
		g.fillRect(0, 450, 50, 50);
		g.fillRect(425, 275, 50, 50);
		g.fillRect(750, 526, 550, 50);
		g.fillRect(750, 0, 40, 536);
		g.fillRect(1390, -200, 300, 1000);
		g.fillRect(950, 300, 150, 50);
		g.fillRect(1100, 250, 150, 100);
		g.fillRect(1240, 200, 210, 150);

		g.setColor(Color.gray);

		g.fillRect(890, 526, 20, 30);
		g.fillRect(1040, 526, 20, 30);
		g.fillRect(1190, 526, 20, 30);

		for (int i = 0; i < pads.size(); i++) {
			if (pads.get(i).active) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.ORANGE);
			}
			g.fillRect(pads.get(i).x, pads.get(i).y, pads.get(i).width, pads.get(i).height);
		}
		coins.get(1).draw(g);
		for (int i = 0; i < player.health; i++) {
			g.drawImage(himage, 1275 - 100 * i, 60, 75, 75, null);
		}

		player.draw(g);
		g.setFont(intScoreFont);
		g.setColor(Color.blue);
		g.drawString("Score: "+score, 10, 40);
		for (int i = 0; i < manager.projectile.size(); i++) {
			manager.projectile.get(i).draw(g);

		}
	}

	void drawLevelVState(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect(0, 700, 1400, 100);
		g.setColor(Color.yellow);
		g.fillRect(doorX, doorY, 100, 150);
		if (boss.size() > 0) {
			boss.get(0).draw(g);
			g.setColor(Color.red);
			g.fillRect(boss.get(0).x, boss.get(0).y - 30, boss.get(0).width, 15);
			g.setColor(Color.green);
			g.fillRect(boss.get(0).x, boss.get(0).y - 30, (boss.get(0).width / 25) * boss.get(0).heath, 15);

		}
		for (int i = 0; i < player.health; i++) {
			g.drawImage(himage, 1275 - 100 * i, 60, 75, 75, null);
		}

		player.draw(g);
		g.setFont(intScoreFont);
		g.setColor(Color.blue);
		g.drawString("Score: "+score, 10, 40);
	}

	void drawEndState(Graphics g) {

		g.setFont(deathFont); 
		if (win) {
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
			g.setColor(Color.BLACK);
			g.drawString("You Scored " + score + " This Run!", 25, 200);
			g.drawString("The Current Highscore Is " + highScore + "!", 25, 400);
			g.drawString("Click Enter To Play Again!", 190, 600);
		} else {
			g.setColor(Color.RED);
			g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
			g.setColor(Color.BLACK);
			g.drawString("You FAILED!", 525, 200);
			g.drawString("Better Luck Next Time!", 250, 400);
			g.drawString("Double Click Enter To Play Again!", 25, 600);
		}

	}

	void updateMenuState() {

	}

	void updateLevelIState() {
	

doorX = 30;
doorY=60;
		manager.update();
		if(firstRun==0) {
			player.health=3;
			firstRun=1;
		}
		if (player.lose) {
			currentState = END;
		}
	}

	void updateLevelIIState() {
		manager.update();
		if (player.lose) {
			currentState = END;
		}
	}

	void updateLevelIIIState() {
		manager.update();
		if (player.lose) {
			currentState = END;
		}
	}

	void updateLevelIVState() {
		manager.update();
		if (player.lose) {
			currentState = END;
		}
	}

	void updateLevelVState() {
		manager.update();
		if (player.lose) {
			currentState = END;
		}
		if (boss.size() > 0) {
			if (boss.get(0).heath == 0) {
				boss.clear();
				win = true;
				player.x = 100;
				player.y = 600;
				doorX = 650;
				doorY = 550;
			}
		}
	}

	void updateEndState() {
sound.stop();
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	void hloadImage(String himageFile) {
		if (hneedImage) {
			try {
				himage = ImageIO.read(this.getClass().getResourceAsStream(himageFile));
				hgotImage = true;
			} catch (Exception e) {

			}
			hneedImage = false;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == INT) {
			drawIntState(g);
		} else if (currentState == LEVELI) {
			drawLevelIState(g);
		} else if (currentState == LEVELII) {
			drawLevelIIState(g);
		} else if (currentState == LEVELIII) {
			drawLevelIIIState(g);
		} else if (currentState == LEVELIV) {
			drawLevelIVState(g);
		} else if (currentState == LEVELV) {
			drawLevelVState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub'

		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == LEVELI) {

			updateLevelIState();
		} else if (currentState == LEVELII) {
			updateLevelIIState();
		} else if (currentState == LEVELIII) {
			updateLevelIIIState();
		} else if (currentState == LEVELIV) {
			updateLevelIVState();
		} else if (currentState == LEVELV) {
			updateLevelVState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();
		counter++;
		if(currentState!=END) {
		score = 40000-counter*3 + player.health * 2000;
		if (coins.get(0).collected) {
			score = score + 7500;

		}
		if (coins.get(1).collected) {
			score = score + 17500;
		}
		if(score<=0) {
			score = 0;
		}
		}
		if (counter % 145 == 0 && LEVELV != currentState&&END!=currentState) {
			playGameTheme();
		} else if (counter % 260 == 0 && LEVELV == currentState) {
			playBossTheme();
		}
		door.setBounds(doorX, doorY, 100, 150);
		for (int i = 0; i < launchers.size(); i++) {
			if (counter % launchers.get(i).rate == 0) {
				manager.addProjectile(launchers.get(i).getProjectile());
			}
		}
		for (int i = 0; i < pads.size(); i++) {
			if (counter % pads.get(i).rate == 0) {
				pads.get(i).active = true;
				pads.get(i).holder = pads.get(i).length;

			} else {
				if (pads.get(i).holder < 0) {

					pads.get(i).active = false;
					pads.get(i).holder--;
				} else {
					pads.get(i).active = true;
					pads.get(i).holder--;
				}
			}

		}

	}

	public void playGameTheme() {
		try {
			sound = JApplet.newAudioClip(getClass().getResource("level.wav"));
			sound.stop();
			sound.play();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void playBossTheme() {
		try {
			sound = JApplet.newAudioClip(getClass().getResource("boss.wav"));
			sound.stop();
			sound.play();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_UP && door.intersects(player.playerHitBox)) {
			player.health++;
			currentState++;
			sound.stop();
			sound.play();
			manager.projectile.clear();
			manager.lvls.remove(0);
			launchers.clear();
			pads.clear();
			if (currentState == LEVELII) {
				coins.get(0).active = false;
				doorY = doorY + 10;
				player.x = 100;
				player.y = 600;
				manager.lvls.add(manager.createLevelII());
				launchers.add(new projectileLauncher(900, 650, 20, 30, 3, 9, 113));
				launchers.add(new projectileLauncher(1175, 700, 30, 20, 2, 16, 92));
				launchers.add(new projectileLauncher(225, 225, 30, 20, 2, 6, 76));
				launchers.add(new projectileLauncher(491, 230, 30, 20, 2, 14, 54));
				launchers.add(new projectileLauncher(758, 230, 30, 20, 2, 11, 86));

			}
			if (currentState == LEVELIII) {
				doorX = 537;
				doorY = 60;
				coins.get(1).active=true;
				pads.add(new hotPad(275, 700, 125, 15, 150, 25));
				launchers.add(new projectileLauncher(440, 800, 30, 20, 2, 10, 55));
				pads.add(new hotPad(1000, 675, 50, 50, 200, 20));
				pads.add(new hotPad(1300, 475, 70, 50, 70, 10));
				pads.add(new hotPad(150, 200, 125, 20, 100, 50));
				pads.add(new hotPad(275, 200, 125, 20, 300, 150));

				pads.add(new hotPad(650, 200, 125, 20, 100, 50));
				pads.add(new hotPad(775, 200, 125, 20, 200, 100));
				player.x = 30;
				player.y = 600;
				manager.lvls.add(manager.createLevelIII());

			}
			if (currentState == LEVELIV) {
				
				coins.get(1).active = false;
				player.x = 700;
				player.y = 600;
				doorX = 1300 - 10;
				doorY = 50;
				launchers.add(new projectileLauncher(215, 800, 20, 20, 2, 10, 87));
				manager.lvls.add(manager.createLevelIV());
				pads.add(new hotPad(325, 375, 50, 50, 150, 25));
				pads.add(new hotPad(800, 700, 100, 50, 200, 50));
				pads.add(new hotPad(1000, 700, 100, 50, 100, 25));
				pads.add(new hotPad(1200, 700, 100, 50, 200, 50));
				launchers.add(new projectileLauncher(890, 526, 20, 30, 2, 12, 79));
				launchers.add(new projectileLauncher(1040, 526, 20, 30, 2, 16, 57));
				launchers.add(new projectileLauncher(1190, 526, 20, 30, 2, 13, 68));

			}
			if (currentState == LEVELV) {
			
				player.x = 650;
				player.y = 600;
				if(boss.size()==0) {
				boss.add(new Boss(1100, 550, 200, 150, 4, 25));
				}
				else {
					boss.get(0).heath = 0;
				}
				manager.lvls.add(manager.createLevelV());
				doorX = -900;
				doorY = -900;

			}
			if (currentState == END) {
				
				}
			if(win) {
				if (highScore < score) {
					highScore = score;
				}
			}
			}

		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU) {
				currentState++;

			} else if (currentState == INT) {
				currentState++;
				counter=0;
			
			} else if (currentState == END) {
				manager.projectile.clear();
				pads.clear();
				launchers.clear();
				win=false;
				gotKey=false;
				
				for(int i = 0;i<coins.size();i++) {
					coins.get(i).active = false;
					coins.get(i).collected=false;
				}

				coins.get(0).active = true;
				
				currentState = LEVELI;
				
				firstRun=0;
				player = new Player(50, 525, 125, 75, 10, true, true);
				player.lose=false;
				counter = 0;
				
				score = 0;
				key.setBounds(50, 75, 25, 50);
				doorX=30;
				doorY=60;
				manager = new ObjectManager(player);

			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			player.left();

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			player.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !player.ifAir) {

			player.jump();

		}

	}

	private Object boss(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if ((e.getKeyCode() == KeyEvent.VK_RIGHT)) {
			player.rightSpeed = 0;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.leftSpeed = 0;
		}
	}
}
