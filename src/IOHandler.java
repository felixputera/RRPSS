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

    public static void writeStringToTxtFile(String filename, String content){
        String path = "./" + filename + ".txt";
        try{
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            fout.println(content);
            fout.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStringToTxtFile(String filename, String content){
        String path = "./" + filename + ".txt";
        try{
            PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(path)));
            fout.println(content);
            fout.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}