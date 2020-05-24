import java.util.Scanner;

public class Conference extends Event {
    /**
     * @param kbd lets the user input information when asked
     * @param tech is one of the partners of the HipHapOrg
     * @param cleaning is one of the partners of the HipHapOrg
     * @param transportation is one of the partners of the HipHapOrg
     */
    private static Scanner kbd = new Scanner( System.in );
    private Partner tech;
    private Partner cleaning;
    private Partner transportation;

    /**
     * Creates one conference event
     * @param x is the event name
     * @param y is the location
     * @param z is the type of service offered
     * @param te is the tech partner
     * @param cl is the cleaning partner
     * @param tr is the transportation partner
     */
    Conference(String[] x, Location y, Service z, Partner te, Partner cl, Partner tr) {
        super( x, y, z );
        tech = te;
        cleaning = cl;
        transportation = tr;
        setPrice( super.getEventLocation().getEHours(), super.getParticipants() );
    }

    /**
     *
     * @param x is the number of hours
     * @param y is the number of people
     */
    public void setPrice(long x, int y) {
        if (tech.getChoice())
            super.setPrice( getPrice() + tech.getPrice() * x );

        if (cleaning.getChoice())
            super.setPrice( getPrice() + cleaning.getPrice() * x );

        if (transportation.getChoice())
            super.setPrice( getPrice() + transportation.getPrice() * y );
    }

    /**
     *
     * @return a string made from 2 values
     */
    static String needPartners() {
        String firstPartner, secondPartner, thirdPartner;
        int answer;
        System.out.println( "Do you need tech equipment for your event? " +
                "\n(1 for yes/2 for no)" );
        do answer = kbd.nextInt();
        while (answer != 1 && answer != 2);
        if (answer == 1) firstPartner = "true,";
        else firstPartner = "false,";

        System.out.println( "Do you need catering service for your event? " +
                "\n(1 for yes/2 for no)" );
        do answer = kbd.nextInt();
        while (answer != 1 && answer != 2);
        if (answer == 1) secondPartner = "true,";
        else secondPartner = "false,";

        System.out.println( "Do you need cleaning service for your event? " +
                "\n(1 for yes/2 for no)" );
        do answer = kbd.nextInt();
        while (answer != 1 && answer != 2);
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
        if(transportation.getChoice())
            partners+="There is transportation included. Partner's e-mail: " + transportation.getContact();
        return partners;
    }

    /**
     * Provide the user with information regarding the event
     * @return a string representation for the event including the name, location, number of participants ,type of service and price
     */
    @Override
    public String toString() {
        return super.toString() + partners()+"\n"+
                "Total price of event: " + getPrice() + "\n";
    }

    /**
     * Writes the event in the file on a single line
     * @return a string representation for the event
     */
    public String toFile() {
        return super.toFile() + String.valueOf( tech.getChoice() ) + "," +
                String.valueOf( cleaning.getChoice() ) + "," +
                String.valueOf( transportation.getChoice() );
    }
}