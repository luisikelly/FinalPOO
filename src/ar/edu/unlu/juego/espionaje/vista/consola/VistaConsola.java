package ar.edu.unlu.juego.espionaje.vista.consola;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;

import java.io.Serializable;
import java.rmi.RemoteException;

public class VistaConsola implements IVista, Serializable {

	private Controlador controlador;
	private Menu menu;
	private Scanner entrada = new Scanner(System.in);
	
	public VistaConsola() {
	
	}
	
	@Override
	public void setControlador(Controlador c) {
		this.controlador = c;
		menu = new MenuConfig(controlador, this);
		this.iniciarJuego();
	}
	
	@Override
	public void iniciarJuego() {
		try{
			this.mostrarConfiguracion();
		} catch(IndexOutOfBoundsException e) {
			this.mostrarError("-jugadores");
			menu.mostrarMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// "Pantallas" del juego 
	
	@Override
	public void mostrarArriesgar() {
	System.out.println("~ SOSPECHA FINAL ~");
		
		System.out.println("Ingrese la opcion de DISPOSITIVO");
		System.out.println("1- SATELITE");
		System.out.println("2- AUTOPROPULSOR");
		System.out.println("3- GAS LETAL");
		System.out.println("4- AVION");
		System.out.println("5- HELICOPTERO");
		String disp = entrada.nextLine();
		String dispositivo = null;
		String ciudades = null;
		String agentes = null;
		
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
		
		System.out.println();
		System.out.println(dispositivo);
		System.out.println();
		
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
		}
		
		System.out.println();
		System.out.println(ciudades);
		System.out.println();
		
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
		}
		
		System.out.println();
		System.out.println(agentes);
		System.out.println();
		
		controlador.rtaSospechaFinal(agentes, dispositivo, ciudades);
	}



	@Override
	public void avisoGanador() {
		menu = new MenuGanador(controlador);
		menu.mostrarMenu();
	}

	@Override
	public void mostraJugadores() {
		System.out.println("--------------- Jugadores --------------");
		System.out.println();
		ArrayList<IJugador> j = controlador.listaJugadores();
		for (IJugador jugador : j) {
			System.out.println("Jugador: " +  jugador.getNombre());
		}
		System.out.println();
	}



	@Override
	public void mostrarConfiguracion() {
		menu = new MenuConfig(controlador, this);
		menu.mostrarMenu();
		
	}

	@Override
	public void mostrarResponder() {
		System.out.println();
		System.out.println("Responder Sospecha de: "+ controlador.getJugadorEnTurno().getNombre());
		System.out.println();
		System.out.println("SOSPECHA: ");
		try {
			System.out.println(controlador.getSospecha().get(0));
			System.out.println(controlador.getSospecha().get(1));
			System.out.println();
			System.out.println(".............................................................");
			System.out.println();
			System.out.println("LAS CARTAS DE ARCHIVO CONFIDENCIAL QUE TENES SON: ");
			System.out.println();
			int j=1;
			int jug = controlador.getSospechado();
			boolean esta = false;
			for(int i=0; i<= controlador.listaJugadores().get(jug).getCartasSecretas().cantCartas() -1; i++) {
				if((controlador.listaJugadores().get(jug).getCartasSecretas().getCarta(i).getFigura().equals(controlador.getSospecha().get(0))) || (controlador.listaJugadores().get(jug).getCartasSecretas().getCarta(i).getFigura().equals(controlador.getSospecha().get(1)))) {
					System.out.println(j + ". " + controlador.listaJugadores().get(jug).getCartasSecretas().getCarta(i).getFigura());
					esta = true;
					j++;
				}	
				if(esta) {
					System.out.println(".............................................................");
					System.out.println("             ELEGÍ UN ELEMENTO TU RESPUESTA   ");
					String resp = entrada.nextLine();
					controlador.setRespuesta(resp);
				}else {
					System.out.println(".............................................................");
					System.out.println("         NO TENES NINGUNA CARTA DE LA SOSPECHA");
					
				}
			}
			System.out.println(".............................................................");
			System.out.println("    ");
		
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}



		

	@Override
	public void avisoPerdio() {
		if(controlador.getGanador().getNroJugador() == controlador.getNroJugador()) {
			System.out.println("------ " + controlador.getJugadorEnTurno().getNombre() + " ------"  );
			System.out.println("                          ------ PERDISTE ------");
			System.out.println("               ------ TU SOSPECHA FINAL FUE INCORRECTA ------");
			System.out.println("");
			System.out.println("LA INFORMACION CONFIDENCIAL ES:");
			System.out.println();
			System.out.println("CIUDAD: " + controlador.informacionConfidencial()[0].getFigura());
			System.out.println();
			System.out.println("AGENTE: " + controlador.informacionConfidencial()[1].getFigura());
			System.out.println();
			System.out.println("DISPOSITIVO: " + controlador.informacionConfidencial()[2].getFigura());
			System.out.println();
		}
	}

	@Override
	public void mostrarError(String tError) {
		switch(tError) {
		case "-jugadores":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("La cantidad de jugadores registrados debe ser mayor a 2");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			break;
		case "+jugadores":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("HA SUPERADO LA CANTIDAD DE JUGADORES!");
			System.out.println("La cantidad maxima de jugadores es 4");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();

			break;
		case "error":
			break;	
		}
		
	}

	@Override
	public void mostrarSospechar() {
		menu = new MenuJuego(controlador);
		menu.mostrarMenu();
	}

	@Override
	public void mostrarRespuesta(String string) {
		System.out.println(controlador.listaJugadores().get(controlador.getSospechado()).getNombre()+ " responde:");
		System.out.println();
			try {
				if(!controlador.getRespuesta().equals("")) {
					System.out.println(controlador.getRespuesta().toUpperCase());
				}else {
					System.out.println(controlador.listaJugadores().get(controlador.getSospechado()) + " NO TIENE NINGUNA DE LAS CARTAS DE LA SOSPECHA");
				}
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	@Override
	public void mostrarTurno(String string) {
		System.out.println("-----------------------------------------");
		System.out.println("Ahora es el turno de " + controlador.getJugadorEnTurno().getNombre());
		System.out.println("-----------------------------------------");
		
	}

	@Override
	public void quienGano() {
		System.out.println("------ " + controlador.getJugadorEnTurno().getNombre() + " ------"  );
		System.out.println("                          ------ PERDISTE ------");
		System.out.println(controlador.getGanador().getNombre()+ "  GANÓ LA PARTIDA");

	}

	protected void agregarJugador() {
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("Ingresa tu nombre:");
		
		String nombre = entrada.next();
		if(!nombre.isEmpty() && !nombre.equals("")) {
			controlador.agregarJugador(nombre);
		}
			
		
			System.out.println();
	}

	

}
