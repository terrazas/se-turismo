package se.negocio.beans;

import java.io.Serializable;

public class Hospedaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7634656866373992100L;
	
	private int codigo;
	private String nombre;
	private String direccion;
	private int clasificacion;
	private int costo;
	
	
	public Hospedaje(int codigo, String nombre, String direccion,
			int clasificacion, int costo) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.clasificacion = clasificacion;
		this.costo = costo;
	}


	public Hospedaje() {
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


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getClasificacion() {
		return clasificacion;
	}


	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}


	public int getCosto() {
		return costo;
	}


	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	
	
	
}
