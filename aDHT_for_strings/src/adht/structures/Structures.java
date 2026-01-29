package adht.structures;
import adht.node.*;
//this class is for the methods to create different type of structures

public class Structures {

	public Node createRing(int numberOfNodes) {
	Node first = null;
	Node prev = null;
		 
	for (int i = 0; i< numberOfNodes; i++ ) {
	Node node = new Node();
		if  (first == null) { //se siamo all inizio dell annello
				node.setid(0);
				first = node; //il nodo che ho appena creato diventa il primo		 
			} else {
				node.setid(i);
				prev.setnext(node); //se non è il primo allora lo setto come prossimo del precdente
			 }
			 prev = node; // il precedente diventa il nodo corrente
		 } 
		return first;
	}
	
	
}
