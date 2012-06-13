package bomber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Bombe implements Runnable, ActionListener {
	private int r, x, y;
	private Field feld;
	private Figur spieler;
	private Timer time;
	boolean ende;

	public Bombe(int xPosition, int yPosition, int radius, Field feld, Figur spieler) {
		r = radius;
		x = xPosition; // array posi
		y = yPosition;
		this.spieler=spieler;
		this.feld = feld;
		ende = false;
	}

	public void actionPerformed(ActionEvent e) {
		Timerende();
	}

	private void Timerende() {
		ende = true;
		explode();
		time.stop();
		spieler.bombsWorkingMinus();
	}

	public void run() {
		try {
			feld.setArry(spieler.gethauptarrayX(), spieler.gethauptarrayY(), 10);
			spieler.bombsWorkingPlus();
			time = new Timer(2000, this);
			time.start();
			while (ende == false) {
				if (feld.getArry(x, y) != 10) {
					Timerende();
				}
				Thread.sleep(10);
			}
		} catch (Exception e) {
		}
	}

	public void explode() {
		Thread e = new Thread(new Explosion(x, y, feld));
		e.start();
		for (int i = 1; i <= r; i++) {
			if (feld.isExplodierbar(x + i, y)) {
				Thread e1 = new Thread(new Explosion(x + i, y, feld));
				e1.start();
			} else if (feld.isZerstoerar(x+i, y)){
				Thread e2 = new Thread (new Broeckeln(x + i, y, feld));
				e2.start();
			}
				break;
			
		}

		for (int i = 1; i <= r; i++) {
			if (feld.isExplodierbar(x-i, y)) {
				Thread e1 = new Thread(new Explosion(x - i, y, feld));
				e1.start();
			} else {
				break;
			}
		}

		for (int i = 1; i <= r; i++) {
			if (feld.isExplodierbar(x, y+i)) {
				Thread e1 = new Thread(new Explosion(x, y + i, feld));
				e1.start();
			} else {
				break;
			}
		}

		for (int i = 1; i <= r; i++) {
			if (feld.isExplodierbar(x, y-i)) {
				Thread e1 = new Thread(new Explosion(x, y - i, feld));
				e1.start();
			} else {
				break;
			}
		}
	}
}
//public void hilf(int i, int j){
//	if feld.getArry(i, j)==0 ||}