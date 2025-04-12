class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;
    public Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}
class TaskCircularLinkedList {
    Task head = null;
    Task current = null;
    public void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);

        if (head == null) {
            head = newTask;
            head.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
    }
    public void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }
    public void addAtPosition(int position, int id, String name, int priority, String dueDate) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        int count = 1;
        while (count < position - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }
        newTask.next = temp.next;
        temp.next = newTask;
    }
    public void removeById(int id) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Task temp = head, prev = null;
        if (head.id == id) {
            if (head.next == head) {
                head = null;
                current = null;
            } else {
                Task last = head;
                while (last.next != head) {
                    last = last.next;
                }
                head = head.next;
                last.next = head;
                if (current.id == id) current = head;
            }
            System.out.println("Task with ID " + id + " removed.");
            return;
        }
        do {
            prev = temp;
            temp = temp.next;
            if (temp.id == id) {
                prev.next = temp.next;
                if (current.id == id) current = current.next;
                System.out.println("Task with ID " + id + " removed.");
                return;
            }
        } while (temp != head);
        System.out.println("Task not found.");
    }
    public void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks scheduled.");
            return;
        }
        System.out.println("Current Task: " + current.id + " | " + current.name +
                " | Priority: " + current.priority + " | Due: " + current.dueDate);
    }
    public void moveToNextTask() {
        if (current != null) {
            current = current.next;
        }
    }
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        System.out.println("All Tasks:");
        do {
            System.out.println("ID: " + temp.id + " | " + temp.name + " | Priority: " + temp.priority +
                    " | Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }
        boolean found = false;
        Task temp = head;
        do {
            if (temp.priority == priority) {
                System.out.println("Found: " + temp.name + " | ID: " + temp.id + " | Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with Priority " + priority);
        }
    }
}
public class TaskScheduler {
    public static void main(String[] args) {
        TaskCircularLinkedList scheduler = new TaskCircularLinkedList();
        scheduler.addAtEnd(16, "Email Client", 2, "2025-04-18");
        scheduler.addAtBeginning(12, "Prepare Report", 1, "2025-04-17");
        scheduler.addAtPosition(27, 103, "Team Meeting", 3, "2025-04-19");
        scheduler.displayAllTasks();
        System.out.println();
        scheduler.viewCurrentTask();
        scheduler.moveToNextTask();
        scheduler.viewCurrentTask();
        scheduler.moveToNextTask();
        scheduler.viewCurrentTask();
        System.out.println();
        scheduler.searchByPriority(2);
        scheduler.removeById(102);
        System.out.println();
        scheduler.displayAllTasks();
    }
}
