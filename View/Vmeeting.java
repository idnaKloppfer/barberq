package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import Controller.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Dimension;
import java.awt.Font;

/**
 *
 * @author idnak
 */
public class Vmeeting extends javax.swing.JDialog {
    
    private ControllerMeeting controllerMeeting;
    
    /**
     *
     * @param controllerMeeting
     */
    public void setControllerMeeting(ControllerMeeting controllerMeeting){
        
        this.controllerMeeting = controllerMeeting;
        setListener();
    }
    /**
     *
     * @return
     */
    public ControllerMeeting getControllerMeeting(){
        return controllerMeeting;
    }
    private void setListener(){
        newBtt.addActionListener(controllerMeeting);
        getTextSearch().addKeyListener(controllerMeeting);
        tableMeeting.addMouseListener(controllerMeeting);
    }

    /**
     *
     * @return
     */
    public JTextField getTextSearch() {
        return TextSearch;
    }

    /**
     *
     * @param TextSearch
     */
    public void setTextSearch(JTextField TextSearch) {
        this.TextSearch = TextSearch;
    }

    /**
     *
     * @return
     */
    public JTable gettableMeeting() {
        return tableMeeting;
    }

    /**
     *
     * @param tableMeeting
     */
    public void setTableMeeting(JTable tableMeeting) {
        this.tableMeeting = tableMeeting;
    }

    /**
     *
     * @return
     */
    public JLabel getCommend() {
        return comment;
    }

    /**
     *
     * @param commend
     */
    public void setCommend(JLabel commend) {
        this.comment = commend;
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
     *
     */
    public void close(){    
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Vmeeting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates new form Vmeeting
     * @param parent
     * @param modal
     */
    public Vmeeting(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        variablesForm();
        setTitle("Meeting");
        setResizable(false);
        setLocationRelativeTo(null);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void variablesForm() {

        jPanelTableMeeting = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        center = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        TextSearch = new javax.swing.JTextField();
        TextSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jPanelChoosing = new javax.swing.JPanel();
        newBtt = new javax.swing.JButton();
        exitBtt = new javax.swing.JButton();
        centerTable = new javax.swing.JPanel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        tableMeeting = new javax.swing.JTable();
        footer = new javax.swing.JPanel();
        comment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanelTableMeeting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/center.png"))); // NOI18N
        jPanelTableMeeting.setMaximumSize(new java.awt.Dimension(700,520));
        jPanelTableMeeting.setPreferredSize(new Dimension(700, 500));
        jPanelTableMeeting.setLayout(new java.awt.BorderLayout());

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/Meeting.png"))); // NOI18N
        header.setMaximumSize(new java.awt.Dimension(464, 75));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(464, 75));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        jPanelTableMeeting.add(header, java.awt.BorderLayout.NORTH);

        center.setOpaque(false);
        center.setLayout(new java.awt.BorderLayout(0, 10));

        jPanelSearch.setForeground(new java.awt.Color(0, 0, 0));
        jPanelSearch.setOpaque(false);
        jPanelSearch.setPreferredSize(new java.awt.Dimension(442, 40));
        jPanelSearch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        TextSearch.setForeground(new java.awt.Color(0, 0, 0));
        TextSearch.setPreferredSize(new Dimension(400, 37));
        TextSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSearchFocusGained(evt);
            }
        });
        TextSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textSearchKeyTyped(evt);
            }
        });
        jPanelSearch.add(TextSearch);

        center.add(jPanelSearch, java.awt.BorderLayout.NORTH);

        jPanelChoosing.setOpaque(false);
        jPanelChoosing.setPreferredSize(new java.awt.Dimension(448, 30));
        jPanelChoosing.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        newBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/new.png"))); // NOI18N
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

        exitBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/exit.png"))); // NOI18N
        exitBtt.setBorderPainted(false);
        exitBtt.setContentAreaFilled(false);
        exitBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtt.setFocusPainted(false);
        exitBtt.setPreferredSize(new java.awt.Dimension(150, 30));
        exitBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanelChoosing.add(exitBtt);

        center.add(jPanelChoosing, java.awt.BorderLayout.SOUTH);

        centerTable.setOpaque(false);
        centerTable.setLayout(new java.awt.BorderLayout());

        jScrollPaneTable.setOpaque(false);
        jScrollPaneTable.setPreferredSize(new java.awt.Dimension(500, 500));

        tableMeeting.setFont(new Font("Times New Roman", Font.PLAIN, 19)); // NOI18N
        tableMeeting.setForeground(new java.awt.Color(0, 0, 0));
        tableMeeting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
            		"Phone", "Name", "Last Name", "Date", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMeeting.setToolTipText("Users");
        tableMeeting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableMeeting.setOpaque(false);
        tableMeeting.setPreferredSize(new java.awt.Dimension(450, 200));
        tableMeeting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableMeetingKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableMeetingKeyTyped(evt);
            }
        });
        jScrollPaneTable.setViewportView(tableMeeting);

        centerTable.add(jScrollPaneTable, java.awt.BorderLayout.CENTER);

        center.add(centerTable, java.awt.BorderLayout.CENTER);

        jPanelTableMeeting.add(center, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(0, 102, 255));
        footer.setOpaque(false);
        footer.setPreferredSize(new java.awt.Dimension(0, 30));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new java.awt.Font("Serif", 2, 14)); // NOI18N
        comment.setForeground(new java.awt.Color(0, 0, 0));
        footer.add(comment, java.awt.BorderLayout.CENTER);

        jPanelTableMeeting.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelTableMeeting, "card2");

        pack();
    }// </editor-fold>                        

    private void textSearchFocusGained(java.awt.event.FocusEvent evt) {                                       
        String commend = "Find the appointment you want, either to modify it or cancel it or even to process it";
        getCommend().setText(commend);
    }                                      

    private void textSearchKeyTyped(java.awt.event.KeyEvent evt) {                                    
        //
    }                                   

    private void newBttActionPerformed(java.awt.event.ActionEvent evt) {                                       

    }                                      

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {                                     
        try {
            //  getController().getModel().conexionClosed();
     getControllerMeeting().rMeeting.close();
            finalize();
            
        } catch (Throwable ex) {
            Logger.getLogger(Vmeeting.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        
    }                                    

    private void tableMeetingKeyReleased(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void tableMeetingKeyTyped(java.awt.event.KeyEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    

    // Variables declaration - do not modify                     
    private javax.swing.JTextField TextSearch;
    private org.edisoncor.gui.panel.Panel jPanelTableMeeting;
    private javax.swing.JTable tableMeeting;
    private javax.swing.JPanel centerTable;
    private javax.swing.JPanel center;
    private javax.swing.JButton exitBtt;
    private javax.swing.JPanel footer;
    private org.edisoncor.gui.panel.Panel header;
    private javax.swing.JPanel jPanelChoosing;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JLabel comment;
    private javax.swing.JButton newBtt;
    // End of variables declaration                   
}
