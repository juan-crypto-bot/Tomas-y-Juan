package Logica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.table.DefaultTableModel;

public class MantenimientoService {

	private static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static String user = "died";
	private static String pass = "died";
	
	public void darDeAltaMantenimiento(Integer id, Date inicio, Date fin, String obs, Integer estacion) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			pstm = conn.prepareStatement("INSERT INTO briani_ferreira_tardivo_died.mantenimiento (id_mantenimiento, inicio, fin, observaciones, id_estacion) VALUES (?, ?, ?, ?, ?)");
			pstm.setInt(1, id);
			pstm.setDate(2, inicio);
			pstm.setDate(3, fin);
			pstm.setString(4, obs);
			pstm.setInt(5, estacion);
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

	public void eliminarMantenimiento(Integer est) throws SQLException {
	
	Connection conn = null;
	PreparedStatement pstm = null;

	try {
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, pass);
		pstm = conn.prepareStatement("DELETE FROM briani_ferreira_tardivo_died.mantenimiento WHERE id_estacion = ?");
		pstm.setInt(1, est);
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
	
	public DefaultTableModel historial(Integer est, DefaultTableModel modelo) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			pstm = conn.prepareStatement("SELECT id_mantenimiento, inicio, fin, observaciones FROM briani_ferreira_tardivo_died.mantenimiento WHERE id_estacion = ?");
			pstm.setInt(1, est);
			rs = pstm.executeQuery();
			while(rs.next()) { 
				Object aux[] = new Object[4];
				aux[0] = rs.getInt("id_mantenimiento");
				aux[1] = rs.getDate("inicio");
				aux[2] = rs.getDate("fin");
				aux[3] = rs.getString("observaciones");
				modelo.addRow(aux);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) 	try { rs.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(pstm != null) try { pstm.close(); }
							catch (SQLException e) { e.printStackTrace(); }
			if(conn != null) try { conn.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
		
		return modelo;
	}
}
