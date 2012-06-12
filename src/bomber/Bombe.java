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
	
	@Override
	public void run() {
		try{
			Thread.sleep(1000);
			explode();
		}catch(Exception e){}
	}

	public void explode() {
		for (int i=0; i<=r;i++){
			if (i==0){
				Thread e1 = new Thread(new Explosion(x,y,feld));
				e1.start();
			}else{
				if (feld.getArry(x+i, y)==0){ //frei
					Thread e1 = new Thread(new Explosion(x+1,y,feld));
					e1.start();
				} 
				if (feld.getArry(x-i, y)==0){
					Thread e1 = new Thread(new Explosion(x-i,y,feld));
					e1.start();
				} 
				if (feld.getArry(x, y+i)==0){
					Thread e1 = new Thread(new Explosion(x,y+i,feld));
					e1.start();
				} 
				if (feld.getArry(x, y-i)==0){
					Thread e1 = new Thread(new Explosion(x,y-i,feld));
					e1.start();
				}
			}
		}
	}
}
