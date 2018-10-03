
package View;

import com.toedter.calendar.JYearChooser;
import datechooser.beans.DateChooserPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import Controller.*;
import com.toedter.calendar.JMonthChooser;
import java.awt.Cursor;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author idnak
 */
public class Customers extends javax.swing.JDialog {
    
    private ControllerReport controllerReport;
    
    /**
     *
     * @param controllerReport
     */
    public void setControllerReport(ControllerReport controllerReport){
        this.controllerReport = controllerReport;
        setListener();
    }

    /**
     *
     * @return
     */
    public ControllerReport getControllerReport(){
        return controllerReport;
    }
    private void setListener(){
        btnProcess.addActionListener(controllerReport);
        btnPrint.addActionListener(controllerReport);
        radioBtnDay.addActionListener(controllerReport);
        radioBtnMonth.addActionListener(controllerReport);
        radioBtnRank.addActionListener(controllerReport);   
    }

    /**
     *
     * @return
     */
    public JRadioButton getRadioBtnDay() {
        return radioBtnDay;
    }

    /**
     *
     * @param radioBtnDay
     */
    public void setRadioBtnDay(JRadioButton radioBtnDay) {
        this.radioBtnDay = radioBtnDay;
    }

    /**
     *
     * @return
     */
    public ButtonGroup getFilterGroupRadioBtn() {
        return filterGroupRadioBtn;
    }

