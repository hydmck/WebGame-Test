import java.awt.Color;
import java.awt.Graphics;

public class AgilityDown extends Item {

	public AgilityDown(int x) {
		super(x);

	}
	@Override
	public void performAction(Ball b) {
		if (b.getAgility() >= 2 )
		b.setAgility(b.getAgility() -1);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.yellow);
		super.paint(g);
	}

}
// Test Comment

