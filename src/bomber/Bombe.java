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
			Thread.sleep(2000);
			explode();
		}catch(Exception e){}
	}

	public void explode() {
		Thread e = new Thread(new Explosion(x,y,feld));
		e.start();
		for (int i=1; i<=3;i++){
			if (feld.getArry(x+i,y)==0){
				Thread e1 = new Thread(new Explosion(x+i,y,feld));
				e1.start(); 
			}else{
				break;	
			}
		}
		
		for (int i=1; i<=3;i++){
			if (feld.getArry(x-i,y)==0){
				Thread e1 = new Thread(new Explosion(x-i,y,feld));
				e1.start(); 
			}else{
				break;	
			}
		}
		
		for (int i=1; i<=3;i++){
			if (feld.getArry(x,y+i)==0){
				Thread e1 = new Thread(new Explosion(x,y+i,feld));
				e1.start(); 
			}else{
				break;	
			}
		}
		
		for (int i=1; i<=3;i++){
			if (feld.getArry(x,y-i)==0){
				Thread e1 = new Thread(new Explosion(x,y-i,feld));
				e1.start(); 
			}else{
				break;	
			}
		}
		/*	if (feld.getArry(x-i,y)==0 || feld.getArry(x-i,y)==10){
				Thread e1 = new Thread(new Explosion(x-i,y,feld));
				e1.start(); }
			if (feld.getArry(x,y+i)==0 || feld.getArry(x,y+i)==10){
				Thread e1 = new Thread(new Explosion(x,y+i,feld));
				e1.start(); }
			if (feld.getArry(x,y-i)==0 || feld.getArry(x,y-i)==10){
				Thread e1 = new Thread(new Explosion(x,y-i,feld));
				e1.start(); }
		}
		/*boolean obenfrei=true;
		boolean untenfrei=true;
		boolean rechtsfrei=true; 
		boolean linksfrei=true;
		for (int i=0; i<=4;i++){
			if (i==0){
				Thread e1 = new Thread(new Explosion(x,y,feld));
				e1.start();
			}else{
				if (feld.getArry(x+i, y)==0 && rechtsfrei){ //frei
					Thread e1 = new Thread(new Explosion(x+1,y,feld));
					e1.start();
				}else{
					rechtsfrei=false;
					}
				} 
			if (feld.getArry(x-i, y)==0 && linksfrei){
				Thread e1 = new Thread(new Explosion(x-i,y,feld));
				e1.start();
			}else{
				linksfrei=false;
				} 
			if (feld.getArry(x, y+i)==0 && untenfrei){
				Thread e1 = new Thread(new Explosion(x,y+i,feld));
				e1.start();
			}else{
				untenfrei=false;
				}
			if (feld.getArry(x, y-i)==0 && obenfrei){
				Thread e1 = new Thread(new Explosion(x,y-i,feld));
				e1.start();
			}else{
				obenfrei=false;
			}
		}*/
	}
}
