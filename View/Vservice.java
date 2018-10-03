package View;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import Controller.ControllerService;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 *
 * @author idnak
 */
public class Vservice extends javax.swing.JDialog {

	private ControllerService controllerService;

    /**
     *
     * @param controllerService
     */
    public void setControllerService(ControllerService controllerService) {
		this.controllerService = controllerService;
		setListener();
	}

    /**
     *
     * @return
     */
    public ControllerService getControllerService() {
		return controllerService;
	}

	private void setListener() {
		newBtt.addActionListener(controllerService);
		textSearch.addKeyListener(controllerService);
		serviceTable.addMouseListener(controllerService);
	}

    /**
     *
     * @return
     */
    public JTextField getTextSearch() {
		return textSearch;
	}

    /**
     *
     * @param textSearch
     */
    public void setTextSearch(JTextField textSearch) {
		this.textSearch = textSearch;
	}

    /**
     *
     * @return
     */
    public JTable getServiceTable() {
		return serviceTable;
	}

    /**
     *
     * @param serviceTable
     */
    public void setServiceTable(JTable serviceTable) {
		this.serviceTable = serviceTable;
	}

    /**
     *
     * @return
     */
    public JLabel getComment() {
		return comment;
	}

    /**
     *
     * @param comment
     */
    public void setComment(JLabel comment) {
		this.comment = comment;
	}

    /**
     *
     * @return
     */
    public JButton getNewBtt() {
		return newBtt;
	}

    /**
     *
     * @param newBtt
     */
    public void setNewBtt(JButton newBtt) {
		this.newBtt = newBtt;
	}

	/**
	 * Creates new form Vservice
	 * 
	 * @param parent
	 * @param model
	 */
	public Vservice(java.awt.Frame parent, boolean model) {
		super(parent, model);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		variablesForm();
		setTitle("Services");
		setResizable(false);
		setLocationRelativeTo(null);
	}


