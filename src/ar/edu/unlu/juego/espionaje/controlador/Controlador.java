package ar.edu.unlu.juego.espionaje.controlador;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.ValidationException;

import ar.edu.unlu.juego.espionaje.modelo.AGENTES;
import ar.edu.unlu.juego.espionaje.modelo.CIUDADES;
import ar.edu.unlu.juego.espionaje.modelo.CambiosJuego;
import ar.edu.unlu.juego.espionaje.modelo.Carta;
import ar.edu.unlu.juego.espionaje.modelo.DISPOSITIVOS;
import ar.edu.unlu.juego.espionaje.modelo.IJuego;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;
import ar.edu.unlu.juego.espionaje.modelo.Juego;
import ar.edu.unlu.juego.espionaje.modelo.Juego.E_EN_JUEGO;
import ar.edu.unlu.juego.espionaje.modelo.Jugador;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.juego.espionaje.modelo.ESTADOS;

public class Controlador implements IControladorRemoto {
	
	private IJuego juego;
	private IVista vista;
	private ArrayList<String> sospecha;
	private int nroJugador;
	//private String sospechado;
	
	
	
	private static final String ErrorCantidadMinimaJugadores = "MenosJugadores";
	private static final String ErrorCantidadMaximaJugadores = "MasJugadores";
	private static final String SOSPECHA = "SOSPECHA";
	private static final String ARRIESGA = "ARRIESGA";
	private static final String RESPUESTA = "RESPUESTA";
	private static final String RESPONDER = "RESPONDER";
	

	public Controlador(IVista vista) {
		this.vista = vista;
	}
	
	public void setVista(IVista vista) {
		this.vista = vista;
	}

	@Override
	public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
		if(arg1 instanceof CambiosJuego) {
			CambiosJuego cambio = (CambiosJuego) arg1;
			System.out.println("* "+juego.getEstado());
			switch(cambio) {
				case INICIAR: juego.iniciarJuego();
					break;
				case CAMBIO_LISTA_JUGADORES: 
					vista.mostraJugadores();
					break;
				case JUGADOR_PERDIO: 
					if ( ! juego.getJugadores().get(nroJugador).estadoJugador()) {
						vista.avisoPerdio();
					}
					break;
				case HAY_GANADOR:
					if(nroJugador == juego.getGanador().getNroJugador()) {
						vista.avisoGanador();						
					} else { 
						vista.avisoPerdio();
						vista.quienGano();
					}
					break;
				case JUGADOR_SALIO:
				//		if((juego.getSalio().getNroJugador() < this.listaJugadores().size()-1) && (this.nroJugador > juego.getSalio().getNroJugador()) && (juego.getSalio().getNroJugador() != -1))  
				//		this.nroJugador --;
				//		System.out.println(this.nroJugador);
				//		if(juego.getSalio().getNroJugador() != this.nroJugador) vista.notificarSalio();
					break;
				case CAMBIO_ESTADO:
					ESTADOS e = juego.getEstado();
					String estado = juego.getEstado().name();
					if(e == ESTADOS.EN_JUEGO) {
						System.out.println("* "+juego.getEstadoEnJuego());
							switch(juego.getEstadoEnJuego().name()) {
								case ARRIESGA: vista.mostrarArriesgar();
								break;
								case SOSPECHA: vista.mostrarSospechar();
									break;
								case RESPUESTA: vista.mostrarRespuesta(juego.getRespuesta());
									break;
								case RESPONDER: vista.mostrarResponder(); 
									break;	
							}
					}
					else if (e == ESTADOS.CONFIGURANDO) {
						System.out.println(this.nroJugador);
							vista.mostrarConfiguracion();
					}
					else if (e == ESTADOS.REINICIAR) {
						juego.reiniciar(this.nroJugador);
					}
					 else if (e == ESTADOS.FINALIZADO) {
						//if( nroJugador == juego.getGanador().getNroJugador()){ vista.avisoGanador();}
						//else { vista.avisoPerdio();}
						 System.out.println("------------ JUEGO FINALIZADO ------------");
					 }
				break;
			}
		}
	}
	
	
	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T arg0) throws RemoteException {
		this.juego = (IJuego) arg0;
	}
	
