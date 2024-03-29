package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuAcusacion extends Menu {
	Scanner entrada;
	private int jugTurno, jug;

	public MenuAcusacion(Controlador controlador,VistaConsola vista) {
		miControlador = controlador;
		miVista = vista;
		entrada = new Scanner(System.in);
		jugTurno = miControlador.getJugadorEnTurno().getNroJugador();
		jug = miControlador.getNroJugador();
	}

@Override
	public void mostrarMenu() {
	Scanner entrada = new Scanner(System.in);
	miVista.mostrarTituloEspionaje();
	if(jug == jugTurno) {
		System.out.println();
		System.out.println();
		System.out.println("-------- REALIZAR ACUSACI�N --------------");
		System.out.println();
		System.out.println("");
		System.out.println("Eleg� TRES elementos para realizar tu acusaci�n:");
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

	}else {
			System.out.println("-----------------------------------------");
			System.out.println( miControlador.getJugadorEnTurno().getNombre()+" est� realizando su acusaci�n ");
			System.out.println("-----------------------------------------");	
	}
}

private String mostrarDispositivos() {
	String dispositivo = null;
	System.out.println("Ingrese la opcion de DISPOSITIVO");
	System.out.println("1- SATELITE");
	System.out.println("2- AUTOPROPULSOR");
	System.out.println("3- GAS LETAL");
	System.out.println("4- AVION");
	System.out.println("5- HELICOPTERO");
	Scanner eDispositivo = new Scanner(System.in);	
	String disp = eDispositivo.nextLine();
	if(!disp.equals("1") && !disp.equals("2") && !disp.equals("3") && !disp.equals("4") && !disp.equals("5")) {
		this.mostrarDispositivos();
	}else {
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
		}

	}

	return dispositivo;		
}

private String mostrarCiudades() {
	String ciudades = "";
	System.out.println("Ingrese la opcion de CIUDAD");
	System.out.println("1- PARIS");
	System.out.println("2- LONDRES");
	System.out.println("3- TOKIO");
	System.out.println("4- PANAMA");
	System.out.println("5- ATENAS");
	Scanner eCiudad = new Scanner(System.in);	
	String ciudad = eCiudad.nextLine();
	if(!ciudad.equals("1") && !ciudad.equals("2") && !ciudad.equals("3") && !ciudad.equals("4") && !ciudad.equals("5")) {
		this.mostrarCiudades();
	}else {
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
		}

	}
	return ciudades;
}

private String mostrarAgentes() {
	String agentes = "";
	System.out.println("Ingrese la opcion de AGENTE");
	System.out.println("1- AGENTE ROJO");
	System.out.println("2- AGENTE VERDE");
	System.out.println("3- AGENTE AZUL");
	System.out.println("4- AGENTE NARANJA");
	System.out.println("5- AGENTE BLANCO");
	Scanner eAgente = new Scanner(System.in);	
	String agente = eAgente.nextLine();
	if(!agente.equals("1") && !agente.equals("2") && !agente.equals("3") && !agente.equals("4") && !agente.equals("5")) {
		this.mostrarAgentes();
	}else {
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
		}
	}
	return agentes;
}
	

}
