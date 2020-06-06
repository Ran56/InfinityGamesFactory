package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleDao {


    List<Console> consoles = new ArrayList();

    static final String DBURL = "jdbc:postgresql://localhost:5431/project";
    static final String USER = "ran";
    static final String PASSWORD = "password";

    private Logger logger = LoggerFactory.getLogger(ConsoleDao.class);


    public int save (Console console) {
        Connection conn = null;
        int r = 0;
        PreparedStatement ps = null;

        try {

            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);

            logger.info("Creating statement...");
            String sql = "INSERT INTO Consoles (consoleName, price, issueTime, color, developer, whatIncluded)" + "VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, console.getName());
            ps.setDouble(2, console.getPrice());
            ps.setString(3, console.getIssueTime());
            ps.setString(4, console.getColor());
            ps.setString(5, console.getDeveloper());
            ps.setString(6, console.getWhatIncluded());

            r = ps.executeUpdate();


        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {

            try {
                if (ps != null) { ps.close();}
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return r;
    }

    public int delete(String name){

        Connection conn = null;
        PreparedStatement ps = null;
        int r = 0;

        try {
            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");

            String sql = "DELETE FROM Consoles WHERE consoleName = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);

            r = ps.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (ps != null) ps.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return r;
    }

    public int update(String oldName, Console console){

        Connection conn = null;
        PreparedStatement ps = null;
        int r = 0;


        try {
            logger.debug("Connection to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");
            String sql = "UPDATE Consoles SET consoleName=?, price=?, issueTime=?, color=?, developer=?, whatIncluded=? WHERE consoleName=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,console.getName());
            ps.setDouble(2,console.getPrice());
            ps.setString(3,console.getIssueTime());
            ps.setString(4,console.getColor());
            ps.setString(5,console.getDeveloper());
            ps.setString(6,console.getWhatIncluded());
            ps.setString(7,oldName);

            r = ps.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (ps != null) ps.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return r;
    }

    public List<Console> getConsoles() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Consoles";
            rs = stmt.executeQuery(sql);

            logger.info("Converting data...");
            while (rs.next()) {

                Long id = rs.getLong("id");
                String name = rs.getString("consoleName");
                double price = rs.getDouble("price");
                String issueTime = rs.getString("issueTime");
                String color = rs.getString("color");
                String developer = rs.getString("developer");
                String whatIncluded = rs.getString("whatIncluded");


                Console console = new Console();
                console.setId(id);
                console.setName(name);
                console.setPrice(price);
                console.setIssueTime(issueTime);
                console.setColor(color);
                console.setDeveloper(developer);
                console.setWhatIncluded(whatIncluded);

                consoles.add(console);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return consoles;
    }
}
