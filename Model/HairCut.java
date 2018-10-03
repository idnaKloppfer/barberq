package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author idnak
 */
public class HairCut {

	private int id;
	private String style;
	private double price;
	private char gender;
	private long duration;
	public boolean registry, delete, update;
	private final conection CONNECTION;

	public HairCut() {
		CONNECTION = conection.Conec();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean deleteHairCut() {
		delete = false;

		String sql = "DELETE from haircut_type where id = " + this.id + "";
		int result = CONNECTION.runUpdate(sql);

		if (result != 0) {
			delete = true;
		}
		return delete;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean updateHairCut() {
		update = false;
		String sql = "UPDATE haircut_type set style = '" + this.style + "',price = " + this.price + ", gender = '"
				+ this.gender + "', " + "duration = " + this.duration + " where id = " + this.id + "";
		int result = CONNECTION.runUpdate(sql);

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
	public boolean insertHairCut() {
		registry = false;

		String sql = "INSERT INTO haircut_type(style,price,gender,duration)" + "values('" + this.style + "',"
				+ this.price + ",'" + this.gender + "'," + this.duration + ")";
		int result = CONNECTION.runUpdate(sql);

		if (result != 0) {
			registry = true;
		}
		return registry;
	}

	/**
	 *
	 * @return list of hair cut
	 */
	public String[][] hairCutList() {

		String sql = "select style,price,gender,duration from haircut_type";

		ResultSet result = CONNECTION.runQuery(sql);

		if (result == null) {

			return null;
		}

		int i = 0;
		try {
			while (result.next())
				i++;
			String[][] data = new String[i][4];
			i = 0;
			result.beforeFirst();
			Calendar du = Calendar.getInstance();
			while (result.next()) {
				data[i][0] = result.getString("style");
				data[i][1] = result.getString("price");
				data[i][2] = result.getString("gender");
				data[i][2] = result.getString("gender");

				int hrs = du.get(Calendar.HOUR_OF_DAY);
				int mins = du.get(Calendar.MINUTE);
				int sec = du.get(Calendar.SECOND);
				du.add(Calendar.HOUR_OF_DAY, -hrs);
				du.add(Calendar.MINUTE, -mins);
				du.add(Calendar.SECOND, -sec);
				du.add(Calendar.MILLISECOND, +result.getInt("Duration"));

				Time dur = Time.valueOf(
						du.get(Calendar.HOUR_OF_DAY) + ":" + du.get(Calendar.MINUTE) + ":" + du.get(Calendar.SECOND));
				data[i][3] = dur.toString();

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
	 * @param nameStyle of hair cut
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean searchHairCutByNameStyle(String nameStyle) {

		boolean flag = false;
		String sql = "select * from haircut_type where style = '" + nameStyle + "'";

		ResultSet result = CONNECTION.runQuery(sql);
		try {

			if (result != null) {
				System.out.println(sql);
				result.next();
				setId(result.getInt("id"));
				setStyle(result.getString("style"));
				setPrice(result.getDouble("price"));
				setDuration(result.getLong("duration"));
				String genderr = result.getString("gender");
				char gener = genderr.charAt(0);
				setGender(gener);
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
	 * Check whether the type of haircut exists
	 * 
	 * @param nameStyle-
	 *            name of style
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 * 
	 */
	public boolean validateStyle(String nameStyle) {
		boolean flag = false;
		String sql = "select id from haircut_type where style = '" + nameStyle + "'";
		ResultSet result = CONNECTION.runQuery(sql);

		if (result != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Find a price by type of haircut
	 * 
	 * @return price of hair cuts
	 */
	public double findPriceofHairCut() {
		double price = 0;

		String sql = "select price from haircut_type where style = '" + this.style + "'";
		ResultSet result = CONNECTION.runQuery(sql);

		if (result != null) {
			try {
				result.next();
				price = result.getFloat("price");
			} catch (SQLException ex) {
				Logger.getLogger(HairCut.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return price;
	}

	/**
	 *
	 * @param id of haircut_type
	 * @return true or false, if the haircut_type is capture
	 */
	public boolean searchHaircutById(long id) {

		boolean flag = false;
		String sql = "select * from haircut_type where id= " + id + "";

		ResultSet result = CONNECTION.runQuery(sql);
		try {

			if (result != null) {
				result.next();
				setId(result.getInt("id"));
				setStyle(result.getString("style"));
				setPrice(result.getDouble("price"));
				setDuration(result.getLong("duration"));
				String genderr = result.getString("gender");
				char gender = genderr.charAt(0);
				setGender(gender);
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
	 * @param id of meeting
	 * @return List haircut type by meeting id
	 */

	public String[][] listHairCut(Long id) {

		String sql = "select h.style,h.price from haircut_type as h \n" + "join meeting as m on h.ID =m.HAIRCUT\n"
				+ "	where m.ID = " + id + "";

		ResultSet resultQuery = CONNECTION.runQuery(sql);

		if (resultQuery == null) {
			return null;
		}

		int i = 0;
		try {
			while (resultQuery.next())
				i++;
			String[][] data = new String[i][2];
			i = 0;
			resultQuery.beforeFirst();
			while (resultQuery.next()) {
				data[i][0] = resultQuery.getString("style");
				data[i][1] = resultQuery.getString("price");

				i++;
			}
			return data;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
