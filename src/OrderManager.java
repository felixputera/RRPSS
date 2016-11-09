import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringJoiner;

public class OrderManager {
    private AlaCarteManager alaManager = new AlaCarteManager();
    private PPackageManager packManager = new PPackageManager();
    private TableManager tManager = new TableManager();
    private StaffManager sManager = new StaffManager();
    private ReservationManager rManager = new ReservationManager();
    private List<Order> orderList;
    private String FName = "./order.dat";

    public OrderManager() {
        this.orderList = (ArrayList) IOHandler.readSerializedObject(FName);
    }

    public int findIndex(int id) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId() == id) return i;
        }
        System.out.println("Order ID not found");
        return -1;
    }

    public void createOrderNoReservation(int pax, int sId) {
        int id;
        tManager.refresh();
        sManager.refresh();
        Calendar now = Calendar.getInstance();
        if (tManager.resIsOpen(now)) {
            int tableId = tManager.findEmptyTable(pax, now);
            int sIndex = sManager.findIndex(sId);
            if (tableId == -1 && sIndex == -1) {
                System.out.println("Sorry there is no available table and staff with that ID");
            } else if (tableId == -1) {
                System.out.println("Sorry there is no available table");
            } else if (sIndex == -1) {
                System.out.println("Sorry there is no staff with that ID");
            } else {
                if (orderList.isEmpty()) {
                    id = 1;
                } else {
                    id = orderList.get(orderList.size() - 1).getOrderId() + 1;
                }
                Order o = new Order(id, now, tableId, sId);
                orderList.add(o);
                System.out.println("Created order with ID: " + id);
                tManager.updateStatus(now, tableId, 2);
                IOHandler.writeSerializedObject(FName, orderList);
            }
        }
    }

    public void createOrderReservation(int pax, int sId, int contactNo) {
        int id;
        tManager.refresh();
        sManager.refresh();
        rManager.refresh();
        Calendar now = Calendar.getInstance();
        if (tManager.resIsOpen(now)) {
            int tableId = rManager.getTableIDByContactNumber(contactNo);
            int sIndex = sManager.findIndex(sId);
            if (tableId == -1 && sIndex == -1) {
                System.out.println("Sorry there is no available table and staff with that ID");
            } else if (tableId == -1) {
                System.out.println("Sorry there is no available table");
            } else if (sIndex == -1) {
                System.out.println("Sorry there is no staff with that ID");
            } else {
                if (orderList.isEmpty()) {
                    id = 1;
                } else {
                    id = orderList.get(orderList.size() - 1).getOrderId() + 1;
                }
                Order o = new Order(id, now, tableId, sId);
                orderList.add(o);
                System.out.println("Created order with ID: " + id);
                tManager.updateStatus(now, tableId, 2);
                IOHandler.writeSerializedObject(FName, orderList);
            }
        }
    }

    public void removeOrder(int id) {
        sManager.refresh();
        int i = findIndex(id);
        if (i != -1) {
            orderList.remove(i);
            System.out.println("Order removed");
            IOHandler.writeSerializedObject(FName, orderList);
        }
    }

    public void addItemToOrderAlaCarte(int alaCarteId, int orderId) {
        alaManager.refresh();
        int oIndex = findIndex(orderId);
        int aIndex = alaManager.findIndex(alaCarteId);
        if (oIndex != -1 && aIndex != -1) {
            orderList.get(oIndex).addItemAlaCarte(alaCarteId);
            System.out.println("Added Ala Carte Item to Order");
            IOHandler.writeSerializedObject(FName, orderList);
        }
    }

    public void addItemToOrderPromo(int packId, int orderId) {
        packManager.refresh();
        int oIndex = findIndex(orderId);
        int pIndex = packManager.findIndex(packId);
        if (oIndex != -1 && pIndex != -1) {
            orderList.get(oIndex).addItemPromo(packId);
            System.out.println("Added Promotional Package Item to Order");
            IOHandler.writeSerializedObject(FName, orderList);
        }
    }

    public void removeItemFromOrderAlaCarte(int orderId, int alaCarteId) {
        alaManager.refresh();
        int oIndex = findIndex(orderId);
        int aIndex = alaManager.findIndex(alaCarteId);
        if (oIndex != -1 && aIndex != -1) {
            if (orderList.get(oIndex).getAlaCarteIdNQtyList().contains(alaCarteId)) {
                orderList.get(oIndex).removeItemAlaCarte(alaCarteId);
                System.out.println("Ala Carte item deleted from order");
                IOHandler.writeSerializedObject(FName, orderList);
            } else {
                System.out.println("Ala Carte item has not been added to order before");
            }
        }
    }

    public void removeItemFromOrderPromo(int orderId, int packId) {
        packManager.refresh();
        int oIndex = findIndex(orderId);
        int pIndex = packManager.findIndex(packId);
        if (oIndex != -1 && pIndex != -1) {
            if (orderList.get(oIndex).getPackageIdNQtyList().contains(packId)) {
                orderList.get(oIndex).removeItemPromo(packId);
                System.out.println("Promotional Package item deleted from order");
                IOHandler.writeSerializedObject(FName, orderList);
            } else {
                System.out.println("Promotional Package item has not been added to order before");
            }
        }
    }

    public float CalculateTotal(int orderId) {
        float total = 0;
        int oIndex = findIndex(orderId);
        List<Integer[]> alaIdList = orderList.get(oIndex).getAlaCarteIdNQtyList();
        List<Integer[]> promoIdList = orderList.get(oIndex).getPackageIdNQtyList();
        for (Integer[] alaCarte : alaIdList) {
            float price = alaManager.getAlaCarteById(alaCarte[0]).getPrice();
            int quantity = alaCarte[1];
            total = total + price * quantity;
        }
        for (Integer[] promo : promoIdList) {
            float price = packManager.getPromoById(promo[0]).getPrice();
            int quantity = promo[1];
            total = total + price * quantity;
        }
        return total;
    }

    public void viewOrder(int orderId) {
        int oIndex = findIndex(orderId);
        List<Integer[]> alaIdList = orderList.get(oIndex).getAlaCarteIdNQtyList();
        List<Integer[]> promoIdList = orderList.get(oIndex).getPackageIdNQtyList();
        if (alaIdList.size() == 0 && promoIdList.size() == 0) {
            System.out.println("There is no entry in order ID " + orderId);
        }
        for (Integer[] alaCarte : alaIdList) {
            String name = alaManager.getAlaCarteById(alaCarte[0]).getName();
            float price = alaManager.getAlaCarteById(alaCarte[0]).getPrice();
            int quantity = alaCarte[1];
            System.out.println(name + " " + quantity + " " + price * quantity);
        }
        for (Integer[] promo : promoIdList) {
            String name = "Package " + promo[0];
            float price = packManager.getPromoById(promo[0]).getPrice();
            int quantity = promo[1];
            System.out.println(name + " " + quantity + " " + price * quantity);
        }
    }

    public void printInvoice(int orderId) {
        int oIndex = findIndex(orderId);
        int tableId = orderList.get(oIndex).getTableId();
        Calendar cal = orderList.get(oIndex).getDateTime();
        String invoice = "";
        double total;
        System.out.println("	Delicious Food Restaurant	");
        invoice = invoice+("    Delicious Food Restaurant   \n");
        System.out.println("		12 Newton Street		");
        invoice = invoice+("        12 Newton Street        \n");
        System.out.println("		   Singapore			");
        invoice = invoice+("           Singapore            \n\n");
        System.out.println("");
        System.out.println("		Tel: 123-456-7000		");
        invoice = invoice+("        Tel: 123-456-7000       \n");
        System.out.println("		 Order ID: " + orderId);
        invoice = invoice+("        Order ID: " + orderId + "\n");
        System.out.println("		 Table ID: " + tableId);
        invoice = invoice+("         Table ID: " + tableId + "\n");
        System.out.println("___________________________________");
        invoice = invoice+("___________________________________\n");
        Calendar timestamp = Calendar.getInstance();
        int year = timestamp.get(Calendar.YEAR);
        int month = timestamp.get(Calendar.MONTH);      // NOTE!!! : Month from 0 to 11
        int day = timestamp.get(Calendar.DAY_OF_MONTH);
        int hour = timestamp.get(Calendar.HOUR_OF_DAY);
        int minute = timestamp.get(Calendar.MINUTE);
        int second = timestamp.get(Calendar.SECOND);
        System.out.println("		  " + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);
        invoice = invoice+("		  " + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second + "\n");
        this.viewOrder(orderId);
        total = CalculateTotal(orderId);
        System.out.println("Subtotal.....		" + total);
        invoice = invoice+("Subtotal.....		" + total + "\n");
        System.out.println("10% Service Charge	" + 0.1 * total);
        invoice = invoice+("10% Service Charge	" + 0.1 * total + "\n");
        total = total + 0.1 * total;
        System.out.println("7%GST				" + 0.07 * total);
        invoice = invoice+("7%GST				" + 0.07 * total + "\n");
        total = total + 0.07 * total;
        System.out.println("___________________________________");
        invoice = invoice+("___________________________________\n");
        System.out.println("Total				" + total);
        invoice = invoice+("Total				" + total + "\n");

        IOHandler.writeStringToTxtFile(String.valueOf(orderId), invoice);
        tManager.updateStatus(cal, tableId, 0);
    }

    public void salesRevenueReport(int month, int day, int year) {
        int amountAlaCarte[] = new int[alaManager.menuSize()];
        int amountPackage[] = new int[packManager.menuSize()];
        float total = 0;
        System.out.println(month + "-" + day + "-" + year + " (MM-dd-yyyy)");
        for (Order o : orderList) {
            if (year == o.getDateTime().get(Calendar.YEAR) &&
                    month == o.getDateTime().get(Calendar.MONTH) &&
                    day == o.getDateTime().get(Calendar.DAY_OF_MONTH)) {
                total = total + CalculateTotal(o.getOrderId());
                for (int j = 0; j < o.getAlaCarteIdNQtyList().size(); j++) {
                    List<Integer[]> alaCarte = o.getAlaCarteIdNQtyList();
                    amountAlaCarte[alaCarte.get(j)[0]] += alaCarte.get(j)[1];
                }
                for (int j = 0; j < o.getPackageIdNQtyList().size(); j++) {
                    List<Integer[]> promo = o.getPackageIdNQtyList();
                    amountPackage[promo.get(j)[0]] += promo.get(j)[1];
                }
            }
        }
        for (int i = 1; i <= alaManager.menuSize(); i++) {
            String name = alaManager.getAlaCarteById(i).getName();
            System.out.println(name + " " + amountAlaCarte[i]);
        }
        for (int i = 1; i <+ packManager.menuSize(); i++) {
            String name = "Package " + i;
            System.out.println(name + " " + amountPackage[i]);
        }
        System.out.println("Total Revenue " + total);
    }

    public void salesRevenueReport(int month) {
        int amountAlaCarte[] = new int[100];
        int amountPackage[] = new int[100];
        float total = 0;
        System.out.println("Month " + month);
        for (Order o : orderList) {
            if (month == o.getDateTime().get(Calendar.MONTH) ) {
                total = total + CalculateTotal(o.getOrderId());
                for (int j = 0; j < o.getAlaCarteIdNQtyList().size(); j++) {
                    List<Integer[]> alaCarte = o.getAlaCarteIdNQtyList();
                    amountAlaCarte[alaCarte.get(j)[0]] += alaCarte.get(j)[1];
                }
                for (int j = 0; j < o.getPackageIdNQtyList().size(); j++) {
                    List<Integer[]> promo = o.getPackageIdNQtyList();
                    amountPackage[promo.get(j)[0]] += promo.get(j)[1];
                }
            }
        }
        for (int i = 1; i <= alaManager.menuSize(); i++) {
            String name = alaManager.getAlaCarteById(i).getName();
            System.out.println(name + " " + amountAlaCarte[i]);
        }
        for (int i = 1; i <+ packManager.menuSize(); i++) {
            String name = "Package " + i;
            System.out.println(name + " " + amountPackage[i]);
        }
        System.out.println("Total Revenue " + total);
    }

    public void refresh() {
        this.orderList = (ArrayList) IOHandler.readSerializedObject(FName);
    }
}