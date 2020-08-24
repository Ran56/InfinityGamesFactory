package com.infinity.gamesFactory.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameJDBCDao {




    List<Game> games = new ArrayList();

    static final String DBURL = System.getProperty("database.url");
    static final String USER = System.getProperty("database.user");
    static final String PASSWORD = System.getProperty("database.password");

    private Logger logger = LoggerFactory.getLogger(GameJDBCDao.class);


    public int save (Game game) {

        Connection conn = null;
        int r = 0;
        PreparedStatement ps = null;

        try {

            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);

            logger.info("Creating statement...");
            String sql = "INSERT INTO Games (name, price, genre, players, releaseTime, console_id, supportedLanguages)" + "VALUES (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, game.getName());
            ps.setDouble(2, game.getPrice());
            ps.setString(3, game.getGenre());
            ps.setString(4, game.getPlayers());
            ps.setDate(5, game.getReleaseTime());
            ps.setLong(6, game.getConsoleId());
            ps.setString(7, game.getSupportedLanguages());

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

            String sql = "DELETE FROM Games WHERE name = ?";
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

    public int update(String oldName, Game game){

        Connection conn = null;
        PreparedStatement ps = null;
        int r = 0;


        try {
            logger.debug("Connection to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");
            String sql = "UPDATE Games SET name=?, price=?, genre=?, players=?, releaseTime=?, console_id=?, supportedLanguages=? WHERE name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,game.getName());
            ps.setDouble(2,game.getPrice());
            ps.setString(3,game.getGenre());
            ps.setString(4,game.getPlayers());
            ps.setDate(5,game.getReleaseTime());
            ps.setLong(6,game.getConsoleId());
            ps.setString(7,game.getSupportedLanguages());
            ps.setString(8,oldName);

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

    public List<Game> getGames() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
            logger.info("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Games";
            rs = stmt.executeQuery(sql);

            logger.info("Converting data...");
            while (rs.next()) {

                Long id = rs.getLong("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String genre = rs.getString("genre");
                String players = rs.getString("players");
                Date releaseTime = rs.getDate("releaseTime");
                Long consoleId = rs.getLong("console_id");
                String supportedLanguages = rs.getString("supportedLanguages");


                Game game = new Game();
                game.setName(name);
                game.setPrice(price);
                game.setGenre(genre);
                game.setPlayers(players);
                game.setReleaseTime(releaseTime);
                game.setConsoleId(consoleId);
                game.setSupportedLanguages(supportedLanguages);

                games.add(game);

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
        return games;
    }




}
