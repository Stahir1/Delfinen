/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinen.Main;

import delfinen.Controller.Controller;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Jimmy
 */
public class Svømmeklub {
    
    public static void main(String[] args) throws SQLException, ParseException {
        Controller program = new Controller();
        program.runProg();
    }
}
