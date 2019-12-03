
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
     String query2 = "UPDATE delfinen.svømmehold SET teamID = ?, teamName = ?, trainer = ?";
     String query3 = "SELECT swimmerAge FROM delfinen.svømmehold WHERE ID = ?";
             
     Connection myConnector = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet resultSet = null;
        myConnector = DBConnector.getConnector();

        pstmt = myConnector.prepareStatement(query);
        pstmt2 = myConnector.prepareStatement(query);
        pstmt3 = myConnector.prepareStatement(query2);
        
        pstmt.setInt(1, choiceID);
        
       pstmt.executeUpdate();
        
        while (resultSet.next()) {
        int swimmerAge = resultSet.getInt("swimmerAge");
        pstmt2.executeUpdate();
        }
        
         
         pstmt.close();
         pstmt2.close();
         pstmt3.close();
         myConnector.close();
         resultSet.close();

    }
    
}
