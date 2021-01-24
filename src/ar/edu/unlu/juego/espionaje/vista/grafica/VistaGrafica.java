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

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;
import java.awt.CardLayout;

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
	private final String SOSPECHA = "SOSPECHA";
	private final String RESPUESTA = "RESPUESTA";
	private final String GANADOR = "GANADOR";
	private final String ErrorCantidadMinimaJugadores = "MenosJugadores";
	private final String ErrorCantidadMaximaJugadores = "MasJugadores";
	
	//OTROS
	private int seleccionados = 0; 
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
		
		normalFont = Font.createFont(Font.TRUETYPE_FONT, isNormal);
		boldFont = Font.createFont(Font.TRUETYPE_FONT, isBold);
		lightFont = Font.createFont(Font.TRUETYPE_FONT, isLight);
		
		contentPane.add(this.crearPantallaConfig(),CONFIG);		
		//pantallaConfig = new PanelConfig(this.controlador), CONFIG);
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
		try {
			contentPane.add(pantallaSospecha = new PanelMostrar(this.controlador, SOSPECHA), SOSPECHA);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		cardLayout.show(contentPane, SOSPECHA);
	}


	@Override
	public void avisoGanador() {
		try {
			contentPane.add(pantallaGanador = new Ganador(this.controlador), GANADOR);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		cardLayout.show(contentPane, GANADOR);
	
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
		cardLayout.show(contentPane, CONFIG);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardLayout.show(contentPane, RESPONDER);
		
	}


	@Override
	public void mostrarRespuesta() {
	try {
		contentPane.add(pantallaRespuesta = new PanelMostrar(this.controlador, RESPUESTA), RESPUESTA);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	cardLayout.show(contentPane, RESPUESTA);
		
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
	public void mostrarSospechar() {
		// TODO Auto-generated method stub
		try {
			contentPane.add(pantallaSospechar = this.crearPantallaEntrada(SOSPECHAR), SOSPECHAR);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
				String nombre = textField.getText();
				controlador.agregarJugador(nombre);
				System.out.println(textField.getText());
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
	
	private JPanel crearPantallaEntrada(String tipo) throws RemoteException {
		JPanel pantallaEntrada = new JPanel();
		int seleccionados = 0;
		
		pantallaEntrada.setBackground(Color.DARK_GRAY);
		pantallaEntrada.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEspionaje = new JLabel("Espionaje");
		lblEspionaje.setHorizontalAlignment(SwingConstants.CENTER);
		pantallaEntrada.add(lblEspionaje, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		
		pantallaEntrada.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(75, 49, 46, 14);
		panel.add(lblTipo);
		
		JLabel lblNombrejugador = new JLabel("NombreJugador");
		lblNombrejugador.setBounds(226, 49, 46, 14);
		panel.add(lblNombrejugador);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 74, 329, 156);
		panel.add(scrollPane);
		
		JButton btnArriesgar = new JButton("ARRIESGAR");
		btnArriesgar.setBounds(332, 11, 108, 23);
		panel.add(btnArriesgar);
		
		if(tipo.equals("RESPONDER")) {
			btnArriesgar.setVisible(false);
		}
			
		JButton btnOk = 
				new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipo.equals("SOSPECHAR")) {
					if(seleccionados == 2) {
						controlador.recibirSospecha(lista);
					}else {
						JOptionPane.showMessageDialog(null,"La sospecha contiene 2 elementos");
					}
				}
				if(tipo.equals("RESPONDER")) {
					if(seleccionados == 1) {
						controlador.setRespuesta(lista.get(0));
					}else {
						JOptionPane.showMessageDialog(null,"La respuesta debe contener solo un elemento");
					}
				}
					
					
			}
		});
		btnOk.setBounds(332, 252, 89, 23);
		panel.add(btnOk);
		
		
		if(tipo.equals("SOSPECHAR")) {
			for(int i = 0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1;i++) {
				addCheckBox(scrollPane,controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura(),pantallaEntrada);
			}
		}
		if(tipo.equals("RESPONDER")) {
			for(int i = 0; i<= controlador.getSospecha().length-1 ;i++) {
				addCheckBox(scrollPane,controlador.getSospecha()[i].getFigura(),pantallaEntrada);
			}
		}

		return pantallaEntrada;
	}
	
JCheckBox cb;
	
	private void addCheckBox(JScrollPane scrollPane ,String cbName, JPanel pantalla) {
	       cb = new JCheckBox(cbName);	       
	      cb.addChangeListener((ChangeListener) pantalla);
	       scrollPane.add(cb);
	       scrollPane.revalidate();
	}
	
	public void stateChanged(ChangeEvent e){
		if(cb.isSelected() && seleccionados < 2) {
			seleccionados ++;
			lista.add(cb.getName());
		}
		if(!cb.isSelected()&& seleccionados > 0) {
			seleccionados --;
			int p = this.buscarSeleccionados(lista, cb.getName());
			if(p != -1) {
				lista.remove(p);
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

}
