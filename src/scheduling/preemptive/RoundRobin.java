package src.scheduling.preemptive;

import java.util.Scanner;

public class RoundRobin {
    //Private Members
    private static final Scanner sc = new Scanner(System.in);

    //Protected Members
    protected int numOfProcesses;
    protected int timeQuantum;
    protected int currentTime = 0;
    protected int temp;
    protected int count;
    protected int[] burstTime, waitingTime, turnAroundTime, remainingTime;

    //Public Members
    public void fetchData() {
        for (int i = 0; i < numOfProcesses; i++) {
            System.out.print("Enter the burst time for Process " + (i + 1) + ": ");
            remainingTime[i] = burstTime[i] = sc.nextInt();
        }
    }

    public void calculate() {
        
        while(count != numOfProcesses) {
            int i;
            for(i = 0, count = 0; i < numOfProcesses; i++) {
                if(remainingTime[i] == 0) {
                    count++;
                    continue;
                }
                temp = timeQuantum;
                if(remainingTime[i] > timeQuantum) {
                    remainingTime[i] -= timeQuantum;
                } else if(remainingTime[i] >= 0) {
                    temp = remainingTime[i];
                    remainingTime[i] = 0;
                    turnAroundTime[i] = currentTime + temp;
                }
                currentTime += temp;
            }
        }
        for(int i = 0; i < numOfProcesses; i++) {
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
        }
    }

    public void printResults() {
        System.out.println();
        System.out.println("Process | Burst Time | Turnaround Time | Waiting Time");
        for(int i = 0; i < numOfProcesses; i++) {
            System.out.println((i + 1) + "\t\t" + burstTime[i]+ "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i]);
            System.out.println("-----------------------------------------------------");
        }
    }

    public RoundRobin() {
        System.out.print("Enter the number of processes: ");
        numOfProcesses = sc.nextInt();
        System.out.print("Enter Time Quantum: ");
        timeQuantum = sc.nextInt();
        System.out.println();

        burstTime = new int[numOfProcesses];
        waitingTime = new int[numOfProcesses];
        turnAroundTime = new int[numOfProcesses];
        remainingTime = new int[numOfProcesses];
    }
}
