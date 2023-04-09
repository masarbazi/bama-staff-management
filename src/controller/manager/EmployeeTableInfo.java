package controller.manager;

public class EmployeeTableInfo {

    String name;
    String lastname;
    String post;
    String phone;
    String gmail;
    String totalSalary;

    public EmployeeTableInfo(String name, String lastname, String post, String phone, String gmail, String totalSalary) {
        this.name = name;
        this.lastname = lastname;
        this.post = post;
        this.phone = phone;
        this.gmail = gmail;
        this.totalSalary = totalSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }
}
