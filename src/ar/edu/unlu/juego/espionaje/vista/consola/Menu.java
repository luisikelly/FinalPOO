package ar.edu.unlu.juego.espionaje.vista.consola;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public abstract class Menu {
		protected Controlador miControlador;
		protected VistaConsola miVista;
		protected static final String SOSPECHA = "SOSPECHA";
		protected static final String ARRIESGA = "ARRIESGA";
		protected static final String RESPUESTA = "RESPUESTA";
		protected static final String RESPONDER = "RESPONDER";
		protected static final String RESPONDER_JET = "RESPONDER_JET";
		protected static final String SOSPECHAR = "SOSPECHAR";
		protected static final String ARRIESGAR = "ARRIESGAR";
		public abstract void mostrarMenu();
	
}
