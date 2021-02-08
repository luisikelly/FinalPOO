package ar.edu.unlu.juego.espionaje.modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.juego.espionaje.modelo.Juego.E_EN_JUEGO;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IJuego extends IObservableRemoto, Serializable{

	
	boolean ganador() throws RemoteException;

	int getSospechado() throws RemoteException;

	ArrayList<String> getSospecha() throws RemoteException;

	void iniciarAplicacion() throws RemoteException;

	void finalizar() throws RemoteException;

	Carta[] getInfoSecreta() throws RemoteException;

	void iniciarJuego() throws IndexOutOfBoundsException, RemoteException;

	void setInfoSecreta() throws RemoteException;

	void mezclarCartas() throws RemoteException;

	void repartirAC() throws RemoteException;

//	ArrayList<Enum> sospecha(IJugador j, Enum s1, Enum s2) throws RemoteException;

	//boolean verificarSospechaFinal(Enum agente, Enum dispositivo, Enum ciudad) throws RemoteException;

	void descartarArchivoConfidencial_AgendaPersonal() throws RemoteException;

	IJugador getGanador() throws RemoteException;

	ArrayList<IJugador> getJugadores() throws RemoteException;

	Jugador getJugadorEnTurno() throws RemoteException;

	int agregarJugador(String nombre) throws IndexOutOfBoundsException, RemoteException;

	ESTADOS getEstado() throws RemoteException;

	E_EN_JUEGO getEstadoEnJuego() throws RemoteException;

	void cambiarJugador() throws RemoteException;

	void reiniciar() throws RemoteException;

	void pasar() throws RemoteException;

	void setSospecha(ArrayList<String> sospecha) throws RemoteException;

	ArrayList<String> verificarRespuesta() throws RemoteException;

	String getRespuesta() throws RemoteException;

	void setRespuesta(String respuesta) throws RemoteException;

	// void salir(int nroJugador);

	boolean verificarSospechaFinal(String agentes, String dispositivos, String ciudades) throws RemoteException;

	void arriesgar() throws RemoteException;

	ArrayList<String> getGanadores() throws RemoteException;


}