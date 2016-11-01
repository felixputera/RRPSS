import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.
public class IOHandler {
    public static List readSerializedObject(String filename) {
        List pDetails = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        // print out the size
        //System.out.println(" Details Size: " + pDetails.size());
        //System.out.println();
        return pDetails;
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
            // read from serialized file the list of professors
            list = (ArrayList) IOHandler.readSerializedObject("professor.dat");
            for (int i = 0; i < list.size(); i++) {
                //Professor p = (Professor)list.get(i);
                //System.out.println("name is " + p.getName() );
                //System.out.println("contact is " + p.getContact() );
            }

            // write to serialized file - update/insert/delete
            // example - add one more professor
            //Professor p = new Professor("Joseph","jos@ntu.edu.sg",67909999);
            // add to list
            //list.add(p);
            // list.remove(p);  // remove if p equals object in the list

            IOHandler.writeSerializedObject("professor.dat", list);

        } catch (Exception e) {
            System.out.println("Exception >> " + e.getMessage());
        }
    }
}