package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class EmployeeController {

    @FXML
    private Pane paneContent;
    @FXML
    private Label lblProfile,lblMySalary,lblMyTeams,lblLeave,lblChatBox;
    @FXML
    private ImageView btnSettings, btnBell;


    public void initialize()
    {
        loadPreviewImages();// loads bell and gear image

        //show off profile at start up
        profileClick();
    }


    private void loadPreviewImages()
    {
        Image bellImage = new Image("file:images/bell.png");
        Image gearImage = new Image("file:images/gear.png");
        btnBell.setImage(bellImage);
        btnSettings.setImage(gearImage);
    }//end loadPreviewImages


    @FXML
    private void profileClick()
    {
        try
        {
            fillDashboardButton(lblProfile);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/employee/Profile.fxml"));
            paneContent.getChildren().add(root);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }//end profileClick

    @FXML
    private void mySalaryClick()
    {
        try
        {
            fillDashboardButton(lblMySalary);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/employee/MySalary.fxml"));
            paneContent.getChildren().add(root);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end mySalaryClick

    @FXML
    private void myTeamsClick()
    {
        try
        {
            fillDashboardButton(lblMyTeams);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/employee/MyTeams.fxml"));
            paneContent.getChildren().add(root);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end myTeamsClick


    @FXML
    private void leaveClick()
    {
        try
        {
            fillDashboardButton(lblLeave);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/employee/Leave.fxml"));
            paneContent.getChildren().add(root);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end leaveClick


    @FXML
    private void chatBoxClick()
    {
        try
        {
            fillDashboardButton(lblChatBox);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/ChatBox.fxml"));
            paneContent.getChildren().add(root);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end chatBoxClick


    private void fillDashboardButton(Label label)
    {
        lblProfile.setStyle("-fx-background-color:  #0F76BD");
        lblMyTeams.setStyle("-fx-background-color:  #0F76BD");
        lblMySalary.setStyle("-fx-background-color:  #0F76BD");
        lblLeave.setStyle("-fx-background-color:  #0F76BD");
        lblChatBox.setStyle("-fx-background-color:  #0F76BD");

        label.setStyle("-fx-background-color: #ffffff");
    }


}
