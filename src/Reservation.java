import java.io.Serializable;
import java.util.Calendar;

public class Reservation implements Serializable {
    private Calendar dateTime;
    private int pax;
    private String bookingName;
    private int contactNumber;
    private int tableID;

    public Reservation(Calendar dateTime, int pax, String name, int contactNo, int tableID) {
        this.dateTime = dateTime;
        this.pax = pax;
        this.bookingName = name;
        this.contactNumber = contactNo;
        this.tableID = tableID;
    }

    public Calendar getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public int getPax() {
        return this.pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public String getBookingName() {
        return this.bookingName;
    }

    public void setBookingName(String name) {
        this.bookingName = name;
    }

    public int getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(int contactNo) {
        this.contactNumber = contactNo;
    }

    public int getTableID() {
        return this.tableID;
    }

    public void setTable(int tableID) {
        this.tableID = tableID;
    }
}
