package ar.edu.unlu.juego.espionaje.modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import ar.edu.unlu.juego.espionaje.modelo.Mazo;;

public class Jugador implements IJugador{
	
	private String Nombre;
	private Mazo agendaPersonal;
	private Mazo cartasSecretas;
	private boolean juega;
	private int nroJugador;
	
	
	public boolean getEnJuego() {
		return this.juega;
	}

	public Jugador(String nombre) {
		super();
		Nombre = nombre;
		this.juega = true;
		
		this.agendaPersonal= new Mazo(true);
		this.cartasSecretas = new Mazo(false);
	}

	public String getNombre() {
		return Nombre;
	}
	
	public Mazo getAgendaPersonal() {
		return agendaPersonal;
	}
	
	public Mazo getCartasSecretas() {
		return cartasSecretas;
	}
	
	public ArrayList<Enum> respuestaSospecha(Enum fig1, Enum fig2) {
		ArrayList<Enum> tiene = new ArrayList<Enum>();
		boolean coincide= false;
		for(int i = 0; i<= cartasSecretas.cantCartas();i++) {
			if((fig1.name().equals(cartasSecretas.getCarta(i).getFigura()))) {
				tiene.add(fig1);
			}
			if((fig2.name().equals(cartasSecretas.getCarta(i).getFigura()))) {
				tiene.add(fig2);
			}
		}
		return tiene;
	}

	//public void setCartasSecretas(Mazo m) {
	//this.cartasSecretas = m;
		
	//}

	public void reiniciar() {
		for(int i=0; i<= this.agendaPersonal.cantCartas()-1;i++) {
			this.agendaPersonal.getCarta(i).descartar(false);
		}
		cartasSecretas = new Mazo(false);
		for(int i=0; i<= this.cartasSecretas.cantCartas()-1;i++) {
			this.agendaPersonal.getCarta(i).reiniciar();
		}
	}

	public void sacarjuego() {
		juega = false;
	}


	public int getNroJugador() {
		return nroJugador;
	}

	public void setNroJugador(int nroJugador) {
		this.nroJugador = nroJugador;
	}

	
	

}
