package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;
import ar.edu.unlu.juego.espionaje.modelo.Jugador;
import ar.edu.unlu.juego.espionaje.modelo.Carta;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;
import ar.edu.unlu.juego.espionaje.modelo.Juego;;

public class VConsola implements IVista {
	private Menu menu;
	private Controlador controlador;
	private int jug =0;
	
 public VConsola() {
		
	}
	
	public void setControlador(Controlador c) {
		this.controlador = c;
		menu = new Configuracion(controlador, this);

	}
	
	public void mostrarJugando() {
		menu = new Juega(controlador,this);
		menu.mostrarMenu();
	}
	
	protected boolean tiene;
	
	@Override
	public boolean mostrarElegirRespuesta() {

		System.out.println();
		System.out.println("Responder Sospecha de: "+ controlador.getJugadorEnTurno().getNombre());
		System.out.println();
		System.out.println("SOSPECHA: ");
		System.out.println(controlador.getSospecha(1));
		System.out.println(controlador.getSospecha(2));
		System.out.println();
		System.out.println(".............................................................");
		System.out.println();
		System.out.println("LAS CARTAS DE ARCHIVO CONFIDENCIAL QUE TENES SON: ");
		System.out.println();
		int j=1;		
		int jug=0;
		boolean encontre = false;		
		while((jug<= controlador.listaJugadores().size()-1) && (!encontre)) {
			if((controlador.getSospechado().equals(controlador.listaJugadores().get(jug))))
				encontre = true;
			else jug++;
		}
		tiene = false;
		for(int i=0; i<= controlador.listaJugadores().get(jug).getCartasSecretas().cantCartas() -1; i++) {
			if((controlador.listaJugadores().get(jug).getCartasSecretas().getCarta(i).getFigura().equals(controlador.getSospecha(1).name())) || (controlador.listaJugadores().get(jug).getCartasSecretas().getCarta(i).getFigura().equals(controlador.getSospecha(2).name()))) {
				tiene = true;
				System.out.println(j + ". " + controlador.listaJugadores().get(jug).getCartasSecretas().getCarta(i).getFigura());
				j++;
			}			
		}
				   
			return tiene;
			

	}
	
	public void mostrarMenuPrincipal() {
	menu = new Principal(controlador, this);
	menu.mostrarMenu();
		
	}
	
	@Override
	public void mostrarArriesgar() {		
		menu = new Arriesgar(controlador,this);
		menu.mostrarMenu();
	}

	@Override
	public void mostrarSospecha() {
		menu = new Sospecha(controlador,this);
		menu.mostrarMenu();

		
		}
	
	@Override
	public void avisoGanador() {
		System.out.println(controlador.getGanador().getNombre() +"¡GANASTE!");
		System.out.println("Tu sospecha es correcta!");
		
		System.out.println();
		System.out.println("||||||||||||||||||||||||||||||");
		System.out.println("     INFORMACION CONFIDENCIAL ");
		System.out.println("||||||||||||||||||||||||||||||");
		System.out.println();
		System.out.println();
		System.out.println("CIUDAD: " + controlador.informacionConfidencial()[0].getFigura());
		System.out.println();
		System.out.println("AGENTE: " + controlador.informacionConfidencial()[1].getFigura());
		System.out.println();
		System.out.println("DISPOSITIVO: " + controlador.informacionConfidencial()[2].getFigura());
		System.out.println();
		}

	

	@Override
	public void avisoPerdio() {
		System.out.println("------ " + controlador.getJugadorEnTurno().getNombre() + " ------"  );
		System.out.println("                          ------ PERDISTE ------");
		System.out.println("               ------ TU SOSPECHA FINAL FUE INCORRECTA ------");
		System.out.println("");
		/*System.out.println("LA INFORMACION CONFIDENCIAL ES:");
		System.out.println();
		System.out.println("CIUDAD: " + controlador.informacionConfidencial()[0].getFigura());
		System.out.println();
		System.out.println("AGENTE: " + controlador.informacionConfidencial()[1].getFigura());
		System.out.println();
		System.out.println("DISPOSITIVO: " + controlador.informacionConfidencial()[2].getFigura());
		System.out.println();*/
	}
	

