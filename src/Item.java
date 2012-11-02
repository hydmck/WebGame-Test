import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Item {

	private int x, y, dx, radius;
	private StartingPoint sp;
	private boolean createNew = false;

	public boolean isCreateNew() {
		return createNew;
	}

	public void setCreateNew(boolean createnew) {
		this.createNew = createnew;
	}

	public Item(int x) {
		this.x = x;
		Random r = new Random();
		y = r.nextInt(400) + radius;
		radius = 10;
		dx = -2;

	}

	public void update(StartingPoint sp, Ball b) {
		x += dx;
		this.sp = sp;
		checkForCollision(b);
		if (x < 0 - radius) {
			createNew = true;

		}

	}

	private void checkForCollision(Ball b) {
		int ballX = b.getX();
		int ballY = b.getY();
		int ballR = b.getRadius();

		int a = x - ballX;
		int bb = y - ballY;
		int collide = radius + ballR;
		// distance between object centers
		double c = Math.sqrt((double) a * a + (double) bb * bb);

		if (c < collide) {
			performAction(b);
			createNew = true;

		}

	}

	public void performAction(Ball b) {

	}

	public void paint(Graphics g) {

		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);

	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
