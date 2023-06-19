import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private static int num_servers = 4;
    private static int avg_arrival = 10;
    private static int avg_service = 60;
    private static int counter = 180;
    private static int clock;
    private static int maxWaitTime;
    private static int maxQueueSize;
    private static int nextArrival;
    private static int custNumber;
    private static int totalCustomers;
    private static int totalWaitTime;
    private static boolean acceptCustomers = true;

    private static Random arv = new Random(123123);
    private static Random svc = new Random(654321);

    private static ArrayList<Server> servers;
    private static Queue<Customer> customers;

    private static JTextArea outputTextArea;
    private static Timer simulationTimer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        outputTextArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });
        mainPanel.add(startButton, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void startSimulation() {
        servers = new ArrayList<Server>();
        customers = new Queue<Customer>();

        for (int employee = 1; employee <= num_servers; employee++) {
            Server server = new Server(employee);
            servers.add(server);
        }

        outputTextArea.append("SIMULATION ATTEMPT ====================================================\n");
        outputTextArea.append("Time:\n");

        clock = 0;
        simulationTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clock <= counter) {
                    arrival();
                }

                for (Server employee : servers) {
                    service(employee);
                }

                updateWaitTime();

                if (clock == counter) {
                    printOutput("Part1");
                }

                if (clock >= counter && Server.allIdle(servers)) {
                    printOutput("Final Statistics");
                    simulationTimer.stop();
                }

                clock++;
            }
        });
        simulationTimer.start();
    }

    private static void arrival() {
        if (acceptCustomers) {
            int rand_arv_time = arv.nextInt(avg_arrival * 2) + 1;
            nextArrival = clock + rand_arv_time;
            acceptCustomers = false;
        }

        if (nextArrival == clock) {
            custNumber++;
            Customer customer = new Customer(custNumber);
            customer.arrivalTime = clock;
            customer.status = "waiting";
            totalCustomers += 1;
            customers.push(customer);

            if (customers.size() > maxQueueSize) {
                maxQueueSize = customers.size();
            }

            outputTextArea.append("ARRIVAL              ---------------------------------------------------------------\n");
            outputTextArea.append(customer.name + " arrived at=" + customer.arrivalTime + "\n");
            printQueue();
            acceptCustomers = true;
        }
    }

    private static void service(Server server) {
        if (server.busyTimer >= 1) {
            server.busyTimer--;
        } else {
            server.totalIdleTime++;
        }

        if (server.busyTimer == 0 && customers.peekFirst() != null) {
            Customer customer = customers.pop();

            outputTextArea.append("SERVICING -------------------------------------------------------------\n");
            outputTextArea.append(server.name + "   servicing " + customer.name + "\n");
            totalWaitTime += customer.waitTimer;

            int randomServiceTime = svc.nextInt(avg_service * 2) + 1;

            server.cust_name = customer.name;
            server.serviceTime = randomServiceTime;
            server.serviceStartTime = clock;
            server.serviceEndTime = clock + randomServiceTime;
            server.busyTimer = randomServiceTime;
            server.totalBusyTime += randomServiceTime;

            customer.serverName = server.name;
            customer.status = "serviced";
            customer.serviceTime = randomServiceTime;
            customer.serviceStartTime = clock;
            customer.serviceEndTime = clock + randomServiceTime;

            outputTextArea.append(server + "\n");
            outputTextArea.append(customer + "\n");
            outputTextArea.append("-----------------------------------------------------------------------\n");
        }
    }

    private static void updateWaitTime() {
        for (Customer customer : customers) {
            customer.waitTimer++;
            if (customer.waitTimer > maxWaitTime) {
                maxWaitTime = customer.waitTimer;
            }
        }
    }

    private static void printQueue() {
        outputTextArea.append("CUSTOMER QUEUE --------------------------------------------------------\n");
        String serviceBusyTimer = Server.getBusyTimers(servers);
        outputTextArea.append("Servers status or remaining busy time: " + serviceBusyTimer + "\n");
        if (customers.size() == 0) {
            outputTextArea.append("Queue is empty\n");
        }
        for (Customer customer : customers) {
            outputTextArea.append(customer.inQueue() + "\n");
        }
        outputTextArea.append("------------------------------------------------------------------\n");
    }

    private static void printOutput(String output) {
        printQueue();
        int customerServiced = totalCustomers - customers.size();
        int totalServiceTime = Server.getTotalBusyTime(servers);
        int totalIdleTime = Server.getTotalIdleTime(servers);
        float averageWaitTime = (float) totalWaitTime / customerServiced;
        float averageServiceTime = (float) totalServiceTime / customerServiced;
        float averageIdleTime = (float) totalIdleTime / num_servers;

        outputTextArea.append("|===SIMULATION FINISHED (" + output + ")==========================================\n");
        outputTextArea.append("| Number of servers.......: " + num_servers + "\n");
        outputTextArea.append("| Time passed ............: " + clock + " seconds\n");
        outputTextArea.append("| Customers serviced......: " + customerServiced + "\n");
        outputTextArea.append("| Customers still in queue: " + customers.size() + "\n");
        outputTextArea.append("| Average wait time.......: " + averageWaitTime + "\n");
        outputTextArea.append("| Maximum wait time.......: " + maxWaitTime + "\n");
        outputTextArea.append("| Maximum customers queue.: " + maxQueueSize + "\n");
        outputTextArea.append("| Average service time....: " + averageServiceTime + "\n");
        outputTextArea.append("| Average server idle time: " + averageIdleTime + "\n");
        outputTextArea.append("  = " + (averageIdleTime / clock * 100) + "%\n");
        outputTextArea.append("|======================================================================\n");
    }
}
