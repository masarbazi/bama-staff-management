package controller.manager;

import controller.EasyDB;
import data.CurrentManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile extends EasyDB {

    //FXML variables
    @FXML
    private Rectangle recImage;
    @FXML
    private Label lblName, lblNumber, lblGmail;
    @FXML
    private ImageView imgRecycle;

    //my variables
    CurrentManager currentManager = null;


    public void initialize() throws SQLException
    {
        currentManager = CurrentManager.getInstance();
        setup();
    }//end initialize


    private void setup() throws SQLException
    {
        ResultSet resultSet = super.getTableResultSet("INFO");
        resultSet.absolute(currentManager.getId());

        lblName.setText(resultSet.getString("name") + " " + resultSet.getString("lastname"));
        recImage.setStroke(Color.TRANSPARENT);
        recImage.setArcHeight(10);
        recImage.setArcWidth(10);
        String imagePath = "file:data/profilePictures/" + resultSet.getInt("id") + ".png";
        recImage.setFill(new ImagePattern(new Image(imagePath, 120, 120, false, false)));
        lblGmail.setText(resultSet.getString("gmail"));
        lblNumber.setText(resultSet.getString("phone"));
        resultSet.close();
    }//end setup




}
