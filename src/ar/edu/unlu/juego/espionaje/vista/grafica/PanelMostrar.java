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
import javax.swing.SwingConstants;

public class PanelMostrar extends JPanel {

	/**
	 * Create the panel.
	 * @param controlador 
	 * @throws RemoteException 
	 */
	public PanelMostrar(Controlador controlador, String tipo) throws RemoteException {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		
		JLabel lblTipo = new JLabel("JUGADOR");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setForeground(Color.RED);
		lblTipo.setBounds(10, 113, 194, 36);
		add(lblTipo);
		
		JLabel lblNombrejugador = new JLabel("RESPONDE:");
		lblNombrejugador.setForeground(Color.WHITE);
		lblNombrejugador.setBounds(214, 113, 206, 36);
		add(lblNombrejugador);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnOk.setBounds(330, 241, 90, 36);
		add(btnOk);
		
		JLabel lblCarta = new JLabel("CARTA");
		lblCarta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarta.setForeground(Color.ORANGE);
		lblCarta.setBounds(121, 160, 206, 36);
		add(lblCarta);

	}
}
