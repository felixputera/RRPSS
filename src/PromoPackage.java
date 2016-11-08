import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PromoPackage implements Serializable {
    private int promoId;
    private float price;
    private List<Integer> menuIdList = new ArrayList<Integer>();

    public PromoPackage(int promoId, float price) {
        this.promoId = promoId;
        this.price = price;
    }

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
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
