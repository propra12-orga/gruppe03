package bomber;

import java.awt.event.KeyEvent;

public class Figur {
	int x, y,dx,dy;
	
	
	public Figur(int xPosition,int yPosition){
		x=xPosition;
		y=yPosition;
	}
	
	private int getX(){
		return x;
	}
	private int getY(){
		return y;
	}

//Movement
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
			x-=1;

		if (key == KeyEvent.VK_RIGHT)
			x+=1;
	
		if (key == KeyEvent.VK_UP)
			y-=1;

		if (key == KeyEvent.VK_DOWN)
			y += 1;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			dx = 0;

		if (key == KeyEvent.VK_RIGHT)
			dx = 0;		
		
		if (key == KeyEvent.VK_UP)
				dx = 0;

		if (key == KeyEvent.VK_DOWN)
				dx = 0;

	}
	
	
}
