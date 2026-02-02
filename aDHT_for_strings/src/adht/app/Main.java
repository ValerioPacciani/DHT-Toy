package adht.app;
import adht.node.*;
import adht.structures.*;
import java.util.Scanner;
import Gui.*;
/* implementnazione di una adht giocattolo sulle stringhe estramente inefficiente ma divertente */

public class Main {
 public static void main (String[] args) {
	 
	 Gui.CreateGui();
	 
	 
	 System.out.println("Scegliere il numero di nodi con cui avere la rete di overlay (Anello)");
	 @SuppressWarnings("resource") //i need/want the stream always open 
	 Scanner input = new Scanner(System.in);
	 
	 //Structures structures = new Structures();
	 //TODO magari implementare una sorta di scelta della struttura anello, ipercubo etc
	 
	 int numberOfNodes = input.nextInt();
	 
	 System.out.println("Inserire stringa da mappare:");
	 
	// String content = input.next();
	 
	 //System.out.println(structures.MappingContent(content)[0]);
	 
	 Node start = Structures.createRing(numberOfNodes);
	 Structures.DistributeHash(start, null);
	 
	 Node current = start;
	 for (int k = 0; k< numberOfNodes; k++ ) {
		 
		 System.out.println(current.getHashidkey());
		 System.out.println("/////////////////////////////////////////////////////////////////////////");
		 
		 current = current.getnext();
	 	}
	 
 }
 }
