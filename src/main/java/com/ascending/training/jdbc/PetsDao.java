package com.ascending.training.jdbc;

import com.ascending.training.model.Pets;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetsDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/project_db";
    static final String USER = "admin";
    static final String PASS = "kkmacs213";

    public List<Pets> getPets(){
        List<Pets> pets = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Pets";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                int owner_id = rs.getInt("owner_id");
                String name = rs.getString("pet_name");
                String type = rs.getString("type");
                String color = rs.getString("color");
                String breed = rs.getString("breed");
                Long age = rs.getLong("age");

                Pets pet = new Pets();
                pet.setId(id);
                pet.setName(name);
                pets.add(pet);
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

        return pets;
    }
    public static void main(String[] args){
        PetsDao petsDao = new PetsDao();
        List<Pets> pets = petsDao.getPets();

        for(Pets pet :pets ){
            System.out.println(pet.getName());
        }
    }

}

