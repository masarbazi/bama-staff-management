package data;

import java.sql.ResultSet;

public class CurrentEmployee {

    int id;
    String post;

    private static CurrentEmployee currentEmployee = null;

    public static CurrentEmployee getInstance()
    {
        if(currentEmployee==null)
            currentEmployee = new CurrentEmployee();

        return currentEmployee;
    }

    private CurrentEmployee()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        ChatData.senderID = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
