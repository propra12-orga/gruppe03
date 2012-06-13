package bomber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Bombe implements Runnable, ActionListener {
	int r, x, y, i;
	Field feld;
	Figur spieler;
	Timer time;
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

	public void Timerende() {
		explode();
		time.stop();
		ende = true;
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
				Thread.sleep(50);
			}
		} catch (Exception e) {
		}
	}

	public void explode() {
		Thread e = new Thread(new Explosion(x, y, feld));
		e.start();
		for (int i = 1; i <= r; i++) {
			if (feld.getArry(x + i, y) == 0 || feld.getArry(x + i, y) == 10) {
				Thread e1 = new Thread(new Explosion(x + i, y, feld));
				e1.start();
			} else {
				break;
			}
		}

		for (int i = 1; i <= r; i++) {
			if (feld.getArry(x - i, y) == 0 || feld.getArry(x - i, y) == 10) {
				Thread e1 = new Thread(new Explosion(x - i, y, feld));
				e1.start();
			} else {
				break;
			}
		}

		for (int i = 1; i <= r; i++) {
			if (feld.getArry(x, y + i) == 0 || feld.getArry(x, y+i) == 10) {
				Thread e1 = new Thread(new Explosion(x, y + i, feld));
				e1.start();
			} else {
				break;
			}
		}

		for (int i = 1; i <= r; i++) {
			if (feld.getArry(x, y - i) == 0 || feld.getArry(x , y-i) == 10) {
				Thread e1 = new Thread(new Explosion(x, y - i, feld));
				e1.start();
			} else {
				break;
			}
		}
	}
}
