package View;

import Controller.ControllerClient;
import Controller.ControllerEmployee;
import Controller.ControllerMeeting;
//import Controller.ControllerMeeting;
import Controller.ControllerPerson;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.edisoncor.gui.panel.Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

/**
 *
 * @author idnak
 */
public class Vperson extends javax.swing.JDialog {
    private ControllerPerson controllerPerson;
    private ControllerEmployee controllerEmployee;
    private ControllerMeeting controllerMeeting;
    private ControllerClient controllerClient;

    /**
     *
     * @param controllerPerson
     */
    public void setControllerPerson(ControllerPerson controllerPerson){
        this.controllerPerson = controllerPerson;
        setListener(controllerPerson);
    }
    
    /**
     *
     * @return
     */
    public ControllerPerson getControllerPerson(){
        return controllerPerson;
    }
    
    /**
     *
     * @param controllerPerson
     */
    public void setListener(ControllerPerson controllerPerson){
        
        getTextSearch().addKeyListener(controllerPerson);
        newPerson.addActionListener(controllerPerson);
        tablePerson.addMouseListener(controllerPerson);
    }
    
    /**
     *
     * @param controllerEmployee
     */
    public void setListener(ControllerEmployee controllerEmployee){
        getTextSearch().addKeyListener(controllerEmployee);
        newPerson.addActionListener(controllerEmployee);
        tablePerson.addMouseListener(controllerEmployee);
    }

    /**
     *
     * @param controllerClient
     */
    public void setListener(ControllerClient controllerClient){
        getTextSearch().addKeyListener(controllerClient);
        newPerson.addActionListener(controllerClient);
        tablePerson.addMouseListener(controllerClient);
    }

    /**
     *
     * @param controllerMeeting
     */
    public void setListener(ControllerMeeting controllerMeeting){
    	getTextSearch().addKeyListener(controllerMeeting);
    	newPerson.addActionListener(controllerMeeting);
    	tablePerson.addMouseListener(controllerMeeting);
    }

    /**
     *
     * @param control
     */
    public void setControllerEmployee(ControllerEmployee control){
        this.controllerEmployee = control;
        setListener(controllerEmployee);
    }

    /**
     *
     * @return
     */
    public ControllerEmployee getControllerEmployee(){
        return controllerEmployee;
    }

    /**
     *
     * @param control
     */
    public void setController(ControllerClient control){
        this.controllerClient = control;
        setListener(controllerClient);
    }

    /**
     *
     * @return
     */
    public ControllerClient getControllerClient(){
        return controllerClient;
    }

    /**
     *
     * @param controller
     */
    public void setController(ControllerMeeting controller){
        this.controllerMeeting = controller;
        setListener(controllerMeeting);
    }

    /**
     *
     * @return
     */
    public ControllerMeeting getControllerMeeting(){
        return controllerMeeting;
    }

     //Getters y setters

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
    public JTable getTablePerson() {
        return tablePerson;
    }

    /**
     *
     * @param tablePerson
     */
    public void setTablePerson(JTable tablePerson) {
        this.tablePerson = tablePerson;
    }

    /**
     *
     * @return
     */
    public JButton getExit() {
        return exit;
    }

