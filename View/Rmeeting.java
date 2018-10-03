
package View;

import Controller.ControllerMeeting;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;


import lu.tudor.santec.jtimechooser.*;

/**
 *
 * @author idnak
 */
public class Rmeeting extends JDialog {
    
    private ControllerMeeting controllerMeeting;
    private Principal principal;
    
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
       btnProcess.addActionListener(controllerMeeting);
       btnDelete.addActionListener(controllerMeeting);
       btnExit.addActionListener(controllerMeeting);
       btnUpdate.addActionListener(controllerMeeting);
       
       sHaircut.addActionListener(controllerMeeting);
       sEmployee.addActionListener(controllerMeeting);
       sClient.addActionListener(controllerMeeting);
       
       btnAdd.addActionListener(controllerMeeting);
       btnBack.addActionListener(controllerMeeting);
       
       servicesList.addListSelectionListener(controllerMeeting);
       selectService.addListSelectionListener(controllerMeeting);
       getChooseDateMetting().getDateEditor().addPropertyChangeListener((PropertyChangeEvent pce) -> {
            getControllerMeeting().reloadEmployee();
        });
       
       btnTime.addActionListener((ActionEvent ae) -> {
           
           selectTime.setVisible(true);
           if(getControllerMeeting().update){
                getBtnUpdate().setVisible(true);
                getBtnAdd().setEnabled(true);
                getBtnBack().setEnabled(true);
           }
          
       });
       
      
    }

    /**
     *
     * @return
     */
    public JDateChooser getChooseDateMetting() {
        return chooseDateMetting;
    }

    /**
     *
     * @param chooseDateMetting
     */
    public void setChooseDateMetting(JDateChooser chooseDateMetting) {
        this.chooseDateMetting = chooseDateMetting;
    }
    
    /**
     *
     * @return
     */
    public JTimeChooser getTime(){
        time.setShowSeconds(true);
        return time;
    }

    /**
     *
     * @return
     */
    public JTimeChooser getRankTime() {
        return rankTime;
    }

    /**
     *
     * @param rankTime
     */
    public void setRankTime(JTimeChooser rankTime) {
        this.rankTime = rankTime;
    }
  
    /**
     *
     * @return
     */
    public JButton getDelete() {
        return btnDelete;
    }

    /**
     *
     * @param delete
     */
    public void setDelete(JButton delete) {
        this.btnDelete = delete;
    }

    /**
     *
     * @return
     */
    public JButton getBtnAdd() {
        return btnAdd;
    }

    /**
     *
     * @param btnAdd
     */
    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }
    
    /**
     *
     * @return
     */
    public JTextField getPhoneClient() {
        return phoneClient;
    }
    
    /**
     *
     * @param phoneClient
     */
    public void setPhoneClient(JTextField phoneClient) {
        this.phoneClient = phoneClient;
    }
    
    /**
     *
     * @return
     */
    public JTextField getEmployeeSupport() {
        return employeeSupport;
    }
    
    /**
     *
     * @param employeeSupport
     */
    public void setEmployeeSupport(JTextField employeeSupport) {
        this.employeeSupport = employeeSupport;
    }
    
    /**
     *
     * @return
     */
    public JButton getBtnExit() {
        return btnExit;
    }

    /**
     *
     * @param exit
     */
    public void setBtnExit(JButton exit) {
        this.btnExit = exit;
    }

    /**
     *
     * @return
     */
    public JButton getBtnProcess() {
        return btnProcess;
    }

    /**
     *
     * @param btnProcess
     */
    public void setBtnProcess(JButton btnProcess) {
        this.btnProcess = btnProcess;
    }

    /**
     *
     * @return
     */
    public JTextField getHaircut() {
        return haircut;
    }

    /**
     *
     * @param haircut
     */
    public void setHaircut(JTextField haircut) {
        this.haircut = haircut;
    }

    /**
     *
     * @return
     */
    public JButton getBtnBack() {
        return btnBack;
    }

    /**
     *
     * @param btnBack
     */
    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
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
    public JTextField getFirstNameClient() {
        return firstNameClient;
    }

    /**
     *
     * @param firstNameClient
     */
    public void setFirstNameClient(JTextField firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    /**
     *
     * @return
     */
    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    /**
     *
     * @param btnUpdate
     */
    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }
   
    /**
     *
     * @return
     */
    public JButton getSClient() {
        return sClient;
    }

    /**
     *
     * @param sClient
     */
    public void setSClient(JButton sClient) {
        this.sClient = sClient;
    }
   
    /**
     *
     * @return
     */
    public JList<String> getSelectService() {
        return selectService;
    }

    /**
     *
     * @param selectService
     */
    public void setGetSelectService(JList<String> selectService) {
        this.selectService = selectService;
    }

    /**
     *
     * @return
     */
    public JButton getSEmployee() {
        return sEmployee;
    }

    /**
     *
     * @param semployee
     */
    public void setSEmployee(JButton semployee) {
        this.sEmployee = semployee;
    }

    /**
     *
     * @return
     */
    public JList<String> getServicesList() {
        return servicesList;
    }

    /**
     *
     * @param servicesList
     */
    public void setServicesList(JList<String> servicesList) {
        this.servicesList = servicesList;
    }

    /**
     *
     * @return
     */
    public JButton getSHaircut() {
        return sHaircut;
    }

    /**
     *
     * @param sHaircut
     */
    public void setSHaircut(JButton sHaircut) {
        this.sHaircut = sHaircut;
    }

    /**
     *
     * @return
     */
    public JTextField getLastNameclient() {
        return lastNameclient;
    }

    /**
     *
     * @param lastNameclient
     */
    public void setLastNameclient(JTextField lastNameclient) {
        this.lastNameclient = lastNameclient;
    }

    /**
     *
     * @return
     */
    public JTabbedPane getMoveService() {
        return moveService;
    }

    /**
     *
     * @param moveService
     */
    public void setMoveService(JTabbedPane moveService) {
        this.moveService = moveService;
    }

    /**
     *
     * @return
     */
    public JButton getBtnTime() {
        return btnTime;
    }

    /**
     *
     * @param btnTime
     */
    public void setBtnTime(JButton btnTime) {
        this.btnTime = btnTime;
    }
    
    /**
     *
     */
    @SuppressWarnings("FinalizeCalledExplicitly")
     public void close(){    
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(Vmeeting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates new form Rmeeting
     * @param parent
     * @param bool
     */
    public Rmeeting(java.awt.Frame parent, boolean bool) {
        super(parent, bool);
        initComponents();
        setTitle("Meeting");
        setResizable(false);
        setLocationRelativeTo(null);
        getChooseDateMetting().setMinSelectableDate(new Date());
      
        getChooseDateMetting().setDate(new Date());
        
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        DefaultListModel modelList = new DefaultListModel();
        this.selectService.setModel(modelList);
        selectTime = new SelectTime();

        getServicesList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code"> 
    private void initComponents() {

        panelParent = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        centerPanel = new javax.swing.JPanel();
        center = new javax.swing.JPanel();
        Lperson = new javax.swing.JPanel();
        jLabelPhoneClient = new javax.swing.JLabel();
        phoneClient = new javax.swing.JTextField();
        sClient = new javax.swing.JButton();
        jLabelFirstName = new javax.swing.JLabel();
        jLabelLastName = new javax.swing.JLabel();
        lastNameclient = new javax.swing.JTextField();
        firstNameClient = new javax.swing.JTextField();
        jLabelChooseDate = new javax.swing.JLabel();
        moveService = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectService = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        servicesList = new javax.swing.JList<>();
        btnAdd = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabelService = new javax.swing.JLabel();
        jLabelSelrctedService = new javax.swing.JLabel();
        jLabelHairCut = new javax.swing.JLabel();
        employeeSupport = new javax.swing.JTextField();
        sEmployee = new javax.swing.JButton();
        jLabelEmployee = new javax.swing.JLabel();
        haircut = new javax.swing.JTextField();
        sHaircut = new javax.swing.JButton();
        chooseDateMetting = new com.toedter.calendar.JDateChooser();
        jLabelSelectTimeMeeting = new javax.swing.JLabel();
        btnTime = new javax.swing.JButton();
        optionsSelect = new javax.swing.JPanel();
        btnProcess = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        comment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(737, 440));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelParent.setMaximumSize(new java.awt.Dimension(737, 504));
        panelParent.setMinimumSize(new java.awt.Dimension(737, 457));
        panelParent.setPreferredSize(new java.awt.Dimension(737, 498));
        panelParent.setLayout(new java.awt.BorderLayout());

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/header/Meeting.png"))); // NOI18N
        header.setMaximumSize(new java.awt.Dimension(737, 75));
        header.setMinimumSize(new java.awt.Dimension(737, 75));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 746, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        panelParent.add(header, java.awt.BorderLayout.NORTH);

        centerPanel.setOpaque(false);
        centerPanel.setLayout(new java.awt.BorderLayout());

        center.setPreferredSize(new java.awt.Dimension(683, 150));

        Lperson.setPreferredSize(new java.awt.Dimension(0, 140));

        jLabelPhoneClient.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelPhoneClient.setText("Phone Client:");

        phoneClient.setEnabled(false);
        phoneClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneClientActionPerformed(evt);
            }
        });

        sClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/Sear.png"))); // NOI18N
        sClient.setBorderPainted(false);
        sClient.setContentAreaFilled(false);
        sClient.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sClient.setFocusPainted(false);
        sClient.setMaximumSize(new java.awt.Dimension(30, 30));
        sClient.setMinimumSize(new java.awt.Dimension(30, 30));
        sClient.setPreferredSize(new java.awt.Dimension(30, 30));
        sClient.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sClientFocusGained(evt);
            }
        });

        jLabelFirstName.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelFirstName.setText("First Name:");

        jLabelLastName.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelLastName.setText("Last Name:");

        lastNameclient.setEnabled(false);

        firstNameClient.setEnabled(false);

        jLabelChooseDate.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelChooseDate.setText("Date:");

        selectService.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                selectServiceFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(selectService);

        servicesList.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                servicesListFocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(servicesList);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/add.png"))); // NOI18N
        btnAdd.setBorderPainted(false);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd.setFocusPainted(false);
        btnAdd.setMinimumSize(new java.awt.Dimension(30, 30));
        btnAdd.setPreferredSize(new java.awt.Dimension(30, 30));
        btnAdd.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAddFocusGained(evt);
            }
        });

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/back.png"))); // NOI18N
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBack.setFocusPainted(false);
        btnBack.setMinimumSize(new java.awt.Dimension(30, 30));
        btnBack.setPreferredSize(new java.awt.Dimension(30, 30));
        btnBack.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnBackFocusGained(evt);
            }
        });

        jLabelService.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelService.setText("Services:");

        jLabelSelrctedService.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelSelrctedService.setText("Selected:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelService)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabelSelrctedService)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSelrctedService, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelService, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        moveService.addTab("Services", jPanel1);

        jLabelHairCut.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelHairCut.setText("Haircut:");

        employeeSupport.setEnabled(false);

        sEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/Sear.png"))); // NOI18N
        sEmployee.setBorderPainted(false);
        sEmployee.setContentAreaFilled(false);
        sEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sEmployee.setFocusPainted(false);
        sEmployee.setMaximumSize(new java.awt.Dimension(30, 30));
        sEmployee.setMinimumSize(new java.awt.Dimension(30, 30));
        sEmployee.setPreferredSize(new java.awt.Dimension(30, 30));
        sEmployee.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                sEmployeeFocusGained(evt);
            }
        });

        jLabelEmployee.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelEmployee.setText("Employee:");

        haircut.setEnabled(false);

        sHaircut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/Sear.png"))); // NOI18N
        sHaircut.setBorderPainted(false);
        sHaircut.setContentAreaFilled(false);
        sHaircut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sHaircut.setFocusPainted(false);
        sHaircut.setMaximumSize(new java.awt.Dimension(30, 30));
        sHaircut.setMinimumSize(new java.awt.Dimension(30, 30));
        sHaircut.setPreferredSize(new java.awt.Dimension(30, 30));
        sHaircut.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                sHaircutFocusGained(evt);
            }
        });

        jLabelSelectTimeMeeting.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabelSelectTimeMeeting.setText("Select Time:");

        btnTime.setText("Time");
        btnTime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout LpersonLayout = new javax.swing.GroupLayout(Lperson);
        Lperson.setLayout(LpersonLayout);
        LpersonLayout.setHorizontalGroup(
            LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LpersonLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPhoneClient)
                    .addComponent(jLabelLastName)
                    .addComponent(jLabelFirstName))
                .addGap(40, 40, 40)
                .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, LpersonLayout.createSequentialGroup()
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LpersonLayout.createSequentialGroup()
                                .addComponent(phoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(sClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lastNameclient, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSelectTimeMeeting)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTime, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LpersonLayout.createSequentialGroup()
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(LpersonLayout.createSequentialGroup()
                                .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(LpersonLayout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelHairCut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelEmployee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabelChooseDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(employeeSupport)
                                    .addComponent(haircut)
                                    .addComponent(chooseDateMetting, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sHaircut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
            .addGroup(LpersonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(moveService, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LpersonLayout.setVerticalGroup(
            LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LpersonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooseDateMetting, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPhoneClient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelChooseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LpersonLayout.createSequentialGroup()
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSelectTimeMeeting, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employeeSupport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHairCut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(haircut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LpersonLayout.createSequentialGroup()
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lastNameclient, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LpersonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameClient, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LpersonLayout.createSequentialGroup()
                        .addComponent(sEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sHaircut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(moveService, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelFirstName.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout centerLayout = new javax.swing.GroupLayout(center);
        center.setLayout(centerLayout);
        centerLayout.setHorizontalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerLayout.createSequentialGroup()
                .addComponent(Lperson, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        centerLayout.setVerticalGroup(
            centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Lperson, javax.swing.GroupLayout.PREFERRED_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        centerPanel.add(center, java.awt.BorderLayout.CENTER);

        optionsSelect.setPreferredSize(new java.awt.Dimension(737, 50));

        btnProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/process.png"))); // NOI18N
        btnProcess.setBorderPainted(false);
        btnProcess.setContentAreaFilled(false);
        btnProcess.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProcess.setFocusPainted(false);
        btnProcess.setPreferredSize(new java.awt.Dimension(150, 30));
        btnProcess.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnProcessFocusGained(evt);
            }
        });
        optionsSelect.add(btnProcess);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/update.png"))); // NOI18N
        btnUpdate.setBorderPainted(false);
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setOpaque(false);
        btnUpdate.setPreferredSize(new java.awt.Dimension(150, 30));
        btnUpdate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnUpdateFocusGained(evt);
            }
        });
        optionsSelect.add(btnUpdate);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/delete.png"))); // NOI18N
        btnDelete.setBorderPainted(false);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDelete.setFocusPainted(false);
        btnDelete.setOpaque(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(150, 30));
        btnDelete.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnDeleteFocusGained(evt);
            }
        });
        optionsSelect.add(btnDelete);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/exit.png"))); // NOI18N
        btnExit.setBorderPainted(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExit.setFocusPainted(false);
        btnExit.setOpaque(false);
        btnExit.setPreferredSize(new java.awt.Dimension(150, 30));
        btnExit.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnExitFocusGained(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        optionsSelect.add(btnExit);

        centerPanel.add(optionsSelect, java.awt.BorderLayout.SOUTH);

        panelParent.add(centerPanel, java.awt.BorderLayout.CENTER);

        footer.setPreferredSize(new java.awt.Dimension(737, 32));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        footer.add(comment, java.awt.BorderLayout.CENTER);

        panelParent.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelParent, "card2");

        pack();
    }// </editor-fold>                        

    private void btnProcessFocusGained(java.awt.event.FocusEvent evt) {                                       
        String comment = "Save";
        getComment().setText(comment);
    }                                      

    private void btnDeleteFocusGained(java.awt.event.FocusEvent evt) {                                      
        String comment = "Cancel a meeting";
        getComment().setText(comment);
    }                                     

    private void btnExitFocusGained(java.awt.event.FocusEvent evt) {                                    
        String comment = "Exit";
        getComment().setText(comment);
        this.dispose();
    }                                   

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {                                        

    }                                       

    private void sClientFocusGained(java.awt.event.FocusEvent evt) {                                    
        String comment = "Search and select the client to metting";
        getComment().setText(comment);
    }                                   

    private void sEmployeeFocusGained(java.awt.event.FocusEvent evt) {                                      
       String comment = "Search and select the employee who will the work";
        getComment().setText(comment);
    }                                     

    private void sHaircutFocusGained(java.awt.event.FocusEvent evt) {                                     
        String comment = "Search and select the type of cut desired by the customer";
        getComment().setText(comment);
    }                                    

    private void servicesListFocusGained(java.awt.event.FocusEvent evt) {                                         
       String comment = "All the services that can with work";
        getComment().setText(comment);
    }                                        

    private void btnAddFocusGained(java.awt.event.FocusEvent evt) {                                   
         String comment = "The service with the one desired by the client";
        getComment().setText(comment);
    }                                  

    private void btnBackFocusGained(java.awt.event.FocusEvent evt) {                                    
        String comment = "if you make a mistake with the service, you can back";
        getComment().setText(comment);
    }                                   

    private void selectServiceFocusGained(java.awt.event.FocusEvent evt) {                                          
       String comment = "All services add by the client";
        getComment().setText(comment);
    }                                         

    private void btnUpdateFocusGained(java.awt.event.FocusEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void phoneClientActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           


    // Variables declaration - do not modify                     
    private javax.swing.JPanel Lperson;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnProcess;
    private javax.swing.JButton btnTime;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel center;
    private javax.swing.JPanel centerPanel;
    private com.toedter.calendar.JDateChooser chooseDateMetting;
    private javax.swing.JLabel comment;
    private javax.swing.JTextField employeeSupport;
    private javax.swing.JTextField firstNameClient;
    private javax.swing.JPanel footer;
    private javax.swing.JTextField haircut;
    private org.edisoncor.gui.panel.Panel header;
    private javax.swing.JLabel jLabelChooseDate;
    private javax.swing.JLabel jLabelEmployee;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelHairCut;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelPhoneClient;
    private javax.swing.JLabel jLabelSelectTimeMeeting;
    private javax.swing.JLabel jLabelSelrctedService;
    private javax.swing.JLabel jLabelService;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lastNameclient;
    private javax.swing.JTabbedPane moveService;
    private javax.swing.JPanel optionsSelect;
    private org.edisoncor.gui.panel.Panel panelParent;
    private javax.swing.JTextField phoneClient;
    private javax.swing.JButton sClient;
    private javax.swing.JButton sEmployee;
    private javax.swing.JButton sHaircut;
    private javax.swing.JList<String> selectService;
    private javax.swing.JList<String> servicesList;
    // End of variables declaration                   

// manualc
    class SelectTime  extends JDialog implements TimeChangedListener{

        
        private JButton exit,set;
        private JPanel panelCenter,footer;
        private JSlider timeSelect,timeRank;
        
        SelectTime(){
            super(principal,true);
            setTitle("Select Time");
            setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            setPreferredSize(new Dimension(300,300));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            
            setResizable(false);
            setLocationRelativeTo(getBtnTime());
            setLayout(new BorderLayout());
            setVariable();
            setListener();
            set.setEnabled(true);
        }
        
        private void setVariable(){
            panelCenter = new JPanel();
            panelCenter.setLayout(new BorderLayout());
            
            JPanel option1 = new JPanel();
            option1.setPreferredSize(new Dimension(0,100));
            
            JLabel timlabel = new JLabel("Time:");
            timlabel.setPreferredSize(new Dimension(100,50));
            timlabel.setFont(new Font("Serif",Font.BOLD,18));
            
            time = new JTimeChooser(new Date());
            
            time.setFont(new Font("Serif",Font.BOLD,34));
            
            time.setPreferredSize(new Dimension(100,20));
            time.setMaximumSize(time.getPreferredSize());
            
           
            timeSelect = new JSlider(0, 86400,0);
            timeSelect.setPreferredSize(new Dimension(200,50));
            option1.add(timlabel);
            option1.add(time);
            option1.add(timeSelect);
            
            JPanel option2 = new JPanel();
            option2.setPreferredSize(new Dimension(0,100));
            
            JLabel timlab1 = new JLabel("End Time:");
            timlab1.setPreferredSize(new Dimension(100,50));
            timlab1.setFont(new Font("Serif",Font.BOLD,18));
            rankTime = new JTimeChooser();
            rankTime.setPreferredSize(new Dimension(100,20));
            rankTime.setMaximumSize(time.getPreferredSize());
            
            
            timeRank = new JSlider(0, 86400,0);
            timeRank.setPreferredSize(new Dimension(200,50));
            
            option2.add(timlab1);
            option2.add(rankTime);
            option2.add(timeRank);
            panelCenter.add(option1,BorderLayout.CENTER);
            panelCenter.add(option2,BorderLayout.SOUTH);
            
            add(panelCenter,BorderLayout.CENTER);

            footer = new JPanel();
            
            set = new JButton("Set");
            exit = new JButton("Exit");
            
            set.setCursor(new Cursor(Cursor.HAND_CURSOR));
            exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            set.setPreferredSize(new Dimension(100,30));
            exit.setPreferredSize(new Dimension(100,30));
            
            exit.addActionListener((ActionEvent ae) -> {
                setVisible(false);
                dispose();
            });
            
            
            footer.add(set);
            footer.add(exit);
            
            add(footer,BorderLayout.SOUTH);
        } 
        private void setListener(){
            
            Calendar sl = Calendar.getInstance();
            timeSelect.setSnapToTicks(true);
            timeRank.setSnapToTicks(true);
            time.setShowIcon(true);
            rankTime.setShowIcon(true);
            
//            time.setShowSeconds(false);
//            rankTime.setShowSeconds(false);
            
            timeSelect.setMinorTickSpacing(1800);
            timeSelect.setMajorTickSpacing(3600);
            timeRank.setMinorTickSpacing(1800);
            timeRank.setMajorTickSpacing(3600);
            
            timeSelect.setPaintTicks(true);
            timeSelect.setPaintTrack(true);
            
             timeRank.setPaintTicks(true);
            timeRank.setPaintTrack(true);
            timeSelect.addChangeListener((ChangeEvent ce) -> {
                int valueTimeSelect = timeSelect.getValue();
                
                int h3 = sl.get(Calendar.HOUR_OF_DAY);
                int m1 = sl.get(Calendar.MINUTE);
                int s1 = sl.get(Calendar.SECOND);

                sl.add(Calendar.HOUR_OF_DAY,-h3);
                sl.add(Calendar.MINUTE,-m1);
                sl.add(Calendar.SECOND,-s1);
                sl.add(Calendar.SECOND, +valueTimeSelect);
                java.sql.Time time1 = java.sql.Time.valueOf(sl.get(Calendar.HOUR_OF_DAY)+":"+sl.get(Calendar.MINUTE)+":"+sl.get(Calendar.SECOND));

                time.setTime(time1);
                set.setEnabled(true);
                timeRank.setValue(valueTimeSelect + 7200);
                timeRank.setMinimum(valueTimeSelect);
            });
            timeRank.addChangeListener((ChangeEvent ce) -> {
                int value = timeRank.getValue();
                
                int h3 = sl.get(Calendar.HOUR_OF_DAY);
                int m1 = sl.get(Calendar.MINUTE);
                int s1 = sl.get(Calendar.SECOND);
                
                sl.add(Calendar.HOUR_OF_DAY,-h3);
                sl.add(Calendar.MINUTE,-m1);
                sl.add(Calendar.SECOND,-s1);
                sl.add(Calendar.SECOND, +value);
                java.sql.Time time1 = java.sql.Time.valueOf(sl.get(Calendar.HOUR_OF_DAY)+":"+sl.get(Calendar.MINUTE)+":"+sl.get(Calendar.SECOND));
                rankTime.setTime(time1);
                
            });
            
            set.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent as ){
                String time1 = time.getFormatedTime();
                String time2 = rankTime.getFormatedTime();
                btnTime.setText(time1+" to "+time2);
                btnTime.setForeground(Color.white);
                btnTime.setBackground(Color.red);
                btnTime.setFont(new Font("Serif",Font.ITALIC,20));
                getEmployeeSupport().setText("");
                getHaircut().setText("");
                
                try {
                    this.finalize();
                } catch (Throwable ex) {
                    Logger.getLogger(Rmeeting.class.getName()).log(Level.SEVERE, null, ex);
                }
                setVisible(false);
                dispose();
            }
            
            });
        }
        @Override
        public void timeChanged(TimeChangedEvent event) {
          Object origin = event.getSource();
          if(origin.equals(time)){
              
          }else if(origin.equals(rankTime)){
              
          }
        }
    }
  
    JTimeChooser time,rankTime;

    SelectTime selectTime;
   
}
