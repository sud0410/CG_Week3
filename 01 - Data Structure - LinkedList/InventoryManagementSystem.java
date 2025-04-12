import java.util.Scanner;

class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;

    public Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    Item head = null;

    // Add at beginning
    public void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    // Add at end
    public void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        }
    }

    // Add at position (1-based)
    public void addAtPosition(int position, String name, int id, int quantity, double price) {
        if (position <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (position == 1) {
            addAtBeginning(name, id, quantity, price);
            return;
        }

        Item newItem = new Item(name, id, quantity, price);
        Item temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds.");
            return;
        }

        newItem.next = temp.next;
        temp.next = newItem;
    }

    // Remove by ID
    public void removeById(int id) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.id == id) {
            head = head.next;
            System.out.println("Item with ID " + id + " removed.");
            return;
        }

        Item temp = head;
        while (temp.next != null && temp.next.id != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Item not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Item with ID " + id + " removed.");
        }
    }

    // Update quantity
    public void updateQuantity(int id, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = newQuantity;
                System.out.println("Quantity updated for item ID " + id);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    // Search by ID
    public void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                printItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    // Search by Name
    public void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                printItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // Calculate total value
    public void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: $" + total);
    }

    // Display all items
    public void displayInventory() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        Item temp = head;
        System.out.println("\nInventory Items:");
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }

    private void printItem(Item item) {
        System.out.println("ID: " + item.id + ", Name: " + item.name +
                ", Quantity: " + item.quantity + ", Price: $" + item.price);
    }

    // Sort inventory
    public void sortInventory(String sortBy, boolean ascending) {
        head = mergeSort(head, sortBy, ascending);
        System.out.println("Inventory sorted by " + sortBy + " in " + (ascending ? "ascending" : "descending") + " order.");
    }

    // Merge sort for sorting
    private Item mergeSort(Item head, String sortBy, boolean ascending) {
        if (head == null || head.next == null) return head;

        Item middle = getMiddle(head);
        Item nextToMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, sortBy, ascending);
        Item right = mergeSort(nextToMiddle, sortBy, ascending);

        return sortedMerge(left, right, sortBy, ascending);
    }

    private Item sortedMerge(Item a, Item b, String sortBy, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;

        boolean condition;
        if (sortBy.equalsIgnoreCase("name")) {
            condition = ascending ? a.name.compareToIgnoreCase(b.name) < 0 : a.name.compareToIgnoreCase(b.name) > 0;
        } else {
            condition = ascending ? a.price < b.price : a.price > b.price;
        }

        Item result;
        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, sortBy, ascending);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, sortBy, ascending);
        }
        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;

        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Inventory Management Menu ---");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item by ID");
            System.out.println("3. Update Quantity");
            System.out.println("4. Search Item by ID");
            System.out.println("5. Search Item by Name");
            System.out.println("6. Display Inventory");
            System.out.println("7. Calculate Total Value");
            System.out.println("8. Sort Inventory");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter position (beginning/end/position): ");
                    String pos = sc.nextLine().toLowerCase();
                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    if (pos.equals("beginning")) {
                        inventory.addAtBeginning(name, id, qty, price);
                    } else if (pos.equals("end")) {
                        inventory.addAtEnd(name, id, qty, price);
                    } else {
                        System.out.print("Enter position number: ");
                        int position = sc.nextInt();
                        inventory.addAtPosition(position, name, id, qty, price);
                    }
                    break;

                case 2:
                    System.out.print("Enter Item ID to remove: ");
                    inventory.removeById(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter Item ID to update: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQty = sc.nextInt();
                    inventory.updateQuantity(updateId, newQty);
                    break;

                case 4:
                    System.out.print("Enter Item ID to search: ");
                    inventory.searchById(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter Item Name to search: ");
                    inventory.searchByName(sc.nextLine());
                    break;

                case 6:
                    inventory.displayInventory();
                    break;

                case 7:
                    inventory.calculateTotalValue();
                    break;

                case 8:
                    System.out.print("Sort by (name/price): ");
                    String sortBy = sc.nextLine();
                    System.out.print("Ascending? (true/false): ");
                    boolean asc = sc.nextBoolean();
                    inventory.sortInventory(sortBy, asc);
                    break;

                case 9:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 9);
        sc.close();
    }
}
