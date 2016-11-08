1import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderManager{
	private AlaCarteManager alaManager = new AlaCarteManager();
	private PPackageManager packManager = new PPackageManager();
	private TableManager tManager = new TableManager();
	private StaffManager sManager = new StaffManager();
	private List<Order> orderList;
	private String FName = "./order.dat";
	
	public OrderManager(){
		this.orderList = (ArrayList) IOHandler.readSerializedObject(FName);
	}
	
	public int findIndex(int id) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getorderId() == id) return i;
        }
        System.out.println("Order ID not found");
        return -1;
    }
	
	public void createOrder(int pax, int sId){
		int id;
		Calendar cal = Calendar.getInstance();
		if(tManager.resIsOpen(cal)){
			int tableId = tManager.findEmptyTable(pax, cal);
			int sIndex = sManager.findIndex(sId);
			if(tableId == -1 && sIndex == -1){
				System.out.println("Sorry there is no available table and staff with that ID");
			}
			else if(tableId == -1){
				System.out.println("Sorry there is no available table");
			}
			else if(sIndex == -1){
				System.out.println("Sorry there is no staff with that ID");
			}
			else{
				if(orderList.isEmpty()){
					id = 1;
				}
				else{
					id = orderList.get(orderList.size() - 1).getorderId() + 1;
				}
				Order o = new Order(id, cal, tableId, sId);
				orderList.add(o);
				System.out.println("Added entry to orderList");
				tManager.updateStatus(cal, tableId, 2);
				IOHandler.writeSerializedObject(FName, orderList);
			}
		}
	}
	
	public void removeOrder(int id){
		int i = findIndex(id);
		if(i != -1){
			orderList.remove(i);
			System.out.println("Order removed");
			IOHandler.writeSerializedObject(FName, orderList);
		}
	}
	
	public void addItemToOrderAlaCarte(int alaCarteId, int orderId){
		alaManager.refresh();
		int oIndex = findIndex(orderId);
        int aIndex = alaManager.findIndex(alaCarteId);
        if (oIndex != -1 && aIndex != -1){
    		orderList.get(oIndex).addItemToAlaCarte(alaCarteId);
        	System.out.println("Added Ala Carte Item to Order");
        	IOHandler.writeSerializedObject(FName, orderList);
        }
	}
	public void addItemToOrderPromo(int packId, int orderId){
		packManager.refresh();
		int oIndex = findIndex(orderId);
        int pIndex = packManager.findIndex(packId);
        if (oIndex != -1 && pIndex != -1){
        	orderList.get(oIndex).addItemToPromo(packId);
        	System.out.println("Added Promotional Package Item to Order");
        	IOHandler.writeSerializedObject(FName, orderList);
        }
	}

	public void removeItemFromOrderAlaCarte(int orderId, int alaCarteId){
		alaManager.refresh();
		int oIndex = findIndex(orderId);
		int aIndex = alaManager.findIndex(alaCarteId);
		if(oIndex != -1 && aIndex != -1){
			if(orderList.get(oIndex).getAlaCarteIdList().contains(alaCarteId)){
				orderList.get(oIndex).removeItemFromAlaCarte(alaCarteId);
				System.out.println("Ala Carte item deleted from order");
				IOHandler.writeSerializedObject(FName, orderList);
			}
			else{
				System.out.println("Ala Carte item has not been added to order before");
			}
		}
	}
	public void removeItemFromOrderPromo(int orderId, int packId){
		packManager.refresh();
		int oIndex = findIndex(orderId);
		int pIndex = packManager.findIndex(packID);
		if(oIndex != -1 && pIndex != -1){
			if(orderList.get(oIndex).getPackageIdList().contains(packId)){
				orderList.get(oIndex).removeItemFromPromo(packID);
				System.out.println("Promotional Package item deleted from order");
				IOHandler.writeSerializedObject(FName, orderList);
			}
			else{
				System.out.println("Promotional Package item has not been added to order before");
			}
		}
	}
		
	public float CalculateTotal(int orderId){
		float total = 0;
		int oIndex = findIndex(orderId);
		List <Integer> alaIdList = orderList.get(oIndex).getAlaCarteIdList();
		List <Integer> promoIdList = orderList.get(oIndex).getPackageIdList();
		for(int i=0; i < alaIdList.size(); i++){
			float price =  alaManager.getAlaCarteById(orderList.get(oIndex).getAlaCarteIdList().get(i)).getPrice();
			int quantity = orderList.get(oIndex).getAmountAlaCarteId(alaIdList.get(i));
			total = total + price * quantity;
		}
		for(int i=0; i< promoIdList.size(); i++){
			float price = packManager.getPromoById(orderList.get(oIndex).getPackageIdList().get(i)).getPrice();
			int quantity = orderList.get(oIndex).getAmountPackageId(promoIdList.get(i));
			total = total + price * quantity;
		}	
		return total;
	}
	
	public void viewOrder(int orderId){
		int oIndex = findIndex(orderId);
		List <Integer> alaIdList = orderList.get(oIndex).getAlaCarteIdList();
		List <Integer> promoIdList = orderList.get(oIndex).getPackageIdList();
		for(int i=0; i < alaIdList.size(); i++){
			String name = alaManager.getAlaCarteById(alaIdList.get(i)).getName();
			float price = alaManager.getAlaCarteById(alaIdList.get(i)).getPrice();
			int quantity = orderList.get(oIndex).getAmountAlaCarteId(alaIdList.get(i));
			System.out.println(name + " " + quantity + " " + price * quantity);
		} 
		for(int i=0; i< promoIdList.size(); i++){
			String name = "Package " + promoIdList.get(i);
			float price = packManager.getPromoById(promoIdList.get(i)).getPrice();
			int quantity = orderList.get(oIndex).getAmountPackageId(promoIdlest.get(i));
			System.out.println(name + " " + quantity + " " + price * quantity);
		}
	}
	
	public void printInvoice(int orderId){
		int oIndex = findIndex(orderId);
		int tableId = orderList.get(oIndex).getTableId();
		Calender cal = orderList.get(oIndex).getDateTime();
		double total;
		System.out.println("	Delicious Food Restaurant	");
		System.out.println("		12 Newton Street		");
		System.out.println("		   Singapore			");
		System.out.println("		Tel: 123-456-7000		");
		System.out.println("			Order ID: " + orderId);
		System.out.println("			Table ID: " + tableId);
		System.out.println("		  " + Calendar.getInstance());
		this.viewOrder(orderId);
		total = CalculateTotal(orderId);
		System.out.println("Subtotal.....		" + total);
		System.out.println("10% Service Charge	" + 0.1 * total);
		total = total + 0.1 * total;
		System.out.println("7%GST				" + 0.07 * total);
		total = total + 0.07 * total;
		System.out.println("Total				" + total);
		
		tManager.updateStatus(cal, tableId, 0);
	}
	
	public void SalesRevenueReport(Date period){
		int amountAlaCarte[] = new int(100);
		int amountPackage[] = new int(100);
		String strDate = period.toString();
		float total = 0;
		System.out.println(strDate);
		for(int i=0; i < orderList.size(); i++){
			if(strDate == orderList.get(i).getDateTime().toString()){
				total = total + CalculateTotal(orderList.get(i).getOrderId());
				for(int j=0; j < orderList.get(i).getAlaCarteIdList().size(); j++){
					List <integer> alaCarte = orderList.get(i).getAlaCarteIdList();
					amountAlaCarte[alaCarte.get(j)] += orderList.get(i).getAmountAlaCarteId(alaCarte.get(j));
				}
				for(int j=0; j < orderList.get(i).getPackageIdList().size(); j++){
					List <integer> promo = orderList.get(i).getPackageIdList();
					amountPackage[promo.get(j)] += orderList.get(i).getAmountPackageId(promo.get(j));
				}
			}
		}
		for(int i = 1; i < 100; i++){
			String name = alaManager.getAlaCarteById(i).getName();
			System.out.println(name + " " + amountAlaCarte[i]);
		}
		for(int i = 1; i < 100; i++){
			String name = "Package " +  packManager.getPromoById(i).getId();
			System.out.println(name + " " + amountPackage[i]);
		}
	}
	
	public void SalesRevenueReport(int month){
		int amountAlaCarte[] = new int(100);
		int amountPackage[] = new int(100);
		float total = 0;
		System.out.println("Month " + month);
		for(int i=0; i<orderList.size(); i++){
			if(orderList.get(i).getDateTime().get(Calendar.MONTH) == month){
				total = total + CalculateTotal(orderList.get(i).getOrderId());
				for(int j=0; j < orderList.get(i).getAlaCarteIdList().size(); j++){
					List <integer> alaCarte = orderList.get(i).getAlaCarteIdList();
					amountAlaCarte[alaCarte.get(j)] += orderList.get(i).getAmountAlaCarteId(alaCarte.get(j));
				}
				for(int j=0; j < orderList.get(i).getPackageIdList().size(); j++){
					List <integer> promo = orderList.get(i).getPackageIdList();
					amountPackage[promo.get(j)] += orderList.get(i).getAmountPackageId(promo.get(j));
				}
			}
		}
		for(int i = 1; i < 100; i++){
			String name = alaManager.getAlaCarteById(i).getName();
			System.out.println(name + " " + amountAlaCarte[i]);
		}
		for(int i = 1; i < 100; i++){
			String name = "Package " +  packManager.getPromoById(i).getId();
			System.out.println(name + " " + amountPackage[i]);
		}
		System.out.println("Total Revenue " + total);
	}
	
	public void refresh(){
		this.orderList = (ArrayList) IOHandler.readSerializedObject(FName);
	}
}