package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author idnak
 */
public class Employee extends Person {

	// Fields of class

	private long idEmployee;
	private int jobId;
	private ArrayList workDay;
	private final conection CONNECTION;
	private String position = null;
	private String entryTime, departureTime;

	public Employee() {
		CONNECTION = conection.Conec();
	}
	// Methods of class

	public long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(long identification) {
		this.idEmployee = identification;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public ArrayList getWorkDay() {
		return workDay;
	}

	public void setWorkDay(ArrayList workDay) {
		this.workDay = workDay;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int positionId) {
		this.jobId = positionId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean insertEmployee() {
		boolean register = false;

		/*
		 * Registration of an employee to the person' class!
		 */
		String sql = "insert into person(name,last_name,phone,gender,typeperson) values(" + "'" + this.getName() + "','"
				+ this.getLastName() + "'," + this.getPhone() + ",'" + this.getGender() + "','Employee')";
		int result = CONNECTION.runUpdate(sql);
		if (result != 0) {
			/*
			 * after the registeration completed to Person class, this operation is started.
			 * this operation is the continue of the process to complete the operation of
			 * add employee.
			 */
			String sql1 = "insert into employee(person_id,job_tittle_id,entrytime,departuretime) values((select max(id) from person),"
					+ this.getJobId() + "," + "'" + this.getEntryTime() + "','" + this.getDepartureTime() + "')";

			int result2 = CONNECTION.runUpdate(sql1);
			if (result2 > 0) {
				System.out.println("Model.Employee.insertEmployee()");
				register = true;
				Iterator df = workDay.iterator();
				while (df.hasNext()) {
					/*
					 * select the workdays of the employee by day id.
					 */
					String sql4 = "select id from workdays where days = '" + df.next().toString() + "'";
					System.out.println(sql4);

					ResultSet resul = CONNECTION.runQuery(sql4);
					if (resul != null) {
						try {
							resul.next();
							int idwork = resul.getInt("id");
							/*
							 * INSERT's work day of the same employee to the empWork linking table, connect
							 * the day id to the worker id.
							 */
							String sql5 = "INSERT INTO empwork(idemployee,idwork) values((select max(id) from employee),"
									+ idwork + ")";

							int res = CONNECTION.runUpdate(sql5);
							if (res > 0) {
								register = true;
							}
						} catch (SQLException ex) {
							Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			}
		}
		return register;
	}

	@Override
	/*
	 * When adding an employee through a new person, the employee get in person and
	 * employee tables.
	 */
	public boolean insert() {

		boolean flag = false;

		String sql = "insert into person(name,last_name,phone,gender,typeperson) values" + "('" + this.getName() + "','"
				+ this.getLastName() + "'," + this.getPhone() + ",'" + this.getGender() + "'," + "'"
				+ this.getTypePerson() + "')";

		int result = CONNECTION.runUpdate(sql);
		if (result > 0) {
			String sql1 = "select max(id) as id from person";
			ResultSet result2 = CONNECTION.runQuery(sql1);
			if (result2 != null) {
				long personId = 0;
				try {

					result2.next();
					personId = result2.getLong("id");
				} catch (SQLException ex) {
					Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
				}
				String sql2;
				if (this.jobId == 0) {
					sql2 = "insert into employee(person_id) values(" + personId + ")";
				} else {
					sql2 = "insert into employee(person_id, job_tittle_id) values(" + personId + "," + this.jobId + ")";
				}

				int resultad = CONNECTION.runUpdate(sql2);
				if (resultad > 0) {
					flag = true;
				}
			}
		}

		return flag;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean updateEmployee() throws SQLException {
		boolean flag = false;

		Connection conec = CONNECTION.getConec();

		String updatePerson = "UPDATE person set name = ?, last_name= ?, phone= ? ,gender = ? where id = ? ";
		String updateEmployee = "UPDATE employee set job_tittle_id = ? ,ENTRYTIME = ?, DEPARTURETIME = ? where person_id = ? ";
		String deleteupWork = "DELETE FROM empwork where  idemployee = ?";
		String sql4 = "select id from workdays where days = ?";
		String sql5 = "INSERT INTO empwork(idemployee,idwork) values(?,?)";
		PreparedStatement person = null, employee = null, upworkdele = null, workdays = null, empWork = null;

		try {
			conec.setAutoCommit(false);
			person = conec.prepareStatement(updatePerson);

			person.setString(1, this.getName());
			person.setString(2, this.getLastName());
			person.setLong(3, this.getPhone());
			person.setString(4, String.valueOf(this.getGender()));
			person.setLong(5, this.getId());
			// System.out.println(person);
			person.executeUpdate();

			employee = conec.prepareStatement(updateEmployee);
			employee.setInt(1, this.getJobId());
			employee.setString(2, this.entryTime);
			employee.setString(3, this.departureTime);
			employee.setLong(4, this.getId());

			employee.executeUpdate();
			// System.out.println(employee.executeUpdate());

			upworkdele = conec.prepareStatement(deleteupWork);
			upworkdele.setLong(1, this.getIdEmployee());

			int result = upworkdele.executeUpdate();
			if (result > 0) {
				Iterator df = workDay.iterator();
				while (df.hasNext()) {

					workdays = conec.prepareStatement(sql4);
					workdays.setString(1, df.next().toString());
					ResultSet daySelect = workdays.executeQuery();

					if (daySelect.next()) {
						int idWork = daySelect.getInt("id");
						empWork = conec.prepareStatement(sql5);
						empWork.setLong(1, this.getIdEmployee());
						empWork.setInt(2, idWork);

						empWork.executeUpdate();
					}
				}
			}

			conec.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			conec.rollback();
		} finally {
			if (person != null) {
				person.close();
			}
			if (employee != null) {
				employee.close();
			}

			if (upworkdele != null) {
				upworkdele.close();
			}
			if (workdays != null) {
				workdays.close();
			}
			if (empWork != null) {
				empWork.close();
			}
		}

		return flag;

	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean deleteEmployee() {
		boolean flag = false;

		String sql = "DELETE from Employee where id = " + this.idEmployee + "";
		int result = CONNECTION.runUpdate(sql);

		if (result != 0) {

			String sql1 = "DELETE from empwork where idemployee = " + this.idEmployee + "";
			int result2 = CONNECTION.runUpdate(sql1);
			if (result2 > 0) {
				flag = true;
			} else {
				flag = false;
			}

		}
		return flag;
	}

	/**
	 * return the employee who is working on the selected day
	 * @param nameDay- name day of employee work
         * @return List By NameDay
	 */

	public String[][] resultListByNameDay(String nameDay) {

		String sql = "select emp.id as idemployee, p.phone, p.name,p.last_name, emp.ENTRYTIME, emp.DEPARTURETIME from person as p\n"
				+ "join employee as emp on p.id = emp.PERSON_ID\n"
				+ "join empwork as empw on emp.ID = empw.idemployee\n" + "join workdays as w on empw.idwork = w.id\n"
				+ "	where w.days = '" + nameDay + "'";

		ResultSet result = CONNECTION.runQuery(sql);

		if (result == null) {

			return null;
		}

		int i = 0;
		try {

			while (result.next())
				i++;
			String[][] data = new String[i][5];
			i = 0;
			result.beforeFirst();
			while (result.next()) {
				data[i][0] = "0"+result.getString("phone");
				data[i][1] = result.getString("name");
				data[i][2] = result.getString("last_name");
				data[i][3] = result.getString("ENTRYTIME");
				data[i][4] = result.getString("DEPARTURETIME");
				i++;
			}

			return data;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	/*
	 * return employee's details
	 */
	public String[][] resultList() {

		String sql = "select emp.id as idemployee, p.phone, p.name,p.last_name, emp.ENTRYTIME, emp.DEPARTURETIME from person as p\n"
				+ "join employee as emp on p.id = emp.PERSON_ID";

		ResultSet result = CONNECTION.runQuery(sql);

		if (result == null) {

			return null;
		}

		int i = 0;
		try {

			while (result.next())
				i++;
			String[][] data = new String[i][5];
			i = 0;
			result.beforeFirst();
			while (result.next()) {
				data[i][0] = "0" + result.getString("phone");
				data[i][1] = result.getString("name");
				data[i][2] = result.getString("last_name");
				data[i][3] = result.getString("ENTRYTIME");
				data[i][4] = result.getString("DEPARTURETIME");
				i++;
			}

			return data;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/**
	 * get employee details by the phone number
	 * @param phone employee
	 * @return true or false, if the employee is capture
	 */
	@Override
	public boolean searceModelPersonByPhone(long phone) {

		boolean flag = false;
		String sql = "select p.id as idperson,p.name,p.last_name,emp.id as idemployee, p.phone, p.gender, emp.job_tittle_id "
				+ "as jobId, emp.entrytime, emp.departuretime \n" + "from person as p\n"
				+ "join employee as emp on p.id = emp.PERSON_ID\n" + "where p.phone = " + phone + "";

		ResultSet result = CONNECTION.runQuery(sql);
		try {

			if (result != null) {
				result.next();
				this.setIdEmployee(result.getLong("idemployee"));
				setId(result.getLong("idperson"));
				setName(result.getString("name"));
				setLastName(result.getString("last_name"));
				setPhone(result.getLong("phone"));
				setJobId(result.getInt("jobId"));
				setGender(result.getString("gender").charAt(0));
				setEntryTime(result.getString("entrytime"));
				setDepartureTime(result.getString("departuretime"));
				flag = true;

				String sql1 = "select w.days from person as p\n" + "   join employee as emp on p.ID = emp.PERSON_ID\n"
						+ "   join empwork as empw on emp.ID = empw.idemployee\n"
						+ "   join workdays as w on empw.idwork = w.id\n" + "   where p.PHONE = " + phone + "";

				ResultSet res = CONNECTION.runQuery(sql1);
				if (res != null) {
					workDay = new ArrayList();
					while (res.next()) {
						workDay.add(res.getString("days"));
					}

				}
			} else {
				flag = false;
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return flag;

	}

	/**
	 * @param id of emp
	 * @return true or false, if the employee is capture
	 */
	public boolean captureEmployeeById(long id) {

		boolean flag = false;
		String SQL = "select p.id as idPerson,p.name,p.last_name,emp.id as idEmployee, p.phone, p.gender, emp.job_tittle_id "
				+ "as idJob\n" + "from person as p\n" + "join employee as emp on p.id = emp.PERSON_ID\n"
				+ "where emp.id = " + id + "";

		ResultSet resultConsult = CONNECTION.runQuery(SQL);
		try {

			if (resultConsult != null) {
				resultConsult.next();
				this.setIdEmployee(resultConsult.getLong("idEmployee"));
				setId(resultConsult.getLong("idPerson"));
				setName(resultConsult.getString("name"));
				setLastName(resultConsult.getString("last_name"));
				setPhone(resultConsult.getLong("phone"));
				setJobId(resultConsult.getInt("idJob"));
				setGender(resultConsult.getString("gender").charAt(0));

				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return flag;

	}
	/*
	 * this method check if there is a unique phone number
	 */

	/**
	 *
	 * @param phone of emp
	 * @return true or false if the employee is already registered
	 */

	public boolean verifyPhone(long phone) {
		boolean flag = false;

		String sql = "select COUNT(PERSON_ID) as quantity from employee as emp join person as p on "
				+ " emp.person_id = p.id where p.phone = " + phone + "";

		ResultSet result = CONNECTION.runQuery(sql);
		if (result != null) {

			try {
				result.next();
				long count = result.getLong("quantity");
				if (count > 0) {
					flag = true;
				}
			} catch (SQLException ex) {
				Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return flag;
	}

	/**
	 *
	 * @param phone of emp
	 * @return true or false, if the employee is capture
	 */
	public boolean captureEmployeeByPhone(Long phone) {
		boolean enc = false;

		String sql = "select p.name, emp.id as idemployee, emp.ENTRYTIME, emp.DEPARTURETIME from person as p \n"
				+ "               join employee as emp on p.id = emp.person_id\n" + "                where p.phone = "
				+ phone + "";

		ResultSet result = CONNECTION.runQuery(sql);

		if (result != null) {
			enc = true;
			try {
				result.next();
				setName(result.getString("name"));
				setIdEmployee(result.getLong("idemployee"));
				setEntryTime(result.getString("entrytime"));
				setDepartureTime(result.getString("departuretime"));
			} catch (SQLException ex) {
				Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

		return enc;
	}

}
