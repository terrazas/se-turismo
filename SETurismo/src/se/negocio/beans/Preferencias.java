package se.negocio.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import jess.Rete;

public class Preferencias implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6451326620636367034L;

	private int historico=0;
	private int montanismo=0;
	private int naturaleza=0;
	private int gastronomia=0;
	private int canotaje=0;
	private int ciclismo=0;
	private int fiestas=0;
	
	public Preferencias() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getHistorico() {
		return historico;
	}

	public void setHistorico(int historico) {
		this.historico = historico;
	}

	public int getMontanismo() {
		return montanismo;
	}

	public void setMontanismo(int montanismo) {
		this.montanismo = montanismo;
	}

	public int getGastronomia() {
		return gastronomia;
	}

	public void setGastronomia(int gastronomia) {
		this.gastronomia = gastronomia;
	}

	public int getCanotaje() {
		return canotaje;
	}

	public void setCanotaje(int canotaje) {
		this.canotaje = canotaje;
	}

	public int getCiclismo() {
		return ciclismo;
	}

	public void setCiclismo(int ciclismo) {
		this.ciclismo = ciclismo;
	}

	public int getFiestas() {
		return fiestas;
	}

	public void setFiestas(int fiestas) {
		this.fiestas = fiestas;
	}

	public int getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(int naturaleza) {
		this.naturaleza = naturaleza;
	}

	public Preferencias(int historico, int montanismo, int naturaleza,
			int gastronomia, int canotaje, int ciclismo, int fiestas) {
		super();
		this.historico = historico;
		this.montanismo = montanismo;
		this.naturaleza = naturaleza;
		this.gastronomia = gastronomia;
		this.canotaje = canotaje;
		this.ciclismo = ciclismo;
		this.fiestas = fiestas;
	}
	
	
	
	

	
}
