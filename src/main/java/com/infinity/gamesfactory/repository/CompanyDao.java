package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {

    List<Company> companies = new ArrayList();

    static final String DBURL = "jdbc:postgresql://localhost:5431/project";
    static final String USER = "ran";
    static final String PASSWORD = "password";

    private Logger logger = LoggerFactory.getLogger(CompanyDao.class);


    public int save (Company company) {
        Connection conn = null;
        int r = 0;
        PreparedStatement ps = null;

        try {

            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);

            logger.info("Creating statement...");
            String sql = "INSERT INTO Companies (companyName, industry, description, number, location, webPageAddress)" + "VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, company.getName());
            ps.setString(2, company.getIndustry());
            ps.setString(3, company.getDescription());
            ps.setLong(4, company.getNumber());
            ps.setString(5, company.getLocation());
            ps.setString(6, company.getWebAddress());

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
            logger.debug("Connection to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");

            String sql = "DELETE FROM Companies WHERE companyName = ?";
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

    public int update(String oldName, Company company){

        Connection conn = null;
        PreparedStatement ps = null;
        int r = 0;


        try {
            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");
            String sql = "UPDATE Companies SET companyName=?, industry=?, description=?, number=?, location=?, webPageAddress=? WHERE companyName=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,company.getName());
            ps.setString(2,company.getIndustry());
            ps.setString(3,company.getDescription());
            ps.setLong(4,company.getNumber());
            ps.setString(5,company.getLocation());
            ps.setString(6,company.getWebAddress());
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

    public List<Company> getCompanies(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Companies";
            rs = stmt.executeQuery(sql);

            logger.info("Converting data...");
            while(rs.next()) {

                Long id  = rs.getLong("id");
                String name = rs.getString("companyName");
                String industry = rs.getString("industry");
                String description = rs.getString("description");
                long number = rs.getLong("number");
                String location = rs.getString("location");
                String webPageAddress = rs.getString("webPageAddress");


                Company company = new Company();
                company.setId(id);
                company.setName(name);
                company.setIndustry(industry);
                company.setDescription(description);
                company.setNumber(number);
                company.setLocation(location);
                company.setWebAddress(webPageAddress);

                companies.add(company);

            }
        }
        catch(SQLException e){
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
        return companies;
    }







}
