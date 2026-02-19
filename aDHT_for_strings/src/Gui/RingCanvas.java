package Gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.function.Function;

import javax.swing.JPanel;

import adht.node.Node;

//this class is a container for the nodes for making them as graphic instances
public class RingCanvas extends JPanel{
	private GraphicNode hovered = null; //variable for graphic hover
	public Coordinates center;
	private List <GraphicNode> nodes; //nodes that need to be istanziated;
	
	
	
	public RingCanvas() {
		setPreferredSize(new Dimension(600,600));
		this.center = new Coordinates();
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				hovered = null;
				for (GraphicNode n : nodes) {
					if((e.getX() >= n.getPositionX() && e.getX()<= n.getPositionX()+n.getSize()) && (e.getY() >= n.getPositionY() && e.getY() <= n.getPositionY()+n.getSize()))  {
						hovered = n;
						break;
					}
				}
				repaint();
			}
		});

	}
	
	public RingCanvas(List <GraphicNode> nodes) {
		this.nodes = nodes;
		setPreferredSize(new Dimension(600,600));
		 this.center = new Coordinates();
	}
	
	public void setNodes(List<GraphicNode> nodes) {
	    double increments = 2 * Math.PI / nodes.size();
	    double angle = -Math.PI / 2;

	    for (GraphicNode n : nodes) {
	        n.setAngle(angle);
	        angle += increments;
	    }

	    this.nodes = nodes;
	    repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    if (nodes == null || nodes.isEmpty()) return; //safeshield

	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    center.setX(getWidth() / 2);
	    center.setY(getHeight() / 2);

	    double radius = Math.min(getWidth(), getHeight()) / 2 - 40;

	    for (GraphicNode n : nodes) {
	        n.SetCoordinatesInCircle(center, radius);
	        n.draw(g2);
	        if (n == hovered) {
	        	n.drawHover(g2);
	        }
	    }
	}
	
}
