
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
		
	}
	
	protected void mostrarTituloEspionaje() {
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
		menu = new MenuConfig(controlador, this,false);
		menu.mostrarMenu();
	}

	

	@Override
	public void mostrarArriesgar() {
		Scanner entrada = new Scanner(System.in);
		this.mostrarTituloEspionaje();
		int jug = controlador.getNroJugador();
		if(jug == controlador.getJugadorEnTurno().getNroJugador()) {
			System.out.println();
			System.out.println();
			System.out.println("-------- REALIZAR ACUSACIÓN --------------");
			System.out.println();
			System.out.println("");
			System.out.println("Elegí TRES elementos para realizar tu acusación:");
			System.out.println();
			System.out.println("................PRESIONE ENTER PARA CONTINUAR................ ");
			entrada.nextLine();
			
			System.out.println("~ SOSPECHA FINAL ~");
			String dispositivo = this.mostrarDispositivos();
				System.out.println();
				System.out.println(dispositivo);
				System.out.println();

				String ciudades = this.mostrarCiudades();
				System.out.println();
				System.out.println(ciudades);
				System.out.println();
		
				String agentes = this.mostrarAgentes();		
				System.out.println();
				System.out.println(agentes);
				System.out.println();
				
				controlador.rtaSospechaFinal(agentes, dispositivo, ciudades);

		}else {
				System.out.println("-----------------------------------------");
				System.out.println( controlador.getJugadorEnTurno().getNombre()+" está realizando su acusación ");
				System.out.println("-----------------------------------------");	
		}
	}
	
	
	private String mostrarDispositivos() {
		String dispositivo = null;
		System.out.println("Ingrese la opcion de DISPOSITIVO");
		System.out.println("1- SATELITE");
		System.out.println("2- AUTOPROPULSOR");
		System.out.println("3- GAS LETAL");
		System.out.println("4- AVION");
		System.out.println("5- HELICOPTERO");
		Scanner eDispositivo = new Scanner(System.in);	
		String disp = eDispositivo.nextLine();
		if(!disp.equals("1") && !disp.equals("2") && !disp.equals("3") && !disp.equals("4") && !disp.equals("5")) {
			this.mostrarDispositivos();
		}else {
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

		}

		return dispositivo;		
	}

	private String mostrarCiudades() {
		String ciudades = "";
		System.out.println("Ingrese la opcion de CIUDAD");
		System.out.println("1- PARIS");
		System.out.println("2- LONDRES");
		System.out.println("3- TOKIO");
		System.out.println("4- PANAMA");
		System.out.println("5- ATENAS");
		Scanner eCiudad = new Scanner(System.in);	
		String ciudad = eCiudad.nextLine();
		if(!ciudad.equals("1") && !ciudad.equals("2") && !ciudad.equals("3") && !ciudad.equals("4") && !ciudad.equals("5")) {
			this.mostrarCiudades();
		}else {
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

		}
		return ciudades;
	}
	
	private String mostrarAgentes() {
		String agentes = "";
		System.out.println("Ingrese la opcion de AGENTE");
		System.out.println("1- AGENTE ROJO");
		System.out.println("2- AGENTE VERDE");
		System.out.println("3- AGENTE AZUL");
		System.out.println("4- AGENTE NARANJA");
		System.out.println("5- AGENTE BLANCO");
		Scanner eAgente = new Scanner(System.in);	
		String agente = eAgente.nextLine();
		if(!agente.equals("1") && !agente.equals("2") && !agente.equals("3") && !agente.equals("4") && !agente.equals("5")) {
			this.mostrarAgentes();
		}else {
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
		}
		return agentes;
	}

	
	@Override
	public void avisoGanador() {
//	menu = new MenuGanador(controlador);
//		menu.mostrarMenu();
		this.mostrarTituloEspionaje();		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("--------       FIN DEL JUEGO     -----------");
		System.out.println();
		System.out.println();
		System.out.println(controlador.getGanador().getNombre() + " GANÓ EL JUEGO");
		System.out.println();
		System.out.println();
		System.out.println("||||||||||||||||||||||||||||||");
		System.out.println("   INFORMACION CONFIDENCIAL ");
		System.out.println("||||||||||||||||||||||||||||||");
		System.out.println();
		System.out.println();
		System.out.println("CIUDAD: " + controlador.informacionConfidencial()[0].getFigura());
		System.out.println();
		System.out.println("AGENTE: " +controlador.informacionConfidencial()[1].getFigura());
		System.out.println();
		System.out.println("DISPOSITIVO: " + controlador.informacionConfidencial()[2].getFigura());
		System.out.println();
		System.out.println();
		
	}


	@Override
	public void mostraJugadores() {
		// No se utiliza en esta vista
	}
	
	
	@Override
	public void mostrarConfiguracion() {
		menu = new MenuConfig(controlador, this,true);
		menu.mostrarMenu();
	}
	
	


	@Override
	public void mostrarResponder() {
		int nJug = controlador.getNroJugador();
		if(nJug == controlador.getSospechado()) {
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
					
				if(!controlador.verificarRespuesta().isEmpty()) { 
					if(controlador.verificarRespuesta().size() == 2) {
						System.out.println("1- "+ controlador.verificarRespuesta().get(0));
						System.out.println("2- "+ controlador.verificarRespuesta().get(1));
						System.out.println();
					
					}else {
						System.out.println("1- "+ controlador.verificarRespuesta().get(0));
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
					controlador.setRespuesta("");
					
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			controlador.paso();

		}else {
			if(nJug == controlador.getJugadorEnTurno().getNroJugador()) {
				System.out.println("-----------------------------------------");
				System.out.println("Esperando respuesta de " + controlador.listaJugadores().get(controlador.getSospechado()).getNombre());			
				System.out.println("-----------------------------------------");
			}else {
				System.out.println("-----------------------------------------");
				System.out.println(controlador.getJugadorEnTurno().getNombre()+ "  envío sospecha a " + controlador.listaJugadores().get(controlador.getSospechado()).getNombre());
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
				case "1" : controlador.setRespuesta(controlador.verificarRespuesta().get(0));
					break;
				case "2" : controlador.setRespuesta(controlador.verificarRespuesta().get(1));
					break;
				}
				
		} catch (RemoteException e) {
			e.printStackTrace();
	   }
		}
		
	}	
	

	@Override
	public void mostrarRespuesta(String string) {
		if(controlador.getNroJugador() == controlador.getJugadorEnTurno().getNroJugador()) {
			System.out.println(".............................................................");
			System.out.println(controlador.listaJugadores().get(controlador.getSospechado()).getNombre()+ " responde:");
			System.out.println();
				try {
					if(!controlador.getRespuesta().equals("")) {
						System.out.println(controlador.getRespuesta().toUpperCase());
					}else {
						System.out.println(controlador.listaJugadores().get(controlador.getSospechado()) + " NO TIENE NINGUNA DE LAS CARTAS DE LA SOSPECHA");
					}
					System.out.println(".............................................................");
					controlador.paso();
//					this.mostrarTurno("SOSPECHAR");
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
				} catch (RemoteException e) {
					e.printStackTrace();
				}	

		}else {
			System.out.println("-----------------------------------------");
			System.out.println( controlador.listaJugadores().get(controlador.getSospechado()).getNombre()+" está respondiendo la sospecha de "+controlador.getJugadorEnTurno().getNombre());
			System.out.println("-----------------------------------------");
			}	
	}


	@Override
	public void avisoPerdio() {
			System.out.println("------ " + controlador.listaJugadores().get(controlador.getNroJugador()).getNombre() + " ------"  );
			System.out.println("                       ------ PERDISTE ------");
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
			System.out.println("---------------------------------------------------------------------");
			System.out.println("---------------------------------------------------------------------");
			System.out.println("---------------------     FUERA DE JUEGO          -------------------");
			System.out.println("---------------------------------------------------------------------");
			System.out.println("---------------------------------------------------------------------");

			
			
			//this.menuFinPartida();
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
			this.agregarJugador();
			break;
		}
	}


	@Override
	public void mostrarSospechar() {
		menu = new MenuSospecha(controlador, this);
		menu.mostrarMenu();
		
		/*int nJug = controlador.getNroJugador();
		if(nJug == controlador.getJugadorEnTurno().getNroJugador()) {
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
						this.mostrarSospechar();
						break;
					case "2" : 
						this.mostrarAP();
						this.mostrarSospechar();
						break;
					case "3" : this.mostrarMenuSospecha();
					case "4":
						try {this.controlador.arriesgar();	} 
						catch (RemoteException e1) { e1.printStackTrace();} 
						break;
				}

		}else {
			System.out.println("-----------------------------------------");
			System.out.println("Ahora es el turno de " + controlador.getJugadorEnTurno().getNombre());
			System.out.println("-----------------------------------------");
		} */
	}
	
	private void mostrarAC() {
		System.out.println(" _____________________________________________________ ");
		System.out.println("| ______  MIS CARTAS DEL ARCHIVO CONFIDENCIAL _______ |");
		for(int i=0; i<= controlador.getJugadorEnTurno().getCartasSecretas().cantCartas() -1; i++) {
			System.out.println("    "+ controlador.getJugadorEnTurno().getCartasSecretas().getCarta(i).getFigura());
		}
		System.out.println();
		System.out.println();
	}


	private void mostrarAP() {
		System.out.println(" __________________________________ ");
		System.out.println("| ______   AGENDA PERSONAL _______ |");
		for(int i=0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
			if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida())
				System.out.println("    "+ controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura() + "    ");
		}
		System.out.println();
		System.out.println();
	}


	private void mostrarMenuSospecha() {
		if(controlador.getNroJugador() == controlador.getJugadorEnTurno().getNroJugador()) {
			System.out.println();
			System.out.println();
			System.out.println("-------- REALIZAR SOSPECHA --------------");
			System.out.println();
			System.out.println("");
			System.out.println("Elegí dos elementos para realizar tu sospecha:");
			System.out.println();
			int nOpcion = 0;
			ArrayList<String> disponibles = new ArrayList<String>();
			for(int i=0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas() -1 ; i++) {
				if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida()) {
					System.out.println(nOpcion+"- " + controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());	
					disponibles.add(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura());
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
		System.out.println( " Enviaste tu sospecha a " + controlador.listaJugadores().get(controlador.getSospechado()).getNombre());
		System.out.println("-----------------------------------------");
		
		controlador.setSospecha(sospecha);
			
		}else {
			System.out.println("-----------------------------------------");
			System.out.println("Ahora es el turno de " + controlador.getJugadorEnTurno().getNombre());
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


	@Override
	public void mostrarTurno(String string) {
	//	menu = new MenuTurno(controlador,string);
	//	menu.mostrarMenu();
	}


	@Override
	public void quienGano() {
		System.out.println(controlador.getGanador().getNombre()+ "  GANÓ LA PARTIDA");
	}

	@Override
	public void mostrarGanadores() {
		try {
			if(!controlador.getGanadores().isEmpty()) {
				System.out.println();
				System.out.println(" ____________________________");
				System.out.println("| ______   GANADORES _______ |");
				for (String ganador : controlador.getGanadores()) {
					System.out.println("   " +ganador);
				}
			} else {
				System.out.println();
				System.out.println(" ______________________________________________");
				System.out.println("| ______  NO HAY GANADORES REGISTRADOS _______ |");
				
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

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
	
	public void listarJugadores() {
		if(!controlador.listaJugadores().isEmpty()) {
			System.out.println();
			System.out.println("--------- JUGADORES ---------");
			System.out.println();
			ArrayList<IJugador> j = controlador.listaJugadores();
			System.out.println("Jugadores");
			for (IJugador jugador : j) {
				System.out.println(jugador.getNombre());
			}
			System.out.println("-----------------------------");
		} else {
			System.out.println();
			System.out.println("------------------------------");
			System.out.println(" NO HAY JUGADORES REGISTRADOS           ");
			System.out.println("-------------------------------");
		}
		
	}
	
	public void mostrarMenuFinPartida() {
		System.out.println("¿Qué queres hacer? ");
		System.out.println("1- Volver al menú principal");
		System.out.println("2 - Salir ");
		Scanner miScanner = new Scanner(System.in);
		String opcion = miScanner.nextLine();
		switch (opcion) {
		case "1" :controlador.reiniciar();
				break;
		case "2" : controlador.finalizarPartida(); 
					break;
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
				"\r\n" + "Trabajo Final de la asignatura Programación Orientada a Objetos - Universidad Nacional de Luján-\r\n" + 
				"Juego Espionaje de la Marca YETEM - El uso del mismo es unicamnete para fines academicos.  \r\n" + " ");
	}

	@Override
	public void notificarSalio() {
		//System.out.println("______ "+ controlador.getSalio().getNombre() + " SALIÓ DE LA PARTIDA _____");
		
	}

	@Override
	public void inicio() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("................PRESIONE ENTER PARA COMENZAR................ ");
		entrada.nextLine();
		controlador.iniciar();
		
	}

}

