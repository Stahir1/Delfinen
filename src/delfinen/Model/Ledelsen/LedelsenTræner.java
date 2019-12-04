package delfinen.Model.Ledelsen;

import delfinen.Controller.Controller;
import delfinen.Util.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Danie
 */
public class LedelsenTræner extends Ledelsen {

    private boolean competitiveSwimmer;
    private boolean active;
    private LocalDate date = LocalDate.now();

    public LedelsenTræner(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        super(name, age, email, phoneNumber, city, zipCode, address);
        this.competitiveSwimmer = competitiveSwimmer;
        this.active = active;
    }

    public void addMembertoTeam(int choiceID) throws SQLException {
        String query = "INSERT INTO delfinen.svømmehold (swimmerID, swimmerAge) SELECT ID, age FROM delfinen.medlemmer WHERE ID = ?";
        String query2 = "SELECT swimmerAge FROM delfinen.svømmehold WHERE swimmerID = ?";
        String query3 = "UPDATE delfinen.svømmehold SET teamID = ?, teamName = ?, trainer = ? WHERE swimmerID = ?";

        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        // query 1
        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        pstmt.executeUpdate();

        // query 2
        pstmt2 = myConnector.prepareStatement(query2);
        pstmt2.setInt(1, choiceID);
        resultSet = pstmt2.executeQuery();
        int swimmerAge = 0;
        while (resultSet.next()) {
            swimmerAge = resultSet.getInt("swimmerAge");
        }
        // pstmt2.executeQuery();

        // guery 3
        pstmt3 = myConnector.prepareStatement(query3);
        if (swimmerAge <= 18) {
            pstmt3.setInt(1, 1);
            pstmt3.setString(2, "Ungdomshold");
            pstmt3.setString(3, "Erik Nielsen");
            pstmt3.setInt(4, choiceID);
        } else if (swimmerAge > 18) {
            pstmt3.setInt(1, 2);
            pstmt3.setString(2, "Seniorhold");
            pstmt3.setString(3, "Cecilie Karlsen");
            pstmt3.setInt(4, choiceID);
        }
        pstmt3.executeUpdate();

        pstmt.close();
        pstmt2.close();
        pstmt3.close();
        myConnector.close();
        resultSet.close();

    }

    public void updateTrainingResults(int choiceSubject, int choiceID, String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String queryFirst = "SELECT swimmerID FROM delfinen.træningsresultater WHERE swimmerID = ?";
        String querySec = "INSERT INTO delfinen.træningsresultater (swimmerID) VALUES (?)";
        String query = "";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmtFirst = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();
        Controller scanners = new Controller();
        double input;
        String hvilkenTid = "Hvad er tiden på? (\"sek,milsek\")";

        pstmtFirst = myConnector.prepareStatement(queryFirst);
        pstmtFirst.setInt(1, choiceID);
        resultSet = pstmtFirst.executeQuery();
        int swimmerID = 0;
        while (resultSet.next()) {
            swimmerID = resultSet.getInt("swimmerID");
        }

        if (choiceID != swimmerID) {
            pstmt2 = myConnector.prepareStatement(querySec);
            pstmt2.setInt(1, choiceID);
            pstmt2.executeUpdate();
            pstmt2.close();
        }

        // dato:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateFormated = date.format(formatter);

        switch (choiceSubject) {
            case 1:
                query = "UPDATE delfinen.træningsresultater SET crawlTime = ?, crawlDate = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);
                System.out.println(hvilkenTid);
                input = scanners.DoubleScanner();
                pstmt.setDouble(1, input);
                pstmt.setString(2, dateFormated);
                pstmt.setInt(3, choiceID);
                break;
            case 2:
                query = "UPDATE delfinen.træningsresultater SET butterflyTime = ?, butterflyDate = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);
                System.out.println(hvilkenTid);
                input = scanners.DoubleScanner();
                pstmt.setDouble(1, input);
                pstmt.setString(2, dateFormated);
                pstmt.setInt(3, choiceID);
                break;
            case 3:
                query = "UPDATE delfinen.træningsresultater SET backstrokeTime = ?, backstrokeDate = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);
                System.out.println(hvilkenTid);
                input = scanners.DoubleScanner();
                pstmt.setDouble(1, input);
                pstmt.setString(2, dateFormated);
                pstmt.setInt(3, choiceID);
                break;
            case 4:
                query = "UPDATE delfinen.træningsresultater SET breaststrokeTime = ?, breaststrokeDate = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);
                System.out.println(hvilkenTid);
                input = scanners.DoubleScanner();
                pstmt.setDouble(1, input);
                pstmt.setString(2, dateFormated);
                pstmt.setInt(3, choiceID);
                break;

        }

        pstmt.executeUpdate();

        pstmt.close();
        pstmtFirst.close();
        myConnector.close();
    }

