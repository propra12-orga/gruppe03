package bomber;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Figur {
	int x, y,dx,dy;
	Image guy;
	
	public Figur(int xPosition,int yPosition){
		x=xPosition;
		y=yPosition;
		ImageIcon i = new ImageIcon("link.png");
		guy = i.getImage();
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}	
	public Image getImage(){
		return guy;
	}

//Movement
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
			x-=10;

		if (key == KeyEvent.VK_RIGHT)
			x+=10;
	
		if (key == KeyEvent.VK_UP)
			y-=10;

		if (key == KeyEvent.VK_DOWN)
			y += 10;		
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
