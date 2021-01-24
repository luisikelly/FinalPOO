package ar.edu.unlu.juego.espionaje.controlador;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ar.edu.unlu.juego.espionaje.modelo.AGENTES;
import ar.edu.unlu.juego.espionaje.modelo.CIUDADES;
import ar.edu.unlu.juego.espionaje.modelo.CambiosJuego;
import ar.edu.unlu.juego.espionaje.modelo.Carta;
import ar.edu.unlu.juego.espionaje.modelo.DISPOSITIVOS;
import ar.edu.unlu.juego.espionaje.modelo.IJuego;
import ar.edu.unlu.juego.espionaje.modelo.IJugador;
import ar.edu.unlu.juego.espionaje.modelo.Juego;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.juego.espionaje.modelo.ESTADOS;

public class Controlador implements IControladorRemoto {
	private IJuego juego;
	private IVista vista;
	private ArrayList<String> sospecha;
	private String respuesta;
	private final String ErrorCantidadMinimaJugadores = "MenosJugadores";
	private final String ErrorCantidadMaximaJugadores = "MasJugadores";
	private int nroJugador;
	private String sospechado;


	
	public Controlador(IVista vista) {
		this.vista = vista;
		//vista.setControlador(this);

	}
	
	public void setVista(IVista vista) {
		this.vista = vista;
	}

	@Override
	public void actualizar(IObservableRemoto arg0, Object arg1) throws RemoteException {
		if(arg1 instanceof CambiosJuego) {
			CambiosJuego cambio = (CambiosJuego) arg1;
			switch(cambio) {
				case CAMBIO_JUGADOR: vista.mostrarJugando(); // Chequear si es necesario
					break;
				case CAMBIO_LISTA_JUGADORES: vista.mostraJugadores();
					break;
				case JUGADOR_PERDIO: vista.avisoPerdio(); // Chequear si es necesario
					break;
				case HAY_GANADOR: vista.avisoGanador();
					break;
				case CAMBIO_ESTADO:
					ESTADOS e = juego.getEstado();
					String estado = juego.getEstado().name();
					if(e == ESTADOS.EN_JUEGO) {
						
						if(nroJugador == juego.getJugadorEnTurno().getNroJugador()) {
							if(juego.getEstadoEnJuego().name() == "ARRIESGA")
								vista.mostrarArriesgar();
							if(juego.getEstadoEnJuego().name() == "SOSPECHA")
								vista.mostrarSospechar();
							if(juego.getEstadoEnJuego().name() == "RESPUESTA")
								vista.mostrarRespuesta();
							
						} else {
							if(nroJugador == juego.getSospechado()) {
								if(juego.getEstadoEnJuego().name() == "RESPONDER")
									vista.mostrarElegirRespuesta();
							}	
						}			
					}
					else if (e == ESTADOS.CONFIGURANDO) {
						vista.mostrarConfiguracion();
					} else if (e == ESTADOS.FINALIZADO) {
						vista.mostrarTerminado();
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
			juego.iniciarJuego();
		} catch (IndexOutOfBoundsException e) {
			if(e.getMessage().equals("CantidadMinima")) {
				vista.mostrarError(ErrorCantidadMinimaJugadores);
			}
			if(e.getMessage().equals("CantidadMaxima")) {
				vista.mostrarError(ErrorCantidadMaximaJugadores);
			}
		} catch (RemoteException e) {
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
	
	public int getNroJugador() {
		return nroJugador;
	}

	public void agregarJugador(String nombre)  {
		try {
			nroJugador = juego.agregarJugador(nombre);
		} catch (IndexOutOfBoundsException e) {
		//	vista.mostrarError(this.ErrorCantidadMaximaJugadores);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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

// TODO IMPORTANTE: ¿QUÉ ES ESTO? REVISAR	
	/* public ArrayList<Enum> rtaSospecha(){
		try {
		if (paquete.size() == 1) // la lista paquete tiene en las primeras posiciones el/los elemento/s que se envian
			return juego.sospecha(this.receptor, this.setSospecha(paquete.get(0)), this.setSospecha(paquete.get(1))); //En este caso las sospechas.
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	} */

	public boolean rtaSospechaFinal(Enum agentes, Enum dispositivos, Enum ciudades) {
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


	public ArrayList<IJugador> listaJugadores() {
		try {
			return juego.getJugadores();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void reiniciar() {
		try {
			juego.reiniciar();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

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
	
	public void recibirSospecha(ArrayList<String> lista) {
		try {
		//	mostrarSospechar();
			respuesta = null;
			juego.setSospecha(lista
					);
			int jugador = juego.getSospechado();
			vista.mostrarSospecha(jugador);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	
	//GETTERS - SETTERS
	

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public void setSospechado(String sospechado) {
		this.sospechado = sospechado;
	}
	
	public Carta[] getSospecha() throws RemoteException {
		return juego.getSospecha();
	}

	
	public void paso() {
		try {
			juego.pasar();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public int getSospechado() {
		try {
			return juego.getSospechado();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public void descartar() throws RemoteException {
		juego.descartarArchivoConfidencial_AgendaPersonal();
		
	}
	public Carta[] getInfoSecreta() throws RemoteException {
		return juego.getInfoSecreta();
	}


/*public static void main(String[] args) {
	IJuego j = new Juego();
	IVista vista = new VConsola();
	Controlador controlador = new Controlador(j,vista);
	try {
		j.iniciarAplicacion();
	} catch (RemoteException e) {

		e.printStackTrace();
	}
}*/

				
}