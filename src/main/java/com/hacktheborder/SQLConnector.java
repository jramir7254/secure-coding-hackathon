package com.hacktheborder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SQLConnector {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

   
    public SQLConnector() {
        //String url = "jdbc:mysql://192.168.1.203:3306/secure_coding_database";
        String url = "jdbc:mysql://10.37.19.27:3306/secure_coding_database";
        String user = "new_user";
        String password = "Qpzm()56";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
        
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Connection error: " + e.getMessage());
        }


    }

    public boolean userExist(int id) {
        try {

            String query = "SELECT COUNT(*) FROM PERSONS WHERE EPCC_ID_Number = " + id + ";";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
           
            return resultSet.getInt("count(*)") > 0;

        } catch (Exception e) {

            return false;

        } finally {

            try {

                resultSet.close();
                statement.close();

            } catch (Exception e) {

            } 

        }
    }

    public void createNewUser(String lastName, String firstName, int epccIdNumber) {
        try {
            String query = "INSERT INTO PERSONS VALUES (?, ?, ?);";

            statement = connection.prepareStatement(query);
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setInt(3, epccIdNumber);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row was inserted successfully!");
            }

            System.out.println("Finished Creating");

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("error creating");

        } finally {

            try {

                statement.close();

            } catch (Exception e) {

            } 

        }
    }


    public User getUser(int epccIdNumber) {
        try {
            String sql = "SELECT * FROM PERSONS WHERE EPCC_ID_Number = ?";
            String lastName = "", firstName = "";
            int id = -1;

            statement = connection.prepareStatement(sql);
            statement.setInt(1, epccIdNumber);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                lastName = resultSet.getString("Last_Name");
                firstName = resultSet.getString("First_Name");
                id = resultSet.getInt("EPCC_ID_Number");
            }

            System.out.println(lastName + " " + firstName + " " + id);
            return new User(lastName, firstName, id);

        } catch (Exception e) {
            return null;
        } finally {

            try {

                statement.close();
                resultSet.close();

            } catch (Exception e) {

            } 

        }
      
    }
}
