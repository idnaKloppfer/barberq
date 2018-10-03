package View;

import Controller.ControllerEmployee;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.swing.event.ChangeEvent;
import lu.tudor.santec.jtimechooser.TimeChangedEvent;
import lu.tudor.santec.jtimechooser.TimeChangedListener;

/**
 *
 * @author idnak
 */
public class Remployee extends JDialog {
    
    /*
    /*         VARIABLES OF CLASS
    */
    private JPanel detailsPanel,center,jPanelCenter,opnBtnPanel,footer;
    private JLabel idLabel,phoneLabel,nameLabel,lastNameLabel,jobTitleLabel,genderLabel,comment,workDaysLabel;
    private JButton btnDelete,btnRegister,exit,searchJob,workdays,workHours;
    private JTextField idPerson,jobTitle,lastName,namePerson,phone;
    private JRadioButton female,male;
    private ButtonGroup gender;
    private org.edisoncor.gui.panel.Panel header,panelREmployee;
//    private GridBagConstraints ct;
    private JTimeChooser entry;
    private JTimeChooser departure;
    private SelectTime selectedtime;
    private Principal principal;
    private ControllerEmployee controllerEmployee;
    private JButton setdays,cancel;
    private JToggleButton Monday,Tuesday,Wednesday,Thursday, Friday, Saturday,Sunday;
    private ArrayList<String> dt;
//    private ArrayList daySelect;

    /**
     *
     * @param controller
     */
    public void setControllerEmployee(ControllerEmployee controller){
        this.controllerEmployee = controller;
        setListener();
    }

    /**
     *
     * @return
     */
    public ControllerEmployee getControllerEmployee(){
        return controllerEmployee;
    }
    private void setListener(){
        btnRegister.addActionListener(controllerEmployee);
        btnDelete.addActionListener(controllerEmployee);
        exit.addActionListener(controllerEmployee);
        searchJob.addActionListener(controllerEmployee);
       
    }
    
    /**
     * @return
     * @return  *****************************************/
    /*         GETTERS Y SETTERS 
    ********************************************/
    public JButton getBtnDelete() {
        return btnDelete;
    }

