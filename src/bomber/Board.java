package bomber;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	Figur bomber1;
	public Image img, block0, block1, bomb;
	Timer time;
	int[][] feld = new int[15][11];
	boolean ini;
	int m; // movementreichweite

	public Board() {
		// Initialisiere Feld
		for (int in = 0; in < 15; in++) {
			for (int jn = 0; jn < 11; jn++) {

				if (in == 0 || jn == 0 || in == 14 || jn == 10
						|| (in % 2 == 0 && jn % 2 == 0))
					feld[in][jn] = 1;
				else
					feld[in][jn] = 0;
			}
		}
		m = 5; // movementreichweite
		bomber1 = new Figur(60, 60);
		addKeyListener(new AL());
		setFocusable(true);

		ImageIcon i1 = new ImageIcon("feld.png");
		img = i1.getImage();

		ImageIcon i2 = new ImageIcon("block0.jpg");
		block0 = i2.getImage();

		ImageIcon i3 = new ImageIcon("block1.jpg");
		block1 = i3.getImage();

		ImageIcon i4 = new ImageIcon("bombe.jpg");
		bomb = i4.getImage();

		time = new Timer(5, this);
		time.start();

	}

	// Spielfeld größe 15x11
	public void actionPerformed(ActionEvent e) {
		// damit immer die position nachgefragt wird
		int x1 = bomber1.getX();
		int y1 = bomber1.getY();
		int dx1 = bomber1.getdx();
		int dy1 = bomber1.getdy();

		// Hauptarray der Figur
		int x1feld = (x1 + 30) / 60;
		int y1feld = (y1 + 30) / 60;

		// Abfrage rechts links
		if (dx1 != 0) {
			if (y1 % 60 > 0 && y1 % 60 < 30) { // Zentralisierung
				bomber1.setdy(-m);
				bomber1.moveUD();
				bomber1.setdy(0);
			} else if (y1 % 60 >= 30 && y1 % 60 < 60) {
				bomber1.setdy(m);
				bomber1.moveUD();
				bomber1.setdy(0);
			} else if (y1 % 60 == 0) {
				// Movement
				if (dx1 > 0) {
					if (feld[x1feld + 1][y1feld] == 0 || x1 % 60 != 0) {
						bomber1.moveLR();
					}

				} else if (dx1 < 0) {
					if (feld[x1feld - 1][y1feld] == 0 || x1 % 60 != 0) {
						bomber1.moveLR();
					}
				}
			}
		}
		// Abfrage unten oben
		if (dy1 != 0) {
			if (x1 % 60 > 0 && x1 % 60 < 30) { // Zentralisierung
				bomber1.setdx(-m);
				bomber1.moveLR();
				bomber1.setdx(0);
			} else if (x1 % 60 >= 30 && x1 % 60 < 60) {
				bomber1.setdx(m);
				bomber1.moveLR();
				bomber1.setdx(0);
			} else if (x1 % 60 == 0) {
				// Movement
				if (dy1 > 0) {
					if (feld[x1feld][y1feld + 1] == 0 || y1 % 60 != 0) {
						bomber1.moveUD();
					}

				} else if (dy1 < 0) {
					if (feld[x1feld][y1feld - 1] == 0 || y1 % 60 != 0) {
						bomber1.moveUD();
					}
				}
			}
		}

		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		// Feld wird gemalt
		// 0=block0(laufweg) 1=block1(fester block) 10=bombe
		g2d.drawImage(img, 0, 0, null);
		int blocksize = 60;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 11; j++) {

				if (feld[i][j] == 0) {
					g2d.drawImage(block0, i * blocksize, j * blocksize, null);
				} else if (feld[i][j] == 1) {
					g2d.drawImage(block1, i * blocksize, j * blocksize, null);
				} else if (feld[i][j] == 10) {
					g2d.drawImage(bomb, i * blocksize, j * blocksize, null);
				}
			}
		}
		g2d.drawImage(bomber1.getImage(), bomber1.getX(), bomber1.getY(), null);
	}

	public void setBomb(int x, int y, int radi) {
		// setzt den feld array auf 10=Bombe; 30 für mittelpunkt
		int meinfeld = feld[(30 + x) / 60][(30 + y) / 60];
		if (meinfeld == 0)
			feld[(30 + x) / 60][(30 + y) / 60] = 10;

	}

	// KEY ABFRAGE

	private class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				bomber1.setdx(-m);

			} else if (key == KeyEvent.VK_RIGHT) {
				bomber1.setdx(m);

			} else if (key == KeyEvent.VK_UP) {
				bomber1.setdy(-m);
			} else if (key == KeyEvent.VK_DOWN) {
				bomber1.setdy(m);
			} else if (key == KeyEvent.VK_SPACE) {
				bomber1.setBomb();
				setBomb(bomber1.getX(), bomber1.getY(), bomber1.getRadi());
			}
		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT)
				bomber1.setdx(0);
			if (key == KeyEvent.VK_RIGHT)
				bomber1.setdx(0);

			if (key == KeyEvent.VK_UP)
				bomber1.setdy(0);

			if (key == KeyEvent.VK_DOWN)
				bomber1.setdy(0);
		}
	}
}
