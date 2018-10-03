package View;

import Controller.ControllerPerson;
import Controller.ControllerUsers;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author idnak
 */
public class Vuser extends javax.swing.JDialog {

    private ControllerUsers ControllerUsers;

    /**
     *
     * @return
     */
    public ControllerUsers getControllerUsers() {
        return ControllerUsers;
    }

    /**
     *
     * @param controllerUsers
     */
    public void setControllerUsers(ControllerUsers controllerUsers) {
        this.ControllerUsers = controllerUsers;
        setListener(controllerUsers);
    }
    
    /**
     *
     * @param controllerUsers
     */
    public void setListener(ControllerUsers controllerUsers){
      getTextSearch().addKeyListener(controllerUsers);
      getTableUser().addMouseListener(controllerUsers);
      getNewBtt().addActionListener(controllerUsers);
      getExitBtt().addActionListener(new ActionListener() {
    	  @Override
    	  public void actionPerformed(ActionEvent e) {
    		  try {
				this.finalize();
				setVisible(false);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		  
    	  }
      });

    }

    /**
     *
     * @return
     */
    public JTable getTableUser() {
        return tableUser;
    }

    /**
     *
     * @param tableUser
     */
    public void setTableUser(JTable tableUser) {
        this.tableUser = tableUser;
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
    public JButton getExitBtt() {
        return exitBtt;
    }

    /**
     *
     * @param exitBtt
     */
    public void setExitBtt(JButton exitBtt) {
        this.exitBtt = exitBtt;
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
     * Creates new form Vuser
     * @param parent
     * @param model
     */
    public Vuser(java.awt.Frame parent, boolean model) {
        super(parent, model);
        variablesForm();
        setTitle("Users");
        setControllerUsers(new ControllerUsers(this));
        this.centerWindow();
      
    }
    private void centerWindow() {
    	// Get the size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     // Get the size of the application window
        Dimension frameSize = getSize();
     // Verify the size of the application window
     // do not exceed the size of the screen
        if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
        }
     // Centers the application window on the screen
        setLocation((screenSize.width - frameSize.width) / 2,
        (screenSize.height - frameSize.height) / 2);
    }
 
    private void variablesForm() {

        usersPanel = new org.edisoncor.gui.panel.Panel();
        usersPanel.setPreferredSize(new Dimension(600, 500));
        usersPanel.setColorSecundario(Color.WHITE);
        usersPanel.setForeground(Color.WHITE);
        header = new org.edisoncor.gui.panel.Panel();
        header.setForeground(Color.WHITE);
        header.setColorSecundario(Color.WHITE);
        center = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        textSearch = new javax.swing.JTextField();
        textSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jPanelChoosing = new javax.swing.JPanel();
        newBtt = new javax.swing.JButton();
        exitBtt = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        comment = new javax.swing.JLabel();

        setPreferredSize(new Dimension(600, 500));
        setResizable(false);

        usersPanel.setMaximumSize(new java.awt.Dimension(464, 422));
        usersPanel.setLayout(new java.awt.BorderLayout());

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/users.png"))); 
        header.setMaximumSize(new java.awt.Dimension(464, 75));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(464, 75));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        usersPanel.add(header, java.awt.BorderLayout.NORTH);

        center.setOpaque(false);
        center.setLayout(new java.awt.BorderLayout(0, 10));

        jPanelSearch.setForeground(new java.awt.Color(0, 0, 0));
        jPanelSearch.setOpaque(false);
        jPanelSearch.setPreferredSize(new java.awt.Dimension(442, 40));
        jPanelSearch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

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
        exitBtt.setPreferredSize(new java.awt.Dimension(150, 30));
        exitBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBttActionPerformed(evt);
            }
        });
        jPanelChoosing.add(exitBtt);

        center.add(jPanelChoosing, java.awt.BorderLayout.SOUTH);

        tablePanel.setOpaque(false);
        tablePanel.setLayout(new java.awt.BorderLayout());

        jScrollPaneTable.setOpaque(false);
        jScrollPaneTable.setPreferredSize(new java.awt.Dimension(400, 403));

        tableUser.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Id", "User"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUser.setToolTipText("Users");
        tableUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableUser.setOpaque(false);
        tableUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableUserKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableUserKeyTyped(evt);
            }
        });
        jScrollPaneTable.setViewportView(tableUser);

        tablePanel.add(jScrollPaneTable, java.awt.BorderLayout.CENTER);

        center.add(tablePanel, java.awt.BorderLayout.CENTER);

        usersPanel.add(center, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(0, 102, 255));
        footer.setOpaque(false);
        footer.setPreferredSize(new java.awt.Dimension(0, 30));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new Font("Serif", Font.ITALIC, 16)); 
        comment.setForeground(new java.awt.Color(0, 0, 0));
        footer.add(comment, java.awt.BorderLayout.CENTER);

        usersPanel.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(usersPanel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void tableUserKeyReleased(java.awt.event.KeyEvent evt) {
    	
    }

    private void tableUserKeyTyped(java.awt.event.KeyEvent evt) {
    	
    }

    private void newBttActionPerformed(java.awt.event.ActionEvent evt) {
    	
    }


    private void exitBttActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void textSearchFocusGained(java.awt.event.FocusEvent evt) {
        String text = "Search for the matching user";
        getComment().setText(text);
    }

    private void textSearchKeyTyped(java.awt.event.KeyEvent evt) {
      this.validate(evt);
    }
    private void validate (KeyEvent e){
        char b = e.getKeyChar();
        

    }
    

    private javax.swing.JTextField textSearch;
    private org.edisoncor.gui.panel.Panel usersPanel;
    private javax.swing.JTable tableUser;
    private javax.swing.JPanel tablePanel;
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
