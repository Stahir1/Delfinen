package delfinen.View;

/**
 *
 * @author Daniel, Emil, Sohaib, Jimmy
 */
public class MainMenuView {

    // Her ses main menuens standard layout i programmet.
    // Denne skærm vendes der hele tiden tilbage til, og er udgangspunktet for programmet.
    public void defaultShowMainMenu() {
        System.out.println("**************************************");
        System.out.println("Velkommen til svømmeklubben Delfinen.");
        System.out.println("Indtast din rolle.");
        System.out.println("Tast 1 for formand.");
        System.out.println("Tast 2 for kasserer.");
        System.out.println("Tast 3 for træner.");
        System.out.println("Tast 0 for at lukke programmet.");
        System.out.println("**************************************");
        System.out.println("Tast herunder...");
    }

    public void showMainMenuFormand() {
        System.out.println("**************************************");
        System.out.println("Tast 1 for at tilføje medlemmer.");
        System.out.println("Tast 2 for at se nuværende medlemmer.");
        System.out.println("Tast 3 for at redigere et medlem.");
        System.out.println("Tast 0 for at lukke programmet.");
        System.out.println("**************************************");
        System.out.println("Tast herunder...");
    }

    public void showMainMenuKasserer() {
        System.out.println("**************************************");
        System.out.println("Tast 1 for at oprette kontingentbetaling.");
        System.out.println("Tast 2 for at se medlemmer i restance.");
        System.out.println("Tast 3 for at administrere kontingentbetaling.");
        System.out.println("Tast 0 for at lukke programmet.");
        System.out.println("**************************************");
        System.out.println("Tast herunder...");
    }

    public void showMainMenuTrainer() {
        System.out.println("**************************************");
        System.out.println("Tast 1 for at opdatere svømmeresultater.");
        System.out.println("Tast 2 for at se top 5 svømmere.");
        System.out.println("Tast 0 for at lukke programmet.");
        System.out.println("**************************************");
        System.out.println("Tast herunder...");
    }
    
    public void showMainMenuTrainerUpdateSwimmers() {
        System.out.println("**************************************");
        System.out.println("Tast 1 for at opdatere træningsresult.");
        System.out.println("Tast 2 for at indtaste stævneresultater.");
        System.out.println("Tast 0 for at lukke programmet.");
        System.out.println("**************************************");
        System.out.println("Tast herunder...");
    }
    
    public void showAdministrationMenuCashier() {
        System.out.println("**************************************");
        System.out.println("Tast 1 for at registrere et medlem som havende betalt.");
        System.out.println("Tast 2 for at registrere et medlem som havende IKKE betalt.");
        System.out.println("Tast 0 for at lukke programmet.");
        System.out.println("**************************************");
        System.out.println("Tast herunder...");
    }

}
