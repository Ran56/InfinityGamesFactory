package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.jdbc.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {




    List<Game> games = new ArrayList();

    static final String DBURL = "jdbc:postgresql://localhost:5431/project";
    static final String USER = "ran";
    static final String PASSWORD = "password";

    private Logger logger = LoggerFactory.getLogger(GameDao.class);


    public int save (Game game) {

        Connection conn = null;
        int r = 0;
        PreparedStatement ps = null;

        try {

            logger.debug("Connecting to a database...");
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);

            logger.info("Creating statement...");
            String sql = "INSERT INTO Games (gamesName, price, genre, players, releaseTime, supportPlatform, developer, supportedLanguages)" + "VALUES (?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, game.getName());
            ps.setDouble(2, game.getPrice());
            ps.setString(3, game.getGenre());
            ps.setString(4, game.getPlayers());
            ps.setString(5, game.getReleaseTime());
            ps.setString(6, game.getSupportPlatform());
            ps.setString(7, game.getDeveloper());
            ps.setString(8, game.getSupportedLanguages());

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

            String sql = "DELETE FROM Games WHERE gamesName = ?";
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
            String sql = "UPDATE Games SET gamesName=?, price=?, genre=?, players=?, releaseTime=?, supportPlatform=?, developer=?, supportedLanguages=? WHERE gamesName=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,game.getName());
            ps.setDouble(2,game.getPrice());
            ps.setString(3,game.getGenre());
            ps.setString(4,game.getPlayers());
            ps.setString(5,game.getReleaseTime());
            ps.setString(6,game.getSupportPlatform());
            ps.setString(7,game.getDeveloper());
            ps.setString(8,game.getSupportedLanguages());
            ps.setString(9,oldName);

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
                String name = rs.getString("gamesName");
                double price = rs.getDouble("price");
                String genre = rs.getString("genre");
                String players = rs.getString("players");
                String releaseTime = rs.getString("releaseTime");
                String supportPlatform = rs.getString("supportPlatform");
                String developer = rs.getString("developer");
                String supportedLanguages = rs.getString("supportedLanguages");


                Game game = new Game();
                game.setId(id);
                game.setName(name);
                game.setPrice(price);
                game.setGenre(genre);
                game.setPlayers(players);
                game.setReleaseTime(releaseTime);
                game.setSupportPlatform(supportPlatform);
                game.setDeveloper(developer);
                game.setSupportedLanguages(supportedLanguages);

                games.add(game);

            }
        } catch (SQLException e) {
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
        }
        return games;
    }




}
