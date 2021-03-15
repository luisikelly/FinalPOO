package ar.edu.unlu.juego.espionaje.vista.consola;

import java.util.Scanner;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;

public class TestConsola implements IVista {

	private Controlador controlador;
	private String nombre = " ";
	
	@Override
	public void mostrarArriesgar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avisoGanador() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostraJugadores() {
		for(int i=0;i<= controlador.listaJugadores().size()-1;i++) {
			System.out.println(controlador.listaJugadores().get(i).getNroJugador() +" "+ controlador.listaJugadores().get(i).getNombre() );
		}
	}

	@Override
	public void setControlador(Controlador c) {
		this.controlador = c;
		
	}

	@Override
	public void mostrarConfiguracion() {
		Scanner s = new Scanner(System.in);
		System.out.println("Jugador ingresar nombre");
		nombre =s.nextLine();
		this.controlador.agregarJugador(nombre);
	}

	@Override
	public void mostrarResponder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarRespuesta(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void iniciarJuego() {
		this.mostrarConfiguracion();
	}

	@Override
	public void avisoPerdio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarError(String tError) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarSospechar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarTurno(String string) {
		System.out.println("................");
	}

	@Override
	public void quienGano() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarGanadores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarAyuda() {
		// TODO Auto-generated method stub
		
	}

}
