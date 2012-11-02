import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.peer.KeyboardFocusManagerPeer;
import java.net.URL;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener,
		MouseMotionListener, MouseListener {

	private Image i;
	private Graphics doubleG;
	Ball b;
	Platform p[] = new Platform[7];
	Item item[] = new Item[3];
	private int score;
	double cityX = 0;
	double cityDx = .3;
	URL url;
	Image city;
	int levelcheck = 0;
	boolean gameOver = false;
	boolean mouseIn = false;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void init() {
		setSize(800, 600);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try {
			url = getDocumentBase();
		} catch (Exception e) {

		}
		city = getImage(url, "images/background.png");
		new Pictures(this);
		Pictures.music.loop();

	}

	@Override
	public void start() {
		b = new Ball();
		score = 0;
		for (int i = 0; i < p.length; i++) {
			Random r = new Random();
			p[i] = new Platform(120 * i, 300);

		}
		for (int i = 0; i < item.length; i++) {
			Random r = new Random();
			switch (r.nextInt(5)) {
			case 0:
				item[i] = new GravUp(getWidth() + 10 * i);
				break;
			case 1:
				item[i] = new GravDown(getWidth() + 10 * i);
				break;
			case 2:
				item[i] = new AgilityUp(getWidth() + 10 * i);
				break;
			case 3:
				item[i] = new AgilityDown(getWidth() + 10 * i);
				break;
			case 4:
				item[i] = new ScorePlus(getWidth() + i, this);
				break;

			}
		}

		Thread thread = new Thread(this);
		thread.start();

	}

	public void run() {
		// thread information
		while (true) {
			
//			if (x < 0 - width) {
			for (int i = 0; i < p.length; i++){
				int testx = p[i].getX();
				if (testx < 0 - p[i].getWidth()) {
					Random r = new Random();
					int fakei = i;
					if (i == 0){
						fakei = p.length;
					}
					p[i].setX(p[fakei-1].getX() + p[i].getWidth() + Pictures.level +r.nextInt(25));
				}
			}

			gameOver = b.getGameOver();

			if (levelcheck > 1000) {
				Pictures.level++;
				levelcheck = 0;
			}
			if (cityX > getWidth() * -1) {
				cityX -= cityDx;
			} else {
				cityX = 0;
			}
			levelcheck++;

			if (!gameOver) {
				score++;
			}

			Random r = new Random();

			for (int i = 0; i < item.length; i++) {
				if (item[i].isCreateNew()) {
					item[i] = null;
					switch (r.nextInt(5)) {
					case 0:
						item[i] = new GravUp(getWidth() + 10 * r.nextInt(500));
						break;

					case 1:
						item[i] = new GravDown(getWidth() + 10 * r.nextInt(500));
						break;

					case 2:
						item[i] = new AgilityUp(getWidth() + 10
								* r.nextInt(500));
						break;
					case 3:
						item[i] = new AgilityDown(getWidth() + 10
								* r.nextInt(500));
						break;
					case 4:
						item[i] = new ScorePlus(getWidth() + 10
								* r.nextInt(500), this);
						break;

					}

					item[i].setCreateNew(false);

				}
			}

			b.update(this);
			for (int i = 0; i < p.length; i++) {
				p[i].update(this, b);
			}
			for (int i = 0; i < item.length; i++) {
				item[i].update(this, b);
			}

			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void update(Graphics g) {
		if (i == null) {

			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city, (int) cityX, 0, this);
		g.drawImage(city, (int) cityX + 800, 0, this);

		for (int i = 0; i < p.length; i++) {
			p[i].paint(g);
		}
		for (int i = 0; i < item.length; i++) {
			item[i].paint(g);
		}
		b.paint(g);

		String s = Integer.toString(score);
		Font font = new Font("Serif", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.GRAY);
		g.drawString(s, getWidth() - 152, 52);
		g.setColor(Color.RED);
		g.drawString(s, getWidth() - 150, 50);
		if (gameOver) {
			g.setColor(Color.white);
			g.drawString("GAME OVER", 300, 300);
			g.drawRect(295, 310, 175, 40);
			if (mouseIn) {
				g.setColor(Color.red);
				g.drawString("Play again?", 300, 340);
			} else {
				g.setColor(Color.white);
				g.drawString("Play again?", 300, 340);
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// (275, 310, 180, 40)
		if (gameOver) {
			if (e.getX() > 295 && e.getX() < 475) {
				if (e.getY() > 310 && e.getY() < 350) {
					mouseIn = true;
				}
			}

			if (e.getX() < 295 || e.getX() > 475) {
				if (e.getY() < 310 && e.getY() > 350) {
					mouseIn = false;
				}

			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// start new game
		
		
		if (mouseIn) {
			b = null;
			b = new Ball();
			score = 0;
			Pictures.level = 1;

		}
		for (int i = 0; i < p.length; i++) {
			p[i] = new Platform(120 * i, 300);

		}

		for (int i = 0; i < item.length; i++) {
			Random r = new Random();
			switch (r.nextInt(5)) {
			case 0:
				item[i] = new GravUp(getWidth() + 10 * i);
				break;
			case 1:
				item[i] = new GravDown(getWidth() + 10 * i);
				break;
			case 2:
				item[i] = new AgilityUp(getWidth() + 10 * i);
				break;
			case 3:
				item[i] = new AgilityDown(getWidth() + 10 * i);
				break;
			case 4:
				item[i] = new ScorePlus(getWidth() + i, this);
				break;

			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
