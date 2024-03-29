
package delfinen.Model.Ledelsen;

import delfinen.Controller.Controller;
import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
 */
public class LedelsenFormand extends Ledelsen {

    private boolean competitiveSwimmer;
    private boolean active;

    public LedelsenFormand(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        super(name, age, email, phoneNumber, city, zipCode, address);
        this.competitiveSwimmer = competitiveSwimmer;
        this.active = active;
    }

    public void addMemberToDB(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String senior = "";
        String junior = "";
        String query = "INSERT INTO delfinen.medlemmer (name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active, senior, junior) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // "DESC LIMIT 1" tager den sidste i tabellen ud fra ID, hvilket er den seneste
        // tilføjet (den seneste tilføjede er det medlem man lige har oprettet).
        // Medlemmets ID, navn, alder og aktiv/passiv info, smides over i kontingentbetaling,
        // så formanden efterfølgende kan oprette betalinger/administrere medlemmet gennem
        // sin del af systemet.
        String query3 = "INSERT INTO delfinen.kontingentbetaling (ID, name, age, active) SELECT ID, name, age, active FROM delfinen.medlemmer ORDER BY ID DESC LIMIT 1";

        if (age < 18) {
            senior = "Nej";
            junior = "Ja";
        } else {
            senior = "Ja";
            junior = "Nej";
        }

        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt3 = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);

        pstmt.setString(1, name);
        pstmt.setInt(2, age);
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
        pstmt3 = myConnector.prepareStatement(query3);
        pstmt3.executeUpdate();