    /**
     *
     * @param btnDelete
     */
    public void setBtnDelete(JButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    /**
     *
     * @return
     */
    public JTextField getId() {
        return idPerson;
    }

    /**
     *
     * @param idPerson
     */
    public void setId(JTextField idPerson) {
        this.idPerson = idPerson;
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
    public JButton getBtnRegister() {
        return btnRegister;
    }

    /**
     *
     * @param btnRegister
     */
    public void setBtnRegister(JButton btnRegister) {
        this.btnRegister = btnRegister;
    }

    /**
     *
     * @return
     */
    public JTextField getJobtitle() {
        return jobTitle;
    }

    /**
     *
     * @param jobTitle
     */
    public void setJobtitle(JTextField jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     *
     * @return
     */
    public JTextField getLastname() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastname(JTextField lastName) {
        this.lastName = lastName;
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
    public JTextField getNamePerson() {
        return namePerson;
    }

    /**
     *
     * @param namePerson
     */
    public void setNamePerson(JTextField namePerson) {
        this.namePerson = namePerson;
    }

    /**
     *
     * @return
     */
    public JButton getSearchJob() {
        return searchJob;
    }

    /**
     *
     * @param searchJob
     */
    public void setSearchJob(JButton searchJob) {
        this.searchJob = searchJob;
    }

    /**
     *
     * @return
     */
    public JRadioButton getFemale() {
        return female;
    }

    /**
     *
     * @param female
     */
    public void setFemale(JRadioButton female) {
        this.female = female;
    }

    /**
     *
     * @return
     */
    public ButtonGroup getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(ButtonGroup gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public JRadioButton getMale() {
        return male;
    }

    /**
     *
     * @param male
     */
    public void setMale(JRadioButton male) {
        this.male = male;
    }

    /**
     *
     * @return
     */
    public JTextField getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(JTextField phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public JButton getWorkdays() {
        return workdays;
    }

    /**
     *
     * @param workdays
     */
    public void setWorkdays(JButton workdays) {
        this.workdays = workdays;
    }

    /**
     *
     * @return
     */
    public JTimeChooser getEntry() {
        return entry;
    }

    /**
     *
     * @param entry
     */
    public void setEntry(JTimeChooser entry) {
        this.entry = entry;
    }

    /**
     *
     * @return
     */
    public JTimeChooser getDeparture() {
        return departure;
    }

    /**
     *
     * @param departure
     */
    public void setDeparture(JTimeChooser departure) {
        this.departure = departure;
    }

    /**
     *
     * @return
     */
    public ArrayList getDt() {
        return dt;
    }

    /**
     *
     * @param dt
     */
    public void setDt(ArrayList dt) {
        this.dt = dt;
         getEntry().setVisible(true);
         getDeparture().setVisible(true);
    }

    /**
     *
     * @return
     */
    public JButton getWorkHours() {
        return workHours;
    }
    
    
    
    /*******************************************/
    /*         CONSTRUCTOR OF CLASS
    ********************************************/
    /**
     * Creates new form Remployee
     * @param parent 
     * @param model
     */
    public Remployee(java.awt.Frame parent, boolean model) {
        super(parent, model);
        initComponents();
        setTitle("Employee");
        setResizable(false);
        setLocationRelativeTo(null);
         getEntry().setVisible(true);
         getDeparture().setVisible(true);
         dt = new ArrayList();
         if(!dt.isEmpty()){
         workdays.setBackground(Color.red);
         getEntry().setVisible(true);
         getDeparture().setVisible(true);
        }
         else{
         workdays.setBackground(Color.GRAY);
         getEntry().setVisible(false);
         getDeparture().setVisible(false);
        }
        selectedtime = new SelectTime();
    }

    
    /**
     * METODOS PARA MOSTRAR DIALOGO PARA ENLISTAR LOS DIAS DE TRABAJO DEL EMPLEADO.... 
     */
    private void showDays(){
        
        JDialog DaysContent = new JDialog(principal, true);
        DaysContent.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        DaysContent.setTitle("Work Days");
        DaysContent.setResizable(false);
        DaysContent.setLocationRelativeTo(null);
        DaysContent.setPreferredSize(new Dimension(300,250));
        DaysContent.setMinimumSize(DaysContent.getPreferredSize());
        DaysContent.setMaximumSize(DaysContent.getPreferredSize());
        DaysContent.getContentPane().setLayout(new BorderLayout());
        
        JPanel contendays = new JPanel();
        ArrayList days = new ArrayList();
        
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
        
        
        contendays.setLayout(new GridLayout(3,3));
        Iterator<String> day = days.iterator();

         
        Monday = new JToggleButton("Monday");
        Tuesday = new JToggleButton("Tuesday");
        Wednesday = new JToggleButton("Wednesday");
        Thursday = new JToggleButton("Thursday");
        Friday = new JToggleButton("Friday");
        Saturday = new JToggleButton("Saturday");
        Sunday = new JToggleButton("Sunday");
  
            if(dt.contains(Monday.getActionCommand())){
             Monday.setSelected(true);
            }
            if(dt.contains(Tuesday.getActionCommand())){
             Tuesday.setSelected(true);
            }
            if(dt.contains(Wednesday.getActionCommand())){
             Wednesday.setSelected(true);
            }
            if(dt.contains(Thursday.getActionCommand())){
             Thursday.setSelected(true);
            }
            if(dt.contains(Friday.getActionCommand())){
             Friday.setSelected(true);
            }
            if(dt.contains(Saturday.getActionCommand())){
             Saturday.setSelected(true);
            }
            if(dt.contains(Sunday.getActionCommand())){
             Sunday.setSelected(true);
            }  

        Monday.addActionListener((ActionEvent ae) -> {
            if(Monday.isSelected()){
                dt.add(ae.getActionCommand());
            }else{
                if(dt.contains(ae.getActionCommand())){
                    dt.remove(ae.getActionCommand());
                }
            }
        });
        Tuesday.addActionListener((ActionEvent ae) -> {
            if(Tuesday.isSelected()){
                dt.add(ae.getActionCommand());
            }else{
                if(dt.contains(ae.getActionCommand())){
                    dt.remove(ae.getActionCommand());
                }
            }
        });
        Wednesday.addActionListener((ActionEvent ae) -> {
            if(Wednesday.isSelected()){
                dt.add(ae.getActionCommand());
            }else{
                if(dt.contains(ae.getActionCommand())){
                    dt.remove(ae.getActionCommand());
                }
            }
        });
        Thursday.addActionListener((ActionEvent ae) -> {
            if(Thursday.isSelected()){
                dt.add(ae.getActionCommand());
            }else{
                if(dt.contains(ae.getActionCommand())){
                    dt.remove(ae.getActionCommand());
                }
            }
        });
        Friday.addActionListener((ActionEvent ae) -> {
            if(Friday.isSelected()){
                dt.add(ae.getActionCommand());
            }else{
                if(dt.contains(ae.getActionCommand())){
                    dt.remove(ae.getActionCommand());
                }
            }
        });
        Saturday.addActionListener((ActionEvent ae) -> {
            if(Saturday.isSelected()){
                dt.add(ae.getActionCommand());
            }else{
                if(dt.contains(ae.getActionCommand())){
                    dt.remove(ae.getActionCommand());
                }
            }
        });
        Sunday.addActionListener((ActionEvent ae) -> {
            if(Sunday.isSelected()){
                dt.add(ae.getActionCommand());
            }else{
                if(dt.contains(ae.getActionCommand())){
                    dt.remove(ae.getActionCommand());
                }
            }
        });
        
        contendays.add(Monday);
        contendays.add(Tuesday);
        contendays.add(Wednesday);
        contendays.add(Thursday);
        contendays.add(Friday);
        contendays.add(Saturday);
        contendays.add(Sunday);
         

        DaysContent.getContentPane().add(contendays,BorderLayout.CENTER);
        
        JPanel footerDay = new JPanel();
        
        setdays = new JButton("Set");
        setdays.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent aee){
                if(!dt.isEmpty()){
                    entry.setVisible(true);
                    departure.setVisible(true);
                    workdays.setBackground(Color.red);
                }else{
                    entry.setVisible(false);
                    departure.setVisible(false);
                    workdays.setBackground(Color.gray);
                }
               DaysContent.dispose();
               DaysContent.setVisible(false);

            }
        });
        cancel = new JButton("Clear");
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               DaysContent.dispose();
               workdays.setBackground(new Color(Color.GRAY.getRGB()));
               entry.setVisible(false);
                    departure.setVisible(false);
               DaysContent.setVisible(false);
               dt.clear();
            }
            
        });
        
        JButton close = new JButton("Close");
        
        close.addActionListener((ActionEvent ae) -> {
            DaysContent.dispose();
            DaysContent.setVisible(false);
        });
        footerDay.add(setdays);
        footerDay.add(cancel);
        footerDay.add(close);
        DaysContent.getContentPane().add(footerDay,BorderLayout.SOUTH);
        
        DaysContent.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     */

    private void showhours(){
        selectedtime.setVisible(true);
    }
    
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">   
    private void initComponents() {

        gender = new ButtonGroup();
        panelREmployee = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        center = new JPanel();
        detailsPanel = new JPanel();
        jPanelCenter = new JPanel();
        idLabel = new JLabel();
        phoneLabel = new JLabel();
        entry = new JTimeChooser();
        departure = new JTimeChooser();
        

        JLabel hourwork = new JLabel("Work hours");
        namePerson = new JTextField();
        lastName = new JTextField();
        nameLabel = new JLabel();
        lastNameLabel = new JLabel();
        jobTitle = new JTextField();
        jobTitleLabel = new JLabel();
        workDaysLabel = new JLabel();
        searchJob = new JButton();
        phone = new JTextField();
        genderLabel = new JLabel();
        male = new JRadioButton();
        female = new JRadioButton();
        opnBtnPanel = new JPanel();
        btnRegister = new JButton();
        btnDelete = new JButton();
        exit = new JButton();
        footer = new JPanel();
        workHours = new JButton("Enter");
        workdays = new JButton("Days");
        
        comment = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        panelREmployee.setMaximumSize(panelREmployee.getPreferredSize());
        panelREmployee.setPreferredSize(new Dimension(737, 280));
        panelREmployee.setLayout(new BorderLayout());

        header.setIcon(new ImageIcon(getClass().getResource("/View/img/header/Employee.png")));
        header.setMaximumSize(new Dimension(737, 75));
        header.setMinimumSize(new Dimension(737, 75));
        header.setPreferredSize(new Dimension(737, 75));
        panelREmployee.add(header,BorderLayout.NORTH);

        center.setOpaque(false);
        center.setLayout(new BorderLayout());

        detailsPanel.setPreferredSize(new Dimension(683, 100));
        jPanelCenter.setPreferredSize(new Dimension(600, 110));
        
        JPanel jPanelDetailsPersons , jPanelDetailsEmployee;
        
        jPanelDetailsPersons = new JPanel();
        jPanelDetailsPersons.setPreferredSize(new Dimension(280,100));
        
        jPanelDetailsPersons.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        jPanelDetailsEmployee = new JPanel();
        jPanelDetailsEmployee.setPreferredSize(new Dimension(280,100));
        jPanelDetailsEmployee.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        jPanelCenter.add(jPanelDetailsPersons);
        jPanelCenter.add(jPanelDetailsEmployee);
        
        detailsPanel.add(jPanelCenter);
        
        idLabel.setFont(new java.awt.Font("Serif", 1, 14)); 
        idLabel.setForeground(new java.awt.Color(0, 0, 0));
        idLabel.setText("Id:");
        idLabel.setPreferredSize(new Dimension(80,20));
     
        jPanelDetailsPersons.add(idLabel);
        
        idPerson = new JTextField();
        idPerson.setMaximumSize(idPerson.getPreferredSize());
        idPerson.setPreferredSize(new Dimension(180,20));

        jPanelDetailsPersons.add(idPerson);
        
        nameLabel.setFont(new Font("Serif", 1, 14)); 
        nameLabel.setForeground(new java.awt.Color(0, 0, 0));
        nameLabel.setText("Name:");
        nameLabel.setPreferredSize(new Dimension(80,20));
        jPanelDetailsPersons.add(nameLabel);
        
        namePerson.setPreferredSize(new Dimension(180,20));
        namePerson.setForeground(Color.BLACK);
        
        jPanelDetailsPersons.add(namePerson);
        
        lastNameLabel.setFont(new Font("Serif", 1, 14));
        lastNameLabel.setForeground(new Color(0, 0, 0));
        lastNameLabel.setText("Last Name:");
        lastNameLabel.setPreferredSize(new Dimension(80,20));
        jPanelDetailsPersons.add(lastNameLabel);
        
        lastName.setPreferredSize(new Dimension(180,20));
        lastName.setForeground(Color.BLACK);
        jPanelDetailsPersons.add(lastName);
         
        genderLabel.setFont(new java.awt.Font("Serif", 1, 14));
        genderLabel.setForeground(new java.awt.Color(0, 0, 0));
        genderLabel.setText("Gender:");
        genderLabel.setPreferredSize(new Dimension(80,20));
        jPanelDetailsPersons.add(genderLabel);
        
        gender.add(male);
        male.setText("M");

        gender.add(female);
        female.setText("F");
        
        jPanelDetailsPersons.add(male);
        jPanelDetailsPersons.add(female);
        
        
        
        
        //for conten2 ...
        
        jobTitle.setEnabled(false);
        jobTitleLabel.setFont(new Font("Serif", 1, 14));
        jobTitleLabel.setForeground(new Color(0, 0, 0));
        jobTitleLabel.setText("Job Title:");
        
        jobTitleLabel.setMaximumSize(jobTitleLabel.getPreferredSize());
        jobTitleLabel.setPreferredSize(new Dimension(80,20));
        
        jPanelDetailsEmployee.add(jobTitleLabel);
        jobTitle.setMaximumSize(jobTitle.getPreferredSize());
        jobTitle.setPreferredSize(new Dimension(140,20));
        jPanelDetailsEmployee.add(jobTitle);
        
        searchJob.setIcon(new ImageIcon(getClass().getResource("/View/img/Sear.png"))); 
        searchJob.setBorderPainted(false);
        searchJob.setContentAreaFilled(false);
        searchJob.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        searchJob.setFocusPainted(false);
        searchJob.setMaximumSize(new Dimension(30, 20));
        searchJob.setMinimumSize(new Dimension(30, 20));
        searchJob.setPreferredSize(new Dimension(30, 20));
       
        searchJob.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                sjobFocusGained(evt);
            }
        });
        
        jPanelDetailsEmployee.add(searchJob);
        
        phoneLabel.setFont(new Font("Serif", 1, 14)); 
        phoneLabel.setForeground(new Color(0, 0, 0));
        phoneLabel.setText("Phone:");
        phoneLabel.setPreferredSize(new Dimension(80,20));
        jPanelDetailsEmployee.add(phoneLabel);
        idPerson.setEnabled(false);

        phone.setPreferredSize(new java.awt.Dimension(180, 20));
        phone.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                
            }
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneKeyTyped(evt);
            }
        });
        
        jPanelDetailsEmployee.add(phone);
        
        workDaysLabel.setPreferredSize(new Dimension(80,20));
        workDaysLabel.setFont(new Font("Serif", 1, 14));
        workDaysLabel.setForeground(new Color(0, 0, 0));
        workDaysLabel.setText("Work Days:");
        jPanelDetailsEmployee.add(workDaysLabel);
