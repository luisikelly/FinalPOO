package ar.edu.unlu.juego.espionaje.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

public class Mazo implements Serializable {
	private ArrayList<Carta> mazo;
    
	
	
	public Mazo (boolean b) { //Agregamos el b de tipo de dato boolean para que tenga distinta firma y poder tener un constructor del mazo vacio y uno con las cartas del juego.
		this.mazo = new ArrayList<Carta>();	
	if (b == true) {	
		mazo.add(new Carta("AGENTE_ROJO","AGENTE"));
		mazo.add(new Carta("AGENTE_BLANCO","AGENTE"));
		mazo.add(new Carta("AGENTE_AZUL","AGENTE"));
		mazo.add(new Carta("AGENTE_VERDE","AGENTE"));
		mazo.add(new Carta("AGENTE_NARANJA","AGENTE"));
		mazo.add(new Carta("TOKIO","CIUDAD"));
		mazo.add(new Carta("PARIS","CIUDAD"));
		mazo.add(new Carta("ATENAS","CIUDAD"));
		mazo.add(new Carta("LONDRES","CIUDAD"));
		mazo.add(new Carta("PANAMA","CIUDAD"));
		mazo.add(new Carta("AVION","DISPOSITIVO"));
		mazo.add(new Carta("HELICOPTERO","DISPOSITIVO"));
		mazo.add(new Carta("GAS_LETAL","DISPOSITIVO"));
		mazo.add(new Carta("AUTOPROPULSOR","DISPOSITIVO"));
		mazo.add(new Carta("SATELITE","DISPOSITIVO"));
	}
}
	
	
	public int cantCartas() {
		return mazo.size();
	}
	
	public Carta getCarta(int i) {
		return mazo.get(i);
	}
	
	public void mezclar() {
		Collections.shuffle(this.mazo); //Metodo que mezcla el contenido de la lista.
	}
	
	public void agregarCarta(Carta c) {
		mazo.add(c);
	}
	
	
	
	public void repartir(Mazo m, int i) {
		if(m.getCarta(i).cartaValida() == true) {
				this.agregarCarta(m.getCarta(i));			
			}
	}

	
	
}