import java.awt.Color;
import java.awt.Graphics;

public class GravUp extends Item {

	public GravUp(int x) {
		super(x);

	}
	@Override
	public void performAction(Ball b) {
		b.setGravity(b.getGravity() + 3);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		super.paint(g);
	}

}
