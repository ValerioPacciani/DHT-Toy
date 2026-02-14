package Gui;
import java.awt.BorderLayout;

import javax.swing.*;

import adht.structures.Controller;
import adht.structures.Sender;
import adht.node.*;
public class Gui extends JFrame {
	
	private final Controller controller; // controller for taking the data and send it to the app
	private final RingCanvas ringcanvas; //this is the compontent we'll use for drawing;
	JPanel mainlayout; //graphic container
	public Gui(Controller controller) {
		
		this.controller = controller;
		this.ringcanvas = new RingCanvas();
		
		setTitle("DHT TOY HOMEPAGE");
		setSize(600,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		//-------Menu-------
		//creazione componenti
		JMenuBar menubar = new JMenuBar();
		JMenu menudati = new JMenu("struttura dati");
		JMenuItem anelloitem = new JMenuItem("Anello");
		JMenuItem hypercubeitem = new JMenuItem("Hypercubo");
		JPanel mainlayout = new JPanel(new BorderLayout());
		
		this.add(mainlayout); //add mainlayout to our page (is a cointer)
		//aggiunta componenti ai loro parents
		
		
		menudati.add(anelloitem);
		menudati.add(hypercubeitem);
		
		menubar.add(menudati);
		setJMenuBar(menubar); //attacca il menubar alla finestra !!!!!!!! la finestra è gia presente quindi questo equivale a this.setJMenuBar(menubar)
		mainlayout.add(ringcanvas,BorderLayout.CENTER);
		
		//gestione eventi 
		
		anelloitem.addActionListener(e -> {
			menudati.setText("Anello");
            System.out.println("Hai scelto: Anello");
            RingForm ringform = new RingForm();
            ringform.setOnSubmit(data -> {
                Node start = controller.HandleRingForm(data);
				ringcanvas.setNodes(Sender.packGraphics(start));
				ringcanvas.repaint();
                return start;
            });
            mainlayout.add(ringform,BorderLayout.NORTH); 
            // qui richiami il tuo codice per creare un anello
        });

        hypercubeitem.addActionListener(e -> {
        	menudati.setText("Hypercube");
            System.out.println("Hai scelto: Hypercube");
            // qui richiami il tuo codice per creare un hypercub
		
        });
		setVisible(true);
	
	
}
}

