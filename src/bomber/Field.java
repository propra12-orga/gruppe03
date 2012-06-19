package bomber;

public class Field {
	/*
	 * Wertetabbele was wof�r steht 0-9 bl�cke 10-19 bomben 20-29 items
	 * 
	 * 0 frei 1 unzerst�rbar 2 zerst�rbar 10 bombe 11 explosion 12 broeckeln 7
	 * Ausgang Triforce
	 */
	private int[][] feld = new int[15][11];

	public Field() {
		for (int in = 0; in < 15; in++) {
			for (int jn = 0; jn < 11; jn++) {

				if (in == 0 || jn == 0 || in == 14 || jn == 10 || (in % 2 == 0 && jn % 2 == 0))
					feld[in][jn] = 1;
				else
					feld[in][jn] = 2;

			}
		}
		feld[13][9] = 0;
		feld[13][8] = 0;
		feld[12][9] = 0;
		feld[1][1] = 0;
		feld[1][2] = 0;
		feld[2][1] = 0;
		feld[1][3] = 7;
	}

	public int getArry(int i, int j) {
		return feld[i][j];
	}

	public void setArry(int i, int j, int wert) {
		feld[i][j] = wert;
	}

	public boolean isWalkable(int x, int y) {
		int wert = feld[x][y];
		if (wert == 0 || wert == 11 || wert == 7)
			return true;
		else
			return false;
	}

	public boolean kannManDraufLeben(int x, int y) {
		int wert = feld[x][y];
		if (wert == 0 || wert == 10 || wert == 7)
			return true;
		else
			return false;
	}

	
	public boolean isExplodierbar(int x, int y) {
		int wert = feld[x][y];
		if (wert == 0 || wert == 10 || wert == 11)
			return true;
		else
			return false;
	}

	public boolean isZerstoerar(int x, int y) {
		int wert = feld[x][y];
		if (wert == 2)
			return true;
		else
			return false;
	}
}
