/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Model.Ledelsen;

import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
 */
public class LedelsenTrænerTest {

    String name = "test";
    int age = 15;
    String email = "test@mail.dk";
    int phoneNumber = 12345678;
    String city = "testby";
    int zipCode = 1234;
    String address = "testvej 123";
    boolean competitiveSwimmer = false;
    boolean active = false;
    LedelsenTræner træner = new LedelsenTræner(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

    public LedelsenTrænerTest() {
    }

    /**
     * Her testes af svømmehold-DB kan vises. Vi bruger kun ID 1 til at bevise testens udfald.
     */
    @Test
    public void testShowSvømmehold() throws Exception {
        System.out.println("showSv\u00f8mmehold");
        int choiceID = 1;
        LedelsenTræner instance = træner;
        instance.showSvømmehold();
        
        String query = "SELECT * FROM delfinen.svømmehold WHERE swimmerID = ?";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            int getTeamID = resultSet.getInt("teamID");
            String getTeamName = resultSet.getString("teamName");
            String getTrainer = resultSet.getString("trainer");
            int getSwimmerAge = resultSet.getInt("swimmerAge");
            assertEquals(1, getTeamID);
            assertEquals("Ungdomshold", getTeamName);
            assertEquals("Erik Nielsen", getTrainer);
            assertEquals(16, getSwimmerAge);
            

        }

        pstmt.close();
        myConnector.close();
        resultSet.close();
    }

    /**
     * Her tester vi top 1 fra seniorholdet i disciplinen crawl.
     */
    @Test
    public void testShowTopFive() throws Exception {
        System.out.println("showTopFive");
        // choiceID 2 er seniorholdet:
        int choiceID = 2;
        LedelsenTræner instance = træner;
        
        String query = "SELECT s.teamName, s.swimmerID, s.swimmerAge, t.crawlTime "
                + "FROM delfinen.svømmehold s "
                + "INNER JOIN delfinen.træningsresultater t "
                + "ON t.swimmerID = s.swimmerID "
                + "WHERE s.teamID = ? ORDER BY t.crawlTime ASC LIMIT 1";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            String getTeamName = resultSet.getString("s.teamName");
            int getSwimmerID = resultSet.getInt("s.swimmerID");
            int getSwimmerAge = resultSet.getInt("s.swimmerAge");
            double getCrawlTime = resultSet.getDouble("t.crawlTime");
            assertEquals("Seniorhold", getTeamName);
            assertEquals(10, getSwimmerID);
            assertEquals(28, getSwimmerAge);
            assertEquals(36.237, getCrawlTime, 0.001);
            

        }

        pstmt.close();
        myConnector.close();
        resultSet.close();
        
    }

}
