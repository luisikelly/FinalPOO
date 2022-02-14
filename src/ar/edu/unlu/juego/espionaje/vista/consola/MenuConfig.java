package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuConfig extends Menu {
	boolean registrado;
	Scanner entrada;

	public MenuConfig(Controlador controlador, VistaConsola vista, boolean b) {
		this.miControlador = controlador;
		this.miVista = vista;
		registrado = b;
		
	}


	@Override
	public void mostrarMenu() {
		
		if(this.registrado) {
			Scanner entrada = new Scanner(System.in);	
			miVista.mostrarTituloEspionaje();
			if(miControlador.getNroJugador()== 0) {
				System.out.println("-----------------------------------------");
				System.out.println();
				System.out.println("1. Iniciar juego");
				System.out.println("2. Jugadores");
				System.out.println("3. Ayuda");
				System.out.println("4. Historial de Ganadores");
				System.out.println("0. Salir");
				System.out.println();
				System.out.println("Ingrese opcion");
				String opcion= entrada.nextLine();
				if(!opcion.equals("0") && !opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4")) {
					this.mostrarMenu();
				}else {
					switch(opcion) {
					case "0": miControlador.finalizarPartida();
						break;
					case "1": miControlador.iniciarPartida();
					  	break; 
					case "2": miVista.listarJugadores();
							this.mostrarMenu();				
						break;
					case "3": miVista.mostrarAyuda();
							this.mostrarMenu();				
						break;
					case "4": miVista.mostrarGanadores(); 
							this.mostrarMenu();
						break;
					}
					
				}
			}else {

					Scanner e = new Scanner(System.in);
					System.out.println("-----------------------------------------");
					System.out.println();
					System.out.println("1. Jugadores");
					System.out.println("2. Ayuda");
					System.out.println("3. Historial de Ganadores");
					System.out.println("0. Salir");
					System.out.println();
					System.out.println("Ingrese opcion");
					String opcion= e.nextLine();
				//	if(!opcion.equals("0") && !opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")) {
				//		this.mostrarConfiguracion();
				//	}else {
						switch(opcion) {
						case "0": miControlador.finalizarPartida();
							break; 
						case "1": miVista.listarJugadores();
						this.mostrarMenu();				
							break;
						case "2": miVista.mostrarAyuda();
						this.mostrarMenu();				
							break;
						case "3": miVista.mostrarGanadores(); 
						this.mostrarMenu();	;
							break;
					}
				//}
			}

			
	
			
		}else {
			Scanner entrada = new Scanner(System.in);	
			miVista.mostrarTituloEspionaje();
			System.out.println("-----------------------------------------");
			System.out.println();
			System.out.println("1. Registrarse");
			System.out.println("2. Jugadores");	
			System.out.println("3. Ayuda");
			System.out.println("4. Historial de Ganadores");
			System.out.println("0. Salir");
			System.out.println();
			System.out.println("Ingrese opcion");
			String opcion= entrada.nextLine();
			if(!opcion.equals("0") && !opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4")) {
				this.mostrarMenu();
			}else {
				switch(opcion) {
				case "0": miControlador.finalizarPartida();
					break;	
				case "1": 
						miVista.agregarJugador();
						registrado = true;
						this.mostrarMenu();
		  			break; 		
				case "2": miVista.listarJugadores();
						this.mostrarMenu();
					break;
				case "3": miVista.mostrarAyuda();
						this.mostrarMenu();
					break;
				case "4": miVista.mostrarGanadores(); 
						this.mostrarMenu();
				  	break;
				}
			}			
		}

	
	}
}