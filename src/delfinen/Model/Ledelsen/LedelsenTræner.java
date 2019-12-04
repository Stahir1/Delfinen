package delfinen.Model.Ledelsen;

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
public class LedelsenTræner {

    public void addMembertoTeam(int choiceID) throws SQLException {
        String query = "INSERT INTO delfinen.svømmehold (swimmerID, swimmerAge) SELECT ID, age FROM delfinen.medlemmer WHERE ID = ?";
        String query2 = "SELECT swimmerAge FROM delfinen.svømmehold WHERE ID = ?";
        String query3 = "UPDATE delfinen.svømmehold SET teamID = ?, teamName = ?, trainer = ?";

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
        resultSet = pstmt2.executeQuery();
        int swimmerAge = 0;
        while (resultSet.next()) {
            swimmerAge = resultSet.getInt("swimmerAge");
        }
        pstmt2 = myConnector.prepareStatement(query2);
        pstmt2.executeUpdate();
        
        // guery 3
        pstmt3 = myConnector.prepareStatement(query3);
        if(swimmerAge <= 18) {
            pstmt3.setInt(1, 1);
            pstmt3.setString(2, "Ungdomshold");
            pstmt3.setString(3, "Erik Nielsen");
        } else {
            pstmt3.setInt(1, 2);
            pstmt3.setString(2, "Seniorhold");
            pstmt3.setString(3, "Cecilie Karlsen");
        }
        pstmt3.executeUpdate();

        pstmt.close();
        pstmt2.close();
        pstmt3.close();
        myConnector.close();
        resultSet.close();

    }

}
