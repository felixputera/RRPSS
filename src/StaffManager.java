import java.util.ArrayList;
import java.util.List;

public class StaffManager {
	private List<Staff> staffList;
	private String FName = "./staff.dat";
	
	public StaffManager(){
		this.staffList = (ArrayList) IOHandler.readSerializedObject(FName);
	}
	
	public int findIndex(int id){
		for(int i=0; i< staffList.size(); i++){
			if(staffList.get(i).getEmployeeId() == id){
				return i;
			}
		}
		System.out.println("Staff ID not found");
		return -1;
	}
	
	public void addStaff(String sname, String sgender, String title){
		int id;
		if(staffList.isEmpty()){
			id = 1;
		}
		else{
			id = staffList.get(staffList.size() - 1).getEmployeeId() + 1;
		}
		Staff s = new Staff(sname, sgender, id, title);
		this.staffList.add(s);
		System.out.println("Hired new employee with ID " + id);
		IOHandler.writeSerializedObject(FName, staffList);
	}
	
	public void removeStaff(int id){
		int i = findIndex(id);
		if(i != -1){
			staffList.remove(i);
			System.out.println("Fired staff ID " + id);
			IOHandler.writeSerializedObject(FName, staffList);
		}
	}

	public void printAllStaff(){
		if(staffList.size() == 0){
			System.out.println("There is no staff");
		}
		for (int i = 0; i < staffList.size(); i++) {
			Staff s = staffList.get(i);
			System.out.println("Staff ID: " + s.getEmployeeId());
			System.out.println("Name: " + s.getName());
			System.out.println("Gender: " + s.getGender());
			System.out.println("Job Title : " + s.getJobTitle());
			System.out.println();
		}
	}
	
	public void refresh() {
		this.staffList = (ArrayList) IOHandler.readSerializedObject(FName);
	}
}
