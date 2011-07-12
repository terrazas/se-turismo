package se.negocio.beans;

import java.io.Serializable;
import java.util.Vector;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6910790425177780405L;
	
	
	private String nombre;
	private String ciudadOrigen;
	private int tipoHospedaje;
	private int presupuesto;
	private String[] preferencias;
	

	public Usuario(String nombre, String ciudadOrigen, int tipoHospedaje,
			int presupuesto, String[] preferencias) {
		
		this.nombre = nombre;
		this.ciudadOrigen = ciudadOrigen;
		this.tipoHospedaje = tipoHospedaje;
		this.presupuesto = presupuesto;
		this.preferencias = preferencias;
	}

	public Usuario() {
		
	}

	public int getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudadOrigen() {
		return ciudadOrigen;
	}
	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}
	public int getTipoHospedaje() {
		return tipoHospedaje;
	}
	public void setTipoHospedaje(int tipoHospedaje) {
		this.tipoHospedaje = tipoHospedaje;
	}


	public String[] getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String[] preferencias) {
		this.preferencias = preferencias;
	}

	

}
