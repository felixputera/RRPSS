import java.io.Serializable;
import java.util.Calendar;

public class Table implements Serializable {
    private int tableID;
    private int size;
    private int status[][][] = new int[12][31][2];

    public Table(int id, int size) {
        this.tableID = id;
        this.size = size;
        for (int i = 0; i < 12; i++)
        	for (int j = 0; i < 31; j++)
        		for (int k = 0; k < 2; k++)
        			this.status[i][j][k]=0;
    }

    public int getTableID() {
        return this.tableID;
    }

    public void setTableID(int id) {
        this.tableID = id;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStatus(Calendar dateTime) {
    	int month = dateTime.get(Calendar.MONTH);
    	int day = dateTime.get(Calendar.DAY_OF_MONTH);
    	if (dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14)
    		return this.status[month][day-1][0];
    	else if (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21)
    		return this.status[month][day-1][1];
    	else
    		return -1;
    }

    public void setStatus(Calendar dateTime, int status) {
    	int month = dateTime.get(Calendar.MONTH);
    	int day = dateTime.get(Calendar.DAY_OF_MONTH);
    	if (dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14)
    		this.status[month][day-1][0]=status;
    	else if (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21)
    		this.status[month][day-1][1]=status;
    }
}