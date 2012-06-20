package bomber;

import javax.swing.*;
import java.awt.event.*;

public class Frame {
	public static boolean NeuesSpiel;

	// public Frame() {
	public Frame() {
		Board.running = false;
		JFrame frame = new JFrame();
		// MEnü
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		JMenu menu = new JMenu("Game");
		JMenuItem neu = new JMenuItem("Neues Spiel");
		JMenuItem exit = new JMenuItem("Exit");
		menubar.add(menu);
		menu.add(neu);
		menu.add(exit);
	
		frame.add(new Board());
		frame.setTitle("our Bombing Game 2 gud");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(60 * 15 + 100, 60 * 11 + 70);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		class neuAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (Board.running == false) {
					Board.running = true;
				} else {
					Board.running = false;
					Board.neuesSpiel = true;
				}
			}
		}
		class exitAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}

		exit.addActionListener(new exitAction());
		neu.addActionListener(new neuAction());
	}

	public static void main(String[] args) {
		new Frame();
	}
}
