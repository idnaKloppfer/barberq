
package Controller;

import View.Rclient;
import View.Principal;
import Model.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import View.Vperson;
import javax.swing.ImageIcon;
import javax.swing.RowFilter;

/**
 *
 * @author idnak
 */
public class ControllerClient implements ActionListener, KeyListener, MouseListener {

	DefaultTableModel dm;
	private Vperson vPerson;
	private final Client client_model;
	private Rclient rClient;
	private Principal principal;
	private boolean ofMeeting = false, meeting = false;
        
	private ImageIcon icon=new ImageIcon(ControllerClient.class.getResource("/View/img/checkmark.png"));

    /**
     *
     * @param person
     */
    public ControllerClient(Vperson person) {

		this.vPerson = person;
		client_model = new Client();
		Tolist();
	}

    /**
     *
     * @param client
     * @param person
     */
    public ControllerClient(Rclient client, Vperson person) {
		ofMeeting = true;
		this.vPerson = person;
		this.rClient = client;
		client_model = new Client();
		client = new Rclient(principal, true);
		Tolist();
	}
        public ControllerClient(Rclient client, Vperson person, boolean meeting) {
                this.meeting = meeting;
		ofMeeting = true;
		this.vPerson = person;
		this.rClient = client;
		client_model = new Client();
		client = new Rclient(principal, true);
		Tolist();
	}
	private void Tolist() {
		String[][] resultList = client_model.resultList();
		vPerson.getTablePerson().setModel(
				new javax.swing.table.DefaultTableModel(resultList, new String[] { "Phone", "Name", "Last Name" }) {
					boolean[] canEdit = new boolean[] { false, false, false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		vPerson.getTablePerson().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
     * @param phone
     */
    public void captureData(long phone) {
		boolean found = client_model.searceModelPersonByPhone(phone);
		if (found) {
			vPerson.dispose();
			vPerson.setVisible(false);
			rClient = new Rclient(principal, true);
			rClient.getPhone().setText("0"+String.valueOf(client_model.getPhone()));
			rClient.getNameC().setText(client_model.getName());
			rClient.getLastName().setText(client_model.getLastName());
			rClient.getMaill().setText(client_model.getEmail());
			rClient.getId().setText(String.valueOf(client_model.getId()));
			char gender = client_model.getGender();
			if (gender == 'F') {
				rClient.getWom().setSelected(true);
			} else if (gender == 'M') {
				rClient.getMan().setSelected(true);
			}
			rClient.getDelete().setEnabled(true);
			rClient.getDelete().setVisible(true);
			rClient.setControllerClient(this);
			rClient.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(principal, "Client not found", "Client", JOptionPane.INFORMATION_MESSAGE);
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

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object event = ae.getSource();
		if (event.equals(vPerson.getNewPerson())) {
			vPerson.dispose();
			vPerson.setVisible(false);

			rClient = new Rclient(principal, true);
			client_model.setPhone(0);
			rClient.getDelete().setVisible(false);
			rClient.setControllerClient(this);
			rClient.setVisible(true);

		} else if (event.equals(rClient.getExit())) {

			if (ofMeeting) {
				rClient.dispose();
				rClient.setVisible(false);

			} else {
				rClient.dispose();
				rClient.setVisible(false);

				vPerson = new Vperson(principal, true);
				ImageIcon iconP = new ImageIcon(getClass().getResource("/View/img/client.png"));
				vPerson.getHeader().setIcon(iconP);
				vPerson.setController(this);
				client_model.setPhone(0);
				this.Tolist();
				vPerson.setVisible(true);
			}
		} else if (event.equals(rClient.getDelete())) {
			this.delete();
		} else if (event.equals(rClient.getRegister())) {
			validate();
		}
	}

	private void validate() {
		String phone = rClient.getPhone().getText().trim();
		if (phone.length() < 10 || phone.length() > 10) {
			JOptionPane.showMessageDialog(principal, "the phone number must be 10 digits", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String err = "verify the phone number";
			rClient.getComment().setText(err);
			rClient.getComment().setForeground(Color.RED);
		}
		else if (!rClient.getPhone().getText().startsWith("05")) {
			JOptionPane.showMessageDialog(principal, "the phone number must start with 05********", "information",
					JOptionPane.INFORMATION_MESSAGE);
		
	}else if(rClient.getNameC().getText().length()<2)

	{
		JOptionPane.showMessageDialog(principal,
				"Verify that the name is not empty and contains no less than 2 characters", "information",
				JOptionPane.INFORMATION_MESSAGE);
		String err = "Verify that the name is not empty and contains no less than 2 characters";
		rClient.getComment().setText(err);
		rClient.getComment().setForeground(Color.RED);
	}else if(rClient.getLastName().getText().length()<2)
	{
		JOptionPane.showMessageDialog(principal,
				"Verify that the last name is not empty and contains no less than 2 characters", "information",
				JOptionPane.INFORMATION_MESSAGE);
		String err = "Verify that the last name is not empty and contains no less than 2 characters";
		rClient.getComment().setText(err);
		rClient.getComment().setForeground(Color.RED);
		rClient.getLastName().setFocusable(true);
	}else if(!rClient.getGender().isSelected(rClient.getMan().getModel())&&!rClient.getGender().isSelected(rClient.getWom().getModel()))
	{
		JOptionPane.showMessageDialog(principal, "please select gender", "information",
				JOptionPane.INFORMATION_MESSAGE);
		String err = "you must select a gender";
		rClient.getComment().setText(err);
		rClient.getMan().setFocusable(true);
	}
	else if((client_model.getPhone())<1&&client_model.validatePhone(Long.parseLong(rClient.getPhone().getText())))
	{
		String err = "This phone number has another rClient";
		rClient.getComment().setText(err);
		rClient.getMan().setFocusable(true);
	}else
	{

		this.record();
	}
	}

	private void record() {
		String name = rClient.getNameC().getText();
		String lastname = rClient.getLastName().getText();
		if ((client_model.getPhone()) > 0) {
			client_model.setPhone(Long.parseLong(rClient.getPhone().getText().trim()));
			client_model.setName(rClient.getNameC().getText());
			client_model.setLastName(rClient.getLastName().getText());
			client_model.setGender(captureGender(rClient.getGender()).charAt(0));

			client_model.setEmail(rClient.getMaill().getText());

			if (!rClient.getMaill().getText().contains("@"))
			{
				JOptionPane.showMessageDialog(principal, "The email is invalid", "information",
						JOptionPane.INFORMATION_MESSAGE);
				String err = "Please enter a valid email";
				rClient.getComment().setText(err);
				rClient.getMaill().setFocusable(true);

			} else {
				boolean result = client_model.updateClient();
				if (result) {
					JOptionPane.showMessageDialog(principal, "You have succesfully modified " + name + " " + lastname , "success",
							JOptionPane.DEFAULT_OPTION,icon);
					rClient.dispose();
					rClient.setVisible(false);
					vPerson = new Vperson(principal, true);
					vPerson.setController(this);
					this.Tolist();
					String success = "Has successfully modified the rClient " + client_model.getName();
					rClient.getComment().setText(success);
				} else {
					JOptionPane.showMessageDialog(principal, "Hasn't modified the rClient" + name + " " + lastname , "success",
							JOptionPane.DEFAULT_OPTION);
					String err = "Hasn't modified the rClient" + client_model.getName();
					rClient.getComment().setText(err);
				}
			}

		} else if ((client_model.getPhone()) < 1) {
                    
                        
			client_model.setPhone(Long.parseLong(rClient.getPhone().getText().trim()));
			client_model.setName(rClient.getNameC().getText());
			client_model.setLastName(rClient.getLastName().getText());
			client_model.setGender(captureGender(rClient.getGender()).charAt(0));

			client_model.setEmail(rClient.getMaill().getText());
			if (!rClient.getMaill().getText().contains("@")) {
				JOptionPane.showMessageDialog(principal, "The email is invalid", "information",
						JOptionPane.INFORMATION_MESSAGE);
				String comment = "Please enter a valid email";
				rClient.getComment().setText(comment);
				rClient.getMaill().setFocusable(true);

			} else {
				boolean result = client_model.insertClient();
				if (result) {
					JOptionPane.showMessageDialog(principal, "You have succesfully registered " + name + " " + lastname + " as a new client", "success",
							JOptionPane.DEFAULT_OPTION,icon);
					
					String success = "Has succesfully registered the rClient " + name;
					rClient.getComment().setText(success);
					rClient.getComment().setForeground(Color.GREEN);

					rClient.dispose();
					rClient.setVisible(false);

                                        if(meeting == false){
                                            vPerson = new Vperson(principal, true);
                                            vPerson.setController(this);
                                            client_model.setPhone(0);
                                            this.Tolist();
                                            vPerson.setVisible(true);
                                        }else{
                                            meeting = false;
                                            rClient.dispose();
                                            rClient.setVisible(false);
                                        }
					

					
					
				} else {
					String err = "Hasn't registered the rClient " + client_model.getName();
					rClient.getComment().setText(err);
				}
			}

		} else {
			JOptionPane.showMessageDialog(principal, "Can not register please check and get in touch with the developers " , "Error",
					JOptionPane.DEFAULT_OPTION);
			String err = "Can not register please check and get in touch with the developers";
			rClient.getComment().setText(err);
		}
	}

	private void delete() {
		boolean result = client_model.deleteClient();
		if (result) {
			rClient.dispose();
			rClient.setVisible(false);

			vPerson = new Vperson(principal, true);

			vPerson.setController(this);
			client_model.setPhone(0);
			this.Tolist();
			vPerson.setVisible(true);
		} else {
			String err = "the rClient could not be deleted";
			rClient.getComment().setText(err);
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		Object keyEvent = ke.getSource();
		if (keyEvent.equals(vPerson.getTablePerson())) {
			char b = ke.getKeyChar();
			if (vPerson.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		Object origin = ke.getSource();
		if (origin.equals(vPerson.getTextSearch())) {
			String search = vPerson.getTextSearch().getText();
			searchList(search, vPerson.getTablePerson());
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object mouseEvent = me.getSource();
		if (mouseEvent.equals(vPerson.getTablePerson())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vPerson.getTablePerson().getSelectedRow();
					int row1 = vPerson.getTablePerson().convertRowIndexToModel(row);
					DefaultTableModel tableModel = (DefaultTableModel) vPerson.getTablePerson().getModel();
					Long capture = Long.parseLong((String) tableModel.getValueAt(row1, 0));
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
}
