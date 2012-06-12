package bomber;

public class Explosion implements Runnable{
	Field feld;
	int x,y;
	public Explosion(int xArray, int yArray, Field feld){
		this.feld=feld;
		x=xArray;
		y=yArray;
	}
	public void run() {try{
		feld.setArry(x, y, 11);
		Thread.sleep(500);
		feld.setArry(x, y, 0);
	}catch(Exception e){}
	}

}
