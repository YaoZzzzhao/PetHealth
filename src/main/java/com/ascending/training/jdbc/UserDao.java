package com.ascending.training.jdbc;

import com.ascending.training.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
            sql = "SELECT * FROM User";
            rs = stmt.executeQuery(sql);

            //Step 4. Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("fullname");

                //Fill the object
                User user = new User();
//                user.setId(id);
                user.setName(name);
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

        logger.trace("Trace - User size" + users.size());
        logger.debug("Debug - User size" + users.size());
        logger.info("Info - User size" + users.size());
        logger.warn("Warn - User size" + users.size());
        logger.error("Error - User size" + users.size());

        logger.info("Exit the method getUsers");

        return users;

    }
    public static void main(String[] args){
        UserDao usersDao = new UserDao();
        List<User> users = usersDao.getUsers();

        for(User user : users){
            System.out.println(user.getName());
        }
    }

}


