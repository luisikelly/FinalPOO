package ar.edu.unlu.juego.espionaje.vista.grafica;

import javax.swing.JPanel;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class PanelMostrar extends JPanel {

	/**
	 * Create the panel.
	 * @param controlador 
	 * @throws RemoteException 
	 */
	public PanelMostrar(Controlador controlador, String tipo) throws RemoteException {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		
		JLabel lblTipo = new JLabel("Tipo ");
		lblTipo.setBounds(114, 34, 46, 14);
		add(lblTipo);
		
		JLabel lblNombrejugador = new JLabel("nombreJugador");
		lblNombrejugador.setBounds(223, 34, 46, 14);
		add(lblNombrejugador);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 81, 390, 147);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblCartaRespuesta = new JLabel("CartaRespuesta");
		lblCartaRespuesta.setBounds(147, 60, 103, 14);
		if(tipo.equals("RESPUESTA")) {
			lblCartaRespuesta.setText(controlador.getRespuesta());
		} else {
			lblCartaRespuesta.setVisible(false);
		}
		panel.add(lblCartaRespuesta);
		
		JLabel lblCartaSospecha1 = new JLabel("CartaSospecha1");
		lblCartaSospecha1.setBounds(33, 60, 103, 14);
		if(tipo.equals("SOSPECHA")) {
			lblCartaSospecha1.setText(controlador.getSospecha()[0].getFigura());
		} else {
			lblCartaSospecha1.setVisible(false);
		}
		panel.add(lblCartaSospecha1);
		
		JLabel lblCartaSospecha2 = new JLabel("CartaSospecha2");
		lblCartaSospecha2.setBounds(260, 60, 103, 14);
		if(tipo.equals("SOSPECHA")) {
			lblCartaSospecha2.setText(controlador.getSospecha()[1].getFigura());
		} else {
			lblCartaSospecha2.setVisible(false);
		}
		panel.add(lblCartaSospecha2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnOk.setBounds(331, 266, 89, 23);
		add(btnOk);

	}
}
