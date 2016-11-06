import java.util.*;
public class Order {
	private int OrderID;
	private Calendar DateTime;
	private List<Integer> AlaCarteIDList = new ArrayList<Integer>();
	private List<Integer> PackageIDList = new ArrayList<Integer>();
	
	
	public Order(int ID, Calendar reserve){
		OrderID = ID;
		DateTime = reserve;
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
	
	
	public void setOrderID(int ID){
		OrderID = ID;
	}
	public void setDateTime(Calendar reserve){
		DateTime = reserve;
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
