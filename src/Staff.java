import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff implements Serializable{
	private String name;
	private boolean gender;
	private int employeeId;
	private String jobTitle;
	
	public Staff(String Sname, boolean Sgender, int id, String title){
		this.name = Sname;
		this.gender = Sgender;
		this.employeeId = id;
		this.jobTitle = title;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String sname){
		this.name = sname;
	}
	
	public boolean getGender(){
		return gender;
	}
	
	public void setGender(boolean sgender){
		this.gender = sgender;
	}
	
	public int getEmployeeId(){
		return employeeId;
	}
	
	public void setEmployeeId(int id){
		this.employeeId = id;
	}
	
	public String getJobTitle(){
		return jobTitle;
	}
	
	public void setJobTitle(String title){
		this.jobTitle = title;
	}
}
