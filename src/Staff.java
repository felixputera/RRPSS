import java.io.Serializable;

public class Staff implements Serializable {
    private String name;
    private String gender;
    private int employeeId;
    private String jobTitle;

    public Staff(String Sname, String sgender, int id, String title) {
        this.name = Sname;
        this.gender = sgender;
        this.employeeId = id;
        this.jobTitle = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String sname) {
        this.name = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sgender) {
        this.gender = sgender;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int id) {
        this.employeeId = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String title) {
        this.jobTitle = title;
    }
}
