package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;

import java.io.Serializable;
import java.rmi.RemoteException;

public class VistaConsola implements IVista{
	private Controlador controlador;
	private Menu menu;
	private String nombre = "";
	private final String ErrorCantidadMinimaJugadores = "MenosJugadores";
	private final String ErrorCantidadMaximaJugadores = "MasJugadores";
	
	public VistaConsola() {
		System.out.println("-----------------------------------------");
		System.out.println("--------        ESPIONAJE     -----------");
		System.out.println("-----------------------------------------");
		System.out.println();
	}
	
	
	@Override
	public void setControlador(Controlador c) {
		this.controlador = c;
	}
	
	@Override
	public void iniciarJuego() {
		this.mostrarConfiguracion();
		
	}


	@Override
	public void mostrarArriesgar() {
		menu = new MenuAcusacion(controlador);
		menu.mostrarMenu();
	}


	@Override
	public void avisoGanador() {
		menu = new MenuGanador(controlador);
		menu.mostrarMenu();
	}


	@Override
	public void mostraJugadores() {
		System.out.println();
		System.out.println("--------------- ¡Se agregó un jugador! --------------");
		System.out.println();
		ArrayList<IJugador> j = controlador.listaJugadores();
		System.out.println("Jugadores");
		for (IJugador jugador : j) {
			System.out.println(jugador.getNombre());
		}
		System.out.println();
		menu.mostrarMenu();
	}


	@Override
	public void mostrarConfiguracion() {
		menu = new MenuConfig(controlador, this);
		menu.mostrarMenu();
	}


