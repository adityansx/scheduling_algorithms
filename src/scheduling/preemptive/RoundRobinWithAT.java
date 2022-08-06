package src.scheduling.preemptive;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class RoundRobinWithAT extends RoundRobin {
    //Private Members
    private static final Scanner sc = new Scanner(System.in);

    //Protected Members

    // protected int it = 0;
    // protected int[] readyQueue;
    protected int lastArrivedProcess;
    protected int[] arrivalTime, completionTime;
    protected float avgWaitingTime = 0.0f;
    protected float avgTurnAroundTime = 0.0f;
    protected ArrayList<Integer> readyQueue = new ArrayList<Integer>();
    protected Iterator<Integer> it = readyQueue.iterator();
    protected Boolean queueUpdationComplete = false;

    //Public Members
    @Override public void fetchData() {
        for(int i = 0; i < numOfProcesses; i++){
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            System.out.println();
        }
    }

    void sortArrivalTime(int[] aTime, int[] bTime) {
        for (int i = 0; i < numOfProcesses - 1; i++) {
            Boolean swapped = false;
            for(int j = 0; j < numOfProcesses - i - 1; j++) {
                if(aTime[j] > aTime[j + 1]) {
                    int temp = aTime[j];
                    aTime[j] = aTime[j + 1];
                    aTime[j + 1] = temp;

                    temp = bTime[i];
                    bTime[j] = bTime[j + 1];
                    bTime[j + 1] = temp;
                    swapped = true;
                }
            }
            if(!swapped)
                break;
        }
    }

    @Override public void calculate() {
        sortArrivalTime(arrivalTime, burstTime);
        currentTime = 0;
        lastArrivedProcess = numOfProcesses - 1;
        while(currentTime != arrivalTime[0]) {
            currentTime++;
        }
        readyQueue.add(0);
        // readyQueue[0] = 0;
        while(count != numOfProcesses) {
            if(it.hasNext()){
                temp = timeQuantum;
                if(remainingTime[(it.next()).intValue()] > timeQuantum){
                    remainingTime[(it).intValue()] -= timeQuantum;
                } else if(remainingTime[(it).intValue()] >= 0){
                    temp = remainingTime[(it.next()).intValue()];
                    remainingTime[(it.next()).intValue()] = 0;
                    completionTime[(it.next()).intValue()] = currentTime + temp;
                    count++;
                }
                currentTime += temp;
            }
        }
        for(int i = 0; i < numOfProcesses; i++){
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
            avgTurnAroundTime += turnAroundTime[i];
            avgWaitingTime += waitingTime[i];
        }
        
        avgTurnAroundTime /= numOfProcesses;
        avgWaitingTime /= numOfProcesses;
    }

    public void queueUpdation(int[] aTime, int cTime){
        
    }

    @Override public void printResults(){
        System.out.println("Process | Arrival Time | Burst Time | Completion Time | Turnaround Time | Waiting Time");
        for (int i = 0; i < numOfProcesses; i++){
            System.out.println((i + 1) + "\t\t" + arrivalTime[i] +"\t\t" + burstTime[i] + "\t\t" + completionTime[i] + "\t\t" + 
            turnAroundTime[i] + "\t\t" + waitingTime[i]);
            System.out.print("---------------------------------------------------------------------------------------\n");
        }
        System.out.println();
        System.out.println("Average Waiting Time = " + avgWaitingTime);
        System.out.println("Average Turn Around Time = " + avgTurnAroundTime);
    }

    public RoundRobinWithAT(){
        super();
        arrivalTime = new int[numOfProcesses];
        completionTime = new int[numOfProcesses + 1];
        // readyQueue = new int[100];
    }
}