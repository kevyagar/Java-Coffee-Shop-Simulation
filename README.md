# Java-Coffee-Shop-Simulation

Project Description: Modeling and Simulation of Customer Queue

In this project, I developed a simulation of a coffee shop where customers arrive and join a queue for service by the shop's employees. The main objective was to model the customer queue system and analyze various statistics related to customer wait times, service times, and server idle times.

To implement the simulation, I created several classes to encapsulate the different entities involved. Firstly, I designed a Queue class as a subclass of LinkedList to represent the customer queue. This class efficiently managed the arrival and removal of customers in the queue.

Next, I implemented a Server class that encapsulated all the attributes and functionalities required for a shop server employee. This class handled the status of the server, whether they were serving a customer or idle.

Additionally, I created a Customer class that represented individual customers visiting the coffee shop. This class contained the necessary attributes and functionalities to simulate customer behavior, such as joining the queue and being served by an available server.

The Simulation class served as the program's entry point, incorporating the parameters and logic for the simulation. The coffee shop was assumed to have four server employees, and an ArrayList was used to store these server instances. Each server's availability was determined by their serving status, with them being busy when serving a customer and idle when available for service.

To introduce randomness in the simulation, I utilized two random number generators: "arv" for customer arrival time and "svc" for service time. Both generators were initialized with fixed seeds to ensure reproducibility. Customers arrived at random intervals with an average arrival rate of one every 10 seconds, calculated using the formula arv.nextInt(10*2)+1. The service time for each customer was also randomized, ranging from 1 to 120 seconds, with an average service time of 60 seconds determined by svc.nextInt(60*2)+1.

The simulation ran in a loop, representing one second per iteration. The loop incremented the time by one second and updated the various activities, such as customer arrival and service times, accordingly. In the first part of the simulation, the program executed for a duration of 3 minutes (180 seconds), representing the operating time of the coffee shop. Intermediate statistics were produced during this phase.

Once the initial 3-minute period ended, the simulation entered the second part. If there were remaining customers in the queue, they were served until all orders were completed, following the same rules as before. Finally, the program generated the final statistics required for analysis.

The final statistics included listing the customers in the queue along with their wait times, the number of servers employed (four in this case), the total elapsed time of the simulation (327 seconds for the final stats, corresponding to 180 seconds for intermediate stats), the number of customers serviced, the number of customers remaining in the queue (should be zero for final stats), the average wait time per customer, the maximum wait time experienced by any customer, the maximum number of customers in the queue at any given time, the average service time per customer, and the average idle time for the servers as a percentage of the total simulation time.

By developing this simulation of a customer queue system, I gained valuable experience in modeling real-world scenarios, implementing data structures such as queues, managing randomization, and analyzing system performance through statistical measures.
