package controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ManagerController {

    //FXML variables
    @FXML
    private AnchorPane anchMain;
    @FXML
    private VBox dashboard;
    @FXML
    private Pane paneDashboardProfile, paneHeader, paneHeaderImage, paneContent;
    @FXML
    private Label lblProfile, lblNewEmployee, lblEmployees, lblTeams, lblSalaryManagement, lblLeaveManagement, lblChatBox;
    @FXML
    private ImageView btnBell, btnSettings;

    //my variables


    public void initialize()
    {
        loadPreviewImages();

        //show off profile at start up
        profileClicked();
    }//end initialize


    private void loadPreviewImages()
    {
        Image bellImage = new Image("file:images/bell.png");
        Image gearImage = new Image("file:images/gear.png");
        btnBell.setImage(bellImage);
        btnSettings.setImage(gearImage);
    }//end loadPreviewImages


    @FXML
    private void profileClicked()
    {
        try
        {
            fillDashboardButton(lblProfile);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/manager/Profile.fxml"));
            paneContent.setOpacity(0);
            paneContent.getChildren().add(root);
            paneContentFade();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end profileClicked


    @FXML
    private void newEmployeeClicked()
    {
        try
        {
            fillDashboardButton(lblNewEmployee);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/manager/NewEmployee.fxml"));
            paneContent.setOpacity(0);
            paneContent.getChildren().add(root);
            paneContentFade();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void employeesClicked()
    {
        try
        {
            fillDashboardButton(lblEmployees);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/manager/Employees.fxml"));
            paneContent.setOpacity(0);
            paneContent.getChildren().add(root);
            paneContentFade();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void teamsClicked()
    {
        try
        {
            fillDashboardButton(lblTeams);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/manager/Teams.fxml"));
            paneContent.setOpacity(0);
            paneContent.getChildren().add(root);
            paneContentFade();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void salaryManagementClicked()
    {
        try
        {
            fillDashboardButton(lblSalaryManagement);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/manager/SalaryManagement.fxml"));
            paneContent.setOpacity(0);
            paneContent.getChildren().add(root);
            paneContentFade();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void leaveManagementClicked()
    {
        try
        {
            fillDashboardButton(lblLeaveManagement);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/manager/LeaveManagement.fxml"));
            paneContent.setOpacity(0);
            paneContent.getChildren().add(root);
            paneContentFade();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    @FXML
    private void chatBoxClicked()
    {
        try
        {
            fillDashboardButton(lblChatBox);
            paneContent.getChildren().clear();
            Parent root = FXMLLoader.load(getClass().getResource("/scene/ChatBox.fxml"));
            paneContent.setOpacity(0);
            paneContent.getChildren().add(root);
            paneContentFade();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    private void paneContentFade()
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.8), paneContent);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


    private void fillDashboardButton(Label label)
    {
        lblProfile.setStyle("-fx-background-color:  #0F76BD");
        lblNewEmployee.setStyle("-fx-background-color:  #0F76BD");
        lblEmployees.setStyle("-fx-background-color:  #0F76BD");
        lblTeams.setStyle("-fx-background-color:  #0F76BD");
        lblSalaryManagement.setStyle("-fx-background-color:  #0F76BD");
        lblLeaveManagement.setStyle("-fx-background-color:  #0F76BD");
        lblChatBox.setStyle("-fx-background-color:  #0F76BD");

        label.setStyle("-fx-background-color: #ffffff");
    }//end fillDashboardButton



}//end class
