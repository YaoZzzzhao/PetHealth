package com.ascending.training.jdbc;

import com.ascending.training.model.Dogs;
import com.ascending.training.model.Pets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogsDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/project_db";
    static final String USER = "admin";
    static final String PASS = "kkmacs213";

    public List<Dogs> getDogs(){
        List<Dogs> dogs = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Dogs";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Dogs dog = new Dogs();
                dog.setId(id);
                dogs.add(dog);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }

        return dogs;
    }
//    public static void main(String[] args){
//        DogsDao dogDao = new DogsDao();
//        List<Dogs> dogs = dogDao.getDogs();
//
//        for(Dogs dog : dogs){
//            System.out.println(department.getName());
//        }
//    }

}


