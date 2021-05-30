package ass3;

import java.util.ArrayList;

public class TimesCalculator {
	protected ArrayList<Process> processesTimeCalculator = new ArrayList<Process>();
	protected ArrayList<Integer> processesBurstTime = new ArrayList<Integer>();
	protected ArrayList<Integer> processesTurnArroundTime = new ArrayList<Integer>();
	
	public TimesCalculator(ArrayList<Process> processesTimeCalculator) {
		this.processesTimeCalculator = processesTimeCalculator;
	}
	
	public void burstTimeCP()
	{
		for (int i = 0 ; i < processesTimeCalculator.size() ;i++) 
		{
			processesBurstTime.add(processesTimeCalculator.get(i).getBurstTime());
		}
	}

	public void completionTimeCalculation(ArrayList<Process> processArrayList)
	{
		System.out.println("Processes execution order");
		int time = 0;
		int numberOfCompletedProcesses = 0;
		int n = processArrayList.size();
		while(numberOfCompletedProcesses != n)
		{
			int indexOfProcess = -10;
			int minumumPriority = Integer.MAX_VALUE;
			for(int i = 0 ; i < n ; i++)
			{
				if(processArrayList.get(i).getArrivalTime() <= time && !processArrayList.get(i).isCompleted())
				{
					if(processArrayList.get(i).getPriority() < minumumPriority)
					{
						minumumPriority = processArrayList.get(i).getPriority();
						indexOfProcess = i;
					}
				if(processArrayList.get(i).getPriority() == minumumPriority)
				{
					if(processArrayList.get(i).getArrivalTime() < processArrayList.get(indexOfProcess).getArrivalTime())
					{
						minumumPriority = processArrayList.get(i).getPriority();
						indexOfProcess = i;
					}
				}
			}
			}
			if(indexOfProcess !=-10)
			{
				processArrayList.get(indexOfProcess).setBurstTime(processArrayList.get(indexOfProcess).getBurstTime() - 1);
				System.out.print(processArrayList.get(indexOfProcess).getName()+" -> "+(time+1)+" ");
				time++;
				if(processArrayList.get(indexOfProcess).getBurstTime() == 0)
				{
					processArrayList.get(indexOfProcess).setCompletionTime(time);
					numberOfCompletedProcesses++;
				}
			}
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------------");
	}
	
	public void turnAroundTimeCalculation()
	{
		System.out.println("Turn around time");
		double turnAroundTimeAverage = 0.0;
		for (int i = 0; i < processesTimeCalculator.size(); i++) {
			int turnAroundTime = processesTimeCalculator.get(i).getCompletionTime() - processesTimeCalculator.get(i).getArrivalTime();
			processesTurnArroundTime.add(turnAroundTime);
			System.out.println(processesTimeCalculator.get(i).getName()+"->"+turnAroundTime);
			turnAroundTimeAverage+=turnAroundTime;
		}
		System.out.println("Turn around time average = "+turnAroundTimeAverage/(double)processesTimeCalculator.size());
		System.out.println("-------------------------------------------------------------------------");
	}
	
	public void waitingTimeCalculation()
	{
		System.out.println("Wating time");
		double watingTimeAverage = 0.0;
		for (int i = 0; i < processesTimeCalculator.size(); i++) {
			int watingTime = processesTurnArroundTime.get(i) - processesBurstTime.get(i);
			System.out.println(processesTimeCalculator.get(i).getName()+"->"+watingTime);
			watingTimeAverage+=watingTime;
		}
		System.out.println("Wating time average = "+watingTimeAverage/(double)processesTimeCalculator.size());
		System.out.println("-------------------------------------------------------------------------");
	}
	
	public void doCalculation()
	{
		burstTimeCP();
		completionTimeCalculation(processesTimeCalculator);
		turnAroundTimeCalculation();
		waitingTimeCalculation();
	}
}
