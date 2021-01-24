package ar.edu.unlu.juego.espionaje.modelo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.modelo.AGENTES;
import ar.edu.unlu.juego.espionaje.modelo.CIUDADES;
import ar.edu.unlu.juego.espionaje.modelo.Carta;
import ar.edu.unlu.juego.espionaje.modelo.DISPOSITIVOS;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;


public class Juego extends ObservableRemoto implements IJuego {
	
	
	public static enum E_EN_JUEGO {SOSPECHA,ARRIESGA,RESPUESTA,RESPONDER, MENU, INICIAL};
	
	private Mazo archivoConfidencial; 
	private Carta[] infoSecreta;
	private ArrayList<Jugador> jugadores;
	private ESTADOS estado = ESTADOS.CONFIGURANDO;
	private E_EN_JUEGO eEJ;
	private boolean enJuego = true;
	private IJugador ganador = null;
	private boolean gano = false;
	private int jugadorEnTurno;
	private int sospechado;
	private Enum sospecha1;
	private Enum sospecha2;
	private Carta[] sospecha;
	
	
	public static void main (String args[]) throws RemoteException {
		IJuego miJuego = new Juego();
		miJuego.iniciarAplicacion();
	}

	@Override
	public void iniciarAplicacion() throws RemoteException {
		archivoConfidencial = new Mazo(true);
		jugadores = new ArrayList<Jugador>();
		infoSecreta = new Carta[3];
		this.estado = ESTADOS.CONFIGURANDO;
		notificar(CambiosJuego.CAMBIO_LISTA_JUGADORES);
		notificar(CambiosJuego.CAMBIO_ESTADO);
	}
	
	@Override
	public void finalizar() throws RemoteException {
		enJuego = false;
		estado = ESTADOS.FINALIZADO;
		notificar(CambiosJuego.CAMBIO_ESTADO);
	}
	
	
	  @Override
	public void reiniciar() throws RemoteException {
		//miMenu = new MenuConfiguracion(this);
		estado = ESTADOS.CONFIGURANDO;
		jugadorEnTurno = 0;
		for(Jugador j : jugadores) 
			j.reiniciar();
		notificar(CambiosJuego.CAMBIO_ESTADO);
		notificar(CambiosJuego.CAMBIO_JUGADOR);
		notificar(CambiosJuego.CAMBIO_LISTA_JUGADORES);
	}
	  
	
	@Override
	public void iniciarJuego() throws IndexOutOfBoundsException, RemoteException {
		if(jugadores.size() >= 2 || jugadores.size() <= 4) {	
			estado = ESTADOS.EN_JUEGO;
			eEJ = E_EN_JUEGO.SOSPECHA;
			this.setInfoSecreta();
            this.repartirAC();
            this.descartarArchivoConfidencial_AgendaPersonal();
			//enJuego = true;
			//notificar(CambiosJuego.CAMBIO_JUGADOR);
			notificar(CambiosJuego.CAMBIO_ESTADO);
		} else {
			IndexOutOfBoundsException ex;
			if(jugadores.size() < 2) {
				ex = new IndexOutOfBoundsException("CantidadMinima");
				throw(ex);
			}
			if(jugadores.size() > 4) {
				ex = new IndexOutOfBoundsException("CantidadMaxina");
				throw(ex);
			}
		}	
	}
	
	
//PROCEDIMIENTOS PARA EL DESARROLLO DEL JUEGO

	@Override
	public boolean ganador() throws RemoteException{
		return gano;
	}

	@Override
	public void setInfoSecreta() throws RemoteException {
		Carta cc = new Carta();
		infoSecreta[0] = new Carta(cc.randomCiudad().name());
		infoSecreta[1]= new Carta(cc.randomAgente().name());
		infoSecreta[2]= new Carta(cc.randomDispositivo().name());
		for(int i=0; i<=archivoConfidencial.cantCartas() -1;i++) {
			for(int j=0;j<=infoSecreta.length-1;j++) {
				if(infoSecreta[j].getFigura().equals(archivoConfidencial.getCarta(i).getFigura()) == true) {
					archivoConfidencial.getCarta(i).descartar(true);
			    }			
		    }
   	    }
	}
	
	@Override
	public void mezclarCartas() throws RemoteException {
		archivoConfidencial.mezclar();
	}
	
	@Override
	public void repartirAC() throws RemoteException{
		int nroCartasRestantes = 12;
		
		this.mezclarCartas();
			
		for(int i=0; i<= jugadores.size()-1;i++) {
			int repartidas = 0;
			for(int j=0; (j<= this.archivoConfidencial.cantCartas() -1)  ; j++) {
			  if (repartidas <  (nroCartasRestantes/jugadores.size())) {
				  if(this.archivoConfidencial.getCarta(j).cartaValida() == true) {
					  
					  jugadores.get(i).getCartasSecretas().agregarCarta(archivoConfidencial.getCarta(j));
					  archivoConfidencial.getCarta(j).descartar(true);
					
					  repartidas ++;
				  }  
			  }				
			}
		}
	}
	
	
	//TODO IMPORTANTE: ¿QUÉ ES ESTO? REVISAR
	
	/*	@Override 
	public ArrayList<Enum> sospecha(IJugador j, Enum s1, Enum s2 ) throws RemoteException {
		ArrayList<Enum> resp = new ArrayList<Enum>();
		for(int jug=0; jug<= jugadores.size() - 1; jug++ ) {
			if(jugadores.get(jug).equals(j)) {
				// Debería enviarle la sospecha al jugador sospechado ¿Como hacerlo?
			    resp = jugadores.get(jug).respuestaSospecha(s1, s2);
			}
		}
		eEJ = E_EN_JUEGO.RESPONDER;
		notificar(CambiosJuego.CAMBIO_ESTADO);
		return resp;
	} */
	
	
	@Override
	public void setSospecha(ArrayList<String> s) throws RemoteException {
		sospecha[0].setTipo(s.get(0));
		sospecha[1].setTipo(s.get(1)) ;
		this.sospechado = this.jugadorEnTurno ++;
	}
	
