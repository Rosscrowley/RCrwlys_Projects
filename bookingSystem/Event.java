
/**
 * 
 *
 * @author Ross Crowley
 * Student Number C20410104
 */
public class Event
{
    
    private String eventName; // common variable between event and customer 
    private String day;
    private String time; 
    private int numOfTickets;

    //2. First method - Constructor
    public Event()
    {
        this.eventName =  ""; 
        this.day = "";
        this.time = "";
        this.numOfTickets = 0;
    }

    // Constructor 2 - create a new Event object with data
    public Event(String EvName, String EvDay, String EvTime, int EvTickets)
    {
        this.eventName = EvName; 
        this.day = EvDay;
        this.time = EvTime;
        this.numOfTickets = EvTickets;
    }

    /** 1.1 Create Standard Operations  */
    // return the Event name
    public String getEventName()
    {
        return this.eventName;
    }

    // return day of Event 
    public String getDay()
    {
        return this.day;
    }

    // return the Time of Event (Late Night or Evening)
    public String getTime()
    {
        return this.time;
    }
    // return the number of tickets sold
    public int getNumberOfTickets()
    {
        return this.numOfTickets;
    }

    //  update the Event name
    public void setEventName(String EName)
    {
        this.eventName = EName;
    }

    //  update the day of Event 
    public void setDay(String EDay)
    {
        this.day = EDay;
    }

    //  update the time of Event
    public void setTime(String ETime)
    {
        this.time = ETime;
    }
    // update number of tickets
    public void setNumberOfTicket(int ENum)
    {
        this.numOfTickets= ENum;
    }

    /** Calculate Unsold Tickets */
    // returns the number of unsold tickets
    public int unsoldTickets()
    {
        int unsoldTick;

        unsoldTick = 50 - getNumberOfTickets();

        return unsoldTick;
    }

    /** Personal Operation*/

    //Method/module to display Event details
    public void display()
    {
        System.out.println("\n\nEvent name is: " + this.eventName);
        System.out.println("Day of event: " + this.day);
        System.out.println("Time of event: " + this.time);
        System.out.println("Number of tickets: " + this.numOfTickets);

    }
}

