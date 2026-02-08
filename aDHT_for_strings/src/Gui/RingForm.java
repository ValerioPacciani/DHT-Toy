package Gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

public class RingForm extends JPanel{
		JSpinner nodenumber; //dichiaration of variables
		JTextField stringfield; //per l inserimento della stringa da mappare
		JButton accept;
		JLabel nodedesc;
		JLabel stringdesc;
		JPanel containerform;
		
		
		//JGridLayout positions;
		
	public  RingForm() {
		
		
		nodenumber = new JSpinner();
		nodenumber.setToolTipText("numero nodi");
		stringfield = new JTextField(20);
		accept = new JButton ();
		accept.
		nodedesc = new JLabel("quanti nodi deve avere la struttura dati?");
		stringdesc = new JLabel("inserire la stringa da mappare");
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5); // margini standard
		c.fill = GridBagConstraints.HORIZONTAL;

		// --- Riga 0: label nodi ---
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("quanti nodi deve avere la struttura dati?"), c);

		// spinner nodi
		c.gridx = 1;
		c.gridy = 0;
		add(nodenumber, c);

		// --- Riga 1: label stringa ---
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("inserire la stringa da mappare"), c);

		// campo stringa
		c.gridx = 1;
		c.gridy = 1;
		add(stringfield, c);

		// --- Riga 2: bottone ---
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1; 
		c.anchor = GridBagConstraints.CENTER;
		add(accept, c);
	   
	
		
		
		
		setVisible(true);
		
		
	}
	
	
	public void onSubmit(Runnable action) {
	        accept.addActionListener(e -> action.run());
	    }
	
}


