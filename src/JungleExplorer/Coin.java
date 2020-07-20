package JungleExplorer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin {
int x;
int y;
int width;
int height;
Rectangle hitbox;
boolean active;
boolean collected;
	public Coin(int x, int y, int width, int height, boolean active, boolean collected) {
		this.x = x;
		this.y = y;
		this.height= height;
		this.width = width;
		this.active = active;
		this.collected=collected;
		hitbox = new Rectangle(x,y,width,height);
	}
	void draw(Graphics g){
		if (active&&!collected) {
			g.setColor(Color.BLUE);
			g.fillOval(x, y, width, height);
			hitbox.setBounds(x,y,width,height);
			
		}
		else {
			hitbox.setBounds(-100,-100,width,height);
		}
		
	}
	
}
