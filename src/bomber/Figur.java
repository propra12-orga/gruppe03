package bomber;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Figur {
	int x, y,dx,dy,radi,maxBombs,bombsWorking;
	Image Forward1;
	
	public Figur(int xPosition,int yPosition){
		x=xPosition;
		y=yPosition;
		maxBombs=1;
		ImageIcon i = new ImageIcon("Images/Player1/Forward1.jpg");
		Forward1 = i.getImage();
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}	
	public Image getImage(){
		return Forward1;
	}
	public int getRadi(){
		return radi;
	}
	//bombe legen
	public boolean setBomb(){
		if (maxBombs > bombsWorking)
			return true;
		else
			return false;
	}	

//new Movement
	public void moveLR(){
		int xt;
		int xt1;
		int y0;
		int y1;
		
		xt = (int) Math.floor((x+dx)/(60));
		xt1 = (int) Math.floor((x+dx+59)/(60));
		y0 = (int) Math.floor((y)/(60));
		y1 = (int) Math.floor((y+59)/(60));
	
		
		if((Board.feld[xt][y0]==1 && dx<0)||(Board.feld[xt1][y0]==1 && dx>0)||(Board.feld[xt][y1]==1 && dx<0)||(Board.feld[xt1][y1]==1 && dx>0)){
			
			if (((((double)y/60)-Math.floor((double)y/60))>0.70)&& (Math.floor((double)y/60)%2!=1)){
				
				System.out.println(((double)y/60)-Math.floor((double)y/60));
				 y=(int)Math.ceil(((double)y/60))*60;
				x+=dx;
			}
			else {
				
			}
			if ((((((double)y-60)/60)-Math.floor(((double)y-60)/60))<0.26)&& (Math.floor(((double)y-60)/60)%2!=1) && ((((double)y-60)/60)-Math.floor(((double)y-60)/60))!=0){
				
				System.out.println(((double)y/60)-Math.ceil((double)y/60));
				 y=(int)Math.floor(((double)y/60))*60;
				x+=dx;
			}
			else {
				
			}
			
		}
		else
		{
		x+=dx;
		
	}
}
	public void moveUD(){
		
		int yt;
		int yt1;
		int x0;
		int x1;
		
		yt = (int) Math.floor((y+dy)/(60));
		yt1 = (int) Math.floor((y+dy+59)/(60));
		x0 = (int) Math.floor((x)/(60));
		x1 = (int) Math.floor((x+59)/(60));
		
		if((Board.feld[x0][yt]==1 && dy<0)||(Board.feld[x0][yt1]==1 && dy>0)||(Board.feld[x1][yt]==1 && dy<0)||(Board.feld[x1][yt1]==1 && dy>0)){
			if (((((double)x/60)-Math.floor((double)x/60))>0.70)&& (Math.floor((double)x/60)%2!=1)){
				
				System.out.println(((double)x/60)-Math.floor((double)x/60));
				 x=(int)Math.ceil(((double)x/60))*60;
				y+=dy;
			}
			else {
				
			}
			if ((((((double)x-60)/60)-Math.floor(((double)x-60)/60))<0.26)&& (Math.floor(((double)x-60)/60)%2!=1) && ((((double)x-60)/60)-Math.floor(((double)x-60)/60))!=0){
				
				System.out.println(((double)x/60)-Math.ceil((double)x/60));
				 x=(int)Math.floor(((double)x/60))*60;
				y+=dy;
			}
			else {
				
			}
		
		}
		else  {	
			y+=dy;
		}
	}
		
	
	public void setdx(int newdx){
		dx=newdx;
	}
	public void setdy(int newdy){
		dy=newdy;
	}
		
}
