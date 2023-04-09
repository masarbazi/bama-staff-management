package controller.manager;

import controller.EasyDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaveManagement extends EasyDB {

    //FXML
    @FXML
    private AnchorPane anchMain;
    @FXML
    private TableView<LeaveManagementTable> table;
    @FXML
    private TableColumn<LeaveManagementTable, String> colID,colName, colPhone, colGmail, colFrom, colTo;
    @FXML
    private Button btnAccept, btnReject;

    //variables

    public void initialize()
    {
        fillTable();
    }

    private void fillTable()
    {
        try
        {
            table.getItems().clear();
            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colGmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
            colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
            colTo.setCellValueFactory(new PropertyValueFactory<>("to"));

            ResultSet infoRS = super.getTableResultSet("INFO");
            ResultSet scheduleRS = super.getTableResultSet("SCHEDULE");
            while (scheduleRS.next())
            {
                String leaveLongText = scheduleRS.getString("leave");
                if(leaveLongText.contains("Under Investigation"))
                {
                    String[] leaveParts = leaveLongText.split(";");
                    for(int i=0;i<leaveParts.length;i++)
                    {
                        String thisPart = leaveParts[i];
                        int rowIndex = scheduleRS.getRow();
                        infoRS.absolute(rowIndex);
                        String fullname = infoRS.getString("name") + " " + infoRS.getString("lastname");
                        LeaveManagementTable leaveManagementTable = new LeaveManagementTable(String.valueOf(rowIndex), fullname, infoRS.getString("phone"), infoRS.getString("gmail"), thisPart.split("!")[1], thisPart.split("!")[2]);
                        table.getItems().add(leaveManagementTable);
                    }//end for
                }//
            }//end while
            infoRS.close();
            scheduleRS.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }//end fillTable


    @FXML
    private void updateStatus(MouseEvent event) throws SQLException
    {
        String status = "";
        if(event.getSource()==btnAccept)
            status = "Accepted";
        else if(event.getSource()==btnReject)
            status = "Rejected";

        if(!table.getSelectionModel().isEmpty())
        {
            try {
                LeaveManagementTable leaveManagementTable = table.getSelectionModel().getSelectedItem();
                int id = Integer.parseInt(leaveManagementTable.getId());
                ResultSet resultSet = super.getTableResultSet("SCHEDULE");
                while (resultSet.next())
                {
                    if(resultSet.getString("id").equals(String.valueOf(id)))
                    {
                        String old = resultSet.getString("leave");
                        String updated = old.replaceAll("Under Investigation", status);
                        super.updateLeave(updated, id);
                        System.out.println("Updated Leave Request Value");
                        break;
                    }
                }//end while
                fillTable();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        else
            System.out.println("Please Select A Row");

    }//end updateStatus




}
