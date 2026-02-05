package adht.structures;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adht.node.*;
//this class is for the methods to create different type of structures

public class Structures {
	public static final long RANGE_MAX = 4294967295L; //massimo valore di mappatura

	
	public static Node createRing(int numberOfNodes) {  //create ring
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
	
	public static Node findMin(Node start) { //find min into the nodes ring by hashed key
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
	
	public static Node OrderByKey(Node start) {  // ordina i nodi per chiave e non per id
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
	    
	public static void PopulateRing(Node start,MappedPair[] content) { //metti il content mappato dentro i nodi corretti
		int k = 0;
		for (int i = 0;i< content.length; i++) { //string.lenght = mappedPair[].length
			k=0;
		Node current = start;
			do {
				
				if (content[i].getHashedkey() > current.getHashidkey() && content[i].getHashedkey() < current.getNext().getHashidkey()) {
					current.populate(content[i]);
					current = current.getNext();
					k++;
					System.out.println("ho incrementato current di 1 e ho aggiunto content");
				
				}
				else if (content[i].getHashedkey() > start.getHashidkey() && content[i].getHashedkey() > current.getHashidkey()  ){ //FIXME wrap around case (it's not working right noe
					current.populate(content[i]);
					current = current.getNext();
					System.out.println("wrap around case");
				}else {
					current = current.getNext();
					System.out.println("ho incrementato current di 1");
					k++;
				}
			} while (k < 10);
		}
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
	public static long CreateHashIds(int nodeId) {  //crea una chiava ash per ogni nodo
		long hash =  MurmurHashx32.hash32(Integer.toString(nodeId));
		return hash & 0xFFFFFFFFL; //normalize the hash output(is a mask for remove the sign)
	}
	
	public static void DistributeHash(Node start) { //distribute the mapped content keys into the mapped node ids (create it in aoutamatic)
		Node current =start;
		do  {
			current.setHashidkey(CreateHashIds(current.getid()));
			current = current.getNext();
			 //we are getting the nodes next of the current (is for the while statement)
		} while (current != start);	
		}
}
