package se.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.negocio.beans.Ciudad;
import se.negocio.beans.Destino;
import se.negocio.beans.Hospedaje;
import se.negocio.beans.Tour;
import se.negocio.beans.Usuario;

public class BaseDeDatos {
	Connection con;
	Statement statement;
	ResultSet resultSet;
	
	public BaseDeDatos() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Error al cargar el driver de HSQLDB");
			
		} 
		
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:db/seturismodb");
			statement=con.createStatement();
		
		} catch (SQLException e) {
			System.out.println();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			resultSet=statement.executeQuery("select * from information_schema.tables where table_schema='PUBLIC';");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(!resultSet.next()){
				System.out.println("No haY tabLas creadas");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public List<Ciudad> cargarCiudades(){
		List<Ciudad> ciudades=new ArrayList<Ciudad>();
		ResultSet rs;

		try {
			rs=statement.executeQuery("Select * from ciudad;");
			while(rs.next()){
				ciudades.add(new Ciudad(rs.getInt("COD_CIUDAD"),
										rs.getString("NOMBRE"),
										rs.getInt("COSTO")));
			}
		} catch (SQLException e) {
			System.out.println("Error cargando ciudades");
			e.printStackTrace();
		}
		
		return ciudades;
	}
	
	public List<Hospedaje> cargarHospedajes(){
		List<Hospedaje> hospedajes=new ArrayList<Hospedaje>();
		ResultSet rs;

		try {
			rs=statement.executeQuery("Select * from hospedaje;");
			while(rs.next()){
				hospedajes.add(new Hospedaje(rs.getInt("COD_HOSPEDAJE"),
										rs.getString("NOMBRE"),
										rs.getString("DIRECCION"),
										rs.getInt("CLASIFICACION"),
										rs.getInt("COSTO")));
			}
		} catch (SQLException e) {
			System.out.println("Error cargando hospedajes");
			e.printStackTrace();
		}
		
		return hospedajes;
	}
	
	public List<Destino> cargarDestinos(){
		List<Destino> destinos=new ArrayList<Destino>();
		ResultSet rs;

		try {
			rs=statement.executeQuery("Select * from destino;");
			while(rs.next()){
				destinos.add(new Destino(rs.getInt("COD_DESTINO"),
										rs.getString("NOMBRE_DESTINO"),
										rs.getString("DESCRIPCION")));
			}
		} catch (SQLException e) {
			System.out.println("Error cargando destinos");
			e.printStackTrace();
		}
		
		return destinos;
	}
	
	public List<Destino> cargarDestinosPorTour(int codigoTour){
		List<Destino> destinos=new ArrayList<Destino>();
		ResultSet rs;

		try {
			rs=statement.executeQuery("Select * from destino d where d.cod_destino in (select dt.cod_destino from destinos_tour dt where dt.cod_tour="+codigoTour+");");
			while(rs.next()){
				destinos.add(new Destino(rs.getInt("COD_DESTINO"),
										rs.getString("NOMBRE_DESTINO"),
										rs.getString("DESCRIPCION")));
			}
		} catch (SQLException e) {
			System.out.println("Error cargando destinos");
			e.printStackTrace();
		}
		
		return destinos;
	}
	
	public List<Tour> cargarTours(){
		List<Tour> tours=new ArrayList<Tour>();
		ResultSet rs;

		try {
			rs=statement.executeQuery("Select * from tour;");
			while(rs.next()){
				tours.add(new Tour(rs.getInt("COD_TOUR"),
										rs.getString("NOMBRE_TOUR"),
										rs.getString("DESCRIPCION"),
										rs.getString("PREFERENCIAS").split(","),
										rs.getInt("COSTO")
										));
			}
		} catch (SQLException e) {
			System.out.println("Error cargando tours");
			e.printStackTrace();
		}
		
		return tours;
	}
	
	

}
