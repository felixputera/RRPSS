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
    
    public void createTable() {
        for (int id = 1; id <= 10; id++) {
        	Table t = new Table(id, 2);
            this.tables.add(t);
            System.out.println("Added table " + id + " with 2 seats");
        }
        for (int id = 11; id <= 20; id++) {
        	Table t = new Table(id, 4);
            this.tables.add(t);
            System.out.println("Added table " + id + " with 4 seats");
        }
        for (int id = 21; id <= 25; id++) {
        	Table t = new Table(id, 8);
            this.tables.add(t);
            System.out.println("Added table " + id + " with 8 seats");
        }
        for (int id = 26; id <= 30; id++) {
        	Table t = new Table(id, 10);
            this.tables.add(t);
            System.out.println("Added table " + id + " with 10 seats");
        }
        IOHandler.writeSerializedObject(FName, tables);
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

    public void checkAvailabilityByID(int tableID, Calendar dateTime) {
        if ((dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14) || (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21)) {
        	int i = findIndex(tableID);
        	if (i != -1) {
                Table t = tables.get(i);
                System.out.println("Table ID: " + t.getTableID());
                System.out.print("Status: ");
                statusIntToString(t.getStatus(dateTime));
                System.out.println();
            }
        }
        else {
        	System.out.println("Restaurant not operating at that time");
        	System.out.println();
        }
    }
    
    public void checkAvailabilityBySize(int size, Calendar dateTime) {
        if ((dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14) || (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21)) {
        	for (int i = 0; i < tables.size(); i++) {
                Table t = tables.get(i);
                if (t.getSize() == size) {
                	System.out.println("Table ID: " + t.getTableID());
                	System.out.print("Status: ");
                    statusIntToString(t.getStatus(dateTime));
                	System.out.println();
                }
            }
        }
        else {
        	System.out.println("Restaurant not operating at that time");
        	System.out.println();
        }
    }
    
    public void checkAllTableAvailability(int size, Calendar dateTime) {
        if ((dateTime.get(Calendar.HOUR_OF_DAY) >= 11 && dateTime.get(Calendar.HOUR_OF_DAY) <= 14) || (dateTime.get(Calendar.HOUR_OF_DAY) >= 18 && dateTime.get(Calendar.HOUR_OF_DAY) <= 21)) {
        	for (int i = 0; i < tables.size(); i++) {
                Table t = tables.get(i);
                System.out.println("Table ID: " + t.getTableID());
                System.out.print("Status: ");
                statusIntToString(t.getStatus(dateTime));
                System.out.println();
            }
        }
        else {
        	System.out.println("Restaurant not operating at that time");
        	System.out.println();
        }
    }
    
    public int findEmptyTable(int pax, Calendar dateTime) {
    	for (int i = 0; i < tables.size(); i++) {
            Table t = tables.get(i);
            if (t.getSize() >= pax){
            	if (t.getStatus(dateTime) == 0){
            		return t.getTableID();
            	}
            }
        }
    	return -1;
    }
    
    private void statusIntToString(int status) {
    	switch(status) {
    		case 0:
    			System.out.println("Free");
    			break;
    		case 1:
    			System.out.println("Reserved");
    			break;
    		case 2:
    			System.out.println("Occupied");
    			break;
    		case 3:
    			System.out.println("Vacated");
    			break;
    	}
    }
}
