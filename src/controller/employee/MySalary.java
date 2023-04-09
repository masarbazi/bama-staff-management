package controller.employee;

import controller.EasyDB;
import data.CurrentEmployee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;

public class MySalary extends EasyDB {

    //fxml variables
    @FXML
    private Label lblPost, lblMonthSalary, lblHousing, lblOvertime, lblFamily, lblInsurance, lblChildren, lblMission, lblTax, lblTotal;

    CurrentEmployee currentEmployee = null;
    String post = "";

    public void initialize()
    {
        currentEmployee = CurrentEmployee.getInstance();
        post = currentEmployee.getPost();
        setupValues();
    }//end initialize


    private void setupValues()
    {
        try
        {
            ResultSet resultSet = super.getTableResultSet("SALARY", "post", currentEmployee.getPost());
            lblPost.setText(resultSet.getString("post"));
            lblMonthSalary.setText(resultSet.getString("month"));
            lblHousing.setText(resultSet.getString("housing"));
            lblOvertime.setText(resultSet.getString("overtime"));
            lblFamily.setText(resultSet.getString("family"));
            lblInsurance.setText(resultSet.getString("insurance"));
            lblChildren.setText(resultSet.getString("children"));
            lblMission.setText(resultSet.getString("mission"));
            lblTax.setText(resultSet.getString("tax"));
            lblTotal.setText(resultSet.getString("out_total"));
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }





}//end MySalary
