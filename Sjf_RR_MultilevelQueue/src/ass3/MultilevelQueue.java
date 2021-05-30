package ass3;

import java.util.ArrayList;
import java.util.Scanner;

public class MultilevelQueue {
	int noOfProcesses;
	String name;
	int burstTime;
	int arrivalTime;
	int quantum_time;
	 ArrayList<Process> processes = new ArrayList<>(); // All processes will exist
	
	Scanner scan = new Scanner(System.in);

	MultilevelQueue() {
	}
	public int getQuantum_time() {
		return quantum_time;
	}

	public void setQuantum_time(int quantum_time) {
		this.quantum_time = quantum_time;
	}

	public void multilevelSchedule() {
		
		System.out.println("Enter number of processes\n");
		noOfProcesses = scan.nextInt();
		int[] queueNo = new int[noOfProcesses];
		System.out.println("\n quantum time time for the first queue (RR) : ");
		quantum_time = scan.nextInt();
		this.setQuantum_time(quantum_time);
		for (int i = 0; i <noOfProcesses; i++) {
			Process process = new Process();
			System.out.println("Enter the name of process " + (i + 1) + " :");
			name = scan.next();
			process.setName(name);
			System.out.println("arrival time of process " + (i + 1) + " : ");
			arrivalTime = scan.nextInt();
			process.setArrivalTime(arrivalTime);
			process.setArrivalTime(arrivalTime);
			System.out.println(" burst time of process " + (i + 1) + " :");
			burstTime = scan.nextInt();
			process.setBurstTime(burstTime);
			process.setBurstTime(burstTime);
			System.out.println(" queue number which is (1 or 2) of process " + (i + 1) + " :");
			queueNo[i] = scan.nextInt();
			processes.add(process);} //Add processes in our list
		int[] Waiting_Time, TurnAround_Time;
        ArrayList<Process> ExecutionOrder_OfProcess = new ArrayList<>();
        for (Process p: processes)
        p.TimeReminder = p.BurstTime;
        Waiting_Time = MultilevelQueue.findWaitingTime(processes, queueNo, quantum_time, ExecutionOrder_OfProcess);
        TurnAround_Time = MultilevelQueue.findtTurnAroundTime(processes, Waiting_Time);
        System.out.println("Multi Level Schedular: ");
        MultilevelQueue.output(processes, ExecutionOrder_OfProcess, Waiting_Time, TurnAround_Time);
			/*if (process.getQueueNo() == 1) {
				Queue1.add(process);
			} else if (process.getQueueNo() == 2) {
				Queue2.add(process);
			}
		}*/

     
	}
	public static int[] findWaitingTime(ArrayList<Process> processes, int[] Queue, int Quantum_time, ArrayList<Process> Execution_Order) {
        Execution_Order.clear();
        int size = processes.size();
        int[] Waiting_Time = new int[size];
       
        ArrayList<Process> Queue1 = new ArrayList<>();// Round Robin higher priority
        ArrayList<Process> Queue2 = new ArrayList<>();// FCFS lower priority
        int current_Time = 0;
        for (int i = 0; i < size; i++) {
            if (Queue[i] == 1)
                Queue1.add(processes.get(i));    //Add to the high priority qeueu rr 
            else if(Queue[i] == 2)
                Queue2.add(processes.get(i));    //Add to lower priority qeueue fcfs
        }
        while (true) {
            int completion = 0;
            for (Process p : Queue1) {
                if (p.TimeReminder == 0) completion++;    //Process executed in q1
            }
            for (Process p : Queue2) {
                if (p.TimeReminder == 0) completion++;   //Process executed in q2
            }
            if (completion == size) break;             //All processes got executed
            while (true) {
                int check = roundRobinAlgo(Queue1, Quantum_time, current_Time, Execution_Order);
                if (check == -1) break;    //Q1 is completed
                current_Time = check;
            }
            current_Time = FCFSAlgo(Queue2, current_Time, Execution_Order);
        }
        for (int i = 0; i < size; i++)
            Waiting_Time[i] = processes.get(i).waitingTime;
        return  Waiting_Time;
    }
	   public static int[] findtTurnAroundTime(ArrayList<Process> processes, int[] Waiting_Time) {
	        int size = processes.size();
	        int[] TurnAround_time = new int[size];
	        for (int i = 0; i < size; i++) {
	            TurnAround_time[i] = processes.get(i).BurstTime + Waiting_Time[i];
	        }
	        return TurnAround_time;
	    }
	  private static int FCFSAlgo(ArrayList<Process> processes, int Current_Time, ArrayList<Process> Execution_Order) {
	        for (Process process : processes) {
	            if (process.TimeReminder > 0) {
	                Execution_Order.add(process);
	                Current_Time++;
	                process.TimeReminder--;
	                if (process.TimeReminder == 0) {
	                    int finishTime = Current_Time;
	                    process.waitingTime = finishTime - process.BurstTime - process.arrivalTime;
	                }
	                break;
	            }
	        }
	        return Current_Time;
	    }
	 private static int roundRobinAlgo(ArrayList<Process> processes, int quantum_time, int Current_Time, ArrayList<Process> Execution_Order) {
	        boolean found_flag = false;
	        for (Process process : processes) {
	            if (process.arrivalTime <= Current_Time && process.TimeReminder > 0) {
	                found_flag = true; //There is a place for more process
	                Execution_Order.add(process);
	                if (process.TimeReminder > quantum_time) { 
	                    Current_Time += quantum_time;
	                    process.TimeReminder -= quantum_time;      //like p1 4-2
	                } else {
	                    Current_Time += process.TimeReminder;
	                    process.TimeReminder = 0;
	                    int finishTime = Current_Time;
	                    process.waitingTime = finishTime - process.BurstTime - process.arrivalTime;
	                }
	            }
	        }
	        if (!found_flag) return -1;
	        return Current_Time;
	    }

	/*static public void sortArrivalTimeofProcess(ArrayList<Process> process) {

		for (int i = 0; i < process.size(); i++) {
			Process temp;
			int min = process.get(i).getArrivalTime();
			for (int j = i + 1; j < process.size(); j++) {
				if (min > process.get(j).getArrivalTime()) {
					temp = process.get(j);
					process.set(j, process.get(i));
					process.set(i, temp);
					min = process.get(j).getArrivalTime();

				}
			}
		}
	}
*/
	 public static void output(ArrayList<Process> processes, ArrayList<Process> executionOrder,
                              int[] waitingTime, int[] turnAroundTime) {
        int n = processes.size();
        System.out.println("Order of processes: ");
        for (Process process: executionOrder)
            System.out.print(process.name + " -> ");
        System.out.println();
        System.out.println("Process Name\tWaiting Time\t\t\t Turn Around Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes.get(i).name + "\t\t\t" + waitingTime[i] + "\t\t\t" + turnAroundTime[i]);
        }
        System.out.println("Average Waiting Time: " + getAvg(waitingTime));
        System.out.println("Average Turn Around Time: " + getAvg(turnAroundTime));
    }

    private static double getAvg(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return (double)sum / arr.length;
    }
}