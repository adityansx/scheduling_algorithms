package src.scheduling;

//importing pre-defined packages
import java.util.Scanner;
//importing user-defined packages
import src.scheduling.preemptive.*;
import src.scheduling.nonpreemptive.*;

public class Main{
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int input = -1;

        System.out.println("\nEnter 1 for FCFS Scheduling\n      2 for SJF Scheduling\n      3 for Round Robin Scheduling\n      4 for SRTF Scheduling\n      0 to exit");

        input = sc.nextInt();

        while (true) {
            switch (input) {
                case 0:
                    break;

                case 1:
                    FCFS proc_FCFS = new FCFS();
                    proc_FCFS.fetchData();
                    proc_FCFS.calculate();
                    proc_FCFS.printResults();
                    break;
                case 2:
                    SJF proc_SJF = new SJF();
                    proc_SJF.fetchData();
                    proc_SJF.sort();
                    proc_SJF.calculate();
                    proc_SJF.printResults();
                    break;
                case 3:
                    RoundRobin proc_RR = new RoundRobin();
                    proc_RR.fetchData();
                    proc_RR.calculate();
                    proc_RR.printResults();
                    break;

                case 4:
                    SRTF proc_SRTF = new SRTF();
                    proc_SRTF.fetchData();
                    proc_SRTF.calculate();
                    proc_SRTF.printResults();
                    break;
            
                default:
                    System.out.println("Invalid Input!");
                    System.out.println("Enter 1 for FCFS Scheduling\n      2 for SJF Scheduling\n      3 for Round Robin Scheduling\n      4 for SRTF Scheduling\n      0 to exit");

                    input = sc.nextInt();
                    continue;
            }
            break;
        }
    }
}
