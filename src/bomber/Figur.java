package bomber;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Figur {
	private int m, x, y, dx,dxl,dxr, dy,dyl,dyr, radi, maxBombs, bombsWorking, richtung,bild;
	private Image forward, right, left, back, dead;
	private boolean isAlive;
	
	public static final int oben=1;
	public static final int unten=2;
	public static final int rechts=3;
	public static final int links=4;
	
	public Figur(int xPosition, int yPosition, int Bild) {
		m = 5; // movementreichweite
		isAlive = true;
		this.bild=Bild;
		x = xPosition;
		y = yPosition;
		bombsWorking=0;
		maxBombs = 3;
		radi = 5;
		dxl=0;
		dxr=0;
		dyl=0;
		dxl=0;
		richtung=unten;
		ImageIcon idead = new ImageIcon("bilder/schaedel.png");
		dead = idead.getImage();

		if (Bild == 1) {
			ImageIcon i1 = new ImageIcon("Images/Player 1/Forward1.jpg");
			forward = i1.getImage();
			ImageIcon i2 = new ImageIcon("Images/Player 1/Rght1.jpg");
			right = i2.getImage();
			ImageIcon i3 = new ImageIcon("Images/Player 1/Left1.jpg");
			left = i3.getImage();
			ImageIcon i4 = new ImageIcon("Images/Player 1/Back1.jpg");
			back = i4.getImage();
		} else if (Bild == 2) {
			ImageIcon i = new ImageIcon("bilder/huhn.png");
			forward = i.getImage();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setMaxBombs(int maxB){
		if (maxB>0)
		maxBombs=maxB;
	}
	public void bombsWorkingPlus(){
		if (bombsWorking<maxBombs)
			bombsWorking+=1;
	}
	public void bombsWorkingMinus(){
		if (bombsWorking>0)
			bombsWorking-=1;
	}
	public Image getImage() {
		if (isAlive == true) {
			if (richtung==unten){
				return forward;}
			else if (richtung==rechts){
				return right;}
			else if (richtung==links){
				return left;}
			else if (richtung==oben){
				return back;}
			else{
				return dead;}
		} else {
			return dead;
		}
	}

	public int getRadi() {
		return radi;
	}

	// bombe legen
	public void setBomb(Field feld) {
		if (maxBombs > bombsWorking &&  feld.getArry(gethauptarrayX(), gethauptarrayY()) == 0 && isAlive) {
			Thread bombe = new Thread(new Bombe(gethauptarrayX(), gethauptarrayY(), radi, feld, Figur.this));
			bombe.start();
		}
	}

	// einfache Funktionen
	public void moveLR() {
		x += dx;
		if (dx>0){
			richtung=rechts;}
		if (dx<0){
			richtung=links;}
	}
	public void moveUD() {
		y += dy;
		if (dy<0)
			richtung=oben;
		if (dy>0)
			richtung=unten;	
	}
	public void setdxl(int newdx) {
		dxl = newdx;
	}
	public void setdxr(int newdx) {
		dxr = newdx;
	}
	private void setdx(int newdx){
		dx=newdx;
	}
	private void setdy(int newdy){
		dy=newdy;
	}
	public void setdyl(int newdy) {
		dyl = newdy;
	}
	public void setdyr(int newdy) {
		dyr = newdy;
	}
	public int getdy() {
		return dy;
	}
	public int getdx() {
		return dx;
	}	
	// Hauptarray der Figur
	public int gethauptarrayX() {
		int posx = (x + 30) / 60;
		return posx;
	}
	public int gethauptarrayY() {
		int posy = (y + 30) / 60;
		return posy;
	}

	// Smart Moving Vers 1.0
	// Abfrage rechts links
	public void Perma(Field feld) {
		dx=dxr+dxl;
		dy=dyr+dyl;
		if (feld.kannManDraufLeben(gethauptarrayX(), gethauptarrayY()) && isAlive == true) {
			if (dx != 0) {
				if (y % 60 > 0 && y % 60 < 30) { // Zentralisierung
					setdy(-m);
					moveUD();
					setdy(0);
				} else if (y % 60 >= 30 && y % 60 < 60) {
					setdy(m);
					moveUD();
					setdy(0);
				} else if (y % 60 == 0) {
					// Movement
					if (dx > 0) {
						if (feld.isWalkable(gethauptarrayX() + 1, gethauptarrayY()) ||x % 60 != 0) {
							moveLR();
						}
					} else if (dx < 0) {
						if (feld.isWalkable(gethauptarrayX() - 1, gethauptarrayY()) || x % 60 != 0) {
							moveLR();
						}
					}
				}
			}
			// Abfrage unten oben
			if (dy != 0) {
				if (x % 60 > 0 && x % 60 < 30) { // Zentralisierung
					setdx(-m);
					moveLR();
					setdx(0);
				} else if (x % 60 >= 30 && x % 60 < 60) {
					setdx(m);
					moveLR();
					setdx(0);
				} else if (x % 60 == 0) {
					// Movement
					if (dy > 0) {
						if (feld.isWalkable(gethauptarrayX(), gethauptarrayY() + 1) || y % 60 != 0) {
							moveUD();
						}
					} else if (dy < 0) {
						if (feld.isWalkable(gethauptarrayX(), gethauptarrayY() - 1) || y % 60 != 0) {
							moveUD();
						}
					}
				}
			}
		} else {
			isAlive = false;
		}
	}
}
