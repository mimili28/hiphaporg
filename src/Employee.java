import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class Employee {

    /**
     * @param kbd is used by the user to input information when asked.
     * @param eventsArray is used to store the events.
     * @param email is used for the employee email address.
     * @param name is the name of the employee
     */
    static Scanner kbd = new Scanner(System.in);
    ArrayList<Event> eventsArray = new ArrayList<>();
    private String email;
    private String name;

    /**
     * Creates an employee
     * @param name for the name of the employee
     */
    Employee(String name) {
        this.name = name;
        this.email = name + "@HipHapOrg.dk";
    }

    /**
     *
     * @return the name
     */
    String getName() {
        return name;
    }

    /**
     * Lets the employee check if his events
     */
    void checkEvent() {
        int x;
        int choice;
        ArrayList<Event> check = new ArrayList<>();
        do {
            System.out.println("Which events do you want to see? (1 for Past Events / 2 for Ongoing Events / 3 for Future events / 4 for specific period of time)");
            x = kbd.nextInt();
        } while (x != 1 && x != 2 && x != 3 && x!=4);
        if (x == 1) {
            for (Event event : eventsArray) {
                if (event.getEventLocation().getEventDate().getEndDate().isBefore(LocalDateTime.now())) {
                    check.add(event);
                    System.out.println((check.indexOf(event) + 1) + " " + event.getEventName());
                }
            }
            if (check.size() == 0) {
                System.out.println("There are no past events.");
            }
        } else if (x == 2) {
            for (Event event : eventsArray) {
                if (event.getEventLocation().getEventDate().getStartDate().isBefore(LocalDateTime.now()) && event.getEventLocation().getEventDate().getEndDate().isAfter(LocalDateTime.now())) {
                    check.add(event);
                    System.out.println((check.indexOf(event) + 1) + " " + event.getEventName() + " ");
                }
            }
            if (check.size() == 0) {
                System.out.println("There are no ongoing events.");
            }
        } else if (x == 3) {
            for (Event event : eventsArray) {
                if (event.getEventLocation().getEventDate().getStartDate().isAfter(LocalDateTime.now())) {
                    check.add(event);
                    System.out.println((check.indexOf(event) + 1) + " " + event.getEventName() + " ");
                }
            }
            if (check.size() == 0) {
                System.out.println("There are no future events.");
            }
        }
        else
        {
            System.out.println("Enter first date:");
            kbd.nextLine();
            String firstDate = kbd.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime1 = LocalDateTime.parse(firstDate, formatter);
            System.out.println("Enter second date:");
            String secondDate = kbd.nextLine();
            LocalDateTime dateTime2 = LocalDateTime.parse(secondDate, formatter);
            for (Event event : eventsArray) {
                if (event.getEventLocation().getEventDate().getStartDate().isAfter(dateTime1) &&
                        event.getEventLocation().getEventDate().getEndDate().isBefore(dateTime2)) {
                    check.add(event);
                    System.out.println((check.indexOf(event) + 1) + " " + event.getEventName() + " ");
                }
            }
            if (check.size()==0)
                System.out.println("There are no event at this period of time.");
        }
        if (check.size() != 0) {
            System.out.println("Which event do you want details for? Input a number from the list above! ");
            do {
                choice = kbd.nextInt();
                if (choice < 1 || choice > check.size()) {
                    System.out.println("Wrong input!");
                }
            } while (choice < 1 || choice > check.size());
            System.out.println(check.get(choice - 1).toString());
        }

    }

    /**
     * lets the employee modifies the details from one of his events
     */
    void modifyEvent(ArrayList<Event> eventsArray) {
        System.out.println("Here is a list with ongoing and upcoming events:");
        int counter = 0;
        for (int i = 0; i < eventsArray.size(); i++) {
            if (eventsArray.get(i).getEventLocation().getEventDate().getEndDate().isAfter(LocalDateTime.now())) {
                System.out.println((i + 1) + " " + eventsArray.get(i).getEventName());
                counter++;
            }
        }
        if (counter != 0) {
            System.out.println("Which event do you want to modify ? Choose a number from the list above! ");
            int chosenEvent = kbd.nextInt();
            System.out.println(eventsArray.get(chosenEvent - 1).toString());
            System.out.println("This is what you can modify: \n1. Location \n2. Start date and time" + "\n3. End date and time \n4. Number of participants");
            System.out.println("Please input a number according to what you want to modify. ");
            int chosenData = kbd.nextInt();
            kbd.nextLine();
            switch (chosenData) {
                case 1:
                    System.out.println("You chose to modify the location of the event " + "\nThe current location of the event is " + eventsArray.get(chosenEvent - 1).getEventLocation().getName() + "\nPlease input the new location name :");
                    String newLocationName = kbd.nextLine();
                    eventsArray.get(chosenEvent - 1).getEventLocation().setName(newLocationName);
                    break;

                case 2:
                    System.out.println("You chose to modify the start date and time of the event" + "\nThe current start date and time of the event is " + eventsArray.get(chosenEvent - 1).getEventLocation().getEventDate().getStartDate() + "\nPlease input the new start date according to this format(yyyy-MM-dd HH:mm)");
                    String newStartDate = kbd.nextLine();
                    eventsArray.get(chosenEvent - 1).getEventLocation().getEventDate().setStartDate(newStartDate);
                    break;

                case 3:
                    System.out.println("You chose to modify the end date and time of the event" + "\nThe current end date and time of the event is " + eventsArray.get(chosenEvent - 1).getEventLocation().getEventDate().getEndDate() + "\nPlease input the new end date according to this format(yyyy-MM-dd HH:mm)");
                    String newEndDate = kbd.nextLine();
                    eventsArray.get(chosenEvent - 1).getEventLocation().getEventDate().setEndDate(newEndDate);
                    break;

                case 4:
                    System.out.println("You chose to modify the number of participants to event " + "\nThe current number of participants is " + eventsArray.get(chosenEvent - 1).getParticipants() + "\nPlease input a new number of participants ");
                    int newParticipants = kbd.nextInt();
                    eventsArray.get(chosenEvent - 1).setParticipants(newParticipants);
                default:
                    break;
            }
        }

    }

    /**
     * is used by the employee to remove one event
     */
    void deleteEvent(ArrayList<Event> eventsArray) {
        System.out.println("Which event do you want to delete? " + "\n 1 for Past Event and 2 for Current and Future Events");
        int type = kbd.nextInt();
        kbd.nextLine();
        switch (type) {
            case 1:
                System.out.println("You chose to delete a past event." + "Here is a list with past events. " + "Input a number of the event you want to delete");
                int counterEvents = 0;
                for (int i = 0; i < eventsArray.size(); i++)
                    if (eventsArray.get(i).getEventLocation().getEventDate().getEndDate().isBefore(LocalDateTime.now())) {
                        System.out.println((i + 1) + " " + eventsArray.get(i).getEventName());
                        counterEvents++;
                    }
                if (counterEvents != 0) {
                    int pastEventIndex;
                    do {
                        pastEventIndex = kbd.nextInt();
                        if (pastEventIndex < 1 || pastEventIndex > eventsArray.size()){
                            System.out.println("Wrong input!");
                        }
                    } while (pastEventIndex < 1 || pastEventIndex > eventsArray.size());
                    System.out.println(eventsArray.get(pastEventIndex - 1).toString() + "\n" + "Are you sure you want to delete the event detailed above?" + " 1 for Yes and 2 for No");
                    int pastAnswer = kbd.nextInt();
                    if (pastAnswer == 1) {
                        eventsArray.remove(pastEventIndex - 1);
                        System.out.println("Event successfully deleted! ");
                    } else break;

                } else System.out.println("There are no past events.");
                break;

            case 2:
                System.out.println("You chose to delete a current/future event." + "Here is a list with current and future events. " + "Input a number of the event you want to delete");
                int counterEventsCase2 = 0;
                for (int i = 0; i < eventsArray.size(); i++)
                    if (eventsArray.get(i).getEventLocation().getEventDate().getEndDate().isAfter(LocalDateTime.now())) {
                        System.out.println((i + 1) + " " + eventsArray.get(i).getEventName());
                        counterEventsCase2++;
                    }
                if (counterEventsCase2 != 0) {
                    int futureEventIndex = kbd.nextInt();
                    System.out.println(eventsArray.get(futureEventIndex - 1).toString() + "\n" + "Are you sure you want to delete the event detailed above?" + " 1 for Yes and 2 for No");
                    int answer = kbd.nextInt();
                    if (answer == 1) {
                        eventsArray.remove(futureEventIndex - 1);
                        System.out.println("Event successfully deleted! ");
                    } else break;
                } else System.out.println("There are no ongoing or future events");
                break;
            default:
                break;
        }
    }
}