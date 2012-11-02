import java.awt.Color;
import java.awt.Graphics;

public class AgilityUp extends Item {

	public AgilityUp(int x) {
		super(x);

	}
	@Override
	public void performAction(Ball b) {
		if (b.getAgility() < 8 )
		b.setAgility(b.getAgility() + 1);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		super.paint(g);
	}

}
