public class Manager extends Employee {
    /**
     * @param name is the name of the manager
     * @param password is the password of the manager
     */
    protected String name;
    protected String password;

    /**
     * This creates an instance of the Manager class
     * @param name is the name given to the manager
     * @param password is the password given to the manager
     */
    protected Manager(String name, String password){
        super(name);
        this.password = password;
    }

    /**
     * This method lets the manager check the events of one employee
     * @param e is the eployee
     */
    public void checkEmployeeEvents(Employee e){
        int choice;
        for (Event ev: e.eventsArray)
        {
            System.out.println((e.eventsArray.indexOf(ev)+1)+ " " + ev.getEventName());
        }
        if((e.eventsArray.size()==0))
            System.out.println(e.getName() + " has no events");
        else {
            System.out.println("Which event do you want to check? Input number!");
            choice = kbd.nextInt();
            System.out.println(e.eventsArray.get(choice - 1).toString());
        }
    }

    /**
     *
     * @return a string representation for the password
     */
    public String getPassword() {
        return password;
    }
}