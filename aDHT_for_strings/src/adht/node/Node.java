package adht.node;

public class Node {
	private int id;
	private char[] stored;
	private Node next;
	
	public void setnext(Node next) {
		this.next = next;
	}
	
	public Node getnext() {
		return next;
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
	
}
