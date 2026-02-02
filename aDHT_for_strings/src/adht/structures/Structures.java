package adht.structures;
import adht.node.*;
//this class is for the methods to create different type of structures

public class Structures {
	public static final long RANGE_MAX = 4294967295L; //massimo valore di mappatura

	
	public static Node createRing(int numberOfNodes) {
	Node first = null;
	Node prev = null;
		 
	for (int i = 0; i< numberOfNodes; i++ ) {
	Node node = new Node();
		if  (first == null) { //se siamo all inizio dell annello
				node.setid(0);
				first = node; //il nodo che ho appena creato diventa il primo		 
			} else {
				node.setid(i);
				prev.setNext(node); //se non è il primo allora lo setto come prossimo del precdente
			 }
			 prev = node; // il precedente diventa il nodo corrente
		 } 
	
		prev.setNext(first); //l ultimo ha il primo come next (circolare


		return first;
	}
	
	public static void OrderNodeHash(Node start) {
		boolean somethingswapped = false;
	
		do {
		somethingswapped = false;
		//FIXME the start node is not updated, so the cycle right now is infinite
		Node prev = start;
		Node current = prev.getNext();
		Node next = current.getNext();
		do  {
			long key = current.getHashidkey();
			long keynext = next.getHashidkey();
			if (key >= keynext) {
				prev.setNext(next);
				current.setNext(next.getNext());
				next.setNext(current);
				
				prev = next;
				next = current.getNext();
				
				somethingswapped = true;
				
			} else {
				prev = current;
				current = next;
				next = next.getNext();
			}
		} while (current != start);	
		
	} while(somethingswapped); //control flag, go out when i control all the cycle and nothing get swapped
	}
	
	public static void PopulateRing(Node start,MappedPair[] content,String string) {
		for (int i = 0; i< string.length(); i++) {
			//TODO function
		}
	}
	public static void DistributeHash(Node start) { //distribute the mapped content keys into the mapped node ids
		Node current =start;
		do  {
			current.setHashidkey(MappingOfIds(current.getid()));
			current = current.getNext();
			 //we are getting the nodes next of the current (is for the while statement)
		} while (current != start);	
		}
	
	
	public static MappedPair[] MappingContent(String content) { //map the single character to his hash key -> it returns an object that is actually a map key-> c
		MappedPair[] map = new MappedPair[content.length()];
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			long hash = MurmurHashx32.hash32(Integer.toString(c));
			map[i] = new MappedPair(hash,c,i);
		}
		return map;
		}
	//IMPORTANT: this has to be used every time i use a id map for normalize it -> maybe add it directly to the hash function
	public static long MappingOfIds(int nodeId) {
		long hash =  MurmurHashx32.hash32(Integer.toString(nodeId));
		return hash & 0xFFFFFFFFL; //normalize the hash output(is a mask for remove the sign)
	}
}
