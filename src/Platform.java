import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Platform {
	int dx;
	int x, y, width, height;
	Image plat;
	URL url;
	float frame = 0;

	public Platform() {
		dx = -10;
		x = 300;
		y = 300;
		width = 120;
		height = 40;

	}

	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 120;
		this.height = 40;
		this.dx = -1;
		plat = Pictures.platform;

	}

	public void update(StartingPoint sp, Ball b) {
		int tester = (int) (frame + .1);
		if (tester < 3) {
			frame += .1;
		} else {
			frame = 0;
		}

		x += - (Pictures.level);
		checkForCollision(b);
		if (x < 0 - width) {
			Random r = new Random();
			y = sp.getHeight() - 40 - r.nextInt(400);
			x = sp.getWidth() + r.nextInt(200);

		}

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void checkForCollision(Ball b) {
		int ballX = b.getX();
		int ballY = b.getY();
		int radius = b.getRadius();

		if (ballY + radius > y && ballY + radius < y + height) {
			if (ballX > x && ballX < x + width) {
				double newDY = b.getGameDy();
				b.setY(y - radius);
				b.setDy(newDY);
				Pictures.bounce.play();
			}
		}

	}

	public void paint(Graphics g) {
		g.drawImage(plat, x, y, x + width, y + height, 0, 40 * (int) frame,
				120, 40 * (int) frame + 40, Pictures.sp);

	}

}
