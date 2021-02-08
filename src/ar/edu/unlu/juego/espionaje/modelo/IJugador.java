package ar.edu.unlu.juego.espionaje.modelo;

import java.io.Serializable;

public interface IJugador extends Serializable {
	
	public String getNombre();
	public Mazo getAgendaPersonal();
	public Mazo getCartasSecretas();
	public int getNroJugador();
	public boolean estadoJugador();
	

}
