package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Dominio.Estacion;
import Dominio.Ruta;
import Dominio.Vertex;

public class RutaService {
		
		EstacionesService es = new EstacionesService();
	
		private static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
		private static String user = "died";
		private static String pass = "died";
		
	
	public void darDeAltaRuta(Integer id, Integer ori, Integer des, Double km, Integer duracion, Integer capacidad, String estado, Double costo, String linea) throws SQLException {
			
		Connection conn = null;
		PreparedStatement pstm = null;

			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, pass);
				pstm = conn.prepareStatement("INSERT INTO briani_ferreira_tardivo_died.ruta (id_ruta, origen, destino, distanciakm, duraciontiempominutos, capacidad, estado, costoenpesos, linea) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstm.setInt(1, id);
				pstm.setInt(2, ori);
				pstm.setInt(3, des);
				pstm.setDouble(4, km);
				pstm.setInt(5, duracion);
				pstm.setInt(6, capacidad);
				pstm.setString(7, estado);
				pstm.setDouble(8, costo);
				pstm.setString(9, linea);
				pstm.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(pstm != null) try { pstm.close(); }
								catch (SQLException e) { e.printStackTrace(); }
				if(conn != null) try { conn.close(); }
				catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
	public void eliminarRuta(Integer rut) throws SQLException {
			
			Connection conn = null;
			PreparedStatement pstm = null;

			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, pass);
				pstm = conn.prepareStatement("DELETE FROM briani_ferreira_tardivo_died.ruta WHERE id_ruta = ?");
				pstm.setInt(1, rut);
				pstm.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(pstm != null) try { pstm.close(); }
								catch (SQLException e) { e.printStackTrace(); }
				if(conn != null) try { conn.close(); }
				catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
	public void editarRuta(Integer id, Integer ori, Integer des, Double km, Integer duracion, Integer capacidad, String estado, Double costo) throws SQLException {
			
			Connection conn = null;
			PreparedStatement pstm = null;

			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, pass);
				pstm = conn.prepareStatement("UPDATE briani_ferreira_tardivo_died.ruta SET origen = ?, destino = ?, distanciakm = ?, duraciontiempominutos = ?, capacidad = ?, estado = ?, costoenpesos = ?, WHERE id_ruta = ?");
				pstm.setInt(1, ori);
				pstm.setInt(2, des);
				pstm.setDouble(3, km);
				pstm.setInt(4, duracion);
				pstm.setInt(5, capacidad);
				pstm.setString(6, estado);
				pstm.setDouble(7, costo);
				pstm.setInt(8, id);
				pstm.executeUpdate();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(pstm != null) try { pstm.close(); }
								catch (SQLException e) { e.printStackTrace(); }
				if(conn != null) try { conn.close(); }
				catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
	public void eliminarRutaPorOrigen(Integer ori) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			pstm = conn.prepareStatement("DELETE FROM briani_ferreira_tardivo_died.ruta WHERE origen = ?");
			pstm.setInt(1, ori);
			pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstm != null) try { pstm.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public void eliminarRutaPorDestino(Integer des) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			pstm = conn.prepareStatement("DELETE FROM briani_ferreira_tardivo_died.ruta WHERE destino = ?");
			pstm.setInt(1, des);
			pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstm != null) try { pstm.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public void eliminarRutaPorLinea(String linea) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			pstm = conn.prepareStatement("DELETE FROM briani_ferreira_tardivo_died.ruta WHERE linea = ?");
			pstm.setString(1, linea);
			pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstm != null) try { pstm.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public List<Ruta> rutas(){
		
		List<Ruta> rutas = new ArrayList<Ruta>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			pstm = conn.prepareStatement("SELECT id_ruta, origen, destino, distanciaKm, duraciontiempominutos, capacidad, estado, costoenpesos FROM briani_ferreira_tardivo_died.ruta");
			rs = pstm.executeQuery();
			while(rs.next()) { 
				/*Object aux[] = new Object[5];
				aux[0] = rs.getInt("id_estacion");
				aux[1] = rs.getString("nombre_estacion");
				aux[2] = rs.getString("apertura");
				aux[3] = rs.getString("cierre");
				aux[4] = rs.getString("estado");*/
				Vertex<Estacion> e1 = new Vertex<Estacion>(es.buscarEstacion(rs.getInt("origen")));
				Vertex<Estacion> e2 = new Vertex<Estacion>(es.buscarEstacion(rs.getInt("destino")));
				Ruta r1 = new Ruta(e1, e2, rs.getInt("id_ruta"), rs.getDouble("distanciaKm"), 
									rs.getInt("duraciontiempominutos"), rs.getInt("capacidad"), rs.getString("estado"), 
									rs.getDouble("costoenpesos"));
				rutas.add(r1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			if(rs != null) try { rs.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(pstm != null) try { pstm.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); }
							catch (SQLException e) { e.printStackTrace(); }
		}
			
			return rutas;
	}
}
