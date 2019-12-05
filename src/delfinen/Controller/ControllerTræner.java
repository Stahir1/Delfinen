package delfinen.Controller;

import delfinen.Model.Ledelsen.LedelsenTræner;
import delfinen.View.MainMenuView;
import java.util.InputMismatchException;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
 */
public class ControllerTræner {

    private MainMenuView menu = new MainMenuView();
    private Controller scanners = new Controller();
    private String name = "";
    private int age = 0;
    private String email = "";
    private int phoneNumber = 0;
    private String city = "";
    private int zipCode = 0;
    private String address = "";
    private boolean competitiveSwimmer;
    private boolean active;
    private boolean keepRunning = true;

    public void runTrainerProg() {
        LedelsenTræner træner = new LedelsenTræner(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);

        while (keepRunning) {
            try {

                menu.showMainMenuTrainer();
                int number = scanners.IntScanner();
                if (number == 0 || number == 1 || number == 2) {
                    keepRunning = false;
                    switch (number) {
                        case 1:
                            menu.showMainMenuTrainerUpdateSwimmers();
                            number = scanners.IntScanner();
                            switch (number) {
                                case 1:
                                    træner.updateTrainingResultsProcess();
                                    break;
                                case 2:
                                    træner.updateEventResultsProcess();
                                    break;
                            }
                            break;

                        case 2:
                            træner.showTopFiveProcess();
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
