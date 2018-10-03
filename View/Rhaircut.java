package View;

import Controller.ControllerHaircut;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.*;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author idnak
 */
public class Rhaircut extends javax.swing.JDialog {

    private ControllerHaircut controllerHairCut;
    private long duration; 

    /**
     *
     * @param controllerHairCut
     */
    public void setControllerHairCut(ControllerHaircut controllerHairCut){
        this.controllerHairCut = controllerHairCut;
        setListener();
    }

    /**
     *
     * @return
     */
    public ControllerHaircut getControllerHairCut(){
        return controllerHairCut;
    }
    private void setListener(){
        
        registerBtt.addActionListener(controllerHairCut);
        deleteBtt.addActionListener(controllerHairCut);
        exitBtt.addActionListener(controllerHairCut);
        getPrice().addKeyListener(controllerHairCut);
        
        
    }
    
    /**
     * @return
     * @return  ******************************/
    /*        GETTERS Y SETTERS 
    /********************************/
    
    public long getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     */
    public JButton getDelete() {
        return deleteBtt;
    }

    /**
     *
     * @param delete
     */
    public void setDelete(JButton delete) {
        this.deleteBtt = delete;
    }

    /**
     *
     * @return
     */
    public JButton getExit() {
        return exitBtt;
    }

    /**
     *
     * @param exit
     */
    public void setExit(JButton exit) {
        this.exitBtt = exit;
    }

    /**
     *
     * @return
     */
    public JRadioButton getFemale() {
        return radioBtnfemale;
    }

