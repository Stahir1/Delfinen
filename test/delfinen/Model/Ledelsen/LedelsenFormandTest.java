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
public class LedelsenFormandTest {

    public LedelsenFormandTest() {
    }

    /**
     * Her tester vi at man kan tilføje til databasen og at det gemmes i den.
     */
    @Test
    public void testAddMemberToDB() throws Exception {
        System.out.println("addMemberToDB");
        String name = "test";
        int age = 15;
        String email = "test@mail.dk";
        int phoneNumber = 12345678;
        String city = "testby";
        int zipCode = 1234;
        String address = "testvej 123";
        boolean competitiveSwimmer = false;
        boolean active = false;
        LedelsenFormand formand = new LedelsenFormand(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
        LedelsenFormand instance = formand;
        instance.addMemberToDB(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        String query = "SELECT * FROM delfinen.medlemmer ORDER BY ID DESC LIMIT 1";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);
        resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            int getID = resultSet.getInt("ID");
            String getName = resultSet.getString("name");
            int getAge = resultSet.getInt("age");
            String getEmail = resultSet.getString("email");
            int getPhoneNumber = resultSet.getInt("phoneNumber");
            String getCity = resultSet.getString("City");
            int getZipCode = resultSet.getInt("zipCode");
            String getAddress = resultSet.getString("address");
            boolean getCompSwimmer = resultSet.getBoolean("competitiveSwimmer");
            boolean getActive = resultSet.getBoolean("active");
            assertEquals(21, getID);
            assertEquals("test", getName);
            assertEquals(15, getAge);
            assertEquals("test@mail.dk", getEmail);
            assertEquals(12345678, getPhoneNumber);
            assertEquals("testby", getCity);
            assertEquals(1234, getZipCode);
            assertEquals("testvej 123", getAddress);
            assertEquals(false, getCompSwimmer);
            assertEquals(false, getActive);

        }

        pstmt.close();
        myConnector.close();
        resultSet.close();

    }

    /**
     * Her tester vi en navneændring.
     */
    @Test
    public void testUpdateMemberInDB() throws Exception {
        System.out.println("updateMemberInDB");
        int choiceSubject = 1;
        int choiceID = 21;
        String name = "testUpdate";
        int age = 0;
        String email = "";
        int phoneNumber = 0;
        String city = "";
        int zipCode = 0;
        String address = "";
        boolean competitiveSwimmer = false;
        boolean active = false;
        LedelsenFormand formand = new LedelsenFormand(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
        LedelsenFormand instance = formand;
        instance.updateMemberInDB(choiceSubject, choiceID, name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        String query = "SELECT * FROM delfinen.medlemmer WHERE ID = ?";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            String getName = resultSet.getString("name");
            assertEquals("testUpdate", getName);
        }

        pstmt.close();
        myConnector.close();
        resultSet.close();

    }

    /**
     * Her testes at man kan få data fra databasen ved at give et ID som input.
     */
    @Test
    public void testGetMembersFromDBByID() throws Exception {
        System.out.println("getMembersFromDBByID");
        int choiceID = 17;
        String name = "";
        int age = 0;
        String email = "";
        int phoneNumber = 0;
        String city = "";
        int zipCode = 0;
        String address = "";
        boolean competitiveSwimmer = true;
        boolean active = true;
        LedelsenFormand formand = new LedelsenFormand(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
        LedelsenFormand instance = formand;
        instance.getMembersFromDBByID(choiceID);
        
        String query = "SELECT * FROM delfinen.medlemmer WHERE ID = ?";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int getID = resultSet.getInt("ID");
            String getName = resultSet.getString("name");
            int getAge = resultSet.getInt("age");
            String getEmail = resultSet.getString("email");
            int getPhoneNumber = resultSet.getInt("phoneNumber");
            String getCity = resultSet.getString("City");
            int getZipCode = resultSet.getInt("zipCode");
            String getAddress = resultSet.getString("address");
            boolean getCompSwimmer = resultSet.getBoolean("competitiveSwimmer");
            boolean getActive = resultSet.getBoolean("active");
            assertEquals(17, getID);
            assertEquals("Mogens Benson", getName);
            assertEquals(65, getAge);
            assertEquals("mogens@mail.dk", getEmail);
            assertEquals(97867564, getPhoneNumber);
            assertEquals("Kokkedal", getCity);
            assertEquals(3074, getZipCode);
            assertEquals("Arnevej 102", getAddress);
            assertEquals(false, getCompSwimmer);
            assertEquals(true, getActive);
        }

        pstmt.close();
        myConnector.close();
        resultSet.close();
        
        
    }


}
