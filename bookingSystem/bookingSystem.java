import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
/**
 * A booking system which stores the details of events and customers .
 *
 * @author Ross Crowley
 * Student Number: C20410104
 */
public class bookingSystem
{
    /** Declare ArrayLists */

    // Events ArrayList
    ArrayList<Event> Events;
    final int EVENT_SIZE = 3;  //number of Events

    //Customer ArrayList
    ArrayList<Customer> customerBookings;

    /** CONSTRUCTOR */
    public bookingSystem()
    {
        int option = 0;
        System.out.print("\f");

        // Create Events
        Events = new ArrayList<Event>();
        createEvents();

        customerBookings = new ArrayList<Customer>();

        System.out.print("\f");

        //Carry out menu options
        do
        {
            option = processMenu();

            if (option == 1)
            {
                buyTickets();
            } 

            if (option == 2)
            {
                System.out.print("\f");
                cancelTickets();
            } 

            if (option == 3)
            {
                System.out.print("\f");
                displayEvents();
            }

            if(option == 4)
            {
                System.out.print("\f");
                popularEvent();
            }

            if(option == 5)
            {
                System.out.print("\f");
                winVipTickets();
            }
                
            if(option == 6)
            {
                System.out.print("\f");
                displayCustomers();
            }
        }while (option != 7);// carry out process menu while menu option 7 is not chosen (Exit)

        System.out.println("You have now exited the booking system. Goodbye!");
    }

    /** Create 3 Events */

    public void createEvents()
    {
        Scanner scan = new Scanner(System.in);
        String eventName;
        String eventTime;
        String eventDay;
        int ticketsBooked ;
        Event anEvent;

        System.out.println("\nCreate " + EVENT_SIZE + " events.");
        for (int count = 1; count <= EVENT_SIZE; count++)
        {
            System.out.print("\nEnter the name of the event for event " + count + " : ");
            eventName = scan.nextLine();
            System.out.print("\nEnter the day the event takes place for event " + count + " : ");
            eventDay = scan.nextLine();
            System.out.print("\nEnter the time of day (Late Night or Evening) for event " + count + " : ");
            eventTime = scan.nextLine();

            //create an Event from those details
            anEvent = new Event(eventName, eventDay, eventTime,0 );

            //add the Event to the ArrayList
            Events.add(anEvent);
        }
    }

    /** 4. Display the menu*/
    public void displayMenu()
    {
        System.out.println("\n\n1.Buy tickets for an event:  " );
        System.out.println("2.Cancel tickets for an event:  ");
        System.out.println("3.Display Full Event Schedule: " );
        System.out.println("4.Most popular Event:  " );
        System.out.println("5.Enter into Lucky VIP!");
        System.out.println("6.Display Customers");
        System.out.println("7.Exit System");
    }

    /** 4.1 Process Menu Option */
    public int processMenu()
    {
        Scanner scan = new Scanner(System.in);
        int menuOption = 0;

        //call Menu() to display the menu 
        displayMenu();

        do
        {
            System.out.println("Input menu option (i.e. 2):  " );
            menuOption = scan.nextInt();

            if (menuOption < 1 || menuOption > 7)
            {
                System.out.println("Invalid menu option. Please input a valid menu option (1-7):  " ); 
            }

        }while (menuOption < 1 || menuOption > 7);
        return menuOption;
    }

    /** 5 Sell tickets for an event*/
    public void buyTickets()
    {
        System.out.print("\f");

        String name = "";
        String email = "";
        String event = "";
        String day;
        int numOfTickets = 0;
        int updateNumOfTick = 0;
        int location;
        Customer customer1 ;
        Scanner scan = new Scanner(System.in);

        /**5.1 Ask user to input the event and day they wish to attend*/

        System.out.print("\nEnter event you wish to attend : " );
        event = scan.nextLine();

        System.out.print("\nEnter the day of the week you wish to attend the event : ");
        day = scan.nextLine();

        /**5.2 Check to see if the event exist */
        location = getEventLocation(event, day);

        /**5.4 Indicate if Event Exists  */
        if(location == 999)
        {
            System.out.println("There is no such Event available");
        }
        else
        {

            System.out.println(  Events.get(location).getEventName() + " has a " + Events.get(location).getTime() + " show on " + Events.get(location).getDay());
            /**5.2.3.Check if there are enough tickets */ 
            if(Events.get(location).getNumberOfTickets() < 50)
            {
                System.out.println("there are tickets available for " + Events.get(location).getEventName() + " on " + Events.get(location).getDay());
                System.out.println();

                /**5.3 Get Number of Tickets the Customer Wishes to Buy and Sell the tickets*/

                System.out.print("\nEnter the number of tickets you wish to buy : " );
                numOfTickets = scan.nextInt();
                scan.nextLine();

                // check if there is enough tickets
                if ( numOfTickets < Events.get(location).unsoldTickets())
                {

                    System.out.print("\nEnter your name: " );
                    name = scan.nextLine();
                    System.out.print("\nEnter your email:  " );
                    email = scan.nextLine();

                    System.out.print("\f");
                    System.out.println("Congrats " + name + "! You have succesfully booked " + numOfTickets + " tickets for " + Events.get(location).getEventName());

                    /**5.3.1 Update Number of Tickets Sold*/

                    updateNumOfTick = Events.get(location).getNumberOfTickets();

                    updateNumOfTick = updateNumOfTick + numOfTickets ;

                    Events.get(location).setNumberOfTicket(updateNumOfTick);
                }
                else
                {
                    System.out.println("Sorry not enough tickets available please refer to our event schedule for details of other similar events");
                    displayEvents();
                }
            }
            else
            {
                System.out.println("This event is sold out. Please view out events schedule for other possible events ");
                displayEvents();
            }

            /** 5.4.Get customer details*/
            customer1 = new Customer(name, email, event, numOfTickets, 0);
            /**5.4.1. Store customer details*/
            //add to arraylist.
            customerBookings.add(customer1);

            System.out.println("Press return to continue ");
            scan.nextLine();
            System.out.print("\f");
        }

    }

