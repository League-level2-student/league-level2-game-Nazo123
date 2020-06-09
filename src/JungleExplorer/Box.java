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
		theBox.add(createWalls());
		theBox.add(createRoof());
	
		
		return theBox;

	}

	Rectangle createRoof() {

		Rectangle roof = new Rectangle(x + 25, y, width - 25, 20);

		return roof;
	}

	Rectangle createWalls() {

		Rectangle wall = new Rectangle(x, y + 20, width, height - 20);
		return wall;
	}

	Rectangle createCealing() {

		Rectangle cealing = new Rectangle(x + 25, y + height - 20, width-25, 20);

		return cealing;
	}
}