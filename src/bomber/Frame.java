package bomber;

import javax.swing.JFrame;

public class Frame {

	public Frame(){
		JFrame frame = new JFrame();
		frame.add(new Board());
		frame.setTitle("our Bombing Game 2 gud");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,756);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	public static void main(String[] args){
		new Frame();
	}
}
