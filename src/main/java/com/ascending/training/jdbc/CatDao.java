package com.ascending.training.jdbc;

import com.ascending.training.model.Cat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/project_db";
    static final String USER = "admin";
    static final String PASS = "kkmacs213";


    public List<Cat> getCats(){
        List<Cat> cats = new ArrayList<>();
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
            sql = "SELECT * FROM cats";
            rs = stmt.executeQuery(sql);

            //Step 4. Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("cat_name");
                char spay = rs.getString("Spay_neuter").charAt(0);
                char deworm = rs.getString("Deworm").charAt(0);
                char panl = rs.getString("Panl").charAt(0);
                char rhi = rs.getString("Rhi").charAt(0);
                char calici = rs.getString("Calici").charAt(0);
                char rabies = rs.getString("Rabies").charAt(0);


                //Fill the object
                Cat cat = new Cat();
                cat.setId(id);
                cat.setName(name);
                cat.setRhi(rhi);
                cat.setDeworm(deworm);
                cat.setPan(panl);
                cat.setCalici(calici);
                cat.setSpayNeuter(spay);
                cat.setRabies(rabies);

                cats.add(cat);
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

        return cats;
    }

    public int insertCat(Cat cat) {
//        List<Cat> cats = new ArrayList<>();
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
            sql = "insert into cats(id, owner_id, cat_name, spay_neuter, deworm, Panleukopenia, Rhinotracheitis, Calici, Rabies) values(" + cat.getId() + ",'" + cat.getName() + "'," + "'" + cat.getSpayNeuter() + "'," + "'" + cat.getDeworm() + "'," + "'" + cat.getPan() + "'," + "'" + cat.getRhi() + "'," + "'" + cat.getCalici() + "'," + "'" + cat.getRabies() + "')";
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

    public int updateCat(Cat cat) {
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
            sql = "update cats set cat_name=?, " +
                    "spay_neuter=?, deworm=?, Pan=?," +
                    " Rhi=?, Calici=?, Rabies=? " +
                    "where id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cat.getName());
            pstmt.setString(2, String.valueOf(cat.getSpayNeuter()));
            pstmt.setString(3, String.valueOf(cat.getDeworm()));
            pstmt.setString(5, String.valueOf(cat.getPan()));
            pstmt.setString(4, String.valueOf(cat.getRhi()));
            pstmt.setString(6, String.valueOf(cat.getCalici()));
            pstmt.setString(7, String.valueOf(cat.getRabies()));
            pstmt.setLong(8, cat.getId());


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



    public int deleteCat(long id){
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
            sql = "delete from cats where id = "+ id +";";
            result = stmt.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }

            return result;
        }
    }




