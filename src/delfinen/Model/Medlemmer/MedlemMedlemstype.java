/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Model.Medlemmer;

import delfinen.Controller.Controller;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Danie
 */
public class MedlemMedlemstype extends Medlem {

    private boolean competitiveSwimmer;
    private boolean active;

    public MedlemMedlemstype(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        super(name, age, email, phoneNumber, city, zipCode, address);
        this.competitiveSwimmer = competitiveSwimmer;
        this.active = active;
    }

    public void addMedlemToDB(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String query = "INSERT INTO delfinen.medlemmer (name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection myConnector = null;
        PreparedStatement pstmt = null;
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

        pstmt.executeUpdate();

        pstmt.close();
        myConnector.close();
    }
    
    public void addMemberProcess() {
        System.out.println("Indtast navn");
        
    }
}
