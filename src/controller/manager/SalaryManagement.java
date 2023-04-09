package controller.manager;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import controller.EasyDB;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import sun.font.CompositeGlyphMapper;

import java.io.PipedOutputStream;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SalaryManagement extends EasyDB
{

    //FXML
    @FXML
    private AnchorPane anchMain;
    @FXML
    private Button btnChangeSalary, btnAddSalary, btnChangeSalaryPercentage, btnAdd, btnChange;
    @FXML
    private Pane paneAdd, paneChange, paneChangeForm, paneChangeSalaryPercentage;
    @FXML
    private TextField txtNewPost, txtNewDailyWage, txtNewDailyWork, txtNewMonth, txtNewHousing, txtNewFamily, txtNewInsurance, txtNewChildren, txtNewMission, txtNewTax, txtTotalIncome, txtNewDeadInsurance, txtNewDeadTax, txtNewDeadLoan, txtNewTotalDead, txtTotalOut, txtDailyWage, txtDailyWork, txtHousing, txtFamily, txtInsurance, txtChildren, txtMission, txtTax, txtDeadInsurance, txtDeadTax, txtDeadLoan, txtMonth, txtPercent;
    @FXML
    private ComboBox<String> comboTarget, comboPost, comboMode;
    @FXML
    private Label lblIncreasement;



    //my variables


    public void initialize()
    {

        btnChangeSalary.setDisable(true);
        btnChangeSalaryPercentage.setDisable(false);
        btnAddSalary.setDisable(false);

        try
        {
            comboPost.getItems().add("All");
            comboMode.getItems().addAll("Increase", "Decrease");
            ResultSet resultSet = super.getTableResultSet("SALARY");
            while (resultSet.next())
            {
                comboPost.getItems().add(resultSet.getString(("post")));
                comboTarget.getItems().add(resultSet.getString("post"));
            }//end while
            resultSet.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }//end initialize



    @FXML
    private void changePane(MouseEvent event)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));

        if(event.getSource() == btnAddSalary)
        {
            fadeTransition.setNode(paneAdd);
            paneAdd.setVisible(true);
            paneChange.setVisible(false);
            paneChangeSalaryPercentage.setVisible(false);
            paneChange.setOpacity(0);
            paneAdd.setOpacity(0);
            paneChangeSalaryPercentage.setOpacity(0);
            btnAddSalary.setDisable(true);
            btnChangeSalary.setDisable(false);
            btnChangeSalaryPercentage.setDisable(false);
        }
        else if(event.getSource() == btnChangeSalary)
        {
            fadeTransition.setNode(paneChange);
            paneAdd.setVisible(false);
            paneChange.setVisible(true);
            paneChangeSalaryPercentage.setVisible(false);
            paneChange.setOpacity(0);
            paneAdd.setOpacity(0);
            paneChangeSalaryPercentage.setOpacity(0);
            btnAddSalary.setDisable(false);
            btnChangeSalary.setDisable(true);
            btnChangeSalaryPercentage.setDisable(false);
        }
        else if(event.getSource() == btnChangeSalaryPercentage)
        {
            fadeTransition.setNode(paneChangeSalaryPercentage);
            paneAdd.setVisible(false);
            paneChange.setVisible(false);
            paneChangeSalaryPercentage.setVisible(true);
            paneChangeSalaryPercentage.setOpacity(0);
            paneAdd.setOpacity(0);
            paneChange.setOpacity(0);
            btnAddSalary.setDisable(false);
            btnChangeSalary.setDisable(false);
            btnChangeSalaryPercentage.setDisable(true);
        }

        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }//end changePane


    @FXML
    private void comboChange() throws SQLException
    {
        Operate operate = new Operate();
        String selectedItem = comboTarget.getValue();
        ResultSet resultSet = super.getTableResultSet("SALARY", "post", selectedItem);
        txtDailyWage.setText(resultSet.getString("daily_wage"));
        txtDailyWork.setText(resultSet.getString("daily_work"));
        txtHousing.setText(resultSet.getString("housing"));
        txtFamily.setText(resultSet.getString("family"));
        txtInsurance.setText(resultSet.getString("insurance"));
        txtChildren.setText(resultSet.getString("children"));
        txtMission.setText(resultSet.getString("mission"));
        txtTax.setText(resultSet.getString("tax"));
        String dailyWage = txtDailyWage.getText();
        txtMonth.setText(addNumberComma(operate.multiply(dailyWage.replaceAll(",",""), "30")));

        txtDeadInsurance.setText(resultSet.getString("d_insurance"));
        txtDeadTax.setText(resultSet.getString("d_tax"));
        txtDeadLoan.setText(resultSet.getString("d_loan"));
        resultSet.close();
    }//end comboChange


    @FXML
    private void changeSalary()
    {
        Operate operate = new Operate();

        String post = comboTarget.getValue();

        //new values
        String dailyWage = addNumberComma(txtDailyWage.getText());
        //month was calculated when loading while
        String month = txtMonth.getText();
        String dailyWork = txtDailyWork.getText();
        String housing = addNumberComma(txtHousing.getText());
        String family = addNumberComma(txtFamily.getText());
        String insurance = addNumberComma(txtInsurance.getText());
        String children = addNumberComma(txtChildren.getText());
        String mission =  addNumberComma(txtMission.getText());
        String tax = addNumberComma(txtTax.getText());

        String d_insurance = addNumberComma(txtDeadInsurance.getText());
        String d_tax = addNumberComma(txtDeadTax.getText());
        String d_loan = addNumberComma(txtDeadLoan.getText());

        String tmp = "";
        tmp = operate.sum(d_insurance.replaceAll(",", ""), d_loan.replaceAll(",", ""));
        tmp = operate.sum(tmp, d_tax.replaceAll(",", ""));
        String d_total = tmp;

        //sum income
        tmp = operate.sum(month.replaceAll(",", ""), housing.replaceAll(",", ""));
        tmp = operate.sum(tmp, family.replaceAll(",", ""));
        tmp = operate.sum(tmp, insurance.replaceAll(",", ""));
        tmp = operate.sum(tmp, children.replaceAll(",", ""));
        tmp = operate.sum(tmp, mission.replaceAll(",", ""));
        tmp = operate.sum(tmp, tax.replaceAll(",", ""));
        String in_total = tmp;

        //out total
        BigInteger first = new BigInteger(in_total);
        BigInteger second = new BigInteger(d_total);
        String out_total = String.valueOf(first.subtract(second));
        out_total = addNumberComma(out_total);
        in_total = addNumberComma(in_total);
        d_total = addNumberComma(d_total);


        //update salary
        try
        {
            super.updateSalary("DAILY_WAGE", post, dailyWage);
            super.updateSalary("DAILY_WORK", post, dailyWork);
            super.updateSalary("MONTH", post, month);
            super.updateSalary("HOUSING", post, housing);
            super.updateSalary("FAMILY", post, family);
            super.updateSalary("INSURANCE", post, insurance);
            super.updateSalary("CHILDREN", post, children);
            super.updateSalary("MISSION", post, mission);
            super.updateSalary("TAX", post, tax);
            super.updateSalary("IN_TOTAL", post, in_total);
            super.updateSalary("D_INSURANCE", post, d_insurance);
            super.updateSalary("D_TAX", post, d_tax);
            super.updateSalary("D_LOAN", post, d_loan);
            super.updateSalary("D_TOTAL", post, d_total);
            super.updateSalary("OUT_TOTAL", post, out_total);

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }//end changeSalary


    @FXML
    private void updateMonth()
    {
        try
        {
            Operate operate = new Operate();
            String dailyWage = txtDailyWage.getText();
            txtMonth.setText(addNumberComma(operate.multiply(dailyWage.replaceAll(",",""), "30")));//3,000
        }
        catch (Exception ignored)
        { }
    }//end updateMonth


    private String addNumberComma(String input)
    {
        input = input.replaceAll(",", "");
        String result = "";

        for(int i=0;i<input.length();i++)
        {
            result += input.charAt(i);

            if((input.length()-i-1)%3==0 && (input.length()-i-1)!=0)
                result += ",";
        }//end for

        return result;
    }//end addNumberComma


    @FXML
    private void addSalary() throws SQLException
    {
        String post = txtNewPost.getText();
        String dailyWage = addNumberComma(txtNewDailyWage.getText().replaceAll(",",""));
        String dailyWork = txtNewDailyWork.getText();
        String overtime = "00:00";
        String month = txtNewMonth.getText();
        String family = addNumberComma(txtNewFamily.getText().replaceAll(",",""));
        String housing = addNumberComma(txtNewHousing.getText().replaceAll(",",""));
        String insurance = addNumberComma(txtNewInsurance.getText().replaceAll(",",""));
        String children = addNumberComma(txtNewChildren.getText().replaceAll(",",""));
        String mission = addNumberComma(txtNewMission.getText().replaceAll(",",""));
        String tax = addNumberComma(txtNewTax.getText().replaceAll(",",""));
        String d_insurance = addNumberComma(txtNewDeadInsurance.getText().replaceAll(",",""));
        String d_tax = addNumberComma(txtNewDeadTax.getText().replaceAll(",",""));
        String d_loan = addNumberComma(txtNewDeadLoan.getText().replaceAll(",",""));
        String in_total = txtTotalIncome.getText();
        String d_total = txtNewTotalDead.getText();
        String out_total = txtTotalOut.getText();

        //now add values to database
        super.insert("SALARY", "('" + post + "', '" + dailyWage + "', '" + dailyWork + "', '" + overtime + "', '" + month + "', '" + housing + "', '" + family + "', '" + insurance + "', '" + children + "', '" + mission + "', '" + tax + "', '" + in_total + "', '" + d_insurance + "', '" + d_tax + "', '" + d_loan + "', '" + d_total + "', '" + out_total + "')");
        System.out.println("new salary added");

    }//end addSalary


    @FXML
    private void updateMonthAddSalary()
    {
        try
        {
            Operate operate = new Operate();
            String dailyWage = txtNewDailyWage.getText();
            txtNewMonth.setText(addNumberComma(operate.multiply(dailyWage.replaceAll(",",""), "30")));
        }
        catch (Exception ignored)
        { }
    }//end updateMonth


    @FXML
    private void updateInTotal()
    {
        try
        {
            String dailyWage = txtNewDailyWage.getText();
            String month = txtNewMonth.getText();
            String family = txtNewFamily.getText();
            String housing = txtNewHousing.getText();
            String insurance = txtNewInsurance.getText();
            String children = txtNewChildren.getText();
            String mission = txtNewMission.getText();
            String tax = txtNewTax.getText();

            Operate operate = new Operate();

            //sum income
            String tmp = "";
            tmp = operate.sum(month.replaceAll(",", ""), housing.replaceAll(",", ""));
            tmp = operate.sum(tmp, family.replaceAll(",", ""));
            tmp = operate.sum(tmp, insurance.replaceAll(",", ""));
            tmp = operate.sum(tmp, children.replaceAll(",", ""));
            tmp = operate.sum(tmp, mission.replaceAll(",", ""));
            tmp = operate.sum(tmp, tax.replaceAll(",", ""));
            String in_total = tmp;

            txtTotalIncome.setText(addNumberComma(in_total.replaceAll(",","")));
            updateOutTotal();
        }
        catch (Exception ignored)
        { }
    }//end updateMonth


    @FXML
    private void updateDeadTotal()
    {
        try
        {
            Operate operate = new Operate();

            String d_insurance = txtNewDeadInsurance.getText();
            String d_tax = txtNewDeadTax.getText();
            String d_loan = txtNewDeadLoan.getText();

            //sum deduction
            String tmp = "";
            tmp = operate.sum(d_insurance.replaceAll(",", ""), d_loan.replaceAll(",", ""));
            tmp = operate.sum(tmp, d_tax.replaceAll(",", ""));
            String d_total = tmp;

            txtNewTotalDead.setText(addNumberComma(d_total.replaceAll(",","")));
            updateOutTotal();
        }
        catch (Exception ignored)
        { }
    }//end updateMonth


    private void updateOutTotal()
    {
        try
        {

            String in_total = txtTotalIncome.getText();
            String d_total = txtNewTotalDead.getText();

            //out total
            BigInteger first = new BigInteger(in_total.replaceAll(",",""));
            BigInteger second = new BigInteger(d_total.replaceAll(",",""));
            String out_total = String.valueOf(first.subtract(second));

            txtTotalOut.setText(addNumberComma(out_total));
        }
        catch (Exception ignored)
        { }
    }//end updateMonth


    @FXML
    private void txtPercentKeyRelease()
    {
        try
        {
            double percent = Double.parseDouble(txtPercent.getText());

            ResultSet resultSet = super.getTableResultSet("Salary", "post", comboPost.getValue());

            long oldMonth = getLong(resultSet.getString("month"));
            long dif = (long)(percent * oldMonth) / 100;
            lblIncreasement.setText((oldMonth + dif) + "");

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }


    }//end txtPercentKeyRelease

    @FXML
    private void btnSaveClicked()
    {
        try
        {
            double percent = Double.parseDouble(txtPercent.getText());


            ResultSet resultSet = super.getTableResultSet("Salary", "post", comboPost.getValue());

            long oldMonth = getLong(resultSet.getString("month"));
            long dif = (long)(percent * oldMonth) / 100;
            long newMonth,newSalary,newIncomeTotal,newDailyWage;

            if(comboMode.getValue().equals("Increase"))
            {
                newMonth = oldMonth + dif;
                newSalary = getLong(resultSet.getString("out_total")) + (dif*12);
                newIncomeTotal = getLong(resultSet.getString("in_total")) + (dif*12);
                newDailyWage = newMonth / 30;
            }
            else
            {
                newMonth = oldMonth - dif;
                newSalary = getLong(resultSet.getString("out_total")) - (dif*12);
                newIncomeTotal = getLong(resultSet.getString("in_total")) - (dif*12);
                newDailyWage = newMonth / 30;
            }

            super.updateSalary("daily_wage", comboPost.getValue(), String.valueOf(newDailyWage));
            super.updateSalary("month", comboPost.getValue(), String.valueOf(newMonth));
            super.updateSalary("out_total", comboPost.getValue(), String.valueOf(newSalary));
            super.updateSalary("in_total", comboPost.getValue(), String.valueOf(newIncomeTotal));
            System.out.println("Edited");
            txtPercent.setText("");

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }//end btnSaveClicked


    private long getLong(String input)
    {
        input = input.replaceAll(" ", "");
        input = input.replaceAll(",", "");

        return Long.parseLong(input);
    }//end getLong





}//end class
