package JungleExplorer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Boss {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	boolean jumping = false;
	boolean justLostLife = false;
	int speed;
	int height;
	int heath;
	int width;
	int x;
	int y;
	int jumpstage=0;
	Rectangle headPart;
	Rectangle bottomPart;
	
	boolean attackDisable;
	public Boss(int x, int y, int width,int height, int speed,int health) {
		this.speed=speed;
		this.height=height;
		this.width=width;
		headPart = new Rectangle(x+10,y,width-20,height/3);
		bottomPart = new Rectangle(x,y+height/3,width,height/3*2);
		if (needImage) {
			loadImage("boss.png");
		}
	}
	
	public void attack(int centerX) {
		if(jumping==false) {
		if(x+width/2>centerX+speed) {
			x=x-speed;
		}
		else if(x+width/2<centerX-speed){
			
		}
		else {
			retreat(x+width/2);
		}
		}
	}
	public void retreat(int centerX) {
		if(jumping==false) {
		if(centerX<700-speed) {
			x=x+speed;
		}
		else if (centerX<700-speed) {
			x=x-speed;
		}
	  else {
		jump();
	  }
		}
	}

	public void jump() {
		if (jumpstage==0) {
			jumping=true;
			y=y+16;
		
		}
		else if (jumpstage==1){
			y=y+12;
		}
		else if (jumpstage==2){
			y=y+8;
		}
		else if (jumpstage==3){
			y=y+4;
		}
		else if (jumpstage==4){
			y=y+2;
		}
		else if (jumpstage==5){
			y=y;
		}
		else if (jumpstage==6){
			y=y-2;
		}
		else if (jumpstage==7){
			y=y-4;
		}
		else if (jumpstage==8){
			y=y-8;
		}
		else if (jumpstage==9){
			y=y-12;
		}
		else if (jumpstage==10){
			y=y-16;
			jumpstage=0;
			jumping=false;
		}
		if(jumping) {
		jumpstage++;
		}
	}
	public void update() {
		
		
		
		
		headPart.setBounds(x+10,y,width-20,height/3);
		bottomPart.setBounds(x,y+height/3,width,height/3*2);
		
		
		
		
		
		
		
	}
	void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
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
	
}
