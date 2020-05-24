public abstract class Event {

    /**
     * @param eventName is the name of the event
     * @param eventLocation is the location of the event
     * @param participants is the number of participants
     * @param eventService is the type of service offered
     * @param price is the price of the event
     */
    private String eventName;
    private Location eventLocation;
    private int participants;
    private static Service eventService;
    private double price;


    /**
     * Creates one event
     * @param x is the name of the event
     * @param y is the location of the event
     * @param z is the type of service offered
     */
    Event(String[] x, Location y, Service z) {
        this.eventName = x[1];
        this.eventLocation = y;
        this.participants = Integer.parseInt( x[5] );
        this.eventService = z;
        setEventPrice(eventService);

    }

    /**
     * Sets the price for the event
     * @param x is the number of hours for the event
     * @param y is the number of people at the event
     */
    public abstract void setPrice(long x, int y);

    /**
     * sets the number of participants
     */
    void setParticipants(int participants) {
        this.participants = participants;
    }

    int getParticipants() {
        return participants;
    }

    String getEventName() {
        return eventName;
    }

    Location getEventLocation() {
        return eventLocation;
    }

    double getPrice() {
        return this.price;
    }

    void setPrice(double price) {
        this.price = price ;
    }
    void setEventPrice(Service s){
        this.price += s.getPrice();
    }

    /**
     * Provide the user with information regarding the event
     * @return a string representation for the event including the name, location, number of participants and type of service
     */
    @Override
    public String toString() {
        return "Name of event: " + eventName + "\n" +
                eventLocation.toString() +
                "Participants to event: " + participants + "\n" +
                eventService.toString();
    }

    /**
     * Writes the event in the file on a single line
     * @return a string representation for the event
     */
    public String toFile() {
        return eventName + "," + getEventLocation().toFile() +
                participants + "," + eventService.toFile() + ",";
    }
}