/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Model.Ledelsen;

import delfinen.Controller.Controller;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
 */
public class LedelsenKasserTest {

    public LedelsenKasserTest() {
    }

    String name = "";
    int age = 0;
    String email = "";
    int phoneNumber = 0;
    String city = "";
    int zipCode = 0;
    String address = "";
    boolean competitiveSwimmer = true;
    boolean active = true;
    LedelsenKasser kasser = new LedelsenKasser(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

    /**
     * Test of createPayment method, of class LedelsenKasser.
     */
    @Test
    public void testCreatePayment() throws Exception {
        System.out.println("createPayment");
        int choiceID = 17;
        LedelsenKasser instance = kasser;
        instance.createPayment(choiceID);
        LocalDate testDate = LocalDate.now();
        testDate = testDate.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String testDateStr = testDate.format(formatter);

        String query = "SELECT * FROM delfinen.kontingentbetaling WHERE ID = ?";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            boolean active = resultSet.getBoolean("active");
            int amount = resultSet.getInt("amount");
            boolean hasPaid = resultSet.getBoolean("hasPaid");
            String date = resultSet.getString("date");
            assertEquals(17, ID);
            assertEquals("Mogens Benson", name);
            assertEquals(65, age);
            assertEquals(true, active);
            assertEquals(1200, amount);
            assertEquals(false, hasPaid);
            assertEquals(testDateStr, date);
        }
        
        pstmt.close();
        myConnector.close();
        resultSet.close();

    }


    /**
     * Test of updateHasPaid method, of class LedelsenKasser.
     */
    @Test
    public void testUpdateHasPaid() throws Exception {
        System.out.println("updateHasPaid");
        int choiceID = 17;
        LedelsenKasser instance = kasser;
        instance.updateHasPaid(choiceID);
        
        
        String query = "SELECT * FROM delfinen.kontingentbetaling WHERE ID = ?";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();
        
        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            boolean getHasPaid = resultSet.getBoolean("hasPaid");
            assertEquals(true, getHasPaid);
            
            
        }
        
        resultSet.close();
        pstmt.close();
        myConnector.close();
        
    }


    /**
     * Test of removeHasPaid method, of class LedelsenKasser.
     */
    @Test
    public void testRemoveHasPaid() throws Exception {
        System.out.println("removeHasPaid");
        int choiceID = 17;
        LedelsenKasser instance = kasser;
        instance.removeHasPaid(choiceID);
        
        String query = "SELECT * FROM delfinen.kontingentbetaling WHERE ID = ?";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();
        
        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();
        
        while(resultSet.next()) {
            boolean testHasPaid = resultSet.getBoolean("hasPaid");
            assertEquals(false, testHasPaid);
        }
        
        resultSet.close();
        pstmt.close();
        myConnector.close();
        
        
    }


}
