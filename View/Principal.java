package View;

import Controller.ControllerPrincipal;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import org.edisoncor.gui.panel.Panel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.edisoncor.gui.panel.Panel.Gradiente;

/**
 *
 * @author idnak
 */
public class Principal extends javax.swing.JFrame {

    private static ControllerPrincipal controllerPrincipal;

    /**
     *
     */
    protected static int idUser;
    private JLabel user,id,jobTittle,date,time;
    private JButton exit,close;
    
    /**
     *
     * @param idUser
     */
    public static void setIduser(int idUser){
     Principal.idUser = idUser;   
    }

    /**
     *
     * @return
     */
    public static int getIduser(){
     return Principal.idUser;   
    }
    
    /**
     *
     * @return
     */
    public static ControllerPrincipal getControllerPrincipal(){
      return controllerPrincipal;
    }

    /**
     *
     * @param controller
     */
    public void setControllerPrincipal(ControllerPrincipal controller){
     controllerPrincipal = controller;
     
    }

    /**
     *
     * @return
     */
    public static int getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     */
    public static void setIdUser(int idUser) {
        Principal.idUser = idUser;
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
    public JButton getClose() {
        return close;
    }

    /**
     *
     * @param close
     */
    public void setClose(JButton close) {
        this.close = close;
    }

    /**
     *
     * @return
     */
    public JTree getMenu() {
        return menu;
    }

    /**
     *
     * @param menu
     */
    public void setMenu(JTree menu) {
        this.menu = menu;
    }
    
    private void Listener(){
        getMenu().addTreeSelectionListener(controllerPrincipal);
        getMenu().addTreeExpansionListener(controllerPrincipal);
        getExit().addActionListener(controllerPrincipal);
        getClose().addActionListener(controllerPrincipal);
    }
    
    /**
     * Creates new form Principal
     */
    public Principal() {

        startwindow();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        Dimension frameSize = getSize();
        setControllerPrincipal(new ControllerPrincipal(this));
        this.Listener();
        setTitle("BarberQ");
        setLocationRelativeTo(null);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        screenshotscreen();
        dateTime();
        

    }

    /**
     *
     */
    public void dateTime(){
    Date date = new Date();
    DateFormat dateFormatHMS = new SimpleDateFormat("HH:mm:ss");
     this.getTime().setText("Time: "+dateFormatHMS.format(date));
//     obtain date
    DateFormat dateFormatDMY = new SimpleDateFormat("dd/MM/yyyy");
    this.getDate().setText("Date: "+dateFormatDMY.format(date));    
}
    
    
    private void header(Panel principal){
        headerPanel = new Panel();
        headerPanel.setColorSecundario(Color.WHITE);
        headerPanel.setForeground(Color.WHITE);
        ImageIcon icon = new ImageIcon(getClass().getResource("/View/img/header.png"));  
        headerPanel.setIcon(icon);
        headerPanel.setPreferredSize(new Dimension(0,100));
        headerPanel.setOpaque(true);
     
        principal.add(headerPanel,BorderLayout.NORTH);
    }
    private void footer(Panel principal){
        footer = new JPanel();
        footer.setPreferredSize(new Dimension(0,30));
        footer.setBackground(Color.LIGHT_GRAY);
        this.optionfooter(footer);
        principal.add(footer,BorderLayout.SOUTH);
    }
    private void optionfooter(JPanel footer){
        footer.setLayout(new GridLayout(1,5));       
        user = new JLabel("");
        user.setForeground(Color.BLACK);
        user.setFont(new Font("Aharoni", Font.BOLD, 15));
        footer.add(user);
        id = new JLabel("");
        id.setForeground(Color.BLACK);
        id.setFont(new Font("Aharoni", Font.BOLD, 15));
        footer.add(id);
        
        jobTittle = new JLabel("");
        jobTittle.setForeground(Color.BLACK);
        jobTittle.setFont(new Font("Aharoni", Font.BOLD, 15));
        footer.add(jobTittle);
        
        date = new JLabel("");
        date.setForeground(Color.BLACK);
        date.setFont(new Font("Aharoni", Font.BOLD, 15));
        footer.add(date);
        
        

        
        time = new JLabel("");
        time.setForeground(Color.BLACK);
        time.setFont(new Font("Aharoni", Font.BOLD, 15));
        footer.add(time);
        
    }
    private void menuSide(Panel principal){
        menuJpanel = new JPanel();
        menuJpanel.setBackground(Color.LIGHT_GRAY);
        menuJpanel.setLayout(new CardLayout());
        menuJpanel.setPreferredSize(new Dimension(400, 100));
 
        contentMenu = new JScrollPane();
        contentMenu.setPreferredSize(new Dimension(400, 100));
        
                
                menu = new JTree();
                contentMenu.setViewportView(menu);
                menu.setFont(new Font("Aharoni", Font.ITALIC, 26));
                menu.setPreferredSize(new Dimension(400, 100));
                menu.setMaximumSize(menu.getPreferredSize());
                menu.setMinimumSize(menu.getPreferredSize());
                menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
                menu.setForeground(Color.LIGHT_GRAY);
                menu.setBackground(Color.LIGHT_GRAY);
                
        optioneofMenu();
        principal.add(menuJpanel,BorderLayout.WEST);
    }
    private void center(Panel principal){
        centerPanel = new Panel();
        centerPanel.setColorSecundario(Color.WHITE);
        centerPanel.setForeground(Color.WHITE);
        centerPanel.setLayout(new BorderLayout(0,0));
        centerPanel.setPreferredSize(new Dimension(500, 100));
        centerPanel.setMinimumSize(new Dimension(200, 100));
        centerPanel.setMaximumSize(new Dimension(500, 100));
        ImageIcon icon = new ImageIcon(getClass().getResource("/View/img/center.png"));  
        centerPanel.setIcon(icon);
        JPanel optionButton = new JPanel();
        FlowLayout fl_optionButton = new FlowLayout(FlowLayout.RIGHT);
        optionButton.setOpaque(false);
        optionButton.setLayout(fl_optionButton);
        
        close = new JButton();
        ImageIcon closes = new ImageIcon(getClass().getResource("/View/img/close.png"));
        close.setIcon(closes);
        close.setBackground(Color.LIGHT_GRAY);
        
        close.setBorderPainted(false);
        close.setOpaque(false);
        close.setPreferredSize(new Dimension(150,30));
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        optionButton.add(close);
        
        exit = new JButton();
        ImageIcon close = new ImageIcon(getClass().getResource("/View/img/exit.png"));
        exit.setIcon(close);
        exit.setBackground(Color.LIGHT_GRAY);
        exit.setBorderPainted(false);
        exit.setOpaque(false);
        exit.setPreferredSize(new Dimension(150,30));
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        optionButton.add(exit);
        
        optionButton.setPreferredSize(new Dimension(0,50));
        centerPanel.add(optionButton,BorderLayout.SOUTH);
        menuJpanel.setPreferredSize(new Dimension(400,200));
        principal.add(centerPanel,BorderLayout.CENTER);
    }
    private void startwindow(){
       
        this.screenshotscreen();

        principalPanel = new Panel();
        principalPanel.setBackground(Color.WHITE);
      
        
        principalPanel.setLayout(new BorderLayout(0,0));
        
        this.header(principalPanel);
        this.footer(principalPanel);
        this.menuSide(principalPanel);
        this.center(principalPanel);

        getContentPane().add(principalPanel,BorderLayout.CENTER);
                
    }
    private void optioneofMenu(){
        barberQTree = new DefaultMutableTreeNode("BarberQ");
        
        mainNode = new DefaultMutableTreeNode("Main");
       
        
//        mainNode.add(new DefaultMutableTreeNode("Persons"));
        mainNode.add(new DefaultMutableTreeNode("Employees"));
        mainNode.add(new DefaultMutableTreeNode("Clients"));
        mainNode.add(new DefaultMutableTreeNode("Work position"));
        mainNode.add(new DefaultMutableTreeNode("Job title"));
        mainNode.add(new DefaultMutableTreeNode("Service"));
        mainNode.add(new DefaultMutableTreeNode("Haircut type"));
        barberQTree.add(mainNode);
        
        processNode = new DefaultMutableTreeNode("Process");
        processNode.add(new DefaultMutableTreeNode("Meetings"));
        barberQTree.add(processNode);
        
        reportNode = new DefaultMutableTreeNode("Reports");
        reportNode.add(new DefaultMutableTreeNode("Customers service"));
        reportNode.add(new DefaultMutableTreeNode("Incomes"));
        barberQTree.add(reportNode);
        
        administration = new DefaultMutableTreeNode("Administration");
        
        administration.add(new DefaultMutableTreeNode("Users"));
        barberQTree.add(administration);
        
        menu.setModel(new DefaultTreeModel(barberQTree));
        contentMenu.setBackground(new Color(Color.OPAQUE));
        menuJpanel.add(contentMenu,"MenuPrincipal");
        
    }
    private void screenshotscreen(){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;
    this.setMinimumSize(new Dimension(width - 300, height - 300));
    this.setMaximumSize(new Dimension(width, height));
}
    private Panel principalPanel,headerPanel,centerPanel;
    private JPanel footer,menuJpanel;
    private JScrollPane contentMenu;
    private JTree menu;
    private DefaultMutableTreeNode  barberQTree, mainNode,processNode, reportNode,
    administration;

    /**
     *
     * @return
     */
    public JLabel getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(JLabel user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public JLabel getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(JLabel id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public JLabel getJobTittle() {
        return jobTittle;
    }

    /**
     *
     * @param position
     */
    public void setJobTittle(JLabel position) {
        this.jobTittle = position;
    }

    /**
     *
     * @return
     */
    public JLabel getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(JLabel date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public JLabel getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(JLabel time) {
        this.time = time;
    }

    
}
