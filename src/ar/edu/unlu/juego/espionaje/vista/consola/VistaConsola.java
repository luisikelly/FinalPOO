package ar.edu.unlu.juego.espionaje.vista.consola;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;

import java.io.Serializable;
import java.rmi.RemoteException;

public class VistaConsola implements IVista, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador controlador;
	private Menu menu;
	private String nombre = "";
	private Scanner entrada;
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
		menu = new MenuConfig(controlador, this);
	}
	
	@Override
	public void iniciarJuego() {
		this.mostrarConfiguracion();
		
	}
	
	// "Pantallas" del juego 
	
	@Override
	public void mostrarArriesgar() {
		entrada = new Scanner(System.in);
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
		System.out.println();
		controlador.rtaSospechaFinal(agentes, dispositivo, ciudades);
	}



	@Override
	public void avisoGanador() {
		entrada.nextLine();
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
			System.out.println(jugador.getNombre()+" Ingresó a la partida");
		}
		System.out.println();
		this.mostrarConfiguracion();
	}



	@Override
	public void mostrarConfiguracion() {
		entrada = new Scanner(System.in);
		if(!nombre.equals("")) {
			System.out.println("-----------------------------------------");
			System.out.println();
			System.out.println("1. Iniciar juego");
			System.out.println("2. Ayuda");
			System.out.println("3. Historial de Ganadores");
			System.out.println("0. Salir");
			System.out.println();
			System.out.println("Ingrese opcion");
			String opcion= entrada.nextLine();
			
			switch(opcion) {
			case "0": controlador.finalizarPartida();
					break;
			case "1": controlador.iniciarPartida();      
			  		break; 
			case "2": mostrarAyuda();
					  mostrarConfiguracion();				
					break;
			case "3": mostrarGanadores(); 
					mostrarConfiguracion();
			  		break;
			}

		}else {
			System.out.println("-----------------------------------------");
			System.out.println();
			System.out.println("1. Registrarse");
			System.out.println("2. Iniciar juego");
			System.out.println("3. Ayuda");
			System.out.println("4. Historial de Ganadores");
			System.out.println("0. Salir");
			System.out.println();
			System.out.println("Ingrese opcion");
			String opcion= entrada.nextLine();
			
			switch(opcion) {
			case "0": controlador.finalizarPartida();
					break;
			case "1": this.agregarJugador();      
	  				break; 		
			case "2": controlador.iniciarPartida();      
			  		break; 
			case "3": mostrarAyuda();
					  mostrarConfiguracion();				
					break;
			case "4": mostrarGanadores(); 
					mostrarConfiguracion();
			  		break;
			}

		}
			
	}

	@Override
	public void mostrarResponder() {
		entrada = new Scanner(System.in);
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
				if(!controlador.verificarRespuesta().isEmpty()) { 
					if(controlador.verificarRespuesta().size() == 1) {
						System.out.println("1- "+ controlador.verificarRespuesta().get(0));
					}else {
						System.out.println("1- "+controlador.verificarRespuesta().get(0));
						System.out.println("2- "+controlador.verificarRespuesta().get(1));
					}
					System.out.println(".............................................................");
					System.out.println(" ELEGÍ TU RESPUESTA   ");
					String resp = entrada.nextLine();
					System.out.println();
					switch(resp) {
					case "1" : controlador.setRespuesta(controlador.verificarRespuesta().get(0));
						break;
					case "2" : controlador.setRespuesta(controlador.verificarRespuesta().get(1));
						break;
					}
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
				}else {
					System.out.println(".............................................................");
					System.out.println("         NO TENES NINGUNA CARTA DE LA SOSPECHA");
					System.out.println(".............................................................");
					System.out.println("    ");
					controlador.setRespuesta("");
				}
		} catch (RemoteException e) {
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
		entrada = new Scanner(System.in);
		System.out.println("-----------------------------------------");
		System.out.println("--------        ESPIONAJE     -----------");
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println();
			System.out.println("¡Es tu turno!");
			System.out.println(" __________________________________ ");
			System.out.println("| ______   AGENDA PERSONAL _______ |");
			for(int i=0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
				if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida())
					System.out.println("    "+ controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura() + "    ");
			}
			System.out.println();
			System.out.println();
			System.out.println(" _____________________________________________________ ");
			System.out.println("| ______  MIS CARTAS DEL ARCHIVO CONFIDENCIAL _______ |");
			for(int i=0; i<= controlador.getJugadorEnTurno().getCartasSecretas().cantCartas() -1; i++) {
				System.out.println("    "+ controlador.getJugadorEnTurno().getCartasSecretas().getCarta(i).getFigura());
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
		
		System.out.println();		
		System.out.println();
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
	public void mostrarTurno(String string) {
		
		switch(string) {
		
		case "SOSPECHAR" : 
			System.out.println("-----------------------------------------");
			System.out.println("Ahora es el turno de " + controlador.getJugadorEnTurno().getNombre());
			System.out.println("-----------------------------------------");
			break;
		case "RESPONDER" :
			System.out.println("-----------------------------------------");
			System.out.println(controlador.getJugadorEnTurno().getNombre()+ "  envío sospecha a " + controlador.listaJugadores().get(controlador.getSospechado()).getNombre());
			System.out.println("-----------------------------------------");
			break;
		case "RESPONDER_JET":
			System.out.println("-----------------------------------------");
			System.out.println("Esperando respuesta de " + controlador.listaJugadores().get(controlador.getSospechado()).getNombre());			System.out.println("-----------------------------------------");
			break;
		case "ARRIESGAR":
			System.out.println("-----------------------------------------");
			System.out.println( controlador.getJugadorEnTurno().getNombre()+" está realizando su acusación ");
			System.out.println("-----------------------------------------");
			break;
		}
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
		
		nombre = entrada.next();
		if(!nombre.equals("")) {
			controlador.agregarJugador(nombre.toUpperCase());
		}
			
		mostrarConfiguracion();
			System.out.println();
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

	private ArrayList<String> elegirOpcion(int cantidadOpcionesAElegir, String s1, String s2) {
		entrada = new Scanner(System.in);
		int opcionElegida = 0;
		ArrayList<String> elegidas = new ArrayList<String>();
		for(int i=0; i< cantidadOpcionesAElegir ; i++) {
			System.out.println("Selecciona "+ s1 +" para realizar tu "+ s2);       
			int opcion = entrada.nextInt();
			while(opcion == opcionElegida) {
				System.out.println("¡La opcion " + opcion + " no esta disponible! ¡Inetnta nuevamente!");
			}
			opcionElegida = opcion;
			elegidas.add(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(opcionElegida - 1).getFigura());
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
		for(int i=0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1 ; i++) {
			int nOpcion = i+1;
			System.out.println("Ingrese la opcion de DISPOSITIVO");
			if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("DISPOSITIVO")) {
				System.out.println(nOpcion + controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				dispositivo = this.elegirOpcion(1,"un DISPOSITIVO","acusación").get(0);
			}
			nOpcion = 1;
			System.out.println("Ingrese la opcion de CIUDAD");
			if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("CIUDAD")) {
				System.out.println(nOpcion + controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());				
				ciudad = elegirOpcion(1," una CIUDAD","acusación").get(0);
			}
			nOpcion = 1;
			System.out.println("Ingrese la opcion de AGENTE");
			if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getTipo().equals("AGENTE")) {		
				System.out.println(nOpcion + controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
				agente = elegirOpcion(1," un AGENTE","acusación").get(0);
			}
		}
		ArrayList<String> acusacion = new ArrayList<String>();
		acusacion.add(dispositivo);
		acusacion.add(ciudad);
		acusacion.add(agente);
		
 		controlador.setSospecha(acusacion);
	}



	private void menuSospecha() {
		System.out.println();
		System.out.println();
		System.out.println("-------- REALIZAR SOSPECHA --------------");
		System.out.println();
		System.out.println("");
		System.out.println("Elegí dos elementos para realizar tu sospecha:");
		System.out.println();
		int nOpcion = 1;
		for(int i=0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1 ; i++) {
			if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida()) {
				System.out.println(nOpcion+"- " + controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());	
				nOpcion++;
			}
		}
		System.out.println();
		ArrayList<String> sospecha = new ArrayList<String>();
		sospecha = elegirOpcion(2, "un elemento", "sospecha");
		controlador.setSospecha(sospecha);
	}


}
