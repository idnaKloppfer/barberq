package Controller;

import View.*;
import java.awt.HeadlessException;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.*;
import Model.*;

import java.awt.Color;
import java.sql.SQLException;
import java.sql.Time;

import java.text.SimpleDateFormat;
import java.util.*;

import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author idnak
 */
public class ControllerMeeting
		implements ActionListener, MouseListener, KeyListener, ChangeListener, ListSelectionListener {

	private DefaultTableModel dm;
	private Time hourSelected;
	private final Meeting METTING_MODEL;
	private final Client CLIENT_MODEL;
	private final Employee EMPLOYEE_MODEL;
	private final HairCut HAIR_CUT_MODEL;
	private final Reports REPORT;
	private final Service MODEL_SERVICES;
	private Vmeeting listVMeeting = null;
        public Rmeeting rMeeting = null;
	private Vperson vPerson = null;
	private Vemployee vEmployee = null;
	private Vhaircut vHairCut = null;
	private Principal principal;

        public boolean update = false;
	private ArrayList<Time> busyHours, startTimeQueue, endTimeQueue;
	private ArrayList busyHoursOfEmp;
	private completedMeeting completedMeeting = null;
	private double  priceTotal;
	private long timeOfService = 0;

	private ArrayList<String> SERVICES_SELECTED = new ArrayList();

    public DefaultListModel dMAllList, selectedListt;

    public ControllerMeeting(Vmeeting list) {
		this.listVMeeting = list;
		METTING_MODEL = new Meeting();
		EMPLOYEE_MODEL = new Employee();
		CLIENT_MODEL = new Client();
		HAIR_CUT_MODEL = new HairCut();
		REPORT = new Reports();
		MODEL_SERVICES = new Service();
		vEmployee = new Vemployee(principal, true);
		vEmployee.setControllerMetting(this);
		rMeeting = new Rmeeting(principal, true);
		vPerson = new Vperson(principal, true);
		vHairCut = new Vhaircut(principal, true);
		completedMeeting = new completedMeeting(principal, true);
		Tolist();
	}

	// All actions of the buttons
	@Override
	public void actionPerformed(ActionEvent ae) {

		Object event = ae.getSource();
		if (event.equals(listVMeeting.getNewBtt())) {

			listVMeeting.setVisible(false);
			listVMeeting.close();
			METTING_MODEL.setIdMeetint(0);
			update = false;
			try {
				busyHours.clear();
			} catch (Exception e) {

			}
			rMeeting = new Rmeeting(principal, true);
			rMeeting.setControllerMeeting(this);
			rMeeting.getBtnUpdate().setVisible(false);
			rMeeting.getDelete().setVisible(false);
			rMeeting.getSEmployee().setEnabled(true);
			rMeeting.getSHaircut().setEnabled(true);

			rMeeting.getBtnAdd().setEnabled(false);
			rMeeting.getBtnBack().setEnabled(false);
			timeOfService = 0;

			rMeeting.setVisible(true);

		} else if (event.equals(rMeeting.getBtnExit())) {
			listVMeeting = new Vmeeting(principal, true);
			busyHours = null;
			startTimeQueue = null;
			endTimeQueue = null;
			rMeeting.close();
			rMeeting.setVisible(false);

			update = false;

			listVMeeting.setControllerMeeting(this);
			Tolist();

			listVMeeting.setVisible(true);
		} else if (event.equals(rMeeting.getSClient())) {

			vPerson.setController(this);
			TolistClient();
			vPerson.setVisible(true);

		} else if (event.equals(rMeeting.getSEmployee())) {

			SERVICES_SELECTED = new ArrayList();
			vEmployee.getNewBtt().setEnabled(false);
			Calendar date1 = rMeeting.getChooseDateMetting().getCalendar();
			String day = date1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
			TolistEmployee(day);
			vEmployee.setVisible(true);
		} else if (event.equals(vPerson.getNewPerson())) {
			Rclient personClient = new Rclient(principal, true);
			personClient.setControllerClient(new ControllerClient(personClient, vPerson,true));
			personClient.getDelete().setVisible(false);
			personClient.setVisible(true);
			vPerson.setTitle("Client");
                        vPerson.dispose();
			TolistClient();
		} else if (event.equals(rMeeting.getDelete())) {
			this.delete();
		} else if (event.equals(rMeeting.getBtnProcess())) {

			this.record();
		} else if (event.equals(rMeeting.getBtnUpdate())) {
			this.update();
		} else if (event.equals(rMeeting.getSHaircut())) {
			vHairCut = new Vhaircut(principal, true);
			vHairCut.setController(this);
			vHairCut.getNewBtt().setEnabled(false);
			TolistHaircut();
			vHairCut.setVisible(true);

		} else if (event.equals(rMeeting.getBtnAdd())) {
			moveSelection(rMeeting.getServicesList(), rMeeting.getSelectService(), true);
			ListModel<String> modelSelect = (ListModel<String>) rMeeting.getSelectService().getModel();

			try {
				MODEL_SERVICES.addPriceServiceToArray(modelSelect);
			} catch (SQLException ex) {
				Logger.getLogger(ControllerMeeting.class.getName()).log(Level.SEVERE, null, ex);
			}
			this.priceTotal();
		} else if (event.equals(rMeeting.getBtnBack())) {
			moveSelection(rMeeting.getSelectService(), rMeeting.getServicesList(), false);
			ListModel<String> modelSelect = (ListModel<String>) rMeeting.getSelectService().getModel();
			try {
				MODEL_SERVICES.addPriceServiceToArray(modelSelect);
			} catch (SQLException ex) {
				Logger.getLogger(ControllerMeeting.class.getName()).log(Level.SEVERE, null, ex);
			}
			this.priceTotal();
		} else if (event.equals(completedMeeting.getProcess())) {
			this.completed();
		}
	}

	private void Tolist() {
		String[][] information = METTING_MODEL.listMetting();
		listVMeeting.gettableMeeting().setModel(new javax.swing.table.DefaultTableModel(information,
				new String[] { "Phone", "Name", "Last Name", "Date", "Time in","Time out","employee" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false,false,false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		listVMeeting.gettableMeeting().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

    /**
     *
     * @param phone
     * @param opc
     */
    public void captureData(long phone, int opc) {

		if (opc == 1) {
			boolean found = METTING_MODEL.searchMeetingByClientPhone(phone);
			this.update = true;
			if (found) {
				busyHours = new ArrayList();
				listVMeeting.setVisible(false);
				listVMeeting.close();
				update = true;
				rMeeting = new Rmeeting(principal, true);

				boolean cModel = CLIENT_MODEL.searchClientById(METTING_MODEL.getClientId());
				if (cModel) {
					rMeeting.getPhoneClient().setText("0"+String.valueOf(CLIENT_MODEL.getPhone()));
					rMeeting.getFirstNameClient().setText(CLIENT_MODEL.getName());
					rMeeting.getLastNameclient().setText(CLIENT_MODEL.getLastName());
				}
				rMeeting.getChooseDateMetting().setDate(java.sql.Date.valueOf(METTING_MODEL.getDate()));
				hourSelected = METTING_MODEL.getHour();
				busyHours.add(METTING_MODEL.getHour());
				rMeeting.getBtnTime().setText("Selected time: " + hourSelected.toString());
				rMeeting.getBtnTime().setBackground(Color.green);

				boolean hcModel = HAIR_CUT_MODEL.searchHaircutById(METTING_MODEL.getHaircutId());
				boolean empModel = EMPLOYEE_MODEL.captureEmployeeById(METTING_MODEL.getEmployeeId());
				if (hcModel && empModel) {
					rMeeting.getHaircut().setText(HAIR_CUT_MODEL.getStyle());
					rMeeting.getEmployeeSupport().setText(EMPLOYEE_MODEL.getName());
				}
				TolistServices();
				ArrayList listt = METTING_MODEL.MeeServ();
                                   System.out.println(listt+" :aaa");
				for (int i = 0; i < listt.size(); i++) {
					rMeeting.getServicesList().setSelectedValue(listt.get(i), true);
					this.moveSelection(rMeeting.getServicesList(), rMeeting.getSelectService(), true);
				}
				timeOfService = 0;
				timeOfService = METTING_MODEL.getDurationService() + HAIR_CUT_MODEL.getDuration();

				rMeeting.getBtnProcess().setEnabled(true);

				rMeeting.setControllerMeeting(this);
				rMeeting.getBtnUpdate().setVisible(true);

				rMeeting.getSClient().setEnabled(false);
				rMeeting.getSEmployee().setEnabled(true);
				rMeeting.getSHaircut().setEnabled(true);
				rMeeting.getBtnAdd().setEnabled(false);
				rMeeting.getBtnBack().setEnabled(false);

				rMeeting.setVisible(true);
			} else {

				JOptionPane.showMessageDialog(new JFrame(), "Record not found", "Meeting",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (opc == 2) {
			boolean found = CLIENT_MODEL.searceModelPersonByPhone(phone);
			if (found) {
				vPerson.setVisible(false);
				rMeeting.getPhoneClient().setText(String.valueOf(CLIENT_MODEL.getPhone()));
				rMeeting.getFirstNameClient().setText(CLIENT_MODEL.getName());
				rMeeting.getLastNameclient().setText(CLIENT_MODEL.getLastName());

			} else {

				JOptionPane.showMessageDialog(new JFrame(), "Record not found", "Meeting",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (opc == 3) {

			vEmployee.setVisible(false);

			boolean flag = EMPLOYEE_MODEL.captureEmployeeByPhone(phone);// capture employee ...
			if (flag) {

				METTING_MODEL.setEmployeeId(EMPLOYEE_MODEL.getIdEmployee());

				Time entryTime = Time.valueOf(EMPLOYEE_MODEL.getEntryTime());// capture the employee's time of entry
				Time departureTime = Time.valueOf(EMPLOYEE_MODEL.getDepartureTime());// capture the employee's
																						// departure time

				// capture the selected hours.
				Time timeIn = Time.valueOf(rMeeting.getTime().getTimeField().getText());
				Time timeEnd = Time.valueOf(rMeeting.getRankTime().getTimeField().getText());

				//take it to Gregorian time, the time of entry
				Calendar entry = Calendar.getInstance();
				entry.setTimeInMillis(entryTime.getTime());

				// employee's departure time
				Calendar depart = Calendar.getInstance();
				depart.setTimeInMillis(departureTime.getTime());

				// The first time of the range selection
				Calendar selectIn = Calendar.getInstance();
				selectIn.setTimeInMillis(timeIn.getTime());

				// Last time selected in range
				Calendar selectEnd = Calendar.getInstance();
				selectEnd.setTimeInMillis(timeEnd.getTime());

				//verify the selected time intervals in the selected work plan ...
				if (selectIn.before(entry) && selectEnd.before(entry) || selectIn.after(depart)) {

					String comment = "You can't select a time outside of the employee's chosen work time";
					rMeeting.getComment().setText(comment);
					rMeeting.getBtnProcess().setEnabled(false);
					JOptionPane.showMessageDialog(principal, comment);
					rMeeting.getComment().setForeground(Color.red);
					rMeeting.getBtnProcess().setEnabled(false);
				} else {

					catchHoursRangeEmployee(entry, depart, selectIn, selectEnd);

				}
			}

		}

	}



	private void catchHoursRangeEmployee(Calendar entry, Calendar depart, Calendar selectin, Calendar selectend) {

		long hourEntry = entry.getTimeInMillis();// hour entry time of employee
		long hourDeparture = depart.getTimeInMillis();// hour departure time of employee
		long selectIn = selectin.getTimeInMillis();// first hour selected in rank
		long selectEnd = selectend.getTimeInMillis(); // end hour selected in rank
		// long hour = 3600000; // One hour equals 3600 seconds
		long millisecondQuarterHour = 900000; // amount in millisecond the equivalent of 1/4 hour

		// create an order in which record every 1/4 hour of the employee's working hours.
		
		ArrayList<Time> listHoursOfEmp = new ArrayList<Time>();
		listHoursOfEmp.add(new Time(hourEntry));// Converts milliseconds to hours
		long mhEmp = hourEntry + millisecondQuarterHour;
		for (long i = hourEntry; i <= hourDeparture; i++) {
			if (i == mhEmp) {
				listHoursOfEmp.add(new Time(i));
				mhEmp += millisecondQuarterHour;
                                System.out.println(listHoursOfEmp);
			}
		}

        // create an order in which record every 1/4 hour of the client's avialable hours.		
		ArrayList<Time> listHoursOfClient = new ArrayList<Time>();
		long mhClient = selectIn + millisecondQuarterHour;
		listHoursOfClient.add(new Time(selectIn));// Converts milliseconds to hours

		for (long i = selectIn; i <= selectEnd; i++) {
			if (i == mhClient) {
				listHoursOfClient.add(new Time(i));
				mhClient += millisecondQuarterHour;
                                System.out.println("client hours: "+listHoursOfClient);
			}
		}

		// capture all the hours that are in the client's range and contain in the employee's working time ...
		ArrayList<Time> listHoursOfQue = new ArrayList<Time>();

		for (int i = 0; i < listHoursOfClient.size(); i++) {
			if (listHoursOfEmp.contains(listHoursOfClient.get(i))) {
				listHoursOfQue.add(listHoursOfClient.get(i));
                                System.out.println("aaa: "+listHoursOfQue);
                                
			}
		}

		
		// get the busy hours of the employee on that day ..
		busyHoursOfEmp = METTING_MODEL.findBusyHoursOfEmployee(EMPLOYEE_MODEL.getIdEmployee(),
				rMeeting.getChooseDateMetting().getDate());
		startTimeQueue = new ArrayList();
		endTimeQueue = new ArrayList();
		ArrayList hoursOccupied1 = (ArrayList) busyHoursOfEmp.get(0); // hours of entry meeting	 
		ArrayList hoursOccupied2 = (ArrayList) busyHoursOfEmp.get(1); // hours of departure meeting

                System.out.println("1: " + hoursOccupied1.toString() + "2: " + hoursOccupied2.toString());

		//Conversion of the entry meeting and departure meeting to the TIME variable type
		for (int i = 0; i < hoursOccupied1.size(); i++) {
			startTimeQueue.add(Time.valueOf((String) hoursOccupied1.get(i)));
			endTimeQueue.add(Time.valueOf((String) hoursOccupied2.get(i)));

		}
//		System.out.println("no clear: " + startTimeQueue.toString() + " " + endTimeQueue.toString());

		busyHours = new ArrayList();// The hours during which the employee can not provide service
		ArrayList eT = (ArrayList) busyHoursOfEmp.get(0);// starting time of que by employee.
		ArrayList dT = (ArrayList) busyHoursOfEmp.get(1); // ending time of que by employee.
                
                System.out.println("et: "+ eT +" ,dt "+dT);
                
		for (int i = 0; i < eT.size(); i++) {

			long entryTimeIn = Time.valueOf((String) eT.get(i)).getTime(); // convert to long time, of Et = Entry Time
			long departureTimeOut = Time.valueOf((String) dT.get(i)).getTime(); // convert to long time, Dt = Departure Time
//                        System.out.println("entryTimeIn: "+entryTimeIn);

			busyHours.add(new Time(entryTimeIn)); // Add hours in arrayList<Time> BusyHours
			long mhBusy = entryTimeIn + millisecondQuarterHour; // Sum of millisecond in 1/4 of hours return long 
			
                        
			//Verify entryTimeIn not < departureTimeOut = hours of exit meeting service .. increase the value
			for (long u = entryTimeIn; u < departureTimeOut; u++) {
				if (u == mhBusy) {
					busyHours.add(new Time(u));
					mhBusy += millisecondQuarterHour;
                                            
				}
			}

		}


		// the free hours of the employee between the time rank of the client ask for
		ArrayList freeHours = new ArrayList();
		for (int i = 0; i < listHoursOfQue.size(); i++) {
			if (busyHours.contains((Time) listHoursOfQue.get(i))) {
			
			} else {
				freeHours.add(listHoursOfQue.get(i));
                                System.out.println("free: "+freeHours);
			}
		}

		this.checkAvailablity(freeHours);

	}





	private void checkAvailablity(ArrayList fHours) {

		if (fHours.isEmpty()) {
			String message = "Excuse me but all the hours are occupied for the selected employee.\n "
					+ "Please check for changing the time range or the day";

			JOptionPane.showMessageDialog(principal, message, "Check Available", JOptionPane.WARNING_MESSAGE);
		} else {
			this.hourSelected = (Time) fHours.get(0); // allways take the first free hour

			String message2 = "Excellent if there is availability for the client. \n" + " The assigned time is : "
					+ hourSelected.toString();
			JOptionPane.showMessageDialog(principal, message2, "Check Available", JOptionPane.INFORMATION_MESSAGE);

			// Once an hour is selected, the selected time enters the meeting in green, and
			rMeeting.getBtnTime().setText("Selected time: " + hourSelected.toString());
			rMeeting.getBtnTime().setBackground(Color.green);
			rMeeting.getEmployeeSupport().setText(EMPLOYEE_MODEL.getName());
			rMeeting.getBtnProcess().setEnabled(false);
			vEmployee.setVisible(false);

		}

	}

	// all the service in the barberQ
	private void TolistServices() {
		JList list = rMeeting.getServicesList();
		MODEL_SERVICES.listServices(list);
	}

	// search all hair cut in barberQ
	private void captureHairCutData(String style) {
		boolean found = HAIR_CUT_MODEL.searchHairCutByNameStyle(style);
		if (found) {
			try {
				selectedListt.clear();
			} catch (NullPointerException e) {

			}

			TolistServices();
			rMeeting.getBtnProcess().setEnabled(true);
			rMeeting.getBtnAdd().setEnabled(true);
			rMeeting.getBtnBack().setEnabled(true);
			vHairCut.dispose();
			vHairCut.setVisible(false);
			timeOfService = 0; 
			timeOfService += HAIR_CUT_MODEL.getDuration(); // sum of type of cut 

			rMeeting.getHaircut().setText(HAIR_CUT_MODEL.getStyle());
			checkDuration();
//			priceHaircut = HAIR_CUT_MODEL.findPriceofHairCut();
			this.priceTotal();

		} else {

			JOptionPane.showMessageDialog(principal, "Record not found", "Meeting", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void checkDuration() {
		//a hour selected of client system automatic  + time of service ...  = Time Duration.. 
		Time duration = new Time(this.hourSelected.getTime() + timeOfService);
		System.out.println("Duration: " + duration);
		if (busyHours.size() > 0) {

			for (int i = 0; i < startTimeQueue.size(); i++) {
				if (duration.after((Time) startTimeQueue.get(i)) && duration.before((Time) endTimeQueue.get(i))
						|| duration.equals((Time) endTimeQueue.get(i)) || duration.after((Time) endTimeQueue.get(i))
								&& hourSelected.before((Time) endTimeQueue.get(i))) {
					JOptionPane.showMessageDialog(principal,
							"Please change the time, since the limit exceeds the free time");
					timeOfService -= HAIR_CUT_MODEL.getDuration();
					rMeeting.getHaircut().setText("");
					this.reloadEmployee();
				}
			}

		}
	}

	// Checks employee availability by the selected date and returns the list of
	// employees who work on that day

    /**
     *
     */
	public void reloadEmployee() {

		try {
			if (!rMeeting.getEmployeeSupport().getText().isEmpty()) {

				Calendar date1 = rMeeting.getChooseDateMetting().getCalendar();
				String day = date1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
				TolistEmployee(day);
				rMeeting.getEmployeeSupport().setText("");

				if (update) {

					rMeeting.getBtnAdd().setEnabled(true);
					rMeeting.getBtnBack().setEnabled(true);
				}

			}
		} catch (Exception e) {
			if (!rMeeting.getEmployeeSupport().getText().isEmpty()) {

				Calendar date1 = Calendar.getInstance();
				date1.setTime(METTING_MODEL.getDatetime());
				String day = date1.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
				TolistEmployee(day);
				rMeeting.getEmployeeSupport().setText("");

				if (update) {

					rMeeting.getBtnAdd().setEnabled(true);
					rMeeting.getBtnBack().setEnabled(true);
				}

			}
		}

	}

	private void completed() {

		METTING_MODEL.setDiscount(completedMeeting.getDiscount().getValue());
		METTING_MODEL.setTotalPrice(Double.parseDouble(completedMeeting.getLtotal().getText()));

		// Returns true if the queue has been executed. Otherwise he will return false
		boolean complete = false;
		try {
			complete = METTING_MODEL.completedMeeting();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// If the queue completed successfully. Gives option to print invoice
		if (complete) {
			String message3 = "Succesfully processed the appoinment.\n do you want to print an invoice?";

			int optInvoice = JOptionPane.showConfirmDialog(principal, message3, "Invoice", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (optInvoice == 0) {
				REPORT.invoiceMeeting(METTING_MODEL.getIdMeetint(), true);

				this.completedMeeting.setVisible(false);

				rMeeting.setVisible(false);
				rMeeting.close();
				listVMeeting = new Vmeeting(principal, true);
				listVMeeting.setControllerMeeting(new ControllerMeeting(listVMeeting));
				Tolist();
				listVMeeting.setVisible(true);
			} else {

				this.completedMeeting.setVisible(false);

				rMeeting.setVisible(false);
				listVMeeting = new Vmeeting(principal, true);
				listVMeeting.setControllerMeeting(new ControllerMeeting(listVMeeting));
				Tolist();

				listVMeeting.setVisible(true);
			}
		}
	}

	//
	private void update() {
		if (METTING_MODEL.getIdMeetint() > 0) {

			if (rMeeting.getEmployeeSupport().getText().isEmpty()) {
				String message = "You must select an employee";
				rMeeting.getComment().setText(message);
			} else {

				Date date = rMeeting.getChooseDateMetting().getDate();

				SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
				METTING_MODEL.setHour(hourSelected);
				String dateFormat = date1.format(date);
				METTING_MODEL.setDate(dateFormat);

				METTING_MODEL.setEmployeeId(EMPLOYEE_MODEL.getIdEmployee());
				METTING_MODEL.setUserId(Principal.getIduser());
				METTING_MODEL.setCompletedWork(0);
				METTING_MODEL.setHaircutId(HAIR_CUT_MODEL.getId());

				ListModel<String> jListSelectedServices = rMeeting.getSelectService().getModel(); // the jList of
																									// services that
																									// selected
				ArrayList<String> arrayServiceSelected = new ArrayList<String>();// add services to the array list
				for (int i = 0; i < jListSelectedServices.getSize(); i++) {
					arrayServiceSelected.add(jListSelectedServices.getElementAt(i));
				}

				METTING_MODEL.setMeetserv(arrayServiceSelected);

				boolean register = false;
				try {
					register = METTING_MODEL.updateMeeting(HAIR_CUT_MODEL.getDuration());
				} catch (SQLException ex) {
					Logger.getLogger(ControllerMeeting.class.getName()).log(Level.SEVERE, null, ex);
				}
				if (register) {
					String message = "Succesfully update the appointment";
					rMeeting.getComment().setText(message);
					update = false;
				} else {
					String message2 = "Sorry there was an error while registering.";
					rMeeting.getComment().setText(message2);
				}

			}

		}
	}

	private void delete() {
		int opcdelete = JOptionPane.showConfirmDialog(principal, "Do you really want to cancel this appointment",
				"Delete Meeting", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcdelete < 1) {
			boolean result = METTING_MODEL.delete();
			if (result) {
				timeOfService = 0;
				JOptionPane.showMessageDialog(principal, "You have successfully deleted the appointment!");
				busyHours = null;
				startTimeQueue = null;
				endTimeQueue = null;
				rMeeting.dispose();
				rMeeting.setVisible(false);
				listVMeeting = new Vmeeting(principal, true);
				listVMeeting.setControllerMeeting(this);
				Tolist();

				listVMeeting.setVisible(true);
			}
		}
	}

	private void priceTotal() {
		ArrayList<Double> priceService = MODEL_SERVICES.getPrices();
		double sumPrice = 0;
		double priceHaircut = HAIR_CUT_MODEL.getPrice();
		if (priceService == null) {
			priceTotal = priceHaircut;

		} else {

			Object[] priceSer = priceService.toArray();// Add a price of service as object into the ArrayList
			for (int i = 0; i < priceSer.length; i++) {

				sumPrice = sumPrice + (Double) priceSer[i];// summary the price of all services in the meeting
			}
			this.priceTotal = sumPrice + priceHaircut;// total price of haircut and services
		}
	}

	// Imports the list of hairCuts in the barberQ
	private void TolistHaircut() {
		String[][] information = HAIR_CUT_MODEL.hairCutList();
		vHairCut.getTableHairCut().setModel(
				new javax.swing.table.DefaultTableModel(information, new String[] { "Style", "Price", "Gender","duration" }) {
					boolean[] canEdit = new boolean[] { false, false, false,false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});

		vHairCut.getTableHairCut().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

	}

	// returns the list of employees who work on that day
	private void TolistEmployee(String nameDay) {
//		System.out.println("Controller.ControllerMeeting.TolistEmployee()");
		String[][] information = EMPLOYEE_MODEL.resultListByNameDay(nameDay);
		vEmployee.getEmployeeTable().setModel(
				new javax.swing.table.DefaultTableModel(information, new String[] { "Phone", "Name", "Last Name","entry time","departure time" }) {
					boolean[] canEdit = new boolean[] { false, false, false,false,false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		vEmployee.getEmployeeTable().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

	private void record() {

		if (METTING_MODEL.getIdMeetint() < 1) {
			// Check the integrity of entering the queue information
			boolean result = validateRegisterNew();
			if (result) {
				Date date = rMeeting.getChooseDateMetting().getDate();

				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

				String format = dateFormat2.format(date);
				METTING_MODEL.setDate(format);
				METTING_MODEL.setHour(hourSelected);
				METTING_MODEL.setUserId(Principal.getIdUser());
				METTING_MODEL.setCompletedWork(0);
				METTING_MODEL.setHaircutId(HAIR_CUT_MODEL.getId());
				METTING_MODEL.setClientId(CLIENT_MODEL.getId());
				METTING_MODEL.setEmployeeId(EMPLOYEE_MODEL.getIdEmployee());
				ListModel<String> jListSelectedServices = rMeeting.getSelectService().getModel();// the jList of
																									// services that
																									// selected
				ArrayList<String> arrayServiceSelected = new ArrayList<String>();// add services to the array list
				for (int i = 0; i < jListSelectedServices.getSize(); i++) {
					arrayServiceSelected.add(jListSelectedServices.getElementAt(i));// capture value in index indicate,
																					// return string.
				}

				METTING_MODEL.setMeetserv(arrayServiceSelected);
				boolean flag = METTING_MODEL.verify();
				if (flag) {
					String comment = "This customer already has an appointmennt,delete the previous one that has not been processed to register a new one";

					rMeeting.getComment().setText(comment);
				} else {

					boolean resultt = METTING_MODEL.insertMeeting(HAIR_CUT_MODEL.getDuration());
					if (resultt) {
						String comment = "The appointment has been successfully registered";
						rMeeting.close();
						rMeeting.setVisible(false);

						listVMeeting = new Vmeeting(principal, true);
						listVMeeting.setControllerMeeting(this);
						Tolist();
						listVMeeting.getCommend().setText(comment);
						listVMeeting.setVisible(true);

					} else {
						String comment = "The appointment can't be register";
						rMeeting.getComment().setText(comment);
					}
				}

			}
			// The queue approval process
		} else if (METTING_MODEL.getIdMeetint() > 0) {
			String comment = "Do you want to confirm the process?";
			int opc = JOptionPane.showConfirmDialog(principal, comment, "Confirmation", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (opc == 0) {
				ListModel<String> modelSelect = (ListModel<String>) rMeeting.getSelectService().getModel();// the jList
																						// selected
				try {
					MODEL_SERVICES.addPriceServiceToArray(modelSelect);// Add prices of services into a list
				} catch (SQLException ex) {
					Logger.getLogger(ControllerMeeting.class.getName()).log(Level.SEVERE, null, ex);
				}
				this.priceTotal();// sum of all prices(service+hair cut)
				this.update();
				completedMeeting.setControllerMeeting(this);
				this.TolistCompletedServices();
				this.TolistCompletedHaircut();
				completedMeeting.getInvoice().setText(String.valueOf(METTING_MODEL.getIdMeetint()));
				completedMeeting.getClient().setText(CLIENT_MODEL.getName() + " " + CLIENT_MODEL.getLastName());
				this.priceTotal();
				completedMeeting.getLtotal().setText(String.valueOf(priceTotal));

				completedMeeting.getLdate().setText(METTING_MODEL.getDate() + " " + METTING_MODEL.getHour());
				completedMeeting.setVisible(true);
			}
		}

	}

	// Check queue integrity
	private boolean validateRegisterNew() {
		boolean validate = false;

		if (rMeeting.getPhoneClient().getText().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "You must select a client", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String comment = "You must select a client";
			rMeeting.getComment().setText(comment);
		} else if (rMeeting.getEmployeeSupport().getText().isEmpty()) {
			JOptionPane.showMessageDialog(principal, "You must select an employee", "information",
					JOptionPane.INFORMATION_MESSAGE);
			String comment = "You must select an employee";
			rMeeting.getComment().setText(comment);

		} else if (rMeeting.getHaircut().getText().isEmpty() && rMeeting.getSelectService().getModel().getSize() < 1) {
			JOptionPane.showMessageDialog(principal,
					"Select a type of cut or select a service, \n" + " otherwise it will not be processed",
					"information", JOptionPane.INFORMATION_MESSAGE);
			String comment = "Select a type of cut or select a service, otherwise it will not be processed";
			rMeeting.getComment().setText(comment);
		} else {

			validate = true;
		}
		return validate;
	}

	// View all clients details when you click on client Search in the Queue listing
	private void TolistClient() {
		String[][] information = CLIENT_MODEL.resultList();
		vPerson.getTablePerson().setModel(
				new javax.swing.table.DefaultTableModel(information, new String[] { "Phone", "Name", "Last Name" }) {
					boolean[] canEdit = new boolean[] { false, false, false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		vPerson.getTablePerson().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		vPerson.setTitle("client");
		vPerson.header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/img/client.png")));
	}

	// Displays the names of the services performed for the queue
	private void TolistCompletedServices() {

		String[][] information = MODEL_SERVICES.listServicesInCompletedMeeting(METTING_MODEL.getIdMeetint());
		completedMeeting.getServiceTable()
				.setModel(new javax.swing.table.DefaultTableModel(information, new String[] { "Service", "Price" }) {
					boolean[] canEdit = new boolean[] { false, false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		completedMeeting.getServiceTable().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

	// Display the name of the hair cut performed for the queue
	private void TolistCompletedHaircut() {
		String[][] information = HAIR_CUT_MODEL.listHairCut(METTING_MODEL.getIdMeetint());
		completedMeeting.getHairCutTable()
				.setModel(new javax.swing.table.DefaultTableModel(information, new String[] { "Style", "Price" }) {
					boolean[] canEdit = new boolean[] { false, false };

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		completedMeeting.getHairCutTable().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

	// open us an option to move services from the list of all services to the list
	// of the wanted services by the client
	private void moveSelection(JList allListServices, JList selectedList, boolean condition) {
		dMAllList = (DefaultListModel) allListServices.getModel();
		selectedListt = (DefaultListModel) selectedList.getModel();

		int[] index = allListServices.getSelectedIndices();

		List select = allListServices.getSelectedValuesList();
		byte i = 0;

		for (Object selecc : select) {
			selectedListt.addElement(selecc);
			SERVICES_SELECTED.add((String) selecc);

			long dur = MODEL_SERVICES.capturDuration(SERVICES_SELECTED);
			SERVICES_SELECTED.clear();
			if (condition) {
				timeOfService = timeOfService + dur;
			} else {
				timeOfService = timeOfService - dur;
			}

			i++;
		}

		if (!update) {

			verifyduration(selectedListt);
		}
		if (index.length > 0) {
			dMAllList.removeRange(index[0], index[i - 1]);
		} else {

		}

		int counter = 0;
		ListModel cont = rMeeting.getSelectService().getModel();
		counter = cont.getSize();
		if (counter != 0) {

			Color micolor = new Color(247, 0, 24, 80);
			rMeeting.getBtnAdd().setBackground(micolor);
			rMeeting.getBtnAdd().setForeground(Color.white);
			rMeeting.getBtnAdd().setText(counter + "Selected services");
		} else {
			rMeeting.getBtnAdd().setText("Assign services");
			rMeeting.getBtnAdd().setForeground(Color.black);
			rMeeting.getBtnAdd().setBackground(Color.white);

		}

	}

        //check duration with service and haircut
	private void verifyduration(DefaultListModel model) {

		if (!busyHours.isEmpty()) {
//			System.out.println("Duracion: " + new Time(this.hourSelected.getTime() + timeOfService));
//			System.out.println(busyHours.get(0));
			Time duration = new Time(this.hourSelected.getTime() + timeOfService);
                           System.out.println("duration with service" + duration);
                                       
			if (busyHours.size() > 0) {

				for (int i = 0; i < startTimeQueue.size(); i++) {
					if (duration.after((Time) startTimeQueue.get(i)) && duration.before((Time) endTimeQueue.get(i))
							|| duration.equals((Time) endTimeQueue.get(i)) || duration.after((Time) endTimeQueue.get(i))
									&& hourSelected.before((Time) endTimeQueue.get(i))) {
						JOptionPane.showMessageDialog(principal,
								"Please change the time, since the limit exceeds the free time");
						timeOfService -= HAIR_CUT_MODEL.getDuration();
						rMeeting.getHaircut().setText("");
						this.reloadEmployee();
						this.SERVICES_SELECTED.clear();
		
						model.clear();
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if (obj.equals(listVMeeting.gettableMeeting())) {
			if (me.getClickCount() == 2) {
				try {
					int row = listVMeeting.gettableMeeting().getSelectedRow();
					int row1 = listVMeeting.gettableMeeting().convertRowIndexToModel(row);
					DefaultTableModel dtm = (DefaultTableModel) listVMeeting.gettableMeeting().getModel();
					Long phoneClient = Long.parseLong((String) dtm.getValueAt(row1, 0));
					captureData(phoneClient, 1);
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
				}

			}
		} else if (obj.equals(vPerson.getTablePerson())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vPerson.getTablePerson().getSelectedRow();
					int row1 = vPerson.getTablePerson().convertRowIndexToModel(row);
					DefaultTableModel dtm = (DefaultTableModel) vPerson.getTablePerson().getModel();
					Long phone = Long.parseLong((String) dtm.getValueAt(row1, 0));
					captureData(phone, 2);
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
				}
			}
		} else if (obj.equals(vEmployee.getEmployeeTable())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vEmployee.getEmployeeTable().getSelectedRow();
					int row1 = vEmployee.getEmployeeTable().convertRowIndexToModel(row);
					DefaultTableModel dtm = (DefaultTableModel) vEmployee.getEmployeeTable().getModel();
					long phone = Long.parseLong((String) dtm.getValueAt(row1, 0));
					timeOfService = 0;
					try {
						selectedListt.clear();
						rMeeting.getHaircut().setText("");
					} catch (Exception e) {
					}

					captureData(phone, 3);
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
				}
			}
		} else if (obj.equals(vHairCut.getTableHairCut())) {
			if (me.getClickCount() == 2) {
				try {
					int row = vHairCut.getTableHairCut().getSelectedRow();
					int row1 = vHairCut.getTableHairCut().convertRowIndexToModel(row);
					DefaultTableModel dtm = (DefaultTableModel) vHairCut.getTableHairCut().getModel();
					String styleName = (String) dtm.getValueAt(row1, 0);
					captureHairCutData(styleName);
				} catch (HeadlessException ex) {
					System.out.println("Error: " + ex);
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

	@Override
	// Limit the search bar to 50 characters
	public void keyTyped(KeyEvent ke) {
		Object obj = ke.getSource();
		if (obj.equals(listVMeeting.getTextSearch())) {
			char b = ke.getKeyChar();
			if (listVMeeting.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		} else if (obj.equals(vPerson.getTextSearch())) {
			char b = ke.getKeyChar();
			if (vPerson.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		} else if (obj.equals(vEmployee.getTextSearch())) {
			char b = ke.getKeyChar();
			if (vEmployee.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		} else if (obj.equals(vHairCut.getTextSearch())) {
			char b = ke.getKeyChar();
			if (vHairCut.getTextSearch().getText().length() > 50) {
				ke.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
	}

	// A function for the desired search, sort the details of the search by what the
	// user typed

    /**
     *
     * @param text
     * @param jTableSearch
     */
	public void SearchList(String text, JTable jTableSearch) {

		dm = (DefaultTableModel) jTableSearch.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
		jTableSearch.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(text));
	}

	@Override

	// Search for the queue, the employee, the client and the type of haircut
	public void keyReleased(KeyEvent ke) {
		Object obj = ke.getSource();
		if (obj.equals(listVMeeting.getTextSearch())) {
			String search = listVMeeting.getTextSearch().getText();
			SearchList(search, listVMeeting.gettableMeeting());
		} else if (obj.equals(vPerson.getTextSearch())) {
			String search = vPerson.getTextSearch().getText();
			SearchList(search, vPerson.getTablePerson());
		} else if (obj.equals(vEmployee.getTextSearch())) {
			String search = vEmployee.getTextSearch().getText();
			SearchList(search, vEmployee.getEmployeeTable());
		} else if (obj.equals(vHairCut.getTextSearch())) {
			String search = vHairCut.getTextSearch().getText();
			SearchList(search, vHairCut.getTableHairCut());
		}
	}

	@Override
	// Discount calculation
	public void stateChanged(ChangeEvent ce) {
		Object obj = ce.getSource();//capture Object of event
                
		if (obj.equals(completedMeeting.getDiscount())) {
                    //value slider = 0 to 100 
			completedMeeting.getLdiscount().setText(completedMeeting.getDiscount().getValue() + " %"); //concatenated the value is %
			int discount = completedMeeting.getDiscount().getValue();
			System.out.println(discount);
			double priceOfDiscount = (priceTotal * discount) / 100;
			double newPrice = priceTotal - priceOfDiscount;

			completedMeeting.getLtotal().setText(String.valueOf(newPrice));
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {

	}

}