    /**
     *
     * @param filterGroupRadioBtn
     */
    public void setFilterGroupRadioBtn(ButtonGroup filterGroupRadioBtn) {
        this.filterGroupRadioBtn = filterGroupRadioBtn;
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
     * @param process
     */
    public void setBtnProcess(JButton process) {
        this.btnProcess = process;
    }

    /**
     *
     * @return
     */
    public JRadioButton getRadioBtnMonth() {
        return radioBtnMonth;
    }

    /**
     *
     * @param month
     */
    public void setRadioBtnMonth(JRadioButton month) {
        this.radioBtnMonth = month;
    }

    /**
     *
     * @return
     */
    public DateChooserPanel getCalendarRank() {
        return calendarRank;
    }

    /**
     *
     * @param rank
     */
    public void setCalendarRank(DateChooserPanel rank) {
        this.calendarRank = rank;
    }

    /**
     *
     * @return
     */
    public JYearChooser getChooseYear() {
        return chooseYear;
    }

    /**
     *
     * @param chooseYear
     */
    public void setChooseYear(JYearChooser chooseYear) {
        this.chooseYear = chooseYear;
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
    public JRadioButton getRadioBtnRank() {
        return radioBtnRank;
    }

    /**
     *
     * @param radioBtnRank
     */
    public void setRadioBtnRank(JRadioButton radioBtnRank) {
        this.radioBtnRank = radioBtnRank;
    }

    /**
     *
     * @return
     */
    public JButton getBtnPrint() {
        return btnPrint;
    }

    /**
     *
     * @param btnPrint
     */
    public void setBtnPrint(JButton btnPrint) {
        this.btnPrint = btnPrint;
    }

    /**
     *
     * @return
     */
    public JMonthChooser getSelectMonth() {
        return selectMonth;
    }

    /**
     *
     * @param selectMonth
     */
    public void setSelectMonth(JMonthChooser selectMonth) {
        this.selectMonth = selectMonth;
    }

    
    /**
     * Creates new form Customers
     * @param parent
     * @param model
     */
    public Customers(java.awt.Frame parent, boolean model) {
        super(parent, model);
        initComponents();
        setTitle("Customers Report");
        setLocationRelativeTo(null);
        setResizable(false);
        setControllerReport(new ControllerReport(this));
        GregorianCalendar date = new GregorianCalendar();
        
        getCalendarRank().setMaxDate(date);
        getChooseYear().setEndYear(date.get(Calendar.YEAR));

        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterGroupRadioBtn = new javax.swing.ButtonGroup();
        panelCustomers = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        center = new javax.swing.JPanel();
        jPanelCenterSelected = new javax.swing.JPanel();
        jLabelFilterBy = new javax.swing.JLabel();
        calendarRank = new datechooser.beans.DateChooserPanel();
        radioBtnDay = new javax.swing.JRadioButton();
        radioBtnMonth = new javax.swing.JRadioButton();
        chooseYear = new com.toedter.calendar.JYearChooser();
        radioBtnRank = new javax.swing.JRadioButton();
        selectMonth = new com.toedter.calendar.JMonthChooser();
        jLabelMonth = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jPanelOpciones = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        comment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(737, 352));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        panelCustomers.setMaximumSize(new java.awt.Dimension(737, 504));
        panelCustomers.setMinimumSize(new java.awt.Dimension(737, 273));
        panelCustomers.setPreferredSize(new java.awt.Dimension(737, 352));
        panelCustomers.setLayout(new java.awt.BorderLayout());

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/customersreport.jpg"))); // NOI18N
        header.setMaximumSize(new java.awt.Dimension(737, 75));
        header.setMinimumSize(new java.awt.Dimension(737, 75));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 737, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        panelCustomers.add(header, java.awt.BorderLayout.NORTH);

        center.setOpaque(false);
        center.setLayout(new java.awt.BorderLayout());

        jPanelCenterSelected.setPreferredSize(new java.awt.Dimension(683, 100));

        jLabelFilterBy.setText("Filter By:");

        calendarRank.setEnabled(false);

        filterGroupRadioBtn.add(radioBtnDay);
        radioBtnDay.setText("Day");
        radioBtnDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnDayActionPerformed(evt);
            }
        });

        filterGroupRadioBtn.add(radioBtnMonth);
        radioBtnMonth.setText("Monthly");

        filterGroupRadioBtn.add(radioBtnRank);
        radioBtnRank.setText("Rank");

        selectMonth.setEnabled(false);
        selectMonth.setPreferredSize(new java.awt.Dimension(150, 24));

        jLabelMonth.setText("Month:");

        jLabelYear.setText("Year:");

        javax.swing.GroupLayout jPanelCenterSelectedLayout = new javax.swing.GroupLayout(jPanelCenterSelected);
        jPanelCenterSelected.setLayout(jPanelCenterSelectedLayout);
        jPanelCenterSelectedLayout.setHorizontalGroup(
            jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterSelectedLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCenterSelectedLayout.createSequentialGroup()
                        .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMonth)
                            .addComponent(jLabelYear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCenterSelectedLayout.createSequentialGroup()
                                .addComponent(radioBtnDay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnMonth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtnRank))
                            .addComponent(chooseYear, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(calendarRank, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelFilterBy))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanelCenterSelectedLayout.setVerticalGroup(
            jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterSelectedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendarRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCenterSelectedLayout.createSequentialGroup()
                        .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCenterSelectedLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabelFilterBy))
                            .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radioBtnDay)
                                .addComponent(radioBtnMonth)
                                .addComponent(radioBtnRank)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCenterSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooseYear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        center.add(jPanelCenterSelected, java.awt.BorderLayout.CENTER);

        jPanelOpciones.setPreferredSize(new java.awt.Dimension(450, 40));

        btnPrint.setBackground(new java.awt.Color(102, 102, 102));
        btnPrint.setFont(new java.awt.Font("Serif", 2, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/print.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.setToolTipText("");
        btnPrint.setBorderPainted(false);
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPrint.setEnabled(false);
        btnPrint.setIconTextGap(10);
        btnPrint.setPreferredSize(new java.awt.Dimension(150, 30));
        btnPrint.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnPrintFocusGained(evt);
            }
        });
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanelOpciones.add(btnPrint);

        btnProcess.setBackground(new java.awt.Color(102, 102, 102));
        btnProcess.setFont(new java.awt.Font("Serif", 2, 18)); // NOI18N
        btnProcess.setForeground(new java.awt.Color(255, 255, 255));
        btnProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/report.png"))); // NOI18N
        btnProcess.setText("To Show");
        btnProcess.setBorderPainted(false);
        btnProcess.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProcess.setEnabled(false);
        btnProcess.setMaximumSize(new java.awt.Dimension(150, 30));
        btnProcess.setMinimumSize(new java.awt.Dimension(150, 30));
        btnProcess.setPreferredSize(new java.awt.Dimension(150, 30));
        btnProcess.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnProcessFocusGained(evt);
            }
        });
        jPanelOpciones.add(btnProcess);

        btnExit.setBackground(new java.awt.Color(102, 102, 102));
        btnExit.setFont(new java.awt.Font("Serif", 2, 18)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit");
        btnExit.setBorderPainted(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExit.setPreferredSize(new java.awt.Dimension(150, 30));
        btnExit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnExitFocusGained(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanelOpciones.add(btnExit);

        center.add(jPanelOpciones, java.awt.BorderLayout.SOUTH);

        panelCustomers.add(center, java.awt.BorderLayout.CENTER);

        footer.setPreferredSize(new java.awt.Dimension(450, 32));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        footer.add(comment, java.awt.BorderLayout.CENTER);

        panelCustomers.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelCustomers, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcessFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnProcessFocusGained
        String comment = "Show report";
        getComment().setText(comment);
    }//GEN-LAST:event_btnProcessFocusGained

    private void btnExitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnExitFocusGained
        String comment = "Exit";
        getComment().setText(comment);
    }//GEN-LAST:event_btnExitFocusGained

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
            this.dispose();
            this.setVisible(false);
            
    }//GEN-LAST:event_btnExitActionPerformed

    private void radioBtnDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnDayActionPerformed

    private void btnPrintFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnPrintFocusGained
         String comment = "Print Report";
        getComment().setText(comment);
    }//GEN-LAST:event_btnPrintFocusGained

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnProcess;
    private datechooser.beans.DateChooserPanel calendarRank;
    private javax.swing.JPanel center;
    private com.toedter.calendar.JYearChooser chooseYear;
    private javax.swing.JLabel comment;
    private javax.swing.ButtonGroup filterGroupRadioBtn;
    private javax.swing.JPanel footer;
    private org.edisoncor.gui.panel.Panel header;
    private javax.swing.JLabel jLabelFilterBy;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JPanel jPanelCenterSelected;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JRadioButton radioBtnMonth;
    private org.edisoncor.gui.panel.Panel panelCustomers;
    private javax.swing.JRadioButton radioBtnDay;
    private javax.swing.JRadioButton radioBtnRank;
    private com.toedter.calendar.JMonthChooser selectMonth;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param CURSOR_WAIT
     */
    public void setCursor(int CURSOR_WAIT) {
         this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    /**
     *
     */
    public void cursordafault() {
       this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
