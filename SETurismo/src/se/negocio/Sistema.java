package se.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JOptionPane;

import se.modelo.BaseDeDatos;
import se.negocio.beans.Ciudad;
import se.negocio.beans.Destino;
import se.negocio.beans.Hospedaje;
import se.negocio.beans.Preferencias;
import se.negocio.beans.Tour;
import se.negocio.beans.Usuario;
import se.presentacion.Base;
import se.presentacion.PaqueteTuristico;

import jess.Activation;
import jess.Console;
import jess.ConsolePanel;
import jess.Deffacts;
import jess.Defrule;
import jess.Deftemplate;
import jess.Fact;
import jess.HasLHS;
import jess.JessException;
import jess.Rete;
import jess.WorkingMemoryMarker;

public class Sistema extends Observable{
	
	private Rete motor;
	private Console consola;
	private ConsolePanel consolePanel;
	private WorkingMemoryMarker marca;
	
	private BaseDeDatos bd;
	
	private List<Ciudad> ciudades;
	private List<Hospedaje> hospedajes;
	private List<Destino> destinos;
	private List<Tour> tours;
	
	private int estrategia;
	public int DEPTH=1;
	public int BREADTH=2;
	public int PRUEBA=3;
	
	private int encadenamiento;
	public int PROGRESIVO=1;
	public int REGRESIVO=2;
	
	private int baseHechos;
	private int baseConocimiento;
	public int DESDEARCHIVO=0;
	public int INTERFAZ=1;
	public int BASEDEDATOS=2;
	
	private Usuario usuario;

	
	
	public Sistema(Base base){
		
		iniciaRete();
		consolePanel=new ConsolePanel(getMotor());
		//consolePanel=new ConsolePanel(new Rete());
		bd=new BaseDeDatos();
		cargarListas();
		this.addObserver(base);
		
	}
	
	public void iniciaRete(){
		motor=new Rete();
		motor.watchAll();
	}
	
	@SuppressWarnings("unchecked")
	public void ejecutar(Usuario usuario){
		/*Cargando al estado inicial*/
		try {
			System.out.println("Reseteando");
			motor.clear();
			motor.reset();
			System.out.println("Limpiando Storage");
			//Storage compartido entre Jess y java (comparte variables)
			motor.clearStorage();
		} catch (JessException e1) {
			System.out.println("Error al resetar el motor");
			e1.printStackTrace();
		}
	
		establecerEncadenamiento();
		establecerEstrategia();
		cargarConocimiento();
		cargarDatos();
		
		setUsuario(usuario);
		cargarUsuario();
		
		mostrarReglasActivadas();
		
		try {
			System.out.println("Corriendo el motor de inferencia!");
			motor.run();
	
		} catch (JessException e) {
			System.out.println("Error al correr Jess");
			e.printStackTrace();
		}
		
	
		setChanged();
		notifyObservers();
	}
	
	
	
