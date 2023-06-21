
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rifky
 */
import javax.swing.*;
import java.sql.DriverManager;

public class koneksi_db {
	public static java.sql.Connection conn;
	public static void openConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/perpustakaan";
			conn = (java.sql.Connection)DriverManager.getConnection(url,"root","");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(koneksi_db.class.getName()).log(Level.SEVERE, null, ex);
		} catch (java.sql.SQLException ex){
			JOptionPane.showMessageDialog(null, ex);
			
		}
		
	}
	public static java.sql.Connection getConnection(){
		return conn;
	}
}
