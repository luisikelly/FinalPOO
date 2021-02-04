package ar.edu.unlu.juego.espionaje.vista.grafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import java.net.MalformedURLException;
import java.net.URL;
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
	
	private JButton btnEnviar;
	private JButton btnArriesgar;


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
	private JLabel lblMensaje;
	private JLabel lblIcono;
	private JLabel lblCartaciudad;
	private JLabel lblCartadisp;
	private JLabel lblCartaagente;
	
	private JTextArea textJugadores;
	private JTextArea textArea;
	
	private JComboBox s1;
	private JComboBox s2;
	private JComboBox cbRespuesta;
	private JComboBox cbCiudad;
	private JComboBox cbAgente;
	private JComboBox cbDispositivo;
	
	private PanelMostrar pantallaRespuesta;
	private CardLayout cardLayout;
	
	//FUENTES PARA LA VISTA
	private InputStream isNormal = VistaGrafica.class.getResourceAsStream("Shakerato.otf");
	private InputStream isBold = VistaGrafica.class.getResourceAsStream("Shakerato-Bold.otf");
	private InputStream isLight = VistaGrafica.class.getResourceAsStream("Shakerato-Light.otf");
	private	 Font normalFont = null;
	private  Font boldFont = null;
	private  Font lightFont = null;
			
	//ICONOS
	
	private Icon iconParis = new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconParis.png"));
	private Icon iconPanama= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconPanama.png"));
	private Icon iconLondres= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconLondres.png"));
	private Icon iconAtenas = new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconAtenas.png"));
	private Icon iconTokio= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconTokio.png"));
	private Icon iconAutopropulsor;//= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconAutopropulsor.png"));
	private Icon iconHelicoptero= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconHelicoptero.png"));
	private Icon iconGasLetal= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconGasLetal.png"));
	private Icon iconSatelite= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconSatelite.png"));
	private Icon iconAvion= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconAvion.png"));
	private Icon iconARojo= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconARojo.png"));
	private Icon iconAVerde= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconAVerde.png"));
	private Icon iconAAzul= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconAAzul.png"));
	private Icon iconABlanco= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconABlanco.png"));
	private Icon iconAAmarillo= new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconAAmarillo.png"));
	private Icon iconGanador = new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/trofeo.png"));
	private Icon iconEspia = new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconEspia.png"));
	private Icon iconGO = new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/go.png"));
	private Icon iconTitulo = new ImageIcon(VistaGrafica.class.getResource("/ar/edu/unlu/juego/espionaje/vista/grafica/componentes/iconTitulo.png"));

	
	
	
	
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
		contentPane.add(this.crearPantallaResponder(), RESPONDER);
		contentPane.add(this.crearPantallaArriesgar(), ARRIESGAR);
		contentPane.add(this.crearPantallaMostrar(), RESPUESTA);
		contentPane.add(this.crearPantallaTurno(), TURNO);
		contentPane.add(this.crearPantallaGanador(), GANADOR);
		
		

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

	
	
	private void setIcon(String s, JLabel lbl) {
		switch (s) {
		case "PARIS": lbl.setIcon(iconParis);break;
		case "LONDRES": lbl.setIcon(iconLondres);break;
		case "PANAMA": lbl.setIcon(iconPanama);break;
		case "ATENAS": lbl.setIcon(iconAtenas);break;
		case "TOKIO": lbl.setIcon(iconTokio);break;
		case "AGENTE_BLANCO": lbl.setIcon(iconABlanco);break;
		case "AGENTE_AZUL": lbl.setIcon(iconAAzul); break;
		case "AGENTE_VERDE": lbl.setIcon(iconAVerde); break;
		case "AGENTE_ROJO": lbl.setIcon(iconARojo); break;
		case "AGENTE_AMARILLO": lbl.setIcon(iconAAmarillo); break;
		case "GAS_LETAL": lbl.setIcon(iconGasLetal); break;
		case "SATELITE": lbl.setIcon(iconSatelite);break;
		case "AVION": lbl.setIcon(iconAvion); break;
		case "HELICOPTERO": lbl.setIcon(iconHelicoptero); break;
		case "AUTOPROPULSOR": lbl.setIcon(iconAutopropulsor); break;
		case "GANADOR": lbl.setIcon(iconGanador); break;
		case "PERDIO": lbl.setIcon(iconGO); break;
		case "ESPIA": lbl.setIcon(iconEspia); break;
		case "TITULO":lbl.setIcon(iconTitulo); break;

		}
	}
	
	//TODO ----- MÉTODOS MOSTRAR -----
	
	//TODO MOSTRAR ARRIESGAR
	@Override
	public void mostrarArriesgar() {
		for(int i=0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1;i++) {
				if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("CIUDAD")  ) {
					cbCiudad.addItem(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				}
				if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("AGENTE")  ) {
					cbAgente.addItem(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				}
				if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("DISPOSITIVO")  ) {
					cbDispositivo.addItem(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				}
		}
		cardLayout.show(this.frmEspionaje.getContentPane(), ARRIESGAR);
	}
	
	//TODO MOSTRAR SOSPECHAR
	@Override
	public void mostrarSospechar() {
		if(!btnEnviar.isEnabled()) {btnEnviar.setEnabled(true);}
		if(!btnArriesgar.isEnabled()) {btnArriesgar.setEnabled(true);}
		String nro = controlador.getNroJugador() + "";
		lblnro.setText(nro);	
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

//TODO AVISO QUIEN GANO
	@Override
	public void quienGano() {
		JOptionPane.showMessageDialog(null, controlador.getGanador().getNombre()+"GANÓ LA PARTIDA");
	}

	//TODO MOSTRAR JUGADORES
	@Override
	public void mostraJugadores() {
		String s = "";
		for(int i=0;i<= controlador.listaJugadores().size()-1;i++) {
			s= s + controlador.listaJugadores().get(i).getNombre() + "\n";
		}
		textJugadores.setText(s);
	}

	//TODO MOSTRAR CONFIGURACION
	@Override
	public void mostrarConfiguracion() {
		cardLayout.show(this.frmEspionaje.getContentPane(), CONFIG);
	}

	//TODO MOSTRAR RESPUESTA
	@Override
	public void mostrarRespuesta(String r) {
		lblNombrejugadorPRespuesta.setText(controlador.listaJugadores().get(controlador.getSospechado()).getNombre());
		this.lblCarta.setText(r);
		this.setIcon(r, lblCarta);
		cardLayout.show(this.frmEspionaje.getContentPane(), RESPUESTA);
		
	}

	//TODO MOSTRAR RESPONDER
	@Override
	public void mostrarResponder() {
		
		try {
			lblElemento1.setText(controlador.getSospecha().get(0));
			this.setIcon(controlador.getSospecha().get(0), this.lblElemento1);
			lblElemento2.setText(controlador.getSospecha().get(1));
			this.setIcon(controlador.getSospecha().get(1), this.lblElemento2);
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
			e.printStackTrace();
		}
		
		cardLayout.show(this.frmEspionaje.getContentPane(), RESPONDER);
		
	}

	//TODO MOSTRAR ERROR
	@Override
	public void mostrarError(String tError) {
		if(tError.equals(ErrorCantidadMinimaJugadores)) {
			JOptionPane.showMessageDialog(null,"¡Deben registrarse por lo menos 2 jugadores para comenzar!");
		}
		if(tError.equals(ErrorCantidadMaximaJugadores)) {
			JOptionPane.showMessageDialog(null,"¡Superó la cantidad máxima de jugadores!");
		}
	}
	
	//TODO MOSTRAR TURNO
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

	
	//TODO MOSTRAR GANADOR
	@Override
	public void avisoGanador()  {
		this.setIcon(GANADOR, lblIcono);
		this.lblMensaje.setText("¡GANASTE!");
		setTextLblInfoSecreta();
		
		cardLayout.show(this.frmEspionaje.getContentPane(), GANADOR);
	}
	
	private void setTextLblInfoSecreta() {
		try {
			String ciudad = controlador.getInfoSecreta()[0].getFigura();
			String disp = controlador.getInfoSecreta()[1].getFigura();
			String agente = controlador.getInfoSecreta()[2].getFigura();
			this.setIcon(ciudad, this.lblCartaciudad);
			this.setIcon(disp, this.lblCartadisp);
			this.setIcon(agente, this.lblCartaagente);
			this.lblCartaciudad.setText(ciudad);
			this.lblCartadisp.setText(disp);
			this.lblCartaagente.setText(agente);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//TODO MOSTRAR PERDIO
	@Override
	public void avisoPerdio() {
			this.setIcon("PERDIO", this.lblIcono);
			this.lblMensaje.setText("¡PERDISTE!");
				setTextLblInfoSecreta();
					cardLayout.show(this.frmEspionaje.getContentPane(), GANADOR);		
	}


	
	
	
//TODO ----- MÉTODOS PARA CREAR PANTALLAS -----
	
// TODO CREAR CONFIGURACION	
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
		this.setIcon("TITULO", lblSimbolo);
		
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
		
		sizedFont2 = normalFont.deriveFont(28f);
		JLabel lblJugadores = new JLabel("JUGADORES");
		lblJugadores.setForeground(Color.WHITE);
		lblJugadores.setFont(sizedFont2);
		lblJugadores.setBounds(28, 136, 131, 41);
		pantallaConfiguracion.add(lblJugadores);
		
		Font sizedFont3 = normalFont.deriveFont(18f);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 179, 212, 94);
		pantallaConfiguracion.add(scrollPane);
		textJugadores = 
				new JTextArea();
		scrollPane.setViewportView(textJugadores);
		textJugadores.setForeground(Color.WHITE);
		textJugadores.setBackground(Color.GRAY);
		textJugadores.setFont(sizedFont3);
		textJugadores.setEditable(false);
	

		return pantallaConfiguracion;
	}
	
	// TODO CREAR ENTRADA : Se utiliza para la sospecha		
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
		this.setIcon("TITULO", lblEspionaje);
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
		
		btnArriesgar = new JButton("ARRIESGAR");
		btnArriesgar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					controlador.arriesgar();
				} catch (RemoteException e1) { 
					e1.printStackTrace();
				}
			}
		});
		btnArriesgar.setBounds(20, 260, 115, 23);
		pantallaEntrada.add(btnArriesgar);
		btnArriesgar.setFont(sizedFont);
		
		btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista.clear();
				if(!s1.getSelectedItem().equals(s2.getSelectedItem())) {
					lista.add((String) s1.getSelectedItem());
					lista.add((String) s2.getSelectedItem());
					controlador.setSospecha(lista);
					s1.removeAllItems();
					s2.removeAllItems();
					btnArriesgar.setEnabled(false);
					btnEnviar.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Esperando respuesta de "+controlador.listaJugadores().get(controlador.getSospechado()).getNombre());
					
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

	// TODO CREAR RESPONDER (Enviar Respuesta)
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
		this.setIcon("TITULO", label);
		
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
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(354, 267, 89, 33);
		pantallaResponder.add(btnOk);

		
		return pantallaResponder;
	}
	
	// TODO CREAR ARRIESGAR	
	private JPanel crearPantallaArriesgar() {
		this.pantallaArriesgar = new JPanel();
		pantallaArriesgar.setBackground(Color.DARK_GRAY);
		pantallaArriesgar.setLayout(null);
		
		JLabel label_1 = new JLabel("ESPIONAJE");
		label_1.setFont(boldFont.deriveFont(40f));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 40));
		label_1.setBounds(0, 11, 465, 55);
		pantallaArriesgar.add(label_1);
		this.setIcon("TITULO", label_1);
		

		JLabel lblElegLasCartas = new JLabel("ELEG\u00CD LAS CARTAS DE TU ACUSACI\u00D3N:");
		lblElegLasCartas.setForeground(Color.LIGHT_GRAY);
		lblElegLasCartas.setFont(normalFont.deriveFont(20f));
		lblElegLasCartas.setHorizontalAlignment(SwingConstants.CENTER);
		lblElegLasCartas.setBounds(45, 77, 388, 36);
		pantallaArriesgar.add(lblElegLasCartas);
		
		JLabel lblCiudad = new JLabel("CIUDAD:");
		lblCiudad.setForeground(Color.LIGHT_GRAY);
		lblCiudad.setFont(normalFont.deriveFont(18f));
		lblCiudad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCiudad.setBounds(45, 124, 169, 34);
		pantallaArriesgar.add(lblCiudad);
		
		cbCiudad = new JComboBox();
		cbCiudad.setFont(normalFont.deriveFont(18f));
		cbCiudad.setBounds(224, 124, 156, 27);
		pantallaArriesgar.add(cbCiudad);
		
		cbAgente = new JComboBox();
		cbAgente.setFont(normalFont.deriveFont(18f));
		cbAgente.setBounds(224, 153, 156, 27);
		pantallaArriesgar.add(cbAgente);
		
		cbDispositivo = new JComboBox();
		cbDispositivo.setFont(normalFont.deriveFont(18f));
		cbDispositivo.setBounds(224, 184, 156, 27);
		pantallaArriesgar.add(cbDispositivo);
		
		JLabel lblAgente = new JLabel("AGENTE:");
		lblAgente.setForeground(Color.LIGHT_GRAY);
		lblAgente.setFont(normalFont.deriveFont(18f));
		lblAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgente.setBounds(45, 149, 169, 34);
		pantallaArriesgar.add(lblAgente);
		
		JLabel lblDispositivo = new JLabel("DISPOSITIVO:");
		lblDispositivo.setForeground(new Color(192, 192, 192));
		lblDispositivo.setFont(normalFont.deriveFont(18f));
		lblDispositivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDispositivo.setBounds(45, 180, 169, 34);
		pantallaArriesgar.add(lblDispositivo);
		
		JButton btnEnviar_1 = new JButton("ENVIAR");
		btnEnviar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.rtaSospechaFinal((String) cbAgente.getSelectedItem(), (String) cbDispositivo.getSelectedItem(),(String)  cbCiudad.getSelectedItem());
			}
		});
		btnEnviar_1.setBounds(334, 253, 109, 34);
		pantallaArriesgar.add(btnEnviar_1);
		

		return pantallaArriesgar;

	}
	
	// TODO CREAR GANADOR	
	private JPanel crearPantallaGanador() {
		this.pantallaGanador = new JPanel();
		pantallaGanador.setBackground(Color.DARK_GRAY);
		pantallaGanador.setLayout(null);
		
		Font sizedFontTitulo = boldFont.deriveFont(40f);
		
		JLabel label_2 = new JLabel("ESPIONAJE");
		label_2.setFont(boldFont.deriveFont(40f));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(sizedFontTitulo);
		label_2.setBounds(0, 11, 465, 55);
		pantallaGanador.add(label_2);
		
		this.setIcon("TITULO", label_2);

		Font sizedFont = normalFont.deriveFont(20f);
		
		Font sizedFontBotones = normalFont.deriveFont(13f);
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.finalizarPartida();
				frmEspionaje.setVisible(false);
				frmEspionaje.dispose();
			}
		});
		btnSalir.setFont(sizedFontBotones);
		btnSalir.setBounds(370, 271, 85, 29);
		pantallaGanador.add(btnSalir);
		
		
		JButton btnVolverAJugar = new JButton("VOLVER A JUGAR");
		btnVolverAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.reiniciar();
			}
		});
		btnVolverAJugar.setFont(sizedFontBotones);
		btnVolverAJugar.setBounds(231, 271, 129, 29);
		pantallaGanador.add(btnVolverAJugar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(49, 62, 368, 184);
		pantallaGanador.add(panel);
		panel.setLayout(null);
		
		lblIcono = new JLabel("");
		lblIcono.setBounds(-2, 0, 145, 184);
		panel.add(lblIcono);
		
				lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
				lblMensaje = new JLabel("MENSAJE");
				lblMensaje.setBounds(-12, 0, 433, 69);
				panel.add(lblMensaje);
				lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
				lblMensaje.setForeground(Color.RED);
				lblMensaje.setFont(sizedFont);
				
				JLabel lblInformacinSecreta = new JLabel("INFORMACI\u00D3N SECRETA");
				lblInformacinSecreta.setFont(sizedFont);
				lblInformacinSecreta.setForeground(Color.ORANGE);
				lblInformacinSecreta.setBounds(147, 60, 211, 28);
				panel.add(lblInformacinSecreta);
				
				lblCartaciudad = new JLabel("CartaCiudad");
				lblCartaciudad.setFont(sizedFont);
				lblCartaciudad.setBounds(153, 90, 205, 28);
				panel.add(lblCartaciudad);
				
				lblCartadisp = new JLabel("CartaDisp");
				lblCartadisp.setFont(sizedFont);
				lblCartadisp.setBounds(153, 120, 205, 28);
				panel.add(lblCartadisp);
				
				lblCartaagente = new JLabel("CartaAgente");
				lblCartaagente.setFont(sizedFont);
				lblCartaagente.setBounds(153, 154, 205, 28);
				panel.add(lblCartaagente);
		


		return pantallaGanador;
	}
	
	
	// TODO CREAR TURNO	
	private JPanel crearPantallaTurno() {
		JPanel pantallaTurno = new JPanel();
		pantallaTurno.setBackground(Color.DARK_GRAY);
		pantallaTurno.setLayout(null);
		
		Font sizedFontTitulo = boldFont.deriveFont(48f);
		Font sizedFont= normalFont.deriveFont(24f);
		this.setIcon("ESPIA", lblnro);
		
		JLabel label = new JLabel("ESPIONAJE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(sizedFontTitulo);
		label.setBounds(0, 11, 465, 55);
		pantallaTurno.add(label);
		this.setIcon("TITULO", label);
		
		 lblTURNOText = new JLabel("TEXT");
		 lblTURNOText.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTURNOText.setForeground(Color.WHITE);
		lblTURNOText.setFont(sizedFont);
		lblTURNOText.setBounds(10, 149, 445, 41);
		pantallaTurno.add(lblTURNOText);
		
		lblTURNOJugador = new JLabel("Jugador");
		lblTURNOJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblTURNOJugador.setFont(sizedFont);
		lblTURNOJugador.setForeground(Color.ORANGE);
		lblTURNOJugador.setBounds(182, 91, 273, 35);
		pantallaTurno.add(lblTURNOJugador);
		
		lblTURNOSospechado = new JLabel("Jugador");
		lblTURNOSospechado.setHorizontalAlignment(SwingConstants.CENTER);
		lblTURNOSospechado.setFont(sizedFont);
		lblTURNOSospechado.setForeground(Color.RED);
		lblTURNOSospechado.setBounds(195, 201, 260, 41);
		pantallaTurno.add(lblTURNOSospechado);
		
		JLabel lblnro = new JLabel("");
		lblnro.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnro.setForeground(Color.LIGHT_GRAY);
		lblnro.setBounds(20, 77, 195, 179);
		lblnro.setFont(sizedFont);
	
		pantallaTurno.add(lblnro);
		return pantallaTurno;
	}

	// TODO CREAR MOSTRAR (Mostrar respuesta)	
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
		this.setIcon("TITULO", label);
		
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
