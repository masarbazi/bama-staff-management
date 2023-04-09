package controller;

import java.sql.*;


public class EasyDB
{
    //variables
    Connection connection = null;
    private static EasyDB easyDB = null;

    String CONN = "jdbc:mysql://localhost/bama"; // enter database name after ...localhost/
    String USER = "user"; // enter database username
    String PASS = "user123"; // enter corresponding users password


    public EasyDB()
    {
        try
        {
            connection = DriverManager.getConnection(CONN, USER, PASS);

            print("Connected!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//end EasyDB


    public ResultSet getTableResultSet(String tableName) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        return  resultSet;
    }//end getNamesList


    public ResultSet getTableResultSet(String tableName, String column, String like) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + column + " = '" + like + "'");
        resultSet.next();
        return resultSet;
    }//end getTableResultSet


    public void executeQuery(String query) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
    }//end executeQuery


    public void updateSalary(String colName, String post, String value) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement("UPDATE SALARY SET " + colName + " = ? WHERE POST = ?");
        statement.setString(1, value);
        statement.setString(2, post);
        statement.executeUpdate();
        statement.close();
    }//end updateSalary


    public void updateSchedule(String colName, int id, String value) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement("UPDATE SCHEDULE SET " + colName + " = ? WHERE ID = ?");
        statement.setString(1, value);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }//end updateSalary


    public void updateLeave(String value, int id) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement("UPDATE SCHEDULE SET `leave` = ? WHERE `id` = ?");
        statement.setString(1, value);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }//end updateSalary


    public void insert(String table, String values) throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO " + table + " VALUES " + values);
        statement.close();
    }

    public void delete()
    {

    }

    private void print(String message)
    {
        System.out.println("EasyDB : " + message);
    }//end print









}//end class


