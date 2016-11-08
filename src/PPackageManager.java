import java.util.ArrayList;
import java.util.List;

public class PPackageManager {
    private AlaCarteManager acManage = new AlaCarteManager();
    private List<PromoPackage> packageList;
    private String FName = "./promo.dat";


    public PPackageManager() {
        this.packageList = (ArrayList) IOHandler.readSerializedObject(FName);
    }

    public int findIndex(int id) {
        for (int i = 0; i < packageList.size(); i++) {
            if (packageList.get(i).getId() == id) return i;
        }
        System.out.println("Package ID not found");
        return -1;
    }

    public void addPackage(float price) {
        int id;
        if (packageList.isEmpty()) id = 1;
        else {
            id = packageList.get(packageList.size() - 1).getId() + 1;
        }
        PromoPackage p = new PromoPackage(id, price);
        this.packageList.add(p);
        System.out.println("Created new promotional package with ID " + id);
        IOHandler.writeSerializedObject(FName, packageList);
    }

    public void removePackage(int id) {
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
        acManage.refresh();
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
        acManage.refresh();
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
            for (int j = 0; j < x.getMenuIdList().size(); j++) {
                System.out.println("-" + acManage.getAlaCarteById(x.getMenuIdList().get(j)).getName());
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

    public PromoPackage getPromoById(int id) {
        for (PromoPackage p : packageList) {
            if (p.getId() == id) return p;
        }
        System.out.println("Promotional Package ID not found");
        return null;
    }

    public void refresh() {
        this.packageList = (ArrayList) IOHandler.readSerializedObject(FName);
    }
}
