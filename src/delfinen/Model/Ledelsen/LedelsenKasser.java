/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Model.Ledelsen;

import delfinen.Controller.Controller;
import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Danie
 */
public class LedelsenKasser extends Ledelsen {

    private boolean competitiveSwimmer;
    private boolean active;
    private LocalDate date = LocalDate.now();

    public LedelsenKasser(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        super(name, age, email, phoneNumber, city, zipCode, address);
        this.competitiveSwimmer = competitiveSwimmer;
        this.active = active;
    }

    public void createPayment(int choiceID) throws SQLException {
        String query = "SELECT age, active FROM delfinen.kontingentbetaling WHERE ID = ?";
        String query2 = "UPDATE delfinen.kontingentbetaling SET amount = ?, hasPaid = false, date = ? WHERE ID = ?";
        double priceAmount = 0;
        date = date.plusWeeks(2);
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();
        pstmt = myConnector.prepareStatement(query);

        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            int age = resultSet.getInt("age");

            boolean active = resultSet.getBoolean("active");

            if (active == false) {
                priceAmount = 500;
            } else if (active == true && age < 18) {
                priceAmount = 1000;
            } else if (active == true && age >= 18) {
                priceAmount = 1600;
            }

            if (age > 60) {
                priceAmount = priceAmount * 0.75;
            }
        }

        pstmt2 = myConnector.prepareStatement(query2);
        pstmt2.setDouble(1, priceAmount);
        pstmt2.setString(2, date.toString());
        pstmt2.setInt(3, choiceID);
        pstmt2.executeUpdate();

        resultSet.close();
        pstmt.close();
        pstmt2.close();
        myConnector.close();
    }

    public void createPaymentProcess(int choiceID) throws SQLException {
        Controller scanners = new Controller();
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

        kasser.getContMembersFromDB();

        System.out.println("Hvilket medlem ønsker du at oprette kontingentbetaling til? (Indtast ID nr. for det ønskede medlem)");
        choiceID = scanners.IntScanner();

        kasser.createPayment(choiceID);

        System.out.println("Betalingsfrist: " + date.plusWeeks(2).toString() + ".");

    }

    public void getContMembersFromDB() throws SQLException {
        String query = "SELECT * FROM delfinen.kontingentbetaling";
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
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            boolean active = resultSet.getBoolean("active");
            String activeStr = "";
            if (active == true) {
                activeStr = "Aktivt medlemskab";
            } else {
                activeStr = "Passivt medlemskab";
            }
            double priceAmount = resultSet.getDouble("amount");
            boolean hasPaid = resultSet.getBoolean("hasPaid");
            String hasPaidStr = "";
            if (hasPaid == true) {
                hasPaidStr = "Har betalt";
            } else {
                hasPaidStr = "Har ikke betalt";
            }
            String getDate = resultSet.getString("date");

            if (priceAmount == 0.0) {
                System.out.println("ID: " + ID + ", " + name + ", " + age + ", "
                        + activeStr + " - Dette medlem har ikke fået oprettet kontingentbetaling.");
            } else {
                System.out.println("ID: " + ID + ", " + name + ", " + age + ", "
                        + activeStr + ", " + priceAmount + " kr., " + hasPaidStr
                        + ", " + getDate + ".");
            }
        }

        System.out.println("");

        resultSet.close();
        pstmt.close();
        myConnector.close();
    }

    public void getContMembersFromDBInRestance() throws SQLException {
        String query = "SELECT * FROM delfinen.kontingentbetaling WHERE hasPaid = false";
        String query2 = "SELECT * FROM delfinen.kontingentbetaling WHERE hasPaid IS null";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        myConnector = DBConnector.getConnector();

        pstmt2 = myConnector.prepareStatement(query2);
        resultSet2 = pstmt2.executeQuery();

        System.out.println("Medlemmer der ikke er oprettet i kontingentbetaling:");
        while (resultSet2.next()) {
            // Nedenfor deklarerer vi vores kolonne-navne, så vi ikke behøver at
            // tilføje det inde i vores printline for hver pizza (dvs. 30+ gange)
            int ID = resultSet2.getInt("ID");
            String name = resultSet2.getString("name");
            int age = resultSet2.getInt("age");
            boolean active = resultSet2.getBoolean("active");
            String activeStr = "";
            if (active == true) {
                activeStr = "Aktivt medlemskab";
            } else {
                activeStr = "Passivt medlemskab";
            }
            double priceAmount = resultSet2.getDouble("amount");
            boolean hasPaid = resultSet2.getBoolean("hasPaid");
            String hasPaidStr = "";
            if (hasPaid == true) {
                hasPaidStr = "Har betalt";
            } else {
                hasPaidStr = "Har ikke betalt";
            }
            String getDate = resultSet2.getString("date");

            System.out.println("ID: " + ID + ", " + name + ", " + age + ", "
                    + activeStr + " - Dette medlem har ikke fået oprettet kontingentbetaling.");
        }
        System.out.println("");
        
        resultSet2.close();
        pstmt2.close();

        pstmt = myConnector.prepareStatement(query);
        resultSet = pstmt.executeQuery();
        System.out.println("Medlemmer i restance:");
        while (resultSet.next()) {
            // Nedenfor deklarerer vi vores kolonne-navne, så vi ikke behøver at
            // tilføje det inde i vores printline for hver pizza (dvs. 30+ gange)
            int ID = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            boolean active = resultSet.getBoolean("active");
            String activeStr = "";
            if (active == true) {
                activeStr = "Aktivt medlemskab";
            } else {
                activeStr = "Passivt medlemskab";
            }
            double priceAmount = resultSet.getDouble("amount");
            boolean hasPaid = resultSet.getBoolean("hasPaid");
            String hasPaidStr = "";
            if (hasPaid == true) {
                hasPaidStr = "Har betalt";
            } else {
                hasPaidStr = "Har ikke betalt";
            }
            String getDate = resultSet.getString("date");

            if (priceAmount == 0.0) {
                System.out.println("ID: " + ID + ", " + name + ", " + age + ", "
                        + activeStr + " - Dette medlem har ikke fået oprettet kontingentbetaling.");
            } else {
                System.out.println("ID: " + ID + ", " + name + ", " + age + ", "
                        + activeStr + ", " + priceAmount + " kr., " + hasPaidStr
                        + ", " + getDate + ".");
            }
        }

        System.out.println("");

        resultSet.close();
        pstmt.close();
        myConnector.close();
    }

}
