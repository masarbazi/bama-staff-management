package controller.manager;

public class LeaveManagementTable {

    String id;
    String name;
    String phone;
    String gmail;
    String from;
    String to;

    public LeaveManagementTable(String id, String name, String phone, String gmail, String from, String to) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gmail = gmail;
        this.from = from;
        this.to = to;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGmail() {
        return gmail;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

}
