package com.hacktheborder.utilities;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class SQLConnector {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement statement;

    private String user, password;
    private static String databaseURL = "jdbc:mysql://192.168.1.213:3306/secure_coding_database";


    public static void setURL(String ipAddr) {

        databaseURL = "jdbc:mysql://"+ ipAddr + ":3306/secure_coding_database";
        System.out.println(databaseURL);
    }



    public static void trySQLConnection() throws SQLException {
        new SQLConnector().testConnection();
    }
    


    public void testConnection() throws SQLException {
        connection = DriverManager.getConnection(databaseURL,  user, password);
        connection.close();
    }
   


    public SQLConnector() {
        //url = "jdbc:mysql://192.168.1.213:3306/secure_coding_database";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String[] propertyValues = getProperties();
            user = propertyValues[0];
            password = propertyValues[1];  
        } catch (Exception e) {
            
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public void startConnection() {
        try {
            connection = DriverManager.getConnection(databaseURL,  user, password);
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }


    public List<String[]> getTopFive() {
        try {
            String query = "SELECT * FROM TEAMS ORDER BY Team_High_Score DESC LIMIT 5";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            List<String[]> topTeams = new ArrayList<>();
            while (resultSet.next()) {
                String teamName = resultSet.getString("Team_Name");
                int teamScore = resultSet.getInt("Team_High_Score");
                topTeams.add(new String[] {teamName, teamScore +""});
            }
            return topTeams;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.err.println("Error");
            }
        }
    }


    public void updateTeamCurrentScore(int score, String teamName) {
        try {
            System.out.println("updating");
            System.out.println(teamName);
            String query = "UPDATE Teams SET Current_Team_Score = ? WHERE Team_Name = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, score);
            statement.setString(2, teamName);

            if (statement.executeUpdate() == 1) 
                System.out.println("Finished Updating Score");
            
            //System.out.println("Finished inserting new team.");
        } catch (Exception e) {

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.err.println("Error");
            }
        }
    }

    public void resetTeamCurrentScore(String teamName) {
        try {
            System.out.println("updating");
            System.out.println(teamName);
            String query = "UPDATE Teams SET Current_Team_Score = ? WHERE Team_Name = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, 0);
            statement.setString(2, teamName);

            if (statement.executeUpdate() == 1) 
                System.out.println("Finished Updating Score");
            
            //System.out.println("Finished inserting new team.");
        } catch (Exception e) {

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.err.println("Error");
            }
        }
    }

    public void updateTeamHighScore(int score, String teamName) {
        try {
            System.out.println("updating");
            System.out.println(teamName);
            String query = "UPDATE Teams SET Team_High_Score = ? WHERE Team_Name = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, score);
            statement.setString(2, teamName);

            if (statement.executeUpdate() == 1) 
                System.out.println("Finished Updating Score");
            
            //System.out.println("Finished inserting new team.");
        } catch (Exception e) {

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.err.println("Error");
            }
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
                connection.close();
            } catch (Exception e) {
                System.err.println("Error closing resources.");
            } 
        }
    }



    
    
    public void insertNewTeam(String lastName, int numMembers, int epccIdNumber) {

        try {
            String query = "INSERT INTO Teams VALUES (?, ?, ?, ?, ?);";

            statement = connection.prepareStatement(query);
            statement.setString(1, lastName);
            statement.setInt(2, numMembers);
            statement.setInt(3, epccIdNumber);
            statement.setInt(4, 0);
            statement.setInt(5, 0);

            if (statement.executeUpdate() > 0) 
                System.out.println("A new row was inserted successfully!");
            
            System.out.println("Finished inserting new team.");
        } catch (SQLException e) {
            System.err.println("Error inserting new team.");

        } finally {
            try {
                statement.close();
                connection.close();
                
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
                int teamHighScore = resultSet.getInt("Team_High_Score");
                System.out.printf("Team successfully retrieved\n\tteam name: %s\n\tnum members: %d\n\tid num: %d \n", teamName, numMembers, idNum, teamHighScore);
                return new Team(teamName, numMembers, idNum, teamHighScore);
            }

        } catch (SQLException e) {
            System.err.println("Error gettign team information.");

        } finally {
            try {
                statement.close();
                resultSet.close();
                connection.close();
            } catch (Exception e) {
                System.err.println("Error closing resources.");
            } 
        }
        return null;
    }
}
