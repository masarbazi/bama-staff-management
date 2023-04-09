package controller.manager;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;
import controller.EasyDB;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;

public class Employees extends EasyDB {

    @FXML
    private TableView<EmployeeTableInfo> table;
    @FXML
    private TableColumn<EmployeeTableInfo, String> colName, colLastname, colPost, colPhone, colGmail, colTotalSalary;


    //my variables
    ResultSet info = null;
    ResultSet salary = null;



    public void initialize()
    {
        try
        {
            info = super.getTableResultSet("INFO");
            salary = super.getTableResultSet("SALARY");
            loadContent();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }//end initialize

    private void loadContent()
    {
         try
         {
             table.getItems().clear();
             colName.setCellValueFactory(new PropertyValueFactory<>("name"));
             colLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
             colPost.setCellValueFactory(new PropertyValueFactory<>("post"));
             colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
             colGmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
             colTotalSalary.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));

             while (info.next())
             {
                 String out_salary = getPostSalary(info.getString("post"));
                 EmployeeTableInfo employeeTableInfo = new EmployeeTableInfo(info.getString("name"), info.getString("lastname"),info.getString("post"), info.getString("phone"), info.getString("gmail"), out_salary);
                 table.getItems().add(employeeTableInfo);
             }//end while
         }
         catch (Exception e)
         {
             System.out.println(e.toString());
         }

    }//end loadContent



    private String getPostSalary(String post)
    {
        String result = "";
        try
        {
            salary.absolute(0);
            while (salary.next())
            {
                String currentPost = salary.getString("post");
                if(currentPost.equals(post))
                {
                    result = salary.getString("out_total");
                    break;
                }
            }//end while
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

        return result;
    }//end getPostSalary

}//end class
