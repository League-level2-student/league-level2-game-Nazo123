package JungleExplorer;

public class projectileLauncher {
	int x;
	int y;
	int width;
	int height;
	int direction;
	int speed;

	projectileLauncher(int x, int y, int width, int height, int direction, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.direction = direction;
		this.speed = speed;

	}

	public Projectile getProjectile() {
		if (direction == 0) {
			return new Projectile(x + width / 2 - 5, y + height + 80, 20, 80, speed, true, true, 0);
		}
		if (direction == 1) {
			return new Projectile(x + width, y + height / 2, 80, 20, speed, true, true, 1);
		}
		if (direction == 2) {
			return new Projectile(x + width / 2-10, y-80, 20, 80, speed, true, true, 2);
		}
		if (direction == 3) {
			return new Projectile(x - 80, y + height / 2+10 , 80, 20, speed, true, true, 3);
		}
		return null;

	}

}
