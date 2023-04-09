package chat;

import com.sun.corba.se.spi.ior.TaggedProfileTemplate;
import data.ChatData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;


public class ChatBoxController extends SetupData {

    @FXML
    private AnchorPane anchMain;
    @FXML
    private Pane paneEmoji, paneMessages, panePeopleList;
    @FXML
    private TextField txtChatText, txtChatID;
    @FXML
    private ImageView btnSendMessage;
    @FXML
    private ListView<String> listMessages;
    @FXML
    private Button btnFindTagetChat, btnEnterChat;
    @FXML
    private Rectangle recTagetChatImage;
    @FXML
    private Label lblTargetChatName;

    //variables




    public void initialize()
    {
        //add send button image
        Image image = new Image("file:images/send-icon.png");
        btnSendMessage.setImage(image);
        btnEnterChat.setDisable(true);

        setupEmoji();
    }//end initialize


    private void setupEmoji()
    {
        double startX = 15, startY = 15;
        int imageIndex = 0;

        for(int i=0;i<14;i++)
        {
            double emojiY = startY + (i*40);

            for(int j=0;j<6;j++)
            {
                double emojiX = startX + (j*40);

                String imagePath = "chat/emoji/Layer " + imageIndex + ".png";
                Image image = new Image("file:" + imagePath, 30, 30, false, false);
                Circle circle = new Circle(15);
                circle.setFill(new ImagePattern(image));
                circle.setLayoutX(emojiX + 7.5);
                circle.setLayoutY(emojiY + 7.5);
                imageIndex++;

                paneEmoji.getChildren().add(circle);
            }
        }//end for

    }//end setupEmoji

    @FXML
    private void btnFindClicked()
    {
        try
        {
            int index = Integer.parseInt(txtChatID.getText());
            ResultSet resultSet = super.getTableResultSet("info");
            resultSet.last();
            if(index<=resultSet.getRow())
            {
                resultSet.absolute(index);
                String name = resultSet.getString("name") + " " + resultSet.getString("lastname");
                lblTargetChatName.setText(name);
                Image proImage = new Image("file:data/profilePictures/" + index + ".png", 80, 80, false, false);
                recTagetChatImage.setFill(new ImagePattern(proImage));
                btnEnterChat.setDisable(false);
            }
            else
            {
                System.out.println("Index out of range");
                lblTargetChatName.setText(". . .");
                recTagetChatImage.setFill(null);
                btnEnterChat.setDisable(true);
            }

            resultSet.close();

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end btnFindClicked

    @FXML
    private void btnEnterChatClicked()
    {
        if(!lblTargetChatName.getText().equals(". . ."))
        {
            int targetIndex = Integer.parseInt(txtChatID.getText());
            int senderID = ChatData.senderID;
            if(senderID>targetIndex)
            {
                int tmp = targetIndex;
                targetIndex = senderID;
                senderID = tmp;
            }
            String chatID = senderID + ":" + targetIndex;
            super.setChatID(chatID);

            loadOldChat();
            //now Enter the chat form
            panePeopleList.setVisible(false);
            paneMessages.setVisible(true);
        }
    }//end btnEnterChat


    private void loadOldChat()
    {
        ArrayList<String> messages = super.getSortedOldChats();

        for(String thisMessage : messages)
        {
            listMessages.getItems().add(thisMessage);
        }

    }//end loadOldChat


    @FXML
    private void btnSendMessageClicked()
    {
        String message = txtChatText.getText();
        super.sendMessage(message);
        listMessages.getItems().add(message);
        txtChatText.setText("");
    }//end btnSendMessageClicked


}//end class
