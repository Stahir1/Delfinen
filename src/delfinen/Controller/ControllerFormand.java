package delfinen.Controller;

import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.View.MainMenuView;
import java.sql.SQLException;

/**
 *
 * @author Jimmy
 */
public class ControllerFormand {

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
    

    public void runFormandProg() throws SQLException {
        MedlemMedlemstype medlemsType = new MedlemMedlemstype(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        while (keepRunning) {

            menu.showMainMenuFormand();
            int number = scanners.IntScanner();

            switch (number) {
                case 1:
                    medlemsType.addMemberToDBProcess();
                    break;
                    
                case 2: 
                    medlemsType.getMembersFromDB();
                    break;
                case 3:
                    medlemsType.getMembersFromDB();
                    System.out.print("\n");
                    medlemsType.updateMemberProcess();
                    break;
                    
                case 0:
                    keepRunning = false;
                    break;
            }

        }
    }
}
