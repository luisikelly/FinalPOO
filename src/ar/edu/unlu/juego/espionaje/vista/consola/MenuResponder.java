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
			System.out.println(miControlador.getSospecha().get(0));
			System.out.println(miControlador.getSospecha().get(1));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println();
		System.out.println(".............................................................");
		System.out.println();
		System.out.println("LAS CARTAS DE ARCHIVO CONFIDENCIAL QUE TENES SON: ");
		System.out.println();
		int j=1;
		int jug = miControlador.getSospechado();
		boolean esta = false;
		//entrada.nextLine();
			try {
				if(!miControlador.verificarRespuesta().isEmpty()) { 
					if(miControlador.verificarRespuesta().size() == 1) {
						System.out.println("1- "+ miControlador.verificarRespuesta().get(0));
						System.out.println();
						System.out.println("---- Enviando respuesta: "+ miControlador.verificarRespuesta().get(0)+"----");
						miControlador.setRespuesta(miControlador.verificarRespuesta().get(0));
					}else {
						System.out.println("1- "+miControlador.verificarRespuesta().get(0));
						System.out.println("2- "+miControlador.verificarRespuesta().get(1));
						this.elegirRespuesta();
						System.out.println();
						System.out.println();
						System.out.println();
						System.out.println();

					}
				}else {
					System.out.println(".............................................................");
					System.out.println("         NO TENES NINGUNA CARTA DE LA SOSPECHA");
					System.out.println(".............................................................");
					System.out.println("    ");
					try {
						miControlador.setRespuesta("");
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	private void elegirRespuesta() {	
		entrada= new Scanner(System.in);
		String resp="1";
		System.out.println("................PRESIONE ENTER PARA CONTINUAR................ ");
		entrada.nextLine();
		System.out.println(".............................................................");
		System.out.println(" ELEGÍ TU RESPUESTA   ");
		resp = entrada.nextLine(); 
		
		System.out.println();
		try {
			if(miControlador.verificarRespuesta().size() == 2) {
				switch (resp) {
				case "1" : 
						miControlador.setRespuesta(miControlador.verificarRespuesta().get(0));
					break;
				case "2" : miControlador.setRespuesta(miControlador.verificarRespuesta().get(1));
					break;
				default: 
					System.out.println("Error! Ingrear nuevamnte la acción a realizar");
					this.elegirRespuesta();
					break;
					}
				} else {
				switch (resp) {
				case "1" : miControlador.setRespuesta(miControlador.verificarRespuesta().get(0));
					break;
				default: 
					System.out.println("Error! Ingrear nuevamnte la acción a realizar");
					this.elegirRespuesta();
					break;
					}
				}
		} catch (RemoteException e) {
			e.printStackTrace();
	   }	
	}	
}
