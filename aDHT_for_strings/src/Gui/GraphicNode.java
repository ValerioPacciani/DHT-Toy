package Gui;

import java.awt.Color;
import java.awt.Graphics2D;
import adht.node.*;
//this is the class component for a sigle graphic elemnt, node
public class GraphicNode{
	
	private int x;	 //coordinates
	private int y;
	private double angle; //respect center
	private Node logicnode; //refernce to the data node 
	private int size = 30; //size
	private Color color = Color.BLUE; //color

	public GraphicNode(Node logicnode) {
		if (logicnode.getIsStart()) {
			this.color = Color.red;
		}
		this.logicnode = logicnode;
		this.x = 0;
		this.y = 0;
		this.angle = 0;
	}
	
	public GraphicNode (int x,int y,Node logicnode,Coordinates center) {
		this.x =x;
		this.y = y;
		this.logicnode = logicnode;
		this.angle = Math.atan2((double) y - center.getY() ,((double) (x - center.getX()))); 
		
		
		
	}
	 public Node getLogicnode() {
		 return logicnode;
	 }
	 
	 public void setAngle(double angle) {
		this.angle = angle; 
	 }
	
	public void setAngle(Coordinates center) {
		if(this.x == 0 || this.y ==0) {
			System.out.println("errore, non posso calcolare l angolo di 0,0");
			return;
		} else {
			this.angle = Math.atan2((double) y - center.getY() ,((double) (x - center.getX())));
		}
	}
	public double getAngle() {
		return angle;
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
    						
    public void SetCoordinatesInCircle(Coordinates center,double radius) { //calculate the coordinates whit the component angle
    	double newX = center.getX() + radius*Math.cos(this.angle);
    	double newY = center.getY() + radius*Math.sin(this.angle);
    	this.setPosition((int)newX, (int)newY);
    }
	
}
