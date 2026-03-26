package adht.structures;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adht.node.*;
//this class is for the methods to create different type of structures

public class Structures {
	public static final long RANGE_MAX = 4294967295L; //massimo valore di mappatura

	
	/*----------------------------------------------------------------------------------
	 * Methods for structure creation
	 */
	public static int countRingEntries(Node start) {
		int c = 0;
		Node current = start;
		do {
			c++;
			current = current.getNext();
		} while (current != start);
	return c;
	}
	
	
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
	    // create  list (buffer)
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

	    //return the start as min
	    return nodes.get(0);
	    }
	    
	public static void PopulateRing(Node start,MappedPair[] content) { //metti il content mappato dentro i nodi corretti
	
		for (int i = 0;i< content.length; i++) { //string.lenght = mappedPair[].length
			
		Node current = start;
			do {
				if (content[i].getHashedkey() < current.getHashidkey() && current == start){ 
					current.populate(content[i]);
					current = current.getNext();
					
					System.out.println("wrap around case");
					break;
				} else if (content[i].getHashedkey() >= current.getHashidkey() && content[i].getHashedkey() < current.getNext().getHashidkey()) {
					current.populate(content[i]);
					current = current.getNext();
					System.out.println("content");
					break;
				} else if (content[i].getHashedkey() > current.getHashidkey() && current.getNext() == start) {
					current.populate(content[i]);
					current = current.getNext();
					System.out.println("wrap around case");
					break;
				} else {
					current = current.getNext();
					System.out.println("pass ->");
				}
			} while (current != start);
		}
	}
	
	
	public static MappedPair[] MappingContent(String content) { //map the single character to his hash key -> it returns an object that is actually a map key-> c
		MappedPair[] map = new MappedPair[content.length()];
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			long hash = MurmurHashx32.hash32(String.valueOf(c) + i); //the hashed function there is taking the value of c and his index (for avoid repetition -> for example every "c" will be mapped in the same node if i don't add the randomness of the i"
			hash = hash & 0xFFFFFFFFL; //normalize the hash output
			map[i] = new MappedPair(hash,c,i);
		}
		return map;
		}
	//IMPORTANT: this has to be used every time i use a id map for normalize it -> maybe add it directly to the hash function
	public static long CreateHashIds(int nodeId) {  //crea una chiava ash per ogni nodo
		long hash =  MurmurHashx32.hash32(Integer.toString(nodeId));
		return hash & 0xFFFFFFFFL; //normalize the hash output(is a mask for remove the sign)
	}
	
	public static void DistributeHash(Node start) { //distribute the node hash(id) into the mapped node ids (create it in aoutamatic)
		Node current =start;
		do  {
			current.setHashidkey(CreateHashIds(current.getid()));
			current = current.getNext();
			 //we are getting the nodes next of the current (is for the while statement)
		} while (current != start);	
		}
	
	
	public static void PrintRIng(Node start) { //THIS HAS TO BE USED ONLY FOR DEBUG PURPOSE; PRINT WILL BE ON THE GUI SIDE
		 Node Current = start;
		 do {
			 System.out.println("id" + Current.getid());
			 System.out.println("hash" + Current.getHashidkey());
			 System.out.println("-------------------------------");
			 
			 for (int k = 0; k< Current.getFingerTable().size(); k++) {
				 System.out.println("hash of fingertable " +Current.getFingerTable().get(k).getHashidkey() +" of entry index: " + k);
			 }
			 
			 Current.debugprint();

			 Current = Current.getNext();
		 } while(Current != start);
		 
	 }
	



/*------------------------------------------------------------------------------------------------------------------------
 * 
 * CHORD LOGIC FOR FINGER TABLE
 * 
 -------------------------------------------------------------------------------------------------------------------------*/
	public static Node Predecessor(Node node, Node start)  { //this functions find the predecessor to a given node
		Node prev = null;
		Node current = start;
		do {
			prev = current;
			current = current.getNext();
		} while( node.getHashidkey() != current.getHashidkey());
		return prev;
	}
	
	
	public static Node Succesor(Node start, long key) { //find first node responsible for key in in input (it is utilized for chord populate later)
	Node current = start;
	do {
		if (key < current.getHashidkey() && current == start){ 
			return current;
		} else if (key>= current.getHashidkey() && key < current.getNext().getHashidkey()) {
			return current;
		} else if (key > current.getHashidkey() && current.getNext() == start) {
			return current;
		} current = current.getNext();
		
	} while (current != start);
	return  null;
}
	
public static void populateFingerTable(Node node ,Node start) {
	node.getFingerTable().clear(); 
	
	for (int i = 0; i<32;i++) {
		long fingerkey = (node.getHashidkey() + (1L << i)) % (RANGE_MAX+1); //this operation compute the key+2^i so i can update the finger 
		Node succesor = Structures.Succesor(start, fingerkey);
		node.setFingerTable(succesor);
	}
}


public static void buildAllFingerTables(Node start) { 
    Node current = start;
    do {
        populateFingerTable(current, start);
        current = current.getNext();
    } while (current != start);
}

public static Node chordSuccessor(Node start, Node compare) {
    Node closestPreceding = null;

    for (int i = 0; i < start.getFingerTable().size(); i++) {
        Node finger = start.getFingerTable().get(i);

        if (finger.getHashidkey() <= compare.getHashidkey()) {
            closestPreceding = finger;
        } else {
            break;
        	} 
    	}	if (closestPreceding == null) { //significa che non ho trovato niente quindi il nodo piu vicino è start
        	return start;
        } else if (closestPreceding.getHashidkey() == compare.getHashidkey()) {
        	return closestPreceding; //nodo esatto
        }
    	return chordSuccessor(closestPreceding, start);
    }

	
/* -----------------------------------------------------------------------------------------------
 * Node Manipluation methods
 */


public static void addNode(Node newnode,Node start) {
	newnode.setid(Structures.countRingEntries(start)+1); //set id
	newnode.setHashidkey(CreateHashIds(newnode.getid())); //set hashkey
	
	Node successor = chordSuccessor(start,newnode);
	Node predecessor = Predecessor(successor,start);
	
	predecessor.setNext(newnode);
	newnode.setNext(successor);
	System.out.println("added node id:" + newnode.getid());
	
	}
}




/* ________________________________________________________________________________________________________-
 * Data maniupulation methods
 ____________________________________________________________________________________________________________*/