    public void updateTrainingResultsProcess() throws SQLException {
        String name = "";
        int age = 0;
        String email = "";
        int phoneNumber = 0;
        String city = "";
        int zipCode = 0;
        String address = "";
        boolean competitiveSwimmer = true;
        boolean active = true;
        Controller scanners = new Controller();
        LedelsenTræner træner = new LedelsenTræner(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        træner.showSvømmehold();
        System.out.println("Hvilken svømmer vil du registrere træningsresultat til?");
        int choiceID = scanners.IntScanner();

        System.out.println("Hvilken disciplin skal opdateres?");
        System.out.println("Tast 1 for crawl.");
        System.out.println("Tast 2 for butterfly.");
        System.out.println("Tast 3 for rygcrawl.");
        System.out.println("Tast 4 for brystsvømning.");
        int subjectID = scanners.IntScanner();

        træner.updateTrainingResults(subjectID, choiceID, name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

    }

    public void updateEventResults(int choiceSubject, int choiceID, String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) throws SQLException {
        String queryFirst = "SELECT swimmerID FROM delfinen.træningsresultater WHERE swimmerID = ?";
        String querySec = "INSERT INTO delfinen.træningsresultater (swimmerID) VALUES (?)";
        String query = "";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmtFirst = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();
        Controller scanners = new Controller();
        double input;
        String eventname = "";
        int place;
        String whichEvent = "Indtast stævne-navn:";
        String whichTime = "Hvad er tiden på? (\"sek,milsek\")";
        String whichPlace = "Hvilken placering blev svømmeren?";

        pstmtFirst = myConnector.prepareStatement(queryFirst);
        pstmtFirst.setInt(1, choiceID);
        resultSet = pstmtFirst.executeQuery();
        int swimmerID = 0;
        while (resultSet.next()) {
            swimmerID = resultSet.getInt("swimmerID");
        }

        if (choiceID != swimmerID) {
            pstmt2 = myConnector.prepareStatement(querySec);
            pstmt2.setInt(1, choiceID);
            pstmt2.executeUpdate();
            pstmt2.close();
        }

        switch (choiceSubject) {
            case 1:
                query = "UPDATE delfinen.konkurrenceresultater SET eventname = ?, eventCrawlTime = ?, eventCrawlPlacement = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);

                System.out.println(whichEvent);
                eventname = scanners.StringScanner();

                System.out.println(whichTime);
                input = scanners.DoubleScanner();

                System.out.println(whichPlace);
                place = scanners.IntScanner();

                pstmt.setString(1, eventname);
                pstmt.setDouble(2, input);
                pstmt.setInt(3, place);
                pstmt.setInt(4, choiceID);
                break;
            case 2:
                query = "UPDATE delfinen.konkurrenceresultater SET eventname = ?, eventButterflyTime = ?, eventButterflyPlacement = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);

                System.out.println(whichEvent);
                eventname = scanners.StringScanner();

                System.out.println(whichTime);
                input = scanners.DoubleScanner();

                System.out.println(whichPlace);
                place = scanners.IntScanner();

                pstmt.setString(1, eventname);
                pstmt.setDouble(2, input);
                pstmt.setInt(3, place);
                pstmt.setInt(4, choiceID);
                break;
            case 3:
                query = "UPDATE delfinen.konkurrenceresultater SET eventname = ?, eventBackstrokeTime = ?, eventBackstrokePlacement = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);

                System.out.println(whichEvent);
                eventname = scanners.StringScanner();

                System.out.println(whichTime);
                input = scanners.DoubleScanner();

                System.out.println(whichPlace);
                place = scanners.IntScanner();

                pstmt.setString(1, eventname);
                pstmt.setDouble(2, input);
                pstmt.setInt(3, place);
                pstmt.setInt(4, choiceID);
                break;
            case 4:
                query = "UPDATE delfinen.konkurrenceresultater SET eventname = ?, eventBreaststrokeTime = ?, eventBreaststrokePlacement = ? WHERE swimmerID = ?";
                pstmt = myConnector.prepareStatement(query);

                System.out.println(whichEvent);
                eventname = scanners.StringScanner();

                System.out.println(whichTime);
                input = scanners.DoubleScanner();

                System.out.println(whichPlace);
                place = scanners.IntScanner();

                pstmt.setString(1, eventname);
                pstmt.setDouble(2, input);
                pstmt.setInt(3, place);
                pstmt.setInt(4, choiceID);
                break;
        }

        pstmt.executeUpdate();

        pstmt.close();
        pstmtFirst.close();
        myConnector.close();
    }

    public void updateEventResultsProcess() throws SQLException {
        String name = "";
        int age = 0;
        String email = "";
        int phoneNumber = 0;
        String city = "";
        int zipCode = 0;
        String address = "";
        boolean competitiveSwimmer = true;
        boolean active = true;
        Controller scanners = new Controller();
        LedelsenTræner træner = new LedelsenTræner(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        træner.showSvømmehold();
        System.out.println("Hvilken svømmer vil du registrere stævneresultat til?");
        int choiceID = scanners.IntScanner();

        System.out.println("Hvilken disciplin skal opdateres?");
        System.out.println("Tast 1 for crawl.");
        System.out.println("Tast 2 for butterfly.");
        System.out.println("Tast 3 for rygcrawl.");
        System.out.println("Tast 4 for brystsvømning.");
        int subjectID = scanners.IntScanner();

        træner.updateEventResults(subjectID, choiceID, name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

    }

    public void showSvømmehold() throws SQLException {
        String query = "SELECT * FROM delfinen.svømmehold";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();
        pstmt = myConnector.prepareStatement(query);
        pstmt.executeQuery();

        resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            String teamName = resultSet.getString("teamName");
            String trainer = resultSet.getString("trainer");
            int swimmerID = resultSet.getInt("swimmerID");
            int swimmerAge = resultSet.getInt("swimmerAge");

            System.out.println("Svømmer-ID: " + swimmerID + ", Svømmer alder: "
                    + swimmerAge + ", Holdnavn: " + teamName
                    + ", Træner: " + trainer + ".");
        }
        System.out.println("");

        pstmt.close();
        myConnector.close();
        resultSet.close();
    }

    public String showTopFive(int choiceID, int choiceSubject) throws SQLException {
        String query = "SELECT * FROM delfinen.svømmehold WHERE teamID = ?";
        String query1 = "SELECT * FROM delfinen.træningsresultater ORDER BY ? DESC LIMIT 5";
        //String query2 = "SELECT * FROM delfinen.konkurrenceresultater ORDER BY ? LIMIT BY 5 DESC";
        Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        myConnector = DBConnector.getConnector();

        //query
        pstmt = myConnector.prepareStatement(query);
        pstmt.setInt(1, choiceID);
        resultSet = pstmt.executeQuery();

        //query 1
        pstmt2 = myConnector.prepareStatement(query1);
       
        switch (choiceSubject) {
            case 1:
                pstmt2.setString(1, "crawlTime");
                pstmt2.executeQuery();
                break;
            case 2:
                pstmt2.setString(1, "butterflyTime");
                pstmt2.executeQuery();
                break;
            case 3:
                pstmt2.setString(1, "backstrokeTime");
                pstmt2.executeQuery();
                break;
            case 4:
                pstmt2.setString(1, "breaststrokeTime");
                pstmt2.executeQuery();
                break;
        }
        resultSet2 = pstmt2.executeQuery();
        int swimmerID = 0;
        double time = 0;
        String output = "";
        while (resultSet2.next()) {
            swimmerID = resultSet2.getInt("swimmerID");

            switch (choiceSubject) {

                case 1:
                    time = resultSet2.getDouble("crawlTime");
                    break;

                case 2:
                    time = resultSet2.getDouble("butterflyTime");
                    break;

                case 3:
                    time = resultSet2.getDouble("backstrokeTime");
                    break;

                case 4:
                    time = resultSet2.getDouble("breaststrokeTime");
                    break;

            }
            output += "SvømmerID: " + swimmerID + ", tid: " + time + "\n";

        }
        pstmt.close();
        pstmt2.close();
        myConnector.close();
        resultSet.close();
        return output;
    }

    public void showTopFiveProcess() throws SQLException {
        String name = "";
        int age = 0;
        String email = "";
        int phoneNumber = 0;
        String city = "";
        int zipCode = 0;
        String address = "";
        boolean competitiveSwimmer = true;
        boolean active = true;
        Controller scanners = new Controller();
        LedelsenTræner træner = new LedelsenTræner(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        System.out.println("Hvilket hold vil du se top 5 over?");
        System.out.println("Indtast 1 for Juniorhold.");
        System.out.println("Indtast 2 for Seniorhold.");
        int choiceID = scanners.IntScanner();

        System.out.println("Hvilken disciplin vil du se top 5 for?");
        System.out.println("Tast 1 for crawl.");
        System.out.println("Tast 2 for butterfly.");
        System.out.println("Tast 3 for rygcrawl.");
        System.out.println("Tast 4 for brystsvømning.");
        int choiceSubject = scanners.IntScanner();

        System.out.println(træner.showTopFive(choiceID, choiceSubject));

    }

}