	@Override
	public void mostrarResponder() {
		menu = new MenuResponder(controlador);
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
				controlador.paso();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
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
		case "MenosJugadores":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("La cantidad de jugadores registrados debe ser mayor a 2");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			this.mostrarConfiguracion();
			break;
		case "MasJugadores":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("HA SUPERADO LA CANTIDAD DE JUGADORES!");
			System.out.println("La cantidad maxima de jugadores es 4");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			this.mostrarConfiguracion();
			break;
		case "NombreRepetido":
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("Ya existe un jugador registrado con ese nombre");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			this.mostrarConfiguracion();
			break;
		}
	}


	@Override
	public void mostrarSospechar() {
		menu = new MenuSospecha(controlador,this);
		menu.mostrarMenu();
	}


	@Override
	public void mostrarTurno(String string) {
		menu = new MenuTurno(controlador,string);
		menu.mostrarMenu();
	}


	@Override
	public void quienGano() {
		System.out.println("------ " + controlador.getJugadorEnTurno().getNombre() + " ------"  );
		System.out.println("                          ------ PERDISTE ------");
		System.out.println(controlador.getGanador().getNombre()+ "  GANÓ LA PARTIDA");
	}


	@Override
	public void mostrarGanadores() {
		try {
			System.out.println();
			System.out.println(" ____________________________");
			System.out.println("| ______   GANADORES _______ |");
			for (String ganador : controlador.getGanadores()) {
				System.out.println("   " +ganador);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void mostrarAyuda() {
		System.out.println("                               |  REGLAS DE JUEGO  |\r\n" + 
				"\r\n" +"2 a 4 JUGADORES +10 AÑOS\r\n" + "____________________________________________________________________\r\n" + 
				"\r\n" + "OBJETIVO\r\n" + 
				"\r\n" + "Descubrir las tres cartas de INFORMACIÓN SECRETA: Agente, Ciudad y Dispositivo.\r\n" + 
				"____________________________________________________________________\r\n" + 
				"\r\n" + "~ PREPARATIVOS ~\r\n" + 
				"\r\n" + "Cada jugador tendrá un mazo de AGENDA PERSONAL cada mazo tiene 15 cartas de color negro.\r\n" + 
				"\r\n" + "El juego toma 3 cartas del ARCHIVO CONFIDENCIAL de color amarillo, que tiene 15 cartas y se tendrán como INFORMACIÓN SECRETA. Las 12 cartas restantes serán repartidas entre los jugadores.\r\n" + 
				"____________________________________________________________________\r\n" + 
				"\r\n" + "¡A JUGAR!\r\n" + 
				"\r\n" + "~ Descartaremos las cartas del ARCHIVO PERSONAL de cada jugador que coincidan con las cartas amarillas del ARCHIVO CONFIDENCIAL que le tocó a cada uno de los jugadores.\r\n" + 
				"\r\n" + "~ Un jugador comienza a jugar realizando una sospecha al jugador de su izquierda. Debe seleccionar el jugador e incluir dos elementos de las variables de ciudad, agente y dispositivo. Pueden incluir dos elementos iguales, por ejemplo dos ciudades, dos dispositivos o dos agentes.\r\n" + 
				"\r\n" + "~Una vez realizada la sospecha el jugador situado a la izquierda examina sus carta de archivo confidencial, si tiene una o las dos debe -obligatoriamente- mostrar una sola de ellas al jugador que formulo la sospecha, sin que los demás lo vean. \r\n" + 
				"¡el jugador que responde no puede mentir!\r\n" + 
				"\r\n" + "Entonces, el jugador que hizo la sospecha descartará dicha carta de su agenda personal. De esta forma podrá deducir que cartas \"no están\" en la pila de información secreta.\r\n" + 
				"\r\n" + "~ Si el jugador sospechado no tiene ninguna de esas cartas dice envia el mensaje \"PASO\" y la misma sospecha pasa al proximo jugador de la izquierda ** y así continua hasta que se haya mostrado alguna carta de archivo confidencial o hasta que haya \"pasado\" por todos los jugadores.\r\n" + 
				"\r\n" + "Finaliza el turno del jugador y comienza el turno del siguiente jugador quien realiza una sospecha.\r\n" + 
				"\r\n" + "--------------------------------------------------------------------\r\n" + 
				"El juego continua de esta manera hasta que un  jugador cree saber cuales son las cartas secretas. Entonces puede realizar una acusación para ganar el juego.                    		\r\n" + 
				"--------------------------------------------------------------------\r\n" + 
				"\r\n" + "~ REALIZAR UNA ACUSACIÓN ~\r\n" + 
				"\r\n" + "~ Si un jugador cree saber cuales son las 3 cartas de información secreta, durante su turno puede hacer una acusación (arriesgar cuales son las 3 cartas de cartas secretas).\r\n" + 
				"\r\n" + "En este caso el jugador selecciona que desea realizar la acusación y el juego se detiene.  \r\n" + 
				"Si el jugador arriesga para intentar ganar el juego en ese turno ya no podrá preguntar emitiendo una sospecha. Entonces:\r\n" + 
				"\r\n" + "	- Todos los jugadores seleccionan las tres cartas de sus agendas personales que creen 	que son las cartas ocultas.\r\n" + 
				"	- El jugador que realizó la acusación podrá ver cuales son las cartas secretas.\r\n" + 
				"		\r\n" + 
				"A: Si la acusación es correcta el jugador gana el juego!\r\n" + 
				"B: Si la acusación es incorrecta (las tres cartas no coinciden), el siguiente jugador podrá ver si sus tres cartas coinciden con la información confidencial, en ese caso será el ganador, sino el siguiente jugador tendrá oportunidad y así hasta que cada jugador pueda arriesgar su acusación. \r\n" + 
				"____________________________________________________________________\r\n" + 
				"\r\n" + "Trabajo Final de la asignatura Programaciòn Orientada a Objetos - Universidad Nacional de Lujàn-\r\n" + 
				"Juego Espionaje de la Marca YETEM - EL uso del mismo es unicamnete pata fines academicos.  \r\n" + " ");
	}
	
	public void agregarJugador() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("Ingresa tu nombre:");
		nombre = sc.nextLine();
			controlador.agregarJugador(nombre.toUpperCase());
			System.out.println();
	}
	
	}
