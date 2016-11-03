import java.io.*;
import java.util.ArrayList;
import java.util.List;

// this is just an example for the alacarteMenu
// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.
public class IOHandler {
    public static List readSerializedObject(String filename) {
        List objDetails = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            objDetails = (ArrayList) in.readObject();
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File is not created yet, creating one...");
            File f = new File(filename);
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return (new ArrayList());
        } catch (EOFException ex) {
            System.out.println("File is currently empty");
            return (new ArrayList());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return objDetails;
    }

    public static void writeSerializedObject(String filename, List list) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List list;
        try {
            //list = new ArrayList<AlaCarteMenu>();
            list = (ArrayList) IOHandler.readSerializedObject("./menu.dat");
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