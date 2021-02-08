package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuConfig extends Menu {
	private Controlador miControlador;
	private VistaConsola miVista;
	
	public MenuConfig(Controlador miControlador, VistaConsola vistaConsola) {
		super();
		this.miControlador = miControlador;
		this.miVista = vistaConsola;
	}


	@Override
	public void mostrarMenu() {
		Scanner s = new Scanner(System.in);

		miVista.agregarJugador();		
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("1. Iniciar juego");
		System.out.println("2. Listar jugadores");
		System.out.println("0. Salir");
		System.out.println();
		System.out.println("Ingrese opcion");
		String opcion= s.nextLine();
		
		switch(opcion) {
		case "0": miControlador.finalizarPartida();
				  break;
		case "1": miControlador.iniciarPartida();      
				  break;
		case "2": miVista.mostraJugadores();
				  mostrarMenu();
				  break;
		default: miVista.mostrarError(opcion);		  
		}
	}

}
