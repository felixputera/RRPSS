import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationManager {
	TableManager tableManage = new TableManager();
    private List<Reservation> reserveList;
    private String FName = "./reserve.dat";

    public ReservationManager() {
        this.reserveList = (ArrayList) IOHandler.readSerializedObject(FName);
    }

    private int findIndex(int contactNo) {
        for (int i = 0; i < reserveList.size(); i++) {
            if (reserveList.get(i).getContactNumber() == contactNo) return i;
        }
        System.out.println("Contact number not found");
        return -1;
    }

    public void createReservation(Calendar dateTime, int pax, String name, int contactNo) {
        int emptyID = tableManage.findEmptyTable(pax, dateTime);
    	if (emptyID != 1 && ((dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14) || (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21))) {
    		Reservation r = new Reservation(dateTime, pax, name, contactNo, emptyID);
            this.reserveList.add(r);
            tableManage.updateStatus(r.getDateTime(), r.getTableID(), 1);
            System.out.println("New reservation created");
            IOHandler.writeSerializedObject(FName, reserveList);
    	}
    	else {
    		System.out.println("Reservation can not be made");
    	}
    }

    public void removeReservation(int contactNo) {
        int i = findIndex(contactNo);
        if (i != -1) {
        	Reservation r = reserveList.get(i);
        	tableManage.updateStatus(r.getDateTime(), r.getTableID(), 0);
            reserveList.remove(i);
            System.out.println("Reservation booking removed");
            IOHandler.writeSerializedObject(FName, reserveList);
        }
    }

    public void checkAllReservation() {
        for (int i = 0; i < reserveList.size(); i++) {
            Reservation r = reserveList.get(i);
            System.out.println("Date/Time: " + r.getDateTime());
            System.out.println("Pax: " + r.getPax());
            System.out.println("Booking Name: " + r.getBookingName());
            System.out.println("Contact Number: " + r.getContactNumber());
            System.out.println("Table ID: " + r.getTableID());
            System.out.println();
        }
    }

    public void checkSpecificReservation(int contactNo) {
        int i = findIndex(contactNo);
        Reservation r = reserveList.get(i);
        System.out.println("Date/Time: " + r.getDateTime());
        System.out.println("Pax: " + r.getPax());
        System.out.println("Booking Name: " + r.getBookingName());
        System.out.println("Contact Number: " + r.getContactNumber());
        System.out.println("Table ID: " + r.getTableID());
        System.out.println();
    }
}
