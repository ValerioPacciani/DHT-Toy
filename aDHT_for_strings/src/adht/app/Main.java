package adht.app;
import adht.node.*;
import adht.structures.*;
import java.util.Scanner;

/* implementnazione di una adht giocattolo sulle stringhe estramente inefficiente ma divertente */

public class Main {
 public static void main (String[] args) {
	 System.out.println("Scegliere il numero di nodi con cui avere la rete di overlay (Anello)");
	 @SuppressWarnings("resource") //i need/want the stream always open 
	 Scanner input = new Scanner(System.in);
	 
	 Structures structures = new Structures();
	 //TODO magari implemtnare una sorta di scelta della struttura anello, ipercubo etc
	 
	 int numberOfNodes = input.nextInt();
	 Node start = structures.createRing(numberOfNodes);
	 
	 Node current = start;
	 for (int k = 0; k< numberOfNodes; k++ ) {
		 
		 System.out.println(structures.MappingOfIds(start,current.getid()));
		 System.out.println("/////////////////////////////////////////////////////////////////////////");
		 
		 current = current.getnext();
	 	}
	 
 }
 }