	public void recibioRespuesta(String respuesta) throws RemoteException {
		for(int i = 0; i<= this.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
			Carta  carta = this.getJugadorEnTurno().getAgendaPersonal().getCarta(i);
			if(respuesta.equals(carta.getFigura()) && carta.cartaValida()) {
				carta.descartar(true);
				}
		}
	}
	
	@Override
	public boolean verificarSospechaFinal(Enum agente, Enum dispositivo, Enum ciudad) throws RemoteException {
		boolean resultado = false;
			if((infoSecreta[1].getFigura().equals(agente.name())) && (infoSecreta[2].getFigura().equals(dispositivo.name())) && (infoSecreta[0].getFigura().equals(ciudad.name()))) {
								resultado = true; }
		if(resultado) {
			ganador = this.getJugadorEnTurno();
			gano= true;
			notificar(CambiosJuego.HAY_GANADOR);
			estado = ESTADOS.FINALIZADO;	} 
		else {
			notificar(CambiosJuego.JUGADOR_PERDIO);
			int cantJugadores =0;
			for(int jugador =0; jugador <= jugadores.size()-1; jugador ++ ) {
				if(jugadores.get(jugador).getEnJuego())
					cantJugadores++; }
		if(cantJugadores == 2) {
			this.cambiarJugador();
			ganador = this.getJugadorEnTurno();
			gano = true;
			notificar(CambiosJuego.HAY_GANADOR);
			estado = ESTADOS.FINALIZADO;}
		else {
			this.getJugadorEnTurno().sacarjuego();
			this.pasar();
			notificar(CambiosJuego.CAMBIO_JUGADOR);}
		}
		
		return resultado;
		
	}
	
	
	@Override
	public void descartarArchivoConfidencial_AgendaPersonal() throws RemoteException {
		for(int j=0; j<= jugadores.size()-1;j++) {
			for(int ap= 0; ap<= jugadores.get(j).getAgendaPersonal().cantCartas()-1; ap++) {
				for(int cs=0; cs<= jugadores.get(j).getCartasSecretas().cantCartas()-1; cs++) {
					if(jugadores.get(j).getAgendaPersonal().getCarta(ap).getFigura().equals(jugadores.get(j).getCartasSecretas().getCarta(cs).getFigura()))
						jugadores.get(j).getAgendaPersonal().getCarta(ap).descartar(true);
				}
			}
			
		}
		
	}
	
    
    @Override
	public int agregarJugador(String nombre) throws IndexOutOfBoundsException, RemoteException {
	    Jugador j = new Jugador(nombre);
	    int nroJugador;
    	if (jugadores.size() < 4 && estado == ESTADOS.CONFIGURANDO) {
			// 4 es la cantidad maxima establecidas por el reglamento oficial.
			jugadores.add(j);
			j.setNroJugador(jugadores.size()-1);
			nroJugador = j.getNroJugador();
			notificar(CambiosJuego.CAMBIO_LISTA_JUGADORES);
    		} else {
			IndexOutOfBoundsException ex = new IndexOutOfBoundsException();
			throw(ex);
		}
    	return nroJugador; // Devuelve el numero de jugador que se utiliza en el controlador y la vista para mostrar permitir que accion realizar dependiendo si es su turno o si tiene que responder una sospecha realizada por el jugador en turno
	}

    @Override
	public void cambiarJugador() throws RemoteException {
		jugadorEnTurno ++;
		if (jugadorEnTurno == jugadores.size()) {
			jugadorEnTurno = 0;
			if (ganador()) {
				estado = ESTADOS.FINALIZADO;
				notificar(CambiosJuego.HAY_GANADOR);
				notificar(CambiosJuego.CAMBIO_ESTADO);
			}
		}
		if(jugadores.get(jugadorEnTurno).getEnJuego() == false) {
			jugadorEnTurno ++;
		}
		//if (estado != ESTADOS.FINALIZADO) {
			//notificar(CambiosJuego.CAMBIO_JUGADOR);
		//}
	}

    
	@Override
	public void pasar() throws RemoteException {
		this.cambiarJugador();
		notificar(CambiosJuego.CAMBIO_JUGADOR);
	}

	
// GETTERS
	
	
	@Override
	public int getSospechado() throws RemoteException{
		return sospechado;
	}


	@Override
	public IJugador getGanador() throws RemoteException{
		return ganador;
	}
	
	
	@Override
	public ArrayList<IJugador> getJugadores() throws RemoteException {
		ArrayList<IJugador> j = new ArrayList<>();
		for (Jugador jugador : jugadores) {
			j.add(jugador);
		}
		return j;
	}

    @Override
	public Jugador getJugadorEnTurno() throws RemoteException{
    	return jugadores.get(jugadorEnTurno);
    }

    @Override
	public ar.edu.unlu.juego.espionaje.modelo.ESTADOS getEstado() throws RemoteException {
		return this.estado;
	}

	@Override
	public E_EN_JUEGO getEstadoEnJuego() throws RemoteException {		
		return this.eEJ;
	}
    
	private void notificar(CambiosJuego cambio) throws RemoteException {
		notificarObservadores(cambio);
	}
		
	@Override
	public Carta[] getInfoSecreta() throws RemoteException {
		return infoSecreta;
	}

	@Override
	public Carta[] getSospecha() throws RemoteException {
		// TODO Auto-generated method stub
		return sospecha;
	}


}


