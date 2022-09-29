
/**
 *
 *
 * @author Ross Crowley
 * Student Number : C20410104
 */
public class Customer
{
    //1. Data about customer - Instance variables
    private String customerName;
    private String email;
    private String nameOfEvent;  //common variable
    private int ticketsBooked;
    private int vipTickets;
    private boolean hasBookedEvent;
    private boolean isVIP = true;
    // Constructor 
    public Customer()
    {
        this.customerName =  ""; 
        this.email = "";
        this.nameOfEvent = "";
        this.ticketsBooked = 0;
        this.vipTickets = 0;
        this.hasBookedEvent = true;
        this.isVIP = true;
    }

    // Constructor 2 - create a new customer object with data
    public Customer(String CName, String CEmail, String Event, int tickBooked, int vipTick)
    {
        this.customerName = CName; 
        this.email = CEmail;
        this.nameOfEvent = Event;
        this.ticketsBooked = tickBooked;
        this.vipTickets = vipTick;

    }

    /** 2.1 Create Standard Operations*/
    // return the customers name
    public String getCustomerName()
    {
        return this.customerName;
    }

    // return the customers email
    public String getEmail()
    {
        return this.email;
    }

    // return the name of event 
    public String getNameOfEvent()
    {
        return this.nameOfEvent;
    }

    // return the number of tickets the customer booked
    public int getTicketBooked()
    {
        return this.ticketsBooked;
    }

    // return the number of tickets the customer booked
    public int getVipTicket()
    {
        return this.vipTickets;
    }

    /** 2.2 Check If Customer Has Booked An Event*/
    public boolean getHasBookedEvent()
    {
        if (!this.nameOfEvent.equals(""))
        {
            this.hasBookedEvent = true;
        }
        else
        {
            this.hasBookedEvent = false;
        }

        return this.hasBookedEvent;
    }
    /**Check if a customer has VIP tickets*/
    public boolean getHasVipTickets()
    {
        if (this.vipTickets == 2)
        {
            this.isVIP = true;
        }
        else
        {
            this.isVIP = false;
        }

        return this.isVIP;
    }
    //  set name
    public void setCustomerName(String CName)
    {
        this.customerName = CName;
    }

    //  set email
    public void setEmail(String CEmail)
    {
        this.email = CEmail;
    }

    //  set name of event
    public void setNameOfEvent(String EventName)
    {
        this.nameOfEvent = EventName;
    }

    // set number of tickets booked
    public void setNumberOfTicket(int ticketsBook)
    {
        this.ticketsBooked= ticketsBook;
    }
    // set VIP tickets
    public void setVipTickets(int vipTick)
    {
        this.vipTickets = vipTick;
    }

    /** Personal Operation*/

    //Method/module to display customer details
    public void display()
    {
        System.out.println("\n\nCustomer name is: " + this.customerName);
        System.out.println("Customers email is: " + this.email);
        System.out.println("The customer is attending: " + this.nameOfEvent);
        System.out.println("Number of standard tickets booked: " + this.ticketsBooked);

        if (this.hasBookedEvent == false)
        {
            System.out.println("The customer has booked tickets for an event ");  
        }

        else if (this.hasBookedEvent == true)
        {
            System.out.println("The customer has not booked tickets for an event ");
        }
        
        if(this.isVIP == true && this.ticketsBooked == 0)
        {
             System.out.println("The customer has 2 VIP tickets");
        } 
        
    }
}