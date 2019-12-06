
package delfinen.Model.Medlemmer;


/**
 *
 * @author Emil, Sohaib, Jimmy, Daniel
 */
public class MedlemMedlemstype extends Medlem {

    private boolean competitiveSwimmer;
    private boolean active;

    public MedlemMedlemstype(String name, int age, String email, int phoneNumber, String city, int zipCode, String address, boolean competitiveSwimmer, boolean active) {
        super(name, age, email, phoneNumber, city, zipCode, address);
        this.competitiveSwimmer = competitiveSwimmer;
        this.active = active;
    }

    @Override
    public String toString() {
        String n = ".\n";
        String compSwimWord;
        String activeWord;
        if (competitiveSwimmer == true) {
            compSwimWord = "Ja";
        } else {
            compSwimWord = "Nej";
        }

        if (active == true) {
            activeWord = "Aktivt";
        } else {
            activeWord = "Passivt";
        }

        return "\nNavn: " + super.getName() + n
                + "Alder: " + super.getAge() + n
                + "Email: " + super.getEmail() + n
                + "Tlf.: " + super.getPhoneNumber() + n
                + "By: " + super.getCity() + n
                + "Postnr.: " + super.getZipCode() + n
                + "Adresse: " + super.getAdress() + n
                + "Konkurrencesvømmer: " + compSwimWord + n
                + "Medlemskab: " + activeWord + n;
    }

}
