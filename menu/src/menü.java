

import javax.swing.*;
import java.awt.event.*;

public class men�{
	
	public static void main (String[] args){
		
		JFrame frame = new JFrame();
		frame.setTitle("BombermanZ");
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu datei = new JMenu ("Datei");
		
		JMenuItem �ffnen = new JMenuItem("�ffnen");
		JMenuItem speichern = new JMenuItem ("Speichern");
		JMenuItem neustart = new JMenuItem ("Neustart");
		JMenuItem beenden = new JMenuItem ("Beenden");
		
		datei.add(�ffnen);
		datei.add(speichern);
		datei.add(neustart);
		datei.addSeparator();
		datei.add(beenden);
		
		beenden.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				System.exit(0);
				
			}
		});
		
		menuBar.add(datei);
		
		frame.getContentPane().add(panel);
		
		frame.setVisible(true);
	
}}