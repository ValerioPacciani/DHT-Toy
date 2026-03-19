package adht.structures;

import Gui.RingFormData;
import adht.node.*;

public class Controller {
	boolean success = false;
	
	
	public Node  HandleRingForm(RingFormData data) {
		int nodes = data.getNNodes();
		String text = data.getText();
		
		Node Start= Structures.createRing(nodes);
		MappedPair[] content =Structures.MappingContent(text);
		Structures.DistributeHash(Start);
		Start = Structures.OrderByKey(Start);
		Structures.PopulateRing(Start, content);
		Structures.buildAllFingerTables(Start);
		
		
		
		
		Start.setIsStart(true);
		success = true;
		
		
		
		return Start;
		
	}
	
	public void addNode(Node start) {
		System.out.println("ciao mamma aggiungo un nodo");
		Node newnode = new Node();
		Structures.addNode(newnode, start);
		Structures.buildAllFingerTables(start); //this is terrible for a real word implementation o(N) every time i add a new node
		
		Structures.PrintRIng(start);
		
	}
}

