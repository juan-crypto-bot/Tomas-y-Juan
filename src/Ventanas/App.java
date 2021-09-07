package Ventanas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Dominio.Estacion;
import Dominio.EstadoRuta;
import Dominio.Ruta;


public class App extends JFrame{
	
	private GridBagConstraints gbc;	
		
	private JLabel labelUsuario;
	private JLabel labelContraseña;
	private JTextField textoUsuario;
	private JPasswordField textoContraseña;	
	private JLabel labelIngresar;
	private JButton ingresar;
	public Principal prin;
		
		public App() {
			this.gbc = new GridBagConstraints();
			this.setLayout(new GridBagLayout());
		}
		
		public void correrApp() {
			this.setBackground(Color.LIGHT_GRAY);
			this.labelUsuario = new JLabel("Usuario"); 
			this.labelContraseña = new JLabel("Contraseña"); 
			this.textoUsuario = new JTextField(20);
			this.textoContraseña = new JPasswordField(20);
			this.labelIngresar = new JLabel("Iniciar sesión");
			this.ingresar = new JButton("Ingresar");
			Principal prin = new Principal();
			JLabel aviso = new JLabel("Usuario: Admin, Contraseña:admin");
		
			
			gbc.gridx = 1;
			gbc.gridy = 0;
			//gbc.anchor = GridBagConstraints.CENTER;
			this.add(aviso, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.ipady = 20;
			this.add(labelIngresar, gbc);
			gbc.ipady = 0;
			
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.ipadx = 10;
			this.add(labelUsuario, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 2;
			this.add(textoUsuario, gbc);
			

			gbc.gridx = 0;
			gbc.gridy = 3;
			this.add(labelContraseña, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 3;
			this.add(textoContraseña, gbc);
				
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = 4;
			this.add(ingresar, gbc);
			
			
			ingresar.addActionListener(e -> {
				//if(textoUsuario.getText().contentEquals("Admin") && 
				//textoContraseña.getText().contains("admin")) {
				prin.armarVentanaPrincipal(this);
				this.revalidate();
				this.repaint();
				//}
				//else JOptionPane.showMessageDialog(null, "Datos incorrectos");
			});
			
		
		}
			
		
	public static void main(String[] args) {
			App app = new App();
			app.correrApp();
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setTitle("Tp-Died");
			app.setSize(1280,720);
			app.setVisible(true);
			/*Estacion e1 = new Estacion();
			Estacion e2 = new Estacion();
			Estacion e3 = new Estacion();
			Estacion e4 = new Estacion();
			Estacion e5 = new Estacion();
			Vertex v1 = new Vertex(e1);
			Vertex v2 = new Vertex(e2);
			Vertex v3 = new Vertex(e3);
			Vertex v4 = new Vertex(e4);
			Vertex v5 = new Vertex(e5);
			Ruta r1 = new Ruta(1, e1, e2, 20, 15, 200, EstadoRuta.Activa, 12.6);
			Ruta r2 = new Ruta(2, e2, e3, 30, 16, 250, EstadoRuta.Activa, 13.6);
			Ruta r3 = new Ruta(3, e3, e4, 40, 17, 300, EstadoRuta.Activa, 14.6);
			Ruta r4 = new Ruta(4, e4, e5, 50, 18, 350, EstadoRuta.Activa, 15.6);
			Edge ed1 = new Edge(v1, v2);
			Edge ed2 = new Edge(v2, v3);
			Edge ed3 = new Edge(v3, v4);
			Edge ed4 = new Edge(v4, v5);*/
			
		}
	
}
		
	
	

