package bomber;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bombe {
	Image bomb;
	int radius,x,y;
	
	public Bombe(int xPosition,int yPosition,int radius){
		ImageIcon i = new ImageIcon("bombe2.png");
		bomb = i.getImage();
		this.radius= radius;
		x=xPosition;
		y=yPosition;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}	
	public Image getImage(){
		return bomb;
	}
}
