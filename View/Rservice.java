package View;

import Controller.ControllerService;
import java.awt.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author idnak
 */
public class Rservice extends javax.swing.JDialog {

    private ControllerService controllerService;
    private long durationService; 

    /**
     *
     * @param controllerService
     */
    public void setControllerService(ControllerService controllerService){
        this.controllerService = controllerService;
        setListener();
    }

    /**
     *
     * @return
     */
    public ControllerService getControllerService(){
        return controllerService;
        
    }
    private void setListener(){
        registerBtt.addActionListener(controllerService);
        deleteBtt.addActionListener(controllerService);
        exitBtt.addActionListener(controllerService);
        price.addKeyListener(controllerService);
    }

    /**
     *
     * @return
     */
    public long getDurationService() {
        return durationService;
    }

    /**
     *
     * @param duration
     */
    public void setDurationService(long duration) {
        this.durationService = duration;
    }

    /**
     *
     * @return
     */
    public JLabel getTimeService() {
        return timeService;
    }

    /**
     *
     * @param timeService
     */
    public void setTimeService(JLabel timeService) {
        this.timeService = timeService;
    }
    
    /**
     *
     * @return
     */
    public JButton getDeleteBtt() {
        return deleteBtt;
    }

    /**
     *
     * @param deleteBtt
     */
    public void setDeleteBtt(JButton deleteBtt) {
        this.deleteBtt = deleteBtt;
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
    public JButton getRgisterBtt() {
        return registerBtt;
    }

    /**
     *
     * @param registerBtt
     */
    public void setRegisterBtt(JButton registerBtt) {
        this.registerBtt = registerBtt;
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
    public JTextField getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(JTextField price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public JTextField getService() {
        return service;
    }

    /**
     *
     * @param service
     */
    public void setService(JTextField service) {
        this.service = service;
    }
    
    
    
    /**
     * Creates new form Rservice
     * @param parent
     * @param model
     */
    public Rservice(java.awt.Frame parent, boolean model) {
        super(parent, model);
        variablesForm();
        setTitle("Service");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void variablesForm() {

        servicePanel = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        center = new javax.swing.JPanel();
        detailsService = new javax.swing.JPanel();
        details = new javax.swing.JPanel();
        serviceLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        service = new javax.swing.JTextField();
        jPanelChoosing = new javax.swing.JPanel();
        registerBtt = new javax.swing.JButton();
        deleteBtt = new javax.swing.JButton();
        exitBtt = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        comment = new javax.swing.JLabel();
        duration = new JLabel("Duration:");
        
        //configuration of panel
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        servicePanel.setLayout(new java.awt.BorderLayout());
        servicePanel.setMaximumSize(new java.awt.Dimension(737, 504));
       

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/header/Service.png"))); // NOI18N
        header.setMaximumSize(new java.awt.Dimension(737, 75));
        header.setMinimumSize(new java.awt.Dimension(737, 75));
        header.setPreferredSize(new java.awt.Dimension(450, 75));
        servicePanel.add(header,BorderLayout.NORTH);

        center.setOpaque(false);
        center.setLayout(new java.awt.BorderLayout());

        detailsService.setPreferredSize(new java.awt.Dimension(683, 70));
        details.setPreferredSize(new java.awt.Dimension(600, 140));

         JPanel conten1 , conten2;
        conten1 = new JPanel();
        conten1.setPreferredSize(new Dimension(280,100));
        
        conten1.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        conten2 = new JPanel();
        conten2.setPreferredSize(new Dimension(280,100));
        conten2.setLayout(new FlowLayout(FlowLayout.LEFT));
        details.add(conten1);
        details.add(conten2);
        
        detailsService.add(details);
        
        serviceLabel.setFont(new java.awt.Font("Serif", 1, 14));
        serviceLabel.setForeground(new java.awt.Color(0, 0, 0));
        serviceLabel.setText("Service:");
        serviceLabel.setPreferredSize(new Dimension(80,20));
        conten1.add(serviceLabel);
        
        service.setFont(new java.awt.Font("Serif", 0, 12)); 
        service.setForeground(new java.awt.Color(0, 0, 0));
        service.setMaximumSize(service.getPreferredSize());
        service.setPreferredSize(new Dimension(180,20));
        service.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                serviceFocusGained(evt);
            }
        });
        conten1.add(service);
        
        duration.setFont(new java.awt.Font("Serif", 1, 14)); 
        duration.setForeground(new java.awt.Color(0, 0, 0));
        duration.setPreferredSize(new Dimension(80,20));
        conten1.add(duration);
        
        jSliderDuration = new JSlider(0,7200000,900000);
      //  jSliderDuration.setMinimum(9000000);
        
        jSliderDuration.setMajorTickSpacing(1800000);
        jSliderDuration.setMinorTickSpacing(900000);
        jSliderDuration.setPaintTicks(true);
        jSliderDuration.setPaintTrack(true);
        jSliderDuration.setSnapToTicks(true);
        jSliderDuration.setPreferredSize(new Dimension(100,50));
        conten1.add(jSliderDuration);
//        
        Calendar min = Calendar.getInstance();
       
        timeService = new JLabel();
        timeService.setForeground(Color.black);
        timeService.setFont(new Font("Serif", Font.ITALIC, 14));
        timeService.setPreferredSize(new Dimension(80,50));
        timeService.setText("15 minute");
        durationService = jSliderDuration.getValue();
        conten1.add(timeService);
        jSliderDuration.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent ce) {
                durationService = jSliderDuration.getValue();
                 int hr = min.get(Calendar.HOUR_OF_DAY);
                int mi = min.get(Calendar.MINUTE);
                int s = min.get(Calendar.SECOND);

                min.add(Calendar.HOUR_OF_DAY, -hr);
                min.add(Calendar.MINUTE, -mi);
                min.add(Calendar.SECOND, -s);
                
                min.add(Calendar.MILLISECOND, (int) +durationService);

                 java.sql.Time time1 = java.sql.Time.valueOf(min.get(Calendar.HOUR_OF_DAY)+":"+min.get(Calendar.MINUTE)+":"+min.get(Calendar.SECOND));
                timeService.setText(time1.toString() + " Hrs");

            }
            
        });
        
        priceLabel.setFont(new java.awt.Font("Serif", 1, 14));
        priceLabel.setForeground(new java.awt.Color(0, 0, 0));
        priceLabel.setText("Price:");
        priceLabel.setPreferredSize(new Dimension(80,20));
        conten2.add(priceLabel);
        
        price.setFont(new java.awt.Font("Serif", 1, 12)); 
        price.setForeground(new java.awt.Color(0, 0, 0));
        price.setMaximumSize(service.getPreferredSize());
        price.setPreferredSize(new Dimension(180,20));
        
        price.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                priceFocusGained(evt);
            }
        });
        conten2.add(price);
        
        center.add(detailsService, java.awt.BorderLayout.CENTER);

        jPanelChoosing.setPreferredSize(new java.awt.Dimension(450, 40));

        registerBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/save.png")));
        registerBtt.setBorderPainted(false);
        registerBtt.setContentAreaFilled(false);
        registerBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerBtt.setFocusPainted(false);
        registerBtt.setMaximumSize(new java.awt.Dimension(150, 30));
        registerBtt.setMinimumSize(new java.awt.Dimension(150, 30));
        registerBtt.setPreferredSize(new java.awt.Dimension(150, 30));
        registerBtt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerBttFocusGained(evt);
            }
        });
        jPanelChoosing.add(registerBtt);

        deleteBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/delete.png"))); 
        deleteBtt.setBorderPainted(false);
        deleteBtt.setContentAreaFilled(false);
        deleteBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtt.setFocusPainted(false);
        deleteBtt.setMaximumSize(new java.awt.Dimension(150, 30));
        deleteBtt.setMinimumSize(new java.awt.Dimension(150, 30));
        deleteBtt.setPreferredSize(new java.awt.Dimension(150, 30));
        deleteBtt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                deleteFocusGained(evt);
            }
        });
        jPanelChoosing.add(deleteBtt);

        exitBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/exit.png"))); 
        exitBtt.setBorderPainted(false);
        exitBtt.setContentAreaFilled(false);
        exitBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtt.setFocusPainted(false);
        exitBtt.setMaximumSize(new java.awt.Dimension(150, 30));
        exitBtt.setMinimumSize(new java.awt.Dimension(150, 30));
        exitBtt.setPreferredSize(new java.awt.Dimension(150, 30));
        exitBtt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                exitFocusGained(evt);
            }
        });
        exitBtt.addActionListener((java.awt.event.ActionEvent evt) -> {
            exitActionPerformed(evt);
        });
        jPanelChoosing.add(exitBtt);

        center.add(jPanelChoosing, java.awt.BorderLayout.SOUTH);

        servicePanel.add(center, java.awt.BorderLayout.CENTER);

        footer.setPreferredSize(new java.awt.Dimension(450, 32));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new java.awt.Font("Serif", 1, 14)); 
        comment.setForeground(new java.awt.Color(0, 0, 0));
        footer.add(comment, java.awt.BorderLayout.CENTER);

        servicePanel.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(servicePanel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void registerBttFocusGained(java.awt.event.FocusEvent evt) {
        String txt = "Save";
        getComment().setText(txt);
    }

    private void deleteFocusGained(java.awt.event.FocusEvent evt) {
        String txt = "Remove the service";
        getComment().setText(txt);
    }

    private void exitFocusGained(java.awt.event.FocusEvent evt) {
        String txt = "exit";
        getComment().setText(txt);
    }

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void serviceFocusGained(java.awt.event.FocusEvent evt) {
        String txt = "Enter or modify the service";
        getComment().setText(txt);
    }

    private void priceFocusGained(java.awt.event.FocusEvent evt) {
       String txt = "Enter or modify the price of the service";
        getComment().setText(txt);
    }

    private JPanel detailsService, jPanelChoosing,details,center,footer;
    private JLabel serviceLabel,priceLabel, duration, timeService,comment;
    private JButton deleteBtt,exitBtt, registerBtt;
    private JSlider jSliderDuration;
    private org.edisoncor.gui.panel.Panel header;
    private org.edisoncor.gui.panel.Panel servicePanel;
    private javax.swing.JTextField price, service;
}
