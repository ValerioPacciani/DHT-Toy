package Gui;
import java.awt.Dimension;

import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.*;

import adht.node.*;
import adht.structures.Controller;
import adht.structures.Sender;

public  class RingForm extends JPanel{
	
		Controller controller;
		private Node start;
		
		
		JSpinner numberofnodes;
		JTextField stringtomap;
		JButton sendbutton;
		JButton addnodebutton;
		private Function <RingFormData,Node> onSubmit;
		private Runnable  onNewNodeAdded; //callback for node adding 
		
	

	public RingForm(Controller controller) {
		this.controller = controller;
		numberofnodes = new JSpinner();
		numberofnodes.setPreferredSize(new Dimension(30,30));
		numberofnodes.setToolTipText("number of ring nodes");
		
		stringtomap = new JTextField();
		stringtomap.setPreferredSize(new Dimension(200,30));
		stringtomap.setToolTipText("insert the string that have to be mapped into the nodes");
		
		sendbutton = new JButton("send");
		sendbutton.setToolTipText("send the data and create structure");
		sendbutton.setPreferredSize(new Dimension(80,30));
		sendbutton.addActionListener(e-> handlesubmit());
		
		addnodebutton = new JButton("add");
		addnodebutton.setToolTipText("add a new node to the structure, id and key will be calcolauted automatically");
		addnodebutton.setPreferredSize(new Dimension(80,20));
		addnodebutton.addActionListener(e-> handleaddnewnode());
		
		
		this.add(numberofnodes);
		this.add(stringtomap);
		this.add(sendbutton);
		this.add(addnodebutton);
	}
	
	public void setOnSubmit(Function<RingFormData,Node> onSubmit) { //callback
        this.onSubmit = onSubmit;
    }
	public void setonNewNodeAdded(Runnable onNewNodeAdded) {
		this.onNewNodeAdded = onNewNodeAdded;
	}

	
	
	public void handlesubmit() {
		int nodes = (int)numberofnodes.getValue();
		String text = stringtomap.getText();
		
		RingFormData record = new RingFormData(nodes,text);
		
		onSubmit.apply(record); //called the lamda fuction that send the datas
		sendbutton.setEnabled(false);
		}

	public void handleaddnewnode() {
		//FIXME should be not callable if the ring still not created
		controller.addNode(start);
		onNewNodeAdded.run();
		}
	
	
	//setter and getter (if needed)
	
	public void setStart(Node start) {
		this.start = start;
	}
	
	
	

}
