package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author idnak
 */
public class JobTitle {

	private String jobName;
	private int id, positionId;
	public boolean registry, update, delete;
	private final conection CONNECTION;

	public JobTitle() {
		CONNECTION = conection.Conec();
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */

	public boolean insertJob() {
		registry = false;

		String sql = "INSERT INTO job_tittle (name,work_position_id) " + "values('" + this.jobName + "',"
				+ this.positionId + ")";
		int result = CONNECTION.runUpdate(sql);
		if (result != 0) {

			registry = true;
		}
		return registry;
	}

	/**
	 * 
	 * @return Returns a boolean to indicate that the statement was executed
	 *         successfully or not.
	 */
	public boolean updateJob() {
		update = false;
		String sql = "UPDATE job_tittle set name = '" + this.jobName + "',work_position_id = " + this.positionId + " "
				+ "where id = " + this.id + "";
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
	public boolean deleteJob() {
		delete = false;
		String sql = "DELETE from job_tittle where id =" + this.id + "";
		int result = CONNECTION.runUpdate(sql);
		if (result != 0) {
			delete = true;

		}
		return delete;
	}

	/**
	 *
	 * @return list of job title
	 */
	public String[][] resultListJobTitle() {

		String sql = "select j.name as job, w.name as position from job_tittle as j "
				+ "join work_position as w on j.WORK_POSITION_ID = w.ID";

		ResultSet result = CONNECTION.runQuery(sql);

		if (result == null) {
			return null;
		}

		int i = 0;
		try {
			while (result.next())
				i++;
			String[][] data = new String[i][2];
			i = 0;
			result.beforeFirst();
			while (result.next()) {
				data[i][0] = result.getString("job");
				data[i][1] = result.getString("position");

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
	 * @param name of job title
	 * @return true or false if detailse capture
	 */

	public boolean captureJobTitleByName(String name) {

		boolean flag = false;
		String sql = "select j.id, j.name as job, w.name as position,w.id as positionId from job_tittle as j \n"
				+ "	join work_position as w on j.WORK_POSITION_ID = w.ID\n" + "	where j.name = '" + name + "'";

		ResultSet result = CONNECTION.runQuery(sql);
		try {

			if (result != null) {

				result.next();
				setId(result.getInt("id"));
				setJobName(result.getString("job"));
				setPositionId(result.getInt("positionId"));
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
	 * @param id of job title
	 * @return name job title
	 */
	public String captureNameJobTitle(int id) {

		String name = "";
		String sql = "select name from job_tittle where id = " + id + "";

		ResultSet result = CONNECTION.runQuery(sql);

		if (result != null) {
			try {
				result.next();
				name = result.getString("name");
			} catch (SQLException ex) {
				Logger.getLogger(JobTitle.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return name;
	}

	/**
	 *
	 * @param name of job title
	 * @return id job_tittle
	 */
	public int searchJobTitleId(String name) {
		int idC = 0;

		String sql = "select id from job_tittle where name = '" + name + "'";
		ResultSet result = CONNECTION.runQuery(sql);

		if (result != null) {
			try {
				result.next();
				idC = result.getInt("id");
			} catch (SQLException ex) {
				Logger.getLogger(JobTitle.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return idC;
	}

}