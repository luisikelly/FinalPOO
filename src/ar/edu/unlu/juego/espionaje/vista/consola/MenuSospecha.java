package ar.edu.unlu.juego.espionaje.vista.consola;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuSospecha extends Menu{
	Scanner entrada;

	
	
	public MenuSospecha(Controlador controlador, VistaConsola vista) {
		this.miControlador = controlador;
		this.miVista = vista;
		System.out.println("-----------------------------------------");
		System.out.println("--------        ESPIONAJE     -----------");
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println();
	    System.out.println("¡Es tu turno!");
		System.out.println();
		
		System.out.println();
	}
	
	
	@Override
	public void mostrarMenu() {
		entrada = new Scanner(System.in);
		entrada.reset();
			System.out.println("Elija una opción");
			System.out.println();
			System.out.println("1- Ver Agenda Personal");
			System.out.println("2- Ver Archivo Confidencial");
			System.out.println("3- Realizar Sospecha");
			System.out.println("4- Realizar Acusación");
		    String opcion = entrada.nextLine();
			System.out.println(opcion);			
			switch (opcion) {
				case "1" : 
					this.mostrarAP();
					this.mostrarMenu();
					break;
				case "2" : 
					this.mostrarAC();
				    this.mostrarMenu();
					break;
				case "3" : 
					mostrarMenuSospecha();  //Menu para realizar sospecha 
					break;
				case "4" :
					try {this.miControlador.arriesgar();	} 
					catch (RemoteException e1) { e1.printStackTrace();} 
					break;
				default: 
					System.out.println("Error! Ingrear nuevamnte la acción a realizar");
					this.mostrarMenu();
					break;
			}
	
	}


	private void mostrarAC() {
		System.out.println(" _____________________________________________________ ");
		System.out.println("| ______  MIS CARTAS DEL ARCHIVO CONFIDENCIAL _______ |");
		for(int i=0; i<= miControlador.getJugadorEnTurno().getCartasSecretas().cantCartas() -1; i++) {
			System.out.println("    "+ miControlador.getJugadorEnTurno().getCartasSecretas().getCarta(i).getFigura());
		}
		System.out.println();
		System.out.println();
	}


	private void mostrarAP() {
		System.out.println(" __________________________________ ");
		System.out.println("| ______   AGENDA PERSONAL _______ |");
		for(int i=0; i<= miControlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
			if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida())
				System.out.println("    "+ miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura() + "    ");
		}
		System.out.println();
		System.out.println();
	}


	private void mostrarMenuSospecha() {
		entrada.reset();
		System.out.println();
		System.out.println();
		System.out.println("-------- REALIZAR SOSPECHA --------------");
		System.out.println();
		System.out.println("");
		System.out.println("Elegí dos elementos para realizar tu sospecha:");
		System.out.println();
		int nOpcion = 0;
		ArrayList<String> disponibles = new ArrayList<String>();
		for(int i=0; i<= miControlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1 ; i++) {
			if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida()) {
				System.out.println(nOpcion+"- " + miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());	
				disponibles.add(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				nOpcion++;
			}
		}
		
	
		System.out.println();
		ArrayList<String> sospecha = new ArrayList<String>();
		int s1 = -1;
		int s2 = -1;
		System.out.println("................PRESIONE ENTER PARA CONTINUAR................ ");
		entrada.nextLine();
		while(s1 == s2) {
			System.out.println("Seleccionar primer elemento de la sospecha ");
			s1 = entrada.nextInt();
			System.out.println();
			System.out.println("Seleccionar segundo elemento de la sospecha ");
			s2 = entrada.nextInt();
			if(s1 == (s2)) {
		    	System.out.println("LOS ELEMENTOS DE LAS SOSPECHAS ESTAN REPETIDAS");
		    	System.out.println();
		    	System.out.println("Ingresa nuevamente tu sospecha");
		    	System.out.println();
		    }	
		}
		sospecha.add(disponibles.get(s1));
		sospecha.add(disponibles.get(s2));
		entrada.nextLine();
		
			
	System.out.println("-----------------------------------------");
	System.out.println( " Enviaste tu sospecha a " + miControlador.listaJugadores().get(miControlador.getSospechado()).getNombre());
	System.out.println("-----------------------------------------");
	
	miControlador.setSospecha(sospecha);
		
		
	}

}