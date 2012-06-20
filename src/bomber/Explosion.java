package bomber;

public class Explosion implements Runnable{
	public final static int explotime=300;
	private Field feld;
	private int x,y;
	public Explosion(int xArray, int yArray, Field feld){
		this.feld=feld;
		x=xArray;
		y=yArray;
	}
	public void run() {try{
		feld.setArry(x, y, 11);
		Thread.sleep(explotime);
		feld.setArry(x, y, 0);
	}catch(Exception e){}
	}

}
