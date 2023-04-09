package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import data.CurrentEmployee;
import data.CurrentManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class LoginController extends EasyDB
{

    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField txtUsername, txtPassword;


    public void initialize()
    {

    }//end initialize

    @FXML
    private void loginUser()
    {
        String username = txtUsername.getText();
        String password = txtPassword.getText();


        if(!username.isEmpty() && !password.isEmpty())
        {
            try
            {
                ResultSet resultSet = super.getTableResultSet("INFO");
                resultSet.last();
                int lastRow = resultSet.getRow();
                resultSet.absolute(0);

                while (resultSet.next())
                {
                    String dbName = resultSet.getString("name") + " " + resultSet.getString("lastname");
                    String dbpassword = resultSet.getString("password");
                    int currentRow = resultSet.getRow();

                    if(username.equals(dbName) && password.equals(dbpassword))
                    {
                        int id = resultSet.getInt("id");
                        String post = resultSet.getString("post");
                        Parent root = null;

                        if(post.equals("CEO"))
                        {
                            CurrentManager currentManager = CurrentManager.getInstance();
                            currentManager.setId(id);
                            //open up Manager page
                            root = FXMLLoader.load(getClass().getResource("/scene/Manager.fxml"));
                        }
                        else
                        {
                            CurrentEmployee currentEmployee = CurrentEmployee.getInstance();
                            currentEmployee.setId(resultSet.getInt("id"));
                            currentEmployee.setPost(resultSet.getString("post"));
                            //open up Employee page
                            root = FXMLLoader.load(getClass().getResource("/scene/Employee.fxml"));
                        }
                        Stage stage = new Stage();
                        //stage.initStyle(StageStyle.TRANSPARENT);
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        break;
                    }
                    else if(currentRow == lastRow)
                        System.out.println("wrong password dudeeee!!!");

                }//end while
                resultSet.close();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        else
        {
            System.out.println("Print some message");
        }
    }//end loginUser


}//end class
