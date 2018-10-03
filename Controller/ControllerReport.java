package Controller;

import View.Customers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
//import com.sun.glass.ui.Cursor;
import datechooser.model.multiple.MultyModelBehavior;
import datechooser.model.multiple.PeriodSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author idnak
 */
public class ControllerReport implements ActionListener{
    
    
    private Customers customers;
    private Reports report;
    
    /**
     *
     * @param customer
     */
    public ControllerReport(Customers customer){
        this.customers = customer;
        report = new Reports();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        
        if(obj.equals(customers.getRadioBtnRank())){
            customers.getCalendarRank().setEnabled(true);
            customers.getSelectMonth().setEnabled(false);
            customers.getChooseYear().setEnabled(false);
            customers.getBtnProcess().setEnabled(true);
             customers.getCalendarRank().setBehavior(MultyModelBehavior.SELECT_PERIOD);
            customers.getBtnPrint().setEnabled(true);
            String comment = "Select the date range in the calendar panel";
            customers.getComment().setText(comment);
            
        }else if(obj.equals(customers.getRadioBtnDay())){
            customers.getCalendarRank().setEnabled(true);
            customers.getCalendarRank().setBehavior(MultyModelBehavior.SELECT_SINGLE);
            customers.getSelectMonth().setEnabled(false);
            customers.getChooseYear().setEnabled(false);
            customers.getBtnProcess().setEnabled(true);
            customers.getBtnPrint().setEnabled(true);
            
            
            String comment = "Select a single date in the calendar";
            customers.getComment().setText(comment);
        }else if(obj.equals(customers.getRadioBtnMonth())){
            customers.getCalendarRank().setEnabled(false);
            customers.getSelectMonth().setEnabled(true);
            customers.getBtnProcess().setEnabled(true);
            customers.getBtnPrint().setEnabled(true);
            customers.getChooseYear().setEnabled(true);
            
            
            String comment ="Select one month and one year, compatible to your request";
            customers.getComment().setText(comment);
            
        }else if(obj.equals(customers.getBtnProcess())){
            process(false);
        }else if(obj.equals(customers.getBtnPrint())){
            process(true);
        }
            

    }

    private void process(boolean flag){
        
        if(false == flag){//show the report
        	
            if(customers.getRadioBtnDay().isSelected()){//daily report customers
                 Date date = customers.getCalendarRank().getSelectedDate().getTime();
                 DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                 String strDate = formatDate.format(date);
                report.customersServiceDaily(strDate,false);
                this.clear();
            }else if(customers.getRadioBtnMonth().isSelected()){//monthly report customers
                int month = customers.getSelectMonth().getMonth();
                int year = customers.getChooseYear().getYear();
                report.customersServiceMonth(month,year,false);
                this.clear();
            }else if(customers.getRadioBtnRank().isSelected()){//customers report by rank
                if(customers.getCalendarRank().getSelectedPeriodSet().isSingleDate()){
                    String comment = "Select a period of dates by dragging the mouse on the calendar";
                    customers.getComment().setText(comment);
                    customers.cursordafault();
                }else{
                	
                    PeriodSet per = customers.getCalendarRank().getSelectedPeriodSet();
                    
                    ArrayList<Calendar> calenderArray = (ArrayList<Calendar>) per.getDates();
                    int size = calenderArray.size();
                    Date date1  = calenderArray.get(0).getTime();
                    Date date2 = calenderArray.get(size -1).getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate1 = dateFormat.format(date1);
                    String strDate2 = dateFormat.format(date2);
                    
                    report.customersServiceRank(strDate1,strDate2, false);
                    this.clear();
                }
               
            }
        }else{//print the report
        	
            if(customers.getRadioBtnDay().isSelected()){
             Date date = customers.getCalendarRank().getSelectedDate().getTime();
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String strDate = dateFormat.format(date);
                 report.customersServiceDaily(strDate,true);
                 this.clear();
            }else if(customers.getRadioBtnMonth().isSelected()){
                int month = customers.getSelectMonth().getMonth();
                int year = customers.getChooseYear().getYear();
                report.customersServiceMonth(month,year,true);
                this.clear();
            }else if(customers.getRadioBtnRank().isSelected()){
                if(customers.getCalendarRank().getSelectedPeriodSet().isSingleDate()){
                    String comment = "Select a period of dates by dragging the mouse on the calendar";
                    customers.getComment().setText(comment);
                }else{
                	
                    PeriodSet per = customers.getCalendarRank().getSelectedPeriodSet();
                    
                    ArrayList<Calendar> calenderArray = (ArrayList<Calendar>) per.getDates();
                    int size = calenderArray.size();
                    Date date1  = calenderArray.get(0).getTime();
                    Date date2 = calenderArray.get(size -1).getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate1 = dateFormat.format(date1);
                    String strDate2 = dateFormat.format(date2);
                    
                    
                    report.customersServiceRank(strDate1,strDate2, true);
                    this.clear();
                }
               
            }
        }
        
    }
    private void clear(){//clear all areas in the selection of the customer report
        customers.getBtnProcess().setEnabled(false);
        customers.getBtnPrint().setEnabled(false);
        customers.getFilterGroupRadioBtn().clearSelection();
        customers.getCalendarRank().setSelectedDate(new GregorianCalendar());
        customers.getCalendarRank().setEnabled(false);
        customers.cursordafault();
        
        
    }
}
