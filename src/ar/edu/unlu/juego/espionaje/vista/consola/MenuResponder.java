package ar.edu.unlu.juego.espionaje.vista.consola;

import java.rmi.RemoteException;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuResponder extends Menu {
	Scanner entrada;
	public MenuResponder(Controlador c) {
		this.miControlador = c;
	}
	
@Override
public void mostrarMenu() {
	System.out.println();
	System.out.println("Responder Sospecha de: "+ miControlador.getJugadorEnTurno().getNombre());
	System.out.println();
	System.out.println("SOSPECHA: ");
	try {
			//System.out.println(miControlador.getSospecha().get(0));
			//System.out.println(miControlador.getSospecha().get(1));
		System.out.println();
		System.out.println(".............................................................");
		System.out.println();
		System.out.println("LAS CARTAS DE ARCHIVO CONFIDENCIAL QUE TENES SON: ");
		System.out.println();
			
		if(!miControlador.verificarRespuesta().isEmpty()) { 
				System.out.println("1- "+ miControlador.verificarRespuesta().get(0));
				System.out.println("2- "+ miControlador.verificarRespuesta().get(1));
				System.out.println();
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

}
	
	private void elegirRespuesta() {	
		entrada= new Scanner(System.in);
		String resp;
		System.out.println(".............................................................");
		System.out.println(" ELEGÍ TU RESPUESTA   ");
		resp = entrada.next(); 		
		System.out.println();
		try {
			switch (resp) {
				case "1" : miControlador.setRespuesta(miControlador.verificarRespuesta().get(0));
					break;
				case "2" : miControlador.setRespuesta(miControlador.verificarRespuesta().get(1));
					break;
				default: 
					System.out.println("Error! Ingrear nuevamnte la acción a realizar");
					this.elegirRespuesta();
					break;
			}
		} catch (RemoteException e) {
			e.printStackTrace();
	   }	
	}	
}
