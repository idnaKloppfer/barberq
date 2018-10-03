package Controller;

import Model.Service;
import View.Principal;
import View.Rservice;
import View.Vservice;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author idnak
 */
public class ControllerService implements ActionListener,KeyListener,MouseListener{

    DefaultTableModel dm;
    private Vservice vService;
    private Service serviceModel;
    private Principal principal;
    private Rservice rService;
    private ImageIcon icon=new ImageIcon(ControllerService.class.getResource("/View/img/checkmark.png"));
    
    /**
     *
     * @param vService
     */
    public ControllerService(Vservice vService){
        this.vService = vService;
        serviceModel = new Service();
         rService = new Rservice(principal,true); 
         rService.setControllerService(this);
        this.Tolist();
    }
    
    
    private void Tolist(){
        String[][] information =  serviceModel.serviceList();
        vService.getServiceTable().setModel(new javax.swing.table.DefaultTableModel(
        information,
        new String [] {"Services","Price","Duration"}) {
        boolean[] canEdit = new boolean [] {
            false,false,false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
});
        
    vService.getServiceTable().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    /**
     *
     * @param name
     */
    public void captureData(String name){
     
           boolean found = serviceModel.searchHairCutByNameService(name);
           if (found){//If the service is found at Vservice, all the details of that service will be displayed at Rservice
               vService.dispose();
               vService.setVisible(false);
               rService = new Rservice(principal,true);
               rService.getService().setText(serviceModel.getService());
               rService.getPrice().setText(String.valueOf(serviceModel.getPrice()));
                Calendar calendar = Calendar.getInstance();

                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                int sec = calendar.get(Calendar.SECOND);
                calendar.add(Calendar.HOUR_OF_DAY, -hours);
                calendar.add(Calendar.MINUTE, -mins);
                calendar.add(Calendar.SECOND, -sec);
                int duration = (int) serviceModel.getDuration();
                calendar.add(Calendar.MILLISECOND,duration);
                Time time = Time.valueOf(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND));
                rService.getTimeService().setText(time.toString());
                rService.setDurationService(serviceModel.getDuration());
                rService.setControllerService(this);
                rService.setVisible(true);
               
               }else{
               
                JOptionPane.showMessageDialog(principal,"service not found ","Service",JOptionPane.INFORMATION_MESSAGE);
            }
       }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        
        if(obj.equals(vService.getNewBtt())){
            vService.dispose();
            vService.setVisible(false);
            rService = new Rservice(principal,true); 
            serviceModel.setId(0);
            rService.getDeleteBtt().setVisible(false);
            rService.setControllerService(this);
            rService.setVisible(true);
               
        }else if(obj.equals(rService.getExitBtt())){
            rService.dispose();
            rService.setVisible(false);
            vService = new Vservice(principal,true);
            vService.setControllerService(this);
            Tolist();
            vService.setVisible(true);
        }else if(obj.equals(rService.getRgisterBtt())){
            this.record();
        }else if(obj.equals(rService.getDeleteBtt())){
            this.delete();
        }
        
    }
    private void delete(){
    	int opt = JOptionPane.showConfirmDialog(principal, "Confirm delete?", "Delete Person",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
if (opt != 0) { // if you regret and want to keep on with the work position on the list
			
			
		}
else {

    boolean result = serviceModel.deleteService();
    if(result){
        rService.dispose();
        rService.setVisible(false);
        
        vService = new Vservice(principal,true);
        vService.setControllerService(this);
        Tolist();
        vService.setVisible(true);
        vService.getComment().setText("The Service has been succesfully deleted");
    }else{
         String txt = "The Service has not deleted, check again";
         rService.getComment().setText(txt);
    }
}
}


    private void record(){

         if(rService.getService().getText().length() < 2 || rService.getService().getText().contains("  ")){
        	 JOptionPane.showMessageDialog(principal, "You can not register a Service that is empty or that contains less than 2 characters or contains multiple blancks", "Error",
 					JOptionPane.INFORMATION_MESSAGE);
                String txt = "You can not register a Service that is empty or that contains less than 2 characters or contains multiple blancks";
                rService.getComment().setText(txt);
            }else if(rService.getPrice().getText().isEmpty()){
            	JOptionPane.showMessageDialog(principal, "You can not register a Service without price", "Error",
     					JOptionPane.INFORMATION_MESSAGE);
                  String txt = "You can not register a Service without price";
                rService.getComment().setText(txt);
            }else{
                if(serviceModel.getId() > 0){
            serviceModel.setService(rService.getService().getText());
            serviceModel.setPrice(Double.parseDouble(rService.getPrice().getText()));
              serviceModel.setDuration(rService.getDurationService());
            boolean resultt = serviceModel.updateService();
            if(resultt){
            	JOptionPane.showMessageDialog(principal, "The Service has been succesfully modified" , "success",
						JOptionPane.DEFAULT_OPTION,icon);
                String txt = "The Service has been succesfully modified";
                rService.getComment().setText(txt);
                rService.dispose();
                rService.setVisible(false);
                
                vService = new Vservice(principal,true);
                vService.setControllerService(this);
                Tolist();
                vService.setVisible(true);
                
            }else{
                String txt = "The Service was not modified";
                rService.getComment().setText(txt);
            }
            
            }else if(serviceModel.getId() < 1){
            serviceModel.setService(rService.getService().getText());
            serviceModel.setPrice(Double.parseDouble(rService.getPrice().getText()));
            serviceModel.setDuration(rService.getDurationService());
              boolean resulttt = serviceModel.insertService();
            if(resulttt){
            	JOptionPane.showMessageDialog(principal, "The Service has been succesfully registered" , "success",
						JOptionPane.DEFAULT_OPTION,icon);
                String txt = "The Service has been succesfully registered";
                rService.getComment().setText(txt);
                rService.getComment().setText(txt);
                rService.dispose();
                rService.setVisible(false);
                
                vService = new Vservice(principal,true);
                vService.setControllerService(this);
                Tolist();
                vService.setVisible(true);
                
            }else{
                String comment = "The rService was not registered";
                rService.getComment().setText(comment);
            }
            }
            }
        
        
    }


   
    @Override
    public void keyTyped(KeyEvent ke) {
        Object obj = ke.getSource();
        if(obj.equals(vService.getServiceTable())){
            if(vService.getTextSearch().getText().length()>50){
                ke.consume();
            }
        }else if(obj.equals(rService.getPrice())){
            char b = ke.getKeyChar();
            if(!Character.isDigit(b) || rService.getPrice().getText().length() > 10){
                ke.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }
    
    /**
     *
     * @param query
     * @param jTableSearch
     */
    public void SearchList(String query, JTable jTableSearch){
        
        dm = (DefaultTableModel) jTableSearch.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        jTableSearch.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
         Object obj = ke.getSource();
        if(obj.equals(vService.getTextSearch())){
            String search = vService.getTextSearch().getText();
            SearchList(search,vService.getServiceTable());
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
          Object obj = me.getSource();
        if(obj.equals(vService.getServiceTable())){
            if (me.getClickCount() == 2) {
            try{
                int row = vService.getServiceTable().getSelectedRow();
                int row1 = vService.getServiceTable().convertRowIndexToModel(row);
                DefaultTableModel tableModel=(DefaultTableModel) vService.getServiceTable().getModel();
                String captureData = (String) tableModel.getValueAt(row1, 0);
                captureData(captureData);
            }catch(HeadlessException ex){
                System.out.println("Error: "+ex);
            }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
       
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
      
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }
    
}
