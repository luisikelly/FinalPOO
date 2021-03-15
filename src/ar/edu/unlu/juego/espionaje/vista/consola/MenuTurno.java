package ar.edu.unlu.juego.espionaje.vista.consola;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public class MenuTurno extends Menu{

	private String opcion;
	
	MenuTurno(Controlador controlador,String s){
		this.opcion = s;
		this.miControlador = controlador;
	}
	
	@Override
	public void mostrarMenu() {
		switch(opcion) {
		
		case "SOSPECHAR" : 
			System.out.println("-----------------------------------------");
			System.out.println("Ahora es el turno de " + miControlador.getJugadorEnTurno().getNombre());
			System.out.println("-----------------------------------------");
			break;
		case "RESPONDER" :
			System.out.println("-----------------------------------------");
			System.out.println(miControlador.getJugadorEnTurno().getNombre()+ "  envío sospecha a " + miControlador.listaJugadores().get(miControlador.getSospechado()).getNombre());
			System.out.println("-----------------------------------------");
			break;
		case "RESPONDER_JET":
			System.out.println("-----------------------------------------");
			System.out.println("Esperando respuesta de " + miControlador.listaJugadores().get(miControlador.getSospechado()).getNombre());			
			System.out.println("-----------------------------------------");
			break;
		case "ARRIESGAR":
			System.out.println("-----------------------------------------");
			System.out.println( miControlador.getJugadorEnTurno().getNombre()+" está realizando su acusación ");
			System.out.println("-----------------------------------------");
			break;
		}
	}
	
	

}
