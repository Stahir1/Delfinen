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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
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

    public void createPayment(int choiceID) {
        try {
            String query = "SELECT age, active FROM delfinen.kontingentbetaling WHERE ID = ?";
            String query2 = "UPDATE delfinen.kontingentbetaling SET amount = ?, hasPaid = false, date = ? WHERE ID = ?";
            double priceAmount = 0;
            date = date.plusWeeks(2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String dateFormated = date.format(formatter);
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
            pstmt2.setString(2, dateFormated);
            pstmt2.setInt(3, choiceID);
            pstmt2.executeUpdate();
            
            resultSet.close();
            pstmt.close();
            pstmt2.close();
            myConnector.close();
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }
    }

    public void createPaymentProcess() {
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
        int choiceID = scanners.IntScanner();
        kasser.createPayment(choiceID);
        LedelsenTræner træner = new LedelsenTræner(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
        træner.addMembertoTeam(choiceID);
        date = date.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateFormated = date.format(formatter);
        System.out.println("Betalingsfrist: " + dateFormated + ".");

    }

    public void getContMembersFromDB() {
        try {
            String query = "SELECT * FROM delfinen.kontingentbetaling";
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            myConnector = DBConnector.getConnector();
            
            pstmt = myConnector.prepareStatement(query);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
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
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }
    }

    public void getContMembersFromDBInRestance() {
        try {
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
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }
    }

    public void updateHasPaid(int choiceID) {
        try {
            String query = "SELECT date FROM delfinen.kontingentbetaling WHERE ID = ?";
            String query2 = "UPDATE delfinen.kontingentbetaling SET hasPaid = true, date = ? WHERE ID = ?";
            
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            ResultSet resultSet = null;
            myConnector = DBConnector.getConnector();
            pstmt = myConnector.prepareStatement(query);
            
            pstmt.setInt(1, choiceID);
            resultSet = pstmt.executeQuery();
            
            while (resultSet.next()) {
                String getDate = resultSet.getString("date");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date stringToDate = formatter.parse(getDate);
                Calendar c = Calendar.getInstance();
                c.setTime(stringToDate);
                c.add(Calendar.YEAR, 1);
                stringToDate = c.getTime();
                String stringToDateToString = formatter.format(stringToDate);
                
                pstmt2 = myConnector.prepareStatement(query2);
                pstmt2.setString(1, stringToDateToString);
                pstmt2.setInt(2, choiceID);
                pstmt2.executeUpdate();
            }
            
            resultSet.close();
            pstmt.close();
            pstmt2.close();
            myConnector.close();
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        } catch (ParseException ex) {
            System.out.println("Kan ikke formattere korrekt.");
        }

    }

    public void updateHasPaidProcess() {
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

        kasser.getContMembersFromDBInRestance();

        System.out.println("Hvilket medlem skal markeres som betalt?");
        int choiceID = scanners.IntScanner();

        kasser.updateHasPaid(choiceID);

    }
    
    public void removeHasPaid(int choiceID) {
        try {
            String query = "UPDATE delfinen.kontingentbetaling SET hasPaid = false WHERE ID = ?";
            
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            myConnector = DBConnector.getConnector();
            pstmt = myConnector.prepareStatement(query);
            
            pstmt.setInt(1, choiceID);
            pstmt.executeUpdate();
            
            pstmt.close();
            myConnector.close();
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }

    }
    
    public void removeHasPaidProcess() {
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

        System.out.println("Hvilket medlem skal markeres med manglende betaling?");
        int choiceID = scanners.IntScanner();

        kasser.removeHasPaid(choiceID);

    }
    
    
    
}
