package ar.edu.unlu.juego.espionaje.vista.consola;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;

public abstract class Menu {
		protected Controlador miControlador;
		protected VistaConsola miVista;
		public abstract void mostrarMenu();
	
}
