package ar.edu.unlu.juego.espionaje.vista.grafica;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;
import javax.swing.SwingConstants;

public class PanelConfig extends JPanel {
	private JTextField textField;
	private Controlador controlador;
	private String s ="";
	protected JTextPane textJugadores;
	/**
	 * Create the panel.
	 * @param controlador 
	 */
	public PanelConfig(Controlador controlador) {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		this.controlador = controlador;
		
		InputStream is1 = PanelConfig.class.getResourceAsStream("Shakerato.otf");
		InputStream is2 = PanelConfig.class.getResourceAsStream("Shakerato-Bold.otf");
		InputStream is3 = PanelConfig.class.getResourceAsStream("STAY HOME.otf");
	    Font font1 = null;
	    Font font2 = null;
	    Font font3 = null;
		try {
			font1 = Font.createFont(Font.TRUETYPE_FONT, is1);
			font2 = Font.createFont(Font.TRUETYPE_FONT, is2);
			font3 = Font.createFont(Font.TRUETYPE_FONT, is3);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font sizedFont = font2.deriveFont(48f);
		Font sizedFont2 = font1.deriveFont(24f);
		
		JLabel lblSimbolo = 
				new JLabel("ESPIONAJE");
		lblSimbolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimbolo.setForeground(Color.WHITE);
		lblSimbolo.setFont(sizedFont);
		lblSimbolo.setBounds(87, 5, 269, 70);
		add(lblSimbolo);
		
		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setFont(sizedFont2);
		textField.setBackground(Color.GRAY);
		textField.setBounds(109, 86, 153, 30);
		add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(21, 81, 87, 41);
		lblNombre.setFont(sizedFont2);
		add(lblNombre);
		
		sizedFont2 = font1.deriveFont(16f);
		JButton btnIniciarJuego = new JButton("JUGAR");
		btnIniciarJuego.setFont(sizedFont2);
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.iniciarPartida();
				
				
				
			}
		});
		btnIniciarJuego.setBounds(287, 239, 142, 34);
		add(btnIniciarJuego);
		
		
		
		
		JButton btnReglasDelJuego = new JButton("AYUDA");
		btnReglasDelJuego.setFont(sizedFont2);
		btnReglasDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnReglasDelJuego.setBounds(287, 126, 142, 31);
		add(btnReglasDelJuego);
		
		
		JButton btnAgregarJugador = new JButton("REGISTRARSE");
		btnAgregarJugador.setFont(sizedFont2);
		btnAgregarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(!textField.getText().equals("")) {
				String nombre = textField.getText();
				System.out.println(textField.getText());
				controlador.agregarJugador(nombre);

				//}else {
				//	JOptionPane.showMessageDialog(null,"Debe ingresar el nombre del jugador");
				//}
			}
		});
		btnAgregarJugador.setBounds(287, 84, 142, 31);
		add(btnAgregarJugador);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

			
		});
		btnActualizar.setBounds(172, 144, 42, 23);
		add(btnActualizar);

		Font sizedFont3 = font1.deriveFont(14f);
		textJugadores = new JTextPane();
		textJugadores.setForeground(Color.WHITE);
		textJugadores.setBackground(Color.GRAY);
		textJugadores.setFont(sizedFont3);
		textJugadores.setEditable(false);
		textJugadores.setBounds(28, 178, 212, 95);
		add(textJugadores);
		
		sizedFont2 = font1.deriveFont(28f);
		
		JLabel lblJugadores = new JLabel("JUGADORES");
		lblJugadores.setForeground(Color.WHITE);
		lblJugadores.setFont(sizedFont2);
		lblJugadores.setBounds(28, 136, 131, 41);
		add(lblJugadores);



	}
	
	
}
