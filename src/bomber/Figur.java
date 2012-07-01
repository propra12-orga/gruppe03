package bomber;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Figur {
	private int m, x, y, dx, dxl, dxr, dy, dyl, dyr, timerLost, richtung, bild, radi, maxBombs, bombsWorking;
	public int arrowPosX, arrowPosY, arrowRichtung;
	private Image forward, right, left, back, dead;
	private boolean isAlive, lost;
	public boolean arrowIsWorking, gotArrow;

	public static final int oben = 1;
	public static final int unten = 2;
	public static final int rechts = 3;
	public static final int links = 4;
	public static final int startmovement = 2;
	public static final int startradi = 1;

	public Figur(int xPosition, int yPosition, int Bild) {
		m = startmovement; // movementreichweite
		isAlive = true;
		timerLost = 0;
		this.bild = Bild;
		x = xPosition;
		y = yPosition;
		bombsWorking = 0;
		maxBombs = 1;
		gotArrow = false;
		arrowIsWorking = false;
		lost = false;
		radi = startradi;
		dxl = 0;
		dxr = 0;
		dyl = 0;
		dxl = 0;
		richtung = unten;
		ImageIcon idead = new ImageIcon("bilder/schaedel.png");
		dead = idead.getImage();

		if (Bild == 1) {
			ImageIcon i1 = new ImageIcon("Images/Player 1/Forward1.png");
			forward = i1.getImage();
			ImageIcon i2 = new ImageIcon("Images/Player 1/Rght1.png");
			right = i2.getImage();
			ImageIcon i3 = new ImageIcon("Images/Player 1/Left1.png");
			left = i3.getImage();
			ImageIcon i4 = new ImageIcon("Images/Player 1/Back1.png");
			back = i4.getImage();
		} else if (Bild == 2) {
			// TODO 2. spieler richtugen
			ImageIcon i = new ImageIcon("bilder/huhn.png");
			forward = i.getImage();
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getMovementspeed() {
		return m;
	}

	public void setMaxBombs(int maxB) {
		if (maxB > 0)
			maxBombs = maxB;
	}

	public void bombsWorkingPlus() {
		if (bombsWorking < maxBombs)
			bombsWorking += 1;
	}

	public void bombsWorkingMinus() {
		if (bombsWorking > 0)
			bombsWorking -= 1;
	}

	public Image getImage() {
		if (isAlive == true) {
			if (richtung == unten) {
				return forward;
			} else if (richtung == rechts) {
				return right;
			} else if (richtung == links) {
				return left;
			} else if (richtung == oben) {
				return back;
			} else {
				return dead;
			}
		} else {
			return dead;
		}
	}

	public int getRadi() {
		return radi;
	}

	// bombe legen
	public void setBomb(Field feld) {
		if (maxBombs > bombsWorking && feld.getArry(gethauptarrayX(), gethauptarrayY()) == 0 && isAlive) {
			Thread bombe = new Thread(new Bombe(gethauptarrayX(), gethauptarrayY(), radi, feld, Figur.this));
			bombe.start();
		}
	}

	// einfache Funktionen
	public void moveLR() {
		x += dx;
		if (dx > 0) {
			richtung = rechts;
		}
		if (dx < 0) {
			richtung = links;
		}
	}

	public void moveUD() {
		y += dy;
		if (dy < 0)
			richtung = oben;
		if (dy > 0)
			richtung = unten;
	}

	public void setdxl(int newdx) {
		dxl = newdx;
	}

	public void setdxr(int newdx) {
		dxr = newdx;
	}

	private void setdx(int newdx) {
		dx = newdx;
	}

	private void setdy(int newdy) {
		dy = newdy;
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

	public void restart(int xPos, int yPos) {
		x = xPos;
		y = yPos;
		dx = 0;
		dy = 0;
		m = startmovement;
		isAlive = true;
		bombsWorking = 0;
		maxBombs = 1;
		lost = false;
		timerLost = 0;
		radi = startradi;
		dxl = 0;
		dxr = 0;
		dyl = 0;
		dxl = 0;
		richtung = unten;
		arrowIsWorking = false;
		gotArrow = false;
	}

	// ITEM FUNKTIONEN
	public void getBombentasche() {
		maxBombs += 1;
	}

	public void getReichweite() {
		radi += 1;
	}

	public void getStiefel() {
		if (m == 2)
			m = 3;
		else if (m == 3)
			m = 4;
		else if (m == 4)
			m = 5;
	}

	private void getBogen() {
		gotArrow = true;

	}

	public void shootArrow(Field feld) {
		if (gotArrow) {
			if (arrowIsWorking == false) {
				arrowRichtung = richtung;
				arrowPosX = gethauptarrayX() * 60 + 25;
				arrowPosY = gethauptarrayY() * 60 + 25;
				feld.setArry(arrowPosX / 60, arrowPosY / 60, Field.startarrow);
				arrowIsWorking = true;

			}
		}

	}

	public void flyingArrow(Field feld) {
		int aax = arrowPosX / 60;
		int aay = arrowPosY / 60;
		if (feld.isWalkable(aax, aay)) {
			if (feld.getArry(aax, aay) != Field.startarrow)
				feld.setArry(aax, aay, Field.flyingarrow);
			if (arrowRichtung == unten) {
				if (feld.isArrow(aax, aay - 1))
					feld.setArry(aax, aay - 1, Field.block0);
				arrowPosY += 10;
			} else if (arrowRichtung == oben) {
				if (feld.isArrow(aax, aay + 1))
					feld.setArry(aax, aay + 1, Field.block0);
				arrowPosY -= 10;
			} else if (arrowRichtung == rechts) {
				if (feld.isArrow(aax - 1, aay))
					feld.setArry(aax - 1, aay, Field.block0);
				arrowPosX += 10;
			} else if (arrowRichtung == links) {
				if (feld.isArrow(aax + 1, aay))
					feld.setArry(aax + 1, aay, Field.block0);
				arrowPosX -= 10;
			}
		} else if (feld.isBombe(aax, aay)) {
			feld.setArry(aax, aay, Field.explosion);
			arrowIsWorking = false;
		} else {
			if (arrowRichtung == unten) {
				if (feld.isArrow(aax, aay - 1))
					feld.setArry(aax, aay - 1, Field.block0);
				arrowPosY += 10;
			} else if (arrowRichtung == oben) {
				if (feld.isArrow(aax, aay + 1))
					feld.setArry(aax, aay + 1, Field.block0);
				arrowPosY -= 10;
			} else if (arrowRichtung == rechts) {
				if (feld.isArrow(aax - 1, aay))
					feld.setArry(aax - 1, aay, Field.block0);
				arrowPosX += 10;
			} else if (arrowRichtung == links) {
				if (feld.isArrow(aax + 1, aay))
					feld.setArry(aax + 1, aay, Field.block0);
				arrowPosX -= 10;
			}
			arrowIsWorking = false;
		}
	}

	// Smart Moving Vers 1.0
	// Abfrage rechts links
	public void Perma(Field feld) {
		// ArrowAbfrage
		if (arrowIsWorking) {
			flyingArrow(feld);
		}
		// Verliererabfrage
		if (isAlive == false) {
			if (timerLost == 200) {
				lost = true;
			} else {
				timerLost += 1;
			}
		}
		// itemabfrage
		if (feld.isItem(gethauptarrayX(), gethauptarrayY())) {
			if (feld.getArry(gethauptarrayX(), gethauptarrayY()) == Field.bombentasche) {
				getBombentasche();
				feld.setArry(gethauptarrayX(), gethauptarrayY(), Field.block0);
			} else if (feld.getArry(gethauptarrayX(), gethauptarrayY()) == Field.reichweite) {
				getReichweite();
				feld.setArry(gethauptarrayX(), gethauptarrayY(), Field.block0);
			} else if (feld.getArry(gethauptarrayX(), gethauptarrayY()) == Field.stiefel && x % 60 == 0 && y % 60 == 0) {
				getStiefel();
				feld.setArry(gethauptarrayX(), gethauptarrayY(), Field.block0);
			} else if (feld.getArry(gethauptarrayX(), gethauptarrayY()) == Field.bogen) {
				getBogen();
				feld.setArry(gethauptarrayX(), gethauptarrayY(), Field.block0);
			}
		}
		dx = dxr + dxl;
		dy = dyr + dyl;
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
						if (feld.isWalkable(gethauptarrayX() + 1, gethauptarrayY()) || x % 60 != 0) {
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

	public boolean lost() {
		return lost;
	}

}
