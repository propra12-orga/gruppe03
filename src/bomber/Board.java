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
	private Figur bomber1, bomber2;
	private Image img, block0, block1, block2, bomb, explo;
	private Timer time;
	private  Field spielfeld = new Field();
	private int m; // movementreichweite

	public Board() {
		m = 5; // movementreichweite
		bomber1 = new Figur(60, 60, 1);
		bomber2 = new Figur(60 * 13, 60 * 9, 2);
		addKeyListener(new AL());
		setFocusable(true);

		ImageIcon i1 = new ImageIcon("bilder/feld.png");
		img = i1.getImage();

		ImageIcon i2 = new ImageIcon("bilder/block0.jpg");
		block0 = i2.getImage();

		ImageIcon i3 = new ImageIcon("bilder/block1.jpg");
		block1 = i3.getImage();

		ImageIcon i3a = new ImageIcon("bilder/block2.jpg");
		block2 = i3a.getImage();

		ImageIcon i4 = new ImageIcon("bilder/blauebombe.png"); // bombe.jpg
		bomb = i4.getImage();

		ImageIcon i5 = new ImageIcon("bilder/explosion.jpg");
		explo = i5.getImage();

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

				if (spielfeld.getArry(i, j) == 0) {
					g2d.drawImage(block0, i * blocksize, j * blocksize, null);
				} else if (spielfeld.getArry(i, j) == 1) {
					g2d.drawImage(block1, i * blocksize, j * blocksize, null);
				} else if (spielfeld.getArry(i, j) == 2) {
					g2d.drawImage(block2, i * blocksize, j * blocksize, null);
				} else if (spielfeld.getArry(i, j) == 10) { 
					// wegen transparenz
					g2d.drawImage(block0, i * blocksize, j * blocksize, null);
					g2d.drawImage(bomb, i * blocksize, j * blocksize, null);
				} else if (spielfeld.getArry(i, j) == 11) {
					g2d.drawImage(explo, i * blocksize, j * blocksize, null);
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
			// bomber1
			if (key == KeyEvent.VK_LEFT) {
				bomber1.setdxl(-m);
			} else if (key == KeyEvent.VK_RIGHT) {
				bomber1.setdxr(m);
			} else if (key == KeyEvent.VK_UP) {
				bomber1.setdyl(-m);
			} else if (key == KeyEvent.VK_DOWN) {
				bomber1.setdyr(m);
			} else if (key == KeyEvent.VK_CONTROL) {
				bomber1.setBomb(spielfeld);
			}

			// bomber2
			if (key == KeyEvent.VK_A) {
				bomber2.setdxl(-m);
			} else if (key == KeyEvent.VK_D) {
				bomber2.setdxr(m);
			} else if (key == KeyEvent.VK_W) {
				bomber2.setdyl(-m);
			} else if (key == KeyEvent.VK_S) {
				bomber2.setdyr(m);
			} else if (key == KeyEvent.VK_SPACE) {
				bomber2.setBomb(spielfeld);
			}
		}

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			// bomber1
			if (key == KeyEvent.VK_LEFT)
				bomber1.setdxl(0);
			if (key == KeyEvent.VK_RIGHT)
				bomber1.setdxr(0);
			if (key == KeyEvent.VK_UP)
				bomber1.setdyl(0);
			if (key == KeyEvent.VK_DOWN)
				bomber1.setdyr(0);
			// bomber2
			if (key == KeyEvent.VK_A)
				bomber2.setdxl(0);
			if (key == KeyEvent.VK_D)
				bomber2.setdxr(0);
			if (key == KeyEvent.VK_W)
				bomber2.setdyl(0);
			if (key == KeyEvent.VK_S)
				bomber2.setdyr(0);
		}
	}
}
