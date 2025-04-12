import java.util.*;
class User {
    int userID;
    String name;
    int age;
    Set<Integer> friendIDs;
    User next;

    public User(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendIDs = new HashSet<>();
        this.next = null;
    }
}
class SocialMedia {
    private User head;
    public void addUser(int userID, String name, int age) {
        User newUser = new User(userID, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newUser;
        }
    }
    private User findUser(int userID) {
        User temp = head;
        while (temp != null) {
            if (temp.userID == userID) return temp;
            temp = temp.next;
        }
        return null;
    }
    public void addFriendConnection(int userID1, int userID2) {
        User user1 = findUser(userID1);
        User user2 = findUser(userID2);
        if (user1 != null && user2 != null && userID1 != userID2) {
            user1.friendIDs.add(userID2);
            user2.friendIDs.add(userID1);
            System.out.println("Friend connection added between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("Invalid User IDs.");
        }
    }
    public void removeFriendConnection(int userID1, int userID2) {
        User user1 = findUser(userID1);
        User user2 = findUser(userID2);
        if (user1 != null && user2 != null) {
            user1.friendIDs.remove(userID2);
            user2.friendIDs.remove(userID1);
            System.out.println("Friend connection removed.");
        } else {
            System.out.println("Invalid User IDs.");
        }
    }
    public void displayFriends(int userID) {
        User user = findUser(userID);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Friends of " + user.name + ":");
        for (int fid : user.friendIDs) {
            User friend = findUser(fid);
            if (friend != null) {
                System.out.println("- " + friend.name + " (ID: " + friend.userID + ")");
            }
        }
    }
    public void findMutualFriends(int userID1, int userID2) {
        User user1 = findUser(userID1);
        User user2 = findUser(userID2);
        if (user1 == null || user2 == null) {
            System.out.println("Invalid User IDs.");
            return;
        }
        System.out.println("Mutual Friends between " + user1.name + " and " + user2.name + ":");
        for (int id : user1.friendIDs) {
            if (user2.friendIDs.contains(id)) {
                User mutual = findUser(id);
                if (mutual != null)
                    System.out.println("- " + mutual.name + " (ID: " + mutual.userID + ")");
            }
        }
    }
    public void searchUser(String query) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (String.valueOf(temp.userID).equals(query) || temp.name.equalsIgnoreCase(query)) {
                System.out.println("User found: " + temp.name + " (ID: " + temp.userID + ", Age: " + temp.age + ")");
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("User not found.");
    }
    public void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIDs.size() + " friend(s).");
            temp = temp.next;
        }
    }
    public void displayAllUsers() {
        User temp = head;
        System.out.println("All Users:");
        while (temp != null) {
            System.out.println("- " + temp.name + " (ID: " + temp.userID + ", Age: " + temp.age + ")");
            temp = temp.next;
        }
    }
}
public class SocialMediaDemo {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();
        sm.addUser(1, "Rohit", 25);
        sm.addUser(2, "Mahi", 24);
        sm.addUser(3, "Mahesh", 23);
        sm.addUser(4, "Ganesh", 22);
        sm.addFriendConnection(1, 2);
        sm.addFriendConnection(1, 3);
        sm.addFriendConnection(2, 4);
        sm.displayAllUsers();
        System.out.println();
        sm.displayFriends(1);
        sm.displayFriends(2);
        System.out.println();
        sm.findMutualFriends(1, 2);
        System.out.println();
        sm.searchUser("Rohit");
        sm.searchUser("3");
        sm.searchUser("Eve");
        System.out.println();
        sm.countFriends();
        System.out.println();
        sm.removeFriendConnection(1, 2);
        sm.displayFriends(1);
        sm.displayFriends(2);
    }
}
