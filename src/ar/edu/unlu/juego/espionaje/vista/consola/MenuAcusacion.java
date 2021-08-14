package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuAcusacion extends Menu {
	Scanner entrada;
	

	public MenuAcusacion(Controlador controlador) {
		miControlador = controlador;
		entrada = new Scanner(System.in);

	}

@Override
	public void mostrarMenu() {
		System.out.println();
		System.out.println();
		System.out.println("-------- REALIZAR ACUSACIÓN --------------");
		System.out.println();
		System.out.println("");
		System.out.println("Elegí TRES elementos para realizar tu acusación:");
		System.out.println();
		System.out.println("................PRESIONE ENTER PARA CONTINUAR................ ");
		entrada.nextLine();
		
		System.out.println("~ SOSPECHA FINAL ~");
		String dispositivo = this.mostrarDispositivos();
			System.out.println();
			System.out.println(dispositivo);
			System.out.println();

			String ciudades = this.mostrarCiudades();
			System.out.println();
			System.out.println(ciudades);
			System.out.println();
	
			String agentes = this.mostrarAgentes();		
			System.out.println();
			System.out.println(agentes);
			System.out.println();
			
			miControlador.rtaSospechaFinal(agentes, dispositivo, ciudades);
		
		}

	private String mostrarDispositivos() {
		String dispositivo = null;
		System.out.println("Ingrese la opcion de DISPOSITIVO");
		System.out.println("1- SATELITE");
		System.out.println("2- AUTOPROPULSOR");
		System.out.println("3- GAS LETAL");
		System.out.println("4- AVION");
		System.out.println("5- HELICOPTERO");
		String disp = entrada.nextLine();
		switch(disp) {
		case "1" : dispositivo = "SATELITE";
			break;
			
		case "2" : dispositivo = "AUTOPROPULSOR";
			break;
		case "3" : dispositivo = "GAS_LETAL";
			break;
		case "4" : dispositivo = "AVION";
			break;
		case "5" : dispositivo = "HELICOPTERO";
			break;
		default: System.out.println("Elemento incorrecto! Ingresa nuevamente tu sospecha");
			this.mostrarDispositivos();
		break;
		}
		return dispositivo;		
	}

	private String mostrarCiudades() {
		String ciudades = null;
		System.out.println("Ingrese la opcion de CIUDAD");
		System.out.println("1- PARIS");
		System.out.println("2- LONDRES");
		System.out.println("3- TOKIO");
		System.out.println("4- PANAMA");
		System.out.println("5- ATENAS");
		
		String ciudad = entrada.nextLine();

		
		
		switch(ciudad) {
		case "1" : ciudades = "PARIS";
			break;
		case "2" : ciudades = "LONDRES";
			break;
		case "3" : ciudades = "TOKIO";
			break;
		case "4" : ciudades = "PANAMA";
			break;
		case "5" : ciudades =  "ATENAS";
			break;	
		default: System.out.println("Elemento incorrecto! Ingresa nuevamente tu sospecha");
			this.mostrarCiudades();
		break;
		
		}
		return ciudades;
	}
	
	private String mostrarAgentes() {
		String agentes = null;
		System.out.println("Ingrese la opcion de AGENTE");
		System.out.println("1- AGENTE ROJO");
		System.out.println("2- AGENTE VERDE");
		System.out.println("3- AGENTE AZUL");
		System.out.println("4- AGENTE NARANJA");
		System.out.println("5- AGENTE BLANCO");
		
		String agente = entrada.nextLine();
		switch(agente) {
		case "1" : agentes = "AGENTE_ROJO";
			break;
		case "2" : agentes = "AGENTE_VERDE";
			break;
		case "3" : agentes = "AGENTE_AZUL";
			break;
		case "4" : agentes = "AGENTE_NARANJA";
			break;
		case "5" : agentes = "AGENTE_BLANCO";
			break;	
		default: System.out.println("Elemento incorrecto! Ingresa nuevamente tu sospecha");
			this.mostrarAgentes();
		break;		
		}

		return agentes;
		
	}
	

}
