package ar.edu.unlu.juego.espionaje.vista.consola;

import java.rmi.RemoteException;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuResponder extends Menu {
	Scanner entrada;
	private int nJug, jugTurno, sospechado;
	

	public MenuResponder(Controlador c, VistaConsola vista) {
		this.miControlador = c;
		this.miVista = vista;
		nJug = miControlador.getNroJugador();
		jugTurno = miControlador.getJugadorEnTurno().getNroJugador();
		sospechado = miControlador.getSospechado();
	}
	
@Override
public void mostrarMenu() {
	if(nJug == sospechado) {
		System.out.println();
		System.out.println("Responder Sospecha de: "+ miControlador.getJugadorEnTurno().getNombre());
		System.out.println();
		System.out.println("SOSPECHA: ");
		try {
			System.out.println(miControlador.getSospecha().get(0));
			System.out.println(miControlador.getSospecha().get(1));
			System.out.println();
			System.out.println(".............................................................");
			System.out.println();
			System.out.println("LAS CARTAS DE ARCHIVO CONFIDENCIAL QUE TENES SON: ");
			System.out.println();
				
			if(!miControlador.verificarRespuesta().isEmpty()) { 
				if(miControlador.verificarRespuesta().size() == 2) {
					System.out.println("1- "+ miControlador.verificarRespuesta().get(0));
					System.out.println("2- "+ miControlador.verificarRespuesta().get(1));
					System.out.println();
				
				}else {
					System.out.println("1- "+ miControlador.verificarRespuesta().get(0));
					System.out.println();
				
				}	
				this.elegirRespuesta();
				
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			}else {
				System.out.println(".............................................................");
				System.out.println("         NO TENES NINGUNA CARTA DE LA SOSPECHA");
				System.out.println(".............................................................");
				System.out.println("    ");
				miControlador.setRespuesta("");
				
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		miControlador.paso();

	}else {
		if(nJug == miControlador.getJugadorEnTurno().getNroJugador()) {
			System.out.println("-----------------------------------------");
			System.out.println("Esperando respuesta de " + miControlador.listaJugadores().get(miControlador.getSospechado()).getNombre());			
			System.out.println("-----------------------------------------");
		}else {
			System.out.println("-----------------------------------------");
			System.out.println(miControlador.getJugadorEnTurno().getNombre()+ "  envío sospecha a " + miControlador.listaJugadores().get(miControlador.getSospechado()).getNombre());
			System.out.println("-----------------------------------------");
			
		}
	}

	
	
}

private void elegirRespuesta() {	
	System.out.println(".............................................................");
	System.out.println(" ELEGÍ TU RESPUESTA   ");
	Scanner entrada= new Scanner(System.in);
	String resp = entrada.nextLine();		
	System.out.println();
	
	if(!resp.equals("1") && !resp.equals("2") ) {
		this.elegirRespuesta();
	}else {
		try {
			switch (resp) {
			case "1" : miControlador.setRespuesta(miControlador.verificarRespuesta().get(0));
				break;
			case "2" : miControlador.setRespuesta(miControlador.verificarRespuesta().get(1));
				break;
			}
			
	} catch (RemoteException e) {
		e.printStackTrace();
   }
	}
}
	
}	