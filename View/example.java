/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.edisoncor.gui.panel.Panel;

/**
 *
 * @author idnak
 */
public class example extends JDialog{
    
    
    public example(Frame parent, boolean modal){
        setTitle("Eample DateChooser");
        setPreferredSize(new Dimension(600,400));
        setMinimumSize(this.getPreferredSize());
        setResizable(modal);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        Panel header = new Panel();
        
        header.setPreferredSize(new Dimension(600,50));
        header.setBackground(Color.LIGHT_GRAY);
        
        add(header,BorderLayout.NORTH);
        
        
        Panel center = new Panel();
        
        center.setPreferredSize(new Dimension(600,200));
        
        center.setBackground(Color.white);
        
        JLabel time  = new JLabel("Time:");
        time.setPreferredSize(new Dimension(70,30));
        time.setFont(new Font(Font.SERIF,Font.ITALIC,12));
        
        center.add(time);
        
        JDateChooser time1 = new JDateChooser();
               
        time1.setPreferredSize(new Dimension(300,30));
        center.add(time1);
        
        add(center,BorderLayout.CENTER);
        
        Panel footer =  new Panel();
        footer.setPreferredSize(new Dimension(600,50));
        JButton exit = new JButton("Exit");
        
        exit.setPreferredSize(new Dimension(100,30));
        
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
            
        });
        
        footer.add(exit);
        
        add(footer,BorderLayout.SOUTH);
        

    }
    
     public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            example begin  = new example(new Frame(),true);
            
            begin.setVisible(true);
        });
    }
}
