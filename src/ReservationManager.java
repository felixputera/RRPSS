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
        int emptyID = tableManage.findEmptyTable(pax);
    	if (emptyID != 1 && ) {
    		Reservation r = new Reservation(dateTime, pax, name, contactNo, emptyID);
            this.reserveList.add(r);
            System.out.println("New reservation created");
            IOHandler.writeSerializedObject(FName, reserveList);
    	}
    	else {
    		System.out.println("Reservation can not be made");
    	}
    }

    public void removeReservation(int id) {
        int i = findIndex(id);
        if (i != -1) {
            packageList.remove(i);
            System.out.println("Promotional package removed");
            IOHandler.writeSerializedObject(FName, packageList);
        }
    }

    public void updatePrice(int id, float price) {
        int i = findIndex(id);
        if (i != -1) {
            packageList.get(i).setPrice(price);
            System.out.println("Promotional package price updated");
            IOHandler.writeSerializedObject(FName, packageList);
        }
    }

    public void addItemToPackage(int packageId, int alaCarteId) {
        int pIndex = findIndex(packageId);
        int mIndex = acManage.findIndex(alaCarteId);
        if (mIndex != -1 && pIndex != -1) {
            if (!packageList.get(pIndex).getMenuIdList().contains(alaCarteId)) {
                packageList.get(pIndex).addMenuIdList(alaCarteId);
                System.out.println("Menu item added to the promotional package");
                IOHandler.writeSerializedObject(FName, packageList);
            } else System.out.println("Menu item has been added to promotional package before");
        }
    }

    public void removeItemFromPackage(int packageId, int alaCarteId) {
        int pIndex = findIndex(packageId);
        int mIndex = acManage.findIndex(alaCarteId);
        if (mIndex != -1 && pIndex != -1) {
            if (packageList.get(pIndex).getMenuIdList().contains(alaCarteId)) {
                packageList.get(pIndex).removeMenuIdList(alaCarteId);
                System.out.println("Menu item deleted from the promotional package");
                IOHandler.writeSerializedObject(FName, packageList);
            } else System.out.println("Menu item has not been added to promotional package before");
        }
    }

    public void printAllPackage() {
        for (int i = 0; i < packageList.size(); i++) {
            PromoPackage x = packageList.get(i);
            System.out.println("ID: " + x.getId());
            System.out.println("Price: $" + x.getPrice());
            System.out.println("Contains:");
            if (!x.getMenuIdList().isEmpty()) {
                for (int j = 0; j < x.getMenuIdList().size(); j++) {
                    System.out.println("-" + acManage.getAlaCarteById(x.getMenuIdList().get(j)).getName());
                }
            }
            System.out.println();
        }
    }

    //print format might need to be changed
    public void printSpecificPackage(int id) {
        int i = findIndex(id);
        PromoPackage x = packageList.get(i);
        System.out.println("ID: " + x.getId());
        System.out.println("Price: $" + x.getPrice());
        System.out.println("Contains:");
        for (int j = 0; j < x.getMenuIdList().size(); j++) {
            System.out.println("-" + acManage.getAlaCarteById(x.getMenuIdList().get(j)).getName());
        }
        System.out.println();
    }
}
