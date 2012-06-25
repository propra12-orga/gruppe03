package bomber;

import javax.swing.*;
import java.awt.event.*;

public class Frame {
	public static boolean NeuesSpiel, laden;
	public static int leveltoload;
	// public Frame() {
	public Frame() {
		leveltoload=1;
		laden=false;
		Board.running = false;
		JFrame frame = new JFrame();
		// Menü
		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		JMenu menu = new JMenu("Game");
		JMenu laden = new JMenu("Level laden");
		JMenuItem level1 = new JMenuItem("Level1");
		JMenuItem level2 = new JMenuItem("Level2");
		JMenuItem level3 = new JMenuItem("Level3");
		JMenuItem neu = new JMenuItem("Neues Spiel");
		JMenuItem exit = new JMenuItem("Exit    [ESC]");
		menubar.add(menu);
		menu.add(neu);
		menu.add(laden);
		menu.add(exit);
		laden.add(level1);
		laden.add(level2);
		laden.add(level3);

		frame.add(new Board());
		frame.setTitle("our Bombing Game 2 gud");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(60 * 15 + 100, 60 * 11 + 70);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		// TODO Levelloading einbauen

		class neuAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Board.winner = 0;
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
		class level1Action implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Frame.laden=true;
				Frame.leveltoload=1;
			}
		}
		class level2Action implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Frame.laden=true;
				Frame.leveltoload=2;
			}
		}
		class level3Action implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				Frame.laden=true;
				Frame.leveltoload=3;
			}
		}
		exit.addActionListener(new exitAction());
		neu.addActionListener(new neuAction());
		level1.addActionListener(new level1Action());
		level2.addActionListener(new level2Action());
		level3.addActionListener(new level3Action());

	}

	public static void main(String[] args) {
		new Frame();
	}
}
