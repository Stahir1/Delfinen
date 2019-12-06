package delfinen.Controller;

import delfinen.Model.Ledelsen.LedelsenKasser;
import delfinen.View.MainMenuView;
import java.util.InputMismatchException;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
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

    public void runKassererProg() {
        LedelsenKasser kasser = new LedelsenKasser(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        while (keepRunning) {
            try {
                menu.showMainMenuKasserer();
                int number = scanners.IntScanner();
                if (number == 0 || number == 1 || number == 2 || number == 3) {

                    switch (number) {
                        case 1:
                            kasser.createPaymentProcess();
                            break;

                        case 2:
                            kasser.getContMembersFromDBInRestance();
                            break;

                        case 3:
                            menu.showAdministrationMenuCashier();
                            number = scanners.IntScanner();
                            switch (number) {
                                case 1:
                                    kasser.updateHasPaidProcess();
                                    break;

                                case 2:
                                    kasser.removeHasPaidProcess();
                                    break;
                            }
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
