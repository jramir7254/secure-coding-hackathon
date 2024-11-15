package com.hacktheborder;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class SQLConnector {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    //String url = "jdbc:mysql://192.168.1.213:3306/secure_coding_database";
    //String url = "jdbc:mysql://hacktheborder.ddns.net:3306/secure_coding_database";
    //String url = "jdbc:mysql://10.37.52.227:3306/secure_coding_database";
   




    public SQLConnector() {
        String url = "jdbc:mysql://192.168.1.213:3306/secure_coding_database";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String[] propertyValues = getProperties();
            connection = DriverManager.getConnection(url,  propertyValues[0], propertyValues[1]);
            System.out.println("Connected to the database!");
        
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Connection error: " + e.getMessage());
        }
    }





    

    public String[] getProperties() throws Exception {
        Properties properties = new Properties();
    
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) 
                System.out.println("Sorry, unable to find config.properties");
            
            properties.load(input);
            return new String[] { properties.getProperty("database.name"), properties.getProperty("database.password") };

        } catch (Exception e) {
            throw new Exception();
        }
    }





    
    
    public boolean teamExist(String teamName) throws SQLException {
        try {

            String query = "SELECT COUNT(*) FROM Teams WHERE Team_Name = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, teamName.toUpperCase());
            resultSet = statement.executeQuery();
            resultSet.next();
           
            return resultSet.getInt("count(*)") > 0;

        } catch (Exception e) {

           throw new SQLException();

        } finally {

            try {

                resultSet.close();
                statement.close();

            } catch (Exception e) {

            } 

        }
    }





    
    
    public void insertNewTeam(String lastName, int numMembers, int epccIdNumber) throws SQLException {
        try {
            String query = "INSERT INTO Teams VALUES (?, ?, ?, ?);";

            statement = connection.prepareStatement(query);
            statement.setString(1, lastName);
            statement.setInt(2, numMembers);
            statement.setInt(3, epccIdNumber);
            statement.setInt(4, 0);

     
            if (statement.executeUpdate() > 0) {
                System.out.println("A new row was inserted successfully!");
            }

            System.out.println("Finished Creating");

        } catch (SQLException e) {
     
            e.printStackTrace();
            System.out.println("error creating");
            throw new SQLException();

        } finally {

            try {

                statement.close();

            } catch (Exception e) {

            } 

        }
    }





    
    
    public Team getTeam(String getTeamName) {
        try {
            String teamName = null;
            int numMembers = -1, idNum = -1, teamScore = -1;

            String query = "SELECT * FROM Teams WHERE Team_Name = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, getTeamName.toUpperCase());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                teamName = resultSet.getString("Team_Name");
                numMembers = resultSet.getInt("Num_Members");
                idNum = resultSet.getInt("ID_Number");
                teamScore = resultSet.getInt("Team_Score");
                
            }
            System.out.printf("Inside SQLConnection %s, %d, %d", teamName, numMembers, idNum, teamScore);
            return new Team(teamName, numMembers, idNum, teamScore);

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
