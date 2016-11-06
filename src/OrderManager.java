import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderManager{
	AlaCarteManager AlaManager = new AlaCarteManager();
	PPackageManager PackManager = new PPackageManager();
	private List<Order> OrderList;
	private String FName = "./menu.dat";
	
	public OrderManager(){
		this.OrderList = (ArrayList) IOHandler.readSerializedObject(FName);
	}
	
	public void printInvoice(int OrderID){
		OrderList.get(OrderID).printOrder();
		System.out.println(OrderList.get(OrderID).CalculateTotal());
	}
	
	public int findIndex(int id) {
        for (int i = 0; i < OrderList.size(); i++) {
            if (OrderList.get(i).getOrderID() == id) return i;
        }
        System.out.println("ID not found");
        return -1;
    }
	
	public void createOrder(){
		OrderList.add(new Order(OrderList.size(), Calendar.getInstance()));
		System.out.println("Added entry to orderList");
		IOHandler.writeSerializedObject(FName, OrderList);
	}
	
	public void addItemToOrderAlaCarte(int alaCarteID, int OrderID){
		int oIndex = findIndex(OrderID);
        int aIndex = AlaManager.findIndex(alaCarteID);
        if (oIndex != -1 && aIndex != -1){
        	OrderList.get(oIndex).addItemToAlaCarte(alaCarteID);
        	System.out.println("Added Item to Order");
        }
		OrderList.get(OrderID).addItemToAlaCarte(alaCarteID);
		System.out.println("Added item to Order " + OrderID);
	}
	public void addItemToOrderPromo(int packID, int OrderID){
		int oIndex = findIndex(OrderID);
        int pIndex = PackManager.findIndex(packID);
        if (oIndex != -1 && pIndex != -1){
        	OrderList.get(oIndex).addItemToPromo(packID);
        	System.out.println("Added Item to Order " + OrderID);
        }
	}

	public void removeItemFromOrderAlaCarte(int alaCarteID, int OrderID){
		OrderList.get(OrderID).removeItemFromAlaCarte(alaCarteID);
	}
	public void removeItemFromOrderPromo(int packID, int OrderID){
		OrderList.get(OrderID).removeItemFromPromo(packID);
	}
	
	public void viewOrder(int orderId){
		OrderList.get(orderId).printOrder();
	}
	
	public float CalculateTotal(){
		float total = 0;
		for(int i=0; i < OrderList.get(i).getAlaCarteIDList().size(); i++){
			total = total + .getPrice();
		}
		for(int i=0; i< PackageList.size(); i++){
			total = total + PackageList.get(i).getPrice();
		}
		
		return total;
	}
	
	public void printOrder(){
		for(int i=0; i < AlaCarteList.size(); i++){
			System.out.println(AlaCarteList.get(i).getName() + " " + AlaCarteList.get(i).getPrice());
		}
		for(int i=0; i< PackageList.size(); i++){
			System.out.println("Package " + PackageList.get(i).getId() + " " + PackageList.get(i).getPrice());
		}
	}
	
	public void SalesRevenueReport(Date period){
		String strDate = period.toString();
		for(int i=0; i < OrderList.size(); i++){
			if(strDate == OrderList.get(i).getDateTime().toString()){
				System.out.println(OrderList.get(i).CalculateTotal());
			}
		}
	}
	public void SalesRevenueReport(int month){
		for(int i=0; i<OrderList.size(); i++){
			if(OrderList.get(i).getDateTime().get(Calendar.MONTH) == month){
				System.out.println(OrderList.get(i).CalculateTotal());
			}
		}
	}
}
