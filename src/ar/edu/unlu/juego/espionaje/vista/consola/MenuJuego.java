package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuJuego extends Menu {

	private Controlador miContolador;
	Scanner entrada = new Scanner(System.in);
	
	public MenuJuego(Controlador miContolador) {
		super();
		this.miContolador = miContolador;
	}

	@Override
	public void mostrarMenu() {
		System.out.println("-----------------------------------------");
		System.out.println("--------        ESPIONAJE     -----------");
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println();
			System.out.println("¡Es tu turno!");
			System.out.println(" __________________________________ ");
			System.out.println("| ______   AGENDA PERSONAL _______ |");
			for(int i=0; i<= miControlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1; i++) {
				if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida())
					System.out.println("    "+ miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura() + "    ");
			}
			System.out.println();
			System.out.println();
			System.out.println(" _____________________________________________________ ");
			System.out.println("| ______  MIS CARTAS DEL ARCHIVO CONFIDENCIAL _______ |");
			for(int i=0; i<= miControlador.getJugadorEnTurno().getCartasSecretas().cantCartas() -1; i++) {
				System.out.println(miControlador.getJugadorEnTurno().getCartasSecretas().getCarta(i).getFigura());
			}
			System.out.println();
			System.out.println();
			System.out.println("Elija una opción");
			System.out.println();
			System.out.println("1- Realizar Sospecha");
			System.out.println("2- Realizar Acusación");
			String opcion = entrada.nextLine();
			switch (opcion) {
				case "1" :
					this.menuSospecha(); //Menu para realizar sospecha 
					break;
				case "2" :
					this.menuAcusacion(); //Menu para arriesgar cartas finales
					
					break;
			}
		
		System.out.println("");
		
		System.out.println();

		
		
	}

	private ArrayList<String> elegirOpcion(int cantidadOpcionesAElegir, String s1, String s2) {
		// TODO Auto-generated method stub
		int opcionElegida = 0;
		ArrayList<String> elegidas = null;
		for(int i=0; i< cantidadOpcionesAElegir ; i++) {
			System.out.println("Selecciona "+ s1 +" para realizar tu "+ s2);
			int opcion = entrada.nextInt();
			while(opcion == opcionElegida) {
				System.out.println("¡La opcion " + opcion + " no esta disponible! ¡Inetnta nuevamente!");
			}
			opcionElegida = opcion;
			elegidas.add(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(opcionElegida - 1).getFigura());
		}
		return elegidas;
	} 
	
	
	private void menuAcusacion() { //ARRIESGAR 
		System.out.println();
		System.out.println();
		System.out.println("-------- REALIZAR ACUSACIÓN --------------");
		System.out.println();
		System.out.println("");
		System.out.println("Elegí TRES elementos para realizar tu acusación:");
		System.out.println();
		String dispositivo = null;
		String ciudad = null;
		String agente = null;
		for(int i=0; i<= miControlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1 ; i++) {
			int nOpcion = i+1;
			System.out.println("Ingrese la opcion de DISPOSITIVO");
			if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("DISPOSITIVO")) {
				System.out.println(nOpcion + miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				dispositivo = this.elegirOpcion(1,"un DISPOSITIVO","acusación").get(0);
			}
			nOpcion = 1;
			System.out.println("Ingrese la opcion de CIUDAD");
			if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("CIUDAD")) {
				System.out.println(nOpcion + miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());				
				ciudad =this.elegirOpcion(1," una CIUDAD","acusación").get(0);
			}
			nOpcion = 1;
			System.out.println("Ingrese la opcion de AGENTE");
			if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("AGENTE")) {		
				System.out.println(nOpcion + miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				agente = this.elegirOpcion(1," un AGENTE","acusación").get(0);
			}
		}
		ArrayList<String> acusacion = new ArrayList<String>();
		acusacion.add(dispositivo);
		acusacion.add(ciudad);
		acusacion.add(agente);
		
 		miControlador.setSospecha(acusacion);
	}



	private void menuSospecha() {
		System.out.println();
		System.out.println();
		System.out.println("-------- REALIZAR SOSPECHA --------------");
		System.out.println();
		System.out.println("");
		System.out.println("Elegí dos elementos para realizar tu sospecha:");
		System.out.println();
		for(int i=0; i<= miControlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1 ; i++) {
			int nOpcion = i+1;
			System.out.println(nOpcion + miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
		}
		System.out.println();
		ArrayList<String> sospecha = new ArrayList<String>();
		sospecha =this.elegirOpcion(2, "un elemento", "sospecha");
		
		miControlador.setSospecha(sospecha);
	
	}

	
	
}
