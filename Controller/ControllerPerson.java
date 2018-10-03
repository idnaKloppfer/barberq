
package Controller;

import View.Principal;
import View.Rperson;
import View.Vperson;
import java.awt.event.*;
import Model.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author idnak
 */
public class ControllerPerson implements ActionListener, KeyListener, MouseListener {
	DefaultTableModel dm;
	private Vperson vPerson;

	private Rperson rPerson;
	private final Person modelPerson;
	private Employee modelEmployee;
	private Client modelClient;
	private Principal principal;
	private final ImageIcon ICON=new ImageIcon(ControllerPerson.class.getResource("/View/img/checkmark.png"));

    /**
     *
     * @param person
     */
    public ControllerPerson(Vperson person) {
		this.vPerson = person;
		modelPerson = new Person();
		modelEmployee = new Employee();
		modelClient = new Client();

		this.Tolist();
	}

	/**
	 * 
	 * Search all people from the resultList method
	 */
	private void Tolist() {
		String[][] info = modelPerson.resultList();
		vPerson.getTablePerson().setModel(
				new javax.swing.table.DefaultTableModel(info, new String[] { "Type", "Name", "Last Name", "Phone" }) {
					boolean[] canEdit = new boolean[] { false, false, false, false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		vPerson.getTablePerson().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

	/**
	 * return data of person by query in model
	 * 
	 * @param phone
	 *            of person
	 * @param type
	 *            of person
	 */
	public void captureData(long phone, String type) {
		if (type.equals("Employee")) {
			boolean found = modelPerson.searceModelPersonByPhone(phone);
			if (found) {// If the Employee Type is found at Vperson, all the details of that person will
						// be displayed at Rperson
				vPerson.dispose();
				vPerson.setVisible(false);
				rPerson = new Rperson(principal, true);
				rPerson.getId().setText(String.valueOf(modelPerson.getId()));
				rPerson.getId().setEnabled(false);
				rPerson.getNameP().setText(modelPerson.getName());
				rPerson.getLastName().setText(modelPerson.getLastName());
				rPerson.getPhone().setText("0"+String.valueOf(modelPerson.getPhone()));
				rPerson.getTypeperson().setSelectedItem(modelPerson.getTypePerson());
				rPerson.getTypeperson().setEnabled(false);
				char gender = modelPerson.getGender();
				if (gender == 'F') {
					rPerson.getWom().setSelected(true);
				} else if (gender == 'M') {
					rPerson.getMan().setSelected(true);
				}

				rPerson.getDelete().setEnabled(true);
				rPerson.getDelete().setVisible(true);
				rPerson.setControllerPerson(this);
				rPerson.setVisible(true);

			} else {

				JOptionPane.showMessageDialog(principal, "Record not found", "Person", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (type.equals("Client")) {
			boolean found = modelPerson.searceModelPersonByPhone(phone);
			if (found) {// If the Client Type is found at Vperson, all the details of that person will
						// be displayed at Rperson
				vPerson.dispose();
				vPerson.setVisible(false);
				rPerson = new Rperson(principal, true);
				rPerson.getId().setText(String.valueOf(modelPerson.getId()));
				rPerson.getId().setEnabled(false);
				rPerson.getNameP().setText(modelPerson.getName());
				rPerson.getLastName().setText(modelPerson.getLastName());
				rPerson.getPhone().setText("0"+String.valueOf(modelPerson.getPhone()));
				rPerson.getTypeperson().setSelectedItem(modelPerson.getTypePerson());
				rPerson.getTypeperson().setEnabled(false);
				char gender = modelPerson.getGender();
				if (gender == 'F') {
					rPerson.getWom().setSelected(true);
				} else if (gender == 'M') {
					rPerson.getMan().setSelected(true);
				}

				rPerson.getDelete().setEnabled(true);
				rPerson.getDelete().setVisible(true);
				rPerson.setControllerPerson(this);
				rPerson.setVisible(true);

			} else {

				JOptionPane.showMessageDialog(principal, "Record not found", "Person", JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object event = ae.getSource();
		
		if (event.equals(vPerson.getNewPerson())) {// add new person
			vPerson.setVisible(false);
			vPerson.dispose();
			modelPerson.setId(0);// set automatic id to the new person - start at 0.
			rPerson = new Rperson(principal, true);
			rPerson.getDelete().setVisible(false);
			rPerson.setControllerPerson(this);
			rPerson.setVisible(true);
		} else if (event.equals(rPerson.getExit())) {// exit from the rPerson - back to vPerson.
			rPerson.setVisible(false);
			rPerson.dispose();
			vPerson = new Vperson(principal, true);
			vPerson.setControllerPerson(this);
			modelPerson.setId(0);
			this.Tolist();
			vPerson.setVisible(true);

		}

		else if (event.equals(rPerson.getRegister())) {// save all the details of the new person
			this.validate();
		} else if (event.equals(rPerson.getDelete())) {// delete the person

			int opt = JOptionPane.showConfirmDialog(principal, "Confirm deleteeee?", "Delete Person",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (opt != 0) {// if you regret and want to keep on with the person on the list

			} else {
				boolean del = modelPerson.delete();// Returns a boolean value if deleted from the database.
				if (del) {
					rPerson.dispose();
					rPerson.setVisible(false);
					vPerson = new Vperson(principal, true);
					vPerson.setControllerPerson(this);
					this.Tolist();
					vPerson.setVisible(true);
				} else {
					System.out.println("cucuc");
					JOptionPane.showMessageDialog(principal,"The person could not be deleted,"
							+ " \nCheck whether the person is registered as an administrator", "Error",JOptionPane.ERROR_MESSAGE);

					String err = "The person could not be deleted";
					rPerson.getComment().setText(err);
					rPerson.getComment().setForeground(Color.RED);
				}
			}

		}
	}

	// verify that all the details of the person is correct
	private void validate() {

		if (rPerson.getTypeperson().getSelectedItem().equals("Select")) {
			JOptionPane.showMessageDialog(principal, "please select type of person", "error",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "Check the type of person";
			rPerson.getComment().setText(err);
			rPerson.getComment().setForeground(Color.RED);
		} else if (rPerson.getNameP().getText().length() < 2) {
			JOptionPane.showMessageDialog(principal, "Verify that the name is not empty and contains no less than 2 characters", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "Verify that the name is not empty and contains no less than 2 characters";
			rPerson.getComment().setText(err);
			rPerson.getComment().setForeground(Color.RED);
		} else if (rPerson.getLastName().getText().length() < 2) {
			JOptionPane.showMessageDialog(principal, "Verify that the last name is not empty and contains no less than 2 characters", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "Verify that the last name is not empty and contains no less than 2 characters";
			rPerson.getComment().setText(err);
			rPerson.getComment().setForeground(Color.RED);
			rPerson.getLastName().setFocusable(true);
		} 
		else if (!rPerson.getPhone().getText().startsWith("05")) {
			JOptionPane.showMessageDialog(principal, "the phone number must start with 05********", "information",
					JOptionPane.INFORMATION_MESSAGE);			
		}
		else if (rPerson.getPhone().getText().trim().length() < 10 || rPerson.getPhone().getText().trim().length() > 10) {
			JOptionPane.showMessageDialog(principal, "the phone number must be 10 digits", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "Verify the phone number";
			rPerson.getComment().setText(err);
			rPerson.getComment().setForeground(Color.RED);

		}
		else if (!rPerson.getGender().isSelected(rPerson.getMan().getModel())
				&& !rPerson.getGender().isSelected(rPerson.getWom().getModel())) {
			JOptionPane.showMessageDialog(principal, "please select gender", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "You must select a gender";
			rPerson.getComment().setText(err);
			rPerson.getMan().setFocusable(true);
		}  else {
			record();
		}
	}

	private void record() {
		String name = rPerson.getNameP().getText();
		String lastname = rPerson.getLastName().getText();
		if (modelPerson.getId() != 0)// if the person exist in the vPerson list
		{

			if (rPerson.getTypeperson().getSelectedItem().equals("Employee"))// if the person type is Employee
			{
				// if we want to update the Employee details in the Person table.
				modelEmployee.setPhone(Long.parseLong(rPerson.getPhone().getText().trim()));
				modelEmployee.setTypePerson(rPerson.getTypeperson().getSelectedItem().toString());
				modelEmployee.setName(rPerson.getNameP().getText());
				modelEmployee.setLastName(rPerson.getLastName().getText());
				modelEmployee.setGender(captureGender().charAt(0));
				modelEmployee.setId(modelPerson.getId());
				boolean update = modelEmployee.update();//// Returns a boolean value if update the database.
				if (update) {
					JOptionPane.showMessageDialog(principal, "You have succesfully modified " + name + " " + lastname , "success",
							JOptionPane.DEFAULT_OPTION,ICON);
					rPerson.dispose();
					rPerson.setVisible(false);
					vPerson = new Vperson(principal, true);
					vPerson.setControllerPerson(this);
					this.Tolist();
					vPerson.setVisible(true);
					String success = "Has succesfully modified " + name + " " + lastname;
					rPerson.getComment().setText(success);
					rPerson.getComment().setForeground(Color.GREEN);

				} else {
					String err = "Not modified " + name + " " + lastname + ", Please check";
					rPerson.getComment().setText(err);
					rPerson.getComment().setForeground(Color.RED);
				}

			} else if (rPerson.getTypeperson().getSelectedItem().equals("Client")) {
				modelClient.setPhone(Long.parseLong(rPerson.getPhone().getText().trim()));
				modelClient.setTypePerson(rPerson.getTypeperson().getSelectedItem().toString());
				modelClient.setName(rPerson.getNameP().getText());
				modelClient.setLastName(rPerson.getLastName().getText());
				modelClient.setGender(captureGender().charAt(0));
				modelClient.setId(modelPerson.getId());
                                boolean verifyPhone = modelEmployee.phoneVerification();
                                if(verifyPhone){
                                    
                                }
				boolean update = modelClient.update();
				if (update) {
					JOptionPane.showMessageDialog(principal, "You have succesfully modified " + name + " " + lastname , "success",
							JOptionPane.DEFAULT_OPTION,ICON);
					rPerson.dispose();
					rPerson.setVisible(false);
					vPerson = new Vperson(principal, true);
					vPerson.setControllerPerson(this);
					this.Tolist();
					vPerson.setVisible(true);
					String success = "Has succesfully modified " + name + " " + lastname;
					rPerson.getComment().setText(success);
					rPerson.getComment().setForeground(Color.GREEN);

				} else {
					String err = "Not modified " + name + " " + lastname + ", Please check";
					rPerson.getComment().setText(err);
					rPerson.getComment().setForeground(Color.RED);
				}
			}

		} else if (modelPerson.getId() < 1) {// if the person doesn't exist in the vPerson list

			if (rPerson.getTypeperson().getSelectedItem().equals("Employee")) {
				modelEmployee.setPhone(Long.parseLong(rPerson.getPhone().getText().trim()));
				modelEmployee.setName(rPerson.getNameP().getText());
				modelEmployee.setLastName(rPerson.getLastName().getText());
				modelEmployee.setGender(captureGender().charAt(0));
				modelEmployee.setTypePerson(rPerson.getTypeperson().getSelectedItem().toString());
				boolean verifyPhone = modelEmployee.phoneVerification();
				if (verifyPhone) {
					String err = "The phone number already exists with another person, please try again";
					rPerson.getComment().setText(err);
				} else {

					boolean update = modelEmployee.insert();
					if (update) {
						JOptionPane.showMessageDialog(principal, "You have succesfully registered " + name + " " + lastname + " as a new Employee", "success",
								JOptionPane.DEFAULT_OPTION,ICON);
						rPerson.dispose();
						rPerson.setVisible(false);
						vPerson = new Vperson(principal, true);
						vPerson.setControllerPerson(this);
						this.Tolist();
						vPerson.setVisible(true);
						String success = "You have succesfully registered to " + name + " " + lastname;
						rPerson.getComment().setText(success);
						rPerson.getComment().setForeground(Color.GREEN);

					} else {
						String err = "Could not register to " + name + " " + lastname + ", Please check. ";
						rPerson.getComment().setText(err);
						rPerson.getComment().setForeground(Color.RED);
					}
				}
			} else if (rPerson.getTypeperson().getSelectedItem().equals("Client")) {
				modelClient.setPhone(Long.parseLong(rPerson.getPhone().getText().trim()));
				modelClient.setName(rPerson.getNameP().getText());
				modelClient.setLastName(rPerson.getLastName().getText());
				modelClient.setGender(captureGender().charAt(0));
				modelClient.setTypePerson(rPerson.getTypeperson().getSelectedItem().toString());
				boolean verifyPhone = modelClient.phoneVerification();
				if (verifyPhone) {
					String err = "The phone number already has another person can not have two identical.";
					rPerson.getComment().setText(err);
				} else {

					boolean update = modelClient.insertClient();
					if (update) {
						JOptionPane.showMessageDialog(principal, "You have succesfully registered " + name + " " + lastname + " as a new Client", "success",
								JOptionPane.INFORMATION_MESSAGE);
						rPerson.dispose();
						rPerson.setVisible(false);
						vPerson = new Vperson(principal, true);
						vPerson.setControllerPerson(this);
						this.Tolist();
						vPerson.setVisible(true);
						String success = "You have succesfully registered to " + name + " " + lastname;
						rPerson.getComment().setText(success);
						rPerson.getComment().setForeground(Color.GREEN);

					} else {
						String err = "Could not register to " + name + " " + lastname + ", Please check. ";
						rPerson.getComment().setText(err);
						rPerson.getComment().setForeground(Color.RED);
					}
				}
			}

		}
	}

	// Gender selection option
	private String captureGender() {
		String gender = null;

		for (Enumeration<AbstractButton> buttons = rPerson.getGender().getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				gender = button.getText();

			}
		}
		return gender;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object mouseEvent = me.getSource();
		if (mouseEvent.equals(vPerson.getTablePerson())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vPerson.getTablePerson().getSelectedRow();
					int row1 = vPerson.getTablePerson().convertRowIndexToModel(row);
					DefaultTableModel dtm = (DefaultTableModel) vPerson.getTablePerson().getModel();
					Long capture = Long.parseLong((String) dtm.getValueAt(row1, 3));
					String type = (String) dtm.getValueAt(row1, 0);
					captureData(capture, type);
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
		if (keyEvent.equals(vPerson.getTablePerson())) {
			if (vPerson.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {

	}

	// A function for the desired search

    /**
     *
     * @param query
     * @param jTableSearch
     */
	public void Searhlist(String query, JTable jTableSearch) {

		dm = (DefaultTableModel) jTableSearch.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
		jTableSearch.setRowSorter(tr);// sort the persons by table row
		tr.setRowFilter(RowFilter.regexFilter(query));
	}

	@Override
	// Search for the queue, the employee, the client and the type of haircut
	public void keyReleased(KeyEvent ke) {
		Object obj = ke.getSource();
		if (obj.equals(vPerson.getTextSearch())) {
			String search = vPerson.getTextSearch().getText();
			Searhlist(search, vPerson.getTablePerson());
		}
	}

}
