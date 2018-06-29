package com.gmail.timurworkspace.dao;


import com.gmail.timurworkspace.components.Guest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectorToDb implements GuestDao {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static final String URL = "jdbc:mysql://localhost:3306/timur_base";
    private static final String userName = "root";
    private static final String password = "root";


    Connection connection;
    PreparedStatement statement;

    public ConnectorToDb() {
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL,userName,password);
    }

    private void closeAll(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Guest> findByAll() {
        List<Guest> guests = new ArrayList<Guest>();
        Guest guest = null;
        try {
            connection = createConnection();
            statement = connection.prepareStatement("SELECT * FROM guest");
            ResultSet set = statement.executeQuery();

            for (;set.next();){
                guest = new Guest();
                guest.setId(set.getInt("id"));
                guest.setName(set.getString("name"));
                guest.setLastName(set.getString("last_name"));
                guest.setAge(set.getInt("age"));
                guests.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return guests;
   }


    public Guest findById(int id) {
        Guest guest = null;

        try {
            connection = createConnection();
            statement = connection.prepareStatement("SELECT * FROM guest WHERE  id =?;");
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            for(;set.next();){
                guest = new Guest();
                guest.setId(set.getInt("id"));
                guest.setName(set.getString("name"));
                guest.setLastName(set.getString("last_name"));
                guest.setAge(set.getInt("age"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }

        return guest;
    }

    public void saveGuest(Guest guest) {
        try {
            connection = createConnection();
            statement = connection.prepareStatement("INSERT INTO guest(name, last_name, age) VALUES (?,?,?)");
            if(guest.getName()!= null && guest.getLastName() != null && guest.getAge() != 0) {
                statement.setString(1, guest.getName());
                statement.setString(2, guest.getLastName());
                statement.setInt(3, guest.getAge());
                statement.execute();
                ResultSet generatedKeys = statement.getGeneratedKeys();
                guest.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }

    }

    public void deleteGuest(int id) {

        try {
            connection = createConnection();
            statement = connection.prepareStatement("DELETE FROM guest WHERE id = ?;");
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeAll();
        }
    }
}
