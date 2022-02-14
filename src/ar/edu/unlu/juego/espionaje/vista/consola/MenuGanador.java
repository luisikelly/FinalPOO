package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuGanador extends Menu {
	private  Controlador miControlador;
	
	public MenuGanador(Controlador miControlador, VistaConsola vista) {
		super();
		this.miControlador = miControlador;
		miVista = vista;
	}


	@Override
	public void mostrarMenu() {
		System.out.println();
		miVista.mostrarTituloEspionaje();		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("--------       FIN DEL JUEGO     -----------");
		System.out.println();
		System.out.println();
		System.out.println(miControlador.getGanador().getNombre() + " GANÓ EL JUEGO");
		System.out.println();
		System.out.println();
		System.out.println("||||||||||||||||||||||||||||||");
		System.out.println("   INFORMACION CONFIDENCIAL ");
		System.out.println("||||||||||||||||||||||||||||||");
		System.out.println();
		System.out.println();
		System.out.println("CIUDAD: " + miControlador.informacionConfidencial()[0].getFigura());
		System.out.println();
		System.out.println("AGENTE: " +miControlador.informacionConfidencial()[1].getFigura());
		System.out.println();
		System.out.println("DISPOSITIVO: " + miControlador.informacionConfidencial()[2].getFigura());
		System.out.println();
		System.out.println();
		
	}
}
