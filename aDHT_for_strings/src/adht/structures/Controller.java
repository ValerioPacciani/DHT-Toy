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
		
		Structures.PrintRIng(Start);
		
		return Start;
	}
}

