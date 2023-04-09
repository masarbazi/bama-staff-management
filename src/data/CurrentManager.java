package data;

public class CurrentManager
{

    int id;
    private static CurrentManager currentManager = null;

    public static CurrentManager getInstance()
    {
        if(currentManager == null)
            currentManager = new CurrentManager();

        return currentManager;
    }//end getInstance


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        ChatData.senderID = id;
    }


}//end class
