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
    static OrderManager om = new OrderManager();
    static StaffManager sm = new StaffManager();

    //currently main is just used to test classes, remove if necessary
    public static void main(String[] args) {

        int choice = 1; // print all menu first

        while (choice != 10) { // Input 10 to exit
            System.out.println("1. View menu items\n" +
                    "2. View promotions\n" +
                    "3. Create order\n" +
                    "4. View order\n" +
                    "5. Add/Remove order item/s to/from order\n" +
                    "6. Create reservation booking\n" +
                    "7. Check/Remove reservation booking\n" +
                    "8. Check table availability\n" +
                    "9. Print order invoice\n" +
                    "10. Exit\n" +
                    "11. (Admin) Print sale revenue report by period (eg day or month)\n" +
                    "12. (Admin) Create/Update/Remove menu item\n" +
                    "13. (Admin) Create/Update/Remove promotion\n" +
                    "14. (Admin) View all staff\n" +
                    "15. (Admin) Hire staff\n" +
                    "16. (Admin) Fire staff");

            System.out.print("Input: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    manage.printAllMenu();
                    break;

                case 2:
                    pmanage.printAllPackage();
                    break;

                case 3:
                    createOrder();
                    break;

                case 4:
                    viewOrder();
                    break;

                case 5:
                    modifyOrder();
                    break;

                case 6:
                    createReservation();
                    break;

                case 7:
                    modifyReservation();
                    break;

                case 8:
                    checkAvailability();
                    break;

                case 9:
                    printOrderInvoice();
                    break;

                case 10:
                    System.out.print("EXIT APPLICATION ...");

                case 11:
                    printSaleReport();
                    break;

                case 12:
                    modifyMenu();
                    break;

                case 13:
                    modifyPackage();
                    break;

                case 14:
                    viewAllStaff();
                    break;

                case 15:
                    hireStaff();
                    break;

                case 16:
                    fireStaff();
                    break;

                default:
                    System.out.println("Please choose another option\n");
                    break;

            }
        }
    }

    public static void modifyMenu() {
        System.out.println("1. New menu; 2. Update menu; 3.Remove menu");
        System.out.print("Input: ");
        int choice = sc.nextInt();

        String name;
        float price;
        String type;
        int id;
        String desc;

        switch (choice) {
            case 1:
                System.out.print("Menu name to add: ");
                sc.nextLine(); //clear buffer
                name = sc.nextLine();

                System.out.print("Input price: ");
                price = sc.nextFloat();

                System.out.print("Input menu type: ");
                sc.nextLine(); //clear buffer
                type = sc.nextLine();

                manage.addAlaCarte(name, price, type);
                break;

            case 2:
                System.out.println("1. Update price; 2. Update name; 3. Update type; 4. Update desc");
                System.out.print("Input: ");
                choice = sc.nextInt();

                switch (choice) {
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
                        sc.nextLine(); //clear buffer
                        name = sc.nextLine();

                        manage.updateName(id, name);
                        break;
                    case 3:
                        System.out.print("Menu ID to update: ");
                        id = sc.nextInt();

                        System.out.print("New type: ");
                        sc.nextLine(); //clear buffer
                        type = sc.nextLine();

                        manage.updateType(id, type);
                        break;
                    case 4:
                        System.out.print("Menu ID to update: ");
                        id = sc.nextInt();

                        System.out.print("New description: ");
                        sc.nextLine(); //clear buffer
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

    public static void modifyPackage() {
        System.out.println("1. New promotion; 2. Update promotion; 3.Remove promotion");
        System.out.print("Input: ");
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
                System.out.println("1. Add item to a package; 2. Update package price; 3. Remove item from a package");
                System.out.print("Input: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Package ID: ");
                        pid = sc.nextInt();

                        System.out.print("Menu ID to add to package: ");
                        mid = sc.nextInt();

                        pmanage.addItemToPackage(pid, mid);
                        break;

                    case 2:
                        System.out.print("Package ID: ");
                        pid = sc.nextInt();

                        System.out.print("New package price: ");
                        price = sc.nextFloat();

                        pmanage.updatePrice(pid, price);
                        break;

                    case 3:
                        System.out.print("Package ID: ");
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

    public static void createOrder() {
        System.out.println("1. Not reserved yet; 2. Have reserved before");
        System.out.print("Input: ");
        int choice = sc.nextInt();

        int pax;
        int contactNo;
        int staffId;

        switch (choice) {
            case 1:
                System.out.print("Number of people: ");
                pax = sc.nextInt();

                System.out.print("Staff ID: ");
                staffId = sc.nextInt();

                om.createOrderNoReservation(pax, staffId);
                om.refresh();
                break;

            case 2:
                System.out.print("Contact Number: ");
                contactNo = sc.nextInt();

                System.out.print("Staff ID: ");
                staffId = sc.nextInt();

                om.createOrderReservation(contactNo, staffId);
                break;
        }
    }

    public static void viewOrder() {
        System.out.print("View order with ID: ");
        int oid = sc.nextInt();

        om.refresh();
        om.viewOrder(oid);
    }

    public static void modifyOrder() {
        System.out.print("Order ID to modify: ");
        int oid = sc.nextInt();

        System.out.println("1. Add menu item; 2. Add promotion item; 3. Remove menu item; 4. Remove promotion item\n" +
                "5. EXIT modifying order");
        System.out.print("Input: ");
        int choice = sc.nextInt();

        int menuId;
        int pid;

        while (choice != 5) {
            switch (choice) {
                case 1:
                    System.out.print("Menu item ID to add: ");
                    menuId = sc.nextInt();

                    om.addItemToOrderAlaCarte(menuId, oid);
                    om.refresh();
                    break;

                case 2:
                    System.out.print("Menu item ID to remove: ");
                    menuId = sc.nextInt();

                    om.removeItemFromOrderAlaCarte(menuId, oid);
                    om.refresh();
                    break;

                case 3:
                    System.out.print("Package ID to add: ");
                    pid = sc.nextInt();

                    om.addItemToOrderPromo(pid, oid);
                    om.refresh();
                    break;

                case 4:
                    System.out.print("Package ID to remove: ");
                    pid = sc.nextInt();

                    om.removeItemFromOrderPromo(pid, oid);
                    om.refresh();
                    break;

                default:
                    System.out.print("Choose another option.");
                    break;
            }

            System.out.println("1. Add menu item; 2. Add promotion item; 3. Remove menu item; 4. Remove promotion item\n" +
                    "5. EXIT modifying order");
            System.out.print("Input: ");
            choice = sc.nextInt();
        }
    }

    public static void createReservation() {
        System.out.print("Date & Time to reserve (MM-dd-yyyy HH:mm): ");
        sc.nextLine(); //clear buffer
        String dateString = sc.nextLine();
        Date dateTime = null;

        try {
            dateTime = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.setTime(dateTime);

        System.out.print("Number of people: ");
        int pax = sc.nextInt();

        System.out.print("Under the name of: ");
        sc.nextLine(); //clear buffer
        String name = sc.nextLine();

        System.out.print("Contact number: ");
        int contactNo = sc.nextInt();

        rm.createReservation(c, pax, name, contactNo);
        rm.refresh();
    }

    public static void modifyReservation() {
        System.out.println("1. Check specific reservation; 2. Check all reservation; 3. Remove reservation");
        System.out.print("Input: ");
        int choice = sc.nextInt();

        int contactNo;

        switch (choice) {
            case 1:
                System.out.print("Check reservation with contact number: ");
                contactNo = sc.nextInt();

                rm.refresh();
                rm.checkSpecificReservation(contactNo);
                break;

            case 2:
                rm.refresh();
                rm.checkAllReservation();
                break;

            case 3:
                System.out.print("Remove reservation with contact number: ");
                contactNo = sc.nextInt();

                rm.removeReservation(contactNo);
                rm.refresh();
                break;
        }
    }

    public static void checkAvailability() {
        System.out.println("1. Check by ID; 2. Check by size; 3. Check all tables");
        System.out.print("Input: ");
        int choice = sc.nextInt();

        int tid;
        int size;
        Date dateTime = null;
        String dateString;

        switch (choice) {
            case 1:
                System.out.print("Table ID: ");
                tid = sc.nextInt();

                System.out.print("1. Insert Date & Time ; 2. Use current time");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Date & Time (MM-dd-yyyy HH:mm): ");
                        System.out.print("Input: ");
                        sc.nextLine(); //clear buffer
                        dateString = sc.nextLine();

                        try {
                            dateTime = sdf.parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        c.setTime(dateTime);

                        rm.refresh();
                        tm.refresh();
                        tm.checkAvailabilityByID(tid, c);
                        break;

                    case 2:
                        rm.refresh();
                        tm.refresh();
                        tm.checkAvailabilityByID(tid, Calendar.getInstance());
                        break;
                }

                break;

            case 2:
                System.out.print("Table size: ");
                size = sc.nextInt();

                System.out.print("1. Insert Date & Time ; 2. Use current time");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Date & Time (MM-dd-yyyy HH:mm): ");
                        System.out.print("Input: ");
                        sc.nextLine(); //clear buffer
                        dateString = sc.nextLine();

                        try {
                            dateTime = sdf.parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        c.setTime(dateTime);

                        rm.refresh();
                        tm.refresh();
                        tm.checkAvailabilityBySize(size, c);
                        break;

                    case 2:
                        rm.refresh();
                        tm.refresh();
                        tm.checkAvailabilityBySize(size, Calendar.getInstance());
                        break;
                }

                break;

            case 3:
                System.out.println("1. Insert Date & Time ; 2. Use current time");
                System.out.print("Input: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Date & Time (MM-dd-yyyy HH:mm): ");
                        sc.nextLine(); //clear buffer
                        dateString = sc.nextLine();

                        try {
                            dateTime = sdf.parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        c.setTime(dateTime);

                        rm.refresh();
                        tm.refresh();
                        tm.checkAllTableAvailability(c);
                        break;

                    case 2:
                        rm.refresh();
                        tm.refresh();
                        tm.checkAllTableAvailability(Calendar.getInstance());
                        break;
                }

                break;
        }
    }

    public static void printOrderInvoice() {
        System.out.print("Please Enter Order ID: ");
        int oid = sc.nextInt();

        om.refresh();
        om.printInvoice(oid);
    }

    public static void printSaleReport() {
        System.out.println("Print Sales Report in 1. Month; 2. Period");
        System.out.print("Input: ");
        int choice = sc.nextInt();

        int year;
        int month;
        int day;

        switch (choice) {
            case 1:
                System.out.print("Month (0 to 11): ");
                month = sc.nextInt();

                om.refresh();
                om.salesRevenueReport(month);
                break;

            case 2:
                System.out.print("Year Month Day: ");
                year = sc.nextInt();
                month = sc.nextInt();
                day = sc.nextInt();

                om.refresh();
                om.salesRevenueReport(year, month, day);
                break;
        }
    }

    public static void viewAllStaff() {
        sm.refresh();
        sm.printAllStaff();
    }

    public static void hireStaff() {
        System.out.print("Name of new staff: ");
        sc.nextLine(); // clear buffer
        String name = sc.nextLine();

        System.out.print("Gender (Male or Female): ");
        String sex = sc.next();

        System.out.print("Title/Position: ");
        sc.nextLine(); // clear buffer
        String title = sc.nextLine();

        sm.addStaff(name, sex, title);
        sm.refresh();
    }

    public static void fireStaff() {
        System.out.print("Staff ID fired: ");
        sc.nextLine(); // clear buffer
        int id = sc.nextInt();

        sm.removeStaff(id);
        sm.refresh();
    }
}