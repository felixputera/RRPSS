import java.io.Serializable;

public class AlaCarteMenu implements Serializable{
    private int id;
    private String name;
    private float price;
    private String type;
    private String desc = "";

    public AlaCarteMenu(int id, String name, float price, String type){
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getPrice(){
        return this.price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }
}
