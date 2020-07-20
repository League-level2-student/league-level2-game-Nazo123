package JungleExplorer;

import java.awt.Rectangle;

public class hotPad {
	int x;
	int y;
	int width;
	int height;
	int rate;
	Rectangle hitbox;
	boolean active;
	int length;
	int holder;


	hotPad(int x, int y, int width, int height,int rate,int length) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		hitbox = new Rectangle(x,y,width,height);
		this.rate = rate;
		active = true;
		this.length=length;
		holder = this.length;

	}

	

	}

