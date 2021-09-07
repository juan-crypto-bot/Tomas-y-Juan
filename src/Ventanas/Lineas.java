package Ventanas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Dominio.Estacion;
import Logica.EstacionesService;
import Logica.LineasService;
import Logica.RutaService;
import Logica.TrayectoService;

public class Lineas extends JFrame{

	
	private GridBagConstraints gbcLineas;
	LineasService ls = new LineasService();
	TrayectoService ts = new TrayectoService();
	EstacionesService es = new EstacionesService();
	RutaService rs = new RutaService();
	String nomLinea = null;
	String colLinea = null;
	String estLinea = null;
	Integer valor = -1;
	String nomEstacion = null;
	String apEstacion = null;
	String cieEstacion = null;
	String estEstacion = null;
	List<List<String>> estRecorrer = new ArrayList<List<String>>();
	DefaultListModel modeloLista = new DefaultListModel();
	JList mostrar = new JList(modeloLista);
	
	public Lineas() {
		this.gbcLineas = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
	}
	
	
	public JButton armarBotonLineas(App app) {
		JButton lineas = new JButton("Lineas");
		JPanel panel = new JPanel(new GridBagLayout());
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 0;
		panel.add(lineas, gbcLineas);
		
		lineas.addActionListener(e -> {
		this.armarBusquedaLineas(app);
		this.revalidate();
		this.repaint();
		});
		
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
		
		return lineas;
	}
		 
	public void armarNuevaLinea(App app) {
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.LIGHT_GRAY);
		JLabel nombre = new JLabel("Nombre");
		JLabel color = new JLabel("Color");
		JLabel estado = new JLabel("Estado");
		JTextField nombretexto = new JTextField(20);
		JTextField colortexto = new JTextField(10);
		JTextField estadotexto = new JTextField("Activa");
		JButton alta = new JButton("Dar de Alta");
		JButton atras = new JButton("Atras");
	
		
		atras.addActionListener(e -> {
			this.armarBusquedaLineas(app);
			this.revalidate();
			this.repaint();
		});
		
