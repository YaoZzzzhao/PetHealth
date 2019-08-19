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
            //Step 2. Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3. Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM dogs";
            rs = stmt.executeQuery(sql);

            //Step 4. Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("dog_name");
                char spay = rs.getString("Spay_neuter").charAt(0);
                char distemper = rs.getString("Distemper").charAt(0);
                char parvo = rs.getString("Parvo").charAt(0);
                char aden = rs.getString("Adenovirus").charAt(0);
                char bord = rs.getString("Bordetella").charAt(0);
                char rabies = rs.getString("Rabies").charAt(0);


                //Fill the object
                Dog dog = new Dog();
//                dog.setId(id);
                dog.setName(name);
                dog.setParvo(parvo);
                dog.setDistemper(distemper);
                dog.setAdenovirus(aden);
                dog.setBordetella(bord);
                dog.setSpayNeuter(spay);
                dog.setRabies(rabies);

                dogs.add(dog);
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

        return dogs;
    }

    public int insertDog(Dog dog) {
//        List<Dog> dogs = new ArrayList<>();
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
            sql = "insert into dogs(id, name, Spay_neuter, Rabies, Distemper, Parvo, Adenovirus, Bordetella) values(" + dog.getId() + ",'" + dog.getName() + "'," + "'" + dog.getSpayNeuter() + "'," + "'" + dog.getRabies() + "'," + "'" + dog.getDistemper() + "'," + "'" + dog.getParvo() + "'," + "'" + dog.getAdenovirus() + "'," + "'" + dog.getBordetella() + "')";
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

    public int updateDog(Dog dog) {
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
            sql = "update dogs set name=?, " +
                    "Spay_neuter=?, Rabies=?, Distemper=?," +
                    " Parvo?, Adenouvirus=?, Bordetella=? " +
                    "where id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dog.getName());
            pstmt.setString(2, String.valueOf(dog.getSpayNeuter()));
            pstmt.setString(3, String.valueOf(dog.getRabies()));
            pstmt.setString(5, String.valueOf(dog.getDistemper()));
            pstmt.setString(4, String.valueOf(dog.getParvo()));
            pstmt.setString(6, String.valueOf(dog.getAdenovirus()));
            pstmt.setString(7, String.valueOf(dog.getBordetella()));
            pstmt.setLong(8, dog.getId());


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



    public int deleteDog(long id){
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
            sql = "delete from dogs where id = "+ id +";";
            result = stmt.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}




