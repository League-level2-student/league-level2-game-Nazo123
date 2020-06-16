package JungleExplorer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Box {
	int x;
	int y;
	int width;
	int height;

	
	ArrayList<Rectangle> theBox;

	ArrayList<Rectangle> createBox(int x, int y, int width, int height) {
		theBox = new ArrayList<Rectangle>();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		theBox.add(createCealing());
		theBox.add(createLeftWalls());
		theBox.add(createRoof());
		theBox.add(createRightWalls());
	
		
		return theBox;

	}

	Rectangle createRoof() {

		Rectangle roof = new Rectangle(x + 25, y, width - 35, height/5);

		return roof;
	}

	Rectangle createLeftWalls() {

		Rectangle leftWall = new Rectangle(x, y+height/5*2, width/2, height - height/5*2);
		return leftWall;
	}

	Rectangle createCealing() {

		Rectangle cealing = new Rectangle(x + 25, y + height - 20, width-35, height/5);

		return cealing;
	}
	Rectangle createRightWalls() {

		Rectangle rightWall = new Rectangle(x+width/2, y +height/5*2, width/2, height - height/5*2);
		return rightWall;
	}
}