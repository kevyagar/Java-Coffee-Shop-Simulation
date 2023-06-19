package javaSimulation;

public class Customer
{
    int number; //int that represents customer number
    String name;  //string represents customer name
    String status; //string that represents whether customer is being served of waiting. try to do this with boolean?
    String serverName;
    int arrivalTime; //int that represents when the customer arrived
    int waitTimer; //int that represents how long a customer has been waiting for service

    int serviceTime;   //int that represents time taken by server for service
    int serviceStartTime;  //int that represents the start time of service
    int serviceEndTime; //int that represents end time of service

    //basic constructor
    Customer(int number)
    {
        this.number = number;
        this.name   = "Customer" + number;
    }

    public String inQueue()
    {
        String output = name + " Arrived at = " + arrivalTime + " Status= " + status + " for=" + waitTimer + " sec";
        return output;
    }
    @Override
	public String toString()
    {
        String output = name + " Arrived at = " + arrivalTime + " Status = " + status + " by = " + serverName + " waited= " + waitTimer + " Serviced at = " + serviceStartTime + " end at= " + serviceEndTime;
        return output;
    }
}
