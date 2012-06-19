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
			if (randomitem == 0) {
				feld.setArry(x, y, Field.bombentasche);
			}else if (randomitem ==1){
				feld.setArry(x, y, Field.reichweite);
			}else{
				feld.setArry(x, y, 0);}
		} catch (Exception e) {
		}
	}
}
