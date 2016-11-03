import java.io.*;
import java.util.ArrayList;
import java.util.List;

// this is just an example for the alacarteMenu
// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.
public class IOHandler {
    public static List readSerializedObject(String filename) {
        List mDetails = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            mDetails = (ArrayList) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // print out the size
        //System.out.println(" Details Size: " + mDetails.size());
        //System.out.println();
        return mDetails;
    }

    public static void writeSerializedObject(String filename, List list) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            //	System.out.println("Object Persisted");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List list;
        try {
            list = new ArrayList<AlaCarteMenu>();

            // write to serialized file - update/insert/delete
            // example - add one menu
            AlaCarteMenu m = new AlaCarteMenu(1, "Chocolate fudge", 10, "Dessert");
            // add to list
            list.add(m);
            // list.remove(p);  // remove if p equals object in the list

            IOHandler.writeSerializedObject("./menu.dat", list);

            list = (ArrayList) IOHandler.readSerializedObject("./menu.dat");
            // read from serialized file the list of professors
            for (int i = 0; i < list.size(); i++) {
                AlaCarteMenu x = (AlaCarteMenu) list.get(i);
                System.out.println("id is " + x.getId());
                System.out.println("name is " + x.getName());
                System.out.println("price is " + x.getPrice());
                System.out.println("type is " + x.getType());

            }
        } catch (Exception e) {
            System.out.println("Exception >> " + e.getMessage());
        }
    }
}