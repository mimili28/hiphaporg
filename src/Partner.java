public class Partner {
    /**
     * @param price is the price of the partner
     * @param contact is the email of the partner
     * @param choice shows if the partner was chosen or not
     */
    private double price;
    private String contact;
    private boolean choice;

    /**
     * Creates a partner
     * @param x is the price
     * @param y is the email
     */
    Partner(double x, String y) {
        this.price = x;
        this.contact = y;
    }

    /**
     *
     * @return if the partner was chosen or not
     */
    boolean getChoice() {
        return choice;
    }

    /**
     *
     * @return the price
     */
    double getPrice() {
        return price;
    }

    /**
     * return true or false based on the decision
     * @param choice is a string that stores either the partner was selected or not
     */
    void setChoice(String choice) {
        if (choice.equals( "true" ))
            this.choice = true;
        if (choice.equals( "false" ))
            this.choice = false;
    }

    String getContact(){
        return this.contact;
    }

    /**
     * Provide the user with a string representation for the partner
     * @return a string representation
     */
    public String toString() {
        return price + contact + choice;
    }
}
