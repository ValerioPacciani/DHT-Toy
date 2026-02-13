package adht.app;
import adht.structures.*;
import Gui.*;
/* implementnazione di una adht giocattolo sulle stringhe estramente inefficiente ma divertente */

public class Main {
 public static void main (String[] args) {
	 
	 
	 Controller controller = new Controller();
	 Gui Window = new Gui(controller);
	 
	 Window.setVisible(true);
 }
 }
