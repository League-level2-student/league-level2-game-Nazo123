package JungleExplorer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
  
public class Boss {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	boolean jumping = false;
	boolean justLostLife = false;
	Random movment= new Random();
	int speed;
	int height;
	int heath;
	int width;
	int x;
	int y;
	int jumpstage=0;
	Rectangle headPart;
	Rectangle bottomPart;
	int lostLifeCounter=0;
	
	boolean attackDisable;
	public Boss(int x, int y, int width,int height, int speed,int health) {
		this.speed=speed;
		this.height=height;
		this.width=width;
		this.x=x;
		this.heath=health;
		this.y=y;
		headPart = new Rectangle(x+10,y,width-20,height/3);
		bottomPart = new Rectangle(x,y+height/3,width,height/3*2);
		if (needImage) {
			loadImage("boss.png");
		}
	}
	
	public void attack(int centerX) {
		if(jumping==false) {
		if(x+width/2>centerX+30) {
			x=x-speed;
		}
		else if(x+width/2<centerX-30){
			 x=x+speed;
		}
		else {
			retreat(x+width/2);
		}
		}
	}
	public void retreat(int centerX) {
		if(jumping==false) {
		if(centerX>700+30) {
			x=x-speed;
		}
		else if (centerX<700-30) {
			x=x+speed;
		}
	  
		}
	}
	public void Corner() {
		if(jumping==false) {
		if(x+width/2<=700) {
			x=x-speed*2;
		}
		else if (x+width/2>700) {
			x=x+speed*2;
		}
	 
		}
	}

	public void jump() {
		if (jumpstage==0) {
			jumping=true;
			y=y-30;
		
		}
		else if (jumpstage==1){
			y=y-24;
		}
		else if (jumpstage==2){
			y=y-20;
		}
		else if (jumpstage==3){
			y=y-16;
		}
		else if (jumpstage==4){
			y=y-12;
		}
		else if (jumpstage==5){
			y=y-10;
		}
		else if (jumpstage==6){
			y=y-8;
		}
		else if (jumpstage==7){
			y=y-6;
		}
		else if (jumpstage==8){
			y=y-4;
		}
		else if (jumpstage==9){
			y=y-2;
		}
		else if (jumpstage==10){
		
		}else if (jumpstage==11){
			y=y+2;
		}
		else if (jumpstage==12){
			y=y+4;
		}
		else if (jumpstage==13){
			y=y+6;
		}
		else if (jumpstage==14){
			y=y+8;
		}
		else if (jumpstage==15){
			y=y+10;
		}
		else if (jumpstage==16){
			y=y+12;
		}
		else if (jumpstage==17){
			y=y+16;
		}
		else if (jumpstage==18){
			y=y+20;
		}
		else if (jumpstage==19){
			y=y+24;
		}
		else if (jumpstage==20){
			y=y+30;
		jumping=false;
		jumpstage=0;
		}

		if(jumping) {
		jumpstage++;
		}
	}
	public void update() {
	int move = movment.nextInt(1000);
	if(justLostLife&&lostLifeCounter<40) {
		Corner();
		lostLifeCounter++;
	}
		
	else {
		lostLifeCounter = 0;
		justLostLife=false;
	if(jumping) {
		jump();
	}
	else {
	if(move<700) {
		attack(Player.x+Player.width/2);
	}
	else if(move<990) {
		attack(Player.x+Player.width/2);
	}
	else {
		jump();
	}
		
	}
	}
		
		
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