//        workdays.setBorderPainted(false);
        workdays.setContentAreaFilled(false);
        
        workdays.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        workdays.setFocusPainted(false);
        
        workdays.setPreferredSize(new Dimension(180,20));
        jPanelDetailsEmployee.add(workdays);
        workdays.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                String coment = "Select employee work days";
                comment.setText(coment);
            }

            @Override
            public void focusLost(FocusEvent fe) {
               
            }
        });
        
        workdays.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                showDays();
            }
        });
        
        
        hourwork.setPreferredSize(new Dimension(80,20));
        hourwork.setFont(new Font("Serif", Font.BOLD, 14));
        hourwork.setForeground(new Color(0,0,0));
        
        jPanelDetailsEmployee.add(hourwork);
        

//        conten2.add(entrylabel);
//        entry.setVisible(false);


        workHours.setPreferredSize(new Dimension(180,20));
        workHours.setContentAreaFilled(false);
        workHours.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jPanelDetailsEmployee.add(workHours);
        workHours.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                showhours();
            }
        });
//        departurelabel.setPreferredSize(new Dimension(70,20));
//        departurelabel.setFont(new Font("Serif", 1, 14));
//        departurelabel.setForeground(new Color(0, 0, 0));
//        conten2.add(departurelabel);
//        
//        departure.setEnabled(false);
//        
////        departure.setVisible(false);
//        departure.setPreferredSize(new Dimension(60,20));
//        departure.setLocale(Locale.US);
//        
//        conten2.add(departure);
        namePerson.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namepersonKeyTyped(evt);
            }
        });

        lastName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lastnameKeyTyped(evt);
            }
        });

        
        center.add(detailsPanel,BorderLayout.CENTER);

        opnBtnPanel.setPreferredSize(new Dimension(450, 40));

        btnRegister.setIcon(new ImageIcon(getClass().getResource("/View/img/save.png"))); 
        btnRegister.setBorderPainted(false);
        btnRegister.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegister.setMaximumSize(new Dimension(150, 30));
        btnRegister.setMinimumSize(new Dimension(150, 30));
        btnRegister.setOpaque(false);
        btnRegister.setPreferredSize(new java.awt.Dimension(150, 30));
        
        btnRegister.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                grabarFocusGained(evt);
            }
        });
        opnBtnPanel.add(btnRegister);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/delete.png"))); 
        btnDelete.setBorderPainted(false);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setOpaque(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(150, 30));
       
        btnDelete.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                deleteFocusGained(evt);
            }
        });
        opnBtnPanel.add(btnDelete);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/exit.png"))); 
        exit.setBorderPainted(false);
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.setOpaque(false);
        exit.setPreferredSize(new java.awt.Dimension(150, 30));
        exit.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                exitFocusGained(evt);
            }
        });
        opnBtnPanel.add(exit);

        center.add(opnBtnPanel, java.awt.BorderLayout.SOUTH);

        panelREmployee.add(center, java.awt.BorderLayout.CENTER);

        footer.setPreferredSize(new java.awt.Dimension(450, 32));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new java.awt.Font("Serif", 1, 14));
        comment.setForeground(new java.awt.Color(0, 0, 0));
        footer.add(comment, java.awt.BorderLayout.CENTER);

        panelREmployee.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelREmployee);

        pack();
        // </editor-fold> 
    }

    private void grabarFocusGained(java.awt.event.FocusEvent evt) {

    }

    private void deleteFocusGained(java.awt.event.FocusEvent evt) {
        String deleteBtn = "delete the employee ";
        getComment().setText(deleteBtn);
    }

    private void exitFocusGained(java.awt.event.FocusEvent evt) {
        String exitBtn = "Exit";
        getComment().setText(exitBtn);
    }
    
    private void sjobFocusGained(java.awt.event.FocusEvent evt) {
         String profBtn = "Look for the profession of this employee to associate";
        getComment().setText(profBtn);
    }

    private void namepersonKeyTyped(java.awt.event.KeyEvent evt) {
        char b = evt.getKeyChar();
        
         if(Character.isDigit(b)){
            evt.consume();
         }
    }

    private void lastnameKeyTyped(java.awt.event.KeyEvent evt) {
       char b = evt.getKeyChar();
        
         if(Character.isDigit(b)){
            evt.consume();
         }
    }

    private void phoneKeyTyped(java.awt.event.KeyEvent evt) {
       char b = evt.getKeyChar();
        
         if(Character.isLetter(b) || Character.isWhitespace(b) || getPhone().getText().length() > 9 || !Character.isDigit(b)){
            evt.consume();
         }
    }
    
    class SelectTime  extends JDialog implements TimeChangedListener{
        
        private JButton exit,sep;
        private JPanel panelcent,footer;
        private JSlider timeSelect,timeRank;
        
        SelectTime(){
            super(principal,true);
            setTitle("Check in and check out time");
            setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            setPreferredSize(new Dimension(300,300));
            setMaximumSize(getPreferredSize());
            setMinimumSize(getPreferredSize());
            
            setResizable(false);
            setLocationRelativeTo(getWorkHours());
            setLayout(new BorderLayout());
            asignlam();
            setListener();
            sep.setEnabled(true);
        }
        
        private void asignlam(){
            panelcent = new JPanel();
            panelcent.setLayout(new BorderLayout());
            
            JPanel option1 = new JPanel();
            option1.setPreferredSize(new Dimension(0,100));
            
            JLabel timlab = new JLabel("Time:");
             timlab.setPreferredSize(new Dimension(100,50));
            timlab.setFont(new Font("Serif",Font.BOLD,18));
            
           
            entry =   new JTimeChooser();
            entry.setFont(new Font("Serif",Font.BOLD,34));
            
            entry.setPreferredSize(new Dimension(100,20));
            entry.setMaximumSize(entry.getPreferredSize());
            
           
            timeSelect = new JSlider(0, 86400,0);
            timeSelect.setPreferredSize(new Dimension(200,50));
            option1.add(timlab);
            option1.add(entry);
            option1.add(timeSelect);
            
            JPanel option2 = new JPanel();
            option2.setPreferredSize(new Dimension(0,100));
            
            JLabel timlab1 = new JLabel("End Time:");
            timlab1.setPreferredSize(new Dimension(100,50));
            timlab1.setFont(new Font("Serif",Font.BOLD,18));
            departure = new JTimeChooser();
            departure.setPreferredSize(new Dimension(100,20));
            departure.setMaximumSize(departure.getPreferredSize());
            
            
            timeRank = new JSlider(0, 86400,0);
            timeRank.setPreferredSize(new Dimension(200,50));
            
            option2.add(timlab1);
            option2.add(departure);
            option2.add(timeRank);
            panelcent.add(option1,BorderLayout.CENTER);
            panelcent.add(option2,BorderLayout.SOUTH);
            
            add(panelcent,BorderLayout.CENTER);

            footer = new JPanel();
            
            sep = new JButton("Set");
            exit = new JButton("Exit");
            
            sep.setCursor(new Cursor(Cursor.HAND_CURSOR));
            exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            sep.setPreferredSize(new Dimension(100,30));
            exit.setPreferredSize(new Dimension(100,30));
            
            exit.addActionListener((ActionEvent ae) -> {
                setVisible(false);
                dispose();
            });
            
            
            footer.add(sep);
            footer.add(exit);
            
            add(footer,BorderLayout.SOUTH);
        } 
        private void setListener(){
            
            Calendar sl = Calendar.getInstance();
            timeSelect.setSnapToTicks(true);
            timeRank.setSnapToTicks(true);
            entry.setShowIcon(true);
            entry.setShowIcon(true);
            
//            time.setShowSeconds(false);
//            ranktime.setShowSeconds(false);
            
            timeSelect.setMinorTickSpacing(1800);
            timeSelect.setMajorTickSpacing(3600);
            timeRank.setMinorTickSpacing(1800);
            timeRank.setMajorTickSpacing(3600);
            
            timeSelect.setPaintTicks(true);
            timeSelect.setPaintTrack(true);
            
             timeRank.setPaintTicks(true);
            timeRank.setPaintTrack(true);
            timeSelect.addChangeListener((ChangeEvent ce) -> {
                int value = timeSelect.getValue();
                
                int h3 = sl.get(Calendar.HOUR_OF_DAY);
                int m1 = sl.get(Calendar.MINUTE);
                int s1 = sl.get(Calendar.SECOND);
                
                sl.add(Calendar.HOUR_OF_DAY,-h3);
                sl.add(Calendar.MINUTE,-m1);
                sl.add(Calendar.SECOND,-s1);
                sl.add(Calendar.SECOND, +value);
                java.sql.Time time1 = java.sql.Time.valueOf(sl.get(Calendar.HOUR_OF_DAY)+":"+sl.get(Calendar.MINUTE)+":"+sl.get(Calendar.SECOND));
                entry.setTime(time1);
                
                sep.setEnabled(true);
                timeRank.setValue(value + 3600);
                timeRank.setMinimum(value);
            });
            timeRank.addChangeListener((ChangeEvent ce) -> {
                int valor = timeRank.getValue();
                
                int h3 = sl.get(Calendar.HOUR_OF_DAY);
                int m1 = sl.get(Calendar.MINUTE);
                int s1 = sl.get(Calendar.SECOND);
                
                sl.add(Calendar.HOUR_OF_DAY,-h3);
                sl.add(Calendar.MINUTE,-m1);
                sl.add(Calendar.SECOND,-s1);
                sl.add(Calendar.SECOND, +valor);
                java.sql.Time time1 = java.sql.Time.valueOf(sl.get(Calendar.HOUR_OF_DAY)+":"+sl.get(Calendar.MINUTE)+":"+sl.get(Calendar.SECOND));
                departure.setTime(time1);
                
            });
            
            sep.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent as ){
                String time1 = entry.getFormatedTime();
                String time2 = departure.getFormatedTime();
                workHours.setText(time1+" to "+time2);
                workHours.setForeground(Color.black);
                workHours.setBackground(Color.red);
                workHours.setFont(new Font("Serif",Font.ITALIC,14));
               
                setVisible(false);
                dispose();
            }
            
            });
        }
        @Override
        public void timeChanged(TimeChangedEvent event) {
          Object origin = event.getSource();
          if(origin.equals(entry)){
              
          }else if(origin.equals(departure)){
              
          }
        }

    }
   
}
