package Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

//this class is a container for the nodes for making them as graphic instances
public class RingCanvas extends JPanel{
	private List <GraphicNode> nodes; //nodes that need to be istanziated;
	
	
	public RingCanvas() {
		setPreferredSize(new Dimension(600,600));
	}
	
	public RingCanvas(List <GraphicNode> nodes) {
		this.nodes = nodes;
		setPreferredSize(new Dimension(600,600));
	}
	
	public void setNodes(List<GraphicNode> nodes) {
        this.nodes = nodes;
        repaint(); //we need to update the graphic
    }


	@Override
	protected void paintComponent(Graphics g) {
		if (nodes == null) {
			return;// we check if there is actaully somehing to paint !! safeguard
		}
	    super.paintComponent(g); //  we call the super for cleaning the backgground every time we draw something new
	    
	    Graphics2D g2 = (Graphics2D) g;

	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //this is for the antilaisng (better graphics overall)

	    for (GraphicNode n : nodes) {
	    	//TODO set the x and y inside here
	        n.draw(g2);
	    }
	}

	
}
