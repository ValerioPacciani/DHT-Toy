package adht.structures;

import java.util.ArrayList;
import java.util.List;

import Gui.GraphicNode;
import adht.node.Node;

//this class is a sort of bridge, there will be all the fuction for sendig the data to the GUI
public class Sender {
	public static List <GraphicNode> packGraphics(Node Start){
		Node current = Start;
		List <GraphicNode> graph  = new ArrayList<>();
		int x = 0;
		int y = 0;
		do {
			graph.add(new GraphicNode(x,y,current));
			current = current.getNext();
			x = x+50;
			y= y+30;
		} while (current != Start);
		return graph;
	}
}
