import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private int tableId;
    private int staffId;
    private Calendar dateTime;
    private List<Integer[]> alaCarteIdNQtyList = new ArrayList<>();
    private List<Integer[]> packageIdNQtyList = new ArrayList<>();

    public Order(int id, Calendar reserve, int tId, int sId) {
        this.orderId = id;
        this.dateTime = reserve;
        this.tableId = tId;
        this.staffId = sId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar reserve) {
        this.dateTime = reserve;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tId) {
        this.tableId = tId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int sId) {
        this.staffId = sId;
    }

    public List<Integer[]> getAlaCarteIdNQtyList() {
        return this.alaCarteIdNQtyList;
    }

    public List<Integer[]> getPackageIdNQtyList() {
        return this.packageIdNQtyList;
    }

    public int getAlaCarteQty(int alaCarteId) {
        for (Integer[] i : alaCarteIdNQtyList) {
            if (i[0] == alaCarteId) {
                return i[1];
            }
        }
        return 0;
    }

    public int getPromoQty(int promoId) {
        for (Integer[] i : packageIdNQtyList) {
            if (i[0] == promoId) {
                return i[1];
            }
        }
        return 0;
    }

    public void addItemAlaCarte(int alaCarteId) {
        boolean found = false;
        for (Integer[] i : alaCarteIdNQtyList) {
            if (i[0] == alaCarteId) {
                alaCarteIdNQtyList.get(alaCarteIdNQtyList.size() - 1)[1] += 1;
                found = true;
            }
        }
        if (!found) {
            alaCarteIdNQtyList.add(new Integer[2]);
            alaCarteIdNQtyList.get(alaCarteIdNQtyList.size() - 1)[0] = alaCarteId;
            alaCarteIdNQtyList.get(alaCarteIdNQtyList.size() - 1)[1] = 1;
        }
    }

    public void addItemPromo(int promoId) {
        boolean found = false;
        for (Integer[] p : packageIdNQtyList) {
            if (p[0] == promoId) {
                packageIdNQtyList.get(packageIdNQtyList.size() - 1)[1] += 1;
                found = true;
            }
        }
        if (!found) {
            packageIdNQtyList.add(new Integer[2]);
            packageIdNQtyList.get(packageIdNQtyList.size() - 1)[0] = promoId;
            packageIdNQtyList.get(packageIdNQtyList.size() - 1)[1] = 1;
        }
    }

    public void removeItemAlaCarte(int alaCarteId) {
        for (Integer[] i : alaCarteIdNQtyList) {
            if (i[0] == alaCarteId) {
                i[1]--;
            }
        }
        alaCarteIdNQtyList.removeIf(i -> i[1] == 0);
    }

    public void removeItemPromo(int promoId) {
        for (Integer[] i : packageIdNQtyList) {
            if (i[0] == promoId) {
                i[1]--;
            }
        }
        alaCarteIdNQtyList.removeIf(i -> i[1] == 0);
    }
}