package controller.manager;

import com.jfoenix.controls.JFXButton;
import controller.EasyDB;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewEmployee extends EasyDB {

    //FXML variables
    @FXML
    private AnchorPane anchMain;
    @FXML
    private Pane paneLevel1, paneLevel2;
    @FXML
    private TextField txtName, txtLastname, txtChildrenNum, txtGmail, txtPhone, txtPassword, txtPasswordReenter;
    @FXML
    private ComboBox<String> comboPost;
    @FXML
    private ImageView imgBackground;

    String name,lastname,gmail,phone,childrenNumber;


    public void initialize()
    {
        try
        {
            Image image = new Image("file:images/newEmployeeBack.png");
            imgBackground.setImage(image);

            ResultSet resultSet = super.getTableResultSet("SALARY");
            while (resultSet.next())
            {
                comboPost.getItems().add(resultSet.getString("post"));
            }//end while
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end initialize

    @FXML
    private void nextClick()
    {
        name = txtName.getText();
        lastname = txtLastname.getText();
        phone = txtPhone.getText();
        gmail = txtGmail.getText();
        childrenNumber = txtChildrenNum.getText();

        if(name.isEmpty() || lastname.isEmpty() || phone.isEmpty() || gmail.isEmpty() || childrenNumber.isEmpty())
        {
            System.out.println("fill all");
        }
        else
        {
            paneLevel1.setVisible(false);
            anchMain.getChildren().remove(paneLevel1);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), paneLevel2);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
        }

    }//end nextClick


    @FXML
    private void hireClick() throws SQLException
    {
        String firstPass = txtPassword.getText();
        String secondPass = txtPasswordReenter.getText();

        if(!firstPass.equals(secondPass))
        {
            System.out.println("passwords don't match");
            return;
        }

        String post = comboPost.getValue();
        String values = "(NULL, '" + name + "', '" + lastname + "', '" + post + "', '" + childrenNumber + "', '" + gmail + "', '" + phone + "', '" + firstPass + "')";
        super.insert("INFO", values);
        System.out.println("oh god it should be there");



    }//end hireClick

}//end class
