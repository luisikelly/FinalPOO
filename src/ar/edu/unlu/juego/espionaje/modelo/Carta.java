package ar.edu.unlu.juego.espionaje.modelo;

import java.util.Random;
import java.io.Serializable;

public class Carta implements Serializable{
   
	
	private String figura;
	private String tipo;
	private boolean descartado; // descartado es utilizado para saber si el jugado descarto esta carta
	
	
 
	public Carta() {
		this.figura=null;
		this.descartado=false;
	}
	
	public Carta( String figura) {
		super();
    	this.figura = figura;
    }
 
	public Carta(String figura, String tipo) {
		super();
		this.figura = figura;
		this.tipo = tipo;
	}
	
	public void reiniciar() {
		this.figura = null;
	}
	public String getFigura() {
		return figura;
	}

	public boolean getDescartado() {
		return descartado;
	}
	
	public boolean cartaValida() {
		boolean b= true;
		if (this.getDescartado() == true) {
			b= false;
		}
		return b; 
	}
	
	protected CIUDADES randomCiudad() {
		int resultado = new Random().nextInt(CIUDADES.values().length);
		return CIUDADES.values()[resultado];
	}
	protected AGENTES randomAgente() {
		int resultado = new Random().nextInt(AGENTES.values().length);
		return AGENTES.values()[resultado];
	}
	protected DISPOSITIVOS randomDispositivo() {
		int resultado = new Random().nextInt(DISPOSITIVOS.values().length);
		return DISPOSITIVOS.values()[resultado];
	}

	public void descartar (boolean descartado) {
		this.descartado = descartado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
