
package Controller;

import View.Vuser;
import View.Vemployee;
import View.Vhaircut;
import View.Vservice;
import View.Vjobtitle;
import View.Vmeeting;
import View.Vworkposition;
import View.Begin;
import View.Customers;
import View.Income;
import View.Vperson;
import View.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import Model.conection;

/**
 *
 * @author idnak
 */
public class ControllerPrincipal implements ActionListener, TreeExpansionListener, TreeSelectionListener{

    /**
     *
     */
    public Principal principal;
	private Vuser vUsers;
	private Vperson vPerson;
	private Vjobtitle vJobtitle;
	private Vworkposition vWorkPosition;
	private Vemployee vEmployee;
	private Vservice vService;
	private Vhaircut vHaircut;
	private Vmeeting vMeeting;
	private Customers customers;
	private Income income;
	private HoursSysteme  hr;

    /**
     *
     * @param principal
     */
    public ControllerPrincipal(Principal principal) {
		this.principal = principal;
		Runnable hre = new HoursSysteme();
		Thread t = new Thread(hre);
		t.setPriority(Thread.NORM_PRIORITY);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object event = ae.getSource();
		if (event.equals(principal.getExit())) {//exit form system
			String question = "You want to leave BarberQ?";
			int getOut = JOptionPane.showConfirmDialog(principal, question, "Get out", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (getOut == 0) {
				System.exit(0);
			} else {

			}

		} else if (event.equals(principal.getClose())) {//User Switching System
			principal.dispose();
			Begin start = new Begin();
			start.setVisible(true);
		}
	}


	@Override
    public void valueChanged(TreeSelectionEvent tse) {
          
        
       String select = principal.getMenu().getLastSelectedPathComponent().toString();
        Runnable runnable = new newThread(select);
        Thread t = new Thread(runnable);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
      
	}
     

	@Override
    public void treeExpanded(TreeExpansionEvent tee) {
       
    }

	@Override
    public void treeCollapsed(TreeExpansionEvent tee) {
       
    }

    
	/**
	 *We create a class that will contain what I really need to run in the second line of expenditure we call it
	 */
     class newThread implements Runnable{
        
        private String selection;
        
        
        
        public newThread(Object obj){
            this.selection = (String) obj;
              
        }

        @Override
        public void run() {
            if(selection.equalsIgnoreCase("Users")){
                vUsers = new Vuser(principal,true);
                vUsers.setVisible(true);
             
            }else if(selection.equalsIgnoreCase("Persons")){
                vPerson = new Vperson(principal,false);
                vPerson.setControllerPerson(new ControllerPerson(vPerson));
                vPerson.setVisible(true);
               
            }else if(selection.equalsIgnoreCase("Work Position")){
                vWorkPosition = new Vworkposition(principal,false);
                vWorkPosition.setControllerWorkPosition(new ControllerWorkPosition(vWorkPosition));
                vWorkPosition.setVisible(true);
                 
            }else if(selection.equalsIgnoreCase("Job Title")){
                  vJobtitle = new Vjobtitle(principal,false);
                vJobtitle.setControllerJobTittle(new ControllerJobTitle(vJobtitle));
                vJobtitle.setVisible(true);
               
            }else if(selection.equalsIgnoreCase("employees")){
                vEmployee = new Vemployee(principal,false);
                vEmployee.setControllerEmployee(new ControllerEmployee(vEmployee));
                vEmployee.setVisible(true);

            }else if(selection.equalsIgnoreCase("Service")){
                 vService = new Vservice(principal,false);
               vService.setControllerService(new ControllerService(vService));
                vService.setVisible(true);

            }else if(selection.equalsIgnoreCase("Haircut type")){
                vHaircut = new Vhaircut(principal,false);
                vHaircut.setControllerHaircut(new ControllerHaircut(vHaircut));
                vHaircut.setVisible(true);

            }else if(selection.equalsIgnoreCase("Clients")){
                vPerson = new Vperson(principal,false);
                ImageIcon iconp = new ImageIcon(getClass().getResource("/View/img/header/Client.png"));
                vPerson.getHeader().setIcon(iconp);
                vPerson.setTitle("Client");
                vPerson.setController(new ControllerClient(vPerson));
                vPerson.setVisible(true);

            }else if(selection.equalsIgnoreCase("Meetings")){
                    vMeeting = new Vmeeting(principal, false);
                    vMeeting.setControllerMeeting(new ControllerMeeting(vMeeting));
                    vMeeting.setVisible(true);
            }else if(selection.equalsIgnoreCase("Customers Service")){
                    customers = new Customers(principal, false);
                    customers.setVisible(true);
            }else if(selection.equalsIgnoreCase("Incomes")){
                    income = new Income(principal, false);
                    income.setVisible(true);
            }
        }
        
    }
     
     class HoursSysteme implements Runnable {

		@Override
		public void run() {
			boolean flag = true;
			while(flag) {
				principal.dateTime();
			}
		}
    	 
     }
    
}
