package bomber;

public class Field {
	
	private int[][] feld = new int [15][11];
	
	public Field(){
		for (int in = 0; in < 15; in++) {
			for (int jn = 0; jn < 11; jn++) {

				if (in == 0 || jn == 0 || in == 14 || jn == 10|| (in % 2 == 0 && jn % 2 == 0))
					feld[in][jn] = 1;
				else
					feld[in][jn] = 0;
				if(in%2==0 && jn%2==0 && in>3 && jn>3 &&in<9 && jn<9)
					feld[in-1][jn-1] = 2;
			}
		}
	} 
	public int getArry(int i, int j){	
		return feld[i][j];
	}
	public void setArry(int i, int j, int wert){
		feld[i][j]=wert;
	}
	
	public boolean isWalkable(int x, int y){
		int wert =feld[x][y];
		if (wert==0||wert==11)
			return true;
		else
			return false;
	}
}


