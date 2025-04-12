class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie prev, next;
    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = this.next = null;
    }
}
class MovieDoublyLinkedList {
    Movie head = null, tail = null;
    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }
    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }
    public void addAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        newMovie.next = temp.next;
        newMovie.prev = temp;
        temp.next.prev = newMovie;
        temp.next = newMovie;
    }
    public void removeByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Movie not found.");
            return;
        }

        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (temp == tail) {
            tail = temp.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        System.out.println("Movie \"" + title + "\" removed.");
    }
    public void searchByDirector(String director) {
        boolean found = false;
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println("Found: " + temp.title + " (" + temp.year + "), Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found by Director: " + director);
    }
    public void searchByRating(double rating) {
        boolean found = false;
        Movie temp = head;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println("Found: " + temp.title + " directed by " + temp.director + " (" + temp.year + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movies found with Rating: " + rating);
    }
    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Updated rating of \"" + title + "\" to " + newRating);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found.");
    }
    public void displayForward() {
        if (head == null) {
            System.out.println("No movies to display.");
            return;
        }
        System.out.println("Movies (Forward Order):");
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | Rating: " + temp.rating);
            temp = temp.next;
        }
    }
    public void displayReverse() {
        if (tail == null) {
            System.out.println("No movies to display.");
            return;
        }
        System.out.println("Movies (Reverse Order):");
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
}
public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieDoublyLinkedList list = new MovieDoublyLinkedList();
        list.addAtEnd("master", "loki", 2010, 8.8);
        list.addAtBeginning("managaram", "loki", 2014, 8.6);
        list.addAtEnd("Vikram", "Lokesh Kanagaraj", 2022, 8.3);
        list.addAtPosition(2, "Kaithi", "Lokesh Kanagaraj", 2019, 8.7);
        list.displayForward();
        System.out.println();
        list.searchByDirector("loki");
        list.searchByRating(8.6);
        System.out.println();
        list.updateRating("Vikram", 9.0);
        list.removeByTitle("Kaithi");
        System.out.println();
        list.displayForward();
        System.out.println();
        list.displayReverse();
    }
}