    /**
     *
     * @param female
     */
    public void setFemale(JRadioButton female) {
        this.radioBtnfemale = female;
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
    public JButton getRegisterBtt() {
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
    public JRadioButton getMale() {
        return radioBtnMale;
    }

    /**
     *
     * @param male
     */
    public void setMale(JRadioButton male) {
        this.radioBtnMale = male;
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
    public JTextField getStyle() {
        return style;
    }

    /**
     *
     * @param style
     */
    public void setStyle(JTextField style) {
        this.style = style;
    }

    /**
     *
     * @return
     */
    public JLabel getMinutes() {
        return jLabelMinutes;
    }

    /**
     *
     * @param jLabelMinutes
     */
    public void setMinutes(JLabel jLabelMinutes) {
        this.jLabelMinutes = jLabelMinutes;
    }
    
    

/**********************************/
/*          CONSTRUCTOR
/**********************************/
    /**
     * Creates new form Rhaircut
     * @param parent
     * @param model
     */
    public Rhaircut(java.awt.Frame parent, boolean model) {
        super(parent, model);
        variablesForm();
        setTitle("Haircut");
        setResizable(false);
        setLocationRelativeTo(null);
    }


    private void variablesForm() {

        //propiedades of component
        gender = new ButtonGroup();
        newHairCutPanel = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        detailsHaircutPanel = new JPanel();
        detailsPanel = new JPanel();
        detailsPanel2 = new JPanel();
        styleLabel = new JLabel();
        priceLabel = new JLabel();
        jLablelDuration = new JLabel("Duration:");
        price = new JTextField();
        style = new JTextField();
        genderLabel = new JLabel();
        radioBtnMale = new JRadioButton();
        radioBtnfemale = new JRadioButton();
        jPanelChoosing = new JPanel();
        registerBtt = new JButton();
        deleteBtt = new JButton();
        exitBtt = new JButton();
        footer = new JPanel();
        comment = new JLabel();
        
        //configuration of panel
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        //container
        newHairCutPanel.setMaximumSize(new Dimension(737, 504));
        newHairCutPanel.setLayout(new BorderLayout());

        //HEADER 
        header.setIcon(new ImageIcon(getClass().getResource("/View/img/header/Haircut type.png"))); // NOI18N
        header.setMaximumSize(new Dimension(737, 75));
        header.setMinimumSize(new Dimension(737, 75));
        header.setPreferredSize(new Dimension(737, 75));
        newHairCutPanel.add(header,BorderLayout.NORTH);

        detailsHaircutPanel.setOpaque(false);
        detailsHaircutPanel.setLayout(new BorderLayout());

        detailsPanel.setPreferredSize(new Dimension(683, 100));
        detailsPanel2.setPreferredSize(new Dimension(600, 85));

        JPanel jPanelContent , jPanelContent2;
        jPanelContent = new JPanel();
        jPanelContent.setPreferredSize(new Dimension(280,100));
        
        jPanelContent.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        jPanelContent2 = new JPanel();
        jPanelContent2.setPreferredSize(new Dimension(280,100));
        jPanelContent2.setLayout(new FlowLayout(FlowLayout.LEFT));
        detailsPanel2.add(jPanelContent);
        detailsPanel2.add(jPanelContent2);
        
        detailsPanel.add(detailsPanel2);
        
        styleLabel.setFont(new Font("Serif", 1, 14)); 
        styleLabel.setForeground(new Color(0, 0, 0));
        styleLabel.setText("Style:");
        styleLabel.setPreferredSize(new Dimension(80,20));
        jPanelContent.add(styleLabel);
        
        style.setFont(new Font("Serif", 0, 12)); 
        style.setForeground(new Color(0, 0, 0));
        style.setMaximumSize(style.getPreferredSize());
        style.setPreferredSize(new Dimension(180,20));
        style.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                styleFocusGained(evt);
            }
        });
        jPanelContent.add(style);
        
        priceLabel.setFont(new Font("Serif", 1, 14)); 
        priceLabel.setForeground(new Color(0, 0, 0));
        priceLabel.setText("Price:");
        priceLabel.setPreferredSize(new Dimension(80,20));
        jPanelContent2.add(priceLabel);
        
        price.setFont(new java.awt.Font("Serif", 1, 12)); 
        price.setForeground(new java.awt.Color(0, 0, 0));
        price.setPreferredSize(new Dimension(180,20));
        price.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                priceFocusGained(evt);
            }
        });
        jPanelContent2.add(price);
        

        genderLabel.setFont(new java.awt.Font("Serif", 1, 14)); 
        genderLabel.setForeground(new java.awt.Color(0, 0, 0));
        genderLabel.setPreferredSize(new Dimension(80,20));
        genderLabel.setText("Gender:");
        jPanelContent.add(genderLabel);
        
        gender.add(radioBtnMale);
        radioBtnMale.setText("M");
        radioBtnMale.setPreferredSize(new Dimension(70,20));
        radioBtnMale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maleFocusGained(evt);
            }
        });
        jPanelContent.add(radioBtnMale);
        
        gender.add(radioBtnfemale);
        radioBtnfemale.setText("F");
        radioBtnfemale.setPreferredSize(new Dimension(70,20));
        radioBtnfemale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                femaleFocusGained(evt);
            }
        });
        jPanelContent.add(radioBtnfemale);
        
        jLablelDuration.setFont(new java.awt.Font("Serif", 1, 14)); 
        jLablelDuration.setForeground(new java.awt.Color(0, 0, 0));
        jLablelDuration.setPreferredSize(new Dimension(80,20));
        jPanelContent2.add(jLablelDuration);
        
        jSliderDuration = new JSlider(900000,7200000,900000);
      //  jSliderDuration.setMinimum(9000000);
        
        jSliderDuration.setMajorTickSpacing(1800000);
        jSliderDuration.setMinorTickSpacing(900000);
        jSliderDuration.setPaintTicks(true);
        jSliderDuration.setPaintTrack(true);
        jSliderDuration.setSnapToTicks(true);
        jSliderDuration.setPreferredSize(new Dimension(100,50));
        jPanelContent2.add(jSliderDuration);
