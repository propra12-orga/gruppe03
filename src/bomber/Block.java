package bomber;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Block {
	
		int x, y,dx,dy;
		Image block;
		
		public Figur(int xPosition,int yPosition){
			x=xPosition;
			y=yPosition;
			ImageIcon i = new ImageIcon("");
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
}
