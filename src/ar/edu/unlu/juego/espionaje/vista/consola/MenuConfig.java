package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuConfig extends Menu {
	boolean registrado;


	public MenuConfig(Controlador controlador, VistaConsola vista) {
		this.miControlador = controlador;
		this.miVista = vista;
		registrado = false;
	}


	@Override
	public void mostrarMenu() {
		Scanner entrada = new Scanner(System.in);	
		
		if(this.registrado) {
			System.out.println("-----------------------------------------");
			System.out.println();
			System.out.println("1. Iniciar juego");
			System.out.println("2. Ayuda");
			System.out.println("3. Historial de Ganadores");
			System.out.println("0. Salir");
			System.out.println();
			System.out.println("Ingrese opcion");
			String opcion= entrada.nextLine();
			
			switch(opcion) {
			case "0": miControlador.finalizarPartida();
					break;
			case "1": miControlador.iniciarPartida();      
			  		break; 
			case "2": miVista.mostrarAyuda();
					  this.mostrarMenu();				
					break;
			case "3": miVista.mostrarGanadores(); 
					  this.mostrarMenu();
			  		break;
			}

		}else {
			System.out.println("-----------------------------------------");
			System.out.println();
			System.out.println("1. Registrarse");
			System.out.println("2. Ayuda");
			System.out.println("3. Historial de Ganadores");
			System.out.println("0. Salir");
			System.out.println();
			System.out.println("Ingrese opcion");
			String opcion= entrada.nextLine();
			
			switch(opcion) {
			case "0": miControlador.finalizarPartida();
					break;
					
			case "1": registrado = true;	
				miVista.agregarJugador();	
					this.mostrarMenu();
	  				break; 		
			case "2": miVista.mostrarAyuda();
					  this.mostrarMenu();				
					break;
			case "3": miVista.mostrarGanadores(); 
						this.mostrarMenu();
			  		break;
			}

		}
		
	}

}
