package com.ascending.training.jdbc;

import com.ascending.training.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/project_db";
    static final String USER = "admin";
    static final String PASS = "kkmacs213";


    public List<Pet> getPets(){
        List<Pet> pets = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Step 2. Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3. Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM pets";
            rs = stmt.executeQuery(sql);

            //Step 4. Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String breed = rs.getString("breed");
                int age = rs.getInt("age");
                String color = rs.getString("color");
                String type = rs.getString("type");


                //Fill the object
                Pet pet = new Pet();
                pet.setId(id);
                pet.setName(name);
                pet.setBreed(breed);
                pet.setColor(color);
                pet.setAge(age);
                pet.setType(type);

                pets.add(pet);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
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

    public int insertPet(Pet pet) {
//        List<Pet> pets = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            //Step 2. Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3. Execute a query
            System.out.println("Inserting statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into pets(id, owner_id, name, type, color, breed, age) values(" + pet.getId() + ",'" + pet.getUser().getId() + ",'" + pet.getName() + "'," + "'" + pet.getType() + "'," + "'" + pet.getColor() + "'," + "'" + pet.getBreed() + "'," + "'" + pet.getAge() + "')";
            result = stmt.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //STEP 6: finally block used to close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

            return result;
        }
    }

    public int updatePet(Pet pet) {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            //Step 2. Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3. Execute a query
            System.out.println("Updating statement...");

            String sql;
            sql = "update pets set name=?, " +
                    "type=?, color=?, breed=?," +
                    " age=?" +
                    "where id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pet.getName());
            pstmt.setString(2, String.valueOf(pet.getType()));
            pstmt.setString(3, String.valueOf(pet.getColor()));
            pstmt.setString(4, String.valueOf(pet.getBreed()));
            pstmt.setString(5, String.valueOf(pet.getAge()));
            pstmt.setLong(6, pet.getId());


            result = stmt.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //STEP 6: finally block used to close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

            return result;
        }
    }



    public int deletePet(long id){
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            //Step 2. Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3. Execute a query
            System.out.println("Deleting statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete from pets where id = "+ id +";";
            result = stmt.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
