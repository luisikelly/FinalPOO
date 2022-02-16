package ar.edu.unlu.juego.espionaje.vista.consola;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuSospecha extends Menu{
	Scanner entrada;
	Menu turno;
	int jug,jugTurno;
	
	
	public MenuSospecha(Controlador controlador, VistaConsola vista ) {
		this.miControlador = controlador;
		this.miVista = vista;
		this.jug = miControlador.getNroJugador();
		this.jugTurno = miControlador.getJugadorEnTurno().getNroJugador();
		System.out.println("-----------------------------------------");
		System.out.println("--------        ESPIONAJE     -----------");
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println();
	}
	
	
	@Override
	public void mostrarMenu() {
		if(jug == jugTurno) {
			Scanner entrada = new Scanner(System.in);
			
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
				case "3" : this.mostrarMenuSospecha();
				case "4":
					try {this.miControlador.arriesgar();	} 
					catch (RemoteException e1) { e1.printStackTrace();} 
					break;
			}

	}else {
		System.out.println("-----------------------------------------");
		System.out.println("Ahora es el turno de " + miControlador.getJugadorEnTurno().getNombre());
		System.out.println("-----------------------------------------");
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
		if(miControlador.getNroJugador() == miControlador.getJugadorEnTurno().getNroJugador()) {
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
			int s1 = this.seleccionarOpcionSospecha(disponibles.size());
			int s2 = this.seleccionarOpcionSospecha(disponibles.size());
			while(s1 == s2) {
		    	System.out.println("LOS ELEMENTOS DE LAS SOSPECHAS ESTAN REPETIDAS");
		    	System.out.println();
		    	System.out.println("Ingresa nuevamente tu sospecha");
		    	System.out.println();
		    	s1 = this.seleccionarOpcionSospecha(disponibles.size());
		    	s2 = this.seleccionarOpcionSospecha(disponibles.size());
			}
			sospecha.add(disponibles.get(s1));
			sospecha.add(disponibles.get(s2));
			
				
		System.out.println("------------------"
				+ "-----------------------");
		System.out.println( " Enviaste tu sospecha a " + miControlador.listaJugadores().get(miControlador.getSospechado()).getNombre());
		System.out.println("-----------------------------------------");
		
		miControlador.setSospecha(sospecha);
			
		}else {
			System.out.println("-----------------------------------------");
			System.out.println("Ahora es el turno de " + miControlador.getJugadorEnTurno().getNombre());
			System.out.println("-----------------------------------------");
		}


	}


	
	private int seleccionarOpcionSospecha(int maxOpciones) {
		Scanner entrada = new Scanner(System.in);	
		int s;
		System.out.println("................PRESIONE ENTER PARA CONTINUAR................ ");
		entrada.nextLine();	
		System.out.println("Selecciona elemento de la sospecha ");
		s = entrada.nextInt();
		while(s>=maxOpciones) {
			System.out.println();
			System.out.println("--- Elemento invalido ---");
			System.out.println();
			System.out.println("Selecciona elemento de la sospecha ");
			s = entrada.nextInt();
		}
		return s;
	}


}