    /** 5.2 Check If Event Exists */
    /**5.2.1. Check to see if the event is on the same day*/
    public int getEventLocation(String event, String day)
    {
        int location=999;

        for (int count = 0; count < Events.size(); count++)
        {
            if (event.equalsIgnoreCase(Events.get(count).getEventName()) && day.equalsIgnoreCase(Events.get(count).getDay()))
            {
                location = count;
            }
        }

        return location;
    }

    /** 5.3 Check If Event Exists for cancelTickets() */
    public int getEventLocationCancel(String event)
    {
        int location=999;

        for (int count = 0; count < Events.size(); count++)
        {
            if (event.equalsIgnoreCase(Events.get(count).getEventName()) )
            {
                location = count;
            }
        }

        return location;
    }

    /**6. Cancel tickets for an event */
    public void cancelTickets()
    {
        Scanner scan = new Scanner(System.in);
        String name;
        String event;
        int updateTickets;
        Customer c = null;
        boolean found = false;
        int location;

        /**6.1. Ask customer to input name*/
        System.out.print("Please input your name: ");
        name = scan.nextLine();

        /**6.2. Check if customer exists */
        for (Customer aCustomer : customerBookings)
        {
            if (name.equalsIgnoreCase(aCustomer.getCustomerName()))
            {
                c = aCustomer; //save the location of the customer we wish to delete in c as aCustomer will be deleted once loop is finished
                found = true;
            }

        }

        if (found == true) 
        {
            System.out.println("Hello " + c.getCustomerName() + " we have found your details");

            /**6.3. Ask user to input the event they were going to attend */
            System.out.print("Please input the event you were going to attend : ");
            event = scan.nextLine();

            if (event.equalsIgnoreCase(c.getNameOfEvent()))
            {
                System.out.println("Yes, you did have tickets for this event "); 
                System.out.println("We will cancel your tickets now and remove your details");

                /**6.3.1. If the event matches the event they were going to attend remove customer*/
                customerBookings.remove(c);

                /**6.4. update the amount of tickets booked for the event*/
                location = getEventLocationCancel(event);

                updateTickets = Events.get(location).getNumberOfTickets() - c.getTicketBooked();

                Events.get(location).setNumberOfTicket(updateTickets);
            }
            else
            {
                System.out.println("You have not booked tickets for this event  "); 
            }
        }
        else
        {
            System.out.println("No such customer with that name");
        }

    }

    public void winVipTickets()
    {
        Scanner scan = new Scanner(System.in);

        /**7.1. Generate a random number*/
        Random dice = new Random();
        int randomNumber = 1+dice.nextInt(6);
        int eventOption;
        int playersNumber;

        System.out.println("Welcome to Lucky VIP!");
        System.out.println("The game is simple! To enter type in a number between 1 and 6 and we will roll our virtual die to see if your number is the winning number!");
        /**7.2. Ask user to enter the game by inputting a number between 1-6*/
        do
        {
            System.out.println("Please enter a number between 1-6");
            playersNumber = scan.nextInt();
            scan.nextLine();
            if (playersNumber < 1 || playersNumber > 6)
            {
                System.out.println("Oops invalid number, please enter a number between 1 and 6");
            }
        } while (playersNumber < 1 || playersNumber > 6);

        /**7.2.1.Check if the random number equals the number input*/
        if( playersNumber == randomNumber)
        {
            /**7.2.2. If the numbers match, the customer has won two VIP tickets*/
            System.out.println("Congrats you have won two VIP tickets to an event of your choice. Please refer to the Events below and indicate which you would like to attend event 1,2 or 3.(1 being the first event display and 3 being the last)");
            displayEvents();
            do {
                System.out.println("Enter the event you wish to attend by inputting its number here: ");
                eventOption = scan.nextInt();
                scan.nextLine(); 
                /**7.2.2.1. Get customer to choose which event they wish to attend*/

                if(eventOption == 1)
                {
                    System.out.println("You have two VIP tickets for " + Events.get(0).getEventName());

                    inputVIPDetails(Events.get(0).getEventName());
                }
                else if (eventOption == 2)
                {
                    System.out.println("You have two VIP tickets for " + Events.get(1).getEventName());

                    inputVIPDetails(Events.get(1).getEventName());
                }
                else if (eventOption == 3)
                {
                    System.out.println("You have two VIP tickets for " + Events.get(2).getEventName());

                    inputVIPDetails(Events.get(2).getEventName());
                }
                else if (eventOption < 1 || eventOption > 3)
                {
                    System.out.println("Invalid entry, please choose between 1-3.");
                }
            }while (eventOption < 1 || eventOption > 3);
        }
        else
        {
            System.out.println("Sorry you did not win");
        }
    }

