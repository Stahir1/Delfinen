
package delfinen.Main;

import delfinen.Controller.Controller;


/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
 */
public class Svømmeklub {
    
    public static void main(String[] args) {
        // "program.runProg();" henviser til Controller-klassen.
        // under den er 3 nye runProg-metoder; Formand, Kasserer, Træner.
        Controller program = new Controller();
        program.runProg();
    }
}
