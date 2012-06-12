package bomber;

import javax.swing.JFrame;

public class Frame {

	public Frame(){
/*		JFrame menu = new JFrame();
		menu.add(new Menu());
		menu.setTitle("our Bombing Game 2 gud");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(1000,700);
		menu.setVisible(true);
		menu.setLocationRelativeTo(null);
	*/	
		JFrame frame = new JFrame();
		frame.add(new Board());
		frame.setTitle("our Bombing Game 2 gud");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(60*15+100,60*11+46);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	public static void main(String[] args){
		new Frame();
	}

}