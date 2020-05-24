import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Location {
    /**
     * @param kbd is used by the user to input information when asked
     * @param name stores the name for location
     * @param eventDate is the date of the event
     * @param eHours is the number of hours for the event
     */
    private static Scanner kbd = new Scanner( System.in );
    private String name;
    private Date eventDate;
    private long eHours;

    /**
     * Creates one location
     * @param x is used to assign the name
     * @param y is used to assign the date
     */
    Location(String[] x, Date y) {
        this.name = x[2];
        this.eventDate = y;
        setEHours( eventDate );
    }

    /**
     * is used to set the number of hours
     * @param x is the date
     */
    private void setEHours(Date x) {
        this.eHours = ChronoUnit.HOURS.between( x.getStartDate(), x.getEndDate() );
    }

    /**
     *
     * @return the number of hours
     */
    long getEHours() {
        return eHours;
    }

    /**
     *
     * @return the event date
     */
    Date getEventDate() {
        return eventDate;
    }

    /**
     *
     * @return the name of the location
     */
    String getName() {
        return this.name;
    }

    /**
     * sets the name of the location
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param y is a value that makes one action happen
     * @return a location for the event based on the type
     */
    static String chooseLocation(int y) {
        switch (y) {
            case 1:
                return partyLocations();
            case 2:
                return tripLocations();
            case 3:
                return conferenceLocations();
        }
        return "";
    }

    /**
     *
     * @return one party location
     */
    private static String partyLocations() {
        System.out.println();
        System.out.println( "Pick a location for your party: " );
        System.out.println( "1. Gerald's fun house." );
        System.out.println( "2. House of mirrors." );
        System.out.println( "3. Bella Center" );

        int input;
        do {
            input = kbd.nextInt();
        } while (input < 1 || input > 3);

        switch (input) {
            case 1:
                return "Gerald's fun house";
            case 2:
                return "House of mirrors";
            case 3:
                return "Bella Center";
        }
        return "";
    }

    /**
     *
     * @return one trip location
     */
    private static String tripLocations() {
        System.out.println( "Pick a location for your trip: " );
        System.out.println( "1. Madrid" );
        System.out.println( "2. Rome." );
        System.out.println( "3. Paris" );

        int input;
        do {
            input = kbd.nextInt();
        } while (input < 1 || input > 3);

        switch (input) {
            case 1:
                return "Madrid";
            case 2:
                return "Rome";
            case 3:
                return "Paris";
        }
        return "";
    }

    /**
     *
     * @return one conference location
     */
    private static String conferenceLocations() {
        System.out.println( "Pick a location for your conference: " );
        System.out.println( "1. Talent Garden" );
        System.out.println( "2. Forum." );
        System.out.println( "3. Carlsberg Conference Center." );

        int input;
        do {
            input = kbd.nextInt();
        } while (input < 1 || input > 3);

        switch (input) {
            case 1:
                return "Talent Garden";
            case 2:
                return "Forum";
            case 3:
                return "Carlsberg Conference Center";
        }
        return "";
    }

    /**
     * Provide the user with a string representation for the location
     * @return a string representation
     */
    @Override
    public String toString() {
        return
                "Name of the location for the event: " + this.name + "\n" +
                        eventDate.toString();
    }

    /**
     * @return a string representation to the file on a single line
     */
    String toFile() {
        return name + "," + eventDate.toFile();
    }
}