package Controller;

import View.Income;
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
public class ControllerReportIncome implements ActionListener{
    
    
    private Income income;
    private final Reports report;
    
    /**
     *
     * @param income
     */
    public ControllerReportIncome(Income income){
       this.income = income;
        report = new Reports();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        
        if(obj.equals(income.getRank())){
            income.getCalendarRank().setEnabled(true);
            income.getSelectMonth().setEnabled(false);
            income.getYear().setEnabled(false);
            income.getProcess().setEnabled(true);
            income.getCalendarRank().setBehavior(MultyModelBehavior.SELECT_PERIOD);
            income.getPrint().setEnabled(true);
            String comment = "Select the date range in the calendar panel";
            income.getComment().setText(comment);
            
        }else if(obj.equals(income.getDay())){
            income.getCalendarRank().setEnabled(true);
            income.getCalendarRank().setBehavior(MultyModelBehavior.SELECT_SINGLE);
            income.getSelectMonth().setEnabled(false);
            income.getYear().setEnabled(false);
            income.getProcess().setEnabled(true);
            income.getPrint().setEnabled(true);
            
            
            String comment = "Select a single date in the calendar";
            income.getComment().setText(comment);
        }else if(obj.equals(income.getMonth())){
            income.getCalendarRank().setEnabled(false);
            income.getSelectMonth().setEnabled(true);
            income.getProcess().setEnabled(true);
            income.getPrint().setEnabled(true);
            income.getYear().setEnabled(true);
            
            
            String comment = "Select one month and one year, compatible  to your request";
            income.getComment().setText(comment);
            
        }else if(obj.equals(income.getProcess())){
            process(false);
        }else if(obj.equals(income.getPrint())){
            process(true);
        }
    }

    private void process(boolean flag){
 
        if(false == flag){
            if(income.getDay().isSelected()){
                 Date date = income.getCalendarRank().getSelectedDate().getTime();
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String strDate = dateFormat.format(date);
                 
               report.incomeDaily(strDate,false);
                this.clear();
            }else if(income.getMonth().isSelected()){
                int month = income.getSelectMonth().getMonth();
                int year = income.getYear().getYear();
                report.incomeMonth(month,year,false);
                this.clear();
            }else if(income.getRank().isSelected()){
                if(income.getCalendarRank().getSelectedPeriodSet().isSingleDate()){
                    String comment = "Select a period of dates by dragging the mouse on the calendar";
                    income.getComment().setText(comment);
                }else{
                	
                    PeriodSet per = income.getCalendarRank().getSelectedPeriodSet();
                    
                    ArrayList<Calendar> calendarArray = (ArrayList<Calendar>) per.getDates();
                    int size = calendarArray.size();
                    Date date1  = calendarArray.get(0).getTime();
                    Date date2 = calendarArray.get(size -1).getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate1 = dateFormat.format(date1);
                    String strDate2 = dateFormat.format(date2);
                    
                    
                    report.incomeRank(strDate1,strDate2, false);
                    this.clear();
                }
               
            }
        }else{
        	
            if(income.getDay().isSelected()){
             Date date = income.getCalendarRank().getSelectedDate().getTime();
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String strDate = dateFormat.format(date);
                 report.incomeDaily(strDate,true);
                 this.clear();
            }else if(income.getMonth().isSelected()){
                 int month = income.getSelectMonth().getMonth();
                int year = income.getYear().getYear();
                report.incomeMonth(month,year,true);
                this.clear();
            }else if(income.getRank().isSelected()){
                if(income.getCalendarRank().getSelectedPeriodSet().isSingleDate()){
                    String comment = "Select a period of dates by dragging the mouse on the calendar";
                    income.getComment().setText(comment);
                }else{
                    PeriodSet per = income.getCalendarRank().getSelectedPeriodSet();
                    
                    ArrayList<Calendar> calendarArray = (ArrayList<Calendar>) per.getDates();
                    int size = calendarArray.size();
                    Date date1  = calendarArray.get(0).getTime();
                    Date date2 = calendarArray.get(size -1).getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate1 = dateFormat.format(date1);
                    String strDate2 = dateFormat.format(date2);
                    
                    
                    report.incomeRank(strDate1,strDate2, true);
                    this.clear();
                }
               
            }
        }
        
    }
  
    private void clear(){
        income.getProcess().setEnabled(false);
        income.getPrint().setEnabled(false);
        income.getFilter().clearSelection();
        income.getCalendarRank().setSelectedDate(new GregorianCalendar());
        income.getCalendarRank().setEnabled(false);
        
    }
}
