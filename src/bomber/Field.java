package bomber;

public class Field {
	
	public int[][] feld = new int [15][11];
	
	public Field(){
		for (int in = 0; in < 15; in++) {
			for (int jn = 0; jn < 11; jn++) {

				if (in == 0 || jn == 0 || in == 14 || jn == 10
						|| (in % 2 == 0 && jn % 2 == 0))
					feld[in][jn] = 1;
				else
					feld[in][jn] = 0;
			}
		}

	} 
	public int getArry(int i, int j){	
		return feld[i][j];
	}
	public void setArry(int i, int j, int wert){
		feld[i][j]=wert;
	}
}


