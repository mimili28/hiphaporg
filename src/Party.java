import java.util.Scanner;

public class Party extends Event {
    /**
     * @param kbd is used by the user to input information when asked.
     * @param tech
     * @param catering is a partner
     * @param cleaning is a partner
     */
    private static Scanner kbd = new Scanner( System.in );
    private Partner tech;
    private Partner catering;
    private Partner cleaning;

    /**
     * Creates a party object
     * @param x is the name
     * @param y is the location
     * @param z is the service type
     * @param t is the tech partner
     * @param ca is the catering partner
     * @param cl is the cleaning partner
     */
    Party(String[] x, Location y, Service z, Partner t, Partner ca, Partner cl) {
        super( x, y, z );
        tech = t;
        catering = ca;
        cleaning = cl;
        setPrice( super.getEventLocation().getEHours(), super.getParticipants() );
    }

    /**
     * sets the price based on the number of hours and people
     * @param x is the number of hours for the event
     * @param y is the number of people at the event
     */
    public void setPrice(long x, int y) {
        if (tech.getChoice())
            super.setPrice( getPrice() + tech.getPrice() * x );

        if (catering.getChoice())
            super.setPrice( getPrice() + catering.getPrice() * y );

        if (cleaning.getChoice())
            super.setPrice( getPrice() + cleaning.getPrice() * x );
    }

    /**
     *
     * @return a string that stores the answers for partners
     */
    static String needPartners() {
        String firstPartner, secondPartner, thirdPartner;
        int answer;
        System.out.println( "Do you need tech equipment for your event? " +
                "\n(1 for yes/2 for no)" );
        do {
            answer = kbd.nextInt();
        } while (answer != 1 && answer != 2);
        if (answer == 1) firstPartner = "true,";
        else firstPartner = "false,";

        System.out.println( "Do you need cleaning service for your event? " +
                "\n(1 for yes/2 for no)" );
        do {
            answer = kbd.nextInt();
        } while (answer != 1 && answer != 2);
        if (answer == 1) {
            secondPartner = "true,";
        } else {
            secondPartner = "false,";
        }
        System.out.println( "Do you need transportation service for your event? " +
                "\n(1 for yes/2 for no)" );
        do {
            answer = kbd.nextInt();
        } while (answer != 1 && answer != 2);
        if (answer == 1) thirdPartner = "true";
        else thirdPartner = "false";

        return firstPartner + secondPartner + thirdPartner;
    }


    public String partners (){
        String partners="";
        if(tech.getChoice())
            partners+="There is tech equipment included. Partner's e-mail: " + tech.getContact() + "\n";
        if(cleaning.getChoice())
            partners+="There is cleaning service included. Partner's e-mail: " + cleaning.getContact() + "\n";
        if(catering.getChoice())
            partners+="There is catering service included. Partner's e-mail: " + catering.getContact();
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
        return super.toFile() + String.valueOf( tech.getChoice() ) + "," +
                String.valueOf( catering.getChoice() ) + "," +
                String.valueOf( cleaning.getChoice() );
    }
}