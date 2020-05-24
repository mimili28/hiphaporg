/**
 * The HipHapOrg program is an application that will make the life of the HipHapOrg employees much easier
 *
 * @author Maria Ilieva,Aaron ALAYO, Marian Mocanu, Catalin Atanase
 * @version 1.0
 * @since 2018-12-09
 */

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class HipHapOrg {

    /**
     * @param kbd is a scanner that lets the user inputs information when asked.
     */
    private static Scanner kbd = new Scanner( System.in );

    /**
     * @param eventsFile is the file that is considered the database for this project
     */
    private static File eventsFile = new File( "events.txt" );
    /**
     * The manager instance
     */
    private static Manager manager = new Manager("Aaron", "hiphap");

    /**
     * @param employee1
     * @param employee2
     * @param employee3
     * The Employee instances are the users of the system.
     */
    private static Employee employee1 = new Employee( "Maria" );
    private static Employee employee2 = new Employee( "Marian" );
    private static Employee employee3 = new Employee( "Catalin" );

    /**
     * @param tech
     * @param housing
     * @param cleaning
     * @param catering
     * @param transportation
     * @param isOk is used to know if the user wants to keep on using the program or wants to close it
     * The partners of the HipHapOrg
     */
    private static  Partner tech = new Partner( 100, "tech@system.dk" );
    private static Partner housing = new Partner( 500, "housing@system.dk" );
    private static Partner cleaning = new Partner( 125, "cleaning@system.dk" );
    private static Partner catering = new Partner( 50, "catering@system.dk" );
    private static Partner transportation = new Partner( 200, "transportation@system.dk" );

    public static void main(String[] args) {

        System.out.println("Welcome to HipHapOrg service! \nCurrent date and time "+ LocalDateTime.now().withNano(0)+"\n");
        boolean isOk;
        createFile( eventsFile );
        readFileAndCreateEvents( eventsFile );
        do {
            startSystem();
            isOk = keepGoing();
        } while (isOk);

    }

    /**
     *
     * @param x is the file on which we store the events
     */
    private static void createFile(File x) {
        try {
            x.createNewFile();
        } catch (IOException e) {
            System.out.println( "Exception: " + e );
        }
        //if (x.exists()) System.out.println( "File opened!" );
        //else System.out.println( "File created! " );
    }

    /**
     * Create an event which is added in the arraylist
     * @param x is the file on which we store the events
     *
     */
    private static void readFileAndCreateEvents(File x) {
        try {
            Scanner qwerty = new Scanner( x );
            while (qwerty.hasNextLine()) {

                //initializing set of data to be used when calling an event constructor
                String[] eventDetails = qwerty.nextLine().split( "," );
                Date eventD = new Date( eventDetails );
                Location eventL = new Location( eventDetails, eventD );
                Service eventS = new Service( eventDetails );

                if (eventDetails[0].equals( "1" )) {
                    tech.setChoice( eventDetails[7] );
                    catering.setChoice( eventDetails[8] );
                    cleaning.setChoice( eventDetails[9] );
                    employee1.eventsArray.add( new Party( eventDetails, eventL, eventS, tech, catering, cleaning ) );
                }
                if (eventDetails[0].equals( "2" )) {
                    housing.setChoice( eventDetails[7] );
                    transportation.setChoice( eventDetails[8] );
                    employee2.eventsArray.add( new Trip( eventDetails, eventL, eventS, housing, transportation ) );
                }

                if (eventDetails[0].equals( "3" )) {
                    tech.setChoice( eventDetails[7] );
                    cleaning.setChoice( eventDetails[8] );
                    transportation.setChoice( eventDetails[9] );
                    employee3.eventsArray.add( new Conference( eventDetails, eventL, eventS, tech, cleaning, transportation ) );
                }
            }
        } catch (IOException e) {
            System.out.println( "Exception: " + e );
        }
    }

    /**
     * Starts the system and asks the user for his name or name and password if the user is the manager
     */
    private static void startSystem() {
        String username;
        String password;
        do {
            System.out.print( "Input your name: " );
            username = kbd.nextLine();
            if(!username.equals( employee1.getName() ) && !username.equals( employee2.getName() ) && !username.equals( employee3.getName()) && !username.equals(manager.getName())){
                System.out.println("Wrong username!");
            }
        } while (!username.equals( employee1.getName() ) && !username.equals( employee2.getName() ) && !username.equals( employee3.getName()) && !username.equals(manager.getName()));

        if(username.equals(manager.getName())) {
            do {
                System.out.print("Enter the password: ");
                password = kbd.nextLine();
            } while(!password.equals(manager.getPassword()));
        }

        if (username.equals( employee1.getName() )) {
            optionEmployee( eventsFile, employee1, eventsFile );
        } else if (username.equals( employee2.getName() )) {
            optionEmployee( eventsFile, employee2, eventsFile );
        } else if (username.equals( employee3.getName() )) {
            optionEmployee( eventsFile, employee3, eventsFile );
        } else if(username.equals(manager.getName())) {
            optionManager(eventsFile, eventsFile);
        }
    }

    /**
     * Writes the event in the file
     * @param x is the file on which we store the events
     */
    private static void writeEventIn(File x) {
        try {
            FileWriter planner = new FileWriter( x.getPath(), true );
            System.out.println( "\n Input a number for the type of event: \n 1 for party \n 2 for trip \n 3 for conference. \n" );
            int typeOfEvent, typeOfService;
            do {
                typeOfEvent = kbd.nextInt();
                kbd.nextLine();
            } while (typeOfEvent != 1 && typeOfEvent != 2 && typeOfEvent != 3);

            planner.write( "\n" );
            planner.write( typeOfEvent + "," );
            System.out.println( "\n Input name of the event: " );
            planner.write( kbd.nextLine() + "," );
            planner.write( Location.chooseLocation( typeOfEvent ) + "," );
            System.out.println( "\n Input start date and time of event (yyyy-MM-dd HH:mm): " );
            planner.write( kbd.nextLine() + "," );
            System.out.println( "\n Input end date and time of event (yyyy-MM-dd HH:mm): " );
            planner.write( kbd.nextLine() + "," );
            System.out.println( "\n Input number of participants to event: " );
            planner.write( kbd.nextLine() + "," );
            System.out.println( "\n Input a number for the type of service: \n 1 for Consultancy \n 2 for Planning " );
            do typeOfService = kbd.nextInt();
            while (typeOfService != 1 && typeOfService != 2);

            if (typeOfService == 1) planner.write( "Consultancy" + "," );
            else planner.write( "Planning" + "," );

            if (typeOfEvent == 1) planner.write( Party.needPartners() );
            else if (typeOfEvent == 2) planner.write( Trip.needPartners() );
            else planner.write( Conference.needPartners() );

            planner.flush();
            planner.close();
        } catch (IOException e) {
            System.out.println( "Exception: " + e );
        }
    }

    /**
     * Shows a menu to the employee from which he can choose his next action
     */
    private static void menuEmp() {
        System.out.println( " /\\/\\/\\/\\/\\/\\/\\/\\/\\" );
        System.out.println( "< Choose an option >" );
        System.out.println( "< ---------------- >" );
        System.out.println( "< 1. Create Event  >" );
        System.out.println( "< 2. Check Event   >" );
        System.out.println( "< 3. Modify Event  >" );
        System.out.println( "< 4. Delete Event  >" );
        System.out.println( "< 0. Exit          >" );
        System.out.println( " \\/\\/\\/\\/\\/\\/\\/\\/\\/" );
    }

    /**
     * Does the update on the file after something has changed
     * @param x is the file on which we store the events
     */
    private static void updateFile(File x) {
        try {
            FileWriter planner = new FileWriter( x.getPath(), false );

            for (Event event : employee1.eventsArray) {
                planner.write( "1," + event.toFile() + "\n" );
            }
            for (Event event : employee2.eventsArray) {
                planner.write( "2," + event.toFile() + "\n" );
            }
            for (Event event : employee3.eventsArray) {
                if (event.equals( employee3.eventsArray.get( employee3.eventsArray.size() - 1 ) ))
                    planner.write( "3," + event.toFile() );
                else planner.write( "3," + event.toFile() + "\n" );
            }
            planner.flush();
            planner.close();
        } catch (IOException e) {
            System.out.println( "Exception " + e );
        }
    }

    /**
     * Shows a menu to the manager from which he can choose his next action
     */
    private static void menuMan() {
        System.out.println( " /\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\" );
        System.out.println( "<     Choose an option      >" );
        System.out.println( "< ------------------------- >" );
        System.out.println( "< 1. Check Employee's Event >" );
        System.out.println( "< 2. Modify Event           >" );
        System.out.println( "< 3. Delete Event           >" );
        System.out.println( " \\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/" );
    }

    /**
     * After the manager selects an option from the menu that option is going to be executed in this method
     * @param events is the file on which we store the events
     * @param updatedEvents in case the user wants to use a new file for the database
     */
    private static void optionManager(File events, File updatedEvents) {
        menuMan();
        int input;
        int counter = 0;
        do {
            input = kbd.nextInt();
            if (counter > 0) {
                System.out.println( "Choose an option! " );
            }
            counter++;
        } while (input < 1 || input > 3);
        switch (input) {
            case 1:
                int choice;
                System.out.println( "\n Whose event do you want to check? \n 1. " + employee1.getName() + "\n 2. " + employee2.getName() + "\n 3. " + employee3.getName());
                choice = kbd.nextInt();
                if (choice == 1) manager.checkEmployeeEvents( employee1 );
                if (choice == 2) manager.checkEmployeeEvents( employee2 );
                if (choice == 3) manager.checkEmployeeEvents( employee3 );
                break;
            case 2:
                System.out.println( "\n Whose event do you want to modify? \n 1. " + employee1.getName() + "\n 2. " + employee2.getName() + "\n 3. " + employee3.getName());
                choice = kbd.nextInt();
                if (choice == 1) manager.modifyEvent( employee1.eventsArray );
                if (choice == 2) manager.modifyEvent( employee2.eventsArray );
                if (choice == 3) manager.modifyEvent( employee3.eventsArray );
                updateFile(updatedEvents );
                break;
            case 3:
                System.out.println( "\n Whose event do you want to delete? \n 1. " + employee1.getName() + "\n 2. " + employee2.getName() + "\n 3. " + employee3.getName());
                choice = kbd.nextInt();
                if (choice == 1) manager.deleteEvent( employee1.eventsArray );
                if (choice == 2) manager.deleteEvent( employee2.eventsArray );
                if (choice == 3) manager.deleteEvent( employee3.eventsArray );
                updateFile( updatedEvents );
                break;
        }
    }


    /**
     * After the user selects an option from the menu the action is going to be executed in this method
     * @param events is the file on which we store the events
     * @param employee is the person that is going to have assigned an event
     * @param updatedEvents in case the user wants to use a new file for the database
     */
    private static void optionEmployee(File events, Employee employee, File updatedEvents) {
        menuEmp();
        int input;
        int counter = 0;
        do {
            if (counter > 0) {
                System.out.println( "\n Choose an option! " );
            }
            input = kbd.nextInt();
            counter++;
        } while (input < 0 || input > 4);
        switch (input) {
            case 1:
                writeEventIn( events );
                readFileAndCreateEvents( events );
                break;
            case 2:
                employee.checkEvent();
                break;
            case 3:
                employee.modifyEvent( employee.eventsArray );
                updateFile( events );
                break;
            case 4:
                employee.deleteEvent( employee.eventsArray );
                updateFile( events );
                break;
            case 0:
                break;
        }
    }

    /**
     * Asks the user if he wants to use the program anymore
     * @return true if the user wants to keep using the program or false if he doesn't
     */
    private static boolean keepGoing() {
        System.out.println( "Would you like to do something else?(1. Yes/2. No)" );
        int input;
        do {
            input = kbd.nextInt();
            kbd.nextLine();
        } while (input < 1 || input > 2);
        return input == 1;
    }


}