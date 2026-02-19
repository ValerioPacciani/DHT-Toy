package adht.node;
import adht.structures.*;
import java.util.*;

public class Node {
	private boolean isStart = false;
	private int id;
	public List<MappedPair> charmap = new ArrayList<>();//FIXME it should be private
	private List<Node> FingerTable = new ArrayList<>();
	private Node next;
	private Node prev;
	private long hashidkey;
	
	public void setHashidkey(long key) {
		this.hashidkey = key;
	}
	public char[] readChars () {
		char[] c = new char[charmap.size()];
		int i = 0;
		for (MappedPair n : charmap) {
			c[i] = n.getContent();
			i++;
		} return c;
	}
	
	public void debugprint() { //debug for know how is mapping
		for (MappedPair n : charmap) {
			System.out.println("char: "+n.getContent());
			System.out.println("key: "+n.getHashedkey());
			System.out.println("place "+n.getPlace());
			System.out.println("__________________________________________________");
		}
	}
	
	
	public long getHashidkey() {
		return hashidkey;
	}
	
	public void setIsStart(boolean state) {
		this.isStart = state;
	}
	
	public boolean getIsStart() {
		return isStart;
	}
	
	public int getid() {
		return id;
	}
	public void setid(int i) {
		this.id = i;
	}
	
	public void populate (MappedPair content) {
		if (charmap.isEmpty()) {
			charmap.add(0,content);
		} else {
			charmap.add(content);
		}
	}
	public void read() {
	
		for (MappedPair content: charmap) {
			System.out.println(content.getContent());
			}
		}
 
	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setFingerTable(Node Entry) {
		this.FingerTable.add(Entry);
	}

	
}
