package se.negocio.beans;

import java.io.Serializable;

public class Ciudad implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5686414864212318529L;
	private int codigo;
	private String nombre;
	private int costo;
	
	
	public Ciudad(int codigo, String nombre, int costo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.costo = costo;
	}
	
	public Ciudad() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
}
