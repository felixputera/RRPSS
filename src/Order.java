import java.util.*;
public class Order {
	private int OrderID;
	private int TableID;
	private Calendar DateTime;
	private List<Integer> AlaCarteIDList = new ArrayList<Integer>();
	private List<Integer> PackageIDList = new ArrayList<Integer>();
	
	
	public Order(int ID, Calendar reserve, int tID){
		OrderID = ID;
		DateTime = reserve;
		TableID = tID;
	}
	
	public int getOrderID(){
		return OrderID;
	}
	public Calendar getDateTime(){
		return DateTime;
	}
	public List<Integer> getAlaCarteIDList(){
		return AlaCarteIDList;
	}
	public List<Integer> getPackageIDList(){
		return PackageIDList;
	}
	public int getTableID(){
		return TableID;
	}
	
	
	public void setOrderID(int ID){
		OrderID = ID;
	}
	public void setDateTime(Calendar reserve){
		DateTime = reserve;
	}
	public void setTableID(int tID){
		TableID = tID;
	}
	
	
	public void addItemToAlaCarte(int alaCarteID){
		AlaCarteIDList.add(alaCarteID);
	}
	public void addItemToPromo(int PromoID){
		PackageIDList.add(PromoID);
	}
	
	
	public void removeItemFromAlaCarte(int alaCarteID){
		AlaCarteIDList.remove(alaCarteID);
	}
	public void removeItemFromPromo(int PromoID){
		PackageIDList.remove(PromoID);
	}
	
}
