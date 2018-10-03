
package Model;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import javax.swing.JOptionPane; 

/**
 *
 * @author idnak
 */
public class conection {
	private String user, driver, driverUrl, key;
	private static Connection con = null;

	public static conection conection = new conection();

	public conection() {

		this.Conf_database();
		this.connect();

	};



	public Connection getConec() {
		return con;
	}

	public static conection Conec() {
		return conection;
	}

	private void Conf_database() {
            
               this.driver = "com.mysql.jdbc.Driver";
		// url of db driver jdbc mysql...
		this.driverUrl = "jdbc:mysql://localhost:3306/barberq?" + "useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true&" + "useLegacyDatetimeCode=false&" + "serverTimezone=UTC&"
				+ "character_set_server=ISO8859_8&" + "characterEncoding=UTF-8";
		this.user = "root";
		this.key = "1234";
                
	}

	private Connection connect() {
		try {

			con = DriverManager.getConnection(driverUrl, user, key);
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		return con;
	}

	/**
	 *
	 * @param sql is running
	 * @return 1 if the query is running or 0 if the query is not running
	 */
	public int runUpdate(String sql) {
		Statement sen;
		int a;
		try {

			sen = con.createStatement(); // prepare connection for sentence .
			a = sen.executeUpdate(sql); // execute sentence. except sentence of consult SELect
                        System.out.println(a);
			return a;
		} catch (SQLException e) {
			System.out.println("error on sentence: " + e.getMessage());
			return 0;
		}
	}

	/**
	 *
	 * @param sql running
	 * @return ResultSet
	 */
	public ResultSet runQuery(String sql) {
		Statement sen;
		ResultSet rs;
		try {
			sen = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sen.executeQuery(sql);
			if (rs.next()) {
                            System.out.println(rs);
				rs.beforeFirst();
				return rs;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}

}
