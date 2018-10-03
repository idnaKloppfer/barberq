package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author idnak
 */
public class Position {

	private int id;
	private String position;
	public boolean flag, update, delete;
	private final conection connection;

	public Position() {
		connection = conection.Conec();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean insertPosition() {
		flag = false;
		String sql = "INSERT INTO work_position (name) " + "values ('" + this.position + "')";
		int result = connection.runUpdate(sql);
		if (result != 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean updatePosition() {
		update = false;
		String sql = "UPDATE work_position set name = '" + this.position + "' where id = " + this.id + "";
		int result = connection.runUpdate(sql);
		if (result != 0) {
			update = true;
		}
		return update;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         succesfully or not.
	 */
	public boolean deletePosition() {
		delete = false;
		String sql = "DELETE from work_position where id = " + this.id + "";
		int result = connection.runUpdate(sql);
		if (result != 0) {
			delete = true;
		}
		return delete;
	}

	/**
	 *
	 * @return list position
	 */
	public String[][] resultList() {

		boolean flag = false;

		String sql = "select * from work_position";

		ResultSet result = connection.runQuery(sql);
		if (result == null) {
			return null;
		}
		int i = 0;
		try {
			while (result.next())
				i++;
			String[][] data = new String[i][1];
			i = 0;
			result.beforeFirst();
			while (result.next()) {
				data[i][0] = result.getString("name");
				i++;
			}
			return data;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/**
	 *
	 * @param name position
	 * @return true or false if capture data
	 */
	public boolean matchingModel(String name) {

		boolean flag = false;
		String sql = "select * from work_position where name = '" + name + "'";
		ResultSet result = connection.runQuery(sql);
		try {

			if (result != null) {
				result.next();
				setPosition(result.getString("name"));
				setId(result.getInt("id"));

				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return flag;

	}

	/**
	 *
	 * @param cb- the position is select in combo box
	 * @return  list of position to combo box
	 */
	public boolean listPosition(DefaultComboBoxModel cb) {
		boolean flag = false;
		String sql = "select * from work_position";
		ResultSet result = connection.runQuery(sql);
		if (result != null) {
			try {
				cb.addElement("Select");
				while (result.next()) {
					flag = true;
					cb.addElement(result.getString("name"));
                                        System.out.println("Model.Posit");
				}
			} catch (SQLException ex) {
				Logger.getLogger(Position.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return flag;
	}

	/**
	 *
	 * @param id of position
	 */
	public void checkName(int id) {
		String sql = "select name from work_position where id = " + id + "";
		ResultSet result = connection.runQuery(sql);
		if (result != null) {
			try {
				result.next();
				setPosition(result.getString("name"));
			} catch (SQLException ex) {
				Logger.getLogger(Position.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {

		}
	}

	/**
	 *
	 * @param name of position
	 * @return id position
	 */
	public int checkId(String name) {
		int idP = 0;
		String sql = "SELECT id from work_position where name = '" + name + "'";

		ResultSet result = connection.runQuery(sql);
		if (result != null) {
			try {
				result.next();
				idP = result.getInt("id");
			} catch (SQLException ex) {
				Logger.getLogger(Position.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return idP;
	}
}
