/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Util;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Danie
 */
public class DBConnectorTest {
    
    public DBConnectorTest() {
    }

    /**
     * Test of getConnector method, of class DBConnector.
     */
    @Test
    public void testGetConnector() {
        System.out.println("getConnector");
        Connection result = DBConnector.getConnector();
        assertEquals(result != null, true);
    }
    
}
