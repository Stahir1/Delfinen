package delfinen.Controller;

import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.View.MainMenuView;
import java.sql.SQLException;

/**
 *
 * @author Jimmy
 */
public class ControllerFormand {

    MainMenuView menu = new MainMenuView();
    Controller scanners = new Controller();
    String name = "";
    int age = 0;
    String email = "";
    int phoneNumber = 0;
    String city = "";
    int zipCode = 0;
    String address = "";
    boolean competitiveSwimmer = false;
    boolean active = false;
    boolean keepRunning = true;
    int choiceSubject = 0;
    int choiceID = 0;
    

    public void runFormandProg() throws SQLException {
        MedlemMedlemstype medlemsType = new MedlemMedlemstype(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        while (keepRunning) {

            menu.showMainMenuFormand();
            int number = scanners.IntScanner();

            switch (number) {
                case 1:
                    medlemsType.addMemberProcess();
                    break;
                    
                case 2: 
                    medlemsType.getMembersFromDB();
                    break;
                case 3:
                    medlemsType.getMembersFromDB();
                    System.out.print("\n");
                    medlemsType.updateMemberProcess();
                    
                case 0:
                    keepRunning = false;
                    break;
            }

        }
    }
}
