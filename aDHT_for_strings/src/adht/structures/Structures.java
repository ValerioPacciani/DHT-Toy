package adht.structures;
import adht.node.*;
//this class is for the methods to create different type of structures

public class Structures {
	public final long RANGE_MAX = 4294967295L; //massimo valore di mappatura

	
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
	
		prev.setnext(first); //l ultimo ha il primo come next (circolare


		return first;
	}
	
	
	public void DistributeHash(Node start,MappedPair[] map) { //distribute the mapped content keys into the mapped node ids
		Node current =start;
		do  {
			current.setHashidkey(MappingOfIds(current.getid()));
			current = current.getnext();
			 //we are getting the nodes next of the current (is for the while statements
		} while (current != start);	
		}
	
	
	public MappedPair[] MappingContent(String content) {
		MappedPair[] map = new MappedPair[content.length()];
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			long hash = MurmurHashx32.hash32(Integer.toString(c));
			map[i] = new MappedPair(hash,c);
		}
		return map;
		}
	
	public long MappingOfIds(int nodeId) {
		long hash =  MurmurHashx32.hash32(Integer.toString(nodeId));
		return hash & 0xFFFFFFFFL; //normalize the hash output(is a mask for remove the sign)
	}
}
