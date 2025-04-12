import java.util.*;
class Process {
    int pid;
    int burstTime;
    int priority;
    int remainingTime;
    Process next;
    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}
class RoundRobinScheduler {
    Process head = null;
    Process tail = null;
    int timeQuantum;
    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }
    public void removeProcess(Process processToRemove) {
        if (head == null) return;
        if (head == processToRemove && head == tail) {
            head = null;
            tail = null;
            return;
        }
        Process current = head;
        Process prev = tail;
        do {
            if (current == processToRemove) {
                prev.next = current.next;
                if (current == head) head = current.next;
                if (current == tail) tail = prev;
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in queue.");
            return;
        }
        Process current = head;
        System.out.println("Process Queue:");
        do {
            System.out.printf("PID: %d, Burst: %d, Remaining: %d, Priority: %d%n",
                    current.pid, current.burstTime, current.remainingTime, current.priority);
            current = current.next;
        } while (current != head);
        System.out.println();
    }
    public void simulate() {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }
        Map<Integer, Integer> waitingTime = new HashMap<>();
        Map<Integer, Integer> turnaroundTime = new HashMap<>();
        Map<Integer, Integer> executedTime = new HashMap<>();
        int time = 0;
        Process current = head;
        while (head != null) {
            if (current.remainingTime > timeQuantum) {
                time += timeQuantum;
                current.remainingTime -= timeQuantum;
                executedTime.put(current.pid, time);
            } else {
                time += current.remainingTime;
                executedTime.put(current.pid, time);
                current.remainingTime = 0;
                turnaroundTime.put(current.pid, time);
                waitingTime.put(current.pid, time - current.burstTime);
                Process toRemove = current;
                current = current.next;
                removeProcess(toRemove);
                if (head == null) break;
            }
            displayProcesses();
            current = current.next;
        }
        double totalWT = 0, totalTAT = 0;
        System.out.println("\nFinal Results:");
        for (int pid : turnaroundTime.keySet()) {
            int wt = waitingTime.get(pid);
            int tat = turnaroundTime.get(pid);
            totalWT += wt;
            totalTAT += tat;
            System.out.printf("PID: %d, Waiting Time: %d, Turnaround Time: %d%n", pid, wt, tat);
        }
        System.out.printf("\nAverage Waiting Time: %.2f%n", totalWT / turnaroundTime.size());
        System.out.printf("Average Turnaround Time: %.2f%n", totalTAT / turnaroundTime.size());
    }
}
public class RoundRobinDemo {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(4);
        scheduler.addProcess(1, 10, 1);
        scheduler.addProcess(2, 5, 2);
        scheduler.addProcess(3, 8, 1);
        scheduler.displayProcesses();
        scheduler.simulate();
    }
}
