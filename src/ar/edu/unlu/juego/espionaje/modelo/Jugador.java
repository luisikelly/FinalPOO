
package ar.edu.unlu.juego.espionaje.modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import ar.edu.unlu.juego.espionaje.modelo.Mazo;;

public class Jugador implements IJugador,Serializable{
	
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
	
	public ArrayList<String> respuestaSospecha(ArrayList<String> r) {
		ArrayList<String> tiene = new ArrayList<String>();
		boolean coincide= false;
		for(int i = 0; i<= cartasSecretas.cantCartas()-1;i++) {
			if((r.get(0).equals(cartasSecretas.getCarta(i).getFigura()))) {
				tiene.add(r.get(0));
			}
			if((r.get(1).equals(cartasSecretas.getCarta(i).getFigura()))) {
				tiene.add(r.get(1));
			}
		}
		return tiene;
	}

	//public void setCartasSecretas(Mazo m) {
	//this.cartasSecretas = m;
		
	//}

	public void reiniciar() {
		juega = true;
		/*for(int i=0; i<= this.agendaPersonal.cantCartas()-1;i++) {
			this.agendaPersonal.getCarta(i).descartar(false);
		}*/
		this.agendaPersonal = new Mazo(true);
		this.cartasSecretas = new Mazo(false);

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
	
	public boolean estadoJugador() {
		return juega;
	}
	
	

}
