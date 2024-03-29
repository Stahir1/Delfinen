package delfinen.Controller;

import delfinen.View.MainMenuView;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
 */
public class Controller {

    MainMenuView menu = new MainMenuView();
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

    public void runProg() {

        while (keepRunning) {
            try {
                menu.defaultShowMainMenu();
                int number = IntScanner();

                if (number == 0 || number == 1 || number == 2 || number == 3) {
                    
                    switch (number) {

                        case 1:
                            ControllerFormand formand = new ControllerFormand();
                            formand.runFormandProg();
                            break;

                        case 2:
                            ControllerKasserer kasserer = new ControllerKasserer();
                            kasserer.runKassererProg();
                            break;

                        case 3:
                            ControllerTræner træner = new ControllerTræner();
                            træner.runTrainerProg();
                            break;

                        case 0:
                            keepRunning = false;
                            break;
                    }

                } else {
                    // Hvis man hverken taster 1, 2, 3, 0 - kommer denne besked frem.
                    System.out.println("Du har ikke tastet et korrekt tal ind.");

                }

            } catch (InputMismatchException e) {
                // Hvis man indtaster bogstaver, tegn e.l. - kommer denne besked frem.
                System.out.println("Du skal indtaste et tal.");
            }
        }
    }

    // Her er scannerne som vi bruger i de forskellige classer ved at oprette
    // objektet "scanners" i klasserne.
    public int IntScanner() {
        Scanner myScan = new Scanner(System.in);
        int number = myScan.nextInt();

        return number;
    }

    public String StringScanner() {
        Scanner myScan = new Scanner(System.in);
        String bogstaver = myScan.nextLine();

        return bogstaver;
    }

    public boolean BoolScanner() {
        Scanner myScan = new Scanner(System.in);
        boolean bool = myScan.nextBoolean();

        return bool;
    }

    public double DoubleScanner() {
        Scanner myScan = new Scanner(System.in);
        double number = myScan.nextDouble();

        return number;
    }

}