	private void variablesForm() {

		servicePanel = new org.edisoncor.gui.panel.Panel();
		servicePanel.setForeground(Color.WHITE);
		servicePanel.setColorSecundario(Color.WHITE);
		header = new org.edisoncor.gui.panel.Panel();
		center = new javax.swing.JPanel();
		jPanelSearch = new javax.swing.JPanel();
		textSearch = new javax.swing.JTextField();
		textSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jPanelChoosing = new javax.swing.JPanel();
		newBtt = new javax.swing.JButton();
		exitBtt = new javax.swing.JButton();
		jPanelTable = new javax.swing.JPanel();
		jScrollPaneTable = new javax.swing.JScrollPane();
		serviceTable = new javax.swing.JTable();
		footer = new javax.swing.JPanel();
		comment = new javax.swing.JLabel();

		getContentPane().setLayout(new java.awt.CardLayout());

		servicePanel.setMaximumSize(new java.awt.Dimension(440, 451));
		servicePanel.setPreferredSize(new Dimension(600, 500));
		servicePanel.setLayout(new java.awt.BorderLayout());

		header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/Service.jpg"))); 
		header.setMaximumSize(new java.awt.Dimension(464, 75));
		header.setOpaque(false);
		header.setPreferredSize(new java.awt.Dimension(464, 75));

		javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
		header.setLayout(headerLayout);
		headerLayout.setHorizontalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 440, Short.MAX_VALUE));
		headerLayout.setVerticalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		servicePanel.add(header, java.awt.BorderLayout.NORTH);

		center.setOpaque(false);
		center.setLayout(new java.awt.BorderLayout(0, 10));

		jPanelSearch.setForeground(new java.awt.Color(0, 0, 0));
		jPanelSearch.setOpaque(false);
		jPanelSearch.setPreferredSize(new java.awt.Dimension(442, 40));
		jPanelSearch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

		textSearch.setForeground(new java.awt.Color(0, 0, 0));
		textSearch.setPreferredSize(new java.awt.Dimension(400, 37));
		textSearch.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				textSearchFocusGained(evt);
			}
		});
		textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				textSearchKeyTyped(evt);
			}
		});
		jPanelSearch.add(textSearch);

		center.add(jPanelSearch, java.awt.BorderLayout.NORTH);

		jPanelChoosing.setOpaque(false);
		jPanelChoosing.setPreferredSize(new java.awt.Dimension(448, 30));
		jPanelChoosing.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

		newBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/new.png"))); 
		newBtt.setBorderPainted(false);
		newBtt.setContentAreaFilled(false);
		newBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		newBtt.setFocusPainted(false);
		newBtt.setMaximumSize(new java.awt.Dimension(150, 30));
		newBtt.setMinimumSize(new java.awt.Dimension(150, 30));
		newBtt.setPreferredSize(new java.awt.Dimension(150, 30));
		newBtt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newBttActionPerformed(evt);
			}
		});
		jPanelChoosing.add(newBtt);

		exitBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/exit.png"))); 
		exitBtt.setBorderPainted(false);
		exitBtt.setContentAreaFilled(false);
		exitBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		exitBtt.setFocusPainted(false);
		exitBtt.setMaximumSize(new java.awt.Dimension(150, 30));
		exitBtt.setMinimumSize(new java.awt.Dimension(150, 30));
		exitBtt.setPreferredSize(new java.awt.Dimension(150, 30));
		exitBtt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitBttActionPerformed(evt);
			}
		});
		jPanelChoosing.add(exitBtt);

		center.add(jPanelChoosing, java.awt.BorderLayout.SOUTH);

		jPanelTable.setOpaque(false);
		jPanelTable.setLayout(new java.awt.BorderLayout());

		jScrollPaneTable.setOpaque(false);
		jScrollPaneTable.setPreferredSize(new java.awt.Dimension(400, 403));

		serviceTable.setFont(new Font("Times New Roman", Font.PLAIN, 19)); 
		serviceTable.setForeground(new java.awt.Color(0, 0, 0));
		serviceTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, { null, null } },
				new String[] { "Service", "Price" }) {
			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		serviceTable.setToolTipText("Users");
		serviceTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		serviceTable.setOpaque(false);
		serviceTable.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				serviceTableKeyReleased(evt);
			}

			public void keyTyped(java.awt.event.KeyEvent evt) {
				serviceTableKeyTyped(evt);
			}
		});
		jScrollPaneTable.setViewportView(serviceTable);

		jPanelTable.add(jScrollPaneTable, java.awt.BorderLayout.CENTER);

		center.add(jPanelTable, java.awt.BorderLayout.CENTER);

		servicePanel.add(center, java.awt.BorderLayout.CENTER);

		footer.setBackground(new java.awt.Color(0, 102, 255));
		footer.setOpaque(false);
		footer.setPreferredSize(new java.awt.Dimension(0, 30));
		footer.setLayout(new java.awt.BorderLayout());

		comment.setFont(new java.awt.Font("Serif", 2, 14)); 
		comment.setForeground(new java.awt.Color(0, 0, 0));
		footer.add(comment, java.awt.BorderLayout.CENTER);

		servicePanel.add(footer, java.awt.BorderLayout.SOUTH);

		getContentPane().add(servicePanel, "card2");

		pack();
	}

	private void textSearchFocusGained(java.awt.event.FocusEvent evt) {
		String text = "Search for the service you want";
		getComment().setText(text);
	}

	private void textSearchKeyTyped(java.awt.event.KeyEvent evt) {
		
	}

	private void newBttActionPerformed(java.awt.event.ActionEvent evt) {
		
	}

	private void exitBttActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void serviceTableKeyReleased(java.awt.event.KeyEvent evt) {
		
	}

	private void serviceTableKeyTyped(java.awt.event.KeyEvent evt) {
		
	}

	private javax.swing.JTextField textSearch;
	private org.edisoncor.gui.panel.Panel servicePanel;
	private javax.swing.JTable serviceTable;
	private javax.swing.JPanel jPanelTable;
	private javax.swing.JPanel center;
	private javax.swing.JButton exitBtt;
	private javax.swing.JPanel footer;
	private org.edisoncor.gui.panel.Panel header;
	private javax.swing.JPanel jPanelChoosing;
	private javax.swing.JPanel jPanelSearch;
	private javax.swing.JScrollPane jScrollPaneTable;
	private javax.swing.JLabel comment;
	private javax.swing.JButton newBtt;
	
}
