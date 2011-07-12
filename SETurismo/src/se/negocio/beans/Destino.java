package se.negocio.beans;

import java.io.Serializable;

public class Destino implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4002033574276538684L;
	
	
	int codigo;
	String nombre;
	String descripcion;
	
	public Destino() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Destino(int codigo, String nombre, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	
	
}
