package ass3;

public class Process {
	protected  int  BurstTime;
	protected String name;
	protected int turnAroundTime;
	protected int  waitingTime;
	protected int order;
	protected int completionTime;
	protected int arrivalTime;
	protected int numberOfProcesses;
	protected int id;
	protected int quantum;
	protected int tat;
	protected int queueNo;
	protected int priority;
	protected int TimeReminder;
	public Process() {
		this.name = "";
		this.arrivalTime = 0;
		this.BurstTime = 0;
		this.completionTime = -99;
		this.priority = -99;
	}
	public Process(String processName , int arrivalTime , int burstTime , int priority) {
		this.name = processName;
		this.arrivalTime = arrivalTime;
		this.BurstTime = burstTime;
		this.completionTime = -99;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	public void setTat(int tat) {
		this.tat = tat;
	}
	public int getTat() {
		return tat;
	}
	public void setBurstTime(int burstTime) {
		BurstTime = burstTime;
	}
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setTurnAroundTime(int turnAroundTime) {
		this.turnAroundTime = turnAroundTime;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public void setNumberOfProcesses(int numberOfProcesses) {
		this.numberOfProcesses = numberOfProcesses;
	}
	public int getWaitingTime() {
		return waitingTime;
	}

	public String getName() {
		return name;
	}
	public int getTurnAroundTime() {
		return turnAroundTime;
	}
	public int getOrder() {
		return order;
	}
	public int getCompletionTime() {
		return completionTime;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public int getNumberOfProcesses() {
		return numberOfProcesses;
	}
	public int getId() {
		return id;
	}
	public int getQuantum() {
		return quantum;
	}
	public int getBurstTime() {
		return BurstTime;
	}
	public void setQueueNo(int queueNo) {
		this.queueNo = queueNo;
	}
	public int getQueueNo() {
		return queueNo;
	}
	
	public boolean isCompleted()
	{
		if(completionTime != -99) return true;
		return false;
	}
}
