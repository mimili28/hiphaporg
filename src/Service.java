public class Service {
    /**
     * @param name is the name of the service
     * @param price is the price of the service
     */
    private String name;
    private double price;

    /**
     * Creates a service instance
     * @param x is the name of the service
     */
    Service(String[] x) {
        this.name = x[6].toUpperCase();
        setPrice();
    }

    /**
     * Set the price based on the name
     */
    private void setPrice() {
        if (this.name.equals( "CONSULTANCY" )) {
            this.price = 500;
        } else this.price = 3000;
    }

    /**
     *
     * @return the price
     */
    double getPrice() {
        return price;
    }

    /**
     * Provide the user with a string representation for the Party
     * @return a string representation
     */
    @Override
    public String toString() {
        return "Type of service: " + name + "\n" +
                "Estimated price of service: " + price + "\n";
    }

    /**
     * @return a string representation to the file on a single line
     */
    String toFile() {
        return name;
    }
}