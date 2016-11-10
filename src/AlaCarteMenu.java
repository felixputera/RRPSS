import java.io.Serializable;

public class AlaCarteMenu implements Serializable {
    private int alaCarteId;
    private String name;
    private float price;
    private String type;
    private String desc = "";

    public AlaCarteMenu(int alaCarteId, String name, float price, String type) {
        this.alaCarteId = alaCarteId;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getAlaCarteId() {
        return this.alaCarteId;
    }

    public void setAlaCarteId(int alaCarteId) {
        this.alaCarteId = alaCarteId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
