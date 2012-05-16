package bomber;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bombe {
	Image bomb;
	int radius;
	
	public Bombe(int radius){
		ImageIcon i = new ImageIcon("bomb.jpg");
		bomb = i.getImage();
		radius= this.radius;
	}
}
