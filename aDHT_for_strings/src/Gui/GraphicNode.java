package Gui;

import java.awt.Color;
import java.awt.Graphics2D;

import adht.node.*;
//this is the class component for a sigle graphic elemnt, node
public class GraphicNode{
	
	private int x;	 //coordinates
	private int y;
	private Node logicnode; //refernce to the data node 
	private int size = 30; //size
	private Color color = Color.BLUE; //color

	public GraphicNode(Node logicnode) {
		this.logicnode = logicnode;
		this.x = 0;
		this.y = 0;
	}
	
	public GraphicNode (int x,int y,Node logicnode) {
		this.x =x;
		this.y = y;
		this.logicnode = logicnode;
		
		
	}
	
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }

    public void setPosition(int x,int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public int getPositionX() {
    	return x;
    }
    public int getPositionY() {
    	return y;
    }
	
}
