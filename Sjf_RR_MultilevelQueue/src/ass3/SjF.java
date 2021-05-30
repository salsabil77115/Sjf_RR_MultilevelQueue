package ass3;

import java.util.ArrayList;

public class SjF {
	
	protected ArrayList<Process> processes = new ArrayList<Process>();


	public SjF(ArrayList<Process> processes) {
		super();
		this.processes = processes;
	}
	
	public SjF() {
		super();
	}

public void order(ArrayList<Process>processes,int switchTime) {
	findWaitTime(processes , switchTime);

	for(int i=0;i<processes.size();i++) {
		 System.out.println("P "+processes.get(i).id+" >> order execution is "+processes.get(i).order);
	}
}
public void WaitingTime(ArrayList<Process>processes,int switchTime) {
	findWaitTime(processes , switchTime);

	for(int i=0;i<processes.size();i++) {
		 System.out.println("P "+processes.get(i).id+" >> Waiting Time is "+processes.get(i).waitingTime);
	}
}
public void turnAroundTime(ArrayList<Process>processes,int switchTime) {
	findWaitTime(processes , switchTime);
	for(int i=0;i<processes.size();i++) {
		 System.out.println("P "+processes.get(i).id+" >> turn Around Time is "+processes.get(i).turnAroundTime);
	}
}

public  void findWaitTime(ArrayList<Process> processes,int switchTime) {
	   int bt[]=new int[processes.size()]; 
	   for (int i = 0 ; i < processes.size() ; i++) 
	       bt[i] =  processes.get(i).BurstTime; 
	
    int st=0, totleCompletion=0;
int j=1;
int  complete=0;
boolean check=false;
	while(true){
		
    	int min=99,shortest=processes.size();
    	if (totleCompletion==processes.size())
    		break;
    
    	for (int i=0;i<processes.size();i++)
    	{
    		if ((processes.get(i).arrivalTime<=st) && (bt[i]<min)&&(bt[i]>0))
    		{	
    			min=bt[i];
    			shortest=i;
    			check=true;
    		}
    	}
    	
    	if (check==false){
    		st++;
    	    continue; 
    	}
    
    		bt[shortest]--;
    		
    		 min = bt[shortest]; 
    	        if (min== 0) 
    	        { min = 99;} 
    	        if (bt[shortest] == 0) { 
    	        	
    	        	totleCompletion++; 
    	            check = false;
    	            
    	        complete = st + 1; 
    	  
    	        processes.get(shortest).waitingTime = complete -  processes.get(shortest).arrivalTime  - processes.get(shortest).BurstTime; 
    	        processes.get(shortest).order=j;
    	            if (processes.get(shortest).waitingTime < 0) 
    	            	processes.get(shortest).waitingTime = 0; 
    	            j++;
    	        } else {
    	        	bt[shortest]=bt[shortest]+switchTime;
    	        }
    	        st++;  
    	        
    }
	 for(int i=0;i<processes.size();i++)
	    {
		 processes.get(i).turnAroundTime =processes.get(i).BurstTime +processes.get(i).waitingTime;

	    }	
		
}
public double AverageWaitingTime(ArrayList<Process>processes,int switchTime) {
	findWaitTime(processes, switchTime);
	int sum=0;
	int size=processes.size();
	double avg=0;
	for(int i=0;i<size;i++) {
		sum=sum+processes.get(i).waitingTime;
	}
	avg=(float)sum/(float)size;
	
	return avg;
}
public double AverageTurnaroundTime(ArrayList<Process>processes,int switchTime) {
	findWaitTime(processes, switchTime);

	int sum=0;
	int size=processes.size();
	double avg=0;
	for(int i=0;i<size;i++) {
		sum=sum+processes.get(i).turnAroundTime;
	}
	avg=(float)sum/(float)size;
	
	return avg;
}  

}
