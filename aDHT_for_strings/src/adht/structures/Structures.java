package adht.structures;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	
	public static Node findMin(Node start) {
	    Node current = start.getNext();
	    Node min = start;

	    while (current != start) {
	        if (current.getHashidkey() < min.getHashidkey()) {
	            min = current;
	        }
	        current = current.getNext();
	    }

	    return min;
	}
	
	public static Node OrderByKey(Node start) {
	    if (start == null || start.getNext() == start) {
	        return start; // check if ring is one node
	    }
	    // 1, create  list
	    List<Node> nodes = new ArrayList<>();
	    Node current = start;
	    do {
	        nodes.add(current);
	        current = current.getNext();
	    } while (current != start);

	    // calling ording function form util
	    nodes.sort(Comparator.comparingLong(Node::getHashidkey));

	    // remake the ring
	    for (int i = 0; i < nodes.size() - 1; i++) {
	        nodes.get(i).setNext(nodes.get(i + 1));
	    }
	    nodes.get(nodes.size() - 1).setNext(nodes.get(0)); //make the last node having next as first

	    // 4. return the start as min
	    return nodes.get(0);
	    }
	    
	static void PopulateRing(Node start,MappedPair[] content,String string) {
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
