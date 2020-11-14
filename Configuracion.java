package ar.edu.unlu.juego.espionaje.vista.grafica;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.vista.grafica.config.Instrucciones;
import ar.edu.unlu.juego.espionaje.vista.grafica.config.Ranking;
import ar.edu.unlu.juego.espionaje.vista.grafica.config.RegistrarJugador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Configuracion extends JPanel {
	Instrucciones ventanaAyuda = new Instrucciones();
	JFrame RegistrarJugador;
	Ranking ventanaRanking = new Ranking();
	String nombre;
	TextField txtNombre;
	Controlador c;
	
	
	/**
	 * Create the panel.
	 */
	public Configuracion() {
		
		setBackground(Color.DARK_GRAY);
		InputStream is = Configuracion.class.getResourceAsStream("BebasNeue-Regular.ttf");
	    Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font sizedFont = font.deriveFont(20f);
		//Font sizedFont = fuente.configTamaño(20);
		setLayout(new MigLayout("", "[149px][][][][][][][][]", "[33px][33px][][33px][][][][][][][][][][][][][][33px][][][][][]"));
		
		JButton btnComenzarJuego = new JButton("   Comenzar Juego  ");
		btnComenzarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c.iniciarJuego();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnComenzarJuego.setFont(sizedFont);
		add(btnComenzarJuego, "cell 4 4");
		RegistrarJugador rJug = new RegistrarJugador();
		JButton btnRegistarJugador = new JButton("Registrar Jugador");
		btnRegistarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rJug.setVisible(true);
			}
			});
		btnRegistarJugador.setFont(sizedFont);
		add(btnRegistarJugador, "cell 4 7,alignx center,aligny center");
		
		JButton btnJugadores = new JButton("  Listar Jugadores ");
		btnJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnJugadores.setFont(sizedFont);
		add(btnJugadores, "cell 4 10,alignx center,aligny center");
		
		JButton btnRanking = new JButton(" Ranking de Espias ");
		btnRanking.setFont(sizedFont);
		add(btnRanking, "cell 4 13,alignx center,aligny center");
		
		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ventanaAyuda.setVisible(true);
			}
		});
		btnAyuda.setFont(sizedFont);
		add(btnAyuda, "cell 4 16,alignx center,aligny center");

	}

	
	public void setControlador(Controlador c) {
		this.c = c;
	}
	
}
