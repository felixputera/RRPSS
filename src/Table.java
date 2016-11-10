import java.io.Serializable;
import java.util.Calendar;

public class Table implements Serializable {
    private int tableID;
    private int size;
    private int[][][] status = new int[12][31][2];

    public Table(int tableID, int size) {
        this.tableID = tableID;
        this.size = size;
    }

    public int getTableID() {
        return this.tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStatus(Calendar dateTime, int shift) {
        int month = dateTime.get(Calendar.MONTH);
        int day = dateTime.get(Calendar.DAY_OF_MONTH);
        if (shift != -1) {
            return this.status[month][day - 1][shift];
        }
        return -1;
    }

    public void setStatus(Calendar dateTime, int status, int shift) {
        int month = dateTime.get(Calendar.MONTH);
        int day = dateTime.get(Calendar.DAY_OF_MONTH);
        if (shift != -1) {
            this.status[month][day - 1][shift] = status;
        }
    }
}