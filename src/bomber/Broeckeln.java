package bomber;

public class Broeckeln implements Runnable {

	private int x, y;
	private Field feld;

	public Broeckeln(int xArray, int yArray, Field feld) {
		x = xArray;
		y = yArray;
		this.feld = feld;
	}

	public void run() {
		try {
			feld.setArry(x, y, 12);
			Thread.sleep(500);
			feld.setArry(x, y, 0);
		} catch (Exception e) {
		}
	}
}
