package ar.edu.unlu.juego.espionaje.servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;


import javax.swing.JOptionPane;

import ar.edu.unlu.juego.espionaje.modelo.Juego;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class AppServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> ips = Util.getIpDisponibles();
		String ip = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione la IP en la que escuchara peticiones el servidor", "IP del servidor",
				JOptionPane.QUESTION_MESSAGE, 
				null,
				ips.toArray(),
				null
		);
		String port = (String) JOptionPane.showInputDialog(
				null, 
				"Seleccione el puerto en el que escuchara peticiones el servidor", "Puerto del servidor",
				JOptionPane.QUESTION_MESSAGE,
				null,
				null,
				8888
		);
		Juego juego = new Juego();
		Servidor servidor = new Servidor(ip, Integer.parseInt(port));
		try {
			servidor.iniciar(juego);
			juego.iniciarAplicacion();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RMIMVCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
