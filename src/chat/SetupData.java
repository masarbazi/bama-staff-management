package chat;

import controller.EasyDB;
import data.ChatData;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SetupData extends EasyDB {

    String chatID;


    public SetupData(String chatID)
    {
        this.chatID = chatID;
    }//end SetupData

    public SetupData()
    {

    }

    public String getChatID() {
        return chatID;
    }


    public void setChatID(String chatID) {
        this.chatID = chatID;
    }


    public ArrayList<String> takePackages()
    {
        ArrayList<String> packages = new ArrayList<>();

        if(!chatID.isEmpty())
        {
            try
            {
                ResultSet resultSet = super.getTableResultSet("SCHEDULE");

                int firstIndex = Integer.parseInt(chatID.split(":")[0]);
                int secondIndex = Integer.parseInt(chatID.split(":")[1]);
                resultSet.absolute(firstIndex);
                String allChat = resultSet.getString("chat");
                resultSet.absolute(secondIndex);
                allChat += resultSet.getString("chat");
                //now all chats are stored in allChat
                String regex = "<" + chatID + " (.*?)</" + chatID + ">";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(allChat);
                while (matcher.find())
                {
                    packages.add(matcher.group());
                }

            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }

        return packages;
    }//end takeMessages


    public ArrayList<String> getSortedOldChats()
    {
        ArrayList<String> packages = takePackages();
        System.out.println("packages size is : " + packages.size());
        ArrayList<String> messages = new ArrayList<>();
        //sort chats by tab
        for(int i=1;i<packages.size();i++)
        {
            String thisPackage = packages.get(i);
            int thisTab = Integer.parseInt(thisPackage.substring(thisPackage.indexOf("tab="),thisPackage.indexOf(">")).replaceAll("[\\D]", ""));
            int j = i-1;

            int dynamicTab = Integer.parseInt(packages.get(j).substring(thisPackage.indexOf("tab="), thisPackage.indexOf(">")).replaceAll("[\\D]", ""));
            while (j>=0 && thisTab<dynamicTab)
            {
                packages.set(j+1, packages.get(j));
                j--;
                if(j>=0)
                    dynamicTab = Integer.parseInt(packages.get(j).substring(thisPackage.indexOf("tab=")+4, thisPackage.indexOf(">")));
            }//end while
            packages.set(j+1, thisPackage);

        }//end for

        for(String thisPackage : packages)
        {
            String message = thisPackage.substring(thisPackage.indexOf(">")+1, thisPackage.indexOf("</"));
            messages.add(message);
        }

        return messages;
    }//end getSortedOldChats


    public void sendMessage(String message)
    {
        try
        {
            int sender = ChatData.senderID;//user with this id is currently using app

            if(chatID.isEmpty())
            {
                System.out.println("Empty chat ID");
                return;
            }

            ResultSet resultSet = super.getTableResultSet("SCHEDULE");
            resultSet.absolute(sender);
            String oldChats = resultSet.getString("chat");
            resultSet.close();
            message = "<" + chatID + " owner=" + sender + " tab=" + (takeLastTab()+1) + ">" + message + "</" + chatID + ">";
            String updatedChat = oldChats + message;
            super.updateSchedule("chat", sender, updatedChat);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end sendMessage


    private int takeLastTab()
    {
         ArrayList<String> packages = takePackages();
         int result = 0;

         for(String aPackage : packages)
         {
             if(aPackage.isEmpty())
                 break;
             int tab = Integer.parseInt(aPackage.substring(aPackage.indexOf("tab=")+4, aPackage.indexOf(">")));
             if(tab>result)
                 result = tab;
         }//end for

        return result;
    }//end takeLastTab


}
