package controller.employee;

import controller.EasyDB;
import data.CurrentEmployee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Profile extends EasyDB
{

    @FXML
    private Rectangle recImage;
    @FXML
    private Label lblName, lblPost, lblNumber, lblGmail;
    @FXML
    private ImageView imgRecycle;

    //my variables
    CurrentEmployee currentEmployee = null;


    public void initialize() throws SQLException
    {
        currentEmployee = CurrentEmployee.getInstance();
        setup();//loads user's info
    }//end initialize


    private void setup() throws SQLException
    {
        ResultSet resultSet = super.getTableResultSet("INFO");
        resultSet.absolute(currentEmployee.getId());

        lblName.setText(resultSet.getString("name") + " " + resultSet.getString("lastname"));
        lblPost.setText(resultSet.getString("post"));
        recImage.setStroke(Color.TRANSPARENT);
        recImage.setArcHeight(20);
        recImage.setArcWidth(20);
        String imagePath = "file:data/profilePictures/" + resultSet.getInt("id") + ".png";
        recImage.setFill(new ImagePattern(new Image(imagePath, 120, 120, false, false)));
        lblGmail.setText(resultSet.getString("gmail"));
        lblNumber.setText(resultSet.getString("phone"));
        resultSet.close();

    }//end setup

}
