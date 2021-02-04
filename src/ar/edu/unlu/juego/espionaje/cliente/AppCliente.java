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
		// TODO Auto-generated method stub
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
				ips.toArray(),
				null
		);
		String portServidor = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que corre el servidor", "Puerto del servidor", 
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				8888
		);

		

		
		VistaGrafica vista = new VistaGrafica();
//		VistaConsola vista = new VistaConsola();
		Controlador controlador = new Controlador(vista);	
		vista.setControlador(controlador);
		Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));
		
		try {
			c.iniciar(controlador);
		} catch (RMIMVCException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