// Controles partida
	
	public void iniciarPartida(){
		try {
			if(nroJugador == -1) {vista.mostrarError(ErrorCantidadMaximaJugadores);
			}else {
				juego.iniciarJuego();
			}
		} catch (IndexOutOfBoundsException e) {
			if(e.getMessage().equals("CantidadMinima")) {
				vista.mostrarError(ErrorCantidadMinimaJugadores);
			}
			if(e.getMessage().equals("CantidadMaxima")) {
				vista.mostrarError(ErrorCantidadMaximaJugadores);
			}
		} catch (RemoteException e) {
			System.out.println("REMOTE EX");
			e.printStackTrace();
		}
	}
	
	public void finalizarPartida() {
		try {
			juego.finalizar();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


// Control juego	
	
	public void paso() {
		try {
			juego.pasar();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void reiniciar() {
		try {
			juego.estadoReinicio(); 
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void agregarJugador(String nombre)   {
		try {	
			nroJugador = juego.agregarJugador(nombre);
			if(nroJugador == -1) {
				vista.mostrarError(ErrorCantidadMaximaJugadores);
			}
		} catch (IndexOutOfBoundsException e) {
				vista.mostrarError(ErrorCantidadMaximaJugadores);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			vista.mostrarError("NombreRepetido");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public ArrayList<IJugador> listaJugadores() {
		try {
			return juego.getJugadores();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public IJugador buscarJugador(String s) throws RemoteException {
		IJugador jugador = null;
		int i = 0;
		while( i>= juego.getJugadores().size()-1) {
			if(juego.getJugadores().get(i).getNombre().equals(s))
				jugador = juego.getJugadores().get(i);
			else 
				i++;
				
		}
		return jugador;
	}

	public boolean rtaSospechaFinal(String agentes, String dispositivos, String ciudades) {
		try {
			return juego.verificarSospechaFinal(agentes, dispositivos, ciudades);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Carta[] informacionConfidencial() {
		try {
			return juego.getInfoSecreta();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void descartar() throws RemoteException {
		juego.descartarArchivoConfidencial_AgendaPersonal();
		
	}
	public ArrayList<String> verificarRespuesta() throws RemoteException {
		return juego.verificarRespuesta();
	}
	public boolean hayRespuesta() throws RemoteException{
		boolean hay;
		if(!this.verificarRespuesta().isEmpty()) {
			hay = true;
		} else{hay = false; }
		return hay;
	}
	
	//GETTERS - SETTERS
	
	//SETTERS
	
	public void setSospecha(ArrayList<String> lista) {
		try {
			juego.setSospecha(lista);
			int jugador = juego.getSospechado();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public String getRespuesta() throws RemoteException {return juego.getRespuesta();}

	public void setRespuesta(String respuesta) throws RemoteException { juego.setRespuesta(respuesta);}

	// GETTERS
	
	public ArrayList<String> getSospecha() throws RemoteException {return juego.getSospecha();}
	
	public Carta[] getInfoSecreta() throws RemoteException {return juego.getInfoSecreta();}
	
	public int getNroJugador() {return nroJugador;}
	
	public int getSospechado() {
		try {
			return juego.getSospechado();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public IJugador getJugadorEnTurno() {
		try {
			return juego.getJugadorEnTurno();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public IJugador getGanador() {
		try {
			return juego.getGanador();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void salir() {
		try {
			juego.salir(this.nroJugador);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void arriesgar() throws RemoteException {
		juego.arriesgar();
		
	}

	public ArrayList<String> getGanadores() throws RemoteException {
		ArrayList<String> ganadores = juego.getGanadores();
		return ganadores;
	}

	/*public IJugador getSalio() {
		// TODO Auto-generated method stub
		return juego.getSalio();
	}*/

	
				
}