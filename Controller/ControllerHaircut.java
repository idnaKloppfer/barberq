package Controller;

import View.Vhaircut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Model.*;
import View.Principal;
import View.Rhaircut;
import java.awt.HeadlessException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
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
public class ControllerHaircut implements ActionListener, KeyListener, MouseListener {

	DefaultTableModel dm;
	private Vhaircut vHairCut;
	private Rhaircut rHairCut;
	private HairCut hairCut;
	private Principal principal;
	private ImageIcon icon = new ImageIcon(ControllerHaircut.class.getResource("/View/img/checkmark.png"));

    /**
     *
     * @param vHairCut
     */
    public ControllerHaircut(Vhaircut vHairCut) {
		this.vHairCut = vHairCut;
		hairCut = new HairCut();
		rHairCut = new Rhaircut(principal, true);
		rHairCut.setControllerHairCut(this);
		this.Tolist();
	}

	private void Tolist() {
		String[][] information = hairCut.hairCutList();
		vHairCut.getTableHairCut().setModel(new javax.swing.table.DefaultTableModel(information,
				new String[] { "Style", "Price", "Gender", "Duration" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		vHairCut.getTableHairCut().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

    /**
     *
     * @param query
     * @param jTableSearch
     */
    public void ListingSearch(String query, JTable jTableSearch) {

		dm = (DefaultTableModel) jTableSearch.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
		jTableSearch.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}

    /**
     *
     * @param name
     */
    public void captureData(String name) {

		boolean found = hairCut.searchHairCutByNameStyle(name);
		if (found) {
			vHairCut.dispose();
			vHairCut.setVisible(false);
			rHairCut = new Rhaircut(principal, true);
			rHairCut.getStyle().setText(hairCut.getStyle());
			rHairCut.getPrice().setText(String.valueOf(hairCut.getPrice()));
			Calendar du = Calendar.getInstance();

			int hrs = du.get(Calendar.HOUR_OF_DAY);
			int mins = du.get(Calendar.MINUTE); 
			int sec = du.get(Calendar.SECOND);
			du.add(Calendar.HOUR_OF_DAY, -hrs);  
			du.add(Calendar.MINUTE, -mins); 
			du.add(Calendar.SECOND, -sec); 
			
			int can = (int) hairCut.getDuration(); 
			
			du.add(Calendar.MILLISECOND, + can);
			Time dur = Time.valueOf(
					du.get(Calendar.HOUR_OF_DAY) + ":" + du.get(Calendar.MINUTE) + ":" + du.get(Calendar.SECOND));
			
			
			System.out.println(dur.toString());
			rHairCut.getMinutes().setText(dur.toString());
			rHairCut.setDuration(hairCut.getDuration());
			char gender = hairCut.getGender();
			if (gender == 'F') {
				rHairCut.getFemale().setSelected(true);
			} else {
				rHairCut.getMale().setSelected(true);
			}
			rHairCut.setControllerHairCut(this);
			rHairCut.setVisible(true);

		} else {

			JOptionPane.showMessageDialog(principal, "Record not dound", "Haircut", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if (obj.equals(vHairCut.getNewBtt())) {
			vHairCut.dispose();
			vHairCut.setVisible(false);
			hairCut.setId(0);
			rHairCut = new Rhaircut(principal, true);
			rHairCut.setControllerHairCut(this);
			rHairCut.setVisible(true);
		} else if (obj.equals(rHairCut.getExit())) {
			rHairCut.dispose();
			rHairCut.setVisible(false);

			vHairCut = new Vhaircut(principal, true);
			vHairCut.setControllerHaircut(this);
			Tolist();
			vHairCut.setVisible(true);
		} else if (obj.equals(rHairCut.getRegisterBtt())) {
			this.record();
		} else if (obj.equals(rHairCut.getDelete())) {
			this.delete();
		}
	}

	private void delete() {
		int opt = JOptionPane.showConfirmDialog(principal, "Confirm delete?", "Delete Job Tittle",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (opt == 0) {
			boolean result = hairCut.deleteHairCut();
			if (result) {

				rHairCut.setVisible(false);

				vHairCut = new Vhaircut(principal, true);
				vHairCut.setControllerHaircut(this);
				Tolist();
				vHairCut.setVisible(true);

			} else {
				String text = "The haircut type was not successfully deleted";
				rHairCut.getComment().setText(text);
			}

		}
		else {
			
		}
	}

	private void record() {
		boolean result = validate();
		if (result && hairCut.getId() > 0) {
			hairCut.setStyle(rHairCut.getStyle().getText());
			hairCut.setPrice(Double.parseDouble(rHairCut.getPrice().getText()));
			hairCut.setGender(captureGender(rHairCut.getGender()).charAt(0));
			hairCut.setDuration(rHairCut.getDuration());
			boolean resultt = hairCut.updateHairCut();
			if (resultt) {
				JOptionPane.showMessageDialog(principal, "You have successfully modified the haircut", "success",
						JOptionPane.INFORMATION_MESSAGE, icon);
				String text = "the haircut type was successfully modified";
				rHairCut.getComment().setText(text);
				rHairCut.setVisible(false);

				vHairCut = new Vhaircut(principal, true);
				vHairCut.setControllerHaircut(this);
				Tolist();
				vHairCut.setVisible(true);
			} else {
				String text = "the haircut type was not modified";
				rHairCut.getComment().setText(text);

			}

		} else if (result && hairCut.getId() < 1) {
			hairCut.setStyle(rHairCut.getStyle().getText());
			hairCut.setPrice(Double.parseDouble(rHairCut.getPrice().getText()));
			hairCut.setGender(captureGender(rHairCut.getGender()).charAt(0));
			hairCut.setDuration(rHairCut.getDuration());

			if (hairCut.validateStyle(rHairCut.getStyle().getText())) {
				JOptionPane.showMessageDialog(principal, "The style you are entering already exist", "information",
						JOptionPane.INFORMATION_MESSAGE);
				String comment = "The style you are entering already exist";
				rHairCut.getComment().setText(comment);
			} else {
				boolean resulttt = hairCut.insertHairCut();
				if (resulttt) {
					JOptionPane.showMessageDialog(principal, "You have successfully add haircut", "success",
							JOptionPane.INFORMATION_MESSAGE, icon);
					String comment = "The type of haircut was successfully added";
					rHairCut.getComment().setText(comment);
					rHairCut.setVisible(false);

					vHairCut = new Vhaircut(principal, true);
					vHairCut.setControllerHaircut(this);
					Tolist();
					vHairCut.setVisible(true);
				} else {
					String comment = "the type of cut has not been succesfully added";
					rHairCut.getComment().setText(comment);
				}
			}
		}
	}

	private String captureGender(ButtonGroup gender) {
		String genders = null;

		for (Enumeration<AbstractButton> buttons = gender.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				genders = button.getText();
			}
		}
		return genders;
	}

	private boolean validate() {
		boolean validate = false;
		if (rHairCut.getStyle().getText().contains("  ") || rHairCut.getStyle().getText().length() < 2) {
			JOptionPane.showMessageDialog(principal, "The type of haircut must contain at least 2 characters", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String comment = "The type of haircut must contain at least 2 characters";
			rHairCut.getComment().setText(comment);
		} else if (rHairCut.getPrice().getText().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "The price can not be empty", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String comment = "The price can not be empty";
			rHairCut.getComment().setText(comment);
		} else if (!rHairCut.getGender().isSelected(rHairCut.getFemale().getModel())
				&& !rHairCut.getGender().isSelected(rHairCut.getMale().getModel())) {
			JOptionPane.showMessageDialog(principal, "You must select a gender", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String comment = "You must select a gender";
			rHairCut.getComment().setText(comment);
		} else {
			validate = true;
		}

		return validate;
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		Object obj = ke.getSource();
		if (obj.equals(vHairCut.getTextSearch())) {
			String search = vHairCut.getTextSearch().getText();
			ListingSearch(search, vHairCut.getTableHairCut());
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		Object kevent = ke.getSource();
		if (kevent.equals(vHairCut.getTableHairCut())) {
			char b = ke.getKeyChar();
			if (vHairCut.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		} else if (kevent.equals(rHairCut.getPrice())) {
			char b = ke.getKeyChar();
			if (!Character.isDigit(b) || rHairCut.getPrice().getText().length() > 10) {
				ke.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {

	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if (obj.equals(vHairCut.getTableHairCut())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vHairCut.getTableHairCut().getSelectedRow();
					int row1 = vHairCut.getTableHairCut().convertRowIndexToModel(row);
					DefaultTableModel dtm = (DefaultTableModel) vHairCut.getTableHairCut().getModel();
					String captur = (String) dtm.getValueAt(row1, 0);
					captureData(captur);
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
