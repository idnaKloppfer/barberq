
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author idnak
 */
public class Person {

	// Fields of class
	private String name, lastName, typePerson;
	private long phone;
	private char gender;
	private long id = 0;

	public boolean registry, update, delete;
	private final conection connection;

	public Person() {
		connection = conection.Conec();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public final void setId(long id) {
		this.id = id;
	}

	public String getTypePerson() {
		return typePerson;
	}

	public void setTypePerson(String typePerson) {
		this.typePerson = typePerson;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean insert() {
		boolean insert = false;
		String sql = "INSERT into person(name,last_name,phone,gender,typeperson) values('" + this.name + "'" + ",'"
				+ this.lastName + "'," + this.phone + ",'" + this.gender + "','" + this.typePerson + "')";
		int result = connection.runUpdate(sql);
		if (result == 1) {
			insert = true;
		}
		return insert;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not..
	 */
	public boolean update() {
		update = false;
		String sql = "UPDATE person set name = '" + this.name + "', last_name = '" + this.lastName + "'," + "phone = "
				+ this.phone + ",gender='" + this.gender + "', typeperson = '" + this.typePerson + "'" + " where id="
				+ this.id + "";
		int result = connection.runUpdate(sql);
		if (result != 0) {
			update = true;
		}
		return update;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean delete() {
		delete = false;
		String sql = "DELETE from person where id = " + this.id + "";
		int result = connection.runUpdate(sql);
		if (result != 0) {
			delete = true;
		}
		return delete;
	}

	/**
	 *
	 * @return list of person
	 */
	public String[][] resultList() {

		String sql = "select p.typeperson,p.name,p.last_name,p.phone from person as p";

		ResultSet resultQuery = connection.runQuery(sql);

		if (resultQuery == null) {
			return null;
		}

		int i = 0;
		try {
			while (resultQuery.next())
				i++;
			String[][] data = new String[i][4];
			i = 0;
			resultQuery.beforeFirst();
			while (resultQuery.next()) {
				data[i][0] = resultQuery.getString("typeperson");
				data[i][1] = resultQuery.getString("name");
				data[i][2] = resultQuery.getString("last_name");
				data[i][3] = "0" + resultQuery.getString("phone");

				i++;
			}
			return data;
		} catch (SQLException ex) {

			return null;
		}
	}

	/**
	 * 
	 * @param phone of person
	 * @return person with phone number
	 */

	public boolean searceModelPersonByPhone(long phone) {

		boolean flag = false;
		String sql = "select * from person where phone = " + phone + "";
		ResultSet resultQuery = connection.runQuery(sql);
		try {

			if (resultQuery != null) {
				resultQuery.next();
				setName(resultQuery.getString("NAME"));
				setLastName(resultQuery.getString("LAST_NAME"));
				setId(resultQuery.getLong("id"));
				setPhone(resultQuery.getLong("PHONE"));
				setGender(resultQuery.getString("GENDER").charAt(0));
				setTypePerson(resultQuery.getString("typeperson"));

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
	 * Check whether the phone number exists
	 * 
	 * @return if person is Exists
	 */

	public boolean phoneVerification() {
		boolean exist = false;
		String sql = "select * from person where phone = " + this.phone + "";
		ResultSet result = connection.runQuery(sql);
		if (result != null) {
			exist = true;
		}
		return exist;
	}
}