        pstmt.close();
        pstmt3.close();
        myConnector.close();
    }

    public void addMemberToDBProcess() {
        try {
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

            System.out.println("Indtast navn: [Tast \"0\" hvis du ikke vil oprette et medlem alligevel.]");
            name = scanners.StringScanner();
            if (!name.equals("0")) {

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
                
                // I stedet for at formand skal skrive "true" eller "false"
                // kan vedkommende bare skrive "1" eller "2".
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
                LedelsenFormand formand = new LedelsenFormand(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
                formand.addMemberToDB(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

                System.out.println("Info om det nyoprettede medlem: " + medlemsType.toString());
            } else {
            }
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }

    }

    public void updateMemberInDB(int choiceSubject, int choiceID, String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        try {
            String query = "";
            String query2 = "";
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            ResultSet resultSet = null;
            myConnector = DBConnector.getConnector();

            //UPDATE delfinen.medlemmer SET name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active WHERE ID = ?)"
            // Afhængigt af hvilket emne der ønskes ændret,
            // ændrer queryen sig.
            switch (choiceSubject) {
                case 1:
                    query = "UPDATE delfinen.medlemmer SET name = ? WHERE ID = ?";
                    pstmt = myConnector.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setInt(2, choiceID);
                    query2 = "UPDATE delfinen.kontingentbetaling SET name = ? WHERE ID = ?";
                    pstmt2 = myConnector.prepareStatement(query2);
                    pstmt2.setString(1, name);
                    pstmt2.setInt(2, choiceID);
                    pstmt2.executeUpdate();
                    pstmt2.close();
                    break;
                case 2:
                    query = "UPDATE delfinen.medlemmer SET age = ? WHERE ID = ?";
                    pstmt = myConnector.prepareStatement(query);
                    pstmt.setInt(1, age);
                    pstmt.setInt(2, choiceID);
                    query2 = "UPDATE delfinen.kontingentbetaling SET age = ? WHERE ID = ?";
                    pstmt2 = myConnector.prepareStatement(query2);
                    pstmt2.setInt(1, age);
                    pstmt2.setInt(2, choiceID);
                    pstmt2.executeUpdate();
                    pstmt2.close();
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
                    query2 = "UPDATE delfinen.kontingentbetaling SET active = ? WHERE ID = ?";
                    pstmt2 = myConnector.prepareStatement(query2);
                    pstmt2.setBoolean(1, active);
                    pstmt2.setInt(2, choiceID);
                    pstmt2.executeUpdate();
                    pstmt2.close();
                    break;
            }

            pstmt.executeUpdate();

            pstmt.close();
            myConnector.close();
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }
    }

    public void updateMemberProcess() {
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

        System.out.println("Hvilket medlem vil du ændre? (Søg på medlems-ID) [Tast \"0\" hvis du vil forlade menuen.]");
        int choiceID = scanners.IntScanner();
        if (choiceID != 0) {

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

            switch (choiceSubject) {
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
                    if (number == 1) {
                        competitiveSwimmer = true;
                    } else if (number == 2) {
                        competitiveSwimmer = false;
                    } else {
                        System.out.println("Du har hverken tastet \"1\" eller \"2\"");
                    }
                    break;
                case 9:
                    System.out.println("Hvad skal aktiv/passiv-tilstanden ændres til? (1 = JA, 2 = NEJ)");
                    number = scanners.IntScanner();
                    if (number == 1) {
                        active = true;
                    } else if (number == 2) {
                        active = false;
                    } else {
                        System.out.println("Du har hverken tastet \"1\" eller \"2\"");
                    }
                    break;
            }

            if (choiceSubject == 0) {
            } else {
                LedelsenFormand formand = new LedelsenFormand(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
                formand.updateMemberInDB(choiceSubject, choiceID, name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
                System.out.println("Info om det redigerede medlem:");
                formand.getMembersFromDBByID(choiceID);
            }
        } else {

        }

    }

    public void getMembersFromDBByID(int choiceID) {
        try {
            String query = "SELECT * FROM delfinen.medlemmer WHERE ID = ?";
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            myConnector = DBConnector.getConnector();
            pstmt = myConnector.prepareStatement(query);

            pstmt.setInt(1, choiceID);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                // her bliver databasens data trækket ud til variabler i Java
                // som efterfølgende kan printes til formandens skærm.
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
                String competitiveSwimmerStr = "";
                String activeStr = "";
                if (competitiveSwimmer == true) {
                    competitiveSwimmerStr = "Ja";
                } else {
                    competitiveSwimmerStr = "Nej";
                }
                if (active == true) {
                    activeStr = "Aktivt";
                } else {
                    activeStr = "Passivt";
                }
                System.out.println("ID: " + ID + ".\n" + "Navn: " + name + ".\n"
                        + "Alder: " + age + ".\n" + "Email: " + email + ".\n"
                        + "Tlf.: " + phoneNumber + ".\n" + "By: " + city + ".\n"
                        + "Postnr.: " + zipCode + ".\n" + "Adresse: " + address
                        + ".\n" + "Konkurrencesvømmer: " + competitiveSwimmerStr
                        + ".\n" + "Medlemskab: " + activeStr + ".");
            }

            resultSet.close();
            pstmt.close();
            myConnector.close();
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }
    }

    public void getMembersFromDB() {
        try {
            String query = "SELECT * FROM delfinen.medlemmer";
            Connection myConnector = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;
            myConnector = DBConnector.getConnector();

            pstmt = myConnector.prepareStatement(query);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
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
                String competitiveSwimmerStr = "";
                String activeStr = "";
                if (competitiveSwimmer == true) {
                    competitiveSwimmerStr = "Konkurrencesvømmer";
                } else {
                    competitiveSwimmerStr = "Ikke konkurrencesvømmer";
                }
                if (active == true) {
                    activeStr = "Aktivt medlemskab";
                } else {
                    activeStr = "Passivt medlemskab";
                }
                System.out.println("ID: " + ID + ", " + name + ", " + age + ", " + email + ", "
                        + phoneNumber + ", " + city + ", " + zipCode + ", " + address + ", " + competitiveSwimmerStr + ", " + activeStr + ".");
            }

            resultSet.close();
            pstmt.close();
            myConnector.close();
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }
    }

}
