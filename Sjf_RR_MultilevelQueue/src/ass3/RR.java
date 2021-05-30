package ass3;

import java.util.ArrayList;

public class RR {
	protected ArrayList<Process> processes = new ArrayList<Process>();

public RR(ArrayList<Process> processes) {
		super();
		this.processes = processes;
	}

public RR() {
}

public void findWaitingTime(ArrayList<Process> processes , int quantum,int switchTime) 
{ 
int j=1;
   int bt[]=new int[processes.size()]; 
   
   for (int i = 0 ; i < processes.size() ; i++) 
       bt[i] =  processes.get(i).getBurstTime(); 

   int time = 0; 

   while (true) 
   { 
       boolean done = true; 
        for (int i = 0 ; i < processes.size(); i++) 
       { 
           if (bt[i] > 0) 
           { 
               done = false; //there process pending
 
               if (bt[i] > quantum) 
               {    
                   time+= quantum;
                   bt[i]-= quantum+switchTime; 
               } 
               else
               {  
                   time = time +bt[i]; 
                   processes.get(i).setCompletionTime(time);
                   processes.get(i).setOrder(j);
          
                  bt[i] = 0; 
                  j++;
               } 
           } 
       }
        for (int i = 0; i < processes.size() ; i++) 
            {
        	processes.get(i).setTurnAroundTime(processes.get(i).getCompletionTime() - processes.get(i).getArrivalTime()); 
        	processes.get(i).setWaitingTime(processes.get(i).getTurnAroundTime()-processes.get(i).getBurstTime());
        	}
        if (done == true) 
         break; 
   }
} 
 
public void order( ArrayList<Process> processes, int quantum,int switchTime) {
	findWaitingTime( processes,  quantum,switchTime);
	for(int i=0;i<processes.size();i++) {
		 System.out.println("P "+processes.get(i).getId()+" >> order execution is "+processes.get(i).getOrder());
	}
}

public void TurnAroundTime(ArrayList<Process> processes, int quantum,int switchTime) 
{ 
	findWaitingTime( processes,  quantum,switchTime);

  for(int i=0;i<processes.size();i++) {
	 System.out.println("P "+processes.get(i).getId()+" >> turn Around Time is "+processes.get(i).getTurnAroundTime());
	}
} 
public void WaitingTime(ArrayList<Process> processes, int quantum,int switchTime) {
	findWaitingTime( processes,  quantum,switchTime);
	for(int i=0;i<processes.size();i++) {
	 System.out.println("P "+processes.get(i).getId()+" >> Waiting Time is "+processes.get(i).getWaitingTime());
	}
}

public double AverageWaitingTime(ArrayList<Process> processes, int quantum,int switchTime) {
	findWaitingTime( processes,  quantum,switchTime);

	int sum=0;
	int size=processes.size();
	double avg=0;
	
	for(int i=0;i<size;i++) {
		sum=sum+processes.get(i).getWaitingTime();
	}
	avg=(float)sum/(float)size;
	
	return avg;
}
public double AverageTurnaroundTime(ArrayList<Process> processes, int quantum,int switchTime) {
	findWaitingTime( processes,  quantum,switchTime);

	int sum=0;
	int size=processes.size();
	double avg=0;

	for(int i=0;i<size;i++) {
		sum=sum+processes.get(i).getTurnAroundTime();
	}
	avg=(float)sum/(float)size;
	
	return avg;
}  
}
