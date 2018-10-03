
package Controller;

import View.Vuser;
import View.Vemployee;
import View.Ruser;
import View.Principal;
import Model.Employee;
import Model.User;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author idnak
 */
public class ControllerUsers implements ActionListener, KeyListener, MouseListener {

	DefaultTableModel dm;
	private Vuser vUser;
	private User userModel;
	private Employee employeeModel;
	private Principal principal;
	private Ruser rUser;
	private Vemployee vEmployee;
	private ImageIcon icon=new ImageIcon(ControllerUsers.class.getResource("/View/img/checkmark.png"));

	// Constructors of Class...

    /**
     *
     * @param list
     */
	public ControllerUsers(Vuser list) {
		this.vUser = list;
		userModel = new User();
		employeeModel = new Employee();
		

		vEmployee = new Vemployee(principal, true);
		this.Tolist(1);
	}

    /**
     *
     * @param user
     */
    public ControllerUsers(Ruser user) {
		this.rUser = user;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj.equals(vUser.getNewBtt())) {

			vUser.setVisible(false);
			rUser = new Ruser(principal, true);
			rUser.getDelete().setEnabled(false);
			rUser.getDelete().setVisible(false);
			rUser.getLabelnewpass().setVisible(false);
			rUser.getNewPassword().setVisible(false);
			rUser.setControllerUser(this);
			rUser.getPassword().removeKeyListener(this);
			rUser.setVisible(true);
			vUser.setVisible(false);
			vUser.dispose();

		} else if (obj.equals(rUser.getExitUser())) {
			rUser.setVisible(false);
			rUser.dispose();
			vUser = new Vuser(principal, true);
			vUser.setVisible(true);

		} else if (obj.equals(rUser.getRegister())) {

			this.validate();
		} else if (obj.equals(rUser.getSearchEmployee())) {
			vEmployee.setControllerUser(this);
			this.Tolist(2);
			vEmployee.getNewBtt().setEnabled(false);
			vEmployee.setVisible(true);
		} else if (obj.equals(rUser.getDelete())) {
			this.delete();
		}
	}

	private void delete() {
		int opt = JOptionPane.showConfirmDialog(principal,
				"are you sure you want to delete the user?" + rUser.getTextuser().getText(), "Delete User",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (opt < 1) {
			boolean result = userModel.deleteUser();
			if (result) {

				rUser.dispose();
				rUser.setVisible(false);
				vUser.dispose();
				vUser = new Vuser(principal, true);
				vUser.setControllerUsers(this);
				this.Tolist(1);
				vUser.setVisible(true);

			} else {
				String err = "The User could not be deleted";
				rUser.getComment().setText(err);
			}
		}

	}

	private void Tolist(int opt) {
		if (opt == 1) {
			String[][] info = userModel.resultList();
			vUser.getTableUser()
					.setModel(new javax.swing.table.DefaultTableModel(info, new String[] { "Phone", "Name", "Users" }) {
						boolean[] canEdit = new boolean[] { false, false, false };

						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return canEdit[columnIndex];
						}
					});
			vUser.getTableUser().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
			
		} else if (opt == 2) {
			String[][] info = employeeModel.resultList();
			vEmployee.getEmployeeTable().setModel(
					new javax.swing.table.DefaultTableModel(info, new String[] { "Phone", "Name", "Last Name" }) {
						boolean[] canEdit = new boolean[] { false, false, false };

						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return canEdit[columnIndex];
						}
					});
			vEmployee.getEmployeeTable().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		}

	}

    /**
     *
     * @param query
     * @param jTableSearch
     */
    public void Listsearch(String query, JTable jTableSearch) {
		dm = (DefaultTableModel) jTableSearch.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
		jTableSearch.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}

    /**
     *
     * @param phone
     * @param opt
     */
    public void captureData(long phone, String opt) {

		if (opt.equals("rUser")) {
			boolean found = userModel.searceModelPersonByPhone(phone);
			if (found) {
				vUser.dispose();
				vUser.setVisible(false);
				rUser = new Ruser(principal, true);
				rUser.getTextuser().setText(userModel.getUser());
				rUser.getId().setText(String.valueOf(userModel.getId()));
				rUser.getNamee().setText(userModel.getName());
				rUser.getLastName().setText(userModel.getLastName());

				rUser.getNewPassword().setEnabled(false);
				rUser.getPassword().addKeyListener(this);
				rUser.getDelete().setEnabled(true);
				rUser.getDelete().setVisible(true);
				rUser.getSearchEmployee().setEnabled(false);
				rUser.setControllerUser(this);
				rUser.setVisible(true);
			}

		} else if (opt.equals("vEmployee")) {
			boolean found = employeeModel.searceModelPersonByPhone(phone);
			if (found) {
				vEmployee.setVisible(false);
				vEmployee.dispose();

				rUser.getId().setText(String.valueOf(employeeModel.getIdEmployee()));
				rUser.getNamee().setText(employeeModel.getName());
				rUser.getLastName().setText(employeeModel.getLastName());

			} else {

				JOptionPane.showMessageDialog(principal, "Record not found", "User", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void validate() {
		
		String password = "";
		
		char[] cartac = rUser.getPassword().getPassword();

		for(int i = 0; i < cartac.length; i++) {
			password += cartac[i];
		}
		System.out.println(password);
		 if (rUser.getId().getText().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "You can not register or modify a User if it is not associated with any Employee", "error",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "You can not register or modify a rUser if it is not associated with any vEmployee";
			rUser.getComment().setText(err);
			rUser.getSearchEmployee().setFocusable(true);
		}
		 else if (rUser.getTextuser().getText().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "could not register an empty User", "error",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "could not register an empty rUser";
			rUser.getComment().setText(err);
			rUser.getComment().setForeground(Color.RED);
			
		}
		 else if (rUser.getTextuser().getText().length() < 5) {
				JOptionPane.showMessageDialog(principal, "Make sure that the username fields are not less than 4 characters!", "error",
						JOptionPane.INFORMATION_MESSAGE);
				String err = "Make sure that the username fields are not less than 4 characters!";
				rUser.getComment().setText(err);
				rUser.getComment().setForeground(Color.ORANGE);
		 }
		
//		else if (rUser.getPassword().getPassword().length < 1) {
//			JOptionPane.showMessageDialog(principal, "You can not register multiple spaces in Username or in password", "error",
//					JOptionPane.INFORMATION_MESSAGE);
//			String err = "You can not register multiple spaces in whites o behalf of rUser or in password";
//			rUser.getComment().setText(err);
//			rUser.getComment().setForeground(Color.ORANGE);
//			
//		}
	 
		 else if (password.contains(" ")) {
				JOptionPane.showMessageDialog(principal, "can't be spaces in the password, try another password", "error",
						JOptionPane.INFORMATION_MESSAGE);
				String err = "can't be spaces in the password";
				rUser.getComment().setText(err);
				rUser.getComment().setForeground(Color.RED);
				}

		else if (rUser.getPassword().getPassword().length < 5) {
			JOptionPane.showMessageDialog(principal, "Make sure that the password fields are not less than 4 characters!", "error",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "Make sure that the password fields are not less than 4 characters!";
			rUser.getComment().setText(err);
			rUser.getComment().setForeground(Color.ORANGE);
		} 
		
		else {
			record();
		}
	}

	private void record() {
		if (userModel.getId() != 0) {
			userModel.setUser(rUser.getTextuser().getText());
			if (rUser.getNewPassword().getPassword().length < 1) {
				userModel.setPassword(userModel.getPassword());
			} else {
				userModel.setPassword(capturePass(rUser.getNewPassword()));
			}

			Boolean Result = userModel.updateUser();
			if (Result) {
				JOptionPane.showMessageDialog(principal, "You have succesfully modified " + rUser.getTextuser().getText(), "success",
						JOptionPane.DEFAULT_OPTION,icon);
				String success = "You have succesfully modified the User " + rUser.getTextuser().getText();
				rUser.getComment().setText(success);
				rUser.getComment().setForeground(Color.BLACK);
				rUser.dispose();
				rUser.setVisible(false);
				userModel.setId(0);
				userModel.setPhone(0);
				vUser = new Vuser(principal, true);
				vUser.setControllerUsers(this);
				this.Tolist(1);
				vUser.setVisible(true);
			} else {
				String err = "You didn't modified the rUser, " + rUser.getTextuser().getText() + " check.";
				rUser.getComment().setText(err);
				rUser.getComment().setForeground(Color.RED);
			}

		} else if (userModel.getId() < 1) {
			userModel.setIdentificationEmployee(employeeModel.getIdEmployee());
			userModel.setPassword(capturePass(rUser.getPassword()));
			userModel.setUser(rUser.getTextuser().getText());

			boolean result = userModel.insertUser();
			if (result) {
				JOptionPane.showMessageDialog(principal, "You have succesfully register " + rUser.getTextuser().getText() + " as user", "success",
						JOptionPane.DEFAULT_OPTION,icon);
				String success = "User succesfully registered " + rUser.getTextuser().getText();
				rUser.getComment().setText(success);
				userModel.setId(0);
				userModel.setPhone(0);
				rUser.getPassword().setText("");
				rUser.getTextuser().setText("");
				rUser.getId().setText("");
				rUser.getLastName().setText("");
				rUser.getNamee().setText("");
				rUser.getDelete().setVisible(false);
				rUser.dispose();
				rUser.setVisible(false);
				vUser.dispose();
				vUser = new Vuser(principal, true);
				vUser.setControllerUsers(this);
				this.Tolist(1);
				vUser.setVisible(true);

			} else {
				String err = "User didn't registered, " + rUser.getTextuser().getText() + " Check.";
				rUser.getComment().setText(err);
				rUser.getComment().setForeground(Color.RED);
			}
		}
	}

	private String capturePass(JPasswordField pass) {
		String password;
		int count = pass.getPassword().length;
		String newPass = "";
		char[] charPass = pass.getPassword();
		for (int i = 0; i < count; i++) {
			newPass = newPass + charPass[i];
		}
                password = DigestUtils.sha1Hex(newPass);
		
		return password;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		Object keyEvent = ke.getSource();
		if (keyEvent.equals(vUser.getTableUser())) {
			char b = ke.getKeyChar();
			if (vUser.getTextSearch().getText().length() > 50) {
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
		if (obj.equals(vUser.getTextSearch())) {
			String search = vUser.getTextSearch().getText();
			Listsearch(search, vUser.getTableUser());
		} 
		else if (obj.equals(vEmployee.getTextSearch())) {
			String search = vEmployee.getTextSearch().getText();
			Listsearch(search, vEmployee.getEmployeeTable());
			
		} 
		else if (obj.equals(rUser.getPassword())) {
			char charPass[] = rUser.getPassword().getPassword();
			String password = "";
			for (int i = 0; i < charPass.length; i++) {
				password += "" + charPass[i];
			}
			password = DigestUtils.sha1Hex(password);

			if (password.equals(userModel.getPassword())) {
				rUser.getNewPassword().setEnabled(true);
			} else {
				rUser.getNewPassword().setEnabled(false);
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object mouseEvent = me.getSource();
		if (mouseEvent.equals(vUser.getTableUser())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vUser.getTableUser().getSelectedRow();
					int row1 = vUser.getTableUser().convertRowIndexToModel(row);
					DefaultTableModel tableModel = (DefaultTableModel) vUser.getTableUser().getModel();
					long capture = Long.parseLong((String) tableModel.getValueAt(row1, 0));
					captureData(capture, "rUser");
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
				}
			}
		} else if (mouseEvent.equals(vEmployee.getEmployeeTable())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vEmployee.getEmployeeTable().getSelectedRow();
					int row1 = vEmployee.getEmployeeTable().convertRowIndexToModel(row);
					DefaultTableModel tableModel = (DefaultTableModel) vEmployee.getEmployeeTable().getModel();
					long capture = Long.parseLong((String) tableModel.getValueAt(row1, 0));
					captureData(capture, "vEmployee");
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

    /**
     *
     * @return
     */
    public User getUserModel() {
		return userModel;
	}

}
