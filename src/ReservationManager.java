import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class ReservationManager {
    private TableManager tableManage = new TableManager();
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

    private boolean noSameContact(int contactNo) {
        for (Reservation r : reserveList) {
            if (r.getContactNumber() == contactNo) {
                return false;
            }
        }
        return true;
    }

    private int getTableIDByContactNumber(int contactNo) {
        for (Reservation r : reserveList) {
            if (r.getContactNumber() == contactNo) return r.getTableID();
        }
        System.out.println("Contact number not found");
        return -1;
    }

    public void createReservation(Calendar dateTime, int pax, String name, int contactNo) {
        tableManage.refresh();
        int emptyID = tableManage.findEmptyTable(pax, dateTime);
        if (emptyID != -1 && ((dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14) ||
                (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21)) &&
                dateTime.getTime().after(Calendar.getInstance().getTime()) && noSameContact(contactNo) &&
                (dateTime.getTime().getTime() / 1000 - Calendar.getInstance().getTime().getTime() / 1000) < 30 * 24 * 60 * 60) {
            Reservation r = new Reservation(dateTime, pax, name, contactNo, emptyID);
            this.reserveList.add(r);
            tableManage.updateStatus(r.getDateTime(), r.getTableID(), 1);
            System.out.println("New reservation created");
            IOHandler.writeSerializedObject(FName, reserveList);
        } else {
            System.out.println("Reservation cannot be made");
        }
    }

    public void removeReservation(int contactNo) {
        tableManage.refresh();
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
            System.out.println("Date/Time: " + r.getDateTime().getTime());
            System.out.println("Pax: " + r.getPax());
            System.out.println("Booking Name: " + r.getBookingName());
            System.out.println("Contact Number: " + r.getContactNumber());
            System.out.println("Table ID: " + r.getTableID());
            System.out.println();
        }
    }

    public void checkSpecificReservation(int contactNo) {
        int i = findIndex(contactNo);
        if (i != -1) {
            Reservation r = reserveList.get(i);
            System.out.println("Date/Time: " + r.getDateTime().getTime());
            System.out.println("Pax: " + r.getPax());
            System.out.println("Booking Name: " + r.getBookingName());
            System.out.println("Contact Number: " + r.getContactNumber());
            System.out.println("Table ID: " + r.getTableID());
            System.out.println();
        }
    }

    public int checkInReservation(int contactNo) {
        Calendar now = Calendar.getInstance();
        int index = findIndex(contactNo);
        Reservation res = reserveList.get(index);
        if (index != -1) {
            if ((res.getDateTime().get(Calendar.YEAR) == now.get(Calendar.YEAR)) &&
                    (res.getDateTime().get(Calendar.MONTH) == now.get(Calendar.MONTH)) &&
                    (res.getDateTime().get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH))) {
                int tableId = getTableIDByContactNumber(contactNo);
                if (tableManage.getShift(res.getDateTime()) == tableManage.getShift(now)) {
                    removeReservation(contactNo);
                    return tableId;
                }
                System.out.println("Reservation was made for other time, come again later");
            }
        }
        return -1;
    }

    public void refresh() {
        checkExpiry();
        this.reserveList = (ArrayList) IOHandler.readSerializedObject(FName);
    }

    public void checkExpiry() {
        Calendar now = Calendar.getInstance();
        Iterator<Reservation> iter = reserveList.iterator();
        List<Reservation> deleteList = new ArrayList<>();

        while (iter.hasNext()) {
            Reservation r = iter.next();

            if ((r.getDateTime().getTime().getTime() / 1000 - now.getTime().getTime() / 1000) < -1800) {
                System.out.println("Reservation w/ contact number " + r.getContactNumber() +
                        " has been canceled (30 mins passed)");
                deleteList.add(r);
            }
        }

        for (Reservation d : deleteList) {
            removeReservation(d.getContactNumber());
        }
    }
}
