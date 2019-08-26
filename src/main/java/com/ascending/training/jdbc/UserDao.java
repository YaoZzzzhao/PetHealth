package com.ascending.training.jdbc;

import com.ascending.training.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/project_db";
    static final String USER = "admin";
    static final String PASS = "kkmacs213";


    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
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
            sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);

            //Step 4. Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("full_name");
                String pwd = rs.getString("password");
                String email = rs.getString("email");
                Date rdt = rs.getDate("regis_date");
                String type = rs.getString("pet_type");
                int num = rs.getInt("pet_num");


                //Fill the object
                User user = new User();
                user.setId(id);
                user.setFullName(name);
                user.setPassword(pwd);
                user.setEmail(email);
                user.setPetType(type);
                user.setRegisDate(rdt);
                user.setPetNum(num);

                users.add(user);
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

        return users;
    }

    public int insertUser(User user) {
//        List<User> users = new ArrayList<>();
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
            sql = "insert into users(id, full_name, password, email, regis_date, pet_type, pet_num) values(" + user.getId() + ",'" + user.getFullName() + "'," + "'" + user.getPassword() + "'," + "'" + user.getEmail() + "'," + "'" + user.getRegisDate() + "'," + "'" + user.getPetType() + "'," + "'" + user.getPetNum()  + "')";
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

    public int updateUser(User user) {
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            //Step 2. Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3. Execute a query
            System.out.println("Updating statement...");
//            stmt = conn.createStatement();

            String sql;
            sql = "update users set full_name=?, " +
                    "password=?, email=?, regis_date=?," +
                    " pet_type=?, pet_num=?" +
                    "where id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, String.valueOf(user.getPassword()));
            pstmt.setString(3, String.valueOf(user.getEmail()));
            pstmt.setString(4, String.valueOf(user.getRegisDate()));
            pstmt.setString(5, String.valueOf(user.getPetType()));
            pstmt.setString(6, String.valueOf(user.getPetNum()));
            pstmt.setLong(7, user.getId());


            result = pstmt.executeUpdate(sql);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
//                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

            return result;
        }
    }



    public int deleteUser(long id){
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
            sql = "delete from users where id = "+ id +";";
            result = stmt.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}




