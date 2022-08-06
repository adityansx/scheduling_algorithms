package src.scheduling.preemptive;
import src.scheduling.nonpreemptive.FCFS;

public class SRTF extends FCFS{
    //protected members
    // int[] remainingTime, 

    //public members
    public void sortArrivalTime() {
        Boolean swapped;
        // Boolean matchingElement = false;
        for(int i = 0; i < numOfProcesses - 1; i++) {
            swapped = false;
            for(int j = 0; j < numOfProcesses - i - 1; j++) {
                if(arrivalTime[j] > arrivalTime[j + 1]) {
                    int temp_1 = arrivalTime[j];
                    int temp_2 = burstTime[j];
                    arrivalTime[j] = arrivalTime[j + 1];
                    arrivalTime[j + 1] = temp_1;
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp_2;
                    swapped = true;
                }
            }
            if(!swapped)
                break;
        }
    }

    @Override public void calculate() {
        sortArrivalTime();
        int[] remainingTime = new int[numOfProcesses];
        for (int i = 0; i < numOfProcesses; i++) {
            remainingTime[i] = burstTime[i];
        }

        int count = 0, currentTime = 0, minm = Integer.MAX_VALUE;
        int shortest = 0;
        Boolean check = false;

        while (count != numOfProcesses) {
            for (int i = 0; i < numOfProcesses; i++) {
                if((arrivalTime[i] <= currentTime) &&
                    (remainingTime[i] < minm) && (remainingTime[i] > 0)) {
                        minm = remainingTime[i];
                        shortest = i;
                        check = true;
                    }
            }
            if(!check) {
                currentTime++;
                continue;
            }
            remainingTime[shortest]--;

            minm = remainingTime[shortest];
            if(minm == 0) {
                minm = Integer.MAX_VALUE;
            }
            if(remainingTime[shortest] == 0) {
                count++;
                completionTime[shortest] = currentTime + 1;
                check = false;
            }
            currentTime++;
        }
        for (int i = 0; i < numOfProcesses; i++) {
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
            avgTurnAroundTime += (float)turnAroundTime[i];
            avgWaitingTime += (float)waitingTime[i];
        }
        avgTurnAroundTime /= (float)numOfProcesses;
        avgWaitingTime /= (float)numOfProcesses;
    }

    public SRTF() {
        super();
    }
}