		alta.addActionListener(e -> {
			if(!nombretexto.getText().isEmpty() && !colortexto.getText().isEmpty() && !estadotexto.getText().isEmpty()){
				
				try {
					ls.darDeAltaLinea(nombretexto.getText(), colortexto.getText(), estadotexto.getText());
					JOptionPane.showMessageDialog(null, "Linea cargada correctamente");
					this.armarBusquedaLineas(app);
					this.revalidate();
					this.repaint();
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");
					this.armarNuevaLinea(app);
					this.revalidate();
					this.repaint();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");
				this.armarNuevaLinea(app);
				this.revalidate();
				this.repaint();
			}
		});
		
		
		gbcLineas.anchor = GridBagConstraints.WEST;
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 0;
		gbcLineas.ipadx = 10;
		panel.add(nombre, gbcLineas);
		gbcLineas.ipadx = 0;
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 0;
		panel.add(nombretexto, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 1;
		gbcLineas.ipadx = 10;
		panel.add(color, gbcLineas);
		gbcLineas.ipadx = 0;
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 1;
		panel.add(colortexto, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 2;
		gbcLineas.ipadx = 10;
		panel.add(estado, gbcLineas);
		gbcLineas.ipadx = 0;
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 2;
		panel.add(estadotexto, gbcLineas);
		
		
		gbcLineas.anchor = GridBagConstraints.WEST;
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 5;
		panel.add(atras, gbcLineas);
		
		gbcLineas.anchor = GridBagConstraints.EAST;
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 5;
		panel.add(alta, gbcLineas);
		
		
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
		
	}
	
	private JTable dibujarTablaLineas(App app) {
	
	DefaultTableModel modelo = new DefaultTableModel();	
	
	modelo.addColumn("Nombre");
	modelo.addColumn("Color");
	modelo.addColumn("Estado");
	
	JTable tablaLineas = new JTable(modelo);
	
	/*TableRowSorter<TableModel> ordenador=new TableRowSorter<TableModel>(modelo);
	tablaPlantas.setRowSorter(ordenador);*/
	
	TableColumnModel modeloColumna = tablaLineas.getColumnModel();
	modeloColumna.getColumn(0).setPreferredWidth(240);
	modeloColumna.getColumn(1).setPreferredWidth(240);
	modeloColumna.getColumn(2).setPreferredWidth(240);
	
	try {
		ls.buscarLineas(modelo);
	} catch(NullPointerException e) {
		System.out.println(e);
	}

	
		ListSelectionModel seleccion = tablaLineas.getSelectionModel();
		seleccion.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()) return; 
				
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if(!lsm.isSelectionEmpty()) { 
						nomLinea = ((String)modelo.getValueAt(lsm.getMaxSelectionIndex(), 0));
						colLinea = ((String)modelo.getValueAt(lsm.getMaxSelectionIndex(), 1));
						estLinea = ((String)modelo.getValueAt(lsm.getMaxSelectionIndex(), 2));
					}
				}
		});
		
	return tablaLineas;
}

	public void armarBusquedaLineas(App app) {
	JPanel panel = new JPanel(new GridBagLayout());
	panel.setBackground(Color.LIGHT_GRAY);
	JScrollPane scrollEstaciones=new JScrollPane();
	JButton atras = new JButton("Atras");
	JButton editar = new JButton("Editar");
	JButton eliminar = new JButton("Eliminar");
	JTable tablaEstaciones = this.dibujarTablaLineas(app);
	scrollEstaciones.setViewportView(tablaEstaciones);
	JButton alta = new JButton("Nueva linea");

	gbcLineas.gridx = 0;
	gbcLineas.gridy = 0;
	gbcLineas.gridwidth = 4;
	gbcLineas.gridheight = 1;
	gbcLineas.fill = GridBagConstraints.HORIZONTAL;
	gbcLineas.weightx = 1.0;
	panel.add(scrollEstaciones, gbcLineas);
	gbcLineas.weightx = 0.0;
	gbcLineas.gridwidth = 1;
	gbcLineas.fill = GridBagConstraints.NONE;
	
	gbcLineas.weightx = 1.0;
	gbcLineas.weighty = 1.0;
	gbcLineas.gridx = 0;
	gbcLineas.gridy = 1;
	panel.add(atras, gbcLineas);

	gbcLineas.gridx = 1;
	gbcLineas.gridy = 1;
	panel.add(alta, gbcLineas);
	
	gbcLineas.gridx = 2;
	gbcLineas.gridy = 1;
	panel.add(eliminar, gbcLineas);

	gbcLineas.gridx = 3;
	gbcLineas.gridy = 1;
	panel.add(editar, gbcLineas);
	gbcLineas.weightx = 0.0;
	gbcLineas.weighty = 0.0;
	gbcLineas.anchor = GridBagConstraints.CENTER;
	
	Principal prin = new Principal();
	
	atras.addActionListener(e -> {
		prin.armarVentanaPrincipal(app);
		this.revalidate();
		this.repaint();
	});
	
	alta.addActionListener(e -> {
		this.armarNuevaLinea(app);
		this.revalidate();
		this.repaint();
	});
	
	eliminar.addActionListener(f -> { 
		//aviso.showConfirmDialog(null, "Seguro que quiere eliminar la estacion seleccionada?");
		try {
			rs.eliminarRutaPorLinea(nomLinea);
			ls.eliminarLinea(nomLinea);
			this.armarBusquedaLineas(app);
			this.revalidate();
			this.repaint();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
	
	editar.addActionListener(f -> {
		this.armarEdicionLineas(app, nomLinea, colLinea, estLinea);
		this.revalidate();
		this.repaint();
});
	
	app.setContentPane(panel);
	app.revalidate();
	app.repaint();
	
}

	public void armarEdicionLineas(App app, String nom, String col, String est) {
	
	JPanel panel = new JPanel(new GridBagLayout());
	panel.setBackground(Color.LIGHT_GRAY);
	JLabel nombre = new JLabel("Nombre");
	JLabel color = new JLabel("Color");
	JLabel estado = new JLabel("Estado");
	JTextField nombretexto = new JTextField(20);
	JTextField colortexto = new JTextField(10);
	JComboBox<EstadoLinea> estadocombo = new JComboBox<EstadoLinea>();
	estadocombo.addItem(EstadoLinea.Activa);
	estadocombo.addItem(EstadoLinea.NoActiva);
	JButton cancelar = new JButton("Cancelar");
	JButton aceptar = new JButton("Aceptar");
	JButton trayecto = new JButton("Definir trayecto");

	nombretexto.setText(nom);
	nombretexto.setEditable(false);
	colortexto.setText(col);
	
	cancelar.addActionListener(e -> {
		this.armarBusquedaLineas(app);
		this.revalidate();
		this.repaint();
	});
	
	trayecto.addActionListener(e -> {
		this.definirTrayecto(app, nombretexto.getText(), colortexto.getText(), estadocombo.getSelectedItem().toString());
		this.revalidate();
		this.repaint();
	});
	
	aceptar.addActionListener(e -> {
		if(!nombretexto.getText().isEmpty() && !color.getText().isEmpty()){
			
			JOptionPane.showMessageDialog(null, "Linea editada correctamente");
			try {
				ls.editarLinea(nombretexto.getText(), colortexto.getText(), estadocombo.getSelectedItem().toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.armarBusquedaLineas(app);
			this.revalidate();
			this.repaint();
			}
		
		else {
			JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");
			this.armarEdicionLineas(app, nom, col, est);
			this.revalidate();
			this.repaint();
		}
	});
			
	gbcLineas.anchor = GridBagConstraints.WEST;
	gbcLineas.gridx = 0;
	gbcLineas.gridy = 0;
	gbcLineas.ipadx = 10;
	panel.add(nombre, gbcLineas);
	gbcLineas.ipadx = 0;
	
	gbcLineas.gridx = 1;
	gbcLineas.gridy = 0;
	panel.add(nombretexto, gbcLineas);
	
	gbcLineas.gridx = 0;
	gbcLineas.gridy = 1;
	gbcLineas.ipadx = 10;
	panel.add(color, gbcLineas);
	gbcLineas.ipadx = 0;
	
	gbcLineas.gridx = 1;
	gbcLineas.gridy = 1;
	panel.add(colortexto, gbcLineas);
	
	gbcLineas.gridx = 0;
	gbcLineas.gridy = 2;
	gbcLineas.ipadx = 10;
	panel.add(estado, gbcLineas);
	gbcLineas.ipadx = 0;
	
	gbcLineas.gridx = 1;
	gbcLineas.gridy = 2;
	panel.add(estadocombo, gbcLineas);

	gbcLineas.anchor = GridBagConstraints.CENTER;
	gbcLineas.gridx = 0;
	gbcLineas.gridy = 3;
	panel.add(cancelar, gbcLineas);
	
	gbcLineas.gridx = 1;
	gbcLineas.gridy = 3;
	panel.add(trayecto, gbcLineas);
	
	gbcLineas.gridx = 2;
	gbcLineas.gridy = 3;
	panel.add(aceptar, gbcLineas);
	
	
	app.setContentPane(panel);
	app.revalidate();
	app.repaint();
	
}

	public void definirTrayecto(App app, String nom, String col, String est) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.LIGHT_GRAY);
	
		JComboBox origencombo = new JComboBox();
		JComboBox destinocombo = new JComboBox();
		JButton cancelar = new JButton("Cancelar");
		JButton aceptar = new JButton("Aceptar");
		JLabel origen = new JLabel("Seleccionar Estacion Origen");
		JLabel destino = new JLabel("Seleccionar Estacion Destino");
		
		for(int i=0; i<es.estacionesId().size(); i++) {
			origencombo.addItem(es.estacionesId().get(i));
		}
		for(int i=0; i<es.estacionesId().size(); i++) {
			destinocombo.addItem(es.estacionesId().get(i));
		}
		
		cancelar.addActionListener(e -> {
			this.armarEdicionLineas(app, nom, col, est);
			this.revalidate();
			this.repaint();
		});
		
		aceptar.addActionListener(e -> {
			try {
				ls.editarLinea(nom, col, est);
				this.nuevaRuta(app, Integer.parseInt(origencombo.getSelectedItem().toString()), Integer.parseInt(destinocombo.getSelectedItem().toString()), nom, col, est, nomLinea);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//this.armarBusquedaLineas(app);
			this.revalidate();
			this.repaint();
		});
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 0;
		panel.add(origen, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 0;
		panel.add(destino, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 1;
		panel.add(origencombo, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 1;
		panel.add(destinocombo, gbcLineas);
		
		gbcLineas.weightx = 1.0;
		gbcLineas.weighty = 1.0;
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 2;
		panel.add(cancelar, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 2;
		panel.add(aceptar, gbcLineas);
		gbcLineas.weightx = 0.0;
		gbcLineas.weighty = 0.0;
		
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
	}
	
	public void nuevaRuta(App app, Integer ori, Integer des, String nom, String col, String est, String nomLinea) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel idtexto = new JLabel("Id");
		JLabel origentexto = new JLabel("Origen");
		JLabel destinotexto = new JLabel("Destino");
		JLabel distanciatexto = new JLabel("Distancia en KM");
		JLabel duraciontexto = new JLabel("Duracion en Min");
		JLabel capacidadtexto = new JLabel("Capacidad");
		JLabel estadotexto = new JLabel("Estado");
		JLabel costotexto = new JLabel("Costo");
		JTextField id = new JTextField(5);
		JTextField origen = new JTextField(5);
		JTextField destino = new JTextField(5);
		JTextField distancia = new JTextField(5);
		JTextField duracion = new JTextField(5);
		JTextField capacidad = new JTextField(5);
		JComboBox estadocombo = new JComboBox();
		JTextField costo = new JTextField(5);
		
		JButton atras = new JButton("Atras");
		JButton aceptar = new JButton("Aceptar");
		
		origen.setText(ori.toString());
		destino.setText(des.toString());
		
		estadocombo.addItem("Activa");
		estadocombo.addItem("Inactiva");
		
		atras.addActionListener(e -> {
			this.definirTrayecto(app, nom, col, est);
			this.revalidate();
			this.repaint();
		});
				
		aceptar.addActionListener(e -> {
			if(!id.getText().isEmpty() && !distancia.getText().isEmpty() && !duracion.getText().isEmpty() && 
				!capacidad.getText().isEmpty() && !costo.getText().isEmpty()) {
				try {
					rs.darDeAltaRuta(Integer.parseInt(id.getText()), ori, des, Double.parseDouble(distancia.getText()), Integer.parseInt(duracion.getText()), Integer.parseInt(capacidad.getText()), estadocombo.getSelectedItem().toString(), Double.parseDouble(costo.getText()), nomLinea);
					JOptionPane.showMessageDialog(null, "Ruta cargada correctamente");
					this.armarBusquedaLineas(app);
					this.revalidate();
					this.repaint();
				} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");
					this.nuevaRuta(app, ori, des, nom, col, est, nomLinea);
					this.revalidate();
					this.repaint();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Datos faltantes o fuera de rango");
				this.nuevaRuta(app, ori, des, nom, col, est, nomLinea);
				this.revalidate();
				this.repaint();
			}
		});
		
		gbcLineas.anchor = GridBagConstraints.WEST;
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 0;
		gbcLineas.ipadx = 10;
		panel.add(idtexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 0;
		panel.add(id, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 1;
		gbcLineas.ipadx = 10;
		panel.add(origentexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 1;
		panel.add(origen, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 2;
		gbcLineas.ipadx = 10;
		panel.add(destinotexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 2;
		panel.add(destino, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 3;
		gbcLineas.ipadx = 10;
		panel.add(distanciatexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 3;
		panel.add(distancia, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 4;
		gbcLineas.ipadx = 10;
		panel.add(duraciontexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 4;
		panel.add(duracion, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 5;
		gbcLineas.ipadx = 10;
		panel.add(capacidadtexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 5;
		panel.add(capacidad, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 6;
		gbcLineas.ipadx = 10;
		panel.add(costotexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 6;
		panel.add(costo, gbcLineas);
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 7;
		gbcLineas.ipadx = 10;
		panel.add(estadotexto, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 7;
		panel.add(estadocombo, gbcLineas);
		gbcLineas.anchor = GridBagConstraints.CENTER;
		
		gbcLineas.gridx = 0;
		gbcLineas.gridy = 8;
		panel.add(atras, gbcLineas);
		
		gbcLineas.gridx = 1;
		gbcLineas.gridy = 8;
		panel.add(aceptar, gbcLineas);
		
		app.setContentPane(panel);
		app.revalidate();
		app.repaint();
	}
}
