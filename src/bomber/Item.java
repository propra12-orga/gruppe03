package bomber;

public class Item {
	public static final int bombentasche = 20;
	public static final int reichweite = 21;
	public static final int bogen = 22;
	public static final int umhang = 23;
	public static final int stiefel = 23;

	int itemwahl, x, y;

	// arraypos
	public Item(int xPos, int yPosx, int Item, Field feld) {
		itemwahl = Item;
		x=xPos;
		y=xPos;
		feld.setArry(x, y, itemwahl);
	}
}
