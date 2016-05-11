package pku.edu.cn.conn;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	static String url;
	static String username;
	static String password;
	private static Connection conn;
	static{
		Properties props = new Properties();
		try {
			InputStream in =DbConnection.class.getResourceAsStream("db.properties");
			props.load(in);
			url = props.getProperty("db_url");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static{  
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	} 
	public static Connection getConnection(){
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String args[]){
		DbConnection.getConnection();
	}
}
