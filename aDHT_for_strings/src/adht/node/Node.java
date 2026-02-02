package adht.node;

public class Node {
	private int id;
	private char[] stored;
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
	
	public void populate(char[] chars) {
		int i = 0;
		for (char c : chars) {
			stored[i] = c;
			i++;
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
