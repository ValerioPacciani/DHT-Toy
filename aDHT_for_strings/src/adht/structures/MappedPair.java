package adht.structures;
//this class is for creating a map (key-> value) for hashing the content
public class MappedPair {
	private long hashedkey;
	private char content;
	
	public MappedPair(long hashedkey,char content) {
		this.hashedkey = hashedkey;
		this.content = content;
	}
	
	public char getContent(long hashedkey){
		return content;
	}
}
