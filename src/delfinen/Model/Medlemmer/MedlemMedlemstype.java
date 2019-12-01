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

    public void addMemberToDB(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String senior = "";
        String junior = "";
        String query = "INSERT INTO delfinen.medlemmer (name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active, senior, junior) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String query2 = "TRUNCATE TABLE delfinen.kontingentbetaling";
        String query3 = "INSERT INTO delfinen.kontingentbetaling (ID, name, age, active) SELECT ID, name, age, active FROM delfinen.medlemmer";
        
        
        if(age < 18) {
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

    public void updateMemberInDB(int choiceSubject, int choiceID, String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String query = "";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        //UPDATE delfinen.medlemmer  SET name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active WHERE ID = ?)"
        switch (choiceSubject) {
            case 1:
                query = "UPDATE delfinen.medlemmer SET name = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setInt(2, choiceID);
                break;
            case 2:
                query = "UPDATE delfinen.medlemmer SET age = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setInt(1, age);
                pstmt.setInt(2, choiceID);
                break;
            case 3:
                query = "UPDATE delfinen.medlemmer SET email = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setString(1, email);
                pstmt.setInt(2, choiceID);
                break;
            case 4:
                query = "UPDATE delfinen.medlemmer SET phoneNumber = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setInt(1, phoneNumber);
                pstmt.setInt(2, choiceID);
                break;
            case 5:
                query = "UPDATE delfinen.medlemmer SET city = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setString(1, city);
                pstmt.setInt(2, choiceID);
                break;
            case 6:
                query = "UPDATE delfinen.medlemmer SET zipCode = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setInt(1, zipCode);
                pstmt.setInt(2, choiceID);
                break;
            case 7:
                query = "UPDATE delfinen.medlemmer SET address = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setString(1, address);
                pstmt.setInt(2, choiceID);
                break;
            case 8:
                query = "UPDATE delfinen.medlemmer SET competitiveSwimmer = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setBoolean(1, competitiveSwimmer);
                pstmt.setInt(2, choiceID);
                break;
            case 9:
                query = "UPDATE delfinen.medlemmer SET active = ? WHERE ID = ?";
                pstmt = myConnector.prepareStatement(query);
                pstmt.setBoolean(1, active);
                pstmt.setInt(2, choiceID);
                break;
        }

        pstmt.executeUpdate();

        pstmt.close();
        myConnector.close();
    }

    public void updateMemberProcess() throws SQLException {
        Controller scanners = new Controller();
        boolean untilRight = true;
        String name = "";
        int age = 0;
        String email = "";
        int phoneNumber = 0;
        String city = "";
        int zipCode = 0;
        String address = "";
        boolean competitiveSwimmer = true;
        boolean active = true;
        int number;

        System.out.println("Hvilket medlem vil du ændre? (Søg på medlems-ID)");
        int choiceID = scanners.IntScanner();
        
        System.out.println("Hvad vil du ændre?");
        System.out.println("Tast 1 for navn.");
        System.out.println("Tast 2 for alder.");
        System.out.println("Tast 3 for email.");
        System.out.println("Tast 4 for tlf.");
        System.out.println("Tast 5 for by.");
        System.out.println("Tast 6 for postnr.");
        System.out.println("Tast 7 for adresse.");
        System.out.println("Tast 8 for konkurrencesvømmer tilstand.");
        System.out.println("Tast 9 for aktiv/passiv tilstand.");
        System.out.println("Tast 0 for at afslutte.");
        
        int choiceSubject = scanners.IntScanner();
        
        switch(choiceSubject) {
            case 1:
                System.out.println("Hvad skal navnet ændres til?");
                name = scanners.StringScanner();
                break;
            case 2:
                System.out.println("Hvad skal alderen ændres til?");
                age = scanners.IntScanner();
                break;
            case 3:
                System.out.println("Hvad skal emailen ændres til?");
                email = scanners.StringScanner();
                break;
            case 4:
                System.out.println("Hvad skal tlf. ændres til?");
                phoneNumber = scanners.IntScanner();
                break;
            case 5:
                System.out.println("Hvad skal byen ændres til?");
                city = scanners.StringScanner();
                break;
            case 6:
                System.out.println("Hvad skal postnr. ændres til?");
                zipCode = scanners.IntScanner();
                break;
            case 7:
                System.out.println("Hvad skal adressen ændres til?");
                address = scanners.StringScanner();
                break;
            case 8:
                System.out.println("Hvad skal svømmer-tilstanden ændres til? (1 = JA, 2 = NEJ)");
                number = scanners.IntScanner();
                if(number == 1) {
                    competitiveSwimmer = true;
                } else if(number == 2) {
                    competitiveSwimmer = false;
                } else {
                    System.out.println("Du har hverken tastet \"1\" eller \"2\"");
                }
                break;
            case 9:
                System.out.println("Hvad skal aktiv/passiv-tilstanden ændres til? (1 = JA, 2 = NEJ)");
                number = scanners.IntScanner();
                if(number == 1) {
                    active = true;
                } else if(number == 2) {
                    active = false;
                } else {
                    System.out.println("Du har hverken tastet \"1\" eller \"2\"");
                }
                break;
        }
        
        if(choiceID == 0) { 
        } else {
            MedlemMedlemstype medlemsType = new MedlemMedlemstype(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
            medlemsType.updateMemberInDB(choiceSubject, choiceID, name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
            System.out.println("Info om det redigerede medlem: \n");
                    medlemsType.getMembersFromDBByID(choiceID);
        }

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
        medlemsType.addMemberToDB(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        System.out.println("Info om det nyoprettede medlem: " + medlemsType.toString());

    }
    
    public void getMembersFromDBByID(int choiceID) throws SQLException {
            String query = "SELECT * FROM delfinen.medlemmer WHERE ID = ?";
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            myConnector = DBConnector.getConnector();
            pstmt = myConnector.prepareStatement(query);
            
            pstmt.setInt(1, choiceID);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                // Nedenfor deklarerer vi vores kolonne-navne, så vi ikke behøver at
                // tilføje det inde i vores printline for hver pizza (dvs. 30+ gange)
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                int age = resultSet.getInt("Age");
                String email = resultSet.getString("Email");
                int phoneNumber = resultSet.getInt("phoneNumber");
                String city = resultSet.getString("City");
                int zipCode = resultSet.getInt("ZipCode");
                String address = resultSet.getString("Address");
                boolean competitiveSwimmer = resultSet.getBoolean("competitiveSwimmer");
                boolean active = resultSet.getBoolean("Active");
                System.out.println("ID: " + ID + ", " + name + ": " + age + ", " + email + " " 
                        + phoneNumber + ", " + city + ", " + zipCode + ", " + address + ", " + competitiveSwimmer + " " + active + " ");
            }

            resultSet.close();
            pstmt.close();
            myConnector.close();
        } 
    
       public void getMembersFromDB() throws SQLException {
            String query = "SELECT * FROM delfinen.medlemmer";
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            myConnector = DBConnector.getConnector();

            pstmt = myConnector.prepareStatement(query);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                // Nedenfor deklarerer vi vores kolonne-navne, så vi ikke behøver at
                // tilføje det inde i vores printline for hver pizza (dvs. 30+ gange)
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                int age = resultSet.getInt("Age");
                String email = resultSet.getString("Email");
                int phoneNumber = resultSet.getInt("phoneNumber");
                String city = resultSet.getString("City");
                int zipCode = resultSet.getInt("ZipCode");
                String address = resultSet.getString("Address");
                boolean competitiveSwimmer = resultSet.getBoolean("competitiveSwimmer");
                boolean active = resultSet.getBoolean("Active");
                System.out.println("ID: " + ID + ", " + name + ": " + age + ", " + email + " " 
                        + phoneNumber + ", " + city + ", " + zipCode + ", " + address + ", " + competitiveSwimmer + " " + active + " ");
            }

            resultSet.close();
            pstmt.close();
            myConnector.close();
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

        if (active == true) {
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
