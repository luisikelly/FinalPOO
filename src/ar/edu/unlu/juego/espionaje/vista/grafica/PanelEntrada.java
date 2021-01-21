package ar.edu.unlu.juego.espionaje.vista.grafica;

import javax.swing.JPanel;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.vista.grafica.componentes.CheckboxListCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.ScrollPane;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import  javax.swing.ListModel ;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class PanelEntrada extends JPanel implements ChangeListener{
	private Controlador controlador;
	private String tipoPantalla;
	private JScrollPane scrollPane;
	private int seleccionados = 0;
	private ArrayList<String> list = new ArrayList<String>();	
	
	/**
	 * Create the panel.
	 * @throws RemoteException 
	 */
	public PanelEntrada(String tipo, Controlador controlador) throws RemoteException {
		this.controlador = controlador;
		this.tipoPantalla = tipo;
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblEspionaje = new JLabel("Espionaje");
		lblEspionaje.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblEspionaje, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(88, 38, 46, 14);
		panel.add(lblTipo);
		
		JLabel lblNombrejugador = new JLabel("NombreJugador");
		lblNombrejugador.setBounds(250, 38, 46, 14);
		panel.add(lblNombrejugador);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 74, 329, 156);
		panel.add(scrollPane);
		
		JButton btnOk = 
				new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipoPantalla.equals("SOSPECHAR")) {
					if(seleccionados == 2) {
						controlador.recibirSospecha(list);
					}else {
						JOptionPane.showMessageDialog(null,"La sospecha contiene 2 elementos");
					}
				}
				if(tipoPantalla.equals("RESPONDER")) {
					if(seleccionados == 1) {
						controlador.setRespuesta(list.get(0));
					}else {
						JOptionPane.showMessageDialog(null,"La respuesta debe contener solo un elemento");
					}
				}
					
					
			}
		});
		btnOk.setBounds(332, 252, 89, 23);
		panel.add(btnOk);
		
		 
		
		if(tipoPantalla.equals("SOSPECHAR")) {
			for(int i = 0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1;i++) {
				this.addCheckBox(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
			}
		}
		if(tipoPantalla.equals("RESPONDER")) {
			for(int i = 0; i<= controlador.getSospecha().length-1 ;i++) {
				this.addCheckBox(controlador.getSospecha()[i].getFigura());
			}
		}

	}
	
	JCheckBox cb;
	
	private void addCheckBox(String cbName) {
	       cb = new JCheckBox(cbName);	       
	      cb.addChangeListener(this);
	       scrollPane.add(cb);
	       scrollPane.revalidate();
	}
	
	public void stateChanged(ChangeEvent e){
		if(cb.isSelected() && seleccionados < 2) {
			seleccionados ++;
			list.add(cb.getName());
		}
		if(!cb.isSelected()&& seleccionados > 0) {
			seleccionados --;
			int p = this.buscarSeleccionados(list, cb.getName());
			if(p != -1) {
				list.remove(p);
			}
			
		}
	}
	
	private int buscarSeleccionados(ArrayList<String> l, String s) {
		int pos= -1;
		for(int i=0;i<= l.size()-1;i++) {
			if(l.get(i).equals(s)) {pos= i;}
		}
		return pos;
	}
	
/*	
	ItemListener itemListener = new ItemListener () {
		public  void itemStateChanged (ItemEvent itemEvent) {
	        if(tipoPantalla.equals("SOSPECHAR")) {
	        	 if(itemEvent.SELECTED && seleccionados < 2) {seleccionados++}
		         if(itemEvent.DESELECTED && seleccionados > 0) {seleccionados--}
		         if(itemEvent.SELECTED && seleccionados > 2) {JOptionPane.showMessageDialog(null,"La sospecha contiene 2 elementos");}
	        }
	        if(tipoPantalla.equals("RESPONDER")) {
	        	if(itemEvent.SELECTED && seleccionados < 1) {seleccionados++}
	        	if(itemEvent.DESELECTED && seleccionados > 0) {seleccionados--}
		        if(itemEvent.SELECTED && seleccionados > 1) {JOptionPane.showMessageDialog(null,"La respuesta debe contener solo un elemento");}
	        }
	       }
	}
        
     
	
	
	*/
	
}
