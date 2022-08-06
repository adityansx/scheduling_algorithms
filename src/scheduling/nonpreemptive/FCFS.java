package src.scheduling.nonpreemptive;

import java.util.Scanner;

public class FCFS {
    //Private Members
    private static final Scanner sc = new Scanner(System.in);

    //Protected Members
    protected int numOfProcesses;
    protected int[] arrivalTime;
    protected int[] burstTime;
    protected int[] turnAroundTime;
    protected int[] waitingTime;
    protected int[] completionTime;
    protected float avgWaitingTime = 0.0f;
    protected float avgTurnAroundTime = 0.0f;

    //Public Methods
    public void fetchData() {
        for(int i = 0; i < numOfProcesses; i++) {
            System.out.println("Enter the arrival time for Process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.println("Enter the burst time for Process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            System.out.println();
        }
    }

    public void calculate() {
        for(int i = 0; i < numOfProcesses; i++) {
            waitingTime[i] = 0;
            turnAroundTime[i] = 0;

            waitingTime[i] = completionTime[i] - arrivalTime[i];
            if(waitingTime[i] < 0) 
                waitingTime[i] = 0; //if waiting time turns out to be negative
            completionTime[i + 1] = arrivalTime[i] + waitingTime[i] + burstTime[i];
            turnAroundTime[i] = completionTime[i + 1] - arrivalTime[i];

            avgWaitingTime += (float) waitingTime[i];
            avgTurnAroundTime += (float) turnAroundTime[i];
        }
        avgWaitingTime /= (float) numOfProcesses;
        avgTurnAroundTime /= (float) numOfProcesses;
    }

    public void printResults() {
        System.out.println("Process | Arrival Time | Burst Time | Completion Time | Turnaround Time | Waiting Time");
        for(int i = 0; i < numOfProcesses; i++) {
            System.out.println((i + 1) + "\t\t" + arrivalTime[i] +"\t\t" + burstTime[i] + "\t\t" + completionTime[i + 1] + "\t\t" + 
            turnAroundTime[i] + "\t\t" + waitingTime[i]);
            System.out.print("---------------------------------------------------------------------------------------\n");
        }
        System.out.println();
        System.out.println("Average Waiting Time = " + avgWaitingTime);
        System.out.println("Average Turn Around Time = " + avgTurnAroundTime);
    }

    public FCFS() {
        System.out.println("Enter the number of processes: ");
        numOfProcesses = sc.nextInt();

        arrivalTime = new int[numOfProcesses];
        burstTime = new int[numOfProcesses];
        turnAroundTime = new int[numOfProcesses];
        waitingTime = new int[numOfProcesses];
        completionTime = new int[numOfProcesses + 1];
        completionTime[0] = 0;

        System.out.println();
    }
}