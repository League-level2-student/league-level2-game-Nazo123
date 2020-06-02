package JungleExplorer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends GameObject{
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public static BufferedImage aimage;
	public static boolean aneedImage = true;
	public static boolean agotImage = false;	
	public int yvelocity = 0;
	int yspeed = 0;
	int xspeed = 0;
	boolean ifAir;
	boolean facingRight =true;
	

Player (int x, int y, int height, int width, int speed, boolean isVisable, boolean isAlive){
	super(x,y,height,width,speed,isVisable,isAlive);
	if (needImage) {
	    loadImage ("Player.png");
	}
	if (aneedImage) {
	    aloadImage ("PlayerLeft.png");
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
		}
		else {
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
if(ifAir) {
		xspeed = -speed*2/3;
	}
	else {
		xspeed = -speed;
	}
	}

	public void right() {
		facingRight = true;
if(ifAir) {
		xspeed = speed*2/3;
}
else {
	xspeed = speed;
}
	}
	
	public void adjustSpeed() {
		if(ifAir) {
			if(xspeed<0) {
				xspeed = -speed*2/3;
			}
			else if (xspeed>0) {
				xspeed = speed*2/3;
			}
		
		}
		else {
		
				if(xspeed<0) {
					xspeed = -speed;
				}
				else if (xspeed>0) {
					xspeed = speed;
				}

		}
	}
	public void jump() {
yvelocity = -28;

	}
	void update() {
adjustSpeed();
if(y<600) {
	ifAir = true;
	System.out.println("IN AIR");
	
}
else {
	ifAir=false;
}
	

		x += xspeed;
		y += yvelocity;
		if(yvelocity==0&ifAir) {
			y= y+8;
		}
		else if (yvelocity<0) {
			
			yvelocity= yvelocity+4;
		}
		super.update();
		
	}
}
