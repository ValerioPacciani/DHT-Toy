package Gui;
import java.awt.Dimension;
import java.util.function.Consumer;

import javax.swing.*;

public  class RingForm extends JPanel{
	
		JSpinner numberofnodes;
		JTextField stringtomap;
		JButton sendbutton;
		
		private Consumer <RingFormData> onSubmit; //callback //consumer is liek a lmbda function , use it take a RingFormData and lunch onSubmit
	//create form 3elements
	

	public RingForm() {
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
			
		this.add(numberofnodes);
		this.add(stringtomap);
		this.add(sendbutton);	
		
	}
	
	public void setOnSubmit(Consumer<RingFormData> onSubmit) {
        this.onSubmit = onSubmit;
    }

	
	public void handlesubmit() {
		int nodes = (int)numberofnodes.getValue();
		String text = stringtomap.getText();
		
		RingFormData record = new RingFormData(nodes,text);
		
		onSubmit.accept(record); //called the consumer
		
		
	}
		
		
	}
	
	
	


