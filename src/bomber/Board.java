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
	public Image img;
	Timer time;

	public Board() {
		bomber1 = new Figur(30,30);
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("feld.png");
		img = i.getImage();
		time = new Timer(5, this);
		time.start();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(img, 0, 0, null);
		g2d.drawImage(bomber1.getImage(), bomber1.getX(), bomber1.getY(), null);
	}

	private class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			bomber1.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			bomber1.keyPressed(e);
		}
	}
}