	@Override
	public void mostraJugadores() {
		ArrayList<IJugador> j = controlador.listaJugadores();
		for (IJugador jugador : j) {
			System.out.println("Jugador: " +  jugador.getNombre());
		}

	}

	
	public void agregarJugador() {
		System.out.println("...................");
		System.out.println(".....ESPIONAJE.....");
		System.out.println("...................");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("Nuevo Jugador");
		System.out.println();
		System.out.println("Ingrese el nombre");
	
		String nombre= sc.nextLine();
		System.out.println();
		
		try {
			controlador.agregarJugador(nombre);
		} catch(IndexOutOfBoundsException e){
			System.out.println("HA SUPERADO LA CANTIDAD DE JUGADORES!");
			System.out.println("El jugador "+ nombre + " no ha sido registrado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void iniciarJuego() {
		try{
			controlador.iniciarJuego();
		} catch(IndexOutOfBoundsException e) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~ ¡ERROR! ~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println("La cantidad de jugadores registrados es menor a 2");
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			menu.mostrarMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public void mostrarConfiguracion(){
		menu = new Configuracion(controlador, this);
		menu.mostrarMenu();
	}


	@Override
	public void mostrarTerminado() {
		System.out.println("------------ FIN DEL JUEGO ----------------");
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~ ESPIONAJE ~~~~~~~~~~~~~~~~~~~");
		
		
	}

	public String convertirSospecha(Integer s) {
		String figura = null;
		int h=0;
		for(int i=0; i<= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1;i++) {
			if(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).cartaValida() == true) {
			 if(s == h) 
				 figura = controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(i).getFigura();
			h++;
			 
				 
			}
		}
		return figura;
	}	

	public IJugador convertirJugador(Integer jug) {
		IJugador jugador = null;
		int i=0;
		for(int j=0; j<= controlador.listaJugadores().size()-1;j++) {	
			if(i == jug ) 
				jugador = controlador.listaJugadores().get(j);
			i++;
			
			   
		}
		return jugador;

	}
	
	public void escribirListaJugadores() {
		for(int j=0; j<= controlador.listaJugadores().size()-1;j++) {
			int i=1;
			System.out.println(i + "- " + controlador.listaJugadores().get(j).getNombre());
			i++;
		}
	}

	private int respuesta;
	@Override
	public void mostrarResponder() {
		if(tiene == false ) {
			System.out.println(controlador.getSospechado().getNombre()+ " responde:");
			System.out.println("¡No tengo ninguna!");
			controlador.paso();
		}else {
		   
			Scanner sc = new Scanner(System.in);
			System.out.println("Elegi cual va a ser tu respuesta a la sospecha!");
		    respuesta= sc.nextInt();
		}
		
	}

	private String sospecha;

	@Override
	public void mostrarRespuesta() {
		
		System.out.println(controlador.listaJugadores().get(jug).getNombre()+ " responde:");
		System.out.println();
		if(respuesta == 1) {
			sospecha = controlador.getSospecha(1).name();
			System.out.println(controlador.setSospecha(controlador.getSospecha(1).name()));
		}
		else {
			sospecha = controlador.getSospecha(2).name();
			System.out.println(controlador.setSospecha(controlador.getSospecha(2).name()));
		}
		
		
	}
	
	@Override
	public void opcionDescartar() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("¿Queres descartarla de tus sospechas?");
		System.out.println("1. SI");
		System.out.println("2. NO");
		int confirmaciom = sc.nextInt();
		boolean encontre = false;
		int c=0;
		if(confirmaciom == 1) {
			while((encontre == false) && (c <= controlador.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1)) {
				if(sospecha.equals(controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(c).getFigura())) {
					controlador.getJugadorEnTurno().getAgendaPersonal().getCarta(c).descartar(true);
					encontre = true;
				}
				c++;
			}
			controlador.paso();
		}else {
			controlador.paso();
		}
	
	}

	public void mostrarError() {
		// TODO Auto-generated method stub
		System.out.println("ERROR");
	}

	

	

}

