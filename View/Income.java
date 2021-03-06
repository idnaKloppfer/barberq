
package View;

import Controller.ControllerReportIncome;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import datechooser.beans.DateChooserPanel;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author idnak
 */
public class Income extends javax.swing.JDialog {
    private ControllerReportIncome controllerReportIncome;
    
    /**
     *
     * @param controllerReportIncome
     */
    public void setControllerReportIncome(ControllerReportIncome controllerReportIncome){
        this.controllerReportIncome = controllerReportIncome;
        setListener();
    }

    /**
     *
     * @return
     */
    public ControllerReportIncome getControllerReportIncome(){
        return controllerReportIncome;
    }
    private void setListener(){
        process.addActionListener(controllerReportIncome);
        print.addActionListener(controllerReportIncome);
        radioButtonDay.addActionListener(controllerReportIncome);
        radioButtonMonth.addActionListener(controllerReportIncome);
        radioButtonRank.addActionListener(controllerReportIncome);   
    }

    /**
     *
     * @return
     */
    public JRadioButton getDay() {
        return radioButtonDay;
    }

    /**
     *
     * @param day
     */
    public void setDay(JRadioButton day) {
        this.radioButtonDay = day;
    }

    /**
     *
     * @return
     */
    public ButtonGroup getFilter() {
        return filter;
    }

    /**
     *
     * @param filter
     */
    public void setFilter(ButtonGroup filter) {
        this.filter = filter;
    }

    /**
     *
     * @return
     */
    public JButton getProcess() {
        return process;
    }

    /**
     *
     * @param process
     */
    public void setProcess(JButton process) {
        this.process = process;
    }

    /**
     *
     * @return
     */
    public JRadioButton getMonth() {
        return radioButtonMonth;
    }

    /**
     *
     * @param month
     */
    public void setMonth(JRadioButton month) {
        this.radioButtonMonth = month;
    }

    /**
     *
     * @return
     */
    public DateChooserPanel getCalendarRank() {
        return calendarrank;
    }

    /**
     *
     * @param rank
     */
    public void setCalendarRank(DateChooserPanel rank) {
        this.calendarrank = rank;
    }

    /**
     *
     * @return
     */
    public JYearChooser getYear() {
        return selectYear;
    }

    /**
     *
     * @param year
     */
    public void setYear(JYearChooser year) {
        this.selectYear = year;
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
    public JRadioButton getRank() {
        return radioButtonRank;
    }

    /**
     *
     * @param rank
     */
    public void setRank(JRadioButton rank) {
        this.radioButtonRank = rank;
    }

    /**
     *
     * @return
     */
    public JButton getPrint() {
        return print;
    }

    /**
     *
     * @param print
     */
    public void setPrint(JButton print) {
        this.print = print;
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
     * Creates new form income
     * @param parent
     * @param modal
     */
    public Income(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Income Report");
        setLocationRelativeTo(null);
        setResizable(true);
        setControllerReportIncome(new ControllerReportIncome(this));
        GregorianCalendar fech = new GregorianCalendar();
        getCalendarRank().setMaxDate(fech);
        getYear().setEndYear(fech.get(Calendar.YEAR));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filter = new javax.swing.ButtonGroup();
        panel1 = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        center = new javax.swing.JPanel();
        jPanelCenter = new javax.swing.JPanel();
        jLabelFilterBy = new javax.swing.JLabel();
        calendarrank = new datechooser.beans.DateChooserPanel();
        radioButtonDay = new javax.swing.JRadioButton();
        radioButtonMonth = new javax.swing.JRadioButton();
        selectYear = new com.toedter.calendar.JYearChooser();
        radioButtonRank = new javax.swing.JRadioButton();
        selectMonth = new com.toedter.calendar.JMonthChooser();
        jLabelMonth = new javax.swing.JLabel();
        jLabelYear = new javax.swing.JLabel();
        jPanelOptions = new javax.swing.JPanel();
        print = new javax.swing.JButton();
        process = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        comment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        panel1.setMaximumSize(new java.awt.Dimension(737, 504));
        panel1.setMinimumSize(new java.awt.Dimension(737, 273));
        panel1.setPreferredSize(new java.awt.Dimension(737, 352));
        panel1.setLayout(new java.awt.BorderLayout());

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/incomereport.jpg"))); // NOI18N
        header.setMaximumSize(new java.awt.Dimension(737, 75));
        header.setMinimumSize(new java.awt.Dimension(737, 75));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        panel1.add(header, java.awt.BorderLayout.NORTH);

        center.setOpaque(false);
        center.setLayout(new java.awt.BorderLayout());

        jPanelCenter.setPreferredSize(new java.awt.Dimension(683, 100));

        jLabelFilterBy.setText("Filter By:");

        calendarrank.setEnabled(false);

        filter.add(radioButtonDay);
        radioButtonDay.setText("Day");
        radioButtonDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonDayActionPerformed(evt);
            }
        });

