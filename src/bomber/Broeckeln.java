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
		randomitem = r.nextInt(6); // =0,1,..,5

	}

	public void run() {
		try {
			feld.setArry(x, y, 12);
			Thread.sleep(500);
			if (randomitem < 3) {
				Item neu = new Item(x, y, Item.bombentasche, feld);
			}
			feld.setArry(x, y, 0);
		} catch (Exception e) {
		}
	}
}
