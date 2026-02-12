package Gui;

public class RingFormData {
	private int nnodes;
	private String text;
	
	public RingFormData(int nodes, String text) {
		this.nnodes = nodes;
		this.text = text;
	}
	
	public int getNNodes(){
		return nnodes;
	}
	public String getText() {
		return text;
	}
}

