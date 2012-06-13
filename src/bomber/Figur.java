package bomber;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Figur {
	int m, x, y, dx, dy, radi, maxBombs, bombsWorking;
	Image guy, dead;
	boolean isAlive;

	public Figur(int xPosition, int yPosition, int Bild) {
		m = 5; // movementreichweite
		isAlive = true;
		x = xPosition;
		y = yPosition;
		maxBombs = 1;
		radi = 1;

		ImageIcon i2 = new ImageIcon("bilder/dead.jpg");
		dead = i2.getImage();

		if (Bild == 1) {
			ImageIcon i = new ImageIcon("bilder/link.png");
			guy = i.getImage();
		} else if (Bild == 2) {
			ImageIcon i = new ImageIcon("bilder/huhn.png");
			guy = i.getImage();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		if (isAlive == true) {
			return guy;
		} else {
			return dead;
		}
	}

	public int getRadi() {
		return radi;
	}

	// bombe legen
	public void setBomb(Field feld) {
		if (maxBombs > bombsWorking && feld.getArry(gethauptarrayX(), gethauptarrayY()) == 0) {
			feld.setArry(gethauptarrayX(), gethauptarrayY(), 10);
			Thread bombe = new Thread(new Bombe(gethauptarrayX(), gethauptarrayY(), radi, feld));
			bombe.start();
		}
	}

	// Movement
	public void moveLR() {
		x += dx;
	}

	public void moveUD() {
		y += dy;
	}

	public void setdx(int newdx) {
		dx = newdx;
	}

	public void setdy(int newdy) {
		dy = newdy;
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
		if ((feld.getArry(gethauptarrayX(), gethauptarrayY()) == 0 || feld.getArry(gethauptarrayX(), gethauptarrayY()) == 10) && isAlive == true) {
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
						if (feld.getArry(gethauptarrayX() + 1, gethauptarrayY()) == 0 || x % 60 != 0) {
							moveLR();
						}

					} else if (dx < 0) {
						if (feld.getArry(gethauptarrayX() - 1, gethauptarrayY()) == 0 || x % 60 != 0) {
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
						if (feld.getArry(gethauptarrayX(), gethauptarrayY() + 1) == 0 || y % 60 != 0) {
							moveUD();
						}

					} else if (dy < 0) {
						if (feld.getArry(gethauptarrayX(), gethauptarrayY() - 1) == 0 || y % 60 != 0) {
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
