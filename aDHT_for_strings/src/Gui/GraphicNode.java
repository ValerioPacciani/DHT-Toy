package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import adht.node.*;
//this is the class component for a sigle graphic elemnt, node
public class GraphicNode{
	
	private int x;	 //coordinates (respect 0,0 of panel)
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
	public int getSize() {
		return size;
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
    	g.setColor(new Color(200,0,0,100));
        g.fillRoundRect(x, y, size, size,10,10);

        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, size, size,10,10);
    }

    
    public void drawHover(Graphics2D g) {
    	double centerx = x+size/2;
    	double centery = y+size/2; //calcolo il centro del nodo
    	AffineTransform base = g.getTransform(); //trasformazione base
    	g.translate(centerx, centery); //translate the draw for being into the node
    	g.scale(5,5); //scale the node
    	g.translate(-centerx, -centery);
    	//get the data
    	String hashkey = new String();
    	String id = new String();
    	String ch = new String();
    	hashkey = String.valueOf(logicnode.getHashidkey());
    	id = String.valueOf(logicnode.getid());
    	ch = "CHAR = ";
    	//logic for draw strings
    	for(char c : logicnode.readChars()) {
    		ch = ch.concat(String.valueOf(c)+" ");
    		
    	}
    	if(ch.length() > 17) {
			ch = ch.substring(0,17)+ "...";
		}
    	g.setFont(new Font("arial",Font.PLAIN,3));
    	g.drawString("ID = "+id,x+2,y+5);	
    	g.drawString("KEY = "+hashkey,x+2,y+15);
    	g.drawString(ch,x+2,y+25);
    	g.drawRoundRect(x, y, size, size,10,10);
    	g.setColor(new Color(255,0,0,130));
    	g.fillRoundRect(x, y, size, size,10,10);
    	g.setTransform(base);
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
