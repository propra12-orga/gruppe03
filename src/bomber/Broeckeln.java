package bomber;

import java.util.Random;

public class Broeckeln implements Runnable {

	private int x, y, randomitem;
	private Field feld;

	public Broeckeln(int xArray, int yArray, Field feld) {
		x = xArray;
		y = yArray;
		this.feld = feld;
		Random r = new Random();
		randomitem = r.nextInt(100); // =0,1,..,7

	}

	public void run() {
		try {
			boolean newitemOK = false;
			// Ob neues Item erstellt wird und dann welches
			if (feld.getArry(x, y) == Field.block2)
				newitemOK = true;
			feld.setArry(x, y, 12);
			Thread.sleep(Explosion.explotime);
			if (newitemOK) {
				if (randomitem >= 0 && randomitem < 20) {
					feld.setArry(x, y, Field.bombentasche);
				} else if (randomitem >= 20 && randomitem < 40) {
					feld.setArry(x, y, Field.reichweite);
				} else if (randomitem >= 40 && randomitem < 55) {
					feld.setArry(x, y, Field.stiefel);
				} else if (randomitem >= 55 && randomitem < 64) {
					feld.setArry(x, y, Field.bogen);
				} else {
					feld.setArry(x, y, 0);
				}
			} else {
				feld.setArry(x, y, 0);
			}
		} catch (Exception e) {
		}
	}
}
