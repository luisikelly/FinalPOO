package ar.edu.unlu.juego.espionaje.vista.consola;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuSospecha extends Menu{
	
	public MenuSospecha(Controlador controlador, VistaConsola vista) {
		this.miControlador = controlador;
		this.miVista = vista;
	}
	
	
	@Override
	public void mostrarMenu() {
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("-----------------------------------------");
		System.out.println("--------        ESPIONAJE     -----------");
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println();
			System.out.println("¡Es tu turno!");
			System.out.println(" __________________________________ ");
			System.out.println("| ______   AGENDA PERSONAL _______ |");
			for(int i=0; i<= miControlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
				if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida())
					System.out.println("    "+ miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura() + "    ");
			}
			System.out.println();
			System.out.println();
			System.out.println(" _____________________________________________________ ");
			System.out.println("| ______  MIS CARTAS DEL ARCHIVO CONFIDENCIAL _______ |");
			for(int i=0; i<= miControlador.getJugadorEnTurno().getCartasSecretas().cantCartas() -1; i++) {
				System.out.println("    "+ miControlador.getJugadorEnTurno().getCartasSecretas().getCarta(i).getFigura());
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
				try {
					this.miControlador.arriesgar();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
					break;
				
			}
	
		
	}


	private void menuSospecha() {
		System.out.println();
		System.out.println();
		System.out.println("-------- REALIZAR SOSPECHA --------------");
		System.out.println();
		System.out.println("");
		System.out.println("Elegí dos elementos para realizar tu sospecha:");
		System.out.println();
		int nOpcion = 0;
		for(int i=0; i<= miControlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1 ; i++) {
			if(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida()) {
				System.out.println(nOpcion+"- " + miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());	
				nOpcion++;
			}
		}
		Scanner entrada = new Scanner(System.in);
		System.out.println();
		ArrayList<String> sospecha = new ArrayList<String>();
		for(int i=0; i< 2 ; i++) {
			System.out.println("Selecciona un elemento para realizar tu sospecha");       
			int opcion = entrada.nextInt();
			sospecha.add(miControlador.getJugadorEnTurno().getAgendaPersonal().getCarta(opcion).getFigura());
		}
		miControlador.setSospecha(sospecha);
	}

}