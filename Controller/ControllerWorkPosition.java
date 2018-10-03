package Controller;

import Model.Position;
import View.Principal;
import View.Rperson;
import View.Rworkposition;
import View.Vworkposition;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
public class ControllerWorkPosition implements ActionListener, MouseListener, KeyListener {

	DefaultTableModel dm;
	private Vworkposition vWorkPosition;
	private Principal principal;
	private Rworkposition rworkPosition;
	private Position positionModel;
	private ImageIcon icon=new ImageIcon(ControllerWorkPosition.class.getResource("/View/img/checkmark.png"));

    /**
     *
     * @param vWorkPosition
     */
    public ControllerWorkPosition(Vworkposition vWorkPosition) {
		this.vWorkPosition = vWorkPosition;
		positionModel = new Position();
		rworkPosition = new Rworkposition(principal, true);
		this.Tolist();
	}

	private void Tolist() {
		String[][] Information = positionModel.resultList();
		vWorkPosition.getWorkPositionTable()
				.setModel(new javax.swing.table.DefaultTableModel(Information, new String[] { "Work Position" }) {
					boolean[] canEdit = new boolean[] { false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		vWorkPosition.getWorkPositionTable().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		vWorkPosition.getWorkPositionTable().setAutoscrolls(true);
	}

    /**
     *
     * @param query
     * @param jTableSearch
     */
    public void Searchlist(String query, JTable jTableSearch) {
		dm = (DefaultTableModel) jTableSearch.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
		jTableSearch.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}

    /**
     *
     * @param name
     */
    public void Capturedata(String name) {

		boolean found = positionModel.matchingModel(name);
		if (found) {
			vWorkPosition.dispose();
			vWorkPosition.setVisible(false);
			rworkPosition = new Rworkposition(principal, true);

			rworkPosition.getPosition().setText(positionModel.getPosition());

			rworkPosition.getDelete().setEnabled(true);
			rworkPosition.getDelete().setVisible(true);
			rworkPosition.setControllerWorkPosition(this);
			rworkPosition.setVisible(true);

		} else {

			JOptionPane.showMessageDialog(new JFrame(), "work position is  not found", "Work Position",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if (obj.equals(vWorkPosition.getNewBtt())) {
			vWorkPosition.setVisible(false);
			vWorkPosition.dispose();

			rworkPosition=new Rworkposition(principal, true);
			rworkPosition.setControllerWorkPosition(this);
			rworkPosition.getDelete().setVisible(false);
			rworkPosition.setVisible(true);
		} else if (obj.equals(rworkPosition.getExit())) {
			rworkPosition.setVisible(false);
			rworkPosition.dispose();
			vWorkPosition = new Vworkposition(principal, true);
			vWorkPosition.setControllerWorkPosition(this);
			this.Tolist();
			vWorkPosition.setVisible(true);
		} else if (obj.equals(rworkPosition.getRegister())) {
			this.validate();
		} else if (obj.equals(rworkPosition.getDelete())) {
			

			int opt = JOptionPane.showConfirmDialog(principal, "Confirm delete?", "Delete work position",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (opt != 0) {// if you regret and want to keep on with the person on the list
				
			}
			else {
				boolean result = positionModel.deletePosition();
				if (result) {
					rworkPosition.dispose();
					rworkPosition.setVisible(false);
					vWorkPosition = new Vworkposition(principal, true);
					vWorkPosition.setControllerWorkPosition(this);
					Tolist();
					vWorkPosition.setVisible(true);
				} else {
					String txt = "The job position has not been removed, A previous removed of the job title related to the position";
					rworkPosition.getComment().setText(txt);
					rworkPosition.getComment().setForeground(Color.RED);
				}
			}

		}
	}

	private void validate() {
		if (rworkPosition.getPosition().getText().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "Can not register an empty position", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String txt = "Can not register an empty position ";
			rworkPosition.getComment().setText(txt);
			rworkPosition.getComment().setForeground(Color.RED);
			rworkPosition.getPosition().setFocusable(true);
		}
//		else if (rworkPosition.getPosition().getText().contains("  ")
//				&& rworkPosition.getPosition().getText().length() < 3) {
//			String txt = "You can not register several blank spaces in the positionModel and that it is not less than 3 characters";
//			rworkPosition.getComment().setText(txt);
//			rworkPosition.getComment().setForeground(Color.ORANGE);
//			rworkPosition.getPosition().setFocusable(true);
//		} 
		else {
			record();
		}
	}

	private void record() {
		if (positionModel.getId() != 0) {
			positionModel.setPosition(rworkPosition.getPosition().getText());
			Boolean Result = positionModel.updatePosition();
			if (Result) {
				JOptionPane.showMessageDialog(principal, "You have successfully modified the position", "success",
						JOptionPane.INFORMATION_MESSAGE,icon);
				
				String txt = "You have successfully modified the position "
						+ rworkPosition.getPosition().getText();
				positionModel.setId(0);
				rworkPosition.getComment().setText(txt);
				rworkPosition.getComment().setForeground(Color.BLACK);
				rworkPosition.dispose();
				rworkPosition.setVisible(false);
				vWorkPosition = new Vworkposition(principal, true);
				vWorkPosition.setControllerWorkPosition(this);
				Tolist();
				vWorkPosition.setVisible(true);
			} else {
				String txt = "You have not successfully modified the position , "
						+ rworkPosition.getPosition().getText() + " Check.";
				rworkPosition.getComment().setText(txt);
				rworkPosition.getComment().setForeground(Color.RED);
			}

		} else {
			positionModel.setPosition(rworkPosition.getPosition().getText());
			boolean result = positionModel.insertPosition();
			if (result) {
				JOptionPane.showMessageDialog(principal, "You have successfully registered the position", "success",
						JOptionPane.INFORMATION_MESSAGE,icon);
				rworkPosition.dispose();
				rworkPosition.setVisible(false);
				vWorkPosition = new Vworkposition(principal, true);
				vWorkPosition.setControllerWorkPosition(this);
				Tolist();
				vWorkPosition.setVisible(true);
				String txt = "You have successfully registered the position "
						+ rworkPosition.getPosition().getText();
				rworkPosition.getComment().setText(txt);
				rworkPosition.getComment().setForeground(Color.BLACK);
			} else {
				JOptionPane.showMessageDialog(principal, "You have not successfully registered the position "
						+ rworkPosition.getPosition().getText() + " Check.", "Eror",
						JOptionPane.INFORMATION_MESSAGE);
				String txt = "You have not successfully registered the position "
						+ rworkPosition.getPosition().getText() + " Check.";
				rworkPosition.getComment().setText(txt);
				rworkPosition.getComment().setForeground(Color.RED);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if (obj.equals(vWorkPosition.getWorkPositionTable())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vWorkPosition.getWorkPositionTable().getSelectedRow();
					int row1 = vWorkPosition.getWorkPositionTable().convertRowIndexToModel(row);
					DefaultTableModel tableModel = (DefaultTableModel) vWorkPosition.getWorkPositionTable().getModel();
					String workPosition = (String) tableModel.getValueAt(row1, 0);
					Capturedata(workPosition);
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
		Object obj = ke.getSource();
		if (obj.equals(vWorkPosition.getWorkPositionTable())) {
			char b = ke.getKeyChar();
			if (vWorkPosition.getTextSearch().getText().length() > 50) {
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
		if (obj.equals(vWorkPosition.getTextSearch())) {
			String search = vWorkPosition.getTextSearch().getText();
			Searchlist(search, vWorkPosition.getWorkPositionTable());
		}
	}

}
