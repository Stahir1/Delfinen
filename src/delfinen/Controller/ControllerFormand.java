package delfinen.Controller;

import delfinen.Model.Ledelsen.LedelsenFormand;
import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.View.MainMenuView;
import java.util.InputMismatchException;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
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

    public void runFormandProg() {
        LedelsenFormand formand = new LedelsenFormand(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        while (keepRunning) {
            try {
                menu.showMainMenuFormand();
                int number = scanners.IntScanner();
                if (number == 0 || number == 1 || number == 2 || number == 3) {

                    switch (number) {
                        case 1:
                            formand.addMemberToDBProcess();
                            break;

                        case 2:
                            formand.getMembersFromDB();
                            break;
                        case 3:
                            formand.getMembersFromDB();
                            System.out.print("\n");
                            formand.updateMemberProcess();
                            break;

                        case 0:
                            keepRunning = false;
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Du skal indtaste et tal.");
            }
        }
    }
}
