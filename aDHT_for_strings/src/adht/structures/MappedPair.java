package adht.structures;
//this class is for creating a map (key-> value) for hashing the content
public class MappedPair {
	private long hashedkey;
	private char content;
	private int place;
	
	public MappedPair(long hashedkey,char content,int place) {
		this.hashedkey = hashedkey;
		this.content = content;
		this.place = place;
	}
	public long getHashedkey() {
		return hashedkey;
	}
	public char getContent(long hashedkey){
		return content;
	}
	public int getPlace(long hashedkey) {
		return place;
	}
	
}
