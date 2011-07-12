package se.negocio.beans;

import java.io.Serializable;
import java.util.Collection;

public class Tour implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8199525480634764183L;
	private int codigo;
	private String nombre;
	private String descripcion;
	private String[] preferencias;
	private int costo;
	
	public Tour() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tour(int codigo, String nombre, String descripcion,
			String[] preferencias, int costo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.preferencias = preferencias;
		this.costo = costo;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String[] getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(String[] preferencias) {
		this.preferencias = preferencias;
	}
	
	
}

