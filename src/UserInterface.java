import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {
    static AlaCarteManager manage = new AlaCarteManager();
    static PPackageManager pmanage = new PPackageManager();
    static Scanner sc = new Scanner(System.in);
    static TableManager tm = new TableManager();
    static Calendar c = Calendar.getInstance();
    static ReservationManager rm = new ReservationManager();
    static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    //currently main is just used to test classes, remove if necessary
    public static void main(String[] args) {
/*
        System.out.println("Enter the Date ");

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
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

        manage.printAllMenu();

        pmanage.addPackage(19);
        pmanage.addItemToPackage(3, 2);
        pmanage.printAllPackage();
*/
        int choice;

        while(true) {
            System.out.println("1. Create/Update/Remove menu item\n" +
                    "2. Create/Update/Remove promotion\n" +
                    "3. Create order\n" +
                    "4. View order\n" +
                    "5. Add/Remove order item/s to/from order\n" +
                    "6. Create reservation booking\n" +
                    "7. Check/Remove reservation booking\n" +
                    "8. Check table availability\n" +
                    "9. Print order invoice\n" +
                    "10. Print sale revenue report by period (eg day or month)");

            System.out.print("Input: ");

            choice = sc.nextInt();

            switch (choice){
                case 1: modifyMenu();
                    break;

                case 2: modifyPackage();
                    break;

                case 3: createOrder();
                    break;

                case 4: viewOrder();
                    break;

                case 5: modifyOrder();
                    break;

                case 6: createReservation();
                    break;

                case 7: modifyReservation();
                    break;

                case 8: checkAvailability();
                    break;

                case 9: printOrderInvoice();
                    break;

                case 10: printSaleReport();
                    break;

                default: System.out.println("Please choose another option\n");
                    break;

            }
        }
    }

    public static void modifyMenu(){
        System.out.print("1. New menu; 2. Update menu; 3.Remove menu");
        int choice = sc.nextInt();

        String name;
        float price;
        String type;
        int id;
        String desc;

        switch (choice) {
            case 1:
                System.out.print("Menu name to add: ");
                name = sc.nextLine();

                System.out.print("Input price: ");
                price = sc.nextFloat();

                System.out.print("Input menu type: ");
                type = sc.nextLine();

                manage.addAlaCarte(name, price, type);
                break;

            case 2:
                System.out.print("1. Update price; 2. Update name; 3. Update type; 4. Update desc");
                choice = sc.nextInt();

                switch (choice){
                    case 1:
                        System.out.print("Menu ID to update: ");
                        id = sc.nextInt();

                        System.out.print("New price: ");
                        price = sc.nextFloat();

                        manage.updatePrice(id, price);
                        break;

                    case 2:
                        System.out.print("Menu ID to update: ");
                        id = sc.nextInt();

                        System.out.print("New name: ");
                        name = sc.nextLine();

                        manage.updateName(id, name);
                        break;
                    case 3:
                        System.out.print("Menu ID to update: ");
                        id = sc.nextInt();

                        System.out.print("New type: ");
                        type = sc.nextLine();

                        manage.updateType(id, type);
                        break;
                    case 4:
                        System.out.print("Menu ID to update: ");
                        id = sc.nextInt();

                        System.out.print("New description: ");
                        desc = sc.nextLine();

                        manage.updateDesc(id, desc);
                        break;
                }
                break;

            case 3:
                System.out.print("Menu ID to remove: ");
                id = sc.nextInt();

                manage.removeAlaCarte(id);
        }
    }

    public static void modifyPackage(){
        System.out.print("1. New promotion; 2. Update promotion; 3.Remove promotion");
        int choice = sc.nextInt();

        int pid;
        int mid;
        float price;

        switch (choice) {
            case 1:
                System.out.print("Price of new package: ");
                price = sc.nextFloat();

                pmanage.addPackage(price);
                break;

            case 2:
                System.out.print("1. Add item to a package; 2. Update package price; 3. Remove item from a package");
                choice = sc.nextInt();

                switch (choice){
                    case 1: System.out.print("Package ID: ");
                        pid = sc.nextInt();

                        System.out.print("Menu ID to add to package: ");
                        mid = sc.nextInt();

                        pmanage.addItemToPackage(pid, mid);
                        break;

                    case 2: System.out.print("Package ID: ");
                        pid = sc.nextInt();

                        System.out.print("New package price: ");
                        price = sc.nextFloat();

                        pmanage.updatePrice(pid, price);
                        break;

                    case 3: System.out.print("Package ID: ");
                        pid = sc.nextInt();

                        System.out.print("Menu ID to remove from package");
                        mid = sc.nextInt();

                        pmanage.removeItemFromPackage(pid, mid);
                }
                break;

            case 3:
                System.out.print("Package ID to remove: ");
                pid = sc.nextInt();

                pmanage.removePackage(pid);
        }
    }

    public static void createOrder(){
        System.out.println("");
    }

    public static void viewOrder(){
        System.out.println("");
    }

    public static void modifyOrder(){
        System.out.println("");
    }

    public static void createReservation(){
        System.out.print("Date & Time to reserve (MM-dd-yyyy HH:mm): ");
        String dateString = sc.nextLine();
        Date dateTime = null;

        try {
            dateTime = sdf.parse(dateString);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        c.setTime(dateTime);

        System.out.print("Number of people: ");
        int pax = sc.nextInt();

        System.out.print("Under the name of: ");
        String name = sc.nextLine();

        System.out.print("Contact number: ");
        int contactNo = sc.nextInt();

        rm.createReservation(c, pax, name, contactNo);
        rm.refresh();
    }

    public static void modifyReservation(){
        System.out.print("1. Check specific reservation; 2. Check all reservation; 3. Remove reservation");
        int choice = sc.nextInt();

        int contactNo;

        switch(choice){
            case 1: System.out.print("Check reservation with contact number: ");
                contactNo = sc.nextInt();

                rm.refresh();
                rm.checkSpecificReservation(contactNo);
                break;

            case 2: rm.refresh();
                rm.checkAllReservation();
                break;

            case 3: System.out.print("Remove reservation with contact number: ");
                contactNo = sc.nextInt();

                rm.removeReservation(contactNo);
                rm.refresh();
                break;
        }
    }

    public static void checkAvailability(){
        System.out.print("1. Check by ID; 2. Check by size; 3. Check all tables");
        int choice = sc.nextInt();

        int tid;
        int size;
        Date dateTime = null;
        String dateString;
        Calendar now = Calendar.getInstance();

        switch (choice){
            case 1: System.out.print("Table ID: ");
                tid = sc.nextInt();

                System.out.print("1. Insert Date & Time ; 2. Use current time");
                choice = sc.nextInt();

                switch(choice){
                    case 1: System.out.print("Date & Time (MM-dd-yyyy HH:mm): ");
                        dateString = sc.nextLine();

                        try {
                            dateTime = sdf.parse(dateString);
                        }
                        catch (ParseException e){
                            e.printStackTrace();
                        }

                        c.setTime(dateTime);

                        tm.checkAvailabilityByID(tid, c);
                        break;

                    case 2: tm.checkAvailabilityByID(tid, now);
                        break;
                }

                break;

            case 2: System.out.print("Table size: ");
                size = sc.nextInt();

                System.out.print("1. Insert Date & Time ; 2. Use current time");
                choice = sc.nextInt();

                switch(choice){
                    case 1: System.out.print("Date & Time (MM-dd-yyyy HH:mm): ");
                        dateString = sc.nextLine();

                        try {
                            dateTime = sdf.parse(dateString);
                        }
                        catch (ParseException e){
                            e.printStackTrace();
                        }

                        c.setTime(dateTime);

                        tm.checkAvailabilityBySize(size, c);
                        break;

                    case 2: tm.checkAvailabilityBySize(size, now);
                        break;
                }

                break;

            case 3: System.out.print("1. Insert Date & Time ; 2. Use current time");
                choice = sc.nextInt();

                switch(choice){
                    case 1: System.out.print("Date & Time (MM-dd-yyyy HH:mm): ");
                        dateString = sc.nextLine();

                        try {
                            dateTime = sdf.parse(dateString);
                        }
                        catch (ParseException e){
                            e.printStackTrace();
                        }

                        c.setTime(dateTime);

                        tm.checkAllTableAvailability(c);
                        break;

                    case 2: tm.checkAllTableAvailability(now);
                    break;
                }

                break;
        }
    }

    public static void printOrderInvoice(){

    }

    public static void printSaleReport(){

    }
}