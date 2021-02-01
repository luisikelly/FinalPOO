package ar.edu.unlu.juego.espionaje.vista.consola;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;

import java.io.Serializable;
import java.rmi.RemoteException;

public class VistaConsola implements IVista, Serializable {

	private Controlador controlador;
	private Menu menu;
	private Scanner entrada = new Scanner(System.in);
	
	public VistaConsola(Controlador c) {
		this.setControlador(c);
		controlador.setVista(this);
		this.mostrarConfiguracion();
	}
	
	@Override
	public void setControlador(Controlador c) {
		this.controlador = c;
		menu = new MenuConfig(controlador, this);
		
	}
	
	@Override
	public void iniciarJuego() {
		try{
			controlador.iniciarPartida();
		} catch(IndexOutOfBoundsException e) {
			this.mostrarError("-jugadores");
			menu.mostrarMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// "Pantallas" del juego 
	
	@Override
	public void mostrarArriesgar() {
		// 7/9/2020: No se utiliza ese metodo en esta vista POR AHORA. La función esta implementada en el menu del juego.		
	}



	@Override
	public void avisoGanador() {
		menu = new MenuGanador(controlador);
		menu.mostrarMenu();
	}

	@Override
	public void mostraJugadores() {
		System.out.println("--------------- Jugadores --------------");
		System.out.println();
		ArrayList<IJugador> j = controlador.listaJugadores();
		for (IJugador jugador : j) {
			System.out.println("Jugador: " +  jugador.getNombre());
		}
		System.out.println();
	}



	@Override
	public void mostrarConfiguracion() {
		menu = new MenuConfig(controlador, this);
		menu.mostrarMenu();
		
	}

	@Override
	public void mostrarResponder() {
		// TODO Auto-generated method stub
		
	}



		

	@Override
	public void avisoPerdio() {
		if(controlador.getGanador().getNroJugador() == controlador.getNroJugador()) {
			System.out.println("------ " + controlador.getJugadorEnTurno().getNombre() + " ------"  );
			System.out.println("                          ------ PERDISTE ------");
			System.out.println("               ------ TU SOSPECHA FINAL FUE INCORRECTA ------");
			System.out.println("");
			System.out.println("LA INFORMACION CONFIDENCIAL ES:");
			System.out.println();
			System.out.println("CIUDAD: " + controlador.informacionConfidencial()[0].getFigura());
			System.out.println();
			System.out.println("AGENTE: " + controlador.informacionConfidencial()[1].getFigura());
			System.out.println();
			System.out.println("DISPOSITIVO: " + controlador.informacionConfidencial()[2].getFigura());
			System.out.println();
		}
	}

	@Override
	public void mostrarError(String tError) {
		switch(tError) {
		case "-jugadores":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("La cantidad de jugadores registrados debe ser mayor a 2");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			break;
		case "+jugadores":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("HA SUPERADO LA CANTIDAD DE JUGADORES!");
			System.out.println("La cantidad maxima de jugadores es 4");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();

			break;
		case "error":
			break;	
		}
		
	}

	@Override
	public void mostrarSospechar() {
		menu = new MenuJuego(controlador);
		menu.mostrarMenu();
	}

	@Override
	public void mostrarRespuesta(String string) {
		System.out.println(controlador.listaJugadores().get(controlador.getSospechado()).getNombre()+ " responde:");
		System.out.println();
			try {
				System.out.println(controlador.getRespuesta().toUpperCase());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	@Override
	public void mostrarTurno(String string) {
		System.out.println("-----------------------------------------");
		System.out.println("Ahora es el turno de " + controlador.getJugadorEnTurno().getNombre());
		System.out.println("-----------------------------------------");
		
	}

	@Override
	public void quienGano() {
		System.out.println("------ " + controlador.getJugadorEnTurno().getNombre() + " ------"  );
		System.out.println("                          ------ PERDISTE ------");
		System.out.println(controlador.getGanador().getNombre()+ "  GANÓ LA PARTIDA");

	}

	

}
