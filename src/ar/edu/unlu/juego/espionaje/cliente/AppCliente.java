package ar.edu.unlu.juego.espionaje.cliente;

import java.awt.FontFormatException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.controlador.IVista;
import ar.edu.unlu.juego.espionaje.modelo.IJuego;
import ar.edu.unlu.juego.espionaje.modelo.Juego;
import ar.edu.unlu.juego.espionaje.vista.consola.VistaConsola;
import ar.edu.unlu.juego.espionaje.vista.grafica.VistaGrafica;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;

public class AppCliente {

	public static void main(String[] args) throws FontFormatException, IOException {
		ArrayList<String> ips = Util.getIpDisponibles();
		String ip = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la que escuchara peticiones el cliente", "IP del cliente", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				ips.toArray(),
				null
		);
		String port = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que escuchara peticiones el cliente", "Puerto del cliente", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				9999
		);
		String ipServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la corre el servidor", "IP del servidor", 
				JOptionPane.QUESTION_MESSAGE, 
				null,
				null,
				"127.0.0.1"
		);
		String portServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que corre el servidor", "Puerto del servidor", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				8888
		);
		
		ArrayList<String> vistas = new ArrayList<String>();
		vistas.add("Grafica");
		vistas.add("Consola");
		String v = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el tipo de vista que desea utilizar", "Vista", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				vistas.toArray(),
				null
		);
	
		
		boolean mostrarConfig=false; 
		IVista vista = null;
		if(v.equals("Grafica")) {vista = new VistaGrafica(); }
		else { vista = new VistaConsola();  
		mostrarConfig = true;}
		Controlador controlador = new Controlador(vista);	
		vista.setControlador(controlador);
		Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));			
		try {
			c.iniciar(controlador);
			if(mostrarConfig) {vista.iniciarJuego();}
		} catch (RMIMVCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
