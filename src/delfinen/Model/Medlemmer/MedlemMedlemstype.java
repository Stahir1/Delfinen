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
import javax.sound.sampled.Port;

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
    
     public void updateDataInDB(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String query = "UPDATE delfinen.medlemmer  SET name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active WHERE ID = ?)";

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

    public void addMemberProcess() throws SQLException {
        Controller scanners = new Controller();
        boolean untilRight = true;
        String name = "";
        int age;
        String email = "";
        int phoneNumber;
        String city = "";
        int zipCode;
        String address = "";
        boolean competitiveSwimmer = true;
        boolean active = true;

        System.out.println("Indtast navn:");
        name = scanners.StringScanner();

        System.out.println("Indtast alder:");
        age = scanners.IntScanner();

        System.out.println("Indtast email:");
        email = scanners.StringScanner();

        System.out.println("Indtast tlf.:");
        phoneNumber = scanners.IntScanner();

        System.out.println("Indtast by:");
        city = scanners.StringScanner();

        System.out.println("Indtast postnummer:");
        zipCode = scanners.IntScanner();

        System.out.println("Indtast adresse:");
        address = scanners.StringScanner();

        System.out.println("Konkurrencesvømmer? 1 = JA, 2 = NEJ");
        int booNum = scanners.IntScanner();
        if (booNum == 1) {
            competitiveSwimmer = true;
            untilRight = false;
        } else if (booNum == 2) {
            competitiveSwimmer = false;
            untilRight = false;
        } else {
            System.out.println("Du har hverken tastet \"1\" eller \"2\".");
        }
        
        System.out.println("Aktivt medlemskab? 1 = JA, 2 = NEJ");
        booNum = scanners.IntScanner();
        if (booNum == 1) {
            active = true;
            untilRight = false;
        } else if (booNum == 2) {
            active = false;
            untilRight = false;
        } else {
            System.out.println("Du har hverken tastet \"1\" eller \"2\".");
        }

        MedlemMedlemstype medlemsType = new MedlemMedlemstype(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
        medlemsType.addMedlemToDB(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        System.out.println("Info om det nyoprettede medlem: \n" + medlemsType.toString());

    }

    
    
    @Override
    public String toString() {
        String n = "\n";
        String compSwimWord;
        String activeWord;
        if (competitiveSwimmer == true) {
            compSwimWord = "Ja";
        } else {
            compSwimWord = "Nej";
        }
        
        if(active == true) {
            activeWord = "Ja";
        } else {
            activeWord = "Nej";
        }

        return "Navn: " + super.getName() + n
                + "Alder: " + super.getAge() + n
                + "Email: " + super.getEmail() + n
                + "Tlf.: " + super.getPhoneNumber() + n
                + "By: " + super.getCity() + n
                + "Postnummer: " + super.getZipCode() + n
                + "Adresse: " + super.getAdress() + n
                + "Konkurrencesvømmer: " + compSwimWord + n
                + "Aktivt medlemskab: " + activeWord + n;
    }

}
