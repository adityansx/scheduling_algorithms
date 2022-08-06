package src.scheduling.nonpreemptive;

public class SJF extends FCFS {
    public void sort() {
        Boolean swapped;
        Boolean matchingElement = false;
        for(int i = 0; i < numOfProcesses - 1; i++) {
            swapped = false;
            for(int j = 0; j < numOfProcesses - i - 1; j++) {
                if(burstTime[j] > burstTime[j + 1]) {
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
        for (int i = 0; i < numOfProcesses - 1; i++) {
            for (int j = i + 1; j < numOfProcesses; j++) {
                if(burstTime[i] == burstTime[j])
                    matchingElement = true;
            }
        }
        if(matchingElement) {
            for(int i = 0; i < numOfProcesses - 1; i++) {
                swapped = false;
                for(int j = 0; j < numOfProcesses - i - 1; j++) {
                    if(arrivalTime[j] > arrivalTime[j + 1]) {
                        int temp_1 = burstTime[j];
                        int temp_2 = arrivalTime[j];
                        burstTime[j] = burstTime[j + 1];
                        burstTime[j + 1] = temp_1;
                        arrivalTime[j] = arrivalTime[j + 1];
                        arrivalTime[j + 1] = temp_2;
                        swapped = true;
                    }
                }
                if(!swapped)
                    break;
            }
        }
    }
}