	public void mostrarConsola(){
		consola=new Console("Consola de Jess", getMotor());
		consola.setVisible(true);
	}
	
	
	@SuppressWarnings("unchecked")
	public void mostrarReglas(){
		Iterator<Defrule> listaReglas=motor.listDefrules();
		System.out.println("---Reglas definidas---");
		while(listaReglas.hasNext())
			System.out.println(listaReglas.next().getName());
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarDatos(){
		Iterator<Fact> listaDatos=motor.listFacts();
		System.out.println("---Datos definidos---");
		while(listaDatos.hasNext())
			System.out.println(listaDatos.next().getName());
	}
	
	@SuppressWarnings("unchecked")
	public void mostrarReglasActivadas(){
		Iterator<Activation> listaActivaciones;
		try {
			listaActivaciones = motor.listActivations();
			/*
			while(listaActivaciones.hasNext()){
				System.out.println(listaActivaciones.next().getRule().getName());
				escribir(listaActivaciones.next().toString());
			}
			*/
		} catch (JessException e) {
			e.printStackTrace();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public void establecerEncadenamiento(){
		/*Especificando el tipo de encadenamiento*/
		escribirEnJess("Estableciendo tipo de encadenamiento");
		if(encadenamiento==REGRESIVO){
			Iterator<Deftemplate> plantillas=motor.listDeftemplates();
			while(plantillas.hasNext())
				try {
					motor.eval("(do-backward-chaining "+plantillas.next().getName()+")");
				} catch (JessException e) {
					System.out.println("Error estableciendo encadenamiento regresivo");
					e.printStackTrace();
				}
		}
		
	}
	
	public void establecerEstrategia(){
		escribirEnJess("Estableciendo estrategia de resolucion de conflictos");
		try {
			if(estrategia==DEPTH)
				motor.eval("(set-strategy depth)");
			else if(estrategia==BREADTH)
				motor.eval("(set-strategy breadth)");
			else if(estrategia==PRUEBA)
				motor.eval("(set-strategy EstrategiaPrueba)");
		}catch (JessException e) {
			System.out.println("Error estableciendo estrategia");
			e.printStackTrace();
		}
		
	}
	
	public void cargarConocimiento(){
		escribirEnJess("Cargando las reglas y templates al motor de Jess");
		if(baseConocimiento==DESDEARCHIVO)
			try {
				motor.batch("se/negocio/jess/reglas.clp");
			} catch (JessException e) {
				System.out.println("Error al cargar las reglas");
				e.printStackTrace();
			}
		else if(baseConocimiento==BASEDEDATOS){
			try {
				motor.batch("se/negocio/jess/reglas.clp");
			} catch (JessException e) {
				System.out.println("Error al cargar las reglas");
				e.printStackTrace();
			}
		}
	}
	
	public void cargarDatos(){
		escribirEnJess("Cargando los hechos(facts) necesarios para el sistema");
		
		if(baseHechos==BASEDEDATOS){
			try {
				motor.addAll(ciudades);
				motor.addAll(hospedajes );
				motor.addAll(tours);
			} catch (JessException e) {
				System.out.println("Error al agregar los datos");
				e.printStackTrace();
			}
			
		}else if(baseHechos==DESDEARCHIVO){
			try {
				motor.addAll(ciudades);
				motor.addAll(hospedajes );
				motor.addAll(tours);
			} catch (JessException e) {
				System.out.println("Error al agregar los datos");
				e.printStackTrace();
			}
		}
		
	}	
	
	public void cargarUsuario(){
		escribirEnJess("Cargando el hecho con los datos del usuario"); 
		if(usuario!=null){
			try {
				motor.add(usuario);
			} catch (JessException e) {
				escribir("Error al cargar los datos del usuario");
				e.printStackTrace();
			}
			
		}else{
			escribir("El usuario es nullo. Verifique si se ingresaron bien los datos");
		}
		
	};
	
	public void cargarListas(){
		ciudades=bd.cargarCiudades();
		hospedajes=bd.cargarHospedajes();
		tours=bd.cargarTours();
		
	}
	
	public Tour getTourElegido() {
		int codigoTour=-1;
		Tour tourElegido=null;
		boolean encontrado=false;
		
		try {
			codigoTour=motor.fetch("codigo_tour").intValue(motor.getGlobalContext());
		} catch (JessException e) {
			escribir("no se encontro un codigo de tour valido");
			e.printStackTrace();
		}catch(NullPointerException e1){
			escribir("no se encontro un codigo de tour valido");
			e1.printStackTrace();
		}
		
		if(codigoTour!=-1){
			int i=0;
			while(!encontrado && i<tours.size()){
				if(tours.get(i).getCodigo()==codigoTour){
					encontrado=true;
					tourElegido=tours.get(i);
				}
				i++;
			}
			destinos=bd.cargarDestinosPorTour(codigoTour);
		}
		
		return tourElegido;
	}
	
	
	public Hospedaje getHospedajeElegido(){
		Hospedaje hospedajeElegido=new Hospedaje();
		
		if(usuario!=null){
		for(Hospedaje h:hospedajes)
			if(h.getClasificacion()==usuario.getTipoHospedaje())
				hospedajeElegido=h;
		}
		
		return hospedajeElegido;
		
	}
	
	
	public void escribir(String s){
		System.out.println(s);
	}
	
	public void escribirEnJess(String s){
		try {
			motor.eval("(printout t "+s+" crlf)");
		} catch (JessException e) {
			escribir("Error al escribir en al consola de Jess");
			e.printStackTrace();
		}
	}
	
	public Rete getMotor() {
		return motor;
	}

	public void setMotor(Rete motor) {
		this.motor = motor;
	}

	public Vector<String> getNombresCiudades() {
		Vector<String> lista=new Vector<String>();
		for(Ciudad c:ciudades){
			lista.add(c.getNombre());
		}
		return lista;
	}
	
	public WorkingMemoryMarker getMarca() {
		return marca;
	}

	public void setMarca(WorkingMemoryMarker marca) {
		this.marca = marca;
	}

	public BaseDeDatos getBd() {
		return bd;
	}

	public void setBd(BaseDeDatos bd) {
		this.bd = bd;
	}

	public int getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(int estrategia) {
		this.estrategia = estrategia;
	}

	public int getEncadenamiento() {
		return encadenamiento;
	}

	public void setEncadenamiento(int encadenamiento) {
		this.encadenamiento = encadenamiento;
	}

	public int getBaseHechos() {
		return baseHechos;
	}

	public void setBaseHechos(int baseHechos) {
		this.baseHechos = baseHechos;
	}

	public int getBaseConocimiento() {
		return baseConocimiento;
	}

	public void setBaseConocimiento(int baseConocimiento) {
		this.baseConocimiento = baseConocimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Console getConsola() {
		return consola;
	}

	public void setConsola(Console consola) {
		this.consola = consola;
	}
	
	public ConsolePanel getConsolePanel(){
		return consolePanel;
	}

	public void setConsolePanel(ConsolePanel consolePanel) {
		this.consolePanel = consolePanel;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public List<Hospedaje> getHospedajes() {
		return hospedajes;
	}

	public void setHospedajes(List<Hospedaje> hospedajes) {
		this.hospedajes = hospedajes;
	}

	public List<Destino> getDestinos() {
		return destinos;
	}

	public void setDestinos(List<Destino> destinos) {
		this.destinos = destinos;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}

	
	

}
