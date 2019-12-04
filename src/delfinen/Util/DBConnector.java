package delfinen.Util;

import java.sql.*;


/* 
 * @author Emil, Sohaib, Jimmy, Daniel.
 */

public class DBConnector {

    // Bruges til at gøre kommunikation med databasen mulig.
    // OBS: Husk at logge ind med eget login.
    public static Connection getConnector() {
        Connection connector = null;
        String url = "jdbc:mysql://localhost:3306/delfinen?";
        url += "serverTimezone=UTC&allowPublicKeyRetrieval=true&";
        url += "useSSL=false";
        String user = "root";
        String password = "ascent88";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Kan ikke finde stien.");

        }
        try {
            connector = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Kan ikke kommunikere korrekt med databasen.");
        }

        return connector;
    }
}
