package Gui;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import adht.node.*;
//this is a single node graphic component
public class NodeFrame {
	private Node logicalnode; //this is a pointer to the logical node 
	private Rectangle Bounds;
	private Color color = Color.pink;


	public NodeFrame(Node Logical)  {
		this.logicalnode = Logical; //it has to be linked to a real node for displyng info
		this.Bounds = new Rectangle(0,0,30,30);
	}
	
	public void setPosition ( int x, int y) { //this has to be used for determine the popsition of the node it will depends on the type of the data structure and the number of nodes
		Bounds.setLocation(x - Bounds.width/2, y - Bounds.height/2);
	}
	
	public void draw(Graphics2D graphic) {
		graphic.setColor(color);
		graphic.fill(Bounds);
		
		 graphic.setColor(Color.BLACK);
	     graphic.draw(Bounds);

	}
	
	
	
	

}