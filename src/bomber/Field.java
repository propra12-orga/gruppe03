package bomber;

import java.io.*;
import java.util.Scanner;

public class Field {
	/*
	 * Wertetabbele was wofür steht 0-9 blöcke 10-19 bomben 20-29 items
	 * 
	 * 0 frei 1 unzerstörbar 2 zerstörbar 10 bombe 11 explosion 12 broeckeln 7
	 * Ausgang Triforce 20 bombentasche
	 */
	public static final int block0 = 0; // frei
	public static final int block1 = 1; // unzerstörbar
	public static final int block2 = 2; // zerstörbar
	public static final int ausgang = 7;
	public static final int bombe = 10;
	public static final int explosion = 11;
	public static final int broeckeln = 12;
	public static final int flyingarrow = 13;
	public static final int startarrow = 14;
	
	public static final int bombentasche = 20;
	public static final int reichweite = 21;
	public static final int bogen = 22;
	public static final int stiefel = 23;
	public static final int umhang = 24;

	public File lvltext;
	private int[][] feld = new int[15][11];
	private Scanner s;
	public int player1x, player1y, player2x, player2y; // array

	public Field() {
		standartlevel();

	}

	public void standartlevel() {
		player1x = 1;
		player1y = 1;
		player2x = 13;
		player2y = 9;
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
		feld[13][7] = 0;
		feld[12][9] = 0;
		feld[11][9] = 0;
		feld[1][1] = 0;
		feld[1][2] = 0;
		feld[2][1] = 0;
		feld[1][3] = 0;
		feld[3][1] = 0;
		// feld[1][3] = 7;
	}

	public void loadlevel(File f) {
		int mom;
		try {
			s = new Scanner(f);
			for (int j = 1; j < 10; j++) {
				for (int i = 1; i < 14; i++) {
					if (s.hasNextInt()) {
						mom = s.nextInt();
						if (mom == 0) {
							setArry(i, j, block0);
						} else if (mom == 1) {
							setArry(i, j, block1);
						} else if (mom == 2) {
							setArry(i, j, block2);
						} else if (mom == 7) {
							setArry(i, j, ausgang);
						} else if (mom == 8) { // startpunkt player 1
							setArry(i, j, 0);
							player1x = i;
							player1y = j;
						} else if (mom == 9) { // startpunkt player 2
							setArry(i, j, 0);
							player2x = i;
							player2y = j;
						}
					} else {
						setArry(i, j, 0);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Level nicht ladbar");
		}
	}

	public int getArry(int x, int y) {
		return feld[x][y];
	}

	public void setArry(int i, int j, int wert) {
		feld[i][j] = wert;
	}

	public boolean isWalkable(int x, int y) {
		int wert = feld[x][y];
		if (isItem(x, y))
			return true;
		if (wert == block0 || wert == explosion || wert == ausgang || wert == flyingarrow|| wert == startarrow)
			return true;
		else
			return false;
	}

	public boolean isBombe(int x, int y) {
		int wert = feld[x][y];
		if (wert == bombe)
			return true;
		else
			return false;
	}

	public boolean isItem(int x, int y) {
		int wert = feld[x][y];
		if (wert == bombentasche || wert == reichweite || wert == stiefel || wert == bogen || wert == umhang)
			return true;
		else
			return false;
	}

	public boolean kannManDraufLeben(int x, int y) {
		int wert = feld[x][y];
		if (isItem(x, y))
			return true;
		if (wert == block0 || wert == bombe || wert == ausgang || wert==startarrow)
			return true;
		else
			return false;
	}
	public boolean isArrow(int x, int y) {
		int wert = feld[x][y];
		if (wert == flyingarrow || wert == startarrow)
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
		if (isItem(x, y))
			return true;
		if (wert == block2)
			return true;
		else
			return false;
	}

	public void loadhelper() {
		if (Frame.leveltoload == 1) {
			lvltext = new File("level/level1.txt");
			loadlevel(lvltext);
		} else if (Frame.leveltoload == 2) {
			lvltext = new File("level/level2.txt");
			loadlevel(lvltext);
		} else if (Frame.leveltoload == 3) {
			lvltext = new File("level/level3.txt");
			loadlevel(lvltext);
		}
	}

	public void restart() {
		if (Frame.leveltoload == 0)
			standartlevel();
		else if (Frame.leveltoload == 1)
			loadhelper();
		else if (Frame.leveltoload == 2)
			loadhelper();
		else if (Frame.leveltoload == 3)
			loadhelper();
	}
}
