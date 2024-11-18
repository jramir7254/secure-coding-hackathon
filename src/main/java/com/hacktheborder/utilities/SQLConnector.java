package com.hacktheborder.utilities;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.hacktheborder.managers.ApplicationManager;


public class SQLConnector {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    private String url;

    //String url = "jdbc:mysql://192.168.1.213:3306/secure_coding_database";
    //String url = "jdbc:mysql://hacktheborder.ddns.net:3306/secure_coding_database";
    //String url = "jdbc:mysql://10.37.52.227:3306/secure_coding_database";
   


    public SQLConnector() {
        System.out.println("this the first thinf");
        url = "jdbc:mysql://192.168.1.213:3306/secure_coding_database";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String[] propertyValues = getProperties();
            connection = DriverManager.getConnection(url,  propertyValues[0], propertyValues[1]);
            System.out.println("Connected to the database!");
        
        } catch (Exception e) {
            //ApplicationManager.restartSQLConnection();
        }
    }



    public boolean retrySQLConnection(String url) {
        try {
            String[] propertyValues = getProperties();
            connection = DriverManager.getConnection(url,  propertyValues[0], propertyValues[1]);
            System.out.println("Connected to the database!");
            return true;
        
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
            return false;
        }
    }




    

    public String[] getProperties() throws Exception {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) 
                System.err.println("Unable to find config.properties");
            
            properties.load(input);
            return new String[] { properties.getProperty("database.user"), properties.getProperty("database.password") };

        } catch (Exception e) {
            throw new Exception();
        }
    }



    
    
    public boolean teamExist(String teamName) {

        try {
            String query = "SELECT COUNT(*) FROM Teams WHERE Team_Name = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, teamName.toUpperCase());
            resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getInt("count(*)") > 0;

        } catch (Exception e) {
            System.err.println("Problem checking if team exist.");
            return false;

        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (Exception e) {
                System.err.println("Error closing resources.");
            } 
        }
    }



    
    
    public void insertNewTeam(String lastName, int numMembers, int epccIdNumber) {

        try {
            String query = "INSERT INTO Teams VALUES (?, ?, ?, ?);";

            statement = connection.prepareStatement(query);
            statement.setString(1, lastName);
            statement.setInt(2, numMembers);
            statement.setInt(3, epccIdNumber);
            statement.setInt(4, 0);

            if (statement.executeUpdate() > 0) 
                System.out.println("A new row was inserted successfully!");
            
            System.out.println("Finished inserting new team.");
        } catch (SQLException e) {
            System.err.println("Error inserting new team.");

        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                System.err.println("Error closing resources.");
            } 
        }
    }



    
    
    public Team getTeam(String getTeamName) {

        try {
            String query = "SELECT * FROM Teams WHERE Team_Name = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, getTeamName.toUpperCase());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String teamName = resultSet.getString("Team_Name");
                int numMembers = resultSet.getInt("Num_Members");
                int idNum = resultSet.getInt("ID_Number");
                int teamScore = resultSet.getInt("Team_Score");
                System.out.printf("Team successfully retrieved\n\tteam name: %s\n\tnum members: %d\n\tid num: %d \n", teamName, numMembers, idNum, teamScore);
                return new Team(teamName, numMembers, idNum, teamScore);
            }

        } catch (SQLException e) {
            System.err.println("Error gettign team information.");

        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                System.err.println("Error closing resources.");
            } 
        }
        return null;
    }
}
