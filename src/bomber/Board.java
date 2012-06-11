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
	Figur bomber1, bomber2;
	public Image img, block0, block1, bomb;
	Timer time;
	public Field spielfeld = new Field();
	boolean ini;
	int m; // movementreichweite

	public Board() {
		m = 5; // movementreichweite
		bomber1 = new Figur(60, 60,1);
		bomber2 = new Figur (60*13,60*9,2);
		addKeyListener(new AL());
		setFocusable(true);

		ImageIcon i1 = new ImageIcon("bilder/feld.png");
		img = i1.getImage();

		ImageIcon i2 = new ImageIcon("bilder/block0.jpg");
		block0 = i2.getImage();

		ImageIcon i3 = new ImageIcon("bilder/block1.jpg");
		block1 = i3.getImage();

		ImageIcon i4 = new ImageIcon("bilder/bombe.jpg");
		bomb = i4.getImage();

		time = new Timer(5, this);
		time.start();

	}

	// Spielfeld größe 15x11
	public void actionPerformed(ActionEvent e) {

		bomber1.Perma(spielfeld);
		bomber2.Perma(spielfeld);
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

				if (spielfeld.getArry(i, j)== 0) {
					g2d.drawImage(block0, i * blocksize, j * blocksize, null);
				} else if (spielfeld.getArry(i, j) == 1) {
					g2d.drawImage(block1, i * blocksize, j * blocksize, null);
				} else if (spielfeld.getArry(i, j) == 10) {
					g2d.drawImage(bomb, i * blocksize, j * blocksize, null);
				}
			}
		}
		g2d.drawImage(bomber1.getImage(), bomber1.getX(), bomber1.getY(), null);
		g2d.drawImage(bomber2.getImage(), bomber2.getX(), bomber2.getY(), null);
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
				bomber1.setBomb(spielfeld);
			
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