    /**7.2.2.2. Get customer details and store customer details*/
    public void inputVIPDetails(String event)
    {
        String name ;
        String email ; 
        Scanner scan = new Scanner(System.in);
        Customer vip ; 

        System.out.print("Please input name: ");
        name = scan.nextLine();

        System.out.print("Please input email: ");
        email = scan.nextLine();

        vip = new Customer(name, email, event, 0, 2);

        customerBookings.add(vip);
    }

    /**8. Display most popular event */
    public void popularEvent()
    {
        System.out.print("\f");

        /**8.1. Compare the events ticket numbers */
        /**8.3. Display the most popular event*/
        if(Events.get(0).getNumberOfTickets() > Events.get(1).getNumberOfTickets() && Events.get(0).getNumberOfTickets() > Events.get(2).getNumberOfTickets())
        {
            System.out.println(Events.get(0).getEventName() + " is the most popular event");
            System.out.println( percentage(Events.get(0).getNumberOfTickets())  + "% of the tickets have been sold");
        }
        else if (Events.get(1).getNumberOfTickets() > Events.get(0).getNumberOfTickets() && Events.get(1).getNumberOfTickets() > Events.get(2).getNumberOfTickets())
        {
            System.out.println(Events.get(1).getEventName() + " is the most popular event");
            System.out.println( percentage(Events.get(1).getNumberOfTickets())  + "% of the tickets have been sold");
        }
        else if (Events.get(2).getNumberOfTickets() > Events.get(0).getNumberOfTickets() && Events.get(2).getNumberOfTickets() > Events.get(1).getNumberOfTickets())
        {
            System.out.println(Events.get(2).getEventName() + " is the most popular event");
            System.out.println( percentage(Events.get(2).getNumberOfTickets())  + "% of the tickets have been sold");
        }
        else if (Events.get(0).getNumberOfTickets() == Events.get(1).getNumberOfTickets() && Events.get(0).getNumberOfTickets() > Events.get(2).getNumberOfTickets())
        {
            System.out.println(Events.get(0).getEventName() + " and " + Events.get(1).getEventName() + " are the most popular events");
            System.out.println( percentage(Events.get(0).getNumberOfTickets())  + "% of the tickets for both events have been sold");
        }
        else if (Events.get(1).getNumberOfTickets() == Events.get(2).getNumberOfTickets() && Events.get(1).getNumberOfTickets() > Events.get(0).getNumberOfTickets())
        {
            System.out.println(Events.get(0).getEventName() + " and " + Events.get(1).getEventName() + " are the most popular events");
            System.out.println( percentage(Events.get(0).getNumberOfTickets())  + "% of the tickets for both events have been sold");
        }
        else if (Events.get(2).getNumberOfTickets() == Events.get(0).getNumberOfTickets() && Events.get(2).getNumberOfTickets() > Events.get(1).getNumberOfTickets())
        {
            System.out.println(Events.get(0).getEventName() + " and " + Events.get(1).getEventName() + " are the most popular events");
            System.out.println( percentage(Events.get(0).getNumberOfTickets())  + "% of the tickets for both events have been sold");
        }
        else if (Events.get(0).getNumberOfTickets() == 0 && Events.get(1).getNumberOfTickets() == 0 && Events.get(2).getNumberOfTickets() == 0)
        {
            System.out.println("No tickets have been sold for any event");
        }

    }

    /**8.2. Calculate the percentage of tickets sold*/
    public double percentage(double ticketsSold)
    {
        double percent=0;

        percent =  ticketsSold/50*100.0;

        return percent;
    }

    /** Display all Events */
    public void displayEvents()
    {
        System.out.println("\n\nDisplaying Events....");

        int location;
        for (Event e: Events)  /** iterator */
        {
            e.display();

        }
    }

    /**Display all customers*/
    public void displayCustomers()
    {
        System.out.println("\n\nDisplaying customers....");

        int location;
        for (Customer c: customerBookings)  /** iterator */
        {
            c.display();

        }
    }

    public static void main(String[] args)
    {
        new bookingSystem();

    } // end main
}
