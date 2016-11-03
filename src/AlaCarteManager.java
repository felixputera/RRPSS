import java.util.ArrayList;
import java.util.List;

public class AlaCarteManager {
    private List<AlaCarteMenu> menu;
    private String FName = "./menu.dat";

    public AlaCarteManager() {
        this.menu = (ArrayList) IOHandler.readSerializedObject(FName);
    }

    public int findIndex(int id) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getId() == id) return i;
        }
        System.out.println("ID not found");
        return -1;
    }

    public void addAlaCarte(String name, float price, String type) {
        int id;
        if (menu.isEmpty()) id = 1;
        else {
            id = menu.get(menu.size() - 1).getId() + 1;
        }
        AlaCarteMenu m = new AlaCarteMenu(id, name, price, type);
        this.menu.add(m);
        System.out.println("Added entry to menu");
        IOHandler.writeSerializedObject(FName, menu);
    }

    public void removeAlaCarte(int id) {
        int i = findIndex(id);
        if (i != -1) {
            menu.remove(i);
            System.out.println("Entry deleted");
            IOHandler.writeSerializedObject(FName, menu);
        }
    }

    public void updatePrice(int id, float price) {
        int i = findIndex(id);
        if (i != -1) {
            menu.get(i).setPrice(price);
            System.out.println("Price updated");
            IOHandler.writeSerializedObject(FName, menu);
        }
    }

    public void updateName(int id, String name) {
        int i = findIndex(id);
        if (i != -1) {
            menu.get(i).setName(name);
            System.out.println("Name updated");
            IOHandler.writeSerializedObject(FName, menu);
        }
    }

    public void updateType(int id, String type) {
        int i = findIndex(id);
        if (i != -1) {
            menu.get(i).setType(type);
            System.out.println("Type updated");
            IOHandler.writeSerializedObject(FName, menu);
        }
    }

    public void updateDesc(int id, String desc) {
        int i = findIndex(id);
        if (i != -1) {
            menu.get(i).setDesc(desc);
            System.out.println("Description updated");
            IOHandler.writeSerializedObject(FName, menu);
        }
    }

    public void printAllMenu() {
        for (int i = 0; i < menu.size(); i++) {
            AlaCarteMenu x = menu.get(i);
            System.out.println("ID: " + x.getId());
            System.out.println("Name: " + x.getName());
            System.out.println("Price: $" + x.getPrice());
            System.out.println("Type: " + x.getType());
            System.out.println();
        }
    }

    //print format might need to be changed
    public void printSpecificMenu(int id) {
        int i = findIndex(id);
        AlaCarteMenu x = menu.get(i);
        System.out.println("ID: " + x.getId());
        System.out.println("Name: " + x.getName());
        System.out.println("Price: $" + x.getPrice());
        System.out.println("Type: " + x.getType());
    }

    public AlaCarteMenu getAlaCarteById(int id) {
        for (AlaCarteMenu m : menu) {
            if (m.getId() == id) return m;
        }
        System.out.println("AlaCarte menu ID not found");
        return null;
    }
}
