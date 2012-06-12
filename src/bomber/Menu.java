/*package bomber;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;


/*public class Menu extends JFrame {
    
    public Menu() {
        
        setTitle("Optionen");
        setSize(200, 200);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Optionen");
       
        menuBar.add(fileMenu);
      
       
        JMenuItem newAction = new JMenuItem("Start");
        JMenuItem openAction = new JMenuItem("Level Laden");
        JMenuItem exitAction = new JMenuItem("Exit");
       
        
     
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(exitAction); 
      
        public class Menu extends JFrame{
        	  
        	  public Menu() {
        	    JButton button = new JButton("Test");
        	    button.addActionListener(new ActionListener() {
        	      public void actionPerformed(ActionEvent e) {

        	        new Frame2();
        
            

        	      }
    }
  /* public static void main(String[] args) {
        Menu me = new Menu();
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
    }
}
*/