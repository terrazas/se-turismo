package se.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextPane;

import se.negocio.beans.Destino;
import se.negocio.beans.Hospedaje;
import se.negocio.beans.Tour;
import javax.swing.JScrollPane;

public class PaqueteTuristico extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public PaqueteTuristico(Tour tour,List<Destino> destinos,Hospedaje hospedaje) {
		setResizable(false);
		setTitle("Paquete Turistico");
		setBounds(100, 100, 450, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPaqueteTuristicoSugerido = new JLabel("Paquete Turistico Sugerido");
		lblPaqueteTuristicoSugerido.setFont(new Font("Verdana", Font.BOLD, 16));
		lblPaqueteTuristicoSugerido.setBounds(10, 11, 245, 21);
		contentPane.add(lblPaqueteTuristicoSugerido);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 424, 227);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		
		String texto="De acuerdo a sus preferencias el sistema ha seleccionado el siguiente tour.\n";
		texto+=tour.getNombre()+"\n";			
		texto+=tour.getDescripcion()+"\n";
		texto+="Costo: "+tour.getCosto()+"\n";
		for(String s:tour.getPreferencias())
			texto+=s+" ";
		
		texto+="\n\nHOSPEDAJE\n\n";
		
		texto+=hospedaje.getNombre()+"\n";
		texto+="Clasificacion: "+hospedaje.getClasificacion()+" estrellas"+"\n";
		texto+="Costo: "+hospedaje.getCosto()+"\n";
		texto+="Direccion: "+hospedaje.getDireccion()+"\n";
		
		texto+="\nDESTINOS\n";		
		
		for(Destino d:destinos)
			texto+=d.getNombre()+"  "+d.getDescripcion()+"\n";
		
		textPane.setText(texto);
	}
}
