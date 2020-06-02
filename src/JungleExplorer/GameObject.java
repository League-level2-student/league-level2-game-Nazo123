package JungleExplorer;

public class GameObject {
	int x;
	int y;
	int height;
	int width;
	int speed;
	boolean isVisable;
	boolean isAlive;

	GameObject(int x, int y, int height, int width, int speed, boolean isVisable, boolean isAlive) {
		this.isAlive = isAlive;
		this.isVisable = isVisable;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.y = y;
		this.x = x;

	}

void update() {
	
}
}

