import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.io.Serializable;

public class Order implements Serializable {
    private int orderId;
	private int tableId;
	private int staffId;
	private Calendar dateTime;
	private List<Integer> alaCarteIdList = new ArrayList<Integer>();
	private List<Integer> packageIdList = new ArrayList<Integer>();
	private int amountAlaCarteId[] = new int[100];
	private int amountPackageId[] = new int[100];
	
	public Order(int id, Calendar reserve, int tId, int sId){
		this.orderId = id;
		this.dateTime = reserve;
		this.tableId = tId;
		this.staffId = sId;
	}
	
	public int getOrderId(){
		return orderId;
	}
	
	public void setOrderId(int id){
		this.orderId = id;
	}
	
	public Calendar getDateTime(){
		return dateTime;
	}
	
	public void setDateTime(Calendar reserve){
		this.dateTime = reserve;
	}
	
	public int getTableId(){
		return tableId;
	}
	
	public void setTableId(int tId){
		this.tableId = tId;
	}
	
	public int getStaffId(){
		return staffId;
	}
	
	public void setStaffId(int sId){
		this.staffId = sId;
	}
	
	public List<Integer> getAlaCarteIdList(){
		return this.alaCarteIdList;
	}
	
	public List<Integer> getPackageIdList(){
		return this.packageIdList;
	}
	
	public int getAmountAlaCarteId(int alaId){
		return this.amountAlaCarteId[alaId];
	}
	
	public int getAmountPackageId(int packId){
		return this.amountPackageId[packId];
	}
	
	public void addItemToAlaCarte(int alaCarteId){
		if(amountAlaCarteId[alaCarteId] == 0){
			this.alaCarteIdList.add(alaCarteId);
		}
		amountAlaCarteId[alaCarteId] = amountAlaCarteId[alaCarteId] + 1;
	}
	
	public void addItemToPromo(int promoId){
		if(amountPackageId[promoId] == 0){
			this.packageIdList.add(promoId);
		}
		amountPackageId[promoId] = amountPackageId[promoId] + 1;
	}
	
	public void removeItemFromAlaCarte(int alaCarteId){
		amountAlaCarteId[alaCarteId] = amountPackageId[alaCarteId] - 1;
		if(amountAlaCarteId[alaCarteId] == 0){
			alaCarteIdList.remove(alaCarteId);
		}
	}
	
	public void removeItemFromPromo(int PromoID){
		amountPackageId[promoId] = amountPackageId[promoId] - 1;
		if(amountPackageId[promoId] == 0){
			packageIdList.remove(PromoID);
		}
	}
}