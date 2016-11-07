import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {
    //currently main is just used to test classes, remove if necessary
    public static void main(String[] args) {
        TableManager tm = new TableManager();
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        ReservationManager rm = new ReservationManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Date ");

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        Scanner sc = new Scanner(System.in);
        Date dat = null;
        String s = sc.nextLine();
        try {
            dat = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(dat);
        rm.createReservation(c, 9, "bill", 1234567);
        rm.refresh();
        rm.checkAllReservation();
        System.out.println(c.getTime());
    }
}
