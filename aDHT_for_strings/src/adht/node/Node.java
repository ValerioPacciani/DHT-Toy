package adht.node;
import adht.structures.*;
import java.util.*;

public class Node {
	private int id;
	public List<MappedPair> charmap = new ArrayList<>();//FIXME it should be private
	private Node next;
	private Node prev;
	private long hashidkey;
	
	public void setHashidkey(long key) {
		this.hashidkey = key;
	}
	
	public long getHashidkey() {
		return hashidkey;
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
	
}
