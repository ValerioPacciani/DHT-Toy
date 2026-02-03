package Gui;
import javax.swing.*;

public class Gui extends JFrame {
	public Gui() {
		setTitle("DHT TOY HOMEPAGE");
		setSize(600,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//-------Menu-------
		//creazione componenti
		JMenuBar menubar = new JMenuBar();
		JMenu menudati = new JMenu("struttura dati");
		JMenuItem anelloitem = new JMenuItem("Anello");
		JMenuItem hypercubeitem = new JMenuItem("Hypercubo");
		
		//aggiunta componenti ai loro parents
		
		
		menudati.add(anelloitem);
		menudati.add(hypercubeitem);
		
		menubar.add(menudati);
		setJMenuBar(menubar); //attacca il menubar alla finestra !!!!!!!! la finestra è gia presente quindi questo equivale a this.setJMenuBar(menubar)
		
		//gestione eventi 
		
		anelloitem.addActionListener(e -> {
			menudati.setText("Anello");
            System.out.println("Hai scelto: Anello");
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

