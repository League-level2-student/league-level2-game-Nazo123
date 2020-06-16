package JungleExplorer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static BufferedImage himage;
	public static boolean hneedImage = true;
	public static boolean hgotImage = false;
	final int MENU = 0;
	final int INT = 1;
	final int LEVELI = 2;
	final int LEVELII = 3;
	final int LEVELIII = 4;
	final int END = 5;
	int currentState = MENU;
	Timer frameRate;
	Font intTitleFont;
	Font intTextFont;
	Player player;
	ObjectManager manager;
	Rectangle door = new Rectangle(100, 75, 100, 150);

	GamePanel() {
		player = new Player(50, 525, 125, 75, 10, true, true);
		intTitleFont = new Font("Times New Roman", Font.BOLD, 100);
		intTextFont = new Font("Times New Roman", Font.PLAIN, 35);
		frameRate = new Timer(1000 / 60, this);
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
		g.drawString("       To move you must use the a and s keys or the left and right arrow keys. To ", 75, 350);

		g.drawString("jump simply press space. Your goal is to reach the door at the end of the level and", 75, 425);
		g.drawString("presss the up arrow or w. There are obstacles such as projectiles, gaps in the floor", 75, 500);
		g.drawString("and fire. You have 3 attempts to see how far you can go. There are a total of ___levels!", 75,
				575);

	}

	void drawLevelIState(Graphics g) {
	
		g.setColor(Color.RED);
		g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);
		g.setColor(Color.yellow);
		g.fillRect(100, 75, 100, 150);
		g.setColor(Color.GREEN);
		
		g.fillRect(300, 600, 150, 100);
		g.fillRect(0, 700, 450, 100);
		g.fillRect(-20, 0, 50, 900);
		g.fillRect(1370, 0, 50, 900);
		g.fillRect(650, 600, 525, 200);
		g.fillRect(1250,475,120,75);
		g.fillRect(0, 0, 1400,30);
		g.fillRect(725, 350, 350, 50);
		g.fillRect(0, 300, 525, 50);
		g.fillRect(0, 250, 425, 50);
		g.fillRect(0, 200, 325, 50);
		
		for (int i =0;i<player.health;i++) {
			g.drawImage(himage, 1275-100*i, 60, 75,75, null);
			}
		
		player.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, JungleExplorer.WIDTH, JungleExplorer.HEIGHT);

	}

	void updateMenuState() {

	}

	void updateLevelIState() {
  manager.update();
  if(player.lose) {
	  currentState = END;
  }
	}

	void updateEndState() {

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
	}	void hloadImage(String himageFile) {
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
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == INT) {
			drawIntState(g);
		} else if (currentState == LEVELI) {
			drawLevelIState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == LEVELI) {

			updateLevelIState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP&&door.intersects(player.playerHitBox)) {
			currentState++;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU) {
				currentState++;

			} else if (currentState == INT) {
				currentState++;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT&&manager.noLeft == false) {

			player.left();

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT&&manager.noRight == false) {

			player.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !player.ifAir) {

			player.jump();

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if ((e.getKeyCode() == KeyEvent.VK_RIGHT)){
			player.rightSpeed = 0;
		}
		
	
	 if (e.getKeyCode() == KeyEvent.VK_LEFT){
		player.leftSpeed=0;
	}
	}
}
