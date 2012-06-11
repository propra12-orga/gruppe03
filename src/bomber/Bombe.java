package bomber;

public class Bombe implements Runnable{
	int r,x,y;
	Field feld;
	public Bombe(int xPosition,int yPosition,int radius,Field feld){
		r = radius;
		x=xPosition; //array posi
		y=yPosition;
		this.feld=feld;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}	

	@Override
	public void run() {
		for (int i=0; i<10;i++){
			if (feld.getArry(x, y)!=11){ //11explodierend !
			}else{
				explode();
			}
		}
		explode();
	}

	private void explode() {
		
		
	}
}
