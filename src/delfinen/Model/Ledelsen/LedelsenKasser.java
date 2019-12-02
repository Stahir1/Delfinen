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
import java.sql.SQLException;

/**
 *
 * @author Danie
 */
public class LedelsenKasser extends Ledelsen {
    
    private boolean competitiveSwimmer;
    private boolean active;

    public LedelsenKasser(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        super(name, age, email, phoneNumber, city, zipCode, address);
        this.competitiveSwimmer = competitiveSwimmer;
        this.active = active;
    }
    
    public void createContingentPayment(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String senior = "";
        String junior = "";
        String query = "INSERT INTO delfinen.medlemmer (name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active, senior, junior) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String query2 = "TRUNCATE TABLE delfinen.kontingentbetaling";
        String query3 = "INSERT INTO delfinen.kontingentbetaling (ID, name, age, active) SELECT ID, name, age, active FROM delfinen.medlemmer";

        if (age < 18) {
            senior = "Nej";
            junior = "Ja";
        } else {
            senior = "Ja";
            junior = "Nej";
        }

        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);

        pstmt.setString(1, name);
        pstmt.setInt(2, age); // for at lave ordering kolennen i order table starte på 1 i stedet for 0.
        pstmt.setString(3, email);
        pstmt.setInt(4, phoneNumber);
        pstmt.setString(5, city);
        pstmt.setInt(6, zipCode);
        pstmt.setString(7, address);
        pstmt.setBoolean(8, competitiveSwimmer);
        pstmt.setBoolean(9, active);
        pstmt.setString(10, senior);
        pstmt.setString(11, junior);

        pstmt.executeUpdate();
        pstmt2 = myConnector.prepareStatement(query2);
        pstmt2.executeUpdate();
        pstmt3 = myConnector.prepareStatement(query3);
        pstmt3.executeUpdate();

        pstmt.close();
        pstmt2.close();
        pstmt3.close();
        myConnector.close();
    }
    
}
