package javaSimulation;

import java.util.ArrayList;


public class Server
{
    int number;    //int represents number of server
    String name, cust_name;    //string represents name of server / string represents customer name
    int serviceTime;   //int that represents time taken by server for service
    int serviceStartTime;  //int that represents the start time of service
    int serviceEndTime; //int that represents end time of service
    int busyTimer;  //int representing number of seconds left (we are using 1 loop for 1 second)
    int totalBusyTime; //int representing total busy time of the server
    int totalIdleTime; //int representing total idle time :(

    //basic constructor with int number
    Server(int number)
    {
        this.number = number;
        this.name   = "Server" + number;
    }
    // function to return a string with the busy timers of all servers
    static String getBusyTimers(ArrayList<Server> servers)
    {
        String busy_timers = "[ ";
        for (Server emp : servers)
            busy_timers += "srv" + emp.number + "=" + emp.busyTimer + " ";
        busy_timers += "]";
        return busy_timers;
    }
    // function to return a string with the total busy time
    static int getTotalBusyTime(ArrayList<Server> servers)
    {
        int total_busy_time = 0;
        for (Server emp : servers)
            total_busy_time += emp.totalBusyTime;
        return total_busy_time;
    }
    // function to return a string with the total idle time
    static int getTotalIdleTime(ArrayList<Server> servers)
    {
        int total_idle_time = 0;
        for (Server emp : servers)
            total_idle_time += emp.totalIdleTime;
        return total_idle_time;
    }
    //function that tells us if all the servers are idle
    static boolean allIdle(ArrayList<Server> servers)
    {
        boolean areIdle = true;
        for (Server emp : servers)
            if (emp.busyTimer > 0) areIdle = false;
        return areIdle;
    }

    @Override
	public String toString()
    {
        String s = name + "   Service duration= " +serviceTime + "sec Remaining time=" + busyTimer + " Start at= " +serviceStartTime + " end at= " + serviceEndTime;
        return s;
    }
}
