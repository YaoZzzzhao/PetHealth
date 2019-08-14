package com.ascending.training.jdbc;

import com.ascending.training.model.Dog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/project_db";
    static final String USER = "admin";
    static final String PASS = "kkmacs213";

    public List<Dog> getDogs(){
        List<Dog> dogs = new ArrayList<>();
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

                Dog dog = new Dog();
//                dog.setId(id);

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

    public Dog insertCat(Dog dog) {
//        List<Cat> cats = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Step 2. Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3. Execute a query
            System.out.println("Inserting statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Dogs(id, owner_id, cat_name, spay_neuter, deworm, " +
                    "Panleukopenia, Rhinotracheitis, Calici, Rabies) " +
                    "values(cat.getId(), cat.getOwnerId(), cat.getName(), cat.getSpayNeuter(), " +
                    "cat.getDeworm(), cat.getPanleukopenia(), cat.getRhinotracheitis(), " +
                    "cat.getCalici(), cat.getRabies()) ";
            rs = stmt.executeQuery(sql);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //STEP 6: finally block used to close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

            return dog;
        }
    }


    public static void main(String[] args){
        DogDao dogDao = new DogDao();
        List<Dog> dogs = dogDao.getDogs();

        for(Dog dog : dogs){
            System.out.println(dog.getName());
        }
    }

}


