import java.util.Scanner;

class Book {
    String title, author, genre, bookID;
    boolean isAvailable;
    Book prev, next;

    Book(String title, String author, String genre, String bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.prev = null;
        this.next = null;
    }
}

class Library {
    Book head = null, tail = null;

    void addBook(String title, String author, String genre, String bookID, boolean isAvailable, String position, int posNum) {
        Book newBook = new Book(title, author, genre, bookID, isAvailable);

        if (head == null) {
            head = tail = newBook;
            return;
        }

        if (position.equalsIgnoreCase("beginning")) {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        } else if (position.equalsIgnoreCase("end")) {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        } else if (position.equalsIgnoreCase("position")) {
            if (posNum <= 1) {
                addBook(title, author, genre, bookID, isAvailable, "beginning", 0);
                return;
            }
            Book temp = head;
            for (int i = 1; temp != null && i < posNum - 1; i++) temp = temp.next;

            if (temp == null || temp.next == null) {
                addBook(title, author, genre, bookID, isAvailable, "end", 0);
            } else {
                newBook.next = temp.next;
                newBook.prev = temp;
                temp.next.prev = newBook;
                temp.next = newBook;
            }
        }
    }

    void removeBook(String bookID) {
        Book temp = head;
        while (temp != null && !temp.bookID.equals(bookID)) temp = temp.next;

        if (temp == null) {
            System.out.println("Book not found.");
            return;
        }

        if (temp == head) head = head.next;
        if (temp == tail) tail = tail.prev;

        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;

        System.out.println("Book with ID " + bookID + " removed.");
    }

    void searchBookByTitleOrAuthor(String keyword) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(keyword) || temp.author.equalsIgnoreCase(keyword)) {
                displayBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No books found with given title or author.");
    }

    void updateAvailability(String bookID, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookID.equals(bookID)) {
                temp.isAvailable = status;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    void displayForward() {
        Book temp = head;
        System.out.println("\nBooks in Library (Forward):");
        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Book temp = tail;
        System.out.println("\nBooks in Library (Reverse):");
        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }

    void displayBook(Book b) {
        System.out.println("ID: " + b.bookID + ", Title: " + b.title + ", Author: " + b.author +
                ", Genre: " + b.genre + ", Available: " + (b.isAvailable ? "Yes" : "No"));
    }

    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total number of books: " + count);
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title or Author");
            System.out.println("4. Update Availability");
            System.out.println("5. Display Books Forward");
            System.out.println("6. Display Books Reverse");
            System.out.println("7. Count Books");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Enter Book ID: ");
                    String id = sc.nextLine();
                    System.out.print("Is Available? (true/false): ");
                    boolean avail = sc.nextBoolean();
                    sc.nextLine();
                    System.out.print("Enter position (beginning/end/position): ");
                    String pos = sc.nextLine();
                    int posNum = 0;
                    if (pos.equalsIgnoreCase("position")) {
                        System.out.print("Enter position number: ");
                        posNum = sc.nextInt();
                        sc.nextLine();
                    }
                    lib.addBook(title, author, genre, id, avail, pos, posNum);
                    break;

                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    String removeID = sc.nextLine();
                    lib.removeBook(removeID);
                    break;

                case 3:
                    System.out.print("Enter Title or Author to search: ");
                    String keyword = sc.nextLine();
                    lib.searchBookByTitleOrAuthor(keyword);
                    break;

                case 4:
                    System.out.print("Enter Book ID to update: ");
                    String updateID = sc.nextLine();
                    System.out.print("Enter new availability (true/false): ");
                    boolean newStatus = sc.nextBoolean();
                    sc.nextLine();
                    lib.updateAvailability(updateID, newStatus);
                    break;

                case 5:
                    lib.displayForward();
                    break;

                case 6:
                    lib.displayReverse();
                    break;

                case 7:
                    lib.countBooks();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        sc.close();
    }
}
