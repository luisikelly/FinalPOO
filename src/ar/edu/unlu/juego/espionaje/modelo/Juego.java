package ar.edu.unlu.juego.espionaje.modelo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Observable;

import javax.xml.bind.ValidationException;

import ar.edu.unlu.juego.espionaje.controlador.Controlador;
import ar.edu.unlu.juego.espionaje.modelo.AGENTES;
import ar.edu.unlu.juego.espionaje.modelo.CIUDADES;
import ar.edu.unlu.juego.espionaje.modelo.Carta;
import ar.edu.unlu.juego.espionaje.modelo.DISPOSITIVOS;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;


public class Juego extends ObservableRemoto implements IJuego,Serializable {
	
	
	public static enum E_EN_JUEGO {SOSPECHA,ARRIESGA,RESPUESTA,RESPONDER, MENU, INICIAL};
	
	private Mazo archivoConfidencial; 
	private Carta[] infoSecreta;
	private ArrayList<Jugador> jugadores;	
	private ArrayList<String> rankingJugadores = new ArrayList<String>();
	private ESTADOS estado = ESTADOS.CONFIGURANDO;
	private E_EN_JUEGO eEJ;
	private boolean enJuego = true;
	private IJugador ganador = null;
	private boolean gano = false;
	private int sospechas=0;
	private int chancesGanador= 0;
	private int jugadorEnTurno = 0;
	private int sospechado;
	private Jugador salioJug;
	private ArrayList<String> sospecha;
	private String respuesta;
	private int confirmados;
	
	
	
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
		this.eEJ = E_EN_JUEGO.SOSPECHA;
		notificar(CambiosJuego.CAMBIO_LISTA_JUGADORES);
		notificar(CambiosJuego.CAMBIO_ESTADO);
		

	}
	
	@Override
	public void finalizar() throws RemoteException {
		enJuego = false;
		ganador = null;
		this.respuesta =" ";
		gano = false;
		estado = ESTADOS.FINALIZADO;
		notificar(CambiosJuego.CAMBIO_ESTADO);
	}
	
	
	  @Override
	public void reiniciar(int n) throws RemoteException {
		estado = ESTADOS.CONFIGURANDO;
		//this.jugadores.get(n).reiniciar();
		for(Jugador j : jugadores) {
			j.reiniciar();
			System.out.println("* "+j.getCartasSecretas().cantCartas());
		}
		archivoConfidencial = new Mazo(true);
		notificar(CambiosJuego.CAMBIO_ESTADO);
		notificar(CambiosJuego.CAMBIO_LISTA_JUGADORES); 
	}
	
	  @Override
	  public void inicio() throws RemoteException {
		  //eEJ = E_EN_JUEGO.SOSPECHA;
	  }
	
	@Override
	public void iniciarJuego() throws IndexOutOfBoundsException, RemoteException {
		if(jugadores.size() >= 2 && jugadores.size() <= 4) {	
			estado = ESTADOS.EN_JUEGO;
			eEJ = E_EN_JUEGO.SOSPECHA;
			this.setInfoSecreta();
			this.repartirAC();
            this.descartarArchivoConfidencial_AgendaPersonal();
            this.jugadorEnTurno = 0;
            this.sospechado = 1;
            chancesGanador = this.getJugadores().size();
 			notificar(CambiosJuego.CAMBIO_JUGADOR);
 			System.out.println("- CONFIRMA -");
 			System.out.println();
 			System.out.println(this.estado);
 			System.out.println(this.eEJ);
 			System.out.println();
 			
 			System.out.println(this.getInfoSecreta()[0].getFigura());
			System.out.println(this.getInfoSecreta()[1].getFigura());
			System.out.println(this.getInfoSecreta()[2].getFigura());
			
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
	
	

	private void notificar(CambiosJuego cambio) throws RemoteException {
		notificarObservadores(cambio);
	}
	
//TODO PROCEDIMIENTOS PARA EL DESARROLLO DEL JUEGO

	public void resetSospecha() {
		this.respuesta = "";
		sospecha.clear();

	}
	
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
	
		
	
	@Override
	public void setSospecha(ArrayList<String> s) throws RemoteException {
		sospecha = s;
		System.out.println("----");
		for(String e: sospecha) {
			System.out.println(e);
		}
		System.out.println("----");
		this.eEJ = E_EN_JUEGO.RESPONDER;
		notificar(CambiosJuego.CAMBIO_ESTADO);
	}
	
	
	@Override
	public void setRespuesta(String respuesta) throws RemoteException {
		this.respuesta = respuesta;
		System.out.println("Respuesta enviada: "+ this.respuesta);
		if(!respuesta.equals("")){
			for(int i = 0; i<= this.getJugadorEnTurno().getAgendaPersonal().cantCartas()-1; i++) {
			Carta  carta = this.getJugadorEnTurno().getAgendaPersonal().getCarta(i);
			if(respuesta.equals(carta.getFigura()) && carta.cartaValida()) {
				carta.descartar(true);
				this.eEJ = E_EN_JUEGO.RESPUESTA;
				notificar(CambiosJuego.CAMBIO_ESTADO);
				}
			}
		}else {
				if(jugadores.size() > 2 ) {
						if(sospechado == jugadores.size()-1){ sospechado = 0;}
				    	else {sospechado++;}
						if((sospechado != jugadorEnTurno)) {
								this.eEJ = E_EN_JUEGO.RESPONDER;
								notificar(CambiosJuego.CAMBIO_ESTADO);							
						}else {
							this.eEJ = E_EN_JUEGO.RESPUESTA;
							notificar(CambiosJuego.CAMBIO_ESTADO); 
						}
				}else { pasar();}
		}
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
	public int agregarJugador(String nombre) throws IndexOutOfBoundsException, RemoteException, ValidationException{
	    Jugador j = new Jugador(nombre);
	    int nroJugador=-1;
    	if ((jugadores.size() < 4) && (estado == ESTADOS.CONFIGURANDO) && (this.buscarJugador(nombre) == -1)) {
			// buscarJugador = -1 significa que no existe ningun juagdor con ese nombre
    		// 4 es la cantidad maxima establecidas por el reglamento oficial.
			jugadores.add(j);
			j.setNroJugador(jugadores.size()-1);
			nroJugador = j.getNroJugador();
			System.out.println(jugadores.get(nroJugador).getNombre());
			notificar(CambiosJuego.CAMBIO_LISTA_JUGADORES);

    	} else {
    		if(jugadores.size() >= 4) {
    			IndexOutOfBoundsException ex = new IndexOutOfBoundsException();
    			throw(ex);
    		}
    		if(this.buscarJugador(nombre)!= -1) {
    			ValidationException ex = new ValidationException("NombreRepetido");
    			throw(ex);
    		}
    		
		}
    	return nroJugador; // Devuelve el numero de jugador que se utiliza en el controlador y la vista para mostrar permitir que accion realizar dependiendo si es su turno o si tiene que responder una sospecha realizada por el jugador en turno
	}


    
    @Override
	public void cambiarJugador() throws RemoteException {
			if (jugadorEnTurno == jugadores.size()-1) {
				jugadorEnTurno = 0;
				sospechado = 1;	
			}else {
				jugadorEnTurno ++;
				int j = jugadorEnTurno;
				int jug = jugadores.size() -1;
				if(sospechado == jugadores.size()-1){ sospechado = 0;}
		    	else {sospechado = ++j;}
				if(sospechado == jugadores.size()) sospechado = 0;

			}
		if(! eEJ.equals(E_EN_JUEGO.ARRIESGA)) {
			eEJ = E_EN_JUEGO.SOSPECHA;
			notificar(CambiosJuego.CAMBIO_ESTADO);	
		}
	}

    
	@Override
	public void pasar() throws RemoteException {
		this.cambiarJugador();
		if(eEJ.equals(E_EN_JUEGO.SOSPECHA)) this.resetSospecha();
	}

	private int buscarJugador(String s) {
		int nJugador = -1;
		for(int i=0; i<= jugadores.size()-1; i++) {
			if(jugadores.get(i).getNombre().equals(s)) {
				nJugador = i;
			}
		}
		return nJugador;
	}
	
	
// TODO GETTERS
	
	
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
    
		
	@Override
	public Carta[] getInfoSecreta() throws RemoteException {
		return infoSecreta;
	}

	@Override
	public ArrayList<String> getSospecha() throws RemoteException {
		return sospecha;
	}
	
	@Override
	public String getRespuesta() throws RemoteException {
		return respuesta;
	}

	@Override
	public ArrayList<String> verificarRespuesta() throws RemoteException {
		ArrayList<String> opcionesRespuesta = new ArrayList<String>();
		Jugador j = (Jugador) this.getJugadores().get(sospechado);
		return opcionesRespuesta = j.respuestaSospecha(sospecha);
	}

	
	@Override
	public boolean verificarSospechaFinal(String agente, String dispositivo, String ciudad) throws RemoteException {
		boolean resultado = false;
		if((infoSecreta[0].getFigura().equals(ciudad)) && (infoSecreta[1].getFigura().equals(agente)) && (infoSecreta[2].getFigura().equals(dispositivo))) {
			resultado = true; 
		}
		if(chancesGanador > 0) {
			if(resultado) {
				ganador = this.getJugadorEnTurno();
				gano = true;
				notificar(CambiosJuego.HAY_GANADOR);
				this.estado = ESTADOS.FINALIZADO;
				notificar(CambiosJuego.CAMBIO_ESTADO);
				this.addUltimosGanadores(ganador);
				this.finalizar();
				estado = ESTADOS.FINALIZADO;
				notificar(CambiosJuego.CAMBIO_ESTADO);
			}else {
					this.getJugadorEnTurno().sacarjuego();
					notificar(CambiosJuego.JUGADOR_PERDIO);
					System.out.println("Perdio");
					this.pasar();
					chancesGanador --;
					System.out.println("chanceGanar: "+ this.chancesGanador);
					eEJ = E_EN_JUEGO.ARRIESGA;
					}
				if(chancesGanador == 0) {
					this.estado = ESTADOS.FINALIZADO;
					this.finalizar();
					System.out.println("chanceGanarFIN: "+ this.chancesGanador);
				}
					notificar(CambiosJuego.CAMBIO_ESTADO);
			}			
	
	return resultado;
	}

	@Override
	public void arriesgar() throws RemoteException {
			eEJ = E_EN_JUEGO.ARRIESGA;
			notificar(CambiosJuego.CAMBIO_ESTADO);		
		
	}
	

	
// TODO PERSISTENCIA OBJETO JUGADOR -- REGISTRO DE GANADORES --
	private ArrayList<IJugador> getHistorialGanadores() { // RECUPERO GANADORES

		File ganadores = new File("historialGanadores.txt");
		  if (!ganadores.exists()) {
			  try {
				ganadores.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		  	
		  if (0 == ganadores.length() ) {
			try {  
				ArrayList<IJugador> topsHistoricos = new ArrayList<>();
			  ObjectOutputStream oos;
		
				oos = new ObjectOutputStream(new FileOutputStream("historialGanadores.txt"));
				oos.writeObject(topsHistoricos);
		        oos.close();
		     } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();   
		        
		  }
		  	}else
		try {
			 ObjectInputStream ois = new ObjectInputStream( 
			         new FileInputStream("historialGanadores.txt") );
			 Object jugadores = ois.readObject();
			 if (jugadores instanceof ArrayList<?> ) {
			  ArrayList<IJugador> historialGanadores = ( ArrayList<IJugador> ) jugadores;
			        ois.close();
			        
			   return historialGanadores;     
			 }
			}
			catch ( Exception e ) {
			 e.printStackTrace();
			}
			return new ArrayList<IJugador>();
	}

	@Override
	public ArrayList<String> getGanadores() throws RemoteException{ // DEVUELVE AL CONTROLADOR LISTA CON LOS NOMBRES DE LOS GANADORES  
		ArrayList<String> ganadores = new ArrayList<String>();
		for(int i=0; i<= this.getHistorialGanadores().size() -1; i++) {
			ganadores.add(this.getHistorialGanadores().get(i).getNombre());
		}
	
		return ganadores;
	} 
	
	private void addUltimosGanadores(IJugador ganador)  { //AGREGAR GANADOR AL HISTORIAL
		try {
		  File archivoHistorial = new File("historialGanadores.txt");
		  ArrayList<IJugador> historialGanadores;
		  if (archivoHistorial.exists()) {
		   ObjectInputStream ois = new ObjectInputStream( new FileInputStream ("historialGanadores.txt") );
		   historialGanadores = ( ArrayList <IJugador> ) ois.readObject();
		         ois.close();
		         historialGanadores.add(ganador);
		         archivoHistorial.delete();
		  }
		  else {
		   historialGanadores = new ArrayList<IJugador>();
		   historialGanadores.add(ganador);
		  }
		  
		  File file = new File ( "historialGanadores.txt");
		  file.delete();
		  ObjectOutputStream  a = new ObjectOutputStream(new FileOutputStream("historialGanadores.txt"));
		  a.writeObject(historialGanadores);
		        a.close();
		        
		 } catch (Exception e) {
		  e.printStackTrace();
		 }
		
	}
	
	
	@Override
	public void salir(int nroJugador) throws RemoteException {
	}

	@Override
	public void estadoReinicio() throws RemoteException {
		// TODO Auto-generated method stub
		this.estado = ESTADOS.REINICIAR;
		notificar(CambiosJuego.CAMBIO_ESTADO);
	}


	
	
	
	

	
	


}


