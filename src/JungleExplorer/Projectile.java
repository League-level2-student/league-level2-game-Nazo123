package JungleExplorer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Projectile extends GameObject {
	public static BufferedImage dimage;
	public static boolean dneedImage = true;
	public static boolean dgotImage = false;
	public static BufferedImage uimage;
	public static boolean uneedImage = true;
	public static boolean ugotImage = false;
	public static BufferedImage rimage;
	public static boolean rneedImage = true;
	public static boolean rgotImage = false;
	public static BufferedImage limage;
	public static boolean lneedImage = true;
	public static boolean lgotImage = false;
	int x;
	int y;
	int height;
	int width;
	int speed;
	boolean isVisable;
	boolean isAlive;
	int direction;
	Rectangle hitBox;

	Projectile(int x, int y, int width, int height, int speed, boolean isVisable, boolean isAlive, int direction) {
		super(x, y, width, height, speed, isVisable, isAlive);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.isVisable = isVisable;
		this.isAlive = isAlive;
		this.direction = direction;
		hitBox = new Rectangle(x, y, width, height);

		if (dneedImage) {
			dloadImage("downArrow.png");
		}
		if (rneedImage) {
			rloadImage("rightArrow.png");
		}
		if (lneedImage) {
			lloadImage("leftArrow.png");
		}
		if (uneedImage) {
			uloadImage("upArrow.png");
		}
	}

	void draw(Graphics g) {
		if (direction == 0) {
			//g.drawImage(dimage, x, y, width, height, null);
g.drawRect(x, y, width, height);
		}
		if (direction == 1) {
			//g.drawImage(rimage, x, y, width, height, null);
			g.drawRect(x, y, width, height);
		}
		if (direction == 2) {
			//g.drawImage(uimage, x, y, width, height, null);
			g.drawRect(x, y, width, height);
		}
		if (direction == 3) {
			//g.drawImage(limage, x, y, width, height, null);
			g.drawRect(x, y, width, height);
		}
	}

	void update() {
		if (direction == 0) {
			y += speed;
		}
		if (direction == 1) {
			x += speed;
		}
		if (direction == 2) {
			y -= speed;
		}
		if (direction == 3) {
			x -= speed;
		}
	
		super.update();
		hitBox.setBounds(x, y, width, height);
	}

	void uloadImage(String imageFile) {
		if (uneedImage) {
			try {
				uimage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				ugotImage = true;
			} catch (Exception e) {

			}
			uneedImage = false;
		}
	}

	void dloadImage(String imageFile) {
		if (dneedImage) {
			try {
				dimage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				dgotImage = true;
			} catch (Exception e) {

			}
			dneedImage = false;
		}
	}

	void rloadImage(String imageFile) {
		if (rneedImage) {
			try {
				rimage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				rgotImage = true;
			} catch (Exception e) {

			}
			rneedImage = false;
		}
	}

	void lloadImage(String imageFile) {
		if (lneedImage) {
			try {
				limage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				lgotImage = true;
			} catch (Exception e) {

			}
			lneedImage = false;
		}
	}
}
