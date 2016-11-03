import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PromoPackage implements Serializable {
    private int id;
    private float price;
    private List<Integer> menuIdList = new ArrayList<Integer>();

    public PromoPackage(int id, float price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Integer> getMenuIdList() {
        return this.menuIdList;
    }

    public void addMenuIdList(Integer mId) {
        this.menuIdList.add(mId);
    }

    public void removeMenuIdList(Integer mId) {
        this.menuIdList.remove(mId);
    }
}
