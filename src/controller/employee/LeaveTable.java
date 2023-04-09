package controller.employee;

public class LeaveTable {

    String id;
    String from;
    String to;
    String status;

    public LeaveTable(String id, String from, String to, String status) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getStatus() {
        return status;
    }

}
