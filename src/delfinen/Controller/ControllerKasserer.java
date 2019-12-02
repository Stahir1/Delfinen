package delfinen.Controller;

import delfinen.Model.Ledelsen.LedelsenKasser;
import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.View.MainMenuView;
import java.sql.SQLException;

/**
 *
 * @author Jimmy
 */
public class ControllerKasserer {

    private MainMenuView menu = new MainMenuView();
    private Controller scanners = new Controller();
    private String name = "";
    private int age = 0;
    private String email = "";
    private int phoneNumber = 0;
    private String city = "";
    private int zipCode = 0;
    private String address = "";
    private boolean competitiveSwimmer = false;
    private boolean active = false;
    private boolean keepRunning = true;
    private int choiceSubject = 0;
    private int choiceID = 0;

    public void runKassererProg() throws SQLException {
        LedelsenKasser kasser = new LedelsenKasser(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
        
        while (keepRunning) {

            menu.showMainMenuKasserer();
            int number = scanners.IntScanner();

            switch (number) {
                case 1:
                    kasser.createPaymentProcess(choiceID);
                    break;
                    
                case 2:
                    kasser.getContMembersFromDBInRestance();
                    break;

                case 0:
                    keepRunning = false;
                    break;
            }

        }
    }
}
