package delfinen.Controller;

import delfinen.Model.Medlemmer.MedlemMedlemstype;
import delfinen.View.MainMenuView;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Danie
 */
public class Controller {

    public void runProg() throws SQLException {
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

        System.out.println("Velkommen til svømmeklubben - Delfinen.");
        while (keepRunning) {

            menu.showMainMenu();
            int number = IntScanner();

            switch (number) {
                case 1:
                    MedlemMedlemstype medlemsType = new MedlemMedlemstype(name, age, email, phoneNumber, city, zipCode, address, competitiveSwimmer, active);
                    medlemsType.addMemberProcess();
                    break;
                    
                case 8:
                    keepRunning = false;
                    break;
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

    public boolean BoolScanner() {
        Scanner myScan = new Scanner(System.in);
        boolean bool = myScan.nextBoolean();

        return bool;
    }

}
