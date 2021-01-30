package ar.edu.unlu.juego.espionaje.vista.grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class VistaGrafica implements Serializable,IVista{

	private Controlador controlador;
	
	//COMPONENTES 
	private JPanel contentPane;
	private JFrame frmEspionaje;
	
	private JPanel pantallaConfiguracion;
	private JPanel pantallaSospechar;
	private JPanel pantallaResponder;
	private JPanel pantallaArriesgar;
	private JPanel pantallaGanador;
	private JPanel pantallaTerminado;


	private JLabel lblNombrejugadorPSospecha;
	private JLabel lblNombrejugadorPRespuesta;
	private JLabel lblnro;
	private JLabel lblTURNOJugador;
	private JLabel lblTURNOText;
	private JLabel lblTURNOSospechado;
	private JLabel lblElemento1;
	private JLabel lblElemento2;
	private JLabel lblSeleccionTuRespuesta;
	private JLabel lblCarta;
	private JLabel lblNombrejugadorPResponder;
	
	private JTextArea textJugadores;
	private JTextArea textArea;
	
	private JComboBox s1;
	private JComboBox s2;
	JComboBox cbRespuesta;
	
	private PanelMostrar pantallaRespuesta;
	private CardLayout cardLayout;
	
	//FUENTES PARA LA VISTA
	private InputStream isNormal = VistaGrafica.class.getResourceAsStream("Shakerato.otf");
	private InputStream isBold = VistaGrafica.class.getResourceAsStream("Shakerato-Bold.otf");
	private InputStream isLight = VistaGrafica.class.getResourceAsStream("Shakerato-Light.otf");
	private	 Font normalFont = null;
	private  Font boldFont = null;
	private  Font lightFont = null;
			
		
	
	//CONSTANTES
	private final String CONFIG = "CONFIG";
	private final String SOSPECHAR = "SOSPECHAR";
	private final String RESPONDER = "RESPONDER";
	private final String RESPUESTA = "RESPUESTA";
	private final String ARRIESGAR = "ARRIESGAR";
	private final String TERMINO = "TERMINO";
	private final String GANADOR = "GANADOR";
	private final String TURNO = "TURNO";
	private final String ErrorCantidadMinimaJugadores = "MenosJugadores";
	private final String ErrorCantidadMaximaJugadores = "MasJugadores";
	
	//OTROS
	
	private ArrayList<String> lista = new ArrayList<String>();
	 private DefaultTableModel model;


	
	
	
	/**
	 * Create the frame.
	 * @param controlador2 
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public VistaGrafica() throws FontFormatException, IOException {		
		
		this.frmEspionaje = new JFrame();		
		frmEspionaje.setTitle("ESPIONAJE");
		this.frmEspionaje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmEspionaje.setBounds(100, 100, 481, 350);
		this.frmEspionaje.setLocationRelativeTo(null);
		cardLayout = new CardLayout();
		Container contentPane = this.frmEspionaje.getContentPane();
		contentPane.setLayout(cardLayout);
		
		//FUENTES
		normalFont = Font.createFont(Font.TRUETYPE_FONT, isNormal);
		boldFont = Font.createFont(Font.TRUETYPE_FONT, isBold);
		lightFont = Font.createFont(Font.TRUETYPE_FONT, isLight);
		
		// PANTALLAS
		JPanel pantallaSospechar = this.crearPantallaEntrada();
		contentPane.add(this.crearPantallaConfig(),CONFIG);	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 201, 2, 2);
		pantallaConfiguracion.add(scrollPane);
		contentPane.add(pantallaSospechar, SOSPECHAR);
		contentPane.add(this.crearPantallaResponder(), RESPONDER);
		contentPane.add(this.crearPantallaArriesgar(), ARRIESGAR);
		contentPane.add(this.crearPantallaGanador(), GANADOR);
		contentPane.add(this.crearPantallaTerminado(), TERMINO);
		contentPane.add(this.crearPantallaMostrar(), RESPUESTA);
		contentPane.add(this.crearPantallaTurno(), TURNO);

		this.frmEspionaje.setVisible(true);	
	}


	@Override
	public void setControlador(Controlador c) {
		this.controlador = c;
	
	}
	
	@Override
	public void iniciarJuego() {
		this.mostrarConfiguracion();
		
	}
	
	// ----- MÉTODOS MOSTRAR -----
	
	@Override
	public void mostrarArriesgar() {
		cardLayout.show(contentPane, SOSPECHAR);
	}
	
	@Override
	public void mostrarSospechar() {
		JCheckBox cbx;
		String nro = controlador.getNroJugador() + "";
		lblnro.setText(nro);	
		System.out.println(controlador.getSospechado());
		this.lblNombrejugadorPSospecha.setText(controlador.listaJugadores().get(controlador.getSospechado()).getNombre().toUpperCase());
		String s = "";
		for(int i=0;i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
			if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida() == true) {
				s = s + controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura() + "\n";
				textArea.setText(s);
				if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida()) {
					s1.addItem(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
					s2.addItem(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				}
			}
			
		}
		
		cardLayout.show(this.frmEspionaje.getContentPane(), SOSPECHAR);
	}

	
	@Override
	public void mostraJugadores() {
		String s = "";
		for(int i=0;i<= controlador.listaJugadores().size()-1;i++) {
			s= s + controlador.listaJugadores().get(i).getNombre() + "\n";
		}
		textJugadores.setText(s);
	}


	@Override
	public void mostrarConfiguracion() {
		cardLayout.show(this.frmEspionaje.getContentPane(), CONFIG);
	}

	@Override
	public void mostrarRespuesta(String r) {
		lblNombrejugadorPRespuesta.setText(controlador.listaJugadores().get(controlador.getSospechado()).getNombre());
		this.lblCarta.setText(r);
		cardLayout.show(this.frmEspionaje.getContentPane(), RESPUESTA);
		
	}

	@Override
	public void mostrarResponder() {
		try {
			lblElemento1.setText(controlador.getSospecha().get(0));
			lblElemento2.setText(controlador.getSospecha().get(1));
			lblNombrejugadorPResponder.setText(controlador.getJugadorEnTurno().getNombre());
			if(!controlador.verificarRespuesta().isEmpty()) {
				if(controlador.verificarRespuesta().size() == 1) {
					cbRespuesta.addItem(controlador.verificarRespuesta().get(0));

				}else {
					cbRespuesta.addItem(controlador.verificarRespuesta().get(0));
					cbRespuesta.addItem(controlador.verificarRespuesta().get(1));
				}
				
			} else {
				cbRespuesta.setEnabled(false);;
				cbRespuesta.setVisible(false);
				String sospechado = controlador.listaJugadores().get(controlador.getSospechado()).getNombre();
				lblSeleccionTuRespuesta.setText("NO TENES NINGUNA DE LAS CARTAS DE LA SOSPECHA");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cardLayout.show(this.frmEspionaje.getContentPane(), RESPONDER);
		
	}

	@Override
	public void mostrarError(String tError) {
		if(tError.equals(ErrorCantidadMinimaJugadores)) {
			JOptionPane.showMessageDialog(null,"¡Deben registrarse por lo menos 2 jugadores para comenzar!");
		}
		if(tError.equals(ErrorCantidadMaximaJugadores)) {
			JOptionPane.showMessageDialog(null,"¡Superó la cantidad máxima de jugadores!");
		}
	}
	
	@Override
	public void mostrarTurno(String string) {
		if(string.equals(SOSPECHAR)) {
			this.lblTURNOSospechado.setVisible(false);
			this.lblTURNOJugador.setText(controlador.getJugadorEnTurno().getNombre());
			this.lblTURNOText.setText("ESTÁ REALIZANDO SU SOSPECHA");
		}
		if(string.equals(RESPONDER)) {	
			this.lblTURNOJugador.setText(controlador.getJugadorEnTurno().getNombre());
			this.lblTURNOText.setText("ENVIÓ SU SOSPECHA A ");
			this.lblTURNOSospechado.setText(controlador.listaJugadores().get(controlador.getSospechado()).getNombre());
		}
		if(string.equals(ARRIESGAR)) {
			this.lblTURNOSospechado.setVisible(false);
			this.lblTURNOJugador.setText(controlador.getJugadorEnTurno().getNombre());
			this.lblTURNOText.setText("ESTÁ REALIZANDO SU ACUSACIÓN");
		}			
		cardLayout.show(this.frmEspionaje.getContentPane(), TURNO);	
	}

	
	//MOSTRAR PENDIENTES
	@Override
	public void avisoGanador() {
		
	}
	@Override
	public void mostrarTerminado() {

		
	}

	@Override
	public void avisoPerdio() {
	
		
	}


	
	
	
// ----- MÉTODOS PARA CREAR PANTALLAS -----
	
	
	public JPanel crearPantallaConfig() {
		pantallaConfiguracion = new JPanel();
		pantallaConfiguracion.setBackground(Color.DARK_GRAY);
		pantallaConfiguracion.setLayout(null);

		Font sizedFont = boldFont.deriveFont(48f);
		Font sizedFont2 = normalFont.deriveFont(24f);
		
		JLabel lblSimbolo = 
				new JLabel("ESPIONAJE");
		lblSimbolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimbolo.setForeground(Color.WHITE);
		lblSimbolo.setFont(sizedFont);
		lblSimbolo.setBounds(87, 5, 269, 70);
		pantallaConfiguracion.add(lblSimbolo);
		
		JTextField textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setFont(sizedFont2);
		textField.setBackground(Color.GRAY);
		textField.setBounds(109, 86, 153, 30);
		pantallaConfiguracion.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(21, 81, 87, 41);
		lblNombre.setFont(sizedFont2);
		pantallaConfiguracion.add(lblNombre);
		
		sizedFont2 = normalFont.deriveFont(16f);
		JButton btnIniciarJuego = new JButton("JUGAR");
		btnIniciarJuego.setFont(sizedFont2);
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.iniciarPartida();
			}
		});
		btnIniciarJuego.setBounds(287, 239, 142, 34);
		pantallaConfiguracion.add(btnIniciarJuego);
		
		JButton btnReglasDelJuego = new JButton("AYUDA");
		btnReglasDelJuego.setFont(sizedFont2);
		btnReglasDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnReglasDelJuego.setBounds(287, 126, 142, 31);
		pantallaConfiguracion.add(btnReglasDelJuego);
			
		JButton btnAgregarJugador = new JButton("REGISTRARSE");
		btnAgregarJugador.setFont(sizedFont2);
		btnAgregarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!textField.getText().equals("")) {
				String nombre = textField.getText().toUpperCase();
				controlador.agregarJugador(nombre);
				textField.setEnabled(false);
				btnAgregarJugador.setEnabled(false);
				} else {
			    	JOptionPane.showMessageDialog(null,"Debe ingresar el nombre del jugador");
				}
			}
		});
		btnAgregarJugador.setBounds(287, 84, 142, 31);
		pantallaConfiguracion.add(btnAgregarJugador);
		

		
		Font sizedFont3 = normalFont.deriveFont(18f);
		textJugadores = new JTextArea();
		textJugadores.setForeground(Color.WHITE);
		textJugadores.setBackground(Color.GRAY);
		textJugadores.setFont(sizedFont3);
		textJugadores.setEditable(false);
		textJugadores.setBounds(21, 188, 212, 95);
		pantallaConfiguracion.add(textJugadores);
		
		sizedFont2 = normalFont.deriveFont(28f);
		
		JLabel lblJugadores = new JLabel("JUGADORES");
		lblJugadores.setForeground(Color.WHITE);
		lblJugadores.setFont(sizedFont2);
		lblJugadores.setBounds(28, 136, 131, 41);
		pantallaConfiguracion.add(lblJugadores);

		return pantallaConfiguracion;
	}
	
	
	private JPanel crearPantallaEntrada() {
		JPanel pantallaEntrada = new JPanel();
		pantallaEntrada.setBackground(Color.DARK_GRAY);
		pantallaEntrada.setLayout(null);
		
		Font sizedFont = boldFont.deriveFont(40f);
		
		JLabel lblEspionaje = new JLabel("ESPIONAJE");
		lblEspionaje.setBounds(0, 0, 465, 55);
		lblEspionaje.setForeground(Color.WHITE);
		lblEspionaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspionaje.setFont(sizedFont);
		pantallaEntrada.add(lblEspionaje);
		
		sizedFont = normalFont.deriveFont(18f);
		
		lblNombrejugadorPSospecha = new JLabel("NombreJugador");
		lblNombrejugadorPSospecha.setBounds(256, 56, 179, 34);
		lblNombrejugadorPSospecha.setForeground(Color.WHITE);
		lblNombrejugadorPSospecha.setFont(sizedFont);
		pantallaEntrada.add(lblNombrejugadorPSospecha);
		
		JLabel lblTipo = new JLabel("SOPECHA A");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(20, 56, 210, 34);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(sizedFont);
		
		pantallaEntrada.add(lblTipo);
		
		sizedFont = normalFont.deriveFont(15f);
		
		JButton btnArriesgar = new JButton("ARRIESGAR");
		btnArriesgar.setBounds(20, 260, 115, 23);
		pantallaEntrada.add(btnArriesgar);
		btnArriesgar.setFont(sizedFont);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!s1.getSelectedItem().equals(s2.getSelectedItem())) {
					lista.add((String) s1.getSelectedItem());
					lista.add((String) s2.getSelectedItem());
					controlador.setSospecha(lista);
				}else {
				 	JOptionPane.showMessageDialog(null,"Deben ser dos cartas distintas");
				}
			}
		});
		btnEnviar.setForeground(Color.RED);
		btnEnviar.setBounds(320, 260, 115, 23);
		btnEnviar.setFont(sizedFont);
		pantallaEntrada.add(btnEnviar);
		
		lblnro = new JLabel("-");
		lblnro.setForeground(Color.LIGHT_GRAY);
		lblnro.setBounds(409, 11, 46, 14);
		lblnro.setFont(sizedFont);
		pantallaEntrada.add(lblnro);
		
		s1 = new JComboBox();
		s1.setFont(sizedFont);
		s1.setBounds(293, 169, 144, 23);
		pantallaEntrada.add(s1);
		
		s2 = new JComboBox();
		s2.setFont(sizedFont);
		s2.setBounds(293, 203, 144, 23);
		pantallaEntrada.add(s2);
		
		JLabel lblNewLabel = new JLabel("SOSPECHO QUE ES... ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBounds(293, 116, 144, 42);
		lblNewLabel.setFont(sizedFont);
		pantallaEntrada.add(lblNewLabel);
		
		JLabel lblAgendaPersonal = new JLabel("AGENDA PERSONAL");
		lblAgendaPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgendaPersonal.setForeground(Color.RED);
		lblAgendaPersonal.setFont(sizedFont);
		lblAgendaPersonal.setBounds(51, 89, 179, 34);
		pantallaEntrada.add(lblAgendaPersonal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 116, 205, 119);
		pantallaEntrada.add(scrollPane);
				
						textArea = new JTextArea();
						textArea.setForeground(Color.WHITE);
						scrollPane.setViewportView(textArea);
						textArea.setBackground(Color.GRAY);
						textArea.setFont(sizedFont);
		
		return pantallaEntrada;
	}

	private JPanel crearPantallaResponder() {
		
		Font sizedFont = boldFont.deriveFont(40f);
		
		this.pantallaResponder = new JPanel();
		pantallaResponder.setBackground(Color.DARK_GRAY);
		JLabel label = new JLabel("ESPIONAJE");
		label.setFont(sizedFont);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setBounds(0, 11, 465, 55);
		pantallaResponder.setLayout(null);
		pantallaResponder.add(label);
		
		sizedFont = normalFont.deriveFont(18f);
		
		lblNombrejugadorPResponder = new JLabel("NombreJugador");
		lblNombrejugadorPResponder.setForeground(Color.RED);
		lblNombrejugadorPResponder.setFont(sizedFont);
		lblNombrejugadorPResponder.setBackground(Color.DARK_GRAY);
		lblNombrejugadorPResponder.setBounds(38, 89, 139, 25);
		pantallaResponder.add(lblNombrejugadorPResponder);
		
		JLabel lblTexto = new JLabel("SOSPECHA:");
		lblTexto.setFont(sizedFont);
		lblTexto.setForeground(Color.WHITE);
		lblTexto.setBackground(Color.DARK_GRAY);
		lblTexto.setBounds(187, 89, 215, 25);
		pantallaResponder.add(lblTexto);
		
		lblElemento1 = new JLabel("E1");
		this.lblElemento1.setFont(sizedFont);
		lblElemento1.setForeground(Color.ORANGE);
		lblElemento1.setHorizontalAlignment(SwingConstants.CENTER);
		lblElemento1.setBounds(24, 144, 173, 33);
		pantallaResponder.add(lblElemento1);
		
		lblElemento2 = new JLabel("Elemento2");
		lblElemento2.setForeground(Color.ORANGE);
		this.lblElemento2.setFont(sizedFont);
		lblElemento2.setHorizontalAlignment(SwingConstants.CENTER);
		lblElemento2.setBounds(249, 144, 173, 33);
		pantallaResponder.add(lblElemento2);
		
		cbRespuesta = new JComboBox();
		cbRespuesta.setBounds(249, 211, 156, 20);
		pantallaResponder.add(cbRespuesta);
		
		lblSeleccionTuRespuesta = new JLabel("SELECCION\u00C1 TU RESPUESTA");
		lblSeleccionTuRespuesta.setForeground(Color.WHITE);
		this.lblSeleccionTuRespuesta.setFont(sizedFont);
		lblSeleccionTuRespuesta.setBounds(10, 202, 412, 33);
		pantallaResponder.add(lblSeleccionTuRespuesta);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(sizedFont);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			if(cbRespuesta.isEnabled()) {
				controlador.setRespuesta((String) cbRespuesta.getSelectedItem());
			} else {
				controlador.setRespuesta("");
			}
			cbRespuesta.removeAllItems();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(354, 267, 89, 33);
		pantallaResponder.add(btnOk);

		
		return pantallaResponder;
	}
	
	private JPanel crearPantallaArriesgar() {
		this.pantallaArriesgar = new JPanel();
		pantallaArriesgar.setBackground(Color.DARK_GRAY);
		pantallaArriesgar.setLayout(null);
		
		JLabel label_1 = new JLabel("ESPIONAJE");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 40));
		label_1.setBounds(0, 11, 465, 55);
		pantallaArriesgar.add(label_1);


		return pantallaArriesgar;

	}
	
	private JPanel crearPantallaGanador() {
		this.pantallaGanador = new JPanel();
		pantallaGanador.setBackground(Color.DARK_GRAY);
		pantallaGanador.setLayout(null);
		
		JLabel label_2 = new JLabel("ESPIONAJE");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 40));
		label_2.setBounds(0, 11, 465, 55);
		pantallaGanador.add(label_2);

		return pantallaGanador;
	}
	
	private JPanel crearPantallaTerminado() {
		this.pantallaTerminado = new JPanel();
		pantallaTerminado.setLayout(null);

		pantallaTerminado.setBackground(Color.DARK_GRAY);
		JLabel lblTitulo = new JLabel("ESPIONAJE");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.PLAIN, 40));
		lblTitulo.setBounds(0, 11, 465, 55);
		pantallaTerminado.add(lblTitulo);
		return pantallaTerminado;
	}
	
	private JPanel crearPantallaTurno() {
		JPanel pantallaTurno = new JPanel();
		pantallaTurno.setBackground(Color.DARK_GRAY);
		pantallaTurno.setLayout(null);
		
		Font sizedFontTitulo = boldFont.deriveFont(48f);
		Font sizedFont= normalFont.deriveFont(24f);
		
		JLabel label = new JLabel("ESPIONAJE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(sizedFontTitulo);
		label.setBounds(0, 11, 465, 55);
		pantallaTurno.add(label);
		
		 lblTURNOText = new JLabel("TEXT");
		 lblTURNOText.setHorizontalAlignment(SwingConstants.CENTER);
		lblTURNOText.setForeground(Color.WHITE);
		lblTURNOText.setFont(sizedFont);
		lblTURNOText.setBounds(10, 149, 445, 41);
		pantallaTurno.add(lblTURNOText);
		
		lblTURNOJugador = new JLabel("Jugador");
		lblTURNOJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblTURNOJugador.setFont(sizedFont);
		lblTURNOJugador.setForeground(Color.ORANGE);
		lblTURNOJugador.setBounds(81, 89, 273, 35);
		pantallaTurno.add(lblTURNOJugador);
		
		lblTURNOSospechado = new JLabel("Jugador");
		lblTURNOSospechado.setHorizontalAlignment(SwingConstants.CENTER);
		lblTURNOSospechado.setFont(sizedFont);
		lblTURNOSospechado.setForeground(Color.RED);
		lblTURNOSospechado.setBounds(81, 204, 260, 41);
		pantallaTurno.add(lblTURNOSospechado);
		
		JLabel lblnro = new JLabel("New label");
		lblnro.setForeground(Color.LIGHT_GRAY);
		lblnro.setBounds(10, 286, 46, 14);
		lblnro.setFont(sizedFont);
	
		pantallaTurno.add(lblnro);
		return pantallaTurno;
	}

	private JPanel crearPantallaMostrar() {
		JPanel pantallaMostrar = new JPanel();
		pantallaMostrar.setBackground(Color.DARK_GRAY);
		pantallaMostrar.setLayout(null);
		
		JLabel label = new JLabel("ESPIONAJE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(normalFont.deriveFont(40f));
		label.setBounds(0, 11, 465, 55);
		pantallaMostrar.add(label);
		
		lblNombrejugadorPRespuesta = new JLabel("JUGADOR");
		lblNombrejugadorPRespuesta.setFont(normalFont.deriveFont(24f));
		lblNombrejugadorPRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrejugadorPRespuesta.setForeground(Color.RED);
		lblNombrejugadorPRespuesta.setBounds(10, 113, 194, 36);
		pantallaMostrar.add(lblNombrejugadorPRespuesta);
		
		JLabel lblResponde = new JLabel("RESPONDE:");
		lblResponde.setFont(normalFont.deriveFont(24f));
		lblResponde.setForeground(Color.WHITE);
		lblResponde.setBounds(214, 113, 206, 36);
		pantallaMostrar.add(lblResponde);
		
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(normalFont.deriveFont(18f));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					controlador.paso();
				
			}
		});
		btnOk.setBounds(330, 241, 90, 36);
		pantallaMostrar.add(btnOk);
		
		lblCarta = new JLabel("CARTA");
		lblCarta.setFont(normalFont.deriveFont(24f));
		lblCarta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarta.setForeground(Color.ORANGE);
		lblCarta.setBounds(121, 160, 206, 36);
		pantallaMostrar.add(lblCarta);

		return pantallaMostrar;
	}
}
