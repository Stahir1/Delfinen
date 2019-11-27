package delfinen.Controller;

import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.View.MainMenuView;
import java.util.Scanner;

/**
 *
 * @author Danie
 */
public class Controller {

    public void runProg() {
        MainMenuView menu = new MainMenuView();
        
        
        boolean keepRunning = true;

        System.out.println("Velkommen til svømmeklubben - Delfinen.");
        while (keepRunning) {

            menu.showMainMenu();
            int number = IntScanner();

            switch (number) {
                case 1:
                    System.out.println("");
                    MedlemMedlemstype medlemsType = new MedlemMedlemstype();
                    medlemsType.addMedlemToDB(name, number, email, number, city, number, address, competitiveSwimmer, active);
            }

        }
    }
    // Vi har to forskellige scannere. Én til Int som eksempelvis bruges til at nagivere rundt i menuen.
    // Og én til String som bl.a. bruges til afhentningstidspunkt. 

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
}
