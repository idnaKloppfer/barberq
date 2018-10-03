
package Controller;

import View.Vemployee;
import View.Vjobtitle;
import View.Remployee;
import View.Vperson;
import View.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Model.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author idnak
 */
public class ControllerEmployee implements ActionListener, MouseListener, KeyListener {

	DefaultTableModel dm;
	private Vemployee vEmployee;
	private Employee modelEmployee;
	private Remployee rEmployee;
	private Principal principal;
	private JobTitle modelJob;
	private Vperson vPerson;
	private Vjobtitle vJobTitle;
	private Person modelPerson;
	private final ImageIcon ICON=new ImageIcon(ControllerEmployee.class.getResource("/View/img/checkmark.png"));

    /**
     *
     * @param v
     */
    public ControllerEmployee(Vemployee v) {
		this.vEmployee = v;
		modelEmployee = new Employee();
		modelPerson = new Person();
		modelJob = new JobTitle();
		vPerson = new Vperson(principal, true);
		vJobTitle = new Vjobtitle(principal, true);
		rEmployee = new Remployee(principal, true);
		
		this.Tolist(1);
	}

	private void Tolist(int list) {
		if (list == 1) {
			String[][] info = modelEmployee.resultList();
			this.vEmployee.getEmployeeTable().setModel(new javax.swing.table.DefaultTableModel(info,
					new String[] { "Phone", "Name", "Last Name", "Entry Time", "Departure" }) {
				boolean[] canEdit = new boolean[] { false, false, false, false, false };

				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			});
			vEmployee.getEmployeeTable().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		} else if (list == 2) {

			String[][] info = modelPerson.resultList();
			vPerson.getTablePerson().setModel(
					new javax.swing.table.DefaultTableModel(info, new String[] { "Phone", "Name", "Last Name" }) {
						boolean[] canEdit = new boolean[] { false, false, false };

						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return canEdit[columnIndex];
						}
					});
			vPerson.getTablePerson().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		} else {
			String[][] info = modelJob.resultListJobTitle();
			vJobTitle.getTabelJobTittle()
					.setModel(new javax.swing.table.DefaultTableModel(info, new String[] { "Job Title", "Position" }) {
						boolean[] canEdit = new boolean[] { false, false };

						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return canEdit[columnIndex];
						}
					});
			vJobTitle.getTabelJobTittle().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		}

	}

    /**
     *
     * @param query
     * @param jTableSearch
     */
    public void searchList(String query, JTable jTableSearch) {

		dm = (DefaultTableModel) jTableSearch.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
		jTableSearch.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}

    /**
     *
     * @param phoneEmployee
     * @param check
     */
    public void captureData(long phoneEmployee, String check) {

		if (check.equalsIgnoreCase("Employee")) {
			boolean found = modelEmployee.searceModelPersonByPhone(phoneEmployee);
			if (found) {
				vEmployee.dispose();
				vEmployee.setVisible(false);
				rEmployee = new Remployee(principal, true);

				rEmployee.getId().setText(String.valueOf(modelEmployee.getId()));
				rEmployee.getNamePerson().setText(modelEmployee.getName());
				rEmployee.getLastname().setText(modelEmployee.getLastName());
				rEmployee.getPhone().setText("0" + String.valueOf(modelEmployee.getPhone()));

				rEmployee.getWorkHours().setText(modelEmployee.getEntryTime() + " to " + modelEmployee.getDepartureTime());
				rEmployee.getWorkHours().setForeground(Color.black);
				rEmployee.getWorkHours().setBackground(Color.red);
				rEmployee.getWorkHours().setFont(new Font("Serif", Font.ITALIC, 14));

				rEmployee.getEntry().setTime(Time.valueOf(modelEmployee.getEntryTime()));
				rEmployee.getDeparture().setTime(Time.valueOf(modelEmployee.getDepartureTime()));

				rEmployee.getDt().clear();
				if (modelEmployee.getWorkDay() != null) {
					rEmployee.setDt(modelEmployee.getWorkDay());
				}

				char gender = modelEmployee.getGender();
				if (gender == 'M') {
					rEmployee.getMale().setSelected(true);
				} else if (gender == 'F') {
					rEmployee.getFemale().setSelected(true);
				}
				int jobId = modelEmployee.getJobId();
				modelJob = new JobTitle();
				String jobName = modelJob.captureNameJobTitle(jobId);

				rEmployee.getJobtitle().setText(jobName);
				rEmployee.getBtnDelete().setVisible(true);
				rEmployee.getSearchJob().setEnabled(true);
				rEmployee.setControllerEmployee(this);

				rEmployee.setVisible(true);

			} else {

				JOptionPane.showMessageDialog(principal, "Record not found", "Employee",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

    /**
     *
     * @param name
     */
    public void captureData(String name) {

		boolean found = modelJob.captureJobTitleByName(name);
		if (found) {
			vJobTitle.dispose();
			vJobTitle.setVisible(false);

			rEmployee.getJobtitle().setText(modelJob.getJobName());

		} else {

			JOptionPane.showMessageDialog(new JFrame(), "Record not found", "Employee",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object event = ae.getSource();

		if (event.equals(rEmployee.getExit())) {

			rEmployee.dispose();
			rEmployee.setVisible(false);
			vEmployee = new Vemployee(principal, true);
			vEmployee.setControllerEmployee(this);
			Tolist(1);
			vEmployee.setVisible(true);
		} else if (event.equals(rEmployee.getBtnDelete())) {
			int opt = JOptionPane.showConfirmDialog(principal, "Confirm delete?", "Delete Person",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (opt != 0) {// if you regret and want to keep on with the vPerson on the list
				
					
				}
				else {
					this.delete();
	
				}
		} else if (event.equals(rEmployee.getBtnRegister())) {
			this.validate();
		} else if (event.equals(vEmployee.getNewBtt())) {
			vEmployee.dispose();
			vEmployee.setVisible(false);

			rEmployee = new Remployee(principal, true);
			rEmployee.setControllerEmployee(this);
			modelEmployee.setId(0);
			rEmployee.getBtnDelete().setVisible(false);

			rEmployee.setVisible(true);
		} else if (event.equals(rEmployee.getSearchJob())) {
			vJobTitle.setController(this);
			this.Tolist(3);
			vJobTitle.getNewBtt().setEnabled(false);
			vJobTitle.setVisible(true);
		}
	}

	private void validate() {
		Calendar entry, depart;
		entry = new GregorianCalendar();
		entry.setTimeInMillis(Time.valueOf(rEmployee.getEntry().getTimeField().getText()).getTime());

		depart = new GregorianCalendar();
		depart.setTimeInMillis(Time.valueOf(rEmployee.getDeparture().getTimeField().getText()).getTime());

		boolean yes = entry.before(depart);

		if (rEmployee.getNamePerson().getText().length() < 2) {
			JOptionPane.showMessageDialog(principal,
					"please Verify that the name is not empty and contains no less than 2 characters", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "Verify that the name is not empty and contains no less than 2 characters";
			rEmployee.getComment().setText(err);
			rEmployee.getComment().setForeground(Color.RED);
		} else if (rEmployee.getLastname().getText().length() < 2) {
			JOptionPane.showMessageDialog(principal,
					"please Verify that the last name is not empty and contains no less than 2 characters",
					"information", JOptionPane.INFORMATION_MESSAGE);
			String err = "Verify that the last name is not empty and doesn't contain less than 2 characters";
			rEmployee.getComment().setText(err);
			rEmployee.getComment().setForeground(Color.RED);
			rEmployee.getLastname().setFocusable(true);
		} else if (!rEmployee.getGender().isSelected(rEmployee.getMale().getModel())
				&& !rEmployee.getGender().isSelected(rEmployee.getFemale().getModel())) {
			JOptionPane.showMessageDialog(principal, "You must select a gender", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "You must select a gender";
			rEmployee.getComment().setText(err);
			rEmployee.getComment().setForeground(Color.RED);
			rEmployee.getMale().setFocusable(true);
		} else if (rEmployee.getJobtitle().getText().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "You must select a vJobTitle title to the employee", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "You must select a profession to the employee";
			rEmployee.getComment().setText(err);
			rEmployee.getComment().setForeground(Color.RED);
		} else if (!rEmployee.getPhone().getText().startsWith("05")) {
			JOptionPane.showMessageDialog(principal, "the phone number must start with 05********", "information",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (rEmployee.getPhone().getText().trim().length() < 10
				|| rEmployee.getPhone().getText().trim().length() > 10) {
			JOptionPane.showMessageDialog(principal, "the phone number must be 10 digits", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "Verify the phone number";
			rEmployee.getComment().setText(err);
			rEmployee.getComment().setForeground(Color.RED);

		} else if (rEmployee.getDt().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "You must select even one working day for the employee",
					"information", JOptionPane.INFORMATION_MESSAGE);
			String err = "You must select even one working day for the employee";

			rEmployee.getComment().setText(err);
			rEmployee.getComment().setForeground(Color.red);
		} else if (!yes) {
			JOptionPane.showMessageDialog(principal,
					"There is a problem, you must select working hours or change times - the entry time can not be after departure time, please correct.",
					"information", JOptionPane.INFORMATION_MESSAGE);
			String err = "The entry time can not be after departure time, please correct.";
			rEmployee.getComment().setForeground(Color.red);
			rEmployee.getDeparture().setBackground(Color.red);
			rEmployee.getComment().setText(err);
		} else {
			rEmployee.getComment().setForeground(Color.black);
			record();

		}
	}

	private void record() {
		long dn = modelEmployee.getId();

		if (dn > 0) {

			modelEmployee.setName(rEmployee.getNamePerson().getText());
			modelEmployee.setLastName(rEmployee.getLastname().getText());
			modelEmployee.setPhone(Long.parseLong(rEmployee.getPhone().getText()));
			char gend = this.captureGender(rEmployee.getGender()).charAt(0);

			modelEmployee.setGender(gend);
			int idjob = modelJob.searchJobTitleId(rEmployee.getJobtitle().getText());
			modelEmployee.setJobId(idjob);

			modelEmployee.setWorkDay(rEmployee.getDt());

			modelEmployee.setEntryTime(rEmployee.getEntry().getFormatedTime());
			modelEmployee.setDepartureTime(rEmployee.getDeparture().getTimeField().getText());

			boolean result = false;
			try {
				result = modelEmployee.updateEmployee();
			} catch (SQLException ex) {
				Logger.getLogger(ControllerEmployee.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (result) {
				JOptionPane.showMessageDialog(principal,
						modelEmployee.getName() + " " + modelEmployee.getLastName() + " has been successfully modified", "information",
						JOptionPane.INFORMATION_MESSAGE,ICON);
				rEmployee.setVisible(false);
				rEmployee.dispose();
				vEmployee = new Vemployee(principal, true);
				Tolist(1);
				vEmployee.setControllerEmployee(this);
				vEmployee.setVisible(true);

				String success = "The employee has been successfully modified " + modelEmployee.getName() + " "
						+ modelEmployee.getLastName();
				rEmployee.getComment().setText(success);
				rEmployee.getComment().setForeground(Color.GREEN);

			}
		} else if (modelEmployee.getId() < 1) {
			if (modelEmployee.verifyPhone(Long.parseLong(rEmployee.getPhone().getText()))) {
				JOptionPane.showMessageDialog(principal, "Can not register an employee who is already employed",
						"information", JOptionPane.INFORMATION_MESSAGE);
				String err = "Can not register an employee who is already employed";
				rEmployee.getComment().setText(err);
			} else {
				modelEmployee.setName(rEmployee.getNamePerson().getText());
				modelEmployee.setLastName(rEmployee.getLastname().getText());
				modelEmployee.setPhone(Long.parseLong(rEmployee.getPhone().getText()));
				char gend = this.captureGender(rEmployee.getGender()).charAt(0);

				modelEmployee.setWorkDay(rEmployee.getDt());

				modelEmployee.setEntryTime(rEmployee.getEntry().getTimeField().getText());
				modelEmployee.setDepartureTime(rEmployee.getDeparture().getTimeField().getText());

				modelEmployee.setGender(gend);
				int jobId = modelJob.searchJobTitleId(rEmployee.getJobtitle().getText());

				modelEmployee.setJobId(jobId);

				boolean result = modelEmployee.insertEmployee();
				if (result) {
					JOptionPane.showMessageDialog(principal,
							modelEmployee.getName() + " " + modelEmployee.getLastName()
									+ " has been successfully registered as Employee",
							"success", JOptionPane.INFORMATION_MESSAGE,ICON);
					String success = "The employee has been successfully  registered " + modelEmployee.getName() + " "
							+ modelEmployee.getLastName();
					rEmployee.getComment().setText(success);
					rEmployee.getComment().setForeground(Color.GREEN);
					

					rEmployee.setVisible(false);

					vEmployee = new Vemployee(principal, true);
					vEmployee.setControllerEmployee(this);
					Tolist(1);
					vEmployee.setVisible(true);
				}
			}

		} else {
			String err = "The employee hasn't registered";
			rEmployee.getComment().setText(err);
		}

	}

	private String captureGender(ButtonGroup btn) {
		String gender = null;

		for (Enumeration<AbstractButton> buttons = btn.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				gender = button.getText();

			}
		}
		return gender;
	}

	private void delete() {
		boolean result = modelEmployee.delete();
		if (result) {
			rEmployee.setVisible(false);
			rEmployee.dispose();
			vEmployee = new Vemployee(principal, true);
			Tolist(1);
			vEmployee.setControllerEmployee(this);
			vEmployee.setVisible(true);

		} else {
			String err = "The employee could not be deleted ";
			rEmployee.getComment().setText(err);
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object mouseEvent = me.getSource();
		if (mouseEvent.equals(vEmployee.getEmployeeTable())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vEmployee.getEmployeeTable().getSelectedRow();
					int row1 = vEmployee.getEmployeeTable().convertRowIndexToModel(row);
					DefaultTableModel tableModel = (DefaultTableModel) vEmployee.getEmployeeTable().getModel();
					long capture = Long.parseLong((String) tableModel.getValueAt(row1, 0));
					captureData(capture, "employee");
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
				}
			}
		} else if (mouseEvent.equals(vPerson.getTablePerson())) {
			if (me.getClickCount() == 2) {
				try {
					int row1 = vPerson.getTablePerson().getSelectedRow();
					int row2 = vPerson.getTablePerson().convertRowIndexToModel(row1);
					DefaultTableModel dtm = (DefaultTableModel) vPerson.getTablePerson().getModel();
					Long phone = Long.parseLong((String) dtm.getValueAt(row2, 0));
					captureData(phone, "vPerson");
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
				}
			}
		} else if (mouseEvent.equals(vJobTitle.getTabelJobTittle())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vJobTitle.getTabelJobTittle().getSelectedRow();
					int row1 = vJobTitle.getTabelJobTittle().convertRowIndexToModel(row);
					DefaultTableModel tableModel = (DefaultTableModel) vJobTitle.getTabelJobTittle().getModel();
					String capture = (String) tableModel.getValueAt(row1, 0);
					captureData(capture);
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {

	}

	@Override
	public void mouseReleased(MouseEvent me) {

	}

	@Override
	public void mouseEntered(MouseEvent me) {

	}

	@Override
	public void mouseExited(MouseEvent me) {

	}

	@Override
	public void keyTyped(KeyEvent ke) {
		Object keyEvent = ke.getSource();
		if (keyEvent.equals(vEmployee.getTextSearch())) {
			if (vEmployee.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		Object obj = ke.getSource();
		if (obj.equals(vEmployee.getTextSearch())) {
			String search = vEmployee.getTextSearch().getText();
			searchList(search, vEmployee.getEmployeeTable());
		} else if (obj.equals(vPerson.getTextSearch())) {
			String search1 = vPerson.getTextSearch().getText();
			searchList(search1, vPerson.getTablePerson());
		} else if (obj.equals(vJobTitle.getTextSearch())) {
			String search2 = vJobTitle.getTextSearch().getText();
			searchList(search2, vJobTitle.getTabelJobTittle());
		}
	}
}