//        
        Calendar min = Calendar.getInstance();
       
        jLabelMinutes = new JLabel();
        jLabelMinutes.setForeground(Color.black);
        jLabelMinutes.setFont(new Font("Serif", Font.ITALIC, 14));
        jLabelMinutes.setPreferredSize(new Dimension(80,50));
        jLabelMinutes.setText("15 minute");
        duration = jSliderDuration.getValue();
        jPanelContent2.add(jLabelMinutes);
        jSliderDuration.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent ce) {
                duration = jSliderDuration.getValue();
                 int hr = min.get(Calendar.HOUR_OF_DAY);
                int mi = min.get(Calendar.MINUTE);
                int s = min.get(Calendar.SECOND);

                min.add(Calendar.HOUR_OF_DAY, -hr);
                min.add(Calendar.MINUTE, -mi);
                min.add(Calendar.SECOND, -s);
                
                min.add(Calendar.MILLISECOND, (int) +duration);

                 java.sql.Time time1 = java.sql.Time.valueOf(min.get(Calendar.HOUR_OF_DAY)+":"+min.get(Calendar.MINUTE)+":"+min.get(Calendar.SECOND));
                jLabelMinutes.setText(time1.toString() + " Hrs");

            }
            
        });
        
        
        
        detailsHaircutPanel.add(detailsPanel, java.awt.BorderLayout.CENTER);

        jPanelChoosing.setPreferredSize(new java.awt.Dimension(450, 40));

        registerBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/save.png"))); // NOI18N
        registerBtt.setBorderPainted(false);
        registerBtt.setContentAreaFilled(false);
        registerBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        deleteBtt.setMaximumSize(new java.awt.Dimension(150, 30));
        deleteBtt.setMinimumSize(new java.awt.Dimension(150, 30));
        deleteBtt.setPreferredSize(new java.awt.Dimension(150, 30));
        deleteBtt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                deleteFocusGained(evt);
            }
        });
        jPanelChoosing.add(deleteBtt);

        exitBtt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/exit.png"))); 
        exitBtt.setBorderPainted(false);
        exitBtt.setContentAreaFilled(false);   
        exitBtt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtt.setMaximumSize(new java.awt.Dimension(150, 30));
        exitBtt.setMinimumSize(new java.awt.Dimension(150, 30));
        exitBtt.setPreferredSize(new java.awt.Dimension(150, 30));
        exitBtt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                exitFocusGained(evt);
            }
        });
        exitBtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanelChoosing.add(exitBtt);

        detailsHaircutPanel.add(jPanelChoosing, java.awt.BorderLayout.SOUTH);

        newHairCutPanel.add(detailsHaircutPanel, java.awt.BorderLayout.CENTER);

        footer.setPreferredSize(new java.awt.Dimension(450, 32));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new java.awt.Font("Serif", 1, 14)); 
        comment.setForeground(new java.awt.Color(0, 0, 0));
        footer.add(comment, java.awt.BorderLayout.CENTER);

        newHairCutPanel.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(newHairCutPanel);

        pack();
    }

    
/**********************************/
/*          METHOD OF OBJECT
/**********************************/
    private void registerBttFocusGained(java.awt.event.FocusEvent evt) {
        String saveBtn = "Save";
        getComment().setText(saveBtn);
    }

    private void deleteFocusGained(java.awt.event.FocusEvent evt) {
        String deleteBtn = "delete the type of cut";
        getComment().setText(deleteBtn);
    }

    private void exitFocusGained(java.awt.event.FocusEvent evt) {
        String exitBtn = "exit of principal";
        getComment().setText(exitBtn);
    }

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void styleFocusGained(java.awt.event.FocusEvent evt) {
        String haircutBtn = "Enter the name of the type of haircut";
        getComment().setText(haircutBtn);
    }

    private void priceFocusGained(java.awt.event.FocusEvent evt) {
         String priceBtn = "Enter the price";
        getComment().setText(priceBtn);
    }

    private void maleFocusGained(java.awt.event.FocusEvent evt) {
        String mBtn = "Male";
        getComment().setText(mBtn);
    }

    private void femaleFocusGained(java.awt.event.FocusEvent evt) {                                   
          String fBtn = "Woman";
        getComment().setText(fBtn);
    }

    private JLabel styleLabel,comment,priceLabel,genderLabel,jLablelDuration, jLabelMinutes;
    private JSlider jSliderDuration;
    private JPanel detailsPanel,jPanelChoosing,detailsPanel2,detailsHaircutPanel,footer;
    private JButton deleteBtt,exitBtt,registerBtt;
    private javax.swing.ButtonGroup gender;
    private org.edisoncor.gui.panel.Panel header;
    private JRadioButton radioBtnMale,radioBtnfemale;
    private org.edisoncor.gui.panel.Panel newHairCutPanel;
    private JTextField price, style;


}
