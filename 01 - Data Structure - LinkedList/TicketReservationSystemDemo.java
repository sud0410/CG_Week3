class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;
    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}
class TicketReservationSystem {
    private Ticket head;
    private Ticket tail;
    public TicketReservationSystem() {
        head = tail = null;
    }
    public void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }
    }
    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }
        Ticket temp = head;
        Ticket prev = null;
        if (head.ticketId == ticketId) {
            if (head == tail) {
                head = tail = null;
            } else {
                tail.next = head.next;
                head = head.next;
            }
            System.out.println("Ticket with ID " + ticketId + " removed.");
            return;
        }
        do {
            prev = temp;
            temp = temp.next;
            if (temp.ticketId == ticketId) {
                prev.next = temp.next;
                if (temp == tail) {
                    tail = prev;
                }
                System.out.println("Ticket with ID " + ticketId + " removed.");
                return;
            }
        } while (temp != head);
        System.out.println("Ticket with ID " + ticketId + " not found.");
    }
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName +
                    ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber +
                    ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }
    public void searchTicketByCustomerName(String customerName) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket found: ID: " + temp.ticketId + ", Movie: " + temp.movieName +
                        ", Seat: " + temp.seatNumber + ", Booking Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tickets found for customer: " + customerName);
        }
    }
    public void searchTicketByMovieName(String movieName) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket found: ID: " + temp.ticketId + ", Customer: " + temp.customerName +
                        ", Seat: " + temp.seatNumber + ", Booking Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tickets found for movie: " + movieName);
        }
    }
    public int totalBookedTickets() {
        if (head == null) {
            return 0;
        }
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}

public class TicketReservationSystemDemo {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(1, "Sudarsan", "krish", "A1", "2023-04-01 18:00");
        system.addTicket(2, "jordan", "Avatar", "B2", "2023-04-01 19:00");
        system.addTicket(3, "chauz", "Titanic", "C3", "2023-04-02 15:30");
        system.displayTickets();
        system.removeTicket(2);
        system.displayTickets();
        system.searchTicketByCustomerName("Sudarsan");
        system.searchTicketByMovieName("Titanic");
        System.out.println("Total Booked Tickets: " + system.totalBookedTickets());
    }
}
