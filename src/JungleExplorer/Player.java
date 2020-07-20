package JungleExplorer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static BufferedImage aimage;
	public static boolean aneedImage = true;
	public static boolean agotImage = false;
	public int yvelocity = 0;
	int yspeed = 0;
	int rightSpeed = 0;
	int leftSpeed = 0;
	boolean ifAir = true;
	boolean facingRight = true;
	public boolean lose = false;
	static int x;
	static int y;
	int height;
	int width;
	int speed;
	boolean isVisable;
	boolean isAlive;
	Rectangle playerHitBox;
	public static int health = 3;

	Player(int x, int y, int height, int width, int speed, boolean isVisable, boolean isAlive) {
		super(x, y, height, width, speed, isVisable, isAlive);
		playerHitBox = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.speed = speed;
		this.isVisable = isVisable;
		this.isAlive = isAlive;
		

		if (needImage) {
			loadImage("Player.png");
		}
		if (aneedImage) {
			aloadImage("PlayerLeft.png");
		}

	}

	void draw(Graphics g) {
		if (facingRight) {
			if (gotImage) {
				g.drawImage(image, x, y, width, height, null);
			} else {
				g.setColor(Color.BLUE);
				g.fillRect(x, y, width, height);
			}
		} else {
			if (agotImage) {
				g.drawImage(aimage, x, y, width, height, null);
			} else {
				g.setColor(Color.BLUE);
				g.fillRect(x, y, width, height);
			}
		}
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

	void aloadImage(String imageFile) {
		if (aneedImage) {
			try {
				aimage = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				agotImage = true;
			} catch (Exception e) {

			}
			aneedImage = false;
		}
	}

	public void left() {
		facingRight = false;
		if (ifAir) {
			leftSpeed = -speed * 4 / 3;
		} else {
			leftSpeed = -speed;
		}
	}

	public void right() {
		facingRight = true;
		if (ifAir) {
			rightSpeed = speed * 4 / 3;
		} else {
			rightSpeed = speed;
		}
	}

	public void adjustSpeed() {
		if (ifAir) {
			if (leftSpeed < 0) {
				leftSpeed = -speed * 4 / 3;
			} else if (rightSpeed > 0) {
				rightSpeed = speed * 4 / 3;
			}

		} else {
			if (leftSpeed < 0) {
				leftSpeed = -speed;
			} else if (rightSpeed > 0) {
				rightSpeed = speed;
			}

		}
	}

	public void jump() {
		yvelocity = -36;

	}

	void update() {

		adjustSpeed();

		x += rightSpeed + leftSpeed;
		y += yvelocity;
		if (yvelocity == 0 & ifAir) {
			y = y + 12;

		} else if (yvelocity < 0) {

			yvelocity = yvelocity + 4;
		}

		playerHitBox.setBounds(x, y, width, height);
		if (y > 700) {
			x = 100;
			y = 400;
			health--;
		}
		if (health == 0) {
			lose = true;
		}

	}

}