    /**
     *
     * @param exit
     */
    public void setExit(JButton exit) {
        this.exit = exit;
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
    public JButton getNewPerson() {
        return newPerson;
    }

    /**
     *
     * @param newPerson
     */
    public void setNewPerson(JButton newPerson) {
        this.newPerson = newPerson;
    }

    /**
     *
     * @return
     */
    public Panel getHeader() {
        return header;
    }

    /**
     *
     * @param header
     */
    public void setHeader(Panel header) {
        this.header = header;
    }

    /**
     * Creates new form Vperson
     * @param parent
     * @param model
     */
    public Vperson(java.awt.Frame parent, boolean model) {
        super(parent, model);
        setForeground(Color.BLACK);
        variablesForm();
        setTitle("person");
        setResizable(false);
        setLocationRelativeTo(null);
  
    }

 
    private void variablesForm() {

        personPanel = new org.edisoncor.gui.panel.Panel();
        personPanel.setColorSecundario(Color.WHITE);
        personPanel.setForeground(Color.WHITE);
        header = new org.edisoncor.gui.panel.Panel();
        header.setColorSecundario(Color.WHITE);
        header.setForeground(Color.WHITE);
        header.setBackground(Color.WHITE);
        header.setColorPrimario(Color.BLACK);
        center = new javax.swing.JPanel();
        center.setForeground(Color.WHITE);
        center.setBackground(Color.WHITE);
        jPanelSearch = new javax.swing.JPanel();
        textSearch = new javax.swing.JTextField();
        textSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jPanelChoosing = new javax.swing.JPanel();
        jPanelChoosing.setForeground(Color.WHITE);
        newPerson = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        tablePerson = new javax.swing.JTable();
        tablePerson.setBackground(Color.WHITE);
        footer = new javax.swing.JPanel();
        footer.setForeground(Color.WHITE);
        comment = new javax.swing.JLabel();
        comment.setBackground(Color.WHITE);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        personPanel.setMaximumSize(new java.awt.Dimension(600, 451));
        personPanel.setPreferredSize(new Dimension(600, 500));
        personPanel.setLayout(new java.awt.BorderLayout());

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/vperson.jpg")));
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

        personPanel.add(header, java.awt.BorderLayout.NORTH);

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
                TextsearchFocusGained(evt);
            }
        });
        textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextSearchKeyTyped(evt);
            }
        });
        jPanelSearch.add(textSearch);

        center.add(jPanelSearch, java.awt.BorderLayout.NORTH);

        jPanelChoosing.setOpaque(false);
        jPanelChoosing.setPreferredSize(new java.awt.Dimension(448, 30));
        jPanelChoosing.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        newPerson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/new.png"))); 
        newPerson.setBorderPainted(false);
        newPerson.setContentAreaFilled(false);
        newPerson.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newPerson.setFocusPainted(false);
        newPerson.setMinimumSize(new java.awt.Dimension(150, 30));
        newPerson.setPreferredSize(new java.awt.Dimension(150, 30));
        newPerson.setRolloverEnabled(true);
        newPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPersonActionPerformed(evt);
            }
        });
        jPanelChoosing.add(newPerson);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/exit.png"))); 
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.setFocusPainted(false);
        exit.setPreferredSize(new java.awt.Dimension(150, 30));
        exit.setRolloverEnabled(true);
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanelChoosing.add(exit);

        center.add(jPanelChoosing, java.awt.BorderLayout.SOUTH);

        tablePanel.setOpaque(false);
        tablePanel.setLayout(new java.awt.BorderLayout());

        jScrollPaneTable.setOpaque(false);
        jScrollPaneTable.setPreferredSize(new java.awt.Dimension(400, 403));

        tablePerson.setFont(new Font("Times New Roman", Font.PLAIN, 19)); 
        tablePerson.setForeground(new java.awt.Color(0, 0, 0));
        tablePerson.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID", "Name", "Last name "
                
            }

        ) 
        {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePerson.setToolTipText("Users");
        tablePerson.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablePerson.setOpaque(false);
        tablePerson.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablePersonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablePersonKeyTyped(evt);
            }
        });
        jScrollPaneTable.setViewportView(tablePerson);

        tablePanel.add(jScrollPaneTable, java.awt.BorderLayout.CENTER);

        center.add(tablePanel, java.awt.BorderLayout.CENTER);

        personPanel.add(center, java.awt.BorderLayout.CENTER);

        footer.setBackground(Color.WHITE);
        footer.setOpaque(false);
        footer.setPreferredSize(new java.awt.Dimension(0, 30));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new Font("Serif", Font.ITALIC, 16)); 
        comment.setForeground(Color.BLACK);
        footer.add(comment, java.awt.BorderLayout.CENTER);

        personPanel.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(personPanel, "card2");

        pack();
    }
    private void TextsearchFocusGained(java.awt.event.FocusEvent evt) {
        String notification = "Search for the matching person";
        getComment().setText(notification);
    }

    private void TextSearchKeyTyped(java.awt.event.KeyEvent evt) {
    	
    }

    private void newPersonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    }

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {
       this.setVisible(false);
    }

    private void tablePersonKeyReleased(java.awt.event.KeyEvent evt) {
    	
    }

    private void tablePersonKeyTyped(java.awt.event.KeyEvent evt) {
    	
    }


    private javax.swing.JTextField textSearch;
    private org.edisoncor.gui.panel.Panel personPanel;
    private javax.swing.JTable tablePerson;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JPanel center;
    private javax.swing.JButton exit;
    private javax.swing.JPanel footer;

    /**
     *
     */
    public org.edisoncor.gui.panel.Panel header;
    private javax.swing.JPanel jPanelChoosing;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JLabel comment;
    private javax.swing.JButton newPerson;
    
}
