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
    private LocalDate dato = LocalDate.now();

    public LedelsenKasser(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        super(name, age, email, phoneNumber, city, zipCode, address);
        this.competitiveSwimmer = competitiveSwimmer;
        this.active = active;
    }

    public void createPayment(int choiceID) throws SQLException {
        String query = "SELECT age, active FROM delfinen.kontingentbetaling WHERE ID = ?";
        String query2 = "UPDATE delfinen.kontingentbetaling SET amount = ?, hasPaid = false, date = ? WHERE ID = ?";
        double priceAmount = 0;
        dato = dato.plusWeeks(2);
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
        pstmt2.setString(2, dato.toString());
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

        System.out.println("Hvilket medlem ønsker du at oprette kontingentbetaling til? (Indtast ID nr. for det ønskede medlem)");
        choiceID = scanners.IntScanner();

        LedelsenKasser kasser = new LedelsenKasser(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
        kasser.createPayment(choiceID);
        
        System.out.println("Betalingsfrist: " + dato.plusWeeks(2).toString() + ".");

    }

}
