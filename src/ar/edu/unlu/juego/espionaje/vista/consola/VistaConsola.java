package ar.edu.unlu.juego.espionaje.vista.consola;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;

import java.io.Serializable;

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
	public void mostrarJugando() {
		menu = new MenuJuego(controlador);
		menu.mostrarMenu();
	}

	@Override
	public void mostrarSospecha(int jugador) {
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
	public void mostrarTerminado() {
		System.out.println("------------ FIN DEL JUEGO ----------------");
		System.out.println();
		System.out.println("--------        ESPIONAJE     -----------");		
	}

	@Override
	public void mostrarElegirRespuesta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarResponder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarRespuesta() {
		// TODO Auto-generated method stub
		
	}

		

	@Override
	public void avisoPerdio() {
		//No lo utilizo en esta vista.
		
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
		// TODO Auto-generated method stub
		
	}

	

}
