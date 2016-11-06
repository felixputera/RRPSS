import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TableManager {
	private List<Table> tables;
    private String FName = "./tables.dat";

    public TableManager() {
        this.tables = (ArrayList) IOHandler.readSerializedObject(FName);
    }

    public int findIndex(int tableID) {
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getTableID() == tableID) return i;
        }
        System.out.println("Table ID not found");
        return -1;
    }

    public void updateStatus(Calendar dateTime, int id, int status) {
    	if ((dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14) || (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21)) {
    		int i = findIndex(id);
        	if (i != -1) {
	            tables.get(i).setStatus(dateTime, status);
	            System.out.println("Status updated");
	            IOHandler.writeSerializedObject(FName, tables);
	        }
    	}
        else {
        	System.out.println("Restaurant not operating at that time");
        	System.out.println();
        }
    }

    public void checkAvailability(int size, Calendar dateTime) {
        for (int i = 0; i < tables.size(); i++) {
            Table t = tables.get(i);
            if (t.getSize() == size) {
            	System.out.println("Table ID: " + t.getTableID());
            	System.out.println("Status: " + t.getStatus(dateTime));
            	System.out.println();
            }
        }
    }
    
    public int findEmptyTable(int pax) {
    	for (int i = 0; i < tables.size(); i++) {
            Table t = tables.get(i);
            if (t.getSize() >= pax) {
            	return t.getTableID();
            }
        }
    	return -1;
    }
}