        filter.add(radioButtonMonth);
        radioButtonMonth.setText("Monthly");

        filter.add(radioButtonRank);
        radioButtonRank.setText("Rank");

        selectMonth.setEnabled(false);
        selectMonth.setPreferredSize(new java.awt.Dimension(150, 24));

        jLabelMonth.setText("Month:");

        jLabelYear.setText("Year:");

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCenterLayout.createSequentialGroup()
                        .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMonth)
                            .addComponent(jLabelYear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCenterLayout.createSequentialGroup()
                                .addComponent(radioButtonDay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioButtonMonth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioButtonRank))
                            .addComponent(selectYear, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addComponent(calendarrank, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelFilterBy))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendarrank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCenterLayout.createSequentialGroup()
                        .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCenterLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabelFilterBy))
                            .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(radioButtonDay)
                                .addComponent(radioButtonMonth)
                                .addComponent(radioButtonRank)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selectMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelYear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selectYear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        center.add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelOptions.setPreferredSize(new java.awt.Dimension(450, 40));

        print.setBackground(new java.awt.Color(102, 102, 102));
        print.setFont(new java.awt.Font("Serif", 2, 18)); // NOI18N
        print.setForeground(new java.awt.Color(255, 255, 255));
        print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/print.png"))); // NOI18N
        print.setText("Print");
        print.setToolTipText("");
        print.setBorderPainted(false);
        print.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        print.setEnabled(false);
        print.setIconTextGap(10);
        print.setPreferredSize(new java.awt.Dimension(150, 30));
        print.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                printFocusGained(evt);
            }
        });
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        jPanelOptions.add(print);

        process.setBackground(new java.awt.Color(102, 102, 102));
        process.setFont(new java.awt.Font("Serif", 2, 18)); // NOI18N
        process.setForeground(new java.awt.Color(255, 255, 255));
        process.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/report.png"))); // NOI18N
        process.setText("To Show");
        process.setBorderPainted(false);
        process.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        process.setEnabled(false);
        process.setMaximumSize(new java.awt.Dimension(150, 30));
        process.setMinimumSize(new java.awt.Dimension(150, 30));
        process.setPreferredSize(new java.awt.Dimension(150, 30));
        process.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                processFocusGained(evt);
            }
        });
        jPanelOptions.add(process);

        exit.setBackground(new java.awt.Color(102, 102, 102));
        exit.setFont(new java.awt.Font("Serif", 2, 18)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("Exit");
        exit.setBorderPainted(false);
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exit.setPreferredSize(new java.awt.Dimension(150, 30));
        exit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                exitFocusGained(evt);
            }
        });
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        jPanelOptions.add(exit);

        center.add(jPanelOptions, java.awt.BorderLayout.SOUTH);

        panel1.add(center, java.awt.BorderLayout.CENTER);

        footer.setPreferredSize(new java.awt.Dimension(450, 32));
        footer.setLayout(new java.awt.BorderLayout());

        comment.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        footer.add(comment, java.awt.BorderLayout.CENTER);

        panel1.add(footer, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioButtonDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonDayActionPerformed

    private void printFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_printFocusGained
        String leyend = "Print Report";
        getComment().setText(leyend);
    }//GEN-LAST:event_printFocusGained

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printActionPerformed

    private void processFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_processFocusGained
        String leyend = "Show report";
        getComment().setText(leyend);
    }//GEN-LAST:event_processFocusGained

    private void exitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_exitFocusGained
        String leyend = "Get out";
        getComment().setText(leyend);
    }//GEN-LAST:event_exitFocusGained

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        this.dispose();
        this.setVisible(false);

    }//GEN-LAST:event_exitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserPanel calendarrank;
    private javax.swing.JPanel center;
    private javax.swing.JLabel comment;
    private javax.swing.JButton exit;
    private javax.swing.ButtonGroup filter;
    private javax.swing.JPanel footer;
    private org.edisoncor.gui.panel.Panel header;
    private javax.swing.JLabel jLabelFilterBy;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelYear;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelOptions;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JButton print;
    private javax.swing.JButton process;
    private javax.swing.JRadioButton radioButtonDay;
    private javax.swing.JRadioButton radioButtonMonth;
    private javax.swing.JRadioButton radioButtonRank;
    private com.toedter.calendar.JMonthChooser selectMonth;
    private com.toedter.calendar.JYearChooser selectYear;
    // End of variables declaration//GEN-END:variables
}
