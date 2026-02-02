package Gui;
import javax.swing.*;

public class Gui {
	
	//remember to set the parameter for the gui (so i have to take some sort of state)   
	public static void CreateGui() {
	JFrame app = new JFrame("Adht nodes");
	JLabel star = new JLabel ("hello");
	app.getContentPane().add(star);
	
	app.pack();
	app.setVisible(true);
	
}
}
