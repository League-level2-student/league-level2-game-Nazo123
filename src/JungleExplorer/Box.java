package JungleExplorer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Box {
	public int x;
	public int y;
	public int width;
	public int height;

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

		Rectangle roof = new Rectangle(x , y, width, height/5 );

		return roof;
	}

	Rectangle createLeftWalls() {

		Rectangle leftWall = new Rectangle(x, y+height/5, width/5 ,height-(2*height/5));
		return leftWall;
	}

	Rectangle createCealing() {

		Rectangle cealing = new Rectangle(x , y+height-(height/5) , width , height / 5);

		return cealing;
	}

	Rectangle createRightWalls() {

		Rectangle rightWall = new Rectangle(x + width -(width/5), y+height/5, width / 5, height-(2*height/5));
		return rightWall;
	}
}