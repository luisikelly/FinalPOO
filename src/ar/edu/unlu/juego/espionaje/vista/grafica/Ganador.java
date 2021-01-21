package ar.edu.unlu.juego.espionaje.vista.grafica;

import javax.swing.JPanel;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.rmi.RemoteException;

public class Ganador extends JPanel {
	
	private Controlador controlador;

	/**
	 * Create the panel.
	 * @param controlador 
	 * @throws RemoteException 
	 */
	public Ganador(Controlador controlador) throws RemoteException {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		this.controlador = controlador;
		
		JLabel lblNewLabel = new JLabel("nombreJugador");
		lblNewLabel.setBounds(125, 27, 194, 31);
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Sugarpunch DEMO", Font.PLAIN, 28));
		lblNewLabel.setBackground(Color.ORANGE);
		add(lblNewLabel);
		
		JLabel lblGanLaPartida = new JLabel("Gan\u00F3 la partida");
		lblGanLaPartida.setBounds(138, 63, 168, 27);
		lblGanLaPartida.setForeground(Color.LIGHT_GRAY);
		lblGanLaPartida.setFont(new Font("Sugarpunch DEMO", Font.PLAIN, 25));
		add(lblGanLaPartida);
		
		JLabel lblInformacinSecreta = new JLabel("Informaci\u00F3n Secreta");
		lblInformacinSecreta.setBounds(125, 125, 195, 24);
		lblInformacinSecreta.setForeground(Color.WHITE);
		lblInformacinSecreta.setFont(new Font("Sugarpunch DEMO", Font.PLAIN, 22));
		add(lblInformacinSecreta);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(59, 154, 343, 115);
		panel.setLayout(null);
		add(panel);
		
		JLabel lblAgente = new JLabel("CartaAgente");
		lblAgente.setBounds(10, 44, 80, 14);
		lblAgente.setText(this.controlador.getInfoSecreta()[1].getFigura());
		panel.add(lblAgente);
		
		JLabel lblCiudad = new JLabel("CartaCiudad");
		lblCiudad.setText(this.controlador.getInfoSecreta()[0].getFigura());
		lblCiudad.setBounds(124, 44, 80, 14);
		
		panel.add(lblCiudad);
		
		JLabel lblDispositivo = new JLabel("CartaDispositivo");
		lblDispositivo.setBounds(239, 44, 80, 14);
		lblDispositivo.setText(this.controlador.getInfoSecreta()[2].getFigura());
		panel.add(lblDispositivo);

	}
}
