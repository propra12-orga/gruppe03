package bomber;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	Figur bomber1;
	public Image img,block0,block1,bomb;
	Timer time;
	int [][]feld= new int[9][6];
	boolean ini;

	public Board() {
		
		for (int in =0; in<9; in++){
			for (int jn=0; jn<6;jn++){
				if (in%2==1 && jn%2==1)
				feld[in][jn]=0;
				else
					feld [in][jn]=1;
			}
		}
		
		bomber1 = new Figur(0,0);
		addKeyListener(new AL());
		setFocusable(true);
		
		ImageIcon i1 = new ImageIcon("feld.png");
		img = i1.getImage();
		
		ImageIcon i2 = new ImageIcon("block0.jpg");
		block0 = i2.getImage();
		
		ImageIcon i3 = new ImageIcon("block1.jpg");
		block1 = i3.getImage();
		
		ImageIcon i4 = new ImageIcon("bombe2.png");
		bomb = i4.getImage();
		
		time = new Timer(5, this);
		time.start();
		
		
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		
		
		g2d.drawImage(img, 0, 0, null);
		for (int i =0; i<9; i++){
			for (int j=0; j<6;j++){
				
				if (feld[i][j]==0){
					g2d.drawImage(block0, i*100, j*100, null);}
				else if(feld[i][j]==1){
					g2d.drawImage(block1, i*100, j*100, null);}
				else if(feld[i][j]==10){
						g2d.drawImage(bomb, i*100, j*100, null);}
				}		
			}
		g2d.drawImage(bomber1.getImage(), bomber1.getX(), bomber1.getY(), null);
	}

	public void setBomb(int x, int y, int radi) {
		feld[x/100][y/100]=10;
		
	}
	
	private class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			bomber1.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			bomber1.keyPressed(e);
	//		if (key == KeyEvent.VK_SPACE)
		//		setBomb(bomber1.getX(),bomber1.getY(),bomber1.radi);
		}
	}

	
}
