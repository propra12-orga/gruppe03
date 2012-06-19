package bomber;

public class Field {
	/*
	 * Wertetabbele was wofür steht 0-9 blöcke 10-19 bomben 20-29 items
	 * 
	 * 0 frei 1 unzerstörbar 2 zerstörbar 10 bombe 11 explosion 12 broeckeln 7
	 * Ausgang Triforce 20 bombentasche
	 */
	public static final int block0=0;
	public static final int block1=1;
	public static final int block2 =2;
	public static final int ausgang=7;
	public static final int bombe=10;
	public static final int explosion=11;
	public static final int broeckeln=12;
	
	public static final int bombentasche=20;
	public static final int reichweite=21;
	public static final int bogen=22;
	public static final int umhang=23;
	public static final int stiefel=23;
	
	private int[][] feld = new int[15][11];

	public Field() {
		for (int in = 0; in < 15; in++) {
			for (int jn = 0; jn < 11; jn++) {

				if (in == 0 || jn == 0 || in == 14 || jn == 10 || (in % 2 == 0 && jn % 2 == 0))
					feld[in][jn] = block1;
				else
					feld[in][jn] = block2;

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


	
	public int getArry(int x, int y) {
		return feld[x][y];
	}

	public void setArry(int i, int j, int wert) {
		feld[i][j] = wert;
	}

	public boolean isWalkable(int x, int y) {
		int wert = feld[x][y];
		if (isItem(x,y))
			return true;
		if (wert == block0 || wert == explosion || wert == ausgang)
			return true;
		else
			return false;
	}

	public boolean isItem(int x, int y) {
		int wert = feld[x][y];
		if (wert == bombentasche || wert == reichweite || wert == stiefel)
			return true;
		else
			return false;
	}
	
	public boolean kannManDraufLeben(int x, int y) {
		int wert = feld[x][y];
		if (isItem(x,y))
			return true;
		if (wert == block0 || wert == bombe || wert == ausgang )
			return true;
		else
			return false;
	}

	
	public boolean isExplodierbar(int x, int y) {
		int wert = feld[x][y];
		if (wert == block0 || wert == bombe || wert == explosion)
			return true;
		else
			return false;
	}

	public boolean isZerstoerar(int x, int y) {
		int wert = feld[x][y];
		if (isItem(x,y))
			return true;
		if (wert == block2)
			return true;
		else
			return false;
	}

	public void restart() {
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
}
