import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * @author Ismail Sengul 20200808603
 */
public class c20200808603 {
    static int totalTurnaroundTime = 0;
    static int totalWaitingTime = 0;
    public static void main(String[] args) {

        Queue<Process> readyQueue = new LinkedList<>();

        readFile(args, readyQueue);
        FCFS(readyQueue);

    }

    /**
     * @param args to get file from shell
     * @param readyQueue to keep processes
     * This method reads provided file line by line.
     * In each line, first number before : sign determines id process, each bracket determines a tuple of the process
     * Each tuple consists of ([cpu_burst_time],[io_burst_time])
     */
    public static void readFile(String[] args,Queue<Process> readyQueue) {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
            String line;

            while((line = bufferedReader.readLine())!=null){
                Process process = new Process();
                String[] processInfo  = line.split(":");
                process.pid = (processInfo[0]);
                String[] tuples = processInfo[1].split(";");

                for (String tuple : tuples) {

                    int cpu_burst = Integer.parseInt(tuple.split(",")[0]
                            .replace("(", "")
                            .replace(")", ""));


                    int io_burst = Integer.parseInt(tuple.split(",")[1]
                            .replace("(", "")
                            .replace(")", ""));
                    process.tuples.add(new Tuple(cpu_burst,io_burst));

                }
                readyQueue.add(process);
            }
            bufferedReader.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param readyQueue if a process is not waiting, it is in the processQueue
     * In this method, each process runs once at startup
     * After first run, if the current time is enough to execute the next process, that process executes.
     * Otherwise, an IDLE process executes.
     * Cpu burst time of the Idle process is calculates by
     * subtracting the current time from the turnaround time of process with the smallest turnaround time.
     * The process with the smallest turnaround time in the queue will be executed.
     */
    public static void FCFS(Queue<Process> readyQueue){

        int processCount = readyQueue.size();
        int currentTime = 0;

        Process idle = new Process();
        idle.pid = "idle";
        int idleCount = 0;

        while (!readyQueue.isEmpty()){
            if (currentTime >= readyQueue.peek().returnTime) {
                currentTime = execute(readyQueue.poll(),currentTime, readyQueue);
            }else{
                ArrayList<Integer> returnTimes = new ArrayList<>();
                for (Process process : readyQueue) {
                    if (process.returnTime > currentTime) {
                        returnTimes.add(process.returnTime);
                    }
                }
                Collections.sort(returnTimes);
                idle.tuples.add(new Tuple(returnTimes.get(0)-currentTime,0 ));
                currentTime = executeIdle(idle,currentTime);
                readyQueue.add(readyQueue.poll());
                idleCount++;

            }
        }
        System.out.println("HALT");
        System.out.println("Average Total Turnaround Time : " + (double)totalTurnaroundTime/processCount);
        System.out.println("Average Total Waiting Time : " + (double)totalWaitingTime/processCount);
        System.out.println("The idle process executed " + idleCount + " times");

    }

    /**
     * @param idle idle process
     * @param currentTime current time
     * @return current time
     * Executes idle process and changes current time
     */
    private static int executeIdle(Process idle, int currentTime) {
        idle.returnTime = currentTime + idle.tuples.peek().cpu_burst;
        currentTime+= Objects.requireNonNull(idle.tuples.poll()).cpu_burst;
        return currentTime;
    }

    /**
     * @param process next process in the queue
     * @param currentTime current time
     * @param readyQueue ready queue
     * @return current time
     * Executes next process in the queue and changes the current time
     * Calculates turnaround time and waiting time of each process
     * If a process in its last tuple, removes it from the queue.
     * Otherwise, executes the process and add it to the tail of the queue.
     */
    public static int execute(Process process,int currentTime,Queue<Process> readyQueue) {
        Tuple currentTuple = process.tuples.poll();

        if (currentTuple.io_burst != -1) {
            process.returnTime = currentTime + currentTuple.cpu_burst + currentTuple.io_burst;
            currentTime+=currentTuple.cpu_burst;
            process.cpuBurstTime+=currentTuple.cpu_burst;
            readyQueue.add(process);
        }else {
            process.returnTime = currentTime + currentTuple.cpu_burst;
            process.cpuBurstTime +=currentTuple.cpu_burst;
            process.waitingTime = process.returnTime - process.cpuBurstTime;
            totalTurnaroundTime += process.returnTime;
            totalWaitingTime+= process.waitingTime;
            currentTime+=currentTuple.cpu_burst;
        }


        return currentTime;
    }
}

class Process{

    String pid;

    Queue<Tuple> tuples = new LinkedList<>();

    int returnTime;
    int waitingTime;

    int cpuBurstTime = 0;

    @Override
    public String toString() {
        return pid + ":" + tuples;
    }
}

class Tuple{
    int cpu_burst;
    int io_burst;

    @Override
    public String toString() {
        return "(" + cpu_burst + "," + io_burst + ")";
    }

    public Tuple(int cpu_burst, int io_burst) {
        this.cpu_burst = cpu_burst;
        this.io_burst = io_burst;
    }
}
