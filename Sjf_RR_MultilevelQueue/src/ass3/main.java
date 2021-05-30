package ass3;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
	
		char again; 
		ArrayList<Process> processArrayList = new ArrayList<Process>();

		int numberOfProcess=0;
		int choice=0;
		int switchTime=0;
		 int arrivalTime=0;
		 int burstTime=0;
			int quantum=0;
		Scanner sc=new Scanner(System.in);
		do {
		System.out.println ("which way do you need?");
		System.out.println ("1-SJF");
		System.out.println ("2-RR");
		System.out.println ("3-Priority scheduling");
		System.out.println ("4-Multi level scheduling");
		choice= sc.nextInt();

if(choice==1) {
	  arrivalTime=0;
		 burstTime=0;
		System.out.println ("Enter number of process:");
		numberOfProcess= sc.nextInt();
		System.out.println ("Enter context Switch Time:");
		switchTime= sc.nextInt();
		SjF ob = new SjF();
		
		 for (int i=0;i<numberOfProcess;i++)
		    {
		
			 Process newprocess=new Process();
		    	System.out.println ("enter process " +(i+1)+ " arrival time:");
		    	arrivalTime= sc.nextInt();
		    	System.out.println("enter process " +(i+1)+ " burst time:");
		        burstTime= sc.nextInt();
		        newprocess.setBurstTime(burstTime);
		    	newprocess.setArrivalTime(arrivalTime);
				newprocess.setId(i+1);

processArrayList.add(newprocess);
}
		 ob.findWaitTime(processArrayList, switchTime);
		 ob.turnAroundTime(processArrayList, switchTime);

		System.out.println("Average waiting Time:"+ ob.AverageWaitingTime(processArrayList, switchTime));
		System.out.println("Average TurnAroundTime:"+ob.AverageTurnaroundTime(processArrayList, switchTime));
		 
	  ob.order(processArrayList, switchTime);
	
}
		
else if(choice==2) {
	  arrivalTime=0;
		 burstTime=0;
	System.out.println ("Enter number of process:");
	numberOfProcess= sc.nextInt();
	System.out.println ("Enter context Switch Time:");
	switchTime= sc.nextInt();
	System.out.println ("Enter quantum:");
	 quantum= sc.nextInt();
		System.out.println ("Enter context Switch Time:");
		switchTime= sc.nextInt();
	
	RR obj=new RR();
	
	 for (int i=0;i<numberOfProcess;i++)
	    {
		 
		 Process newProcess=new Process();
			newProcess.setId(i+1);			
	    	System.out.println ("enter process " +(i+1)+ " arrival time:");
	    	arrivalTime= sc.nextInt();
	    	System.out.println("enter process " +(i+1)+ " burst time:");
	        burstTime= sc.nextInt();	
	        newProcess.setArrivalTime(arrivalTime);
	        newProcess.setBurstTime(burstTime);
	        processArrayList.add(newProcess) ;  
	    }

	 System.out.println("----------------------------------------------------------");	
	 
	 obj.WaitingTime(processArrayList, quantum,switchTime);
	 obj.TurnAroundTime(processArrayList, quantum,switchTime);

	 System.out.println("AVGwaitingTime:"+obj.AverageWaitingTime(processArrayList, quantum,switchTime));		
	 System.out.println("AVGturnAroundTime:"+obj.AverageTurnaroundTime(processArrayList, quantum,switchTime));	
	 obj.order(processArrayList, quantum,switchTime);

}
if(choice==3) {
	System.out.print("Please enter the number of the processes : ");
	numberOfProcess = sc.nextInt();
	sc.nextLine();
	for (int i = 0; i < numberOfProcess; i++) {
		String processName="";
		int processArrival = 0;
		int processBurst = 0;
		int processPriority = 0;
		System.out.print("Please enter the process name : ");
		processName = sc.nextLine();
		System.out.print("Please enter process's ("+processName+")"+" arrival time : ");
		processArrival = sc.nextInt();
		System.out.print("Please enter process's ("+processName+")"+" priority : ");
		processPriority = sc.nextInt();
		System.out.print("Please enter process's ("+processName+")"+" burst time : ");
		processBurst = sc.nextInt();
		Process newProcess = new Process(processName,processArrival,processBurst,processPriority);
		processArrayList.add(newProcess);
		sc.nextLine();
	}
	System.out.println();
	TimesCalculator calculator = new TimesCalculator(processArrayList);
	calculator.doCalculation();
	}

else if(choice==4)
{
	MultilevelQueue queue=new MultilevelQueue();
	queue.multilevelSchedule();	
}
	
processArrayList.clear();
System.out.println("Do you want to another Way?");		
again=sc.next().charAt(0);
}while(again=='y'||again=='Y');
sc.close();

	}

}
