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
		entrada = new Scanner(System.in);	
		
		if(this.registrado) {
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
				//entrada.close();
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
				default: this.mostrarMenu(); break;  		

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
					e.nextLine();
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
							this.mostrarMenu();
						break;
					default: this.mostrarMenu(); break;  		

					}
			}
			
		}else {
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
			
			switch(opcion) {
			case "0": miControlador.finalizarPartida();
					break;	
			case "1": registrado = true;	
				miVista.agregarJugador();	
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
			default: this.mostrarMenu(); break;  		
			}
			
			
		}
		
	}

}
