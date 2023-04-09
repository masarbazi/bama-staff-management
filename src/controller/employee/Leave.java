package controller.employee;

import com.jfoenix.controls.JFXDatePicker;
import controller.EasyDB;
import data.CurrentEmployee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sun.util.resources.th.CalendarData_th;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

public class Leave extends EasyDB
{

    //FXML
    @FXML
    private AnchorPane anchMain;
    @FXML
    private JFXDatePicker dateFrom, dateTo;
    @FXML
    private Button btnSendRequest;
    @FXML
    private TableView<LeaveTable> table;
    @FXML
    private TableColumn<LeaveTable,String> colID, colFrom, colTo, colStatus;

    //variables
    int lastID = 1;
    String leaveLongText = "";
    CurrentEmployee currentEmployee = null;

    public void initialize()
    {
        try
        {
            currentEmployee = CurrentEmployee.getInstance();
            ResultSet resultSet = super.getTableResultSet("SCHEDULE");
            resultSet.absolute(currentEmployee.getId());
            String leaveLongText = resultSet.getString("leave");
            this.leaveLongText = leaveLongText;

            if(!leaveLongText.isEmpty())
                fillTable(leaveLongText);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end initialize


    private void fillTable(String leaveLongText)
    {
        table.getItems().clear();

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        String[] leaveInfo = leaveLongText.split(";");
        for(int i=0;i<leaveInfo.length;i++)
        {
            lastID++;
            String id = leaveInfo[i].split("!")[0];
            String from = leaveInfo[i].split("!")[1];
            String to = leaveInfo[i].split("!")[2];
            String status = leaveInfo[i].split("!")[3];

            LeaveTable leaveTable = new LeaveTable(id, from, to, status);
            table.getItems().add(leaveTable);

        }
    }//end fillTable

    @FXML
    private void sendRequest() throws SQLException
    {
        String from = dateFrom.getValue().toString();
        String to = dateTo.getValue().toString();

        //return example : from : 2020-06-03 to : 2020-06-05
        String newRequest = lastID + "!" + from + "!" + to + "!Under Investigation;";
        leaveLongText += newRequest;
        super.updateLeave(leaveLongText, currentEmployee.getId());
        System.out.println("Request Sent :)");
    }


}//end class
