package ar.edu.unlu.juego.espionaje.controlador;

import java.rmi.RemoteException;

import ar.edu.unlu.juego.espionaje.modelo.Carta;
import ar.edu.unlu.juego.espionaje.modelo.Jugador;

public interface IVista {

	void mostrarArriesgar();

	void avisoGanador();

	void mostraJugadores();

	void setControlador(Controlador c);
	
	void mostrarConfiguracion();

	void mostrarResponder();
	
	void mostrarRespuesta(String string);

	void iniciarJuego();

	void avisoPerdio();

	void mostrarError(String tError);

	void mostrarSospechar();

	void mostrarTurno(String string);

	void quienGano();



}
