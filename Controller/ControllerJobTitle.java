
package Controller;

import Model.JobTitle;
import Model.Position;
import View.Principal;
import View.Rjobtitle;
import View.Vjobtitle;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
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
public class ControllerJobTitle implements ActionListener, KeyListener, MouseListener {

	DefaultTableModel dm;
	private Vjobtitle vjob;
	private Rjobtitle rJobTitle;
	private JobTitle jobTittleModel;
	private Position positionModel;
	private Principal principal;
	private final ImageIcon ICON = new ImageIcon(ControllerJobTitle.class.getResource("/View/img/checkmark.png"));

    /**
     *
     * @param v
     */
    public ControllerJobTitle(Vjobtitle v) {
		this.vjob = v;
		jobTittleModel = new JobTitle();
		rJobTitle=new Rjobtitle(principal, true);
		rJobTitle.setControllerJobTittle(this);
		positionModel = new Position();
		this.Tolist();

	}

	private void positionsList(DefaultComboBoxModel comboModel) {
		boolean result = false;
		result = positionModel.listPosition(comboModel);

	}

	private void Tolist() {
		{
			String[][] info = jobTittleModel.resultListJobTitle();
			vjob.getTabelJobTittle()
					.setModel(new javax.swing.table.DefaultTableModel(info, new String[] { "Job Title", "Position" }) {
						boolean[] canEdit = new boolean[] { false, false };

						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return canEdit[columnIndex];
						}
					});
			vjob.getTabelJobTittle().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
     * @param name
     */
    public void captureData(String name) {

		
		boolean found = jobTittleModel.captureJobTitleByName(name);
		if (found) {
			vjob.dispose();
			vjob.setVisible(false);

			rJobTitle = new Rjobtitle(principal, true);

			rJobTitle.setControllerJobTittle(this);
			DefaultComboBoxModel cb = (DefaultComboBoxModel) rJobTitle.getPosition().getModel();

			this.positionsList(cb);
			positionModel.checkName(jobTittleModel.getPositionId());
			rJobTitle.getPosition().setSelectedItem(positionModel.getPosition());
			rJobTitle.getJobTitle().setText(jobTittleModel.getJobName());

			rJobTitle.getDelete().setEnabled(true);
			rJobTitle.getDelete().setVisible(true);
			rJobTitle.setControllerJobTittle(this);
			rJobTitle.setVisible(true);
			vjob.dispose();
			vjob.setVisible(false);

		} else {

			JOptionPane.showMessageDialog(new JFrame(), "Record not found", "Job Title",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if (obj.equals(vjob.getNewBtt())) {
			vjob.setVisible(false);
			vjob.dispose();
			rJobTitle = new Rjobtitle(principal, true);
			jobTittleModel.setId(0);// set automatic id to the new job title - start at 0.
			rJobTitle.setControllerJobTittle(this);
			DefaultComboBoxModel cm = (DefaultComboBoxModel) rJobTitle.getPosition().getModel();

			this.positionsList(cm);
			rJobTitle.getDelete().setVisible(false);
			rJobTitle.setVisible(true);

		} else if (obj.equals(rJobTitle.getExit())) {
			rJobTitle.dispose();
			rJobTitle.setVisible(false);

			rJobTitle.getPosition().removeAllItems();

			vjob = new Vjobtitle(principal, true);
			vjob.setControllerJobTittle(this);
			jobTittleModel.setId(0);
			this.Tolist();
			vjob.setVisible(true);

		} else if (obj.equals(rJobTitle.getDelete())) {
			delete();

		} else if (obj.equals(rJobTitle.getRegister())) {
			
			String jobTitle = rJobTitle.getJobTitle().getText().toString();
			System.out.println(jobTitle);
			if (rJobTitle.getPosition().getSelectedItem().toString().equalsIgnoreCase("Select")) {
				JOptionPane.showMessageDialog(principal, "You must select a work position", "information",
						JOptionPane.INFORMATION_MESSAGE);
				String err = "You must select a work position";
				rJobTitle.getComment().setText(err);
			} 
			
			else {
				this.record();
			}		}
	}

	private void delete() {

			boolean result = jobTittleModel.deleteJob();
			if (result) {
				rJobTitle.dispose();
				rJobTitle.setVisible(false);
				vjob = new Vjobtitle(principal, true);
				JOptionPane.showMessageDialog(principal, "", "success",
						JOptionPane.INFORMATION_MESSAGE, ICON);
				vjob.setControllerJobTittle(this);
				Tolist();
				vjob.setVisible(true);


			} else {
				JOptionPane.showMessageDialog(principal, "Delete was not successful,\n" + 
						"Check whether the job type belongs to one of the employees \n "
						+ "listed in the system", "Error",
						JOptionPane.WARNING_MESSAGE);
				String text = "Delete was not successful,\n" + 
						"Check whether the job type belongs to one of the employees listed in the system";
				rJobTitle.getComment().setText(text);

			}

		} 

	

	/**
	 * @wbp.parser.entryPoint
	 */
	private void validate() {
		
		
	}
	

	private void record() {
		if (jobTittleModel.getId() != 0) {
			jobTittleModel.setJobName(rJobTitle.getJobTitle().getText().trim());
			int positionId = positionModel.checkId(rJobTitle.getPosition().getSelectedItem().toString());
			if (positionId != 0) {
				
				jobTittleModel.setPositionId(positionId);
				boolean result = jobTittleModel.updateJob();
				
				if (result) {
					JOptionPane.showMessageDialog(principal, "You have successfully modified the job title", "success",
							JOptionPane.INFORMATION_MESSAGE, ICON);
					String success = "The job title has been modified successfully";
					jobTittleModel.setId(0);
					rJobTitle.getComment().setText(success);
					rJobTitle.getPosition().setSelectedItem("Selction");
					rJobTitle.getJobTitle().setText("");
					rJobTitle.getDelete().setVisible(false);
					rJobTitle.dispose();
					rJobTitle.setVisible(false);

					vjob = new Vjobtitle(principal, true);
					vjob.setControllerJobTittle(this);
					Tolist();
					vjob.setVisible(true);
				}
			} 
			else {
				
				JOptionPane.showMessageDialog(principal, "The job title has not been modified", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				String err = "The job title has not been modified";
				rJobTitle.getComment().setText(err);
				rJobTitle.getComment().setForeground(Color.RED);
			}
			
		}
		else {
			jobTittleModel.setJobName(rJobTitle.getJobTitle().getText().trim());
			int positionId = positionModel.checkId(rJobTitle.getPosition().getSelectedItem().toString());
			if (positionId != 0) {
				jobTittleModel.setPositionId(positionId);
				boolean result = jobTittleModel.insertJob();
				if (result) {
					JOptionPane.showMessageDialog(principal, "You have successfully registery the job title", "success",
							JOptionPane.INFORMATION_MESSAGE, ICON);
					String success = "The job title has been succesfully registered";

					rJobTitle.dispose();
					rJobTitle.setVisible(false);
					vjob = new Vjobtitle(principal, true);
					vjob.setControllerJobTittle(this);
					Tolist();
					vjob.setVisible(true);
					rJobTitle.getComment().setText(success);
					rJobTitle.getPosition().setSelectedItem("Select");
					rJobTitle.getJobTitle().setText("");
					rJobTitle.getDelete().setVisible(false);

				}
			} 
			else {
				JOptionPane.showMessageDialog(principal, "The job title has not registered", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				String err = "The job title has not registered";
				rJobTitle.getComment().setText(err);
				rJobTitle.getComment().setForeground(Color.RED);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		Object obj = ke.getSource();
		if (obj.equals(vjob.getTabelJobTittle())) {
			char b = ke.getKeyChar();
			if (vjob.getTextSearch().getText().length() > 50) {
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
		if (obj.equals(vjob.getTextSearch())) {
			String search = vjob.getTextSearch().getText();
			searchList(search, vjob.getTabelJobTittle());
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object mouseEvent = me.getSource();
		if (mouseEvent.equals(vjob.getTabelJobTittle())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vjob.getTabelJobTittle().getSelectedRow();
					int row1 = vjob.getTabelJobTittle().convertRowIndexToModel(row);
					DefaultTableModel tableModel = (DefaultTableModel) vjob.getTabelJobTittle().getModel();
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

}
