
package Model;

import View.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author idnak
 */
public class Meeting {

	private final conection CONNECTION;

	private String date;
	private String time;
	private long idMeetint, employeeId, clientId, haircutId, userId;
	private Double totalPrice;
	private int completedWork, discount;
	private Date dateMeeting;
	private Time hour, hourexit;
	private ArrayList meetService;
	private long durationservice;

	public Meeting() {
		CONNECTION = conection.Conec();
	}

	public String getDate() {
		return date;
	}

	public void setHourExit(Time hour) {
		this.hourexit = hour;
	}

	public Time getHourExit() {
		return this.hourexit;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getIdMeetint() {
		return idMeetint;
	}

	public void setIdMeetint(long idMeetint) {
		this.idMeetint = idMeetint;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getHaircutId() {
		return haircutId;
	}

	public void setHaircutId(long haircutId) {
		this.haircutId = haircutId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCompletedWork() {
		return completedWork;
	}

	public long getDurationService() {
		return this.durationservice;
	}

	public void setCompletedWork(int completedWork) {
		this.completedWork = completedWork;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getDatetime() {
		return dateMeeting;
	}

	public void setDatetime(Date datetime) {
		this.dateMeeting = datetime;
	}

	public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
	}

	/**
	 * 
	 * @return A list of client who have scheduled a queue
	 */
	public String[][] listMetting() {
		String[][] data = null;
		Connection conect = conection.Conec().getConec();

		String sql1 = "select CONCAT(p.name,\" \", p.last_name) as employee_name\r\n" + "from person as p \r\n"
				+ "join employee as emp on p.id = emp.PERSON_ID\r\n"
				+ "join meeting as m on emp.id = m.EMPLOYEE_SUPPORT\r\n" + "where m.COMPLETEDWORK = 0 order by m.date";
		String sql = "select p.phone,p.name, p.last_name, DATE(m.date) as date,TIME(m.DATE) as hour, TIME(m.DATEEXIT) as hourD from person as p \n"
				+ "join client as c on p.phone = c.phone\n" + "JOIN meeting as m on c.ID = m.CLIENT_ID\n"
				+ "where m.COMPLETEDWORK = 0 order by m.date";
		PreparedStatement list1 = null, list2 = null;

		try {
			list1 = conect.prepareStatement(sql);
			ResultSet rs = list1.executeQuery();
			list2 = conect.prepareStatement(sql1);
			ResultSet result = list2.executeQuery();
			if (rs != null && result != null) {

				int i = 0;
				try {
					while (rs.next() && result.next())
						i++;
					data = new String[i][7];
					i = 0;
					rs.beforeFirst();
					result.beforeFirst();
					while (rs.next() && result.next()) {
						String timee = rs.getString("hour");
						String timeD = rs.getString("hourD");
						data[i][0] = "0" + rs.getString("phone");
						data[i][1] = rs.getString("name");
						data[i][2] = rs.getString("last_name");
						data[i][3] = rs.getString("date");
						data[i][4] = timee;
						data[i][5] = timeD;
						data[i][6] = result.getString("employee_name");
						i++;
					}

				} catch (Exception ex) {
					return null;
				}
			} else {
				data = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			try {
				if (list1 != null) {
					list1.close();
				}
				if (list2 != null) {
					list2.close();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return data;
	}

	/**
	 * 
	 * @param phone of client
	 * @return a boolean to indicate that the statement was executed successfully or not.
	 */
	public boolean searchMeetingByClientPhone(long phone) {

		boolean statusQuery = false;
		String sql = "select m.id,DATE(m.date) as date,m.EMPLOYEE_SUPPORT as employee, m.CLIENT_ID as client, m.COMPLETEDWORK  as completedWork,\n"
				+ "m.DISCOUNT,TIME(m.date) as hour,TIME(m.dateexit) as hourexit, m.HAIRCUT, m.TOTALPRICE, m.USER_ID from meeting as m\n"
				+ "join client as c on m.CLIENT_ID = c.ID\n" + "join person as p on c.phone = p.phone\n"
				+ "where p.phone = " + phone + " and m.completedwork = 0";

		ResultSet rs = CONNECTION.runQuery(sql);
		try {

			if (rs != null) {
				rs.next();
				setIdMeetint(rs.getInt("id"));
				String date1 = rs.getString("date");
				setDatetime(new Date());
				setDate(date1);
				setEmployeeId(rs.getLong("employee"));
				setClientId(rs.getLong("client"));
				setHourExit(Time.valueOf(rs.getString("hourexit")));
				setCompletedWork(rs.getInt("completedWork"));
				setDiscount(rs.getInt("discount"));
				setHaircutId(rs.getLong("haircut"));
				setTotalPrice(rs.getDouble("totalprice"));
				setUserId(rs.getLong("user_id"));
				setHour(Time.valueOf(rs.getString("hour")));

				statusQuery = true;
			} else {
				statusQuery = false;
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}

		return statusQuery;

	}

	/**
	 * 
	 * @param durationCut time duration on millisecond
	 * @return true or false if registry
	 */
	public boolean insertMeeting(long durationCut) {

		boolean registry = false; // field variable indicate registry or not
		ArrayList durationService = new ArrayList(); // arrayList where we will store the duration of the meeting
		SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm:ss");// create a format of how we will work the hour
		Calendar date2 = Calendar.getInstance(); // capture a class instance of calendar class
		date2.setTimeInMillis(this.hour.getTime()); // assign the start time of the meeting

		date2.add(Calendar.MILLISECOND, (int) durationCut);// add the duration of the appointment that I received by
															// parameter will have
		// capture the maximum time in time format
		Time hourMax = Time.valueOf(
				date2.get(Calendar.HOUR_OF_DAY) + ":" + date2.get(Calendar.MINUTE) + ":" + date2.get(Calendar.SECOND));
		String exitDateClient = formatDate.format(hourMax);// the format that we did above is assigned to obtain a
															// string
		Connection conec = CONNECTION.getConec();
		String date1 = this.date + ' ' + this.hour;

		String date3 = this.date + ' ' + exitDateClient;

		String sql = "insert into meeting(date,dateexit,client_id,user_id,completedwork,employee_support, haircut)"
				+ " values(?,?,?,?,?,?,?)";
		String sql4 = "select max(id) as id from meeting";
		String sql3 = "select id,duration from service where name =?";
		String sql2 = "insert into meetserv(ids,idm) values(?,?)";
		String sql5 = "update meeting set date = ?, dateexit = ? where id = ?";

		PreparedStatement inserMeeting = null, selectMaxId = null, selectIdDurationService = null, inserms = null,
				uptmeet = null;

		try {
			conec.setAutoCommit(false);
			inserMeeting = conec.prepareStatement(sql);
			inserMeeting.setString(1, date1);
			inserMeeting.setString(2, date3);
			inserMeeting.setLong(3, this.clientId);
			inserMeeting.setLong(4, this.userId);
			inserMeeting.setInt(5, this.completedWork);
			inserMeeting.setLong(6, this.employeeId);
			inserMeeting.setLong(7, this.haircutId);

			//System.out.println(inserMeeting + "insert meeting check");
			int inser1 = inserMeeting.executeUpdate();
			if (inser1 > 0) {
				selectMaxId = conec.prepareStatement(sql4);
				ResultSet result = selectMaxId.executeQuery();
				result.next();
				setIdMeetint(result.getLong("id"));

				Object[] list = this.meetService.toArray();
				if (list.length > 0) {
					for (int i = 0; i < list.length; i++) {
						selectIdDurationService = conec.prepareStatement(sql3);
						selectIdDurationService.setString(1, (String) list[i]);
						System.out.println(i + ": " + (String) list[i]);
						ResultSet rs = selectIdDurationService.executeQuery();
						if (rs != null) {
							long id_service = 0;
							rs.next();
							id_service = rs.getLong("id");
							durationService.add(rs.getLong("duration"));
							inserms = conec.prepareStatement(sql2);
							inserms.setLong(1, id_service);
							System.out.println(id_service);
							inserms.setLong(2, this.idMeetint);
							inserms.executeUpdate();
						}
					}
				}
			}
			long sumService = 0; // add duration of service
			for (long i = 0; i < durationService.size(); i++) {
				sumService += (Long) durationService.get((int) i);
			}
			sumService += durationCut;
			date2.setTimeInMillis(this.hour.getTime());
			date2.add(Calendar.MILLISECOND, (int) sumService);
			hourMax = Time.valueOf(date2.get(Calendar.HOUR_OF_DAY) + ":" + date2.get(Calendar.MINUTE) + ":"
					+ date2.get(Calendar.SECOND));
			exitDateClient = formatDate.format(hourMax);
			date1 = this.date + ' ' + this.hour;
			date3 = this.date + ' ' + exitDateClient;
			uptmeet = conec.prepareStatement(sql5);
			uptmeet.setString(1, date1);
			uptmeet.setString(2, date3);
			uptmeet.setLong(3, this.getIdMeetint());
			uptmeet.executeUpdate();

			conec.commit();
			registry = true;
		} catch (SQLException ex) {
			try {
				conec.rollback();
			} catch (SQLException ex1) {
				Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex1);
			}
			ex.printStackTrace();

		} finally {

			try {
				if (inserMeeting != null) {
					inserMeeting.close();
				}
				if (selectMaxId != null) {
					selectMaxId.close();
				}
				if (selectIdDurationService != null) {
					selectIdDurationService.close();
				}
				if (inserms != null) {
					inserms.close();
				}
			} catch (SQLException ex) {
				Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
		return registry;
	}

	/**
	 *
	 * @return false or true if completed meeting
	 */
	public boolean completedMeeting() throws SQLException {
		boolean registry = false;
		Connection conec = CONNECTION.getConec();
		String sql = "update meeting set completedwork = ?, totalprice = ?, discount = ?  where id = ?";
		PreparedStatement completed = null;

		try {
			conec.setAutoCommit(false);

			completed = conec.prepareStatement(sql);
			completed.setInt(1, 1);
			completed.setDouble(2, this.totalPrice);
			completed.setInt(3, this.getDiscount());
			completed.setLong(4, this.getIdMeetint());

			completed.executeUpdate();
			conec.commit();
			registry = true;
		} catch (SQLException ex) {
			Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
			conec.rollback();
		} finally {
			if (completed != null) {
				completed.close();
			}
		}
		return registry;
	}

	/**
	 *
	 * @param durationCut of hair cut
	 * @return true or flase if update
	 */
	public boolean updateMeeting(long durationCut) throws SQLException {
		boolean register = false;
		ArrayList durService = new ArrayList();
		Connection conec = CONNECTION.getConec();
		SimpleDateFormat formatdate = new SimpleDateFormat("HH:mm:ss");
		Calendar date2 = Calendar.getInstance();
		date2.setTimeInMillis(this.hour.getTime());
		date2.add(Calendar.MILLISECOND, (int) durationCut);
		Time hourmax = Time.valueOf(
				date2.get(Calendar.HOUR_OF_DAY) + ":" + date2.get(Calendar.MINUTE) + ":" + date2.get(Calendar.SECOND));
		String dateMax = formatdate.format(hourmax);
		String date1 = this.date + ' ' + this.hour;
		String date3 = this.date + ' ' + dateMax;
		String sql = "update meeting set employee_support = ?, haircut = ?, user_id = ?, completedwork = ?, date= ?, dateexit = ? "
				+ "where id = ?";
		String sqldele = "delete from meetserv where idm= ?";
		String sql3 = "select id, duration from service where name = ?";
		String sql2 = "insert into meetserv(ids,idm) values(?,?)";
		String sql5 = "update meeting set date = ?, dateexit = ? where id = ?";
		PreparedStatement meetUpdate = null, meetServi = null, selidser = null, inserser = null, meetUpdateTime = null;

		try {
			conec.setAutoCommit(false);

			meetUpdate = conec.prepareStatement(sql);
			meetUpdate.setLong(1, this.employeeId);
			meetUpdate.setLong(2, this.haircutId);
			meetUpdate.setInt(3, Principal.getIdUser());
			meetUpdate.setInt(4, 0);
			meetUpdate.setString(5, date1);
			meetUpdate.setString(6, date3);
			meetUpdate.setLong(7, this.idMeetint);
			meetUpdate.executeUpdate();
			meetServi = conec.prepareStatement(sqldele);
			meetServi.setLong(1, this.idMeetint);
			int result = meetServi.executeUpdate();
			if (result > 0) {
				Object[] list = this.meetService.toArray();
				if (list.length > 0) {
					for (int i = 0; i < list.length; i++) {
						selidser = conec.prepareStatement(sql3);
						selidser.setString(1, (String) list[i]);

						ResultSet result1 = selidser.executeQuery();

						if (result1 != null) {
							long id_s = 0;
							try {
								result1.next();
								id_s = result1.getLong("id");
								durService.add(result1.getLong("duration"));
							} catch (SQLException ex) {
								Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
							}

							inserser = conec.prepareStatement(sql2);
							inserser.setLong(1, id_s);
							inserser.setLong(2, this.getIdMeetint());
							inserser.executeUpdate();
						}

					}
				}
			}
			long sumserv = 0;
			for (long i = 0; i < durService.size(); i++) {
				sumserv += (Long) durService.get((int) i);
			}
			sumserv += durationCut;
			date2.setTimeInMillis(this.hour.getTime());
			date2.add(Calendar.MILLISECOND, (int) sumserv);
			hourmax = Time.valueOf(date2.get(Calendar.HOUR_OF_DAY) + ":" + date2.get(Calendar.MINUTE) + ":"
					+ date2.get(Calendar.SECOND));
			dateMax = formatdate.format(hourmax);
			date1 = this.date + ' ' + this.hour;
			date3 = this.date + ' ' + dateMax;

			meetUpdateTime = conec.prepareStatement(sql5);
			meetUpdateTime.setString(1, date1);
			meetUpdateTime.setString(2, date3);
			meetUpdateTime.setLong(3, this.getIdMeetint());
			meetUpdateTime.executeUpdate();

			conec.commit();
			register = true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			conec.rollback();

		} finally {
			if (meetUpdate != null) {
				meetUpdate.close();
			}

			if (meetServi != null) {
				meetServi.close();
			}

			if (selidser != null) {
				selidser.close();
			}

		}
		return register;
	}


	/**
	 *
	 * @return true or false if delete
	 */
	public boolean delete() {
		boolean deletem = false;
		String sql = "delete from meeting where id = " + this.idMeetint + " AND completedwork = 0";
		int result = CONNECTION.runUpdate(sql);

		if (result > 0) {
			deletem = true;
		}
		return deletem;
	}


	public void setMeetserv(ArrayList<String> list) {
		this.meetService = list;

	}

	/**
	 *
	 * @return true or false if meeting is exists
	 */
	public boolean verify() {
		boolean verify = false;

		String sql = "select id from meeting where client_id = " + this.getClientId() + " and completedwork = 0";
		ResultSet result = CONNECTION.runQuery(sql);

		if (result != null) {

			verify = true;
		}
		return verify;
	}

	/**
	 *
	 * @return list of service in meeting
	 */

	public ArrayList<String> MeeServ() {
		meetService = new ArrayList();

		String sql = "select s.id,s.name,s.price,s.duration from service as s\n"
				+ "join meetserv as me on s.ID = me.ids\n" + "where me.idm =  " + this.idMeetint + "";

		ResultSet result = CONNECTION.runQuery(sql);

		if (result != null) {
			try {

				while (result.next()) {
					meetService.add(result.getString("name"));
					this.durationservice += result.getLong("duration");
				}
			} catch (SQLException ex) {
				Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return meetService;

	}

	/**
	 *
	 * @param idEmployee of emp
	 * @param date of meeting
	 * @return list of busy hours emp
	 */

	public ArrayList findBusyHoursOfEmployee(long idEmployee, Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateFormat.format(date);

		ArrayList hour = new ArrayList();
		Connection conec = CONNECTION.getConec();
		String sql = "select TIME(m.date) as ENTRYTIME, TIME(m.DATEEXIT) as DEPARTURETIME from employee as emp \n"
				+ "join meeting as m on emp.ID = m.EMPLOYEE_SUPPORT\n"
				+ "where DATE(m.date) = ? and emp.ID = ? and m.COMPLETEDWORK = 0\n" + "ORDER BY m.date";
		PreparedStatement statement = null;

		try {
			statement = conec.prepareStatement(sql);
			statement.setString(1, date1);
			statement.setLong(2, idEmployee);

			ResultSet result = statement.executeQuery();

			ArrayList entryTime = new ArrayList();// Meeting start time
			ArrayList departureTime = new ArrayList();// End of meeting hours
			while (result.next()) {
				// System.out.println("entry time: " + result.getString("ENTRYTIME"));
				// System.out.println("entry time: " + result.getString("ENTRYTIME"));

				entryTime.add(result.getString("ENTRYTIME"));
				departureTime.add(result.getString("DEPARTURETIME"));
			}
			hour.add(entryTime);
			hour.add(departureTime);
			// System.out.println("emp hour: " + hour.toString());
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}

		return hour;
	}
}
