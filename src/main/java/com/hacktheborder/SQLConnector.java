package com.hacktheborder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLConnector {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

   
    public SQLConnector() {
        String url = "jdbc:mysql://192.168.1.213:3306/secure_coding_database";
        //String url = "jdbc:mysql://hacktheborder.ddns.net:3306/secure_coding_database";
        //String url = "jdbc:mysql://10.37.19.27:3306/secure_coding_database";
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

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
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
