package se.presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import se.negocio.Sistema;
import se.negocio.beans.Destino;
import se.negocio.beans.Hospedaje;
import se.negocio.beans.Preferencias;
import se.negocio.beans.Tour;
import se.negocio.beans.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

import jess.Deffacts;
import jess.JessException;
import javax.swing.DefaultComboBoxModel;

import com.sun.jmx.snmp.tasks.Task;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Base extends JFrame implements Observer{

	private JPanel contentPane;
	private Sistema sistema;
	private JTextField txtNombre;
	private JTextField txtPresupuesto;

	private JCheckBox chckbxHistorico; 
	private JCheckBox chckbxNaturaleza; 
	private JCheckBox chckbxMontaismo; 
	private JCheckBox chckbxCanotaje;
	private JCheckBox chckbxTrekking; 
	private JCheckBox chckbxFiestas;
	private JCheckBox chckbxGastronoma;
	
	private JRadioButtonMenuItem rdbtnmntmProgresivo;
	private JRadioButtonMenuItem rdbtnmntmRegresivo;
	
	private JRadioButtonMenuItem rdbtnmntmDepth;
	private JRadioButtonMenuItem rdbtnmntmBreadth;
	
	private JCheckBoxMenuItem chckbxmntmDesdeArchivo;
	private JCheckBoxMenuItem chckbxmntmDesdeBaseDe_1; 
	
	private JCheckBoxMenuItem chckbxmntmDesdeArchivoclp;
	private JCheckBoxMenuItem chckbxmntmDesdeBaseDe; 
	
	private JLabel lblConsola; 
	private JLabel lblDatosDelUsuario;
	private JLabel lblNombre; 
	private JLabel lblCiudadDeOrigen; 
	private JLabel lblHospedaje;
	private JLabel lblPresupuesto;
	private JLabel lblPreferencias;
	private JSeparator separator;
	private JComboBox comboCiudad; 
	private JComboBox comboHospedaje; 
	
	JTextArea textAreaConsola;
	
	/**
	 * Create the frame.
	 */
	public Base() {
		
		sistema=new Sistema(this);
		inicializar();
/*
		PrintStream con;
		try {
			con = new PrintStream(new TextAreaOutputStream(textAreaConsola));
			System.setOut(con);
			System.setErr(con);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
				
		setResizable(false);
		setTitle("Sistema Experto en Turismo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 330);
		
	}
	
	public void inicializar(){
/////////////////  Barra de Menu  /////////////////  
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		///////////////  SubMenu Sistema  /////////////////
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmSugerirTour = new JMenuItem("Sugerir Paquete (Resolver)");
		mntmSugerirTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validar()){
					sistema.ejecutar(cargarUsuario());
				}
			}
		});
		mnSistema.add(mntmSugerirTour);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmMostrarConsola = new JMenuItem("Mostrar Consola");
		mntmMostrarConsola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConsola.setVisible(true);
				sistema.getConsolePanel().setVisible(true);
				setSize(getWidth(),550);
			}
		});
		
		
		JMenuItem mntmOcultarConsola = new JMenuItem("Ocultar Consola");
		mntmOcultarConsola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConsola.setVisible(false);
				sistema.getConsolePanel().setVisible(false);
				setSize(getWidth(),330);
			}
		});
		
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConsola.setVisible(false);
				sistema.getConsolePanel().setVisible(false);
				setBounds(100, 100, 481, 350);
			}
		});
		
		mnSistema.add(mntmMostrarConsola);
		mnSistema.add(mntmOcultarConsola);
		mnSistema.add(mntmSalir);
		
		/////////////////  SubMenu Encadenamiento  ////////////////
		
		JMenu mnEncadenamiento = new JMenu("Encadenamiento");
		mnEncadenamiento.setToolTipText("Metodo de encadenamiento");
		menuBar.add(mnEncadenamiento);

		
		rdbtnmntmProgresivo = new JRadioButtonMenuItem("Progresivo");
		rdbtnmntmProgresivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.setEncadenamiento(sistema.PROGRESIVO);
				rdbtnmntmRegresivo.setSelected(false);
			}
		});
		mnEncadenamiento.add(rdbtnmntmProgresivo);
		
		/*Por defecto*/
		rdbtnmntmProgresivo.setSelected(true);
		sistema.setEncadenamiento(sistema.PROGRESIVO);
		/* ******** */
		
		
		
		rdbtnmntmRegresivo = new JRadioButtonMenuItem("Regresivo");
		rdbtnmntmRegresivo.setEnabled(false);
		rdbtnmntmRegresivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.setEncadenamiento(sistema.REGRESIVO);
				rdbtnmntmProgresivo.setSelected(false);
			}
		});
		mnEncadenamiento.add(rdbtnmntmRegresivo);
		

		/////////////////  SubMenu Estrategia ////////////////
		
		JMenu mnEstrategia = new JMenu("Estrategia");
		mnEstrategia.setToolTipText("Estrategia de solicion de conflictos");
		menuBar.add(mnEstrategia);
		
		rdbtnmntmDepth = new JRadioButtonMenuItem("Depth (Default)");
		rdbtnmntmDepth.setSelected(true);
		rdbtnmntmDepth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.setEstrategia(sistema.DEPTH);
				rdbtnmntmBreadth.setSelected(false);
			}
		});
		mnEstrategia.add(rdbtnmntmDepth);
		
		/*Por defecto*/
		rdbtnmntmDepth.setSelected(true);
		sistema.setEstrategia(sistema.DEPTH);
		/* ******** */
		
		
		rdbtnmntmBreadth = new JRadioButtonMenuItem("Breadth");
		rdbtnmntmBreadth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.setEstrategia(sistema.BREADTH);
				rdbtnmntmDepth.setSelected(false);
			}
		});
		mnEstrategia.add(rdbtnmntmBreadth);
		
		/////////////////  SubMenu Datos ////////////////
		JMenu mnDatos = new JMenu("Datos");
		mnDatos.setToolTipText("Cargar los datos necesarios para el SE");
		menuBar.add(mnDatos);
		
		chckbxmntmDesdeArchivo = new JCheckBoxMenuItem("Desde Archivo");
		chckbxmntmDesdeArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarFormulario();
				sistema.setBaseHechos(sistema.DESDEARCHIVO);
				chckbxmntmDesdeBaseDe_1.setSelected(false);
			}
		});
		
		mnDatos.add(chckbxmntmDesdeArchivo);
		
		chckbxmntmDesdeBaseDe_1 = new JCheckBoxMenuItem("Desde base de datos (HSQLDB)");
		chckbxmntmDesdeBaseDe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitarFormulario();
				sistema.setBaseHechos(sistema.BASEDEDATOS);
				chckbxmntmDesdeArchivo.setSelected(false);
			}
		});
		mnDatos.add(chckbxmntmDesdeBaseDe_1);
		
		/*Por defecto*/
		chckbxmntmDesdeBaseDe_1.setSelected(true);
		sistema.setBaseHechos(sistema.BASEDEDATOS);
		/* ******** */
		
		/////////////////  SubMenu Base de Conocimiento ////////////////
		
		JMenu mnBaseDeConocimiento = new JMenu("Base de conocimiento");
		menuBar.add(mnBaseDeConocimiento);
		
		chckbxmntmDesdeArchivoclp = new JCheckBoxMenuItem("Desde archivo (*.clp)");
		chckbxmntmDesdeArchivoclp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.setBaseConocimiento(sistema.DESDEARCHIVO);
				chckbxmntmDesdeBaseDe.setSelected(false);
			}
		});
		mnBaseDeConocimiento.add(chckbxmntmDesdeArchivoclp);
		
		/*Por defecto*/
		chckbxmntmDesdeArchivoclp.setSelected(true);
		sistema.setBaseConocimiento(sistema.BASEDEDATOS);
		/* ******** */
		
		chckbxmntmDesdeBaseDe = new JCheckBoxMenuItem("Desde base de datos (HSQLDB)");
		chckbxmntmDesdeBaseDe.setEnabled(false);
		chckbxmntmDesdeBaseDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sistema.setBaseConocimiento(sistema.BASEDEDATOS);
				chckbxmntmDesdeArchivoclp.setSelected(false);
			}
		});
		mnBaseDeConocimiento.add(chckbxmntmDesdeBaseDe);
		

		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/////////////////  Formulario ////////////////
		
		lblDatosDelUsuario = new JLabel("Datos del Usuario");
		lblDatosDelUsuario.setBounds(151, 11, 188, 23);
		lblDatosDelUsuario.setFont(new Font("Verdana", Font.BOLD, 18));
		contentPane.add(lblDatosDelUsuario);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 49, 61, 18);
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 14));
		contentPane.add(lblNombre);
		
		lblCiudadDeOrigen = new JLabel("Ciudad de origen");
		lblCiudadDeOrigen.setBounds(10, 78, 133, 18);
		lblCiudadDeOrigen.setFont(new Font("Verdana", Font.BOLD, 14));
		contentPane.add(lblCiudadDeOrigen);
		
		lblHospedaje = new JLabel("Hospedaje");
		lblHospedaje.setBounds(10, 107, 82, 18);
		lblHospedaje.setFont(new Font("Verdana", Font.BOLD, 14));
		contentPane.add(lblHospedaje);
		
		lblPresupuesto = new JLabel("Presupuesto");
		lblPresupuesto.setBounds(10, 136, 97, 18);
		lblPresupuesto.setFont(new Font("Verdana", Font.BOLD, 14));
		contentPane.add(lblPresupuesto);
		
		lblPreferencias = new JLabel("Preferencias");
		lblPreferencias.setBounds(10, 172, 96, 18);
		lblPreferencias.setFont(new Font("Verdana", Font.BOLD, 14));
		contentPane.add(lblPreferencias);
		
		separator = new JSeparator();
		separator.setBounds(10, 165, 453, 2);
		contentPane.add(separator);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(166, 45, 202, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		comboCiudad = new JComboBox();
		comboCiudad.setBounds(166, 79, 133, 20);
		comboCiudad.setModel(new DefaultComboBoxModel(sistema.getNombresCiudades()));
		contentPane.add(comboCiudad);
		
		comboHospedaje = new JComboBox();
		comboHospedaje.setBounds(166, 108, 116, 20);
		comboHospedaje.setModel(new DefaultComboBoxModel(new String[] {"5 estrellas", "4 estrellas", "3 estrellas", "2 estrellas"}));
		contentPane.add(comboHospedaje);
		
		txtPresupuesto = new JTextField();
		txtPresupuesto.setBounds(166, 137, 67, 20);
		contentPane.add(txtPresupuesto);
		txtPresupuesto.setColumns(10);
		
		chckbxHistorico = new JCheckBox("Historico");
		chckbxHistorico.setBounds(10, 197, 76, 24);
		contentPane.add(chckbxHistorico);
		
		chckbxNaturaleza = new JCheckBox("Naturaleza");
		chckbxNaturaleza.setBounds(88, 197, 86, 24);
		contentPane.add(chckbxNaturaleza);
		
		chckbxMontaismo = new JCheckBox("Monta\u00F1ismo");
		chckbxMontaismo.setBounds(176, 197, 95, 24);
		contentPane.add(chckbxMontaismo);
		
		chckbxCanotaje = new JCheckBox("Canotaje");
		chckbxCanotaje.setBounds(10, 224, 75, 24);
		contentPane.add(chckbxCanotaje);
		
		chckbxTrekking = new JCheckBox("Trekking");
		chckbxTrekking.setBounds(88, 224, 74, 24);
		contentPane.add(chckbxTrekking);
		
		chckbxFiestas = new JCheckBox("Fiestas");
		chckbxFiestas.setBounds(177, 224, 66, 24);
		contentPane.add(chckbxFiestas);
		
		chckbxGastronoma = new JCheckBox("Gastronom\u00EDa");
		chckbxGastronoma.setBounds(270, 197, 98, 24);
		contentPane.add(chckbxGastronoma);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 255, 453, 2);
		contentPane.add(separator_1);
		
		lblConsola = new JLabel("Consola");
		lblConsola.setBounds(10, 261, 62, 18);
		lblConsola.setFont(new Font("Verdana", Font.BOLD, 14));
		lblConsola.setVisible(false);
		contentPane.add(lblConsola);
		
		sistema.getConsolePanel().setBounds(10, 281, 454, 200);
		sistema.getConsolePanel().setVisible(false);
		contentPane.add(sistema.getConsolePanel());
	
	}
	
	public Usuario cargarUsuario(){
		
		String preferencias="";
			
		if(chckbxHistorico.isSelected())preferencias+="HISTORICO,";
		if(chckbxNaturaleza.isSelected())preferencias+="NATURALEZA,";
		if(chckbxMontaismo.isSelected())preferencias+="MONTANISMO,";
		if(chckbxCanotaje.isSelected())preferencias+="CANOTAJE,";
		if(chckbxTrekking.isSelected())preferencias+="TREKKING,";
		if(chckbxFiestas.isSelected())preferencias+="FIESTAS,";
		if(chckbxGastronoma.isSelected())preferencias+="GASTRONOMIA,";
		
		
		
		Usuario usuario= new Usuario(txtNombre.getText(),
									  String.valueOf(comboCiudad.getSelectedItem()),
									  Integer.valueOf(String.valueOf(comboHospedaje.getSelectedItem()).substring(0,1)),
									  Integer.valueOf(txtPresupuesto.getText()),
									  preferencias.split(",")
									  );
		return usuario;
	}
	
	public boolean validar(){
		
		
		if(txtNombre.getText().length()>0 && txtPresupuesto.getText().length()>0){
			if(chckbxCanotaje.isSelected() || chckbxTrekking.isSelected() || chckbxFiestas.isSelected() ||
			   chckbxGastronoma.isSelected() || chckbxHistorico.isSelected() || chckbxMontaismo.isSelected() || chckbxNaturaleza.isSelected())
			return true;
			else{
				JOptionPane.showMessageDialog(this,
						 "¡Seleccione al menos una preferencia!",
						  "Cuidado",
						  JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else{
			JOptionPane.showMessageDialog(this,
					 					  "¡Ingrese los datos del usuario!",
										  "Cuidado",
										  JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	
	public void deshabilitarFormulario(){
		chckbxHistorico.setEnabled(false); 
		chckbxNaturaleza.setEnabled(false); 
		chckbxMontaismo.setEnabled(false); 
		chckbxCanotaje.setEnabled(false);
		chckbxTrekking.setEnabled(false); 
		chckbxFiestas.setEnabled(false);
		chckbxGastronoma.setEnabled(false);
		comboCiudad.setEnabled(false); 
		comboHospedaje.setEnabled(false);
		txtNombre.setEnabled(false);
		txtPresupuesto.setEnabled(false);
		
	}
	
	public void habilitarFormulario(){
		chckbxHistorico.setEnabled(true); 
		chckbxNaturaleza.setEnabled(true); 
		chckbxMontaismo.setEnabled(true); 
		chckbxCanotaje.setEnabled(true);
		chckbxTrekking.setEnabled(true); 
		chckbxFiestas.setEnabled(true);
		chckbxGastronoma.setEnabled(true);
		comboCiudad.setEnabled(true); 
		comboHospedaje.setEnabled(true);
		txtNombre.setEnabled(true);
		txtPresupuesto.setEnabled(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o.equals(sistema)){

			Tour tour=sistema.getTourElegido();
			Hospedaje hospedaje=sistema.getHospedajeElegido();
			//Tour tour=null;
			
			if(tour!=null){
				List<Destino> destinos=sistema.getDestinos();
				PaqueteTuristico paquete=new PaqueteTuristico(tour,destinos,hospedaje);
				paquete.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(this,"No se encontro un paquete turistico que coincida con sus preferencias");
			}
	
		}
	}

	public Sistema getSistema() {
		return sistema;
	}
	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public JCheckBox getChckbxHistorico() {
		return chckbxHistorico;
	}

	public void setChckbxHistorico(JCheckBox chckbxHistorico) {
		this.chckbxHistorico = chckbxHistorico;
	}

	public JCheckBox getChckbxNaturaleza() {
		return chckbxNaturaleza;
	}

	public void setChckbxNaturaleza(JCheckBox chckbxNaturaleza) {
		this.chckbxNaturaleza = chckbxNaturaleza;
	}

	public JCheckBox getChckbxMontaismo() {
		return chckbxMontaismo;
	}

	public void setChckbxMontaismo(JCheckBox chckbxMontaismo) {
		this.chckbxMontaismo = chckbxMontaismo;
	}

	public JCheckBox getChckbxCanotaje() {
		return chckbxCanotaje;
	}

	public void setChckbxCanotaje(JCheckBox chckbxCanotaje) {
		this.chckbxCanotaje = chckbxCanotaje;
	}

	public JCheckBox getChckbxCiclismo() {
		return chckbxTrekking;
	}

	public void setChckbxCiclismo(JCheckBox chckbxCiclismo) {
		this.chckbxTrekking = chckbxCiclismo;
	}

	public JCheckBox getChckbxFiestas() {
		return chckbxFiestas;
	}

	public void setChckbxFiestas(JCheckBox chckbxFiestas) {
		this.chckbxFiestas = chckbxFiestas;
	}

	public JCheckBox getChckbxGastronoma() {
		return chckbxGastronoma;
	}

	public void setChckbxGastronoma(JCheckBox chckbxGastronoma) {
		this.chckbxGastronoma = chckbxGastronoma;
	}
}
