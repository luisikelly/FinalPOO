package ar.edu.unlu.juego.espionaje.vista.grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class VistaGrafica implements Serializable,IVista{

	private Controlador controlador;
	
	//COMPONENTES 
	private JPanel contentPane;
	private JFrame frmEspionaje;
	private Ganador pantallaGanador;
	private JPanel pantallaConfiguracion;
	private JPanel pantallaSospechar;
	private JPanel pantallaResponder;
	private JTextPane textJugadores;
	private JLabel lblNombrejugador;
	private JLabel lblTipo;
	
//	private PanelEntrada pantallaSospechar;
//	private PanelEntrada pantallaResponder;
	private PanelMostrar pantallaSospecha;
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
	private final String ARRIESGAR = "ARRIESGAR";
	private final String SOSPECHA = "SOSPECHA";
	private final String RESPUESTA = "RESPUESTA";
	private final String GANADOR = "GANADOR";
	private final String TURNO = "TURNO";
	private final String ErrorCantidadMinimaJugadores = "MenosJugadores";
	private final String ErrorCantidadMaximaJugadores = "MasJugadores";
	
	//OTROS
	
	private JLabel lblnro;
	
	private JLabel lblTURNOJugador;
	private JLabel lblTURNOText;
	private JLabel lblTURNOSospechado;
	private int seleccionados = 0; 
	private ArrayList<String> lista = new ArrayList<String>();
	private JTable table;
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
		contentPane.add(pantallaSospechar, SOSPECHAR);
		contentPane.add(this.crearPantallaTurno(), TURNO);
		
		this.frmEspionaje.setVisible(true);
	
		
	}


	@Override
	public void mostrarArriesgar() {
		cardLayout.show(contentPane, SOSPECHAR);
	}


	@Override
	public void mostrarJugando() {
		
		
	}


	@Override
	public void mostrarSospecha(int jugador) {
	//		contentPane.add(pantallaSospecha = new PanelMostrar(this.controlador, SOSPECHA), SOSPECHA);	
		cardLayout.show(this.frmEspionaje.getContentPane(), SOSPECHA);
	}
	
	@Override
	public void mostrarSospechar() {
		JCheckBox cbx;
		String nro = controlador.getNroJugador() + "";
		lblnro.setText(nro);
		this.lblTipo.setText("SOSPECHA A: ");
		this.lblNombrejugador.setText(controlador.listaJugadores().get(controlador.getSospechado()).getNombre().toUpperCase());
		model = new DefaultTableModel(new Object[]{"Cartas","Seleccionar"}, 0);
		
		for(int i=0;i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
			
			model.addRow(new Object[]{controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura(),false});
		}
		cardLayout.show(this.frmEspionaje.getContentPane(), SOSPECHAR);
	}

	@Override
	public void avisoGanador() {
		try {
			contentPane.add(pantallaGanador = new Ganador(this.controlador), GANADOR);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		cardLayout.show(this.frmEspionaje.getContentPane(), GANADOR);
	
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
	public void setControlador(Controlador c) {
		this.controlador = c;
	
	}


	@Override
	public void mostrarConfiguracion() {
		cardLayout.show(this.frmEspionaje.getContentPane(), CONFIG);
	}


	@Override
	public void mostrarTerminado() {

		
	}


	@Override
	public void mostrarElegirRespuesta() {
		
	}


	@Override
	public void mostrarResponder() {

		try {
			contentPane.add(pantallaResponder = new PanelEntrada(RESPONDER,this.controlador), RESPONDER);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		cardLayout.show(this.frmEspionaje.getContentPane(), RESPONDER);
		
	}


	@Override
	public void mostrarRespuesta() {
	try {
		contentPane.add(pantallaRespuesta = new PanelMostrar(this.controlador, RESPUESTA), RESPUESTA);
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	cardLayout.show(this.frmEspionaje.getContentPane(), RESPUESTA);
		
	}


	@Override
	public void iniciarJuego() {
		this.mostrarConfiguracion();
		
	}


	@Override
	public void avisoPerdio() {
	
		
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
	
		
		
		if(string.equals(SOSPECHA)) {
			this.lblTURNOSospechado.setVisible(false);
			this.lblTURNOJugador.setText(controlador.getJugadorEnTurno().getNombre());
			this.lblTURNOText.setText("ESTÁ REALIZANDO SU SOSPECHA");
		}
		if(string.equals(RESPUESTA)) {
			
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


// --- CREAR PANTALLAS ---
	
	
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
				} else {
			    	JOptionPane.showMessageDialog(null,"Debe ingresar el nombre del jugador");
				}
			}
		});
		btnAgregarJugador.setBounds(287, 84, 142, 31);
		pantallaConfiguracion.add(btnAgregarJugador);
		

		
		Font sizedFont3 = normalFont.deriveFont(18f);
		textJugadores = new JTextPane();
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
		
		Font sizedFont = boldFont.deriveFont(48f);
		
		JLabel lblEspionaje = new JLabel("ESPIONAJE");
		lblEspionaje.setBounds(10, 11, 465, 55);
		lblEspionaje.setForeground(Color.WHITE);
		lblEspionaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspionaje.setFont(sizedFont);
		pantallaEntrada.add(lblEspionaje);
		
		sizedFont = normalFont.deriveFont(18f);
		
		lblNombrejugador = new JLabel("NombreJugador");
		lblNombrejugador.setBounds(258, 71, 179, 34);
		pantallaEntrada.add(lblNombrejugador);
		lblNombrejugador.setForeground(Color.WHITE);
		lblNombrejugador.setFont(sizedFont);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(20, 71, 210, 34);
		pantallaEntrada.add(lblTipo);
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(sizedFont);
		
		sizedFont = normalFont.deriveFont(15f);
		
		JButton btnArriesgar = new JButton("ARRIESGAR");
		btnArriesgar.setBounds(340, 220, 115, 23);
		pantallaEntrada.add(btnArriesgar);
		btnArriesgar.setFont(sizedFont);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.setForeground(Color.RED);
		btnEnviar.setBounds(340, 260, 115, 23);
		btnEnviar.setFont(sizedFont);
		pantallaEntrada.add(btnEnviar);
		
		
		table = new JTable();
		table.setBounds(30, 114, 283, 169);
		pantallaEntrada.add(table);
		
		lblnro = new JLabel("-");
		lblnro.setForeground(Color.LIGHT_GRAY);
		lblnro.setBounds(409, 11, 46, 14);
		lblnro.setFont(sizedFont);
		pantallaEntrada.add(lblnro);
		
		return pantallaEntrada;
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
}
