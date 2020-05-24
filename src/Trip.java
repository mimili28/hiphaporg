import java.util.Scanner;

public class Trip extends Event {
    /**
     * @param kbd is used by the user to input information when asked.
     * @param housing is one partner
     * @param transportation is one partner
     */
    private static Scanner kbd = new Scanner( System.in );
    private Partner housing;
    private Partner transportation;

    /**
     * Creates a Trip instance
     * @param x is the name
     * @param y is the location
     * @param z is the type of service
     * @param ho is housing partner
     * @param tr is transportation partner
     */
    Trip(String[] x, Location y, Service z, Partner ho, Partner tr) {
        super( x, y, z );
        housing = ho;
        transportation = tr;
        setPrice( super.getEventLocation().getEHours(), super.getParticipants() );
    }

    /**
     * It sets price based on the number of hours and number of people
     * @param x is the number of hours for the event
     * @param y is the number of people at the event
     */
    public void setPrice(long x, int y) {
        if (housing.getChoice())
            super.setPrice( housing.getPrice() * (x / 24 + 1) * y );

        if (transportation.getChoice())
            super.setPrice( getPrice() + transportation.getPrice() * y );
    }

    /**
     *
     * @return a string that stores the answers for partners
     */
    static String needPartners() {
        String firstPartner, secondPartner;
        int answer;
        System.out.println( "Do you need housing service for your event? \n(1 for yes/2 for no)" );
        do {
            answer = kbd.nextInt();
        } while (answer != 1 && answer != 2);
        if (answer == 1) firstPartner = "true,";
        else firstPartner = "false,";

        System.out.println( "Do you need transportation service for your event? \n(1 for yes/2 for no)" );
        do {
            answer = kbd.nextInt();
        } while (answer != 1 && answer != 2);
        if (answer == 1) secondPartner = "true";
        else secondPartner = "false";

        return firstPartner + secondPartner;
    }
    public String partners (){
        String partners="";
        if(housing.getChoice())
            partners+="There is housing included. Partner's e-mail: " + housing.getContact() + "\n";
        if(transportation.getChoice())
            partners+="There is transportation included. Partner's e-mail: " + transportation.getContact() + "\n";
        return partners;
    }

    /**
     * Provide the user with a string representation for the Party
     * @return a string representation
     */
    @Override
    public String toString() {
        return super.toString() + partners() + "\n"+
                "Total price of event: " + getPrice() + "\n";
    }


    /**
     * @return a string representation to the file on a single line
     */
    public String toFile() {
        return super.toFile() + String.valueOf( housing.getChoice() ) + "," +
                String.valueOf( transportation.getChoice() );
    }